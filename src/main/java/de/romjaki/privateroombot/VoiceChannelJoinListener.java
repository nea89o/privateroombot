package de.romjaki.privateroombot;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import static de.romjaki.privateroombot.Config.CONFIG;

public class VoiceChannelJoinListener extends ListenerAdapter {
    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
        if (!event.getChannelJoined().getId().equals(CONFIG.from_channel_id)) {
            return;
        }
        Guild guild = event.getGuild();
        Member member = event.getMember();
        Category category = guild.getCategoryById(CONFIG.category_id);
        VoiceChannel newChannel = (VoiceChannel) category.createVoiceChannel(member.getEffectiveName() + " - privat").complete();
        newChannel.createPermissionOverride(member).setAllow(Permission.VOICE_MOVE_OTHERS).queue();
        guild.getController().moveVoiceMember(member, newChannel).queue();
    }

    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
        VoiceChannelJoinListener.this.onGuildVoiceLeave(
                new GuildVoiceLeaveEvent(
                        event.getJDA(),
                        event.getResponseNumber(),
                        event.getMember(),
                        event.getChannelLeft()
                )
        );
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
        if (!event.getChannelLeft().getParent().getId().equals(CONFIG.category_id)) {
            return;
        }
        VoiceChannel channel = event.getChannelLeft();
        if (!channel.getMembers().isEmpty()) {
            return;
        }
        if (channel.getId().equals(CONFIG.from_channel_id)) {
            return;
        }
        channel.delete().queue();
    }
}

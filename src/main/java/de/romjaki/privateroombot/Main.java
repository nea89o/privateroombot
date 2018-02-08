package de.romjaki.privateroombot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

import static de.romjaki.privateroombot.Config.CONFIG;

public class Main {

    public static Gson gson = new GsonBuilder()
            .create();

    public static JDA jda;
    public static void main(String[] args) throws LoginException {
        jda = new JDABuilder(AccountType.BOT)
                .setToken(CONFIG.token)
                .addEventListener(new VoiceChannelJoinListener())
                .buildAsync();
    }
}

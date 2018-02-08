## privateroombot

this bot allows you to automatically create private voice chats. per person.

simply join a voice channel and get redirected to your own private channel where you can move all your friends in.

### setup

download a release and put in a separate folder.
also add a file called `config.json` with the following content:

```json
{
  "token": "TOKEN",
  "from_channel_id": "the channel you want people to join in order to get an own channel. see ID's",
  "category_id": "the channel category you want all channels to be collected in. see ID's"
}
```
you can get your bots token from [the discord developers page][1]
there create an application with botuser associated. paste the bot users token in the config.

#### on your server

create a channel category for all private channels. put the "get private channel"-channel in there, but NOT the "wait for move"-channel. disallow connecting for the category, but explicitly reallow it for the "get private room"-channel. create a "waiting for move"-channel and allow moving others for @everyone and disallow speaking. done.


#### ids
discord ids identify a channel/user. you can view them by 

- going to your settings -> appearance -> developer mode
- right-click on the channel -> copy id. then paste in the `config.json`

[1]: https://discordapp.com/developers/applications/me
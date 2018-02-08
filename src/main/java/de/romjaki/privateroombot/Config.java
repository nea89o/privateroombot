package de.romjaki.privateroombot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Config {

    public static Config CONFIG;

    static {
        try {
            CONFIG = Main.gson.fromJson(new FileReader(new File("config.json")), Config.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    String token;
    String from_channel_id;
    String category_id;
}

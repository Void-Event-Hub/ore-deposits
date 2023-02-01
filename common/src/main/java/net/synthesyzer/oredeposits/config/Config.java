package net.synthesyzer.oredeposits.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.synthesyzer.oredeposits.OreDeposits;
import net.synthesyzer.oredeposits.platform.ConfigHelper;
import net.synthesyzer.oredeposits.util.TimeUnit;

import java.io.*;

public class Config {

    private static JsonObject configs = new JsonObject();

    public static void registerConfigs() {
        OreDeposits.LOGGER.info("Registering configs");
        File serverConfig = ConfigHelper.getConfigDirectory().resolve(OreDeposits.MOD_ID + "-server.json").toFile();

        Gson gson;

        try {
            gson = new Gson();
            configs = gson.fromJson(new FileReader(serverConfig), JsonObject.class);
        } catch (FileNotFoundException e) {
            OreDeposits.LOGGER.info("Creating new server config file");

            try (Writer writer = new FileWriter(serverConfig)) {
                gson = new GsonBuilder().setPrettyPrinting().create();
                JsonObject defaultConfig = new JsonObject();
                defaultConfig.add("coal", new JsonPrimitive(30));
                defaultConfig.add("copper", new JsonPrimitive(45));
                defaultConfig.add("diamond", new JsonPrimitive(180));
                defaultConfig.add("emerald", new JsonPrimitive(120));
                defaultConfig.add("gold", new JsonPrimitive(120));
                defaultConfig.add("iron", new JsonPrimitive(60));
                defaultConfig.add("lapis", new JsonPrimitive(45));
                defaultConfig.add("redstone", new JsonPrimitive(45));
                defaultConfig.add("aquarite", new JsonPrimitive(90));
                defaultConfig.add("flamnite", new JsonPrimitive(90));
                defaultConfig.add("voidium", new JsonPrimitive(90));

                writer.write(gson.toJson(defaultConfig));
                configs = defaultConfig;
            } catch (IOException ee) {
                throw new RuntimeException("Could not create server config file");
            }
        }

        for (var entry : configs.entrySet()) {
            try {
                int value = entry.getValue().getAsInt();

                if (value < 1) {
                    throw new RuntimeException("Invalid config value for " + entry.getKey() + ". Make sure the value is greater than 0");
                }
            } catch (ClassCastException | NumberFormatException eeee) {
                throw new RuntimeException("Invalid config value for " + entry.getKey() + ". Make sure you used valid numbers in your config file");
            }
        }
    }

    public static TimeUnit getDepositTimer(String key) {
        TimeUnit defaultTime = TimeUnit.inSeconds(30);

        try {
            return TimeUnit.inSeconds(configs.get(key).getAsInt());
        } catch (Exception e) {
            e.printStackTrace();
            return defaultTime;
        }
    }

}

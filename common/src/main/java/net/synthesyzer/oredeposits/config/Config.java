package net.synthesyzer.oredeposits.config;

import com.moandjiezana.toml.Toml;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.platform.Platform;
import net.synthesyzer.oredeposits.OreDeposits;
import net.synthesyzer.oredeposits.util.TimeUnit;

import java.nio.file.Path;

public class Config {

    private static Toml serverToml;

    public static void registerConfigs() {
        serverToml = getServerConfig();
    }

    @ExpectPlatform
    private static Path getConfigDirectory() {
        return Platform.getConfigFolder();
    }

    private static Toml getServerConfig() {
        return new Toml().read(getConfigDirectory().resolve(OreDeposits.MOD_ID + "-server.toml").toFile());
    }

    public static TimeUnit getDepositTimer(String key) {
        OreDeposits.LOGGER.info("Getting deposit timer for key: " + key);
        return TimeUnit.inSeconds(10);
    }

}

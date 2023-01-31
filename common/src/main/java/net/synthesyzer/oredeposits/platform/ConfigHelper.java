package net.synthesyzer.oredeposits.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.platform.Platform;

import java.nio.file.Path;

public class ConfigHelper {

    @ExpectPlatform
    public static Path getConfigDirectory() {
        return Platform.getConfigFolder();
    }

}

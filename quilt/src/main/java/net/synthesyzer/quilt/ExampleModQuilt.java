package net.synthesyzer.quilt;

import net.synthesyzer.fabriclike.ExampleModFabricLike;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class ExampleModQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        ExampleModFabricLike.init();
    }
}

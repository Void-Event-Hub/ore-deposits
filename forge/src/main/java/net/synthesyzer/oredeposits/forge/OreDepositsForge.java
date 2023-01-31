package net.synthesyzer.oredeposits.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.synthesyzer.oredeposits.OreDeposits;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.synthesyzer.oredeposits.platform.forge.RegistryHelperImpl;

@Mod(OreDeposits.MOD_ID)
public class OreDepositsForge {
    public OreDepositsForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        EventBuses.registerModEventBus(OreDeposits.MOD_ID, bus);
        RegistryHelperImpl.BLOCKS.register(bus);
        RegistryHelperImpl.ITEMS.register(bus);
        RegistryHelperImpl.BLOCK_ENTITIES.register(bus);

        OreDeposits.init();
    }
}

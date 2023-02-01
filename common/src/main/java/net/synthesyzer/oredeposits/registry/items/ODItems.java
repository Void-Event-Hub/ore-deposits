package net.synthesyzer.oredeposits.registry.items;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.synthesyzer.oredeposits.OreDeposits;
import net.synthesyzer.oredeposits.misc.ODCreativeModeTab;

import static net.synthesyzer.oredeposits.OreDeposits.MOD_ID;

public class ODItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registry.ITEM_REGISTRY);

    public static final RegistrySupplier<Item> AQUARITE = ITEMS.register("aquarite", () -> new Item(new Item.Properties().tab(ODCreativeModeTab.DD_TAB)));
    public static final RegistrySupplier<Item> FLAMNITE = ITEMS.register("flamnite", () -> new Item(new Item.Properties().tab(ODCreativeModeTab.DD_TAB)));
    public static final RegistrySupplier<Item> VOIDIUM = ITEMS.register("voidium", () -> new Item(new Item.Properties().tab(ODCreativeModeTab.DD_TAB)));

    public static void register() {
        OreDeposits.LOGGER.info("Registering items");
        ITEMS.register();
    }

}

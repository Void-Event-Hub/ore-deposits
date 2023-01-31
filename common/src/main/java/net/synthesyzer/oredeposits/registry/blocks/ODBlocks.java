package net.synthesyzer.oredeposits.registry.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.synthesyzer.oredeposits.OreDeposits;
import net.synthesyzer.oredeposits.misc.ODCreativeModeTab;
import net.synthesyzer.oredeposits.platform.RegistryHelper;
import net.synthesyzer.oredeposits.registry.blocks.custom.block.*;

import java.util.*;
import java.util.function.Supplier;

public class ODBlocks {

    public static final Map<String, Supplier<Block>> BLOCKS = new HashMap<>();

    public static final Supplier<ChargingDepositBlock> CHARGING_DEPOSIT = registerBlock("charging_deposit", true, () -> new ChargingDepositBlock(Block.Properties.copy(Blocks.BEDROCK)));

    public static final Supplier<DepositBlock> COAL_DEPOSIT = registerBlock("coal_deposit", true, () -> new DepositBlock(Block.Properties.copy(Blocks.COAL_ORE), "coal"));
    public static final Supplier<DepositBlock> COPPER_DEPOSIT = registerBlock("copper_deposit", true, () -> new DepositBlock(Block.Properties.copy(Blocks.COPPER_ORE), "copper"));
    public static final Supplier<DepositBlock> DIAMOND_DEPOSIT = registerBlock("diamond_deposit", true, () -> new DepositBlock(Block.Properties.copy(Blocks.DIAMOND_ORE), "diamond"));
    public static final Supplier<DepositBlock> EMERALD_DEPOSIT = registerBlock("emerald_deposit", true, () -> new DepositBlock(Block.Properties.copy(Blocks.EMERALD_ORE), "emerald"));
    public static final Supplier<DepositBlock> GOLD_DEPOSIT = registerBlock("gold_deposit", true, () -> new DepositBlock(Block.Properties.copy(Blocks.GOLD_ORE), "gold"));
    public static final Supplier<DepositBlock> IRON_DEPOSIT = registerBlock("iron_deposit", true, () -> new DepositBlock(Block.Properties.copy(Blocks.IRON_ORE), "iron"));
    public static final Supplier<DepositBlock> LAPIS_DEPOSIT = registerBlock("lapis_deposit", true, () -> new DepositBlock(Block.Properties.copy(Blocks.LAPIS_ORE), "lapis"));
    public static final Supplier<DepositBlock> REDSTONE_DEPOSIT = registerBlock("redstone_deposit", true, () -> new DepositBlock(Block.Properties.copy(Blocks.LAPIS_ORE), "redstone"));

    @SuppressWarnings("unchecked")
    private static <T extends Block> Supplier<T> registerBlock(String name, boolean createItem, Supplier<T> block) {
        Supplier<T> toReturn = RegistryHelper.registerBlock(name, block);
        BLOCKS.put(name, (Supplier<Block>) toReturn);
        if (createItem) RegistryHelper.registerItem(name, () -> new BlockItem(toReturn.get(), new Item.Properties().tab(ODCreativeModeTab.DD_TAB)));
        return toReturn;
    }

    public static void registerBlocks() {
        OreDeposits.LOGGER.info("Synth's Ore Deposits blocks have been registered");
    }

}

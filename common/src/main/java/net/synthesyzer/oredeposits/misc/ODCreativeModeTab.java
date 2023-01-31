package net.synthesyzer.oredeposits.misc;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.synthesyzer.oredeposits.platform.RegistryHelper;

public class ODCreativeModeTab {

    public static final CreativeModeTab DD_TAB = RegistryHelper.registerCreativeModeTab(() -> new ItemStack(Items.IRON_PICKAXE));
}

package net.synthesyzer.oredeposits.registry.blocks;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.synthesyzer.oredeposits.OreDeposits;
import net.synthesyzer.oredeposits.platform.RegistryHelper;
import net.synthesyzer.oredeposits.registry.blocks.custom.entity.ChargingDepositBlockEntity;

import java.util.function.Supplier;

public class ODBlockEntities {

    public static final Supplier<BlockEntityType<ChargingDepositBlockEntity>> CHARGING_DEPOSIT_BLOCK_ENTITY = RegistryHelper.registerBlockEntity("charging_deposit_block_entity", () -> BlockEntityType.Builder.of(ChargingDepositBlockEntity::new, ODBlocks.CHARGING_DEPOSIT.get()).build(null));

    public static void registerBlockEntities() {
        OreDeposits.LOGGER.info("Synth's Ore Deposits block entities have been registered");
    }

}

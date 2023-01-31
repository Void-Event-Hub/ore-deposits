package net.synthesyzer.oredeposits.registry.blocks.custom.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.synthesyzer.oredeposits.registry.blocks.ODBlockEntities;
import net.synthesyzer.oredeposits.registry.blocks.custom.entity.ChargingDepositBlockEntity;
import org.jetbrains.annotations.Nullable;

public class ChargingDepositBlock extends BaseEntityBlock implements EntityBlock {

    public ChargingDepositBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ChargingDepositBlockEntity(blockPos, blockState);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ODBlockEntities.CHARGING_DEPOSIT_BLOCK_ENTITY.get(), ChargingDepositBlockEntity::tick);
    }
}

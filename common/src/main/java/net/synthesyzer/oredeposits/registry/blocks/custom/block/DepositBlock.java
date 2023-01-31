package net.synthesyzer.oredeposits.registry.blocks.custom.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

public class DepositBlock extends Block {

    private final String configName;

    public DepositBlock(Properties properties, String configName) {
        super(properties);
        this.configName = configName;
    }

    public String getConfigName() {
        return configName;
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState blockState) {
        return PushReaction.BLOCK;
    }
}

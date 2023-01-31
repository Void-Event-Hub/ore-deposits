package net.synthesyzer.oredeposits.forge.jade;


import mcp.mobius.waila.api.BlockAccessor;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IServerDataProvider;
import mcp.mobius.waila.api.ITooltip;
import mcp.mobius.waila.api.config.IPluginConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.synthesyzer.oredeposits.registry.blocks.custom.entity.ChargingDepositBlockEntity;
import net.synthesyzer.oredeposits.util.TimeUnit;

public enum DepositComponentProvider implements IComponentProvider, IServerDataProvider<BlockEntity> {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig iPluginConfig) {
        if (accessor.getServerData().contains("chargeTime") && accessor.getServerData().contains("progress")) {
            TimeUnit chargeTime = TimeUnit.inTicks(accessor.getServerData().getInt("chargeTime"));
            TimeUnit progress = TimeUnit.inTicks(accessor.getServerData().getInt("progress"));
            tooltip.add(new TextComponent("Cooldown: " + chargeTime.subtract(progress) ));
        }
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, ServerPlayer serverPlayer, Level level, BlockEntity entity, boolean b) {
        ChargingDepositBlockEntity blockEntity = (ChargingDepositBlockEntity) entity;

        compoundTag.putInt("progress", blockEntity.getProgress().getTicks());

        if (blockEntity.getChargeTime() != null) {
            compoundTag.putInt("chargeTime", blockEntity.getChargeTime().getTicks());
        }
    }
}

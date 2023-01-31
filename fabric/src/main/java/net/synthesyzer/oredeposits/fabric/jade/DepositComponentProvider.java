package net.synthesyzer.oredeposits.fabric.jade;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.synthesyzer.oredeposits.OreDeposits;
import net.synthesyzer.oredeposits.registry.blocks.custom.entity.ChargingDepositBlockEntity;
import net.synthesyzer.oredeposits.util.TimeUnit;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum DepositComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockEntity> {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (accessor.getServerData().contains("chargeTime") && accessor.getServerData().contains("progress")) {
            TimeUnit chargeTime = TimeUnit.inTicks(accessor.getServerData().getInt("chargeTime"));
            TimeUnit progress = TimeUnit.inTicks(accessor.getServerData().getInt("progress"));
            tooltip.add(new TextComponent("Cooldown: " + chargeTime.subtract(progress)));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(OreDeposits.MOD_ID, "progress");
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

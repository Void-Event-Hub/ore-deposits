package net.synthesyzer.oredeposits.registry.blocks.custom.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.synthesyzer.oredeposits.config.Config;
import net.synthesyzer.oredeposits.registry.blocks.ODBlockEntities;
import net.synthesyzer.oredeposits.registry.blocks.custom.block.DepositBlock;
import net.synthesyzer.oredeposits.registry.events.DepositBreakEventHandler;
import net.synthesyzer.oredeposits.util.TimeUnit;

import java.util.Random;

public class ChargingDepositBlockEntity extends BlockEntity {

    private TimeUnit chargeTime;
    private int progress;
    private DepositBlock block;

    public ChargingDepositBlockEntity(BlockPos pos, BlockState state) {
        super(ODBlockEntities.CHARGING_DEPOSIT_BLOCK_ENTITY.get(), pos, state);
        this.progress = 0;
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, ChargingDepositBlockEntity entity) {
        if (level.isClientSide()) {
            return;
        }

        entity.progress++;

        if (entity.block == null) {
            entity.setBlock(DepositBreakEventHandler.brokenBlocks.get(blockPos));
            return;
        }
        if (entity.chargeTime == null) {
            entity.setChargeTime(Config.getDepositTimer(entity.getBlock().getConfigName()));
            return;
        }

        boolean is1SecondBeforeCompletion = entity.getProgress().getTicks() == entity.getChargeTime().subtract(TimeUnit.inSeconds(1)).getTicks();

        if (is1SecondBeforeCompletion) {
            for (int i = 0; i < 360; i += 45) {
                ((ServerLevel) level).sendParticles(
                        ParticleTypes.LARGE_SMOKE,
                        blockPos.getX() + 0.5d,
                        blockPos.getY() + 0.5d,
                        blockPos.getZ() + 0.5d,
                        1,
                        new Random().nextBoolean() ? 0.3 : -0.3,
                        new Random().nextBoolean() ? 0.3 : -0.3,
                        new Random().nextBoolean() ? 0.3 : -0.3,
                        0.01
                );
            }
            level.playSound(null, blockPos, SoundEvents.CHORUS_FLOWER_GROW, SoundSource.BLOCKS, 1f, 1f);
        }

        boolean isCompleted = entity.getProgress().getTicks() >= entity.getChargeTime().getTicks();

        if (isCompleted) {
            level.setBlock(blockPos, entity.getBlock().defaultBlockState(), 3);

            for (int i = 0; i < 360; i += 45) {
                double x = Math.sin(i) * 0.5;
                double z = Math.cos(i) * 0.5;
                ((ServerLevel) level).sendParticles(
                        ParticleTypes.SCRAPE,
                        blockPos.getX() + 0.5d + x,
                        blockPos.getY() + 0.5d,
                        blockPos.getZ() + 0.5d + z,
                        3,
                        Math.random() - 0.5,
                        Math.random() - 0.5,
                        Math.random() - 0.5,
                        Math.random() * 0.3
                );
                ((ServerLevel) level).sendParticles(
                        ParticleTypes.CLOUD,
                        blockPos.getX() + 0.5d,
                        blockPos.getY() + 0.5d,
                        blockPos.getZ() + 0.5d,
                        3,
                        new Random().nextBoolean() ? 0.3 : -0.3,
                        new Random().nextBoolean() ? 0.3 : -0.3,
                        new Random().nextBoolean() ? 0.3 : -0.3,
                        0.05
                );
            }
            level.playSound(null, blockPos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1f, 1f);
        }
    }

    public TimeUnit getProgress() {
        return TimeUnit.inTicks(progress);
    }

    public TimeUnit getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(TimeUnit chargeTime) {
        this.chargeTime = chargeTime;
    }

    public DepositBlock getBlock() {
        return block;
    }

    public void setBlock(DepositBlock block) {
        this.block = block;
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.putInt("progress", progress);
        CompoundTag blockStateTag = NbtUtils.writeBlockState(block.defaultBlockState());
        compoundTag.put("blockState", blockStateTag);
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.progress = compoundTag.getInt("progress");
        this.block = (DepositBlock) NbtUtils.readBlockState(compoundTag.getCompound("blockState")).getBlock();

    }
}

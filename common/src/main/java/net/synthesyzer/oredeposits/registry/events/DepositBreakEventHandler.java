package net.synthesyzer.oredeposits.registry.events;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.BlockEvent;
import dev.architectury.utils.value.IntValue;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.synthesyzer.oredeposits.registry.blocks.ODBlocks;
import net.synthesyzer.oredeposits.registry.blocks.custom.block.ChargingDepositBlock;
import net.synthesyzer.oredeposits.registry.blocks.custom.block.DepositBlock;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepositBreakEventHandler {

    public static Map<BlockPos, DepositBlock> brokenBlocks = new HashMap<>();

    public static void register() {
        BlockEvent.BREAK.register(DepositBreakEventHandler::beforeBlockBreak);
    }

    private static EventResult beforeBlockBreak(Level level, BlockPos blockPos, BlockState blockState, ServerPlayer player, @Nullable IntValue intValue) {
        if (level.isClientSide() || player.isCreative()) {
            return EventResult.pass();
        }

        Block block = blockState.getBlock();

        if (block instanceof ChargingDepositBlock) {
            return EventResult.interruptFalse();
        }

        if (block instanceof DepositBlock depositBlock) {
            dropLootTable((ServerLevel) level, player, blockState);
            damageMainHandTool(player);

            brokenBlocks.put(blockPos, depositBlock);

            level.setBlock(blockPos, ODBlocks.CHARGING_DEPOSIT.get().defaultBlockState(), 3);
            return EventResult.interruptFalse();
        }

        return EventResult.pass();
    }

    private static void damageMainHandTool(Player player) {
        player.getMainHandItem().setDamageValue(player.getMainHandItem().getDamageValue() + 1);
    }

    private static void dropLootTable(ServerLevel world, Player player, BlockState state) {
        LootTables lootManager = world.getServer().getLootTables();
        LootTable lootTable = lootManager.get(state.getBlock().getLootTable());
        LootContext.Builder builder = new LootContext.Builder(world)
                .withLuck(player.getLuck())
                .withRandom(player.getRandom())
                .withParameter(LootContextParams.ORIGIN, player.position())
                .withParameter(LootContextParams.TOOL, player.getMainHandItem())
                .withParameter(LootContextParams.THIS_ENTITY, player)
                .withParameter(LootContextParams.BLOCK_STATE, state);

        List<ItemStack> lootTables = lootTable.getRandomItems(builder.create(LootContextParamSets.BLOCK));

        for (ItemStack lootTableEntry : lootTables) {
            world.addFreshEntity(new ItemEntity(world, player.getX(), player.getY(), player.getZ(), lootTableEntry));
        }
    }
}

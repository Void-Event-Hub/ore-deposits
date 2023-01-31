package net.synthesyzer.oredeposits.jade;

import net.synthesyzer.oredeposits.registry.blocks.custom.block.ChargingDepositBlock;
import net.synthesyzer.oredeposits.registry.blocks.custom.entity.ChargingDepositBlockEntity;
import snownee.jade.api.*;

@WailaPlugin()
public class OreDepositsPlugin implements IWailaPlugin {
    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(ChargingDepositComponentProvider.INSTANCE, ChargingDepositBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(ChargingDepositComponentProvider.INSTANCE, ChargingDepositBlock.class);
    }
}

package net.synthesyzer.oredeposits.fabric.jade;

import net.synthesyzer.oredeposits.registry.blocks.custom.block.ChargingDepositBlock;
import net.synthesyzer.oredeposits.registry.blocks.custom.entity.ChargingDepositBlockEntity;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin()
public class OreDepositsPlugin implements IWailaPlugin {

    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(DepositComponentProvider.INSTANCE, ChargingDepositBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(DepositComponentProvider.INSTANCE, ChargingDepositBlock.class);
    }
}

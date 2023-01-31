package net.synthesyzer.oredeposits.forge.jade;

import mcp.mobius.waila.api.*;
import net.synthesyzer.oredeposits.registry.blocks.custom.block.ChargingDepositBlock;
import net.synthesyzer.oredeposits.registry.blocks.custom.entity.ChargingDepositBlockEntity;

@WailaPlugin()
public class OreDepositsPlugin implements IWailaPlugin {

    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(DepositComponentProvider.INSTANCE, ChargingDepositBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerComponentProvider(DepositComponentProvider.INSTANCE, TooltipPosition.BODY, ChargingDepositBlock.class);
    }
}

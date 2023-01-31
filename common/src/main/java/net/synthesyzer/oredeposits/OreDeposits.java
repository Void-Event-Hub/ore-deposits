package net.synthesyzer.oredeposits;

import net.synthesyzer.oredeposits.config.Config;
import net.synthesyzer.oredeposits.registry.blocks.ODBlockEntities;
import net.synthesyzer.oredeposits.registry.blocks.ODBlocks;
import net.synthesyzer.oredeposits.registry.events.DepositBreakEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OreDeposits {

    public static final String MOD_ID = "ore_deposits";

    public static Logger LOGGER = LoggerFactory.getLogger(OreDeposits.MOD_ID);
    
    public static void init() {
        ODBlocks.registerBlocks();
        ODBlockEntities.registerBlockEntities();
        DepositBreakEventHandler.register();
        Config.registerConfigs();
    }
}

package com.github.sedlak477.mrglgaghcore;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = MrglgaghCore.MODID)
@Mod.EventBusSubscriber
public class MrglgaghCore {

    public static final String MODID = "mrglgaghcore";

    @Mod.Instance
    public static MrglgaghCore instance = new MrglgaghCore();

    public static final Logger logger = LogManager.getLogger(MrglgaghCore.MODID);

    @SidedProxy(clientSide = "com.github.sedlak477.mrglgaghcore.ClientProxy", serverSide = "com.github.sedlak477.mrglgaghcore.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){
        proxy.postInit(e);
    }
}

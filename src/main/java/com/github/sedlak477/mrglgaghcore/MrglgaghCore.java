package com.github.sedlak477.mrglgaghcore;

import com.github.sedlak477.mrglgaghcore.proxy.IProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = MrglgaghCore.MODID)
@Mod.EventBusSubscriber
public class MrglgaghCore {

    public static final String MODID = "mrglgaghcore";

    @Mod.Instance
    public static MrglgaghCore instance = new MrglgaghCore();

    public static final Logger logger = LogManager.getLogger(MrglgaghCore.MODID);

    @SidedProxy(clientSide = "com.github.sedlak477.mrglgaghcore.proxy.ClientProxy", serverSide = "com.github.sedlak477.mrglgaghcore.proxy.CommonProxy")
    public static IProxy proxy;

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

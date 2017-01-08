package com.github.sedlak477.mrglgaghcore.feature;

import com.github.sedlak477.mrglgaghcore.MrglgaghCore;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;

public class FeatureLoader {

    private FeatureLoader(){
    }

    /**
     * Register IForgeRegistryEntries in registry
     * @param features Class with IForgeRegistryEntries
     * @param registry Registry to where the entries should get registered
     * @param <T> IForgeRegistryEntry<T>
     */
    public static <T extends IForgeRegistryEntry<T>> void register(Class features, IForgeRegistry<T> registry){
        Field[] fields = features.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Feature.class) && registry.getRegistrySuperType().isAssignableFrom(field.getType())){
                try {
                    registry.register((T) field.get(null));
                    MrglgaghCore.logger.info(field.getName() + " registered!");
                } catch (IllegalAccessException | IllegalArgumentException e) {
                    MrglgaghCore.logger.error("Can't access feature field: " + field.getName() + ". Make sure it is public static!", e);
                }
            }
        }
    }

    /**
     * Register item renderer
     * @param features Class with items
     * @param modid ModID of Mod
     */
    @SideOnly(Side.CLIENT)
    public static void registerItemRenderer(Class features, String modid){
        Field[] fields = features.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Feature.class) && Item.class.isAssignableFrom(field.getType())){
                try {
                    // TODO Add support for metadata
                    Item item = (Item) field.get(null);
                    ModelResourceLocation model = new ModelResourceLocation(modid + ":" + item.getUnlocalizedName().substring(5), "inventory");
                    ModelLoader.registerItemVariants(item, model);
                    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, model);
                    MrglgaghCore.logger.debug(item.getUnlocalizedName() + " item renderer registered!");
                } catch (IllegalAccessException e) {
                    MrglgaghCore.logger.error("Can't access feature field: " + field.getName() + ". Make sure it is public static!", e);
                } catch (NullPointerException e) {
                    MrglgaghCore.logger.error("Oh-Oh, this shouldn't have happened :(", e);
                }
            }
        }
    }

    /**
     * Register block renderer
     * @param features Class with blocks
     * @param modid ModID of Mod
     */
    @SideOnly(Side.CLIENT)
    public static void registerBlockRenderer(Class features, String modid){
        Field[] fields = features.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Feature.class) && Block.class.isAssignableFrom(field.getType())){
                try {
                    // TODO Add support for metadata
                    Block block = (Block) field.get(null);
                    ModelResourceLocation model = new ModelResourceLocation(modid + ":" + block.getUnlocalizedName().substring(5), "inventory");
                    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, model);
                    MrglgaghCore.logger.debug(block.getUnlocalizedName() + " block renderer registered!");
                } catch (IllegalAccessException e) {
                    MrglgaghCore.logger.error("Can't access feature field: " + field.getName() + ". Make sure it is public static!", e);
                } catch (NullPointerException e) {
                    MrglgaghCore.logger.error("Oh-Oh, this shouldn't have happened :(", e);
                }
            }
        }
    }

}

package com.github.sedlak477.mrglgaghcore.feature;


import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

import java.lang.reflect.Field;

public class FeatureLoader {

    public static <T extends IForgeRegistryEntry<T>> void register(Class features, IForgeRegistry<T> registry){
        Field[] fields = features.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Feature.class) && field.getType().isAssignableFrom(registry.getRegistrySuperType())){
                try {
                    registry.register((T) field.get(null));
                } catch (IllegalAccessException | IllegalArgumentException e) {
                    System.out.println("Can't access '"+field.getName()+"'");
                }
            }
        }
    }

}

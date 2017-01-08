package com.github.sedlak477.mrglgaghcore.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemBase extends Item {

    public ItemBase(String unlocalizedName, String modId){
        super();
        setUnlocalizedName(unlocalizedName);
        setRegistryName(new ResourceLocation(modId, unlocalizedName));
    }

}

package com.github.sedlak477.mrglgaghcore.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

public class BaseBlock extends Block {

    public BaseBlock(String unlocalizedName, String modId, Material materialIn) {
        this(unlocalizedName, modId, materialIn, materialIn.getMaterialMapColor());
    }

    public BaseBlock(String unlocalizedName, String modId, Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
        setUnlocalizedName(unlocalizedName);
        setRegistryName(new ResourceLocation(modId, unlocalizedName));
    }
}

package com.github.sedlak477.mrglgaghcore.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import scala.actors.threadpool.Arrays;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BlockCropsBase extends BlockCrops {

    private Item seed;
    private Item crop;
    private EnumPlantType plantType;
    private Collection<Block> canGrowOn;

    public BlockCropsBase(String unlocalizedName, String modId, Item seed, Item crop) {
        this(unlocalizedName, modId, seed, crop, EnumPlantType.Crop);
    }

    public BlockCropsBase(String unlocalizedName, String modId, Item seed, Item crop, EnumPlantType plantType) {
        this(unlocalizedName, modId, seed, crop, Arrays.asList(new Object[]{Blocks.FARMLAND}), plantType);
    }

    public BlockCropsBase(String unlocalizedName, String modId, Item seed, Item crop, Collection<Block> canGrowOn,
                          EnumPlantType plantType) {
        super();
        setUnlocalizedName(unlocalizedName);
        setRegistryName(new ResourceLocation(modId, unlocalizedName));
        this.seed = seed;
        this.crop = crop;
        this.canGrowOn = canGrowOn;
		this.plantType = plantType;
    }

    @Override
    protected boolean canSustainBush(IBlockState state) {
        return canGrowOn.contains(state.getBlock());
    }

    @Override
    public abstract int getMaxAge();

    @Override
    public Item getCrop() {
        return crop;
    }

    @Override
    public Item getSeed() {
        return seed;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return plantType;
    }

    @Override
    protected abstract PropertyInteger getAgeProperty();

    @Override
    protected abstract BlockStateContainer createBlockState();
}

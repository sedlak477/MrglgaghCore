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

public class BlockCropsBase extends BlockCrops {

    public final PropertyInteger AGE;

    private int maxAge;
    private Item seed;
    private Item crop;
    private EnumPlantType plantType;
    private Collection<Block> canGrowOn;

    public BlockCropsBase(String unlocalizedName, String modId, Item seed, Item crop) {
        this(unlocalizedName, modId, seed, crop, 7, Arrays.asList(new Object[]{Blocks.FARMLAND}));
    }

    public BlockCropsBase(String unlocalizedName, String modId, Item seed, Item crop, int maxAge, Collection<Block> canGrowOn) {
        super();
        if(maxAge < 0)
            throw new IllegalArgumentException("maxAge can't be negative");
        AGE = PropertyInteger.create("age", 0, maxAge);
        setUnlocalizedName(unlocalizedName);
        setRegistryName(new ResourceLocation(modId, unlocalizedName));
        this.maxAge = maxAge;
        this.seed = seed;
        this.crop = crop;
        this.canGrowOn = canGrowOn;
    }

    @Override
    protected boolean canSustainBush(IBlockState state) {
        return canGrowOn.contains(state.getBlock());
    }

    @Override
    public int getMaxAge() {
        return maxAge;
    }

    @Override
    public Item getCrop() {
        return crop;
    }

    @Override
    public Item getSeed() {
        return seed;
    }

    @Override
    protected PropertyInteger getAgeProperty() {
        return AGE;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{AGE});
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return super.getPlantType(world, pos);
    }
}

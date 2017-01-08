package com.github.sedlak477.mrglgaghcore.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockContainerBase extends BlockContainer {

    private Class<? extends TileEntity> tileEntityClass;
    private EnumBlockRenderType renderType;
    private boolean opaque;

    public BlockContainerBase(String unlocalizedName, String modId, Class<? extends TileEntity> tileEntityClass, Material materialIn) {
        this(unlocalizedName, modId, tileEntityClass, EnumBlockRenderType.MODEL, materialIn);
    }

    public BlockContainerBase(String unlocalizedName, String modId, Class<? extends TileEntity> tileEntityClass, EnumBlockRenderType renderType, Material materialIn) {
        this(unlocalizedName, modId, tileEntityClass, renderType, true, materialIn, materialIn.getMaterialMapColor());
    }

    public BlockContainerBase(String unlocalizedName, String modId, Class<? extends TileEntity> tileEntityClass, EnumBlockRenderType renderType, boolean opaque, Material materialIn) {
        this(unlocalizedName, modId, tileEntityClass, renderType, opaque, materialIn, materialIn.getMaterialMapColor());
    }

    public BlockContainerBase(String unlocalizedName, String modId, Class<? extends TileEntity> tileEntityClass, EnumBlockRenderType renderType, boolean opaque, Material materialIn, MapColor color) {
        super(materialIn, color);
        setUnlocalizedName(unlocalizedName);
        setRegistryName(new ResourceLocation(modId, unlocalizedName));
        this.tileEntityClass = tileEntityClass;
        this.renderType = renderType;
        this.opaque = opaque;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        try {
            return tileEntityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return renderType;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return opaque;
    }
}

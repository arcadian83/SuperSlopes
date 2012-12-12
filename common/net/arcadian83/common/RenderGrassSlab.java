package net.arcadian83.common;

import net.minecraft.src.BaseMod;
import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemRenderer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderGrassSlab implements ISimpleBlockRenderingHandler
{
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
    	Tessellator tessellator = Tessellator.instance;
    	
    	block.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
    	tessellator.addTranslation(0.0F, -0.5F, 0.0F);
    	
    	// TODO
    	
    	tessellator.addTranslation(0.0F, 0.5F, 0.0F);
    	block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
    	Tessellator tessellator = Tessellator.instance;
    	
    	block.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
    	
    	RenderColoredGrass.renderStandardBlock(block, x, y, z, renderer);
    	
    	block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    	
    	return false;
    }

    public boolean shouldRender3DInInventory()
    {
         return false;
    }

    public int getRenderId()
    {
         return Arc.grassSlabRenderId;
    }
    
    
}
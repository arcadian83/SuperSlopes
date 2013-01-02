package net.arcadian83.common;

import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Facing;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockDirtSlab extends Block{

	public BlockDirtSlab(int id, int texture) {
		super(id, texture, Material.ground);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		this.canBlockGrass[id] = true;
	}
	
	/**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {   
    	this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    }
    
    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender()
    {
    	this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    }
    
    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    // from BlockHalfSlab, not sure if want
    //@SideOnly(Side.CLIENT)
    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    /*public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (par5 != 1 && par5 != 0 && !super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5))
        {
            return false;
        }
        else
        {
            int var6 = par2 + Facing.offsetsXForSide[Facing.faceToSide[par5]];
            int var7 = par3 + Facing.offsetsYForSide[Facing.faceToSide[par5]];
            int var8 = par4 + Facing.offsetsZForSide[Facing.faceToSide[par5]];
            boolean var9 = (par1IBlockAccess.getBlockMetadata(var6, var7, var8) & 8) != 0;
            return var9 ? (par5 == 0 ? true : (par5 == 1 && super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5) ? true : !isBlockSingleSlab(par1IBlockAccess.getBlockId(par2, par3, par4)) || (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 8) == 0)) : (par5 == 1 ? true : (par5 == 0 && super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5) ? true : !isBlockSingleSlab(par1IBlockAccess.getBlockId(par2, par3, par4)) || (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 8) != 0));
        }
    }*/
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.dirt.idDropped(0, par2Random, par3);
    }
    
    /**
     * Called when a block is placed using an item. Used often for taking the facing and figuring out how to position
     * the item. Args: x, y, z, facing
     */
    // turn into a vanilla BlockDirt when placed on a GrassSlab or DirtSlab
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    {
    	int blockBelowID = par1World.getBlockId(par2, par3 - 1, par4);
    	if (blockBelowID == Arc.dirtSlabId ||
    		blockBelowID == Arc.grassSlabId) {
    		par1World.setBlockWithNotify(par2, par3 - 1, par4, Block.dirt.blockID);
    		par1World.setBlockWithNotify(par2, par3, par4, 0); // put air where the second slab would have otherwise been placed
    	} else if (blockBelowID == Block.grass.blockID) {
    		par1World.setBlockWithNotify(par2, par3 - 1, par4, Block.dirt.blockID);
    	}
    }
    
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
    	// transfer grass growth underneath the dirt slab to itself, making it a grass slab  	
    	if(blockId == Block.grass.blockID && world.getBlockId(x, y - 1, z) == Block.grass.blockID) {
    		world.setBlockWithNotify(x, y - 1, z, Block.dirt.blockID);
    		world.setBlockWithNotify(x, y, z, Arc.grassSlabId);
    	}
    }
    
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
    	if(side == 1) {
    		return true;
    	}
    	
    	return super.shouldSideBeRendered(par1IBlockAccess, x, y, z, side);
    }
}
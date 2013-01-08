package net.arcadian83;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGrassSlab extends Block{

	public BlockGrassSlab(int id, int texture) {
		super(id, texture, Material.grass);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		//this.canBlockGrass[id] = true; // TODO make a new Material to block grass instead
		this.blockIndexInTexture = 3;
		this.setTickRandomly(true);
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
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.dirt.idDropped(0, par2Random, par3);
    }
    
    @Override
    public int getRenderType()
    {
        // You know that render ID we talked about earlier? You need to access it here.
        return Arc.grassSlabRenderId;
    }
    
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        return par1 == 1 ? 0 : (par1 == 0 ? 2 : 3);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public int getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (par5 == 1)
        {
            return 0;
        }
        else if (par5 == 0)
        {
            return 2;
        }
        else
        {
            Material var6 = par1IBlockAccess.getBlockMaterial(par2, par3 + 1, par4);
            return var6 != Material.snow && var6 != Material.craftedSnow ? 3 : 68;
        }
    }

    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        double var1 = 0.5D;
        double var3 = 1.0D;
        return ColorizerGrass.getGrassColor(var1, var3);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int par1)
    {
        return this.getBlockColor();
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int x, int y, int z)
    {
        int var5 = 0;
        int var6 = 0;
        int var7 = 0;

        for (int var8 = -1; var8 <= 1; ++var8)
        {
            for (int var9 = -1; var9 <= 1; ++var9)
            {
            	/*System.out.println("x:" + x + "  XXXXXXXXXXXXXXX");
            	int var10 = 1; // default to biome "plains" for rendering item in inventory
            	try {*/
            		int var10 = par1IBlockAccess.getBiomeGenForCoords(x + var9, z + var8).getBiomeGrassColor();
            	/*} catch(NullPointerException npe) {
            		// TODO: never swallow an exception!
            	}*/
                var5 += (var10 & 16711680) >> 16;
                var6 += (var10 & 65280) >> 8;
                var7 += var10 & 255;
            }
        }

        return (var5 / 9 & 255) << 16 | (var6 / 9 & 255) << 8 | var7 / 9 & 255;
    }
    
    public String getTextureFile()
    {
            return "/Arc/terrain.png";
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
    		par1World.setBlockWithNotify(par2, par3, par4, 0); // put air where the second slab would have otherwise been placed
    		par1World.setBlockWithNotify(par2, par3 - 1, par4, Block.grass.blockID);
    	} else if (blockBelowID == Block.grass.blockID) {
    		par1World.setBlockWithNotify(par2, par3 - 1, par4, Block.dirt.blockID);
    	}
    }
    
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
    	// undo any grass growth underneath the slab    	
    	if(blockId == Block.grass.blockID && world.getBlockId(x, y - 1, z) == Block.grass.blockID) {
    		world.setBlockWithNotify(x, y - 1, z, Block.dirt.blockID);
    	}
    }
    
    // from BlockGrass, to make grass spread to dirt slabs
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            if (par1World.getBlockLightValue(par2, par3 + 1, par4) < 4 && par1World.getBlockLightOpacity(par2, par3 + 1, par4) > 2)
            {
                par1World.setBlockWithNotify(par2, par3, par4, Block.dirt.blockID);
            }
            else if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
            {
                for (int var6 = 0; var6 < 4; ++var6)
                {
                    int var7 = par2 + par5Random.nextInt(3) - 1;
                    int var8 = par3 + par5Random.nextInt(5) - 3;
                    int var9 = par4 + par5Random.nextInt(3) - 1;
                    int var10 = par1World.getBlockId(var7, var8 + 1, var9);

                    if (par1World.getBlockId(var7, var8, var9) == Block.dirt.blockID ||
                    		par1World.getBlockId(var7, var8, var9) == Arc.dirtSlabId && // dirt slabs can grow grass too
                    		par1World.getBlockLightValue(var7, var8 + 1, var9) >= 4 && par1World.getBlockLightOpacity(var7, var8 + 1, var9) <= 2)
                    {
                    	// set to grass slab/block based on dirt slab/block
                    	if(par1World.getBlockId(var7, var8, var9) == Block.dirt.blockID) {
                    		par1World.setBlockWithNotify(var7, var8, var9, Block.grass.blockID);
                    	} else if(par1World.getBlockId(var7, var8, var9) == Arc.dirtSlabId) {
                    		par1World.setBlockWithNotify(var7, var8, var9, Arc.grassSlabId);
                    	}
                    }
                }
            }
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
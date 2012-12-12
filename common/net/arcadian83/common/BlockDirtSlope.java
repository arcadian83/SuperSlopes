package net.arcadian83.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockDirtSlope extends Block{
	
	// named after the direction the slope faces outward toward
	public static HashMap<Integer, String> metaNames = new HashMap<Integer, String>() {
		{
			put(0,  "Dirt Straight Slope TopNorth");
			put(1,  "Dirt Straight Slope TopEast");
			put(2,  "Dirt Straight Slope TopSouth");
			put(3,  "Dirt Straight Slope TopWest");
			put(4,  "Dirt Straight Slope BottomNorth");
			put(5,  "Dirt Straight Slope BottomEast");
			put(6,  "Dirt Straight Slope BottomSouth");
			put(7,  "Dirt Straight Slope BottomWest");
			put(8,  "Dirt Straight Slope NorthWest");
			put(9,  "Dirt Straight Slope NorthEast");
			put(10, "Dirt Straight Slope SouthEast");
			put(11, "Dirt Straight Slope SouthWest");
			put(12, "Dirt Corner Slope TopNorthWest");
			put(13, "Dirt Corner Slope TopNorthEast");
			put(14, "Dirt Corner Slope TopSouthEast");
			put(15, "Dirt Corner Slope TopSouthWest");
			put(16, "Dirt Corner Slope BottomNorthWest");
			put(17, "Dirt Corner Slope BottomNorthEast");
			put(18, "Dirt Corner Slope BottomSouthEast");
			put(19, "Dirt Corner Slope BottomSouthWest");
			put(20, "Dirt Interior Corner Slope TopNorthWest");
			put(21, "Dirt Interior Corner Slope TopNorthEast");
			put(22, "Dirt Interior Corner Slope TopSouthEast");
			put(23, "Dirt Interior Corner Slope TopSouthWest");
			put(24, "Dirt Interior Corner Slope BottomNorthWest");
			put(25, "Dirt Interior Corner Slope BottomNorthEast");
			put(26, "Dirt Interior Corner Slope BottomSouthEast");
			put(27, "Dirt Interior Corner Slope BottomSouthWest");
			put(28, "Dirt Slant Slope TopNorthWest");
			put(29, "Dirt Slant Slope TopNorthEast");
			put(30, "Dirt Slant Slope TopSouthEast");
			put(31, "Dirt Slant Slope TopSouthWest");
			put(32, "Dirt Slant Slope BottomNorthWest");
			put(33, "Dirt Slant Slope BottomNorthEast");
			put(34, "Dirt Slant Slope BottomSouthEast");
			put(35, "Dirt Slant Slope BottomSouthWest");
			put(36, "Dirt Oblique Slope TopNorthWest");
			put(37, "Dirt Oblique Slope TopNorthEast");
			put(38, "Dirt Oblique Slope TopSouthEast");
			put(39, "Dirt Oblique Slope TopSouthWest");
			put(40, "Dirt Oblique Slope BottomNorthWest");
			put(41, "Dirt Oblique Slope BottomNorthEast");
			put(42, "Dirt Oblique Slope BottomSouthEast");
			put(43, "Dirt Oblique Slope BottomSouthWest");
		};
	};
	
	public BlockDirtSlope(int id, int texture) {
		super(id, texture, Material.ground);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.blockIndexInTexture = 2;
		setLightOpacity(0);
	}
	
	public int getRenderType()
    {
        return Arc.dirtSlopeRenderId;
    } 
	
	public boolean isOpaqueCube()
    {
        return false;
    }	
	
    public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {        
        return true;        
    }
	
	// from BlockSlopes_Kaevator
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving placer)
	{		
		int oldmeta = world.getBlockMetadata(x, y, z);
		int l;
		
		if(oldmeta == 12){
			l = (int)((double)((placer.rotationYaw * 4F) / 360F)) & 3;
			
		}
		else{
		l = (int)((double)((placer.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		}
		
		if(l == 0)
		{
			world.setBlockMetadataWithNotify(x, y, z, 2 + oldmeta );
		}
		if(l == 1)
		{
			world.setBlockMetadataWithNotify(x, y, z, 1 + oldmeta );
		}
		if(l == 2)
		{
			world.setBlockMetadataWithNotify(x, y, z, 3 + oldmeta );
		}
		if(l == 3)
		{
			world.setBlockMetadataWithNotify(x, y, z, 0 + oldmeta );
		}		
	}
	
	// from BlockSlopes_Kaevator, seems unused
	/*protected int func_21025_b(int i)
    {
		if(i == 0 || i == 1 || i == 2 || i == 3)        {            return 0;        }
		if(i == 4 || i == 5 || i == 6 || i == 7)        {            return 4;        }
		if(i == 8 || i == 9 || i == 10 || i == 11)      {            return 8;        }
		if(i == 12 || i == 13 || i == 14 || i == 15) {            return 12;        }
		else { return i; }
    }	*/
	
	// from BlockSlopes_Kaevator
	public int damageDropped(int i)
	{
		if(i == 0 || i == 1 || i == 2 || i == 3)        {            return 0;        }
		if(i == 4 || i == 5 || i == 6 || i == 7)        {            return 4;        }
		if(i == 8 || i == 9 || i == 10 || i == 11)      {            return 8;        }
		if(i == 12 || i == 13 || i == 14 || i == 15) {            return 12;        }
		else { return i; }
	}
	
	// was BlockSlopes_Kaevator.getCollidingBoundingBoxes 
	public void addCollidingBlockToList(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity entity)
    {
        int l = world.getBlockMetadata(i, j, k);
        if(l == 0)
        {
            setBlockBounds(0.0F, 0.5F, 0.0F, 0.5F, 1.0F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
            setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
        } else
        if(l == 1)
        {
            setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
            setBlockBounds(0.5F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
        } else
        if(l == 2)
        {
            setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 0.5F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
            setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
        } else
        if(l == 3)
        {
            setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
            setBlockBounds(0.0F, 0.5F, 0.5F, 1.0F, 1.0F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
        }else
        if(l == 4)
        {
            setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
            setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
        } else
        if(l == 5)
        {
            setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
            setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
        } else
        if(l == 6)
        {
            setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 0.5F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
            setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
        } else
        if(l == 7)
        {
            setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
            setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
        }else
		if(l == 8)
        {
            setBlockBounds(0.0F, 0.5F, 0.0F, 0.5F, 1.0F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
            setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
        } else
        if(l == 9)
        {
            setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
            setBlockBounds(0.5F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
        } else
        if(l == 10)
        {
            setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 0.5F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
            setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
        } else
        if(l == 11)
        {
            setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
            setBlockBounds(0.0F, 0.5F, 0.5F, 1.0F, 1.0F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);
        }else
		if(l == 12)
        {
			setBlockBounds(0.5F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);                    
        } else
        if(l == 13)
        {
        	setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 0.5F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);            
        } else
        if(l == 14)
        {
        	setBlockBounds(0.0F, 0.0F, 0.5F, 0.5F, 1.0F, 1.0F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);            
        } else
        if(l == 15)
        {	
        	setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
            super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, entity);              
        }
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
	
	public void getSubBlocks(int i, CreativeTabs tab, List list)
	{
		Iterator it = metaNames.entrySet().iterator();
		
		while (it.hasNext()) {
	        Entry entry = (Entry)it.next();
	        list.add(new ItemStack(i, 1, (Integer)entry.getKey()));
	        //i.remove(); // avoids a ConcurrentModificationException
	    }
	}

	
	// from BlockSlopes_Kaevator.blockActivated()
	// don't yet understand this code
	/*
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
    {
		int lCurrentID = world.getBlockId(i, j, k);
		if(lCurrentID == mod_Kaevator_Slopes.BlockKaevBlackWoolSlopes.blockID ||
				lCurrentID == mod_Kaevator_Slopes.BlockKaevRedWoolSlopes.blockID ||
				lCurrentID == mod_Kaevator_Slopes.BlockKaevGreenWoolSlopes.blockID ||
				lCurrentID == mod_Kaevator_Slopes.BlockKaevBrownWoolSlopes.blockID ||
				lCurrentID == mod_Kaevator_Slopes.BlockKaevBlueWoolSlopes.blockID ||
				lCurrentID == mod_Kaevator_Slopes.BlockKaevPurpleWoolSlopes.blockID ||
				lCurrentID == mod_Kaevator_Slopes.BlockKaevCyanWoolSlopes.blockID ||
				lCurrentID == mod_Kaevator_Slopes.BlockKaevSilverWoolSlopes.blockID ||
				lCurrentID == mod_Kaevator_Slopes.BlockKaevGrayWoolSlopes.blockID ||
				lCurrentID == mod_Kaevator_Slopes.BlockKaevPinkWoolSlopes.blockID ||
				lCurrentID == mod_Kaevator_Slopes.BlockKaevLimeWoolSlopes.blockID ||
				lCurrentID == mod_Kaevator_Slopes.BlockKaevYellowWoolSlopes.blockID ||
				lCurrentID == mod_Kaevator_Slopes.BlockKaevLightBlueWoolSlopes.blockID ||
				lCurrentID == mod_Kaevator_Slopes.BlockKaevMagentaWoolSlopes.blockID ||
				lCurrentID == mod_Kaevator_Slopes.BlockKaevOrangeWoolSlopes.blockID ||
				lCurrentID == mod_Kaevator_Slopes.BlockKaevWhiteWoolSlopes.blockID){					
		
			ItemStack itemstack = entityplayer.inventory.getCurrentItem();
	    	if(itemstack != null && itemstack.itemID == Item.dyePowder.shiftedIndex){
	    		int lOldMeta = world.getBlockMetadata(i, j, k);	
	    		
	    		if(itemstack.getItemDamage() == 0 && mod_Kaevator_Slopes.BlockKaevBlackWoolSlopes.blockID != Block.stone.blockID){	    		
	    			world.setBlockAndMetadataWithNotify(i, j, k, mod_Kaevator_Slopes.BlockKaevBlackWoolSlopes.blockID,lOldMeta);
	    			return true;
	    		}else
    			if(itemstack.getItemDamage() == 1 && mod_Kaevator_Slopes.BlockKaevRedWoolSlopes.blockID != Block.stone.blockID){	    		
	    			world.setBlockAndMetadataWithNotify(i, j, k, mod_Kaevator_Slopes.BlockKaevRedWoolSlopes.blockID,lOldMeta);
	    			return true;
    			}else
	    		if(itemstack.getItemDamage() == 2 && mod_Kaevator_Slopes.BlockKaevGreenWoolSlopes.blockID != Block.stone.blockID){	    		
	    			world.setBlockAndMetadataWithNotify(i, j, k, mod_Kaevator_Slopes.BlockKaevGreenWoolSlopes.blockID,lOldMeta);
	    			return true;
	    		}else
	    		if(itemstack.getItemDamage() == 3 && mod_Kaevator_Slopes.BlockKaevBrownWoolSlopes.blockID != Block.stone.blockID){	    		
	    			world.setBlockAndMetadataWithNotify(i, j, k, mod_Kaevator_Slopes.BlockKaevBrownWoolSlopes.blockID,lOldMeta);
	    			return true;
	    		}else
	    		if(itemstack.getItemDamage() == 4 && mod_Kaevator_Slopes.BlockKaevBlueWoolSlopes.blockID != Block.stone.blockID){	    		
	    			world.setBlockAndMetadataWithNotify(i, j, k, mod_Kaevator_Slopes.BlockKaevBlueWoolSlopes.blockID,lOldMeta);
	    			return true;
	    		}else
	    		if(itemstack.getItemDamage() == 5 && mod_Kaevator_Slopes.BlockKaevPurpleWoolSlopes.blockID != Block.stone.blockID){	    		
	    			world.setBlockAndMetadataWithNotify(i, j, k, mod_Kaevator_Slopes.BlockKaevPurpleWoolSlopes.blockID,lOldMeta);
	    			return true;
	    		}else
	    		if(itemstack.getItemDamage() == 6 && mod_Kaevator_Slopes.BlockKaevCyanWoolSlopes.blockID != Block.stone.blockID){	    		
	    			world.setBlockAndMetadataWithNotify(i, j, k, mod_Kaevator_Slopes.BlockKaevCyanWoolSlopes.blockID,lOldMeta);
	    			return true;
	    		}else
	    		if(itemstack.getItemDamage() == 7 && mod_Kaevator_Slopes.BlockKaevSilverWoolSlopes.blockID != Block.stone.blockID){	    		
	    			world.setBlockAndMetadataWithNotify(i, j, k, mod_Kaevator_Slopes.BlockKaevSilverWoolSlopes.blockID,lOldMeta);
	    			return true;
	    		}else
	    		if(itemstack.getItemDamage() == 8 && mod_Kaevator_Slopes.BlockKaevGrayWoolSlopes.blockID != Block.stone.blockID){	    		
	    			world.setBlockAndMetadataWithNotify(i, j, k, mod_Kaevator_Slopes.BlockKaevGrayWoolSlopes.blockID,lOldMeta);
	    			return true;
	    		}else
	    		if(itemstack.getItemDamage() == 9 && mod_Kaevator_Slopes.BlockKaevPinkWoolSlopes.blockID != Block.stone.blockID){	    		
	    			world.setBlockAndMetadataWithNotify(i, j, k, mod_Kaevator_Slopes.BlockKaevPinkWoolSlopes.blockID,lOldMeta);
	    			return true;
	    		}else
	    		if(itemstack.getItemDamage() == 10 && mod_Kaevator_Slopes.BlockKaevLimeWoolSlopes.blockID != Block.stone.blockID){	    		
	    			world.setBlockAndMetadataWithNotify(i, j, k, mod_Kaevator_Slopes.BlockKaevLimeWoolSlopes.blockID,lOldMeta);
	    			return true;
	    		}else
	    		if(itemstack.getItemDamage() == 11 && mod_Kaevator_Slopes.BlockKaevYellowWoolSlopes.blockID != Block.stone.blockID){	    		
	    			world.setBlockAndMetadataWithNotify(i, j, k, mod_Kaevator_Slopes.BlockKaevYellowWoolSlopes.blockID,lOldMeta);
	    			return true;
	    		}else
	    		if(itemstack.getItemDamage() == 12 && mod_Kaevator_Slopes.BlockKaevLightBlueWoolSlopes.blockID != Block.stone.blockID){	    		
	    			world.setBlockAndMetadataWithNotify(i, j, k, mod_Kaevator_Slopes.BlockKaevLightBlueWoolSlopes.blockID,lOldMeta);
	    			return true;
	    		}else
	    		if(itemstack.getItemDamage() == 13 && mod_Kaevator_Slopes.BlockKaevMagentaWoolSlopes.blockID != Block.stone.blockID){	    		
	    			world.setBlockAndMetadataWithNotify(i, j, k, mod_Kaevator_Slopes.BlockKaevMagentaWoolSlopes.blockID,lOldMeta);
	    			return true;
	    		}else
	    		if(itemstack.getItemDamage() == 14 && mod_Kaevator_Slopes.BlockKaevOrangeWoolSlopes.blockID != Block.stone.blockID){	    		
	    			world.setBlockAndMetadataWithNotify(i, j, k, mod_Kaevator_Slopes.BlockKaevOrangeWoolSlopes.blockID,lOldMeta);
	    			return true;
	    		}else
	    		if(itemstack.getItemDamage() == 15 && mod_Kaevator_Slopes.BlockKaevWhiteWoolSlopes.blockID != Block.stone.blockID){	    		
	    			world.setBlockAndMetadataWithNotify(i, j, k, mod_Kaevator_Slopes.BlockKaevWhiteWoolSlopes.blockID,lOldMeta);
	    			return true;
	    		}	    		
	    	}
		}
		return false;		
    }*/
	
	
	// from BlockSlopes_Kaevator, seem unused
	/*
	public static int func_21034_c(int i)
    {       
		return i;
    }

    public static int func_21035_d(int i)
    {        
		return i;
    }*/
}
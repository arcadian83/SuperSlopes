package net.arcadian83;

import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDirtSlope extends Block {

	private int metaOffset = 0;
	
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

	public int getRenderType() {
		return Arc.dirtSlopeRenderId;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i,
			int j, int k, int l) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		
		/*for(int i = getMetaOffset(); i < getMetaOffset() + 16; i++) {
			if(metaNames.containsKey(i)) {
				list.add(new ItemStack(id, 1, i));
			}
		}*/
		for(int i = 0; i < 16; i++) {
			
				list.add(new ItemStack(id, 1, i));
		}
		
	}

	public int getMetaOffset() {
		return metaOffset;
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	/*public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		try {
			return new ExtraMetadata();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		int meta = ((ExtraMetadata)world.getBlockTileEntity(x, y, z)).meta;
		
		
		System.out.println("onBlockActivated GETMETA " + meta + " AT (" + x + "," + y + "," + z + ")");
		return true;
	}*/
	
	/*public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving living) {
		
	}*/
	
	// TODO: place block with direction based on from BlockSlopes_Kaevator.blockActivated()

}
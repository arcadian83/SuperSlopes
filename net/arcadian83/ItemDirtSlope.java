package net.arcadian83;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemDirtSlope  extends ItemBlock {
	
	private int metaOffset = 0;
	
	public ItemDirtSlope(int par1)
	{
		super(par1);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	public String getItemNameIS(ItemStack i) {
		int meta = i.getItemDamage();
		return BlockDirtSlope.metaNames.get(meta + getMetaOffset());
	}

	public int getMetadata(int par1)
	{
		return par1;
	}
	
	public int getMetaOffset() {
		return metaOffset;
	}
	
	/*public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		boolean result = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
		world.setBlockMetadataWithNotify(x, y, z, metadata);
		return result;
	}*/
	
	/*public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		boolean result = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);

		ExtraMetadata tileEntity = (ExtraMetadata) world.getBlockTileEntity(x, y, z);
		tileEntity.setMeta(metadata);
		
		ExtraMetadata tileEntity2 = (ExtraMetadata) world.getBlockTileEntity(x, y, z);
		tileEntity2.setMeta(metadata);
		
		ExtraMetadata tileEntity3 = (ExtraMetadata) world.getBlockTileEntity(x, y, z);
		tileEntity3.setMeta(metadata);
		
		//world.setBlockTileEntity(x, y, z, this.getBlockEntity(world.getBlockMetadata(x, y, z)));

		
		System.out.println("placeBlockAt SETMETA " + metadata + " AT (" + x + "," + y + "," + z + ")");

		//world.setBlockMetadataWithNotify(x, y, z, metadata);
		//int test = world.getBlockMetadata(x, y, z);
		//return result;
		return true;
	}*/

}

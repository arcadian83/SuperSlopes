package net.arcadian83.common;

import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class ItemDirtSlope  extends ItemBlock {
	public ItemDirtSlope(int par1)
	{
		super(par1);
		setMaxDamage(0);
		setHasSubtypes(true);

	}

	public String getItemNameIS(ItemStack i) {
		int meta = i.getItemDamage();
		return BlockDirtSlope.metaNames.get(meta);
	}

	public int getMetadata(int par1)
	{
		return par1;
	}

}

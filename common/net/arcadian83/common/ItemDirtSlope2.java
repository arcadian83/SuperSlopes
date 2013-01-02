package net.arcadian83.common;

import net.minecraft.src.ItemStack;

public class ItemDirtSlope2 extends ItemDirtSlope {
	
	private int metaOffset = 16;
	
	public ItemDirtSlope2(int par1)
	{
		super(par1);
	}

	public int getMetaOffset() {
		return metaOffset;
	}
	
}

package net.arcadian83.common;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

public class BlockGrassSlope extends Block{

	public BlockGrassSlope(int id, int texture) {
		super(id, texture, Material.grass);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.blockIndexInTexture = 3;
	}
}
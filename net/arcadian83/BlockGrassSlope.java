package net.arcadian83;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockGrassSlope extends Block{

	public BlockGrassSlope(int id, int texture) {
		super(id, texture, Material.grass);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.blockIndexInTexture = 3;
	}
}
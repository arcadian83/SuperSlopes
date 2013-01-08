package net.arcadian83;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockTestBlock extends Block{

	public BlockTestBlock(int id, int texture) {
		super(id, texture, Material.cloth);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public String getTextureFile()
    {
            return "/Arc/terrain.png";
    }
}

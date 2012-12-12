package net.arcadian83.common;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

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

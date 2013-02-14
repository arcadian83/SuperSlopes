package net.arcadian83;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "Arc_TestMod", name = "Arc TestMod", version = "0.01")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Arc {
	
	public static Block testBlock;
	public static int testBlockId = 4000;
	public static Block dirtSlab;
	public static int dirtSlabId = 4001;
	public static Block grassSlab;
	public static int grassSlabId = 4002;
	public static int grassSlabRenderId = 4002;
	public static Block grassSlope;
	public static int grassSlopeId = 4003;
	public static Block dirtSlope;
	public static Block dirtSlope2;
	public static Block dirtSlope3;
	public static int dirtSlopeId = 4004;
	public static int dirtSlopeId2 = 4005;
	public static int dirtSlopeId3 = 4006;
	public static int dirtSlopeRenderId = 4004;
	public static int dirtSlopeRenderId2 = 4005;
	public static int dirtSlopeRenderId3 = 4006;
	public static int slopeItemId = 4004;

	@SidedProxy(clientSide = "net.arcadian83.ClientProxyArc",
			    serverSide = "net.arcadian83.CommonProxyArc")
    public static CommonProxyArc proxy;
	
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderInformation();
		
		GameRegistry.registerTileEntity(ExtraMetadata.class, "extraMetadata");
		
		testBlock = new BlockTestBlock(testBlockId, 0).setBlockName("Test Block");
		GameRegistry.registerBlock(testBlock, "arcadian83_testBlock");
		LanguageRegistry.addName(testBlock, "Test Block");
		
		dirtSlab = new BlockDirtSlab(dirtSlabId, 2).setBlockName("Dirt Slab").setHardness(0.5F);
		GameRegistry.registerBlock(dirtSlab, "arcadian83_dirtSlab");
		LanguageRegistry.addName(dirtSlab, "Dirt Slab");
		dirtSlab.setStepSound(Block.soundGravelFootstep);
		
		grassSlab = new BlockGrassSlab(grassSlabId, 1).setBlockName("Grass Slab").setHardness(0.6F);
		GameRegistry.registerBlock(grassSlab, "arcadian83_grassSlab");
		LanguageRegistry.addName(grassSlab, "Grass Slab");
		grassSlab.setStepSound(Block.soundGrassFootstep);
		
		dirtSlope = new BlockDirtSlope(dirtSlopeId, 1).setBlockName("Dirt Slope").setHardness(0.5F).setStepSound(Block.soundGravelFootstep);
		dirtSlope2 = new BlockDirtSlopeMeta2(dirtSlopeId2, 1).setBlockName("Dirt Slope 2").setHardness(0.5F).setStepSound(Block.soundGravelFootstep);
		dirtSlope3 = new BlockDirtSlopeMeta3(dirtSlopeId3, 1).setBlockName("Dirt Slope 3").setHardness(0.5F).setStepSound(Block.soundGravelFootstep);
		GameRegistry.registerBlock(dirtSlope, ItemDirtSlope.class, "arcadian83_dirtSlope");
		GameRegistry.registerBlock(dirtSlope2, ItemDirtSlope2.class, "arcadian83_dirtSlope2");
		GameRegistry.registerBlock(dirtSlope3, ItemDirtSlope3.class, "arcadian83_dirtSlope3");
		LanguageRegistry.addName(dirtSlope, "Dirt Slope");
		LanguageRegistry.addName(dirtSlope2, "Dirt Slope 2");
		LanguageRegistry.addName(dirtSlope3, "Dirt Slope 3");

		
		
		for(int i = 0; i < 16; i++) {
			if(BlockDirtSlope.metaNames.containsKey(i)) {
				ItemStack is = new ItemStack(dirtSlope,1,i);
				LanguageRegistry.addName(is,BlockDirtSlope.metaNames.get(i));
				dirtSlope.setCreativeTab(CreativeTabs.tabBlock);
			}
		}
		for(int i = 0; i < 16; i++) {
			if(BlockDirtSlope.metaNames.containsKey(i + 16)) {
				ItemStack is = new ItemStack(dirtSlope2,1,i);
				LanguageRegistry.addName(is,BlockDirtSlope.metaNames.get(i + 16));
				dirtSlope2.setCreativeTab(CreativeTabs.tabBlock);
			}
		}
		for(int i = 0; i < 16; i++) {
			if(BlockDirtSlope.metaNames.containsKey(i + 32)) {
				ItemStack is = new ItemStack(dirtSlope3,1,i);
				LanguageRegistry.addName(is,BlockDirtSlope.metaNames.get(i + 32));
				dirtSlope3.setCreativeTab(CreativeTabs.tabBlock);
			}
		}

		
//		dirtSlopeMeta = new BlockDirtSlope(dirtSlopeId, 1).setBlockName("Dirt Slope meta").setHardness(0.5F);
//		GameRegistry.registerBlock(dirtSlopeMeta);
//		LanguageRegistry.addName(dirtSlopeMeta, "Dirt Slope Meta");
//		dirtSlopeMeta.setStepSound(Block.soundGravelFootstep);

		grassSlope = new BlockGrassSlope(grassSlopeId, 1).setBlockName("Grass Slope").setHardness(0.6F);
		GameRegistry.registerBlock(grassSlope);
		LanguageRegistry.addName(grassSlope, "Grass Slope");
		grassSlope.setStepSound(Block.soundGrassFootstep);
		
		ItemStack dirtStack = new ItemStack(Block.dirt);
		ItemStack dirtSlabStack = new ItemStack(dirtSlab);

		GameRegistry.addRecipe(new ItemStack(dirtSlab, 4), "xx", 'x', dirtStack);
		//GameRegistry.addRecipe(new ItemStack(dirtSlope, 4, 1), "xx", "xx", 'x', dirtStack);
		GameRegistry.addRecipe(new ItemStack(Block.dirt, 1), "x", "x", 'x', dirtSlabStack);
	}
}

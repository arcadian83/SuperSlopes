package net.arcadian83.common;

import java.util.Iterator;
import java.util.Map.Entry;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
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
	public static Block dirtSlopeMeta;
	public static int dirtSlopeId = 4004;
	public static int dirtSlopeRenderId = 4004;
	

	@SidedProxy(clientSide = "net.arcadian83.client.ClientProxyArc",
			    serverSide = "net.arcadian83.common.CommonProxyArc")
    public static CommonProxyArc proxy;
	
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderInformation(); //You have to call the methods in your proxy class
		
		testBlock = new BlockTestBlock(testBlockId, 0).setBlockName("Test Block");
		GameRegistry.registerBlock(testBlock);
		LanguageRegistry.addName(testBlock, "Test Block");
		
		dirtSlab = new BlockDirtSlab(dirtSlabId, 2).setBlockName("Dirt Slab").setHardness(0.5F);
		GameRegistry.registerBlock(dirtSlab);
		LanguageRegistry.addName(dirtSlab, "Dirt Slab");
		dirtSlab.setStepSound(Block.soundGravelFootstep);
		
		grassSlab = new BlockGrassSlab(grassSlabId, 1).setBlockName("Grass Slab").setHardness(0.6F);
		GameRegistry.registerBlock(grassSlab);
		LanguageRegistry.addName(grassSlab, "Grass Slab");
		grassSlab.setStepSound(Block.soundGrassFootstep);
		
		dirtSlope = new BlockDirtSlope(dirtSlopeId, 1).setBlockName("Dirt Slope").setHardness(0.5F);
		GameRegistry.registerBlock(dirtSlope, ItemDirtSlope.class);
		LanguageRegistry.addName(dirtSlope, "Dirt Slope");
		dirtSlope.setStepSound(Block.soundGravelFootstep);
		GameRegistry.registerTileEntity(ExtraMetadata.class, "extraMetadata");
		
		
		Iterator it = BlockDirtSlope.metaNames.entrySet().iterator();	
		while (it.hasNext()) {
	        Entry entry = (Entry)it.next();
			ItemStack is = new ItemStack(dirtSlope,1,(Integer)entry.getKey());
			LanguageRegistry.addName(is,(String)entry.getValue() + " metaName");
			dirtSlope.setCreativeTab(CreativeTabs.tabBlock);
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

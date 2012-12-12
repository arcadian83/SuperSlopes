package net.arcadian83.common;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderDirtSlope implements ISimpleBlockRenderingHandler
{
    public int getRenderId()
    {
         return Arc.dirtSlopeRenderId;
    }
	
	public boolean shouldRender3DInInventory()
    {
         return false;
    }
	
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
    	// TODO
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
    	return renderWorldBlockAO(world ,x , y, z, block, modelId, renderer);
    }
    
    
    public boolean renderWorldBlockAO(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
    	renderer.enableAO = true;
		
    	int meta = world.getBlockMetadata(x, y, z);
    	Tuple coordinates = new Tuple(x,y,z);
    	HashMap<Tuple, Float> aoLightValue = new HashMap<Tuple, Float>();
    	HashMap<Tuple, Integer> aoBrightness = new HashMap<Tuple, Integer>();
    	
    	for(int zRel = -1; zRel <= 1; zRel++) {
    		for(int yRel = -1; yRel <= 1; yRel++) {
    			for(int xRel = -1; xRel <= 1; xRel++) {
    				aoLightValue.put(new Tuple(xRel, yRel, zRel), block.getAmbientOcclusionLightValue(world, x + xRel, y + yRel, z + zRel));
    				aoBrightness.put(new Tuple(xRel, yRel, zRel), block.getMixedBrightnessForBlock(renderer.blockAccess,x + xRel, y + yRel, z + zRel));
    			}
        	}
    	}
    	
    	// TODO simplify addition of color light vertex info
    	renderer.colorRedTopLeft = 0.5F;
    	renderer.colorRedTopLeft *= (aoLightValue.get(new Tuple(-1,-1, 1)) + 
    			aoLightValue.get(new Tuple(-1,-1, 0)) +
    			aoLightValue.get(new Tuple(0,-1, 1)) +
    			aoLightValue.get(new Tuple(0,-1, 0))) / 4.0F;

    	// TODO calculate these from texture number
        double texUmin = 0.125;
        double texUmax = 0.1875;
        double texVmin = 0;
        double texVmax = 0.0625;
        
        Tessellator topFace = Tessellator.instance;
        topFace.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        VertexLight.addVertex(topFace, 1, coordinates, new Tuple(0,1,0), texUmin, texVmin, aoBrightness);
        VertexLight.addVertex(topFace, 2, coordinates, new Tuple(0,0,1), texUmin, texVmax, aoBrightness);
        VertexLight.addVertex(topFace, 5, coordinates, new Tuple(1,0,0), texUmax, texVmin, aoBrightness);
        VertexLight.addVertex(topFace, 5, coordinates, new Tuple(1,0,0), texUmax, texVmin, aoBrightness);
        
        Tessellator northFace = Tessellator.instance;
        northFace.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        VertexLight.addVertex(northFace, 3, coordinates, new Tuple(0,1,0), texUmin, texVmin, aoBrightness);
        VertexLight.addVertex(northFace, 3, coordinates, new Tuple(1,0,0), texUmax, texVmax, aoBrightness);
        VertexLight.addVertex(northFace, 3, coordinates, new Tuple(1,0,0), texUmax, texVmax, aoBrightness);
        VertexLight.addVertex(northFace, 3, coordinates, new Tuple(0,0,0), texUmin, texVmax, aoBrightness);
        
        Tessellator westFace = Tessellator.instance;
        westFace.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        VertexLight.addVertex(westFace, 4, coordinates, new Tuple(0,1,0), texUmin, texVmin, aoBrightness);
        VertexLight.addVertex(westFace, 4, coordinates, new Tuple(0,0,0), texUmin, texVmax, aoBrightness);
        VertexLight.addVertex(westFace, 4, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);
        VertexLight.addVertex(westFace, 4, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);
        
        Tessellator bottomFace = Tessellator.instance;
        bottomFace.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
        //bottomFace.setColorOpaque_F(1.0F, 1.0F, 1.0F);        
        VertexLight.addVertex(bottomFace, 0, coordinates, new Tuple(0,0,0), texUmin, texVmin, aoBrightness);
        VertexLight.addVertex(bottomFace, 0, coordinates, new Tuple(1,0,0), texUmax, texVmin, aoBrightness);
        VertexLight.addVertex(bottomFace, 0, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);
        VertexLight.addVertex(bottomFace, 0, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);

        renderer.enableAO = false;
        
		return true;
    }

}
package net.arcadian83.common;

import java.util.HashMap;

import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderDirtSlope implements ISimpleBlockRenderingHandler {

	public int getRenderId() {
		return Arc.dirtSlopeRenderId;
	}

	public boolean shouldRender3DInInventory() {
		return false;
	}

	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		// TODO
	}

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    	return renderWorldBlockAO(world ,x , y, z, block, modelId, renderer);
    }
    
    
    public boolean renderWorldBlockAO(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    	renderer.enableAO = true;
		
    	ExtraMetadata tileEntity = (ExtraMetadata)world.getBlockTileEntity(x, y, z);
    	int meta = tileEntity.blockMetadata;
    	
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
        
        Tessellator bottomFace = Tessellator.instance;
        Tessellator topFace = Tessellator.instance;
        Tessellator northFace = Tessellator.instance;
        Tessellator southFace = Tessellator.instance;
        Tessellator eastFace = Tessellator.instance;
        Tessellator westFace = Tessellator.instance;
        Tessellator slopeFace = Tessellator.instance;
        
        // TODO: actually calculate color
        bottomFace.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
        topFace.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        northFace.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        southFace.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        eastFace.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        westFace.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        slopeFace.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        
        
        switch(meta) {
        case 38: // Dirt Oblique Slope TopSouthEast
        
            VertexLight.addVertex(bottomFace, 0, coordinates, new Tuple(0,0,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(bottomFace, 0, coordinates, new Tuple(1,0,0), texUmax, texVmin, aoBrightness);
            VertexLight.addVertex(bottomFace, 0, coordinates, new Tuple(1,0,1), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(bottomFace, 0, coordinates, new Tuple(0,0,1), texUmin, texVmax, aoBrightness);
            
            VertexLight.addVertex(topFace, 1, coordinates, new Tuple(0,1,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(topFace, 1, coordinates, new Tuple(0,1,1), texUmax, texVmin, aoBrightness);
            VertexLight.addVertex(topFace, 1, coordinates, new Tuple(0,1,1), texUmax, texVmin, aoBrightness);
            VertexLight.addVertex(topFace, 1, coordinates, new Tuple(1,1,0), texUmin, texVmax, aoBrightness);
            
            VertexLight.addVertex(northFace, 1, coordinates, new Tuple(0,1,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(northFace, 1, coordinates, new Tuple(1,1,0), texUmax, texVmin, aoBrightness);
            VertexLight.addVertex(northFace, 3, coordinates, new Tuple(1,0,0), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(northFace, 3, coordinates, new Tuple(0,0,0), texUmin, texVmax, aoBrightness);

            VertexLight.addVertex(southFace, 1, coordinates, new Tuple(0,1,1), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(southFace, 1, coordinates, new Tuple(0,1,1), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(southFace, 2, coordinates, new Tuple(0,0,1), texUmin, texVmax, aoBrightness);
            VertexLight.addVertex(southFace, 2, coordinates, new Tuple(1,0,1), texUmax, texVmax, aoBrightness);
            
            VertexLight.addVertex(eastFace, 1, coordinates, new Tuple(1,1,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(eastFace, 1, coordinates, new Tuple(1,1,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(eastFace, 5, coordinates, new Tuple(1,0,1), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(eastFace, 5, coordinates, new Tuple(1,0,0), texUmin, texVmax, aoBrightness);
            
            VertexLight.addVertex(westFace, 4, coordinates, new Tuple(0,0,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(westFace, 4, coordinates, new Tuple(0,0,1), texUmin, texVmax, aoBrightness);
            VertexLight.addVertex(westFace, 4, coordinates, new Tuple(0,1,1), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(westFace, 4, coordinates, new Tuple(0,1,0), texUmax, texVmin, aoBrightness);

            VertexLight.addVertex(slopeFace, 1, coordinates, new Tuple(1,1,0), texUmin, texVmax, aoBrightness);
            VertexLight.addVertex(slopeFace, 2, coordinates, new Tuple(1,0,1), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(slopeFace, 1, coordinates, new Tuple(0,1,1), texUmax, texVmin, aoBrightness);
        	VertexLight.addVertex(slopeFace, 2, coordinates, new Tuple(1,0,1), texUmax, texVmax, aoBrightness);
      
            break;
        
        default: // Dirt Slant Slope TopSouthEast
        	      
            VertexLight.addVertex(slopeFace, 1, coordinates, new Tuple(0,1,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(slopeFace, 2, coordinates, new Tuple(0,0,1), texUmin, texVmax, aoBrightness);
            VertexLight.addVertex(slopeFace, 5, coordinates, new Tuple(1,0,0), texUmax, texVmin, aoBrightness);
            VertexLight.addVertex(slopeFace, 5, coordinates, new Tuple(1,0,0), texUmax, texVmin, aoBrightness);

            VertexLight.addVertex(northFace, 3, coordinates, new Tuple(0,1,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(northFace, 3, coordinates, new Tuple(1,0,0), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(northFace, 3, coordinates, new Tuple(1,0,0), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(northFace, 3, coordinates, new Tuple(0,0,0), texUmin, texVmax, aoBrightness);

            VertexLight.addVertex(westFace, 4, coordinates, new Tuple(0,1,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(westFace, 4, coordinates, new Tuple(0,0,0), texUmin, texVmax, aoBrightness);
            VertexLight.addVertex(westFace, 4, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(westFace, 4, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);
            
            VertexLight.addVertex(bottomFace, 0, coordinates, new Tuple(0,0,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(bottomFace, 0, coordinates, new Tuple(1,0,0), texUmax, texVmin, aoBrightness);
            VertexLight.addVertex(bottomFace, 0, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(bottomFace, 0, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);

        	break;
        }
        
        renderer.enableAO = false;
        
		return true;
	}

}
package net.arcadian83.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.ListIterator;

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
    	int texture = 3;
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
        
        case 29: // Dirt Slant Slope TopNorthEast
        	
        	VertexLight.addVertex(bottomFace, Direction.down, coordinates, new Tuple(0,0,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(bottomFace, Direction.down, coordinates, new Tuple(1,0,0), texUmax, texVmin, aoBrightness);
            VertexLight.addVertex(bottomFace, Direction.down, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(bottomFace, Direction.down, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);
            
            VertexLight.addVertex(southFace, Direction.south, coordinates, new Tuple(0,1,1), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(southFace, Direction.south, coordinates, new Tuple(0,0,1), texUmin, texVmax, aoBrightness);
            VertexLight.addVertex(southFace, Direction.south, coordinates, new Tuple(1,0,1), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(southFace, Direction.south, coordinates, new Tuple(1,0,1), texUmax, texVmax, aoBrightness);
            
            

            VertexLight.addVertex(westFace, Direction.west, coordinates, new Tuple(0,1,1), texUmax, texVmin, aoBrightness);
            VertexLight.addVertex(westFace, Direction.west, coordinates, new Tuple(0,0,0), texUmin, texVmax, aoBrightness);
            VertexLight.addVertex(westFace, Direction.west, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(westFace, Direction.west, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);
            
            VertexLight.addVertex(slopeFace, Direction.up, coordinates, new Tuple(0,1,1), texUmin, texVmax, aoBrightness);
            VertexLight.addVertex(slopeFace, Direction.up, coordinates, new Tuple(1,0,1), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(slopeFace, Direction.up, coordinates, new Tuple(1,0,1), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(slopeFace, Direction.up, coordinates, new Tuple(0,0,0), texUmin, texVmin, aoBrightness);
            
            
        	
        	break;
        
        case 38: // Dirt Oblique Slope TopSouthEast
        
            VertexLight.addVertex(bottomFace, Direction.down, coordinates, new Tuple(0,0,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(bottomFace, Direction.down, coordinates, new Tuple(1,0,0), texUmax, texVmin, aoBrightness);
            VertexLight.addVertex(bottomFace, Direction.down, coordinates, new Tuple(1,0,1), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(bottomFace, Direction.down, coordinates, new Tuple(0,0,1), texUmin, texVmax, aoBrightness);
            
            VertexLight.addVertex(topFace, Direction.up, coordinates, new Tuple(0,1,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(topFace, Direction.up, coordinates, new Tuple(0,1,1), texUmax, texVmin, aoBrightness);
            VertexLight.addVertex(topFace, Direction.up, coordinates, new Tuple(0,1,1), texUmax, texVmin, aoBrightness);
            VertexLight.addVertex(topFace, Direction.up, coordinates, new Tuple(1,1,0), texUmin, texVmax, aoBrightness);
            
            VertexLight.addVertex(northFace, Direction.north, coordinates, new Tuple(0,1,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(northFace, Direction.north, coordinates, new Tuple(1,1,0), texUmax, texVmin, aoBrightness);
            VertexLight.addVertex(northFace, Direction.north, coordinates, new Tuple(1,0,0), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(northFace, Direction.north, coordinates, new Tuple(0,0,0), texUmin, texVmax, aoBrightness);

            VertexLight.addVertex(southFace, Direction.south, coordinates, new Tuple(0,1,1), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(southFace, Direction.south, coordinates, new Tuple(0,1,1), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(southFace, Direction.south, coordinates, new Tuple(0,0,1), texUmin, texVmax, aoBrightness);
            VertexLight.addVertex(southFace, Direction.south, coordinates, new Tuple(1,0,1), texUmax, texVmax, aoBrightness);
            
            VertexLight.addVertex(eastFace, Direction.east, coordinates, new Tuple(1,1,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(eastFace, Direction.east, coordinates, new Tuple(1,1,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(eastFace, Direction.east, coordinates, new Tuple(1,0,1), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(eastFace, Direction.east, coordinates, new Tuple(1,0,0), texUmin, texVmax, aoBrightness);
            
            VertexLight.addVertex(westFace, Direction.west, coordinates, new Tuple(0,0,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(westFace, Direction.west, coordinates, new Tuple(0,0,1), texUmin, texVmax, aoBrightness);
            VertexLight.addVertex(westFace, Direction.west, coordinates, new Tuple(0,1,1), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(westFace, Direction.west, coordinates, new Tuple(0,1,0), texUmax, texVmin, aoBrightness);

            VertexLight.addVertex(slopeFace, Direction.up, coordinates, new Tuple(1,1,0), texUmin, texVmax, aoBrightness);
            VertexLight.addVertex(slopeFace, Direction.up, coordinates, new Tuple(1,0,1), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(slopeFace, Direction.up, coordinates, new Tuple(0,1,1), texUmax, texVmin, aoBrightness);
        	VertexLight.addVertex(slopeFace, Direction.up, coordinates, new Tuple(1,0,1), texUmax, texVmax, aoBrightness);
      
            break;
        
        default: // Dirt Slant Slope TopSouthEast
        	
        	ArrayList<Tuple> vertexesToRemove = new ArrayList<Tuple>() {{
        		add(new Tuple(1,0,1));
        		add(new Tuple(1,1,0));
        		add(new Tuple(0,1,1));
        		add(new Tuple(1,1,1));
        	};};
        	ArrayList<SlopeFace> slopes = new ArrayList<SlopeFace>();
        	slopes.add(new SlopeFace() {{
        		vertexes.add(new Tuple(1,0,0));
        		vertexes.add(new Tuple(0,1,0));
        		vertexes.add(new Tuple(0,1,0));
        		vertexes.add(new Tuple(0,0,1));
        		direction = Direction.up;
        	};});
        	renderSlopedBlock(slopes, vertexesToRemove, coordinates, texture, texture, texture, aoBrightness);
        	
        	/*HashMap<Tuple, Tuple> slopeDeformations = new HashMap<Tuple, Tuple>();
        	slopeDeformations.put(new Tuple(1,0,1), new Tuple(0,0,1));
        	slopeDeformations.put(new Tuple(1,1,0), new Tuple(1,0,0));
        	slopeDeformations.put(new Tuple(0,1,1), new Tuple(0,1,0));
        	slopeDeformations.put(new Tuple(1,1,1), new Tuple(1,0,0));*/
        	
        	/*
        	VertexLight.addVertex(bottomFace, Direction.down, coordinates, new Tuple(0,0,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(bottomFace, Direction.down, coordinates, new Tuple(1,0,0), texUmax, texVmin, aoBrightness);
            VertexLight.addVertex(bottomFace, Direction.down, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(bottomFace, Direction.down, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);
            
            VertexLight.addVertex(northFace, Direction.north, coordinates, new Tuple(0,1,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(northFace, Direction.north, coordinates, new Tuple(1,0,0), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(northFace, Direction.north, coordinates, new Tuple(1,0,0), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(northFace, Direction.north, coordinates, new Tuple(0,0,0), texUmin, texVmax, aoBrightness);

            VertexLight.addVertex(westFace, Direction.west, coordinates, new Tuple(0,1,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(westFace, Direction.west, coordinates, new Tuple(0,0,0), texUmin, texVmax, aoBrightness);
            VertexLight.addVertex(westFace, Direction.west, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);
            VertexLight.addVertex(westFace, Direction.west, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);
            
            VertexLight.addVertex(slopeFace, Direction.up, coordinates, new Tuple(0,1,0), texUmin, texVmin, aoBrightness);
            VertexLight.addVertex(slopeFace, Direction.up, coordinates, new Tuple(0,0,1), texUmin, texVmax, aoBrightness);
            VertexLight.addVertex(slopeFace, Direction.up, coordinates, new Tuple(1,0,0), texUmax, texVmin, aoBrightness);
            VertexLight.addVertex(slopeFace, Direction.up, coordinates, new Tuple(1,0,0), texUmax, texVmin, aoBrightness);
*/
            
        	break;
        }
        
        renderer.enableAO = false;
        
		return true;
	}
    
    public void renderSlopedBlock(
    		ArrayList<SlopeFace> slopeFaces,
    		ArrayList<Tuple> vertexesToRemove,
    		Tuple coordinates,
    		int textureTop,
    		int textureBottom,
    		int textureSides,
    		HashMap<Tuple, Integer> aoBrightness) {
    	
    	// TODO: calculate this from given texture id
    	double texUmin = 0.125;
        double texUmax = 0.1875;
        double texVmin = 0;
        double texVmax = 0.0625;
        
        //array of faces (direction facing outward) of vertexes
        HashMap<Integer, ArrayList<Tuple>> faceVertexes = new HashMap<Integer, ArrayList<Tuple>>();
        
        // start the vertexes out in a cube configuration
        for(int direction : Direction.all) {
        	faceVertexes.put(direction, Tuple.getFaceVertexesOfCube(direction));
        }

        // remove the specified vertexes specified from the cube
        // to sculpt the cut-out for the slope        
        ListIterator it = vertexesToRemove.listIterator();
        while (it.hasNext()) {
            Tuple vertexToRemove = (Tuple)it.next();
            for(int direction : Direction.all) {
            	int indexToRemove = faceVertexes.get(direction).indexOf(vertexToRemove);
            	if(indexToRemove != -1) {
            		faceVertexes.get(direction).remove(indexToRemove);
            	}
            }
        }
        
        
        for(int direction : Direction.all) {
    		// add an extra vertex to faces with 3, since Minecraft seems to like 4-sided polygons
            // just duplicate the last vertex
    		if(faceVertexes.get(direction).size() == 3) {
    			faceVertexes.get(direction).add(new Tuple(faceVertexes.get(direction).get(2)));
    		}
        	
        	// remove faces with no area
            // faces with 2 or fewer vertexes will be removed
        	if(faceVertexes.get(direction).size() <= 2) {
        		faceVertexes.remove(direction);
        	}
        }
        
        
        
        
        HashMap<Integer, Tessellator> tess = new HashMap<Integer, Tessellator>();
        for(int direction : Direction.all) {
        	
        	// we may have removed faces due to them deforming to nothing
        	if(!faceVertexes.containsKey(direction)) continue;
        	
        	tess.put(direction, Tessellator.instance);
        	for(Tuple tuple : faceVertexes.get(direction)) {
        		VertexLight.addVertex(
        				tess.get(direction), 
        				direction, 
        				coordinates, 
        				tuple, 
        				texUmin + (texUmax - texUmin) * tuple.getTextureU(direction), 
        				texVmin + (texVmax - texVmin) * tuple.getTextureV(direction), 
        				aoBrightness);
        	}
        }
        
        // finally, render our slopes, the 7th-and-up faces for our cube
        int faceId = 7;
        for(SlopeFace slope : slopeFaces) {
        	
        	tess.put(faceId, Tessellator.instance);
        	for(Tuple tuple : slope.vertexes) {
        		VertexLight.addVertex(
        				tess.get(faceId), 
        				slope.direction, 
        				coordinates, 
        				tuple, 
        				texUmin + (texUmax - texUmin) * tuple.getTextureU(slope.direction), 
        				texVmin + (texVmax - texVmin) * tuple.getTextureV(slope.direction), 
        				aoBrightness);
        	}
        	faceId++;
        }
        
    }

}
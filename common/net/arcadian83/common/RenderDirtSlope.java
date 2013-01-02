package net.arcadian83.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderDirtSlope implements ISimpleBlockRenderingHandler {

	private int metaOffset = 0;
	
	@Override
	public int getRenderId() {
		return Arc.dirtSlopeRenderId;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		// TODO
	}

	@Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    	return renderWorldBlockAO(world ,x , y, z, block, modelId, renderer);
    }
    
    
    public boolean renderWorldBlockAO(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    	renderer.enableAO = true;
    	
    	
    	/*World clientWorld;
    	try {
    		clientWorld = (World)world;
    	} catch (Exception e) {
    		System.out.println(e.toString());
    		return true;
    	}
    	if (clientWorld.isRemote)
        {
    		System.out.println("WORLD.ISREMOTE == TRUE");
                return true;
        }*/
    	
    	//int meta = ((ExtraMetadata)world.getBlockTileEntity(x, y, z)).meta;
    	/*ExtraMetadata extraMeta = (ExtraMetadata)world.getBlockTileEntity(chunkX, y, chunkZ);
    	if(extraMeta == null) {
    		System.out.println("extraMeta was null");
    		return true;
    	}
    	int meta = extraMeta.meta;*/
    	int extendedMeta = world.getBlockMetadata(x, y, z) + getMetaOffset();
    	//System.out.println("GETMETA " + meta + " AT (" + x + "," + y + "," + z + ")");
    	
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
        
        
        ArrayList<Tuple> vertexesToRemove = new ArrayList<Tuple>();
        ArrayList<SlopeFace> slopesToAdd = new ArrayList<SlopeFace>();
        SlopeBlockDefinition slopeBlock = new SlopeBlockDefinition();
        
        switch(extendedMeta) {
        
        case 0: // Straight Slope TopNorth
        case 1:
        case 2:
        case 3:
        case 4: // Straight Slope BottomNorth
        case 5:
        case 6:
        case 7:
        case 8: // Straight Slope NorthWest
        case 9:
        case 10:
        case 11:
        	slopeBlock.removeVertex(new Tuple(0,1,0));
        	slopeBlock.removeVertex(new Tuple(1,1,0));
        	slopeBlock.addSlope(new SlopeFace() {{
        		vertexes.add(new Vector(1,0,0,2));
        		vertexes.add(new Vector(0,0,0,2));
        		vertexes.add(new Vector(0,1,1,1));
        		vertexes.add(new Vector(1,1,1,1));
        		direction = Direction.up;
        	};});
        	
        	switch(extendedMeta) {
        		case 0:
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 1:
        			slopeBlock.rotateClockwise();
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 2:
        			slopeBlock.rotateClockwise();
        			slopeBlock.rotateClockwise();
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 3:
        			slopeBlock.rotateClockwise();
        			slopeBlock.rotateClockwise();
        			slopeBlock.rotateClockwise();
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 4:
        			slopeBlock.rollNorth();
        			slopeBlock.rollNorth();
        			slopeBlock.rotateClockwise();
        			slopeBlock.rotateClockwise();
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 5:
        			slopeBlock.rollNorth();
        			slopeBlock.rollNorth();
        			slopeBlock.rotateClockwise();
        			slopeBlock.rotateClockwise();
        			slopeBlock.rotateClockwise();
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 6:
        			slopeBlock.rollNorth();
        			slopeBlock.rollNorth();
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 7:
        			slopeBlock.rollNorth();
        			slopeBlock.rollNorth();
        			slopeBlock.rotateClockwise();
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 8:
           			slopeBlock.rotateClockwise();
           			slopeBlock.rotateClockwise();
           			slopeBlock.rotateClockwise();
        			slopeBlock.rollNorth();
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 9:
           			slopeBlock.rotateClockwise();
        			slopeBlock.rollNorth();
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 10:
           			slopeBlock.rotateClockwise();
        			slopeBlock.rollNorth();
        			slopeBlock.rotateClockwise();
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 11:
           			slopeBlock.rotateClockwise();
        			slopeBlock.rollNorth();
        			slopeBlock.rotateClockwise();
        			slopeBlock.rotateClockwise();
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        	}
        	break;
        	
        case 12: // Corner Slope TopNorthWest
        case 13:
        case 14:
        case 15:
        case 16:
        case 17:
        case 18:
        case 19:
        	slopeBlock.removeVertex(new Tuple(0,1,0));
        	slopeBlock.removeVertex(new Tuple(0,1,1));
        	slopeBlock.removeVertex(new Tuple(1,1,0));
        	slopeBlock.addSlope(new SlopeFace() {{
        		vertexes.add(new Vector(0,0,0,2));
        		vertexes.add(new Vector(0,0,0,2));
        		vertexes.add(new Vector(1,1,1,1));
        		vertexes.add(new Vector(1,0,0,2));
        		direction = Direction.up;
        	};});
        	slopeBlock.addSlope(new SlopeFace() {{
        		vertexes.add(new Vector(0,0,0,5));
        		vertexes.add(new Vector(0,0,0,5));
        		vertexes.add(new Vector(0,0,1,5));
        		vertexes.add(new Vector(1,1,1,1));
        		direction = Direction.up;
        	};});
        	
        	switch(extendedMeta) {
    			case 12:
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
    			case 13:
    				slopeBlock.rotateClockwise();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
    			case 14:
    				slopeBlock.rotateClockwise();
    				slopeBlock.rotateClockwise();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
    			case 15:
    				slopeBlock.rotateClockwise();
    				slopeBlock.rotateClockwise();
    				slopeBlock.rotateClockwise();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
    			case 16:
    				slopeBlock.rollNorth();
    				slopeBlock.rollNorth();
    				slopeBlock.rotateClockwise();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
    			case 17:
    				slopeBlock.rollNorth();
    				slopeBlock.rollNorth();
    				slopeBlock.rotateClockwise();
    				slopeBlock.rotateClockwise();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
    			case 18:
    				slopeBlock.rollNorth();
    				slopeBlock.rollNorth();
    				slopeBlock.rotateClockwise();
    				slopeBlock.rotateClockwise();
    				slopeBlock.rotateClockwise();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
    			case 19:
    				slopeBlock.rollNorth();
    				slopeBlock.rollNorth();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
        	}
        	break;
        	
        case 20: //Interior Corner Slope TopNorthWest
        case 21:
        case 22:
        case 23:
        case 24:
        case 25:
        case 26:
        case 27:
        	
        	slopeBlock.removeVertex(new Tuple(0,1,0));
        	slopeBlock.removeVertex(new Tuple(1,1,0));
        	slopeBlock.removeVertex(new Tuple(1,1,1));
        	slopeBlock.removeVertex(new Tuple(0,1,1));
        	slopeBlock.addSlope(new SlopeFace() {{
        		vertexes.add(new Vector(0,0,0,5));
        		vertexes.add(new Vector(0,0,0,5));
        		vertexes.add(new Vector(1,1,1,1));
        		vertexes.add(new Vector(1,1,0,1));
        		direction = Direction.up;
        	};});
        	slopeBlock.addSlope(new SlopeFace() {{
        		vertexes.add(new Vector(0,0,0,2));
        		vertexes.add(new Vector(0,0,0,2));
        		vertexes.add(new Vector(0,1,1,1));
        		vertexes.add(new Vector(1,1,1,1));
        		direction = Direction.up;
        	};});
        	slopeBlock.addSlope(new SlopeFace() {{
        		vertexes.add(new Vector(1,0,0,4));
        		vertexes.add(new Vector(1,1,0,4));
        		vertexes.add(new Vector(1,1,1,4));
        		vertexes.add(new Vector(1,0,1,4));
        		direction = Direction.east;
        	};});
        	slopeBlock.addSlope(new SlopeFace() {{
        		vertexes.add(new Vector(0,0,1,3));
        		vertexes.add(new Vector(1,0,1,3));
        		vertexes.add(new Vector(1,1,1,3));
        		vertexes.add(new Vector(0,1,1,3));
        		direction = Direction.south;
        	};});
        	slopeBlock.addSlope(new SlopeFace() {{
        		vertexes.add(new Vector(0,0,0,5));
        		vertexes.add(new Vector(0,0,1,5));
        		vertexes.add(new Vector(0,1,1,5));
        		vertexes.add(new Vector(0,0,0,5));
        		
        		
        		direction = Direction.west;
        	};});
        	slopeBlock.addSlope(new SlopeFace() {{
        		vertexes.add(new Vector(0,0,0,2));
        		vertexes.add(new Vector(1,1,0,2));
        		vertexes.add(new Vector(1,1,0,2));
        		vertexes.add(new Vector(1,0,0,2));
        		
        		
        		
        		
        		direction = Direction.north;
        	};});
        	
        	switch(extendedMeta) {
				case 20:
					renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
				case 21:
    				slopeBlock.rotateClockwise();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
    			case 22:
    				slopeBlock.rotateClockwise();
    				slopeBlock.rotateClockwise();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
    			case 23:
    				slopeBlock.rotateClockwise();
    				slopeBlock.rotateClockwise();
    				slopeBlock.rotateClockwise();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
    			case 24:
    				slopeBlock.rollNorth();
    				slopeBlock.rollNorth();
    				slopeBlock.rotateClockwise();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
    			case 25:
    				slopeBlock.rollNorth();
    				slopeBlock.rollNorth();
    				slopeBlock.rotateClockwise();
    				slopeBlock.rotateClockwise();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
    			case 26:
    				slopeBlock.rollNorth();
    				slopeBlock.rollNorth();
    				slopeBlock.rotateClockwise();
    				slopeBlock.rotateClockwise();
    				slopeBlock.rotateClockwise();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
    			case 27:
    				slopeBlock.rollNorth();
    				slopeBlock.rollNorth();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
        	}
        	break;
        
        case 28: // Slant Slope TopNorthWest
        case 29:
        case 30:
        case 31:
        	slopeBlock.removeVertex(new Tuple(0,0,0));
        	slopeBlock.removeVertex(new Tuple(0,1,0));
        	slopeBlock.removeVertex(new Tuple(0,1,1));
        	slopeBlock.removeVertex(new Tuple(1,1,0));
        	slopeBlock.addSlope(new SlopeFace() {{
        		vertexes.add(new Vector(1,0,0,2));
        		vertexes.add(new Vector(0,0,1,5));
        		vertexes.add(new Vector(1,1,1,1));
        		vertexes.add(new Vector(1,1,1,1));
        		direction = Direction.up;
        	};});
        	
        	switch(extendedMeta) {
        		case 28:
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 29:
        			slopeBlock.rotateClockwise();
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 30:
        			slopeBlock.rotateClockwise();
        			slopeBlock.rotateClockwise();
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 31:
        			slopeBlock.rotateClockwise();
        			slopeBlock.rotateClockwise();
        			slopeBlock.rotateClockwise();
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 32:
    				slopeBlock.rollNorth();
    				slopeBlock.rollNorth();
    				slopeBlock.rotateClockwise();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
    			case 33:
    				slopeBlock.rollNorth();
    				slopeBlock.rollNorth();
    				slopeBlock.rotateClockwise();
    				slopeBlock.rotateClockwise();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
    			case 34:
    				slopeBlock.rollNorth();
    				slopeBlock.rollNorth();
    				slopeBlock.rotateClockwise();
    				slopeBlock.rotateClockwise();
    				slopeBlock.rotateClockwise();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
    			case 35:
    				slopeBlock.rollNorth();
    				slopeBlock.rollNorth();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
        	}        
        	break;
        
        case 36: // Oblique Slope TopNorthWest
        case 37:
        case 38:
        case 39:
        case 40:
        case 41:
        case 42:
        case 43:
        	slopeBlock.removeVertex(new Tuple(0,1,0));
        	slopeBlock.addSlope(new SlopeFace() {{
        		vertexes.add(new Vector(0,0,0,1));
        		vertexes.add(new Vector(0,0,0,1));
        		vertexes.add(new Vector(0,1,1,2));
        		vertexes.add(new Vector(1,1,0,5));
        		direction = Direction.up;
        	};});
        	
        	switch(extendedMeta) {
        		case 36:
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 37:
        			slopeBlock.rotateClockwise();
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 38:
        			slopeBlock.rotateClockwise();
        			slopeBlock.rotateClockwise();
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 39:
        			slopeBlock.rotateClockwise();
        			slopeBlock.rotateClockwise();
        			slopeBlock.rotateClockwise();
        			renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
        			break;
        		case 40:
    				slopeBlock.rollNorth();
    				slopeBlock.rollNorth();
    				slopeBlock.rotateClockwise();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
    			case 41:
    				slopeBlock.rollNorth();
    				slopeBlock.rollNorth();
    				slopeBlock.rotateClockwise();
    				slopeBlock.rotateClockwise();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
    			case 42:
    				slopeBlock.rollNorth();
    				slopeBlock.rollNorth();
    				slopeBlock.rotateClockwise();
    				slopeBlock.rotateClockwise();
    				slopeBlock.rotateClockwise();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
    			case 43:
    				slopeBlock.rollNorth();
    				slopeBlock.rollNorth();
    				renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);
    				break;
        	}
        	break;
        	
        /*case 38: // Oblique Slope TopSouthEast
        
        	vertexesToRemove = new ArrayList<Tuple>() {{
        		add(new Tuple(1,1,1));
        	};};
        	slopesToAdd = new ArrayList<SlopeFace>();
        	slopesToAdd.add(new SlopeFace() {{
        		vertexes.add(new Tuple(1,1,0));
        		vertexes.add(new Tuple(0,1,1));
        		vertexes.add(new Tuple(1,0,1));
        		vertexes.add(new Tuple(1,0,1));
        		direction = Direction.up;
        	};});
        	renderSlopedBlock(slopesToAdd, vertexesToRemove, coordinates, texture, texture, texture, aoBrightness);   
            break;*/
        
        default: // cube (without removed vertexes or extra slope faces)
        	renderSlopedBlock(slopeBlock, coordinates, texture, texture, texture, aoBrightness);      
        	break;
        }
        
        renderer.enableAO = false;
        
		return true;
	}
    
    public void renderSlopedBlock(
    		SlopeBlockDefinition slopeBlock,
    		Tuple coordinates,
    		int textureTop,
    		int textureBottom,
    		int textureSides,
    		HashMap<Tuple, Integer> aoBrightness) {
    	renderSlopedBlock(slopeBlock.slopesToAdd, slopeBlock.vertexesToRemove, coordinates, textureTop, textureBottom, textureSides, aoBrightness);        
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
        	for(Vector vector : slope.vertexes) {
        		VertexLight.addVertex(
        				tess.get(faceId), 
        				vector.dir, 
        				coordinates, 
        				vector.loc, 
        				texUmin + (texUmax - texUmin) * vector.loc.getTextureU(slope.direction), 
        				texVmin + (texVmax - texVmin) * vector.loc.getTextureV(slope.direction), 
        				aoBrightness);
        	}
        	faceId++;
        }
        
    }
    
    public int getMetaOffset() {
		return metaOffset;
	}

}
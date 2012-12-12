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
    	
    	//int test = aoBrightness.get(new Tuple(0,0,0));
    	
    	// if (this.field_83027_i <= 0.0D) surrounded each of these
    	
    	/*aoBrightness.put(new Tuple(-1, 0, 0), block.getMixedBrightnessForBlock(renderer.blockAccess, x-1, y  , z  ));
    	aoBrightness.put(new Tuple( 1, 0, 0), block.getMixedBrightnessForBlock(renderer.blockAccess, x+1, y  , z  ));
    	aoBrightness.put(new Tuple( 0,-1, 0), block.getMixedBrightnessForBlock(renderer.blockAccess, x  , y-1, z  ));
    	aoBrightness.put(new Tuple( 0, 1, 0), block.getMixedBrightnessForBlock(renderer.blockAccess, x  , y+1, z  ));
    	aoBrightness.put(new Tuple( 0, 0,-1), block.getMixedBrightnessForBlock(renderer.blockAccess, x  , y  , z-1));
    	aoBrightness.put(new Tuple( 0, 0, 1), block.getMixedBrightnessForBlock(renderer.blockAccess, x  , y  , z+1));
    	*/
    	
    	
    	// bottom face
    	
    	/*this.brightnessTopLeft = this.getAoBrightness(
    			this.aoBrightnessXYZNNP, 
    			this.aoBrightnessXYNN, 
    			this.aoBrightnessYZNP, 
    			var21);*/
    	renderer.brightnessTopLeft = renderer.getAoBrightness(
    			aoBrightness.get(new Tuple(-1,-1, 1)),
    			aoBrightness.get(new Tuple(-1,-1, 0)),
    			aoBrightness.get(new Tuple( 0,-1, 1)),
    			aoBrightness.get(new Tuple( 0,-1, 0)));
    	// pseudocode:
    	// faces: down, up, north, south, east, west -> 0-5
    	// do bottomFace (0) topLeftCorner (-1,-1, 1)
    	// get bottomFace,topLeftCorner
    	//renderer.brightnessTopLeft = VertexLight.getBrightnessAtFaceCorner(0, new Tuple(-1,-1, 1), aoBrightness);
    	
    	
    	renderer.brightnessBottomRight = renderer.getAoBrightness(
    			aoBrightness.get(new Tuple(0,-1,-1)),
    			aoBrightness.get(new Tuple(1,-1, 0)),
    			aoBrightness.get(new Tuple(1,-1,-1)),
    			aoBrightness.get(new Tuple(0,-1, 0)));
    	
    	// TODO brightnessTopRight...
    	
    	renderer.colorRedTopLeft = 0.5F;
    	renderer.colorRedTopLeft *= (aoLightValue.get(new Tuple(-1,-1, 1)) + 
    			aoLightValue.get(new Tuple(-1,-1, 0)) +
    			aoLightValue.get(new Tuple(0,-1, 1)) +
    			aoLightValue.get(new Tuple(0,-1, 0))) / 4.0F;
    	
    	// pseudocode:
    	// get bottomFace,topLeftCorner
    	// get 
    	
    	// TODO renderer.colorGreenTopLeft...
        
        //TODO...
        //bottomFace.addVertexWithUV(x + 1, y, z, texUmax, texVmin);
        //bottomFace.addVertexWithUV(x + 1, y, z, texUmax, texVmin);
        //bottomFace.addVertexWithUV(x, y, z + 1, texUmin, texVmax);
    	
    	
    	//if(Minecraft.isAmbientOcclusionEnabled())
        //{
            //return renderSlopesBlockWithAmbientOcclusion(block, x, y, z, f, f1, f2, iDir, renderer, world);
			
			/*Tessellator tessellator = Tessellator.instance;
	        tessellator.setBrightness(lBrightness);
	        
	        
	        
			tessellator.setColorOpaque_F(colorRedTopLeft_TopFace, colorGreenTopLeft_TopFace, colorBlueTopLeft_TopFace);
			tessellator.addVertexWithUV(d8, d9, d11, d4, d6);
			tessellator.setColorOpaque_F(colorRedBottomLeft_TopFace, colorGreenBottomLeft_TopFace, colorBlueBottomLeft_TopFace);
			tessellator.addVertexWithUV(d8, d9, d10, d4, d5);
			tessellator.setColorOpaque_F(colorRedBottomRight_TopFace, colorGreenBottomRight_TopFace, colorBlueBottomRight_TopFace);
			tessellator.addVertexWithUV(d7, d9, d10, d3, d5);
			tessellator.setColorOpaque_F(colorRedTopRight_TopFace, colorGreenTopRight_TopFace, colorBlueTopRight_TopFace);
			tessellator.addVertexWithUV(d7, d9, d11, d3, d6);*/
        //} else {
            //return renderSlopesBlockWithColorMultiplier(block, x, y, z, f, f1, f2, iDir, renderer, world);
        //}
        
        double texUmin = 0.125;
        double texUmax = 0.1875;
        double texVmin = 0;
        double texVmax = 0.0625;
        
        Tessellator topFace = Tessellator.instance;
        //var27 = par1Block.getBlockTexture(this.blockAccess, par2, par3, par4, 2);
        topFace.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        //topFace.setBrightness(15728832);
        //topFace.setBrightness(VertexLight.getBrightnessAtFaceCorner(1, new Tuple(0,1,0), aoBrightness));
        //topFace.addVertexWithUV(x, y + 1, z, texUmin, texVmin);
        
        
        //var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
        //var9.setBrightness(this.brightnessBottomLeft);
        //topFace.setBrightness(VertexLight.getBrightnessAtFaceCorner(2, new Tuple(0,0,1), aoBrightness));
        //topFace.addVertexWithUV(x, y, z + 1, texUmin, texVmax);
        
        //var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
        //var9.setBrightness(this.brightnessBottomRight);
        
        //tess.addVertexWithUV(x + 1, y + 0.1, z + 1, 0.1875, 0.0625);
        //topFace.setBrightness(VertexLight.getBrightnessAtFaceCorner(5, new Tuple(1,0,0), aoBrightness));
        //topFace.addVertexWithUV(x + 1, y, z, texUmax, texVmin);
        
        //var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
        //var9.setBrightness(this.brightnessTopRight);
        //topFace.addVertexWithUV(x + 1, y, z, texUmax, texVmin);
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
        
        
//        northFace.setBrightness(15728832);
//        northFace.addVertexWithUV(x, y + 1, z, texUmin, texVmin);
//        northFace.addVertexWithUV(x + 1, y, z, texUmax, texVmax);
//        northFace.addVertexWithUV(x + 1, y, z, texUmax, texVmax);
//        northFace.addVertexWithUV(x, y, z, texUmin, texVmax);
        
        Tessellator westFace = Tessellator.instance;
        westFace.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        VertexLight.addVertex(westFace, 4, coordinates, new Tuple(0,1,0), texUmin, texVmin, aoBrightness);
        VertexLight.addVertex(westFace, 4, coordinates, new Tuple(0,0,0), texUmin, texVmax, aoBrightness);
        VertexLight.addVertex(westFace, 4, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);
        VertexLight.addVertex(westFace, 4, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);
        
        
        
//        westFace.setBrightness(15728832);
//        westFace.addVertexWithUV(x, y + 1, z, texUmin, texVmin);
//        westFace.addVertexWithUV(x, y, z, texUmin, texVmax);
//        westFace.addVertexWithUV(x, y, z + 1, texUmax, texVmax);
//        westFace.addVertexWithUV(x, y, z + 1, texUmax, texVmax);
        
        Tessellator bottomFace = Tessellator.instance;
        bottomFace.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
        //bottomFace.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        
        //bottomFace.setBrightness(15728832);
        //bottomFace.setBrightness(renderer.brightnessTopLeft);
//        bottomFace.setBrightness(VertexLight.getBrightnessAtFaceCorner(0, new Tuple(-1,-1,-1), aoBrightness));
//        bottomFace.addVertexWithUV(x, y, z, texUmin, texVmin);
//        bottomFace.setBrightness(VertexLight.getBrightnessAtFaceCorner(0, new Tuple( 1,-1,-1), aoBrightness));
//        bottomFace.addVertexWithUV(x + 1, y, z, texUmax, texVmin);
//        bottomFace.setBrightness(VertexLight.getBrightnessAtFaceCorner(0, new Tuple( 1,-1, 1), aoBrightness));
//        bottomFace.addVertexWithUV(x + 1, y, z + 1, texUmax, texVmax);
//        bottomFace.setBrightness(VertexLight.getBrightnessAtFaceCorner(0, new Tuple(-1,-1, 1), aoBrightness));
//        bottomFace.addVertexWithUV(x, y, z + 1, texUmin, texVmax);
        VertexLight.addVertex(bottomFace, 0, coordinates, new Tuple(0,0,0), texUmin, texVmin, aoBrightness);
        VertexLight.addVertex(bottomFace, 0, coordinates, new Tuple(1,0,0), texUmax, texVmin, aoBrightness);
        VertexLight.addVertex(bottomFace, 0, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);
        VertexLight.addVertex(bottomFace, 0, coordinates, new Tuple(0,0,1), texUmax, texVmax, aoBrightness);
        
        
        
        renderer.enableAO = false;
        
		return true;
    }
    
    public boolean renderWorldBlockUnlit(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
    	//int iDir =  world.getBlockMetadata(x, y, z);
        //int l = block.colorMultiplier(world, x, y, z);
        //float f = (float)(l >> 16 & 0xff) / 255F;
        //float f1 = (float)(l >> 8 & 0xff) / 255F;
        //float f2 = (float)(l & 0xff) / 255F;
		
		//if(Minecraft.isAmbientOcclusionEnabled())
        //{
            //return renderSlopesBlockWithAmbientOcclusion(block, x, y, z, f, f1, f2, iDir, renderer, world);
			
			/*Tessellator tessellator = Tessellator.instance;
	        tessellator.setBrightness(lBrightness);
	        
	        
	        
			tessellator.setColorOpaque_F(colorRedTopLeft_TopFace, colorGreenTopLeft_TopFace, colorBlueTopLeft_TopFace);
			tessellator.addVertexWithUV(d8, d9, d11, d4, d6);
			tessellator.setColorOpaque_F(colorRedBottomLeft_TopFace, colorGreenBottomLeft_TopFace, colorBlueBottomLeft_TopFace);
			tessellator.addVertexWithUV(d8, d9, d10, d4, d5);
			tessellator.setColorOpaque_F(colorRedBottomRight_TopFace, colorGreenBottomRight_TopFace, colorBlueBottomRight_TopFace);
			tessellator.addVertexWithUV(d7, d9, d10, d3, d5);
			tessellator.setColorOpaque_F(colorRedTopRight_TopFace, colorGreenTopRight_TopFace, colorBlueTopRight_TopFace);
			tessellator.addVertexWithUV(d7, d9, d11, d3, d6);*/
        //} else {
            //return renderSlopesBlockWithColorMultiplier(block, x, y, z, f, f1, f2, iDir, renderer, world);
        //}
        
        double texUmin = 0.125;
        double texUmax = 0.1875;
        double texVmin = 0;
        double texVmax = 0.0625;
        
        Tessellator southEastFace = Tessellator.instance;
        //var27 = par1Block.getBlockTexture(this.blockAccess, par2, par3, par4, 2);
        southEastFace.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        southEastFace.setBrightness(15728832);
        southEastFace.addVertexWithUV(x, y + 1, z, texUmin, texVmin);
        //var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
        //var9.setBrightness(this.brightnessBottomLeft);
        southEastFace.addVertexWithUV(x, y, z + 1, texUmin, texVmax);
        //var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
        //var9.setBrightness(this.brightnessBottomRight);
        
        //tess.addVertexWithUV(x + 1, y + 0.1, z + 1, 0.1875, 0.0625);
        southEastFace.addVertexWithUV(x + 1, y, z, texUmax, texVmin);
        
        //var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
        //var9.setBrightness(this.brightnessTopRight);
        southEastFace.addVertexWithUV(x + 1, y, z, texUmax, texVmin);
        
        Tessellator northFace = Tessellator.instance;
        northFace.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        northFace.setBrightness(15728832);
        northFace.addVertexWithUV(x, y + 1, z, texUmin, texVmin);
        northFace.addVertexWithUV(x + 1, y, z, texUmax, texVmax);
        northFace.addVertexWithUV(x + 1, y, z, texUmax, texVmax);
        northFace.addVertexWithUV(x, y, z, texUmin, texVmax);
        
        Tessellator westFace = Tessellator.instance;
        westFace.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        westFace.setBrightness(15728832);
        westFace.addVertexWithUV(x, y + 1, z, texUmin, texVmin);
        westFace.addVertexWithUV(x, y, z, texUmin, texVmax);
        westFace.addVertexWithUV(x, y, z + 1, texUmax, texVmax);
        westFace.addVertexWithUV(x, y, z + 1, texUmax, texVmax);
        
        Tessellator bottomFace = Tessellator.instance;
        bottomFace.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        bottomFace.setBrightness(15728832);
        bottomFace.addVertexWithUV(x, y, z, texUmin, texVmin);
        bottomFace.addVertexWithUV(x + 1, y, z, texUmax, texVmin);
        bottomFace.addVertexWithUV(x + 1, y, z, texUmax, texVmin);
        bottomFace.addVertexWithUV(x, y, z + 1, texUmin, texVmax);
        
        
        
        
        
		return true;
    }

    


    
    /*public boolean renderSlopesBlockWithColorMultiplier(Block block, int i, int j, int k, float f, float f1, float f2, int iDir, RenderBlocks renderblocks, IBlockAccess iblockaccess)
    {
    	Tessellator tessellator = Tessellator.instance;        
        boolean flag = false;
        float f3 = 0.5F;
        float f4 = 1.0F;
        float f5 = 0.8F;
        float f6 = 0.6F;
        float f7 = f4 * f;
        float f8 = f4 * f1;
        float f9 = f4 * f2;
        if(block == Block.grass)
        {
            f = f1 = f2 = 1.0F;
        }
        float f10 = f3 * f;
        float f11 = f5 * f;
        float f12 = f6 * f;
        float f13 = f3 * f1;
        float f14 = f5 * f1;
        float f15 = f6 * f1;
        float f16 = f3 * f2;
        float f17 = f5 * f2;
        float f18 = f6 * f2;
        float f19 = block.getAmbientOcclusionLightValue(iblockaccess, i, j, k);			
		
		if(renderblocks.renderAllFaces || block.shouldSideBeRendered(iblockaccess, i, j - 1, k, 0))
        {
			if(iDir/4 != 2){
				float f20 = block.getAmbientOcclusionLightValue(iblockaccess, i, j - 1, k);
				tessellator.setColorOpaque_F(f10 * f20, f13 * f20, f16 * f20);
				renderSlopesBottomFace(block, i, j, k, block.getBlockTexture(iblockaccess, i, j, k, 0),iDir, renderblocks, block.getMixedBrightnessForBlock(iblockaccess, i, j, k));
				flag = true;
			}
        }
		
		if(renderblocks.renderAllFaces || block.shouldSideBeRendered(iblockaccess, i, j + 1, k, 1))
        {
			if(iDir/4 != 1){
				float f21 = block.getAmbientOcclusionLightValue(iblockaccess, i, j + 1, k);
				if(block.getBlockBoundsMaxY() != 1.0D && !block.blockMaterial.isLiquid())
				{
					f21 = f19;
				}
				tessellator.setColorOpaque_F(f7 * f21, f8 * f21, f9 * f21);
				renderSlopesTopFace(block, i, j, k, block.getBlockTexture(iblockaccess, i, j, k, 1),iDir, renderblocks, block.getMixedBrightnessForBlock(iblockaccess, i, j, k));
				flag = true;
			}
        }        
       
        if(renderblocks.renderAllFaces || block.shouldSideBeRendered(iblockaccess, i, j, k - 1, 2) || iDir == 6 || iDir == 10 || iDir == 12)
        {		
			if(iDir == 6){                          
				float f22 = f19;
				tessellator.setColorOpaque_F(f11 * f22, f14 * f22, f17 * f22);	
				renderSlopesEastFace(block, i, j, k, block.getBlockTexture(iblockaccess, i, j, k, 1),iDir, renderblocks, block.getMixedBrightnessForBlock(iblockaccess, i, j, k));
			}else
			if(iDir == 10){ 
                float f22 = f19;           
				tessellator.setColorOpaque_F(f11 * f22, f14 * f22, f17 * f22);	
				renderSlopesEastFace(block, i, j, k, block.getBlockTexture(iblockaccess, i, j, k, 0), iDir, renderblocks, block.getMixedBrightnessForBlock(iblockaccess, i, j, k));
			}else
			if(iDir == 12){ 
                float f22 = f19;					
				tessellator.setColorOpaque_F(f11 * f22, f14 * f22, f17 * f22);		
				renderSlopesEastFace(block, i, j, k, block.getBlockTexture(iblockaccess, i, j, k, 2), iDir, renderblocks, block.getMixedBrightnessForBlock(iblockaccess, i, j, k));	
			}
			else{
				float f22 = block.getAmbientOcclusionLightValue(iblockaccess, i, j, k - 1);
				tessellator.setColorOpaque_F(f11 * f22, f14 * f22, f17 * f22);		
				renderSlopesEastFace(block, i, j, k, block.getBlockTexture(iblockaccess, i, j, k, 2), iDir, renderblocks, block.getMixedBrightnessForBlock(iblockaccess, i, j, k));
			}
            flag = true;
        }
        if(renderblocks.renderAllFaces || block.shouldSideBeRendered(iblockaccess, i, j, k + 1, 3) || iDir == 7 || iDir == 11 || iDir == 13)
        {
			if(iDir == 7){ 
                float f23 =  f19;
				tessellator.setColorOpaque_F(f11 * f23, f14 * f23, f17 * f23);
				renderSlopesWestFace(block, i, j, k, block.getBlockTexture(iblockaccess, i, j, k, 1), iDir, renderblocks, block.getMixedBrightnessForBlock(iblockaccess, i, j, k));
			}else
			if(iDir == 11){ 
                float f23 = f19;           
				tessellator.setColorOpaque_F(f11 * f23, f14 * f23, f17 * f23);
				renderSlopesWestFace(block, i, j, k, block.getBlockTexture(iblockaccess, i, j, k, 0), iDir, renderblocks, block.getMixedBrightnessForBlock(iblockaccess, i, j, k));
			}else
			if(iDir == 13)
            {
                float f23 = f19;            		
				tessellator.setColorOpaque_F(f11 * f23, f14 * f23, f17 * f23);
				renderSlopesWestFace(block, i, j, k, block.getBlockTexture(iblockaccess, i, j, k, 3), iDir, renderblocks, block.getMixedBrightnessForBlock(iblockaccess, i, j, k));
			}
			else{
				float f23 = block.getAmbientOcclusionLightValue(iblockaccess, i, j, k + 1);
				tessellator.setColorOpaque_F(f11 * f23, f14 * f23, f17 * f23);
				renderSlopesWestFace(block, i, j, k, block.getBlockTexture(iblockaccess, i, j, k, 3), iDir, renderblocks, block.getMixedBrightnessForBlock(iblockaccess, i, j, k));
			}           
            flag = true;
        }
        if(renderblocks.renderAllFaces || block.shouldSideBeRendered(iblockaccess, i - 1, j, k, 4) || iDir == 4 || iDir == 8 || iDir == 15)
        {
			if(iDir == 4){ 
                float f24 =  f19;
				tessellator.setColorOpaque_F(f12 * f24, f15 * f24, f18 * f24);
				renderSlopesNorthFace(block, i, j, k, block.getBlockTexture(iblockaccess, i, j, k, 1), iDir, renderblocks, block.getMixedBrightnessForBlock(iblockaccess, i, j, k));
			}else
			if(iDir == 8){ 
                float f24 = f19;           
				tessellator.setColorOpaque_F(f12 * f24, f15 * f24, f18 * f24);
				 renderSlopesNorthFace(block, i, j, k, block.getBlockTexture(iblockaccess, i, j, k, 0), iDir, renderblocks, block.getMixedBrightnessForBlock(iblockaccess, i, j, k));
			}else
			if(iDir == 15){ 
                float f24 = f19;           
				tessellator.setColorOpaque_F(f12 * f24, f15 * f24, f18 * f24);
				 renderSlopesNorthFace(block, i, j, k, block.getBlockTexture(iblockaccess, i, j, k, 4), iDir, renderblocks, block.getMixedBrightnessForBlock(iblockaccess, i, j, k));
			}
			else{
				float f24 = block.getAmbientOcclusionLightValue(iblockaccess, i - 1, j, k);
				tessellator.setColorOpaque_F(f12 * f24, f15 * f24, f18 * f24);
				renderSlopesNorthFace(block, i, j, k, block.getBlockTexture(iblockaccess, i, j, k, 4), iDir, renderblocks, block.getMixedBrightnessForBlock(iblockaccess, i, j, k));
			}           
            flag = true;            
        }
        if(renderblocks.renderAllFaces || block.shouldSideBeRendered(iblockaccess, i + 1, j, k, 5) || iDir == 5 || iDir == 9 || iDir == 14)
        {
			if(iDir == 5){ 
                float f25 =  f19;
				tessellator.setColorOpaque_F(f12 * f25, f15 * f25, f18 * f25);
				renderSlopesSouthFace(block, i, j, k, block.getBlockTexture(iblockaccess, i, j, k, 1), iDir, renderblocks, block.getMixedBrightnessForBlock(iblockaccess, i, j, k));
			}else
			if(iDir == 9){ 
                float f25 = f19;           
				tessellator.setColorOpaque_F(f12 * f25, f15 * f25, f18 * f25);
				renderSlopesSouthFace(block, i, j, k, block.getBlockTexture(iblockaccess, i, j, k, 0), iDir, renderblocks, block.getMixedBrightnessForBlock(iblockaccess, i, j, k));
			}else
			if(iDir == 14){ 
                float f25 = f19;           
				tessellator.setColorOpaque_F(f12 * f25, f15 * f25, f18 * f25);
				renderSlopesSouthFace(block, i, j, k, block.getBlockTexture(iblockaccess, i, j, k, 5), iDir, renderblocks, block.getMixedBrightnessForBlock(iblockaccess, i, j, k));
			}
			else{
				float f25 = block.getAmbientOcclusionLightValue(iblockaccess, i + 1, j, k);
				 tessellator.setColorOpaque_F(f12 * f25, f15 * f25, f18 * f25);
				renderSlopesSouthFace(block, i, j, k, block.getBlockTexture(iblockaccess, i, j, k, 5), iDir, renderblocks, block.getMixedBrightnessForBlock(iblockaccess, i, j, k));
			}
            flag = true;
        }
        return flag;
    }
    
    public void renderSlopesTopFace(Block block, double d, double d1, double d2, 
            int i, int iDir, RenderBlocks renderblocks, int lBrightness)
    {
    	boolean enableAO = false; // TODO: actually check
    	
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(lBrightness);
        if(renderblocks.overrideBlockTexture >= 0)
        {
            i = renderblocks.overrideBlockTexture;
        }
        int j = (i & 0xf) << 4;
        int k = i & 0xf0;
        double d3 = ((double)j + block.getBlockBoundsMinX() * 16D) / 256D;
        double d4 = (((double)j + block.getBlockBoundsMaxX() * 16D) - 0.01D) / 256D;
        double d5 = ((double)k + block.getBlockBoundsMinZ() * 16D) / 256D;
        double d6 = (((double)k + block.getBlockBoundsMaxZ() * 16D) - 0.01D) / 256D;
        if(block.getBlockBoundsMinX() < 0.0D || block.getBlockBoundsMaxX() > 1.0D)
        {
            d3 = ((float)j + 0.0F) / 256F;
            d4 = ((float)j + 15.99F) / 256F;
        }
        if(block.getBlockBoundsMinZ() < 0.0D || block.getBlockBoundsMaxZ() > 1.0D)
        {
            d5 = ((float)k + 0.0F) / 256F;
            d6 = ((float)k + 15.99F) / 256F;
        }
        double d7 = d + block.getBlockBoundsMinX();
        double d8 = d + block.getBlockBoundsMaxX();
        double d9 = d1 + block.getBlockBoundsMaxY();
        double d10 = d2 + block.getBlockBoundsMinZ();
        double d11 = d2 + block.getBlockBoundsMaxZ();
        
		if(enableAO)
        {
			if(iDir == 8 || iDir == 9 || iDir == 10 || iDir == 11){
				// Standard
				tessellator.setColorOpaque_F(colorRedTopLeft_TopFace, colorGreenTopLeft_TopFace, colorBlueTopLeft_TopFace);
				tessellator.addVertexWithUV(d8, d9, d11, d4, d6);
				tessellator.setColorOpaque_F(colorRedBottomLeft_TopFace, colorGreenBottomLeft_TopFace, colorBlueBottomLeft_TopFace);
				tessellator.addVertexWithUV(d8, d9, d10, d4, d5);
				tessellator.setColorOpaque_F(colorRedBottomRight_TopFace, colorGreenBottomRight_TopFace, colorBlueBottomRight_TopFace);
				tessellator.addVertexWithUV(d7, d9, d10, d3, d5);
				tessellator.setColorOpaque_F(colorRedTopRight_TopFace, colorGreenTopRight_TopFace, colorBlueTopRight_TopFace);
				tessellator.addVertexWithUV(d7, d9, d11, d3, d6);
			} else
			if(iDir == 12){
				// Pointing South-West
				tessellator.setColorOpaque_F(colorRedTopLeft_TopFace, colorGreenTopLeft_TopFace, colorBlueTopLeft_TopFace);
				tessellator.addVertexWithUV(d8, d9, d11, d4, d6);
				tessellator.setColorOpaque_F(colorRedBottomLeft_TopFace, colorGreenBottomLeft_TopFace, colorBlueBottomLeft_TopFace);
				tessellator.addVertexWithUV(d8, d9, d10, d4, d5);
				tessellator.setColorOpaque_F(colorRedTopRight_TopFace, colorGreenTopRight_TopFace, colorBlueTopRight_TopFace);
				tessellator.addVertexWithUV(d7, d9, d11, d3, d6);
				tessellator.setColorOpaque_F(colorRedTopRight_TopFace, colorGreenTopRight_TopFace, colorBlueTopRight_TopFace);
				tessellator.addVertexWithUV(d7, d9, d11, d3, d6);
			} else	
			if(iDir == 13){
				// Pointing North-East
				tessellator.setColorOpaque_F(colorRedBottomLeft_TopFace, colorGreenBottomLeft_TopFace, colorBlueBottomLeft_TopFace);
				tessellator.addVertexWithUV(d8, d9, d10, d4, d5);
				tessellator.setColorOpaque_F(colorRedBottomLeft_TopFace, colorGreenBottomLeft_TopFace, colorBlueBottomLeft_TopFace);
				tessellator.addVertexWithUV(d8, d9, d10, d4, d5);
				tessellator.setColorOpaque_F(colorRedBottomRight_TopFace, colorGreenBottomRight_TopFace, colorBlueBottomRight_TopFace);
				tessellator.addVertexWithUV(d7, d9, d10, d3, d5);
				tessellator.setColorOpaque_F(colorRedTopRight_TopFace, colorGreenTopRight_TopFace, colorBlueTopRight_TopFace);
				tessellator.addVertexWithUV(d7, d9, d11, d3, d6);			
			} else	
			if(iDir == 14){
				// Pointing North-West
				tessellator.setColorOpaque_F(colorRedTopLeft_TopFace, colorGreenTopLeft_TopFace, colorBlueTopLeft_TopFace);
				tessellator.addVertexWithUV(d8, d9, d11, d4, d6);
				tessellator.setColorOpaque_F(colorRedBottomRight_TopFace, colorGreenBottomRight_TopFace, colorBlueBottomRight_TopFace);
				tessellator.addVertexWithUV(d7, d9, d10, d3, d5);
				tessellator.setColorOpaque_F(colorRedBottomRight_TopFace, colorGreenBottomRight_TopFace, colorBlueBottomRight_TopFace);
				tessellator.addVertexWithUV(d7, d9, d10, d3, d5);
				tessellator.setColorOpaque_F(colorRedTopRight_TopFace, colorGreenTopRight_TopFace, colorBlueTopRight_TopFace);
				tessellator.addVertexWithUV(d7, d9, d11, d3, d6);	
			} else		
			if(iDir == 15){
				// Pointing South-East
				tessellator.setColorOpaque_F(colorRedTopLeft_TopFace, colorGreenTopLeft_TopFace, colorBlueTopLeft_TopFace);
				tessellator.addVertexWithUV(d8, d9, d11, d4, d6);
				tessellator.setColorOpaque_F(colorRedBottomLeft_TopFace, colorGreenBottomLeft_TopFace, colorBlueBottomLeft_TopFace);
				tessellator.addVertexWithUV(d8, d9, d10, d4, d5);
				tessellator.setColorOpaque_F(colorRedBottomRight_TopFace, colorGreenBottomRight_TopFace, colorBlueBottomRight_TopFace);
				tessellator.addVertexWithUV(d7, d9, d10, d3, d5);
				tessellator.setColorOpaque_F(colorRedTopLeft_TopFace, colorGreenTopLeft_TopFace, colorBlueTopLeft_TopFace);
				tessellator.addVertexWithUV(d8, d9, d11, d4, d6);	
			}
		} else
		{
			if(iDir == 8 || iDir == 9 || iDir == 10 || iDir == 11){
				// Standard
				tessellator.addVertexWithUV(d8, d9, d11, d4, d6);
				tessellator.addVertexWithUV(d8, d9, d10, d4, d5);
				tessellator.addVertexWithUV(d7, d9, d10, d3, d5);
				tessellator.addVertexWithUV(d7, d9, d11, d3, d6);
			} else
			if(iDir == 12){
				// Pointing South-West
				tessellator.addVertexWithUV(d8, d9, d11, d4, d6);
				tessellator.addVertexWithUV(d8, d9, d10, d4, d5);
				tessellator.addVertexWithUV(d7, d9, d11, d3, d6);
				tessellator.addVertexWithUV(d7, d9, d11, d3, d6);
			} else	
			if(iDir == 13){
				// Pointing North-East
				tessellator.addVertexWithUV(d8, d9, d10, d4, d5);
				tessellator.addVertexWithUV(d8, d9, d10, d4, d5);
				tessellator.addVertexWithUV(d7, d9, d10, d3, d5);
				tessellator.addVertexWithUV(d7, d9, d11, d3, d6);			
			} else	
			if(iDir == 14){
				// Pointing North-West
				tessellator.addVertexWithUV(d8, d9, d11, d4, d6);
				tessellator.addVertexWithUV(d7, d9, d10, d3, d5);
				tessellator.addVertexWithUV(d7, d9, d10, d3, d5);
				tessellator.addVertexWithUV(d7, d9, d11, d3, d6);	
			} else		
			if(iDir == 15){
				// Pointing South-East
				tessellator.addVertexWithUV(d8, d9, d11, d4, d6);
				tessellator.addVertexWithUV(d8, d9, d10, d4, d5);
				tessellator.addVertexWithUV(d7, d9, d10, d3, d5);
				tessellator.addVertexWithUV(d8, d9, d11, d4, d6);	
			}		
		}
    }*/
    
}
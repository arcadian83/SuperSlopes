package net.arcadian83.common;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.EntityRenderer;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;

/**
 * This class exists to override RenderBlocks's methods, because they detect
 * custom textures and prevent grass coloring on them
 */

public class RenderColoredGrass {
	
	// unused for now, not doing 3d inventory item yet
	/*public static boolean renderInventoryBlock(Block block, int x, int y, int z, RenderBlocks renderer) {
		// pulled from Block.colorMultiplier() so we don't have to check the non-existent biome
		int var5 = 16777215;
		float var6 = (float)(var5 >> 16 & 255) / 255.0F;
        float var7 = (float)(var5 >> 8 & 255) / 255.0F;
        float var8 = (float)(var5 & 255) / 255.0F;
        
        return RenderColoredGrass.renderStandardBlockWithColorMultiplier(block, x, y, z, var6, var7, var8, renderer);
	}*/
	
	// from RenderBlocks.renderStandardBlock()
    public static boolean renderStandardBlock(Block block, int x, int y, int z, RenderBlocks renderer) {
    	
    	int var5 = block.colorMultiplier(renderer.blockAccess, x, y, z);
        float var6 = (float)(var5 >> 16 & 255) / 255.0F;
        float var7 = (float)(var5 >> 8 & 255) / 255.0F;
        float var8 = (float)(var5 & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float var9 = (var6 * 30.0F + var7 * 59.0F + var8 * 11.0F) / 100.0F;
            float var10 = (var6 * 30.0F + var7 * 70.0F) / 100.0F;
            float var11 = (var6 * 30.0F + var8 * 70.0F) / 100.0F;
            var6 = var9;
            var7 = var10;
            var8 = var11;
        }

        //return Minecraft.isAmbientOcclusionEnabled() && Block.lightValue[block.blockID] == 0 ? this.renderStandardBlockWithAmbientOcclusion(block, x, y, z, var6, var7, var8) : this.renderStandardBlockWithColorMultiplier(block, x, y, z, var6, var7, var8);
        if(Minecraft.isAmbientOcclusionEnabled() && Block.lightValue[block.blockID] == 0) {
        	return RenderColoredGrass.renderStandardBlockWithColorMultiplier(block, x, y, z, var6, var7, var8, renderer);
        }
        else {
        	return RenderColoredGrass.renderStandardBlockWithAmbientOcclusion(block, x, y, z, var6, var7, var8, renderer);
        }
    }
    // from RenderBlocks.renderStandardBlockWithAmbientOcclusion()
    public static boolean renderStandardBlockWithAmbientOcclusion(Block block, int x, int y, int z, float par5, float par6, float par7, RenderBlocks renderer) {
    	renderer.enableAO = true;
        boolean var8 = false;
        float var9 = renderer.lightValueOwn;
        float var10 = renderer.lightValueOwn;
        float var11 = renderer.lightValueOwn;
        float var12 = renderer.lightValueOwn;
        boolean var13 = true;
        boolean var14 = true;
        boolean var15 = true;
        boolean var16 = true;
        boolean var17 = true;
        boolean var18 = true;
        renderer.lightValueOwn = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z);
        renderer.aoLightValueXNeg = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y, z);
        renderer.aoLightValueYNeg = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y - 1, z);
        renderer.aoLightValueZNeg = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z - 1);
        renderer.aoLightValueXPos = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y, z);
        renderer.aoLightValueYPos = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y + 1, z);
        renderer.aoLightValueZPos = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z + 1);
        int var19 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
        int var20 = var19;
        int var21 = var19;
        int var22 = var19;
        int var23 = var19;
        int var24 = var19;
        int var25 = var19;

        if (renderer.field_83027_i <= 0.0D)
        {
            var21 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
        }

        if (renderer.field_83024_j >= 1.0D)
        {
            var24 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
        }

        if (renderer.field_83021_g <= 0.0D)
        {
            var20 = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
        }

        if (renderer.field_83026_h >= 1.0D)
        {
            var23 = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
        }

        if (renderer.field_83025_k <= 0.0D)
        {
            var22 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
        }

        if (renderer.field_83022_l >= 1.0D)
        {
            var25 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
        }

        Tessellator var26 = Tessellator.instance;
        var26.setBrightness(983055);
        renderer.aoGrassXYZPPC = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1, y + 1, z)];
        renderer.aoGrassXYZPNC = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1, y - 1, z)];
        renderer.aoGrassXYZPCP = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1, y, z + 1)];
        renderer.aoGrassXYZPCN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1, y, z - 1)];
        renderer.aoGrassXYZNPC = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1, y + 1, z)];
        renderer.aoGrassXYZNNC = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1, y - 1, z)];
        renderer.aoGrassXYZNCN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1, y, z - 1)];
        renderer.aoGrassXYZNCP = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1, y, z + 1)];
        renderer.aoGrassXYZCPP = Block.canBlockGrass[renderer.blockAccess.getBlockId(x, y + 1, z + 1)];
        renderer.aoGrassXYZCPN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x, y + 1, z - 1)];
        renderer.aoGrassXYZCNP = Block.canBlockGrass[renderer.blockAccess.getBlockId(x, y - 1, z + 1)];
        renderer.aoGrassXYZCNN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x, y - 1, z - 1)];

       
        var18 = false;
        var17 = false;
        var16 = false;
        var15 = false;
        var13 = false;
        

        if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y - 1, z, 0))
        {
            if (renderer.aoType > 0)
            {
                if (renderer.field_83027_i <= 0.0D)
                {
                    --y;
                }

                renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
                renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
                renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
                renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
                renderer.aoLightValueScratchXYNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y, z);
                renderer.aoLightValueScratchYZNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z - 1);
                renderer.aoLightValueScratchYZNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z + 1);
                renderer.aoLightValueScratchXYPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y, z);

                if (!renderer.aoGrassXYZCNN && !renderer.aoGrassXYZNNC)
                {
                    renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXYNN;
                    renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXYNN;
                }
                else
                {
                    renderer.aoLightValueScratchXYZNNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y, z - 1);
                    renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
                }

                if (!renderer.aoGrassXYZCNP && !renderer.aoGrassXYZNNC)
                {
                    renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXYNN;
                    renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXYNN;
                }
                else
                {
                    renderer.aoLightValueScratchXYZNNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y, z + 1);
                    renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
                }

                if (!renderer.aoGrassXYZCNN && !renderer.aoGrassXYZPNC)
                {
                    renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXYPN;
                    renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXYPN;
                }
                else
                {
                    renderer.aoLightValueScratchXYZPNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y, z - 1);
                    renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
                }

                if (!renderer.aoGrassXYZCNP && !renderer.aoGrassXYZPNC)
                {
                    renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXYPN;
                    renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXYPN;
                }
                else
                {
                    renderer.aoLightValueScratchXYZPNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y, z + 1);
                    renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
                }

                if (renderer.field_83027_i <= 0.0D)
                {
                    ++y;
                }

                var9 = (renderer.aoLightValueScratchXYZNNP + renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchYZNP + renderer.aoLightValueYNeg) / 4.0F;
                var12 = (renderer.aoLightValueScratchYZNP + renderer.aoLightValueYNeg + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXYPN) / 4.0F;
                var11 = (renderer.aoLightValueYNeg + renderer.aoLightValueScratchYZNN + renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNN) / 4.0F;
                var10 = (renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXYZNNN + renderer.aoLightValueYNeg + renderer.aoLightValueScratchYZNN) / 4.0F;
                renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXYNN, renderer.aoBrightnessYZNP, var21);
                renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXYPN, var21);
                renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNN, renderer.aoBrightnessXYPN, renderer.aoBrightnessXYZPNN, var21);
                renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNN, renderer.aoBrightnessYZNN, var21);
            }
            else
            {
                var12 = renderer.aoLightValueYNeg;
                var11 = renderer.aoLightValueYNeg;
                var10 = renderer.aoLightValueYNeg;
                var9 = renderer.aoLightValueYNeg;
                renderer.brightnessTopLeft = renderer.brightnessBottomLeft = renderer.brightnessBottomRight = renderer.brightnessTopRight = renderer.aoBrightnessXYNN;
            }

            renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = (var13 ? par5 : 1.0F) * 0.5F;
            renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = (var13 ? par6 : 1.0F) * 0.5F;
            renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = (var13 ? par7 : 1.0F) * 0.5F;
            renderer.colorRedTopLeft *= var9;
            renderer.colorGreenTopLeft *= var9;
            renderer.colorBlueTopLeft *= var9;
            renderer.colorRedBottomLeft *= var10;
            renderer.colorGreenBottomLeft *= var10;
            renderer.colorBlueBottomLeft *= var10;
            renderer.colorRedBottomRight *= var11;
            renderer.colorGreenBottomRight *= var11;
            renderer.colorBlueBottomRight *= var11;
            renderer.colorRedTopRight *= var12;
            renderer.colorGreenTopRight *= var12;
            renderer.colorBlueTopRight *= var12;
            renderer.renderBottomFace(block, (double)x, (double)y, (double)z, block.getBlockTexture(renderer.blockAccess, x, y, z, 0));
            var8 = true;
        }

        if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y + 1, z, 1))
        {
            if (renderer.aoType > 0)
            {
                if (renderer.field_83024_j >= 1.0D)
                {
                    ++y;
                }

                renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
                renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
                renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
                renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
                renderer.aoLightValueScratchXYNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y, z);
                renderer.aoLightValueScratchXYPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y, z);
                renderer.aoLightValueScratchYZPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z - 1);
                renderer.aoLightValueScratchYZPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z + 1);

                if (!renderer.aoGrassXYZCPN && !renderer.aoGrassXYZNPC)
                {
                    renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXYNP;
                    renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXYNP;
                }
                else
                {
                    renderer.aoLightValueScratchXYZNPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y, z - 1);
                    renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
                }

                if (!renderer.aoGrassXYZCPN && !renderer.aoGrassXYZPPC)
                {
                    renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXYPP;
                    renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXYPP;
                }
                else
                {
                    renderer.aoLightValueScratchXYZPPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y, z - 1);
                    renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
                }

                if (!renderer.aoGrassXYZCPP && !renderer.aoGrassXYZNPC)
                {
                    renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXYNP;
                    renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXYNP;
                }
                else
                {
                    renderer.aoLightValueScratchXYZNPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y, z + 1);
                    renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
                }

                if (!renderer.aoGrassXYZCPP && !renderer.aoGrassXYZPPC)
                {
                    renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXYPP;
                    renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXYPP;
                }
                else
                {
                    renderer.aoLightValueScratchXYZPPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y, z + 1);
                    renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
                }

                if (renderer.field_83024_j >= 1.0D)
                {
                    --y;
                }

                var12 = (renderer.aoLightValueScratchXYZNPP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchYZPP + renderer.aoLightValueYPos) / 4.0F;
                var9 = (renderer.aoLightValueScratchYZPP + renderer.aoLightValueYPos + renderer.aoLightValueScratchXYZPPP + renderer.aoLightValueScratchXYPP) / 4.0F;
                var10 = (renderer.aoLightValueYPos + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPN) / 4.0F;
                var11 = (renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPN + renderer.aoLightValueYPos + renderer.aoLightValueScratchYZPN) / 4.0F;
                renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNPP, renderer.aoBrightnessXYNP, renderer.aoBrightnessYZPP, var24);
                renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXYZPPP, renderer.aoBrightnessXYPP, var24);
                renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPN, var24);
                renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, var24);
            }
            else
            {
                var12 = renderer.aoLightValueYPos;
                var11 = renderer.aoLightValueYPos;
                var10 = renderer.aoLightValueYPos;
                var9 = renderer.aoLightValueYPos;
                renderer.brightnessTopLeft = renderer.brightnessBottomLeft = renderer.brightnessBottomRight = renderer.brightnessTopRight = var24;
            }

            renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = var14 ? par5 : 1.0F;
            renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = var14 ? par6 : 1.0F;
            renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = var14 ? par7 : 1.0F;
            renderer.colorRedTopLeft *= var9;
            renderer.colorGreenTopLeft *= var9;
            renderer.colorBlueTopLeft *= var9;
            renderer.colorRedBottomLeft *= var10;
            renderer.colorGreenBottomLeft *= var10;
            renderer.colorBlueBottomLeft *= var10;
            renderer.colorRedBottomRight *= var11;
            renderer.colorGreenBottomRight *= var11;
            renderer.colorBlueBottomRight *= var11;
            renderer.colorRedTopRight *= var12;
            renderer.colorGreenTopRight *= var12;
            renderer.colorBlueTopRight *= var12;
            renderer.renderTopFace(block, (double)x, (double)y, (double)z, block.getBlockTexture(renderer.blockAccess, x, y, z, 1));
            var8 = true;
        }

        int var27;

        if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y, z - 1, 2))
        {
        	
        	if (renderer.aoType > 0)
            {
                if (renderer.field_83025_k <= 0.0D)
                {
                    --z;
                }

                renderer.aoLightValueScratchXZNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y, z);
                renderer.aoLightValueScratchYZNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y - 1, z);
                renderer.aoLightValueScratchYZPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y + 1, z);
                renderer.aoLightValueScratchXZPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y, z);
                renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
                renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
                renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
                renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);

                if (!renderer.aoGrassXYZNCN && !renderer.aoGrassXYZCNN)
                {
                    renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
                    renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
                }
                else
                {
                    renderer.aoLightValueScratchXYZNNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y - 1, z);
                    renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
                }

                if (!renderer.aoGrassXYZNCN && !renderer.aoGrassXYZCPN)
                {
                    renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
                    renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
                }
                else
                {
                    renderer.aoLightValueScratchXYZNPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y + 1, z);
                    renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
                }

                if (!renderer.aoGrassXYZPCN && !renderer.aoGrassXYZCNN)
                {
                    renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
                    renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
                }
                else
                {
                    renderer.aoLightValueScratchXYZPNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y - 1, z);
                    renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
                }

                if (!renderer.aoGrassXYZPCN && !renderer.aoGrassXYZCPN)
                {
                    renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
                    renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
                }
                else
                {
                    renderer.aoLightValueScratchXYZPPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y + 1, z);
                    renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
                }

                if (renderer.field_83025_k <= 0.0D)
                {
                    ++z;
                }

                var9 = (renderer.aoLightValueScratchXZNN + renderer.aoLightValueScratchXYZNPN + renderer.aoLightValueZNeg + renderer.aoLightValueScratchYZPN) / 4.0F;
                var10 = (renderer.aoLightValueZNeg + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXZPN + renderer.aoLightValueScratchXYZPPN) / 4.0F;
                var11 = (renderer.aoLightValueScratchYZNN + renderer.aoLightValueZNeg + renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXZPN) / 4.0F;
                var12 = (renderer.aoLightValueScratchXYZNNN + renderer.aoLightValueScratchXZNN + renderer.aoLightValueScratchYZNN + renderer.aoLightValueZNeg) / 4.0F;
                renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, var22);
                renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN, var22);
                renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNN, renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXZPN, var22);
                renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXZNN, renderer.aoBrightnessYZNN, var22);
            }
            else
            {
                var12 = renderer.aoLightValueZNeg;
                var11 = renderer.aoLightValueZNeg;
                var10 = renderer.aoLightValueZNeg;
                var9 = renderer.aoLightValueZNeg;
                renderer.brightnessTopLeft = renderer.brightnessBottomLeft = renderer.brightnessBottomRight = renderer.brightnessTopRight = var22;
            }

            renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = (var15 ? par5 : 1.0F) * 0.8F;
            renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = (var15 ? par6 : 1.0F) * 0.8F;
            renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = (var15 ? par7 : 1.0F) * 0.8F;
            renderer.colorRedTopLeft *= var9;
            renderer.colorGreenTopLeft *= var9;
            renderer.colorBlueTopLeft *= var9;
            renderer.colorRedBottomLeft *= var10;
            renderer.colorGreenBottomLeft *= var10;
            renderer.colorBlueBottomLeft *= var10;
            renderer.colorRedBottomRight *= var11;
            renderer.colorGreenBottomRight *= var11;
            renderer.colorBlueBottomRight *= var11;
            renderer.colorRedTopRight *= var12;
            renderer.colorGreenTopRight *= var12;
            renderer.colorBlueTopRight *= var12;
			var27 = block.getBlockTexture(renderer.blockAccess, x, y, z, 2);
			renderer.renderEastFace(block, (double)x, (double)y, (double)z, var27);

            //if (Tessellator.instance.defaultTexture && renderer.fancyGrass && var27 == 3 && renderer.overrideBlockTexture < 0)
            //{
                renderer.colorRedTopLeft *= par5;
                renderer.colorRedBottomLeft *= par5;
                renderer.colorRedBottomRight *= par5;
                renderer.colorRedTopRight *= par5;
                renderer.colorGreenTopLeft *= par6;
                renderer.colorGreenBottomLeft *= par6;
                renderer.colorGreenBottomRight *= par6;
                renderer.colorGreenTopRight *= par6;
                renderer.colorBlueTopLeft *= par7;
                renderer.colorBlueBottomLeft *= par7;
                renderer.colorBlueBottomRight *= par7;
                renderer.colorBlueTopRight *= par7;
                renderer.renderEastFace(block, (double)x, (double)y, (double)z, 38);
            //}

            var8 = true;
        }

        if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y, z + 1, 3))
        {
            if (renderer.aoType > 0)
            {
                if (renderer.field_83022_l >= 1.0D)
                {
                    ++z;
                }

                renderer.aoLightValueScratchXZNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y, z);
                renderer.aoLightValueScratchXZPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y, z);
                renderer.aoLightValueScratchYZNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y - 1, z);
                renderer.aoLightValueScratchYZPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y + 1, z);
                renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
                renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
                renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
                renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);

                if (!renderer.aoGrassXYZNCP && !renderer.aoGrassXYZCNP)
                {
                    renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
                    renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
                }
                else
                {
                    renderer.aoLightValueScratchXYZNNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y - 1, z);
                    renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
                }

                if (!renderer.aoGrassXYZNCP && !renderer.aoGrassXYZCPP)
                {
                    renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
                    renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
                }
                else
                {
                    renderer.aoLightValueScratchXYZNPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y + 1, z);
                    renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
                }

                if (!renderer.aoGrassXYZPCP && !renderer.aoGrassXYZCNP)
                {
                    renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
                    renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
                }
                else
                {
                    renderer.aoLightValueScratchXYZPNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y - 1, z);
                    renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
                }

                if (!renderer.aoGrassXYZPCP && !renderer.aoGrassXYZCPP)
                {
                    renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
                    renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
                }
                else
                {
                    renderer.aoLightValueScratchXYZPPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y + 1, z);
                    renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
                }

                if (renderer.field_83022_l >= 1.0D)
                {
                    --z;
                }

                var9 = (renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchXYZNPP + renderer.aoLightValueZPos + renderer.aoLightValueScratchYZPP) / 4.0F;
                var12 = (renderer.aoLightValueZPos + renderer.aoLightValueScratchYZPP + renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
                var11 = (renderer.aoLightValueScratchYZNP + renderer.aoLightValueZPos + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXZPP) / 4.0F;
                var10 = (renderer.aoLightValueScratchXYZNNP + renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchYZNP + renderer.aoLightValueZPos) / 4.0F;
                renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNP, renderer.aoBrightnessXYZNPP, renderer.aoBrightnessYZPP, var25);
                renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXZPP, renderer.aoBrightnessXYZPPP, var25);
                renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXZPP, var25);
                renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP, renderer.aoBrightnessYZNP, var25);
            }
            else
            {
                var12 = renderer.aoLightValueZPos;
                var11 = renderer.aoLightValueZPos;
                var10 = renderer.aoLightValueZPos;
                var9 = renderer.aoLightValueZPos;
                renderer.brightnessTopLeft = renderer.brightnessBottomLeft = renderer.brightnessBottomRight = renderer.brightnessTopRight = var25;
            }

            renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = (var16 ? par5 : 1.0F) * 0.8F;
            renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = (var16 ? par6 : 1.0F) * 0.8F;
            renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = (var16 ? par7 : 1.0F) * 0.8F;
            renderer.colorRedTopLeft *= var9;
            renderer.colorGreenTopLeft *= var9;
            renderer.colorBlueTopLeft *= var9;
            renderer.colorRedBottomLeft *= var10;
            renderer.colorGreenBottomLeft *= var10;
            renderer.colorBlueBottomLeft *= var10;
            renderer.colorRedBottomRight *= var11;
            renderer.colorGreenBottomRight *= var11;
            renderer.colorBlueBottomRight *= var11;
            renderer.colorRedTopRight *= var12;
            renderer.colorGreenTopRight *= var12;
            renderer.colorBlueTopRight *= var12;
            var27 = block.getBlockTexture(renderer.blockAccess, x, y, z, 3);
            renderer.renderWestFace(block, (double)x, (double)y, (double)z, block.getBlockTexture(renderer.blockAccess, x, y, z, 3));

            //if (Tessellator.instance.defaultTexture && renderer.fancyGrass && var27 == 3 && renderer.overrideBlockTexture < 0)
            //{
                renderer.colorRedTopLeft *= par5;
                renderer.colorRedBottomLeft *= par5;
                renderer.colorRedBottomRight *= par5;
                renderer.colorRedTopRight *= par5;
                renderer.colorGreenTopLeft *= par6;
                renderer.colorGreenBottomLeft *= par6;
                renderer.colorGreenBottomRight *= par6;
                renderer.colorGreenTopRight *= par6;
                renderer.colorBlueTopLeft *= par7;
                renderer.colorBlueBottomLeft *= par7;
                renderer.colorBlueBottomRight *= par7;
                renderer.colorBlueTopRight *= par7;
                renderer.renderWestFace(block, (double)x, (double)y, (double)z, 38);
            //}

            var8 = true;
        }

        if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x - 1, y, z, 4))
        {
            if (renderer.aoType > 0)
            {
                if (renderer.field_83021_g <= 0.0D)
                {
                    --x;
                }

                renderer.aoLightValueScratchXYNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y - 1, z);
                renderer.aoLightValueScratchXZNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z - 1);
                renderer.aoLightValueScratchXZNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z + 1);
                renderer.aoLightValueScratchXYNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y + 1, z);
                renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
                renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
                renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
                renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);

                if (!renderer.aoGrassXYZNCN && !renderer.aoGrassXYZNNC)
                {
                    renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
                    renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
                }
                else
                {
                    renderer.aoLightValueScratchXYZNNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y - 1, z - 1);
                    renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
                }

                if (!renderer.aoGrassXYZNCP && !renderer.aoGrassXYZNNC)
                {
                    renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
                    renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
                }
                else
                {
                    renderer.aoLightValueScratchXYZNNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y - 1, z + 1);
                    renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
                }

                if (!renderer.aoGrassXYZNCN && !renderer.aoGrassXYZNPC)
                {
                    renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
                    renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
                }
                else
                {
                    renderer.aoLightValueScratchXYZNPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y + 1, z - 1);
                    renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
                }

                if (!renderer.aoGrassXYZNCP && !renderer.aoGrassXYZNPC)
                {
                    renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
                    renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
                }
                else
                {
                    renderer.aoLightValueScratchXYZNPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y + 1, z + 1);
                    renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
                }

                if (renderer.field_83021_g <= 0.0D)
                {
                    ++x;
                }

                var12 = (renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXYZNNP + renderer.aoLightValueXNeg + renderer.aoLightValueScratchXZNP) / 4.0F;
                var9 = (renderer.aoLightValueXNeg + renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPP) / 4.0F;
                var10 = (renderer.aoLightValueScratchXZNN + renderer.aoLightValueXNeg + renderer.aoLightValueScratchXYZNPN + renderer.aoLightValueScratchXYNP) / 4.0F;
                var11 = (renderer.aoLightValueScratchXYZNNN + renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXZNN + renderer.aoLightValueXNeg) / 4.0F;
                renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP, var20);
                renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNP, renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPP, var20);
                renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessXYNP, var20);
                renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXYNN, renderer.aoBrightnessXZNN, var20);
            }
            else
            {
                var12 = renderer.aoLightValueXNeg;
                var11 = renderer.aoLightValueXNeg;
                var10 = renderer.aoLightValueXNeg;
                var9 = renderer.aoLightValueXNeg;
                renderer.brightnessTopLeft = renderer.brightnessBottomLeft = renderer.brightnessBottomRight = renderer.brightnessTopRight = var20;
            }

            renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = (var17 ? par5 : 1.0F) * 0.6F;
            renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = (var17 ? par6 : 1.0F) * 0.6F;
            renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = (var17 ? par7 : 1.0F) * 0.6F;
            renderer.colorRedTopLeft *= var9;
            renderer.colorGreenTopLeft *= var9;
            renderer.colorBlueTopLeft *= var9;
            renderer.colorRedBottomLeft *= var10;
            renderer.colorGreenBottomLeft *= var10;
            renderer.colorBlueBottomLeft *= var10;
            renderer.colorRedBottomRight *= var11;
            renderer.colorGreenBottomRight *= var11;
            renderer.colorBlueBottomRight *= var11;
            renderer.colorRedTopRight *= var12;
            renderer.colorGreenTopRight *= var12;
            renderer.colorBlueTopRight *= var12;
            var27 = block.getBlockTexture(renderer.blockAccess, x, y, z, 4);
            renderer.renderNorthFace(block, (double)x, (double)y, (double)z, var27);

            //if (Tessellator.instance.defaultTexture && renderer.fancyGrass && var27 == 3 && renderer.overrideBlockTexture < 0)
            //{
                renderer.colorRedTopLeft *= par5;
                renderer.colorRedBottomLeft *= par5;
                renderer.colorRedBottomRight *= par5;
                renderer.colorRedTopRight *= par5;
                renderer.colorGreenTopLeft *= par6;
                renderer.colorGreenBottomLeft *= par6;
                renderer.colorGreenBottomRight *= par6;
                renderer.colorGreenTopRight *= par6;
                renderer.colorBlueTopLeft *= par7;
                renderer.colorBlueBottomLeft *= par7;
                renderer.colorBlueBottomRight *= par7;
                renderer.colorBlueTopRight *= par7;
                renderer.renderNorthFace(block, (double)x, (double)y, (double)z, 38);
            //}

            var8 = true;
        }

        if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x + 1, y, z, 5))
        {
            if (renderer.aoType > 0)
            {
                if (renderer.field_83026_h >= 1.0D)
                {
                    ++x;
                }

                renderer.aoLightValueScratchXYPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y - 1, z);
                renderer.aoLightValueScratchXZPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z - 1);
                renderer.aoLightValueScratchXZPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z + 1);
                renderer.aoLightValueScratchXYPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y + 1, z);
                renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
                renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
                renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
                renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);

                if (!renderer.aoGrassXYZPNC && !renderer.aoGrassXYZPCN)
                {
                    renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
                    renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
                }
                else
                {
                    renderer.aoLightValueScratchXYZPNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y - 1, z - 1);
                    renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
                }

                if (!renderer.aoGrassXYZPNC && !renderer.aoGrassXYZPCP)
                {
                    renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
                    renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
                }
                else
                {
                    renderer.aoLightValueScratchXYZPNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y - 1, z + 1);
                    renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
                }

                if (!renderer.aoGrassXYZPPC && !renderer.aoGrassXYZPCN)
                {
                    renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
                    renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
                }
                else
                {
                    renderer.aoLightValueScratchXYZPPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y + 1, z - 1);
                    renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
                }

                if (!renderer.aoGrassXYZPPC && !renderer.aoGrassXYZPCP)
                {
                    renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
                    renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
                }
                else
                {
                    renderer.aoLightValueScratchXYZPPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y + 1, z + 1);
                    renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
                }

                if (renderer.field_83026_h >= 1.0D)
                {
                    --x;
                }

                var9 = (renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueXPos + renderer.aoLightValueScratchXZPP) / 4.0F;
                var12 = (renderer.aoLightValueXPos + renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
                var11 = (renderer.aoLightValueScratchXZPN + renderer.aoLightValueXPos + renderer.aoLightValueScratchXYZPPN + renderer.aoLightValueScratchXYPP) / 4.0F;
                var10 = (renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXZPN + renderer.aoLightValueXPos) / 4.0F;
                renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXYPN, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXZPP, var23);
                renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXZPP, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPP, var23);
                renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN, renderer.aoBrightnessXYPP, var23);
                renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXYPN, renderer.aoBrightnessXZPN, var23);
            }
            else
            {
                var12 = renderer.aoLightValueXPos;
                var11 = renderer.aoLightValueXPos;
                var10 = renderer.aoLightValueXPos;
                var9 = renderer.aoLightValueXPos;
                renderer.brightnessTopLeft = renderer.brightnessBottomLeft = renderer.brightnessBottomRight = renderer.brightnessTopRight = var23;
            }

            renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = (var18 ? par5 : 1.0F) * 0.6F;
            renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = (var18 ? par6 : 1.0F) * 0.6F;
            renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = (var18 ? par7 : 1.0F) * 0.6F;
            renderer.colorRedTopLeft *= var9;
            renderer.colorGreenTopLeft *= var9;
            renderer.colorBlueTopLeft *= var9;
            renderer.colorRedBottomLeft *= var10;
            renderer.colorGreenBottomLeft *= var10;
            renderer.colorBlueBottomLeft *= var10;
            renderer.colorRedBottomRight *= var11;
            renderer.colorGreenBottomRight *= var11;
            renderer.colorBlueBottomRight *= var11;
            renderer.colorRedTopRight *= var12;
            renderer.colorGreenTopRight *= var12;
            renderer.colorBlueTopRight *= var12;
            var27 = block.getBlockTexture(renderer.blockAccess, x, y, z, 5);
            renderer.renderSouthFace(block, (double)x, (double)y, (double)z, var27);

            //if (Tessellator.instance.defaultTexture && renderer.fancyGrass && var27 == 3 && renderer.overrideBlockTexture < 0)
            //{
                renderer.colorRedTopLeft *= par5;
                renderer.colorRedBottomLeft *= par5;
                renderer.colorRedBottomRight *= par5;
                renderer.colorRedTopRight *= par5;
                renderer.colorGreenTopLeft *= par6;
                renderer.colorGreenBottomLeft *= par6;
                renderer.colorGreenBottomRight *= par6;
                renderer.colorGreenTopRight *= par6;
                renderer.colorBlueTopLeft *= par7;
                renderer.colorBlueBottomLeft *= par7;
                renderer.colorBlueBottomRight *= par7;
                renderer.colorBlueTopRight *= par7;
                renderer.renderSouthFace(block, (double)x, (double)y, (double)z, 38);
            //}

            var8 = true;
        }

        renderer.enableAO = false;
        return var8;
    }
    
    // from RenderBlocks.renderStandardBlockWithColorMultiplier()
    /**
     * Renders a standard cube block at the given coordinates, with a given color ratio.  Args: block, x, y, z, r, g, b
     */
    public static boolean renderStandardBlockWithColorMultiplier(Block par1Block, int par2, int par3, int par4, float par5, float par6, float par7, RenderBlocks renderer)
    {
        renderer.enableAO = false;
        Tessellator var8 = Tessellator.instance;
        boolean var9 = false;
        float var10 = 0.5F;
        float var11 = 1.0F;
        float var12 = 0.8F;
        float var13 = 0.6F;
        float var14 = var11 * par5;
        float var15 = var11 * par6;
        float var16 = var11 * par7;
        float var17 = var10;
        float var18 = var12;
        float var19 = var13;
        float var20 = var10;
        float var21 = var12;
        float var22 = var13;
        float var23 = var10;
        float var24 = var12;
        float var25 = var13;

        /*if (par1Block != Block.grass)
        {
            var17 = var10 * par5;
            var18 = var12 * par5;
            var19 = var13 * par5;
            var20 = var10 * par6;
            var21 = var12 * par6;
            var22 = var13 * par6;
            var23 = var10 * par7;
            var24 = var12 * par7;
            var25 = var13 * par7;
        }*/

        int var26 = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4);

        if (renderer.renderAllFaces || par1Block.shouldSideBeRendered(renderer.blockAccess, par2, par3 - 1, par4, 0))
        {
            var8.setBrightness(renderer.field_83027_i > 0.0D ? var26 : par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 - 1, par4));
            var8.setColorOpaque_F(var17, var20, var23);
            renderer.renderBottomFace(par1Block, (double)par2, (double)par3, (double)par4, par1Block.getBlockTexture(renderer.blockAccess, par2, par3, par4, 0));
            var9 = true;
        }

        if (renderer.renderAllFaces || par1Block.shouldSideBeRendered(renderer.blockAccess, par2, par3 + 1, par4, 1))
        {
            var8.setBrightness(renderer.field_83024_j < 1.0D ? var26 : par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 + 1, par4));
            var8.setColorOpaque_F(var14, var15, var16);
            renderer.renderTopFace(par1Block, (double)par2, (double)par3, (double)par4, par1Block.getBlockTexture(renderer.blockAccess, par2, par3, par4, 1));
            var9 = true;
        }

        int var28;

        if (renderer.renderAllFaces || par1Block.shouldSideBeRendered(renderer.blockAccess, par2, par3, par4 - 1, 2))
        {
            var8.setBrightness(renderer.field_83025_k > 0.0D ? var26 : par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4 - 1));
            var8.setColorOpaque_F(var18, var21, var24);
            var28 = par1Block.getBlockTexture(renderer.blockAccess, par2, par3, par4, 2);
            renderer.renderEastFace(par1Block, (double)par2, (double)par3, (double)par4, var28);

            //if (Tessellator.instance.defaultTexture && renderer.fancyGrass && var28 == 3 && renderer.overrideBlockTexture < 0)
            //{
                var8.setColorOpaque_F(var18 * par5, var21 * par6, var24 * par7);
                renderer.renderEastFace(par1Block, (double)par2, (double)par3, (double)par4, 38);
            //}

            var9 = true;
        }

        if (renderer.renderAllFaces || par1Block.shouldSideBeRendered(renderer.blockAccess, par2, par3, par4 + 1, 3))
        {
            var8.setBrightness(renderer.field_83022_l < 1.0D ? var26 : par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4 + 1));
            var8.setColorOpaque_F(var18, var21, var24);
            var28 = par1Block.getBlockTexture(renderer.blockAccess, par2, par3, par4, 3);
            renderer.renderWestFace(par1Block, (double)par2, (double)par3, (double)par4, var28);

            //if (Tessellator.instance.defaultTexture && renderer.fancyGrass && var28 == 3 && renderer.overrideBlockTexture < 0)
            //{
                var8.setColorOpaque_F(var18 * par5, var21 * par6, var24 * par7);
                renderer.renderWestFace(par1Block, (double)par2, (double)par3, (double)par4, 38);
            //}

            var9 = true;
        }

        if (renderer.renderAllFaces || par1Block.shouldSideBeRendered(renderer.blockAccess, par2 - 1, par3, par4, 4))
        {
            var8.setBrightness(renderer.field_83021_g > 0.0D ? var26 : par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 - 1, par3, par4));
            var8.setColorOpaque_F(var19, var22, var25);
            var28 = par1Block.getBlockTexture(renderer.blockAccess, par2, par3, par4, 4);
            renderer.renderNorthFace(par1Block, (double)par2, (double)par3, (double)par4, var28);

            //if (Tessellator.instance.defaultTexture && renderer.fancyGrass && var28 == 3 && renderer.overrideBlockTexture < 0)
            //{
                var8.setColorOpaque_F(var19 * par5, var22 * par6, var25 * par7);
                renderer.renderNorthFace(par1Block, (double)par2, (double)par3, (double)par4, 38);
            //}

            var9 = true;
        }

        if (renderer.renderAllFaces || par1Block.shouldSideBeRendered(renderer.blockAccess, par2 + 1, par3, par4, 5))
        {
            var8.setBrightness(renderer.field_83026_h < 1.0D ? var26 : par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 + 1, par3, par4));
            var8.setColorOpaque_F(var19, var22, var25);
            var28 = par1Block.getBlockTexture(renderer.blockAccess, par2, par3, par4, 5);
            renderer.renderSouthFace(par1Block, (double)par2, (double)par3, (double)par4, var28);

            //if (Tessellator.instance.defaultTexture && renderer.fancyGrass && var28 == 3 && renderer.overrideBlockTexture < 0)
            //{
                var8.setColorOpaque_F(var19 * par5, var22 * par6, var25 * par7);
                renderer.renderSouthFace(par1Block, (double)par2, (double)par3, (double)par4, 38);
            //}

            var9 = true;
        }

        return var9;
    }
}

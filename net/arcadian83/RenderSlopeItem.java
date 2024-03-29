package net.arcadian83;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class RenderSlopeItem implements IItemRenderer {

	private void renderSlopeItem(RenderBlocks render, ItemStack item, float translateX, float translateY, float translateZ) {

		// GL11.glBindTexture(GL11.GL_TEXTURE_2D, 10);
		Tessellator tessellator = Tessellator.instance;

		//Block block = BuildCraftTransport.genericPipeBlock;
		//int textureID = ((ItemPipe) Item.itemsList[item.itemID]).getTextureIndex();
		//if (textureID > 255) {
		//	textureID -= 256;
		//}

		//block.setBlockBounds(Utils.pipeMinPos, 0.0F, Utils.pipeMinPos, Utils.pipeMaxPos, 1.0F, Utils.pipeMaxPos);
		//block.setBlockBoundsForItemRender();
		//render.setRenderBoundsFromBlock(block);

		GL11.glTranslatef(translateX, translateY, translateZ);
		
		/*tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		render.renderBottomFace(block, 0.0D, 0.0D, 0.0D, textureID);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		render.renderTopFace(block, 0.0D, 0.0D, 0.0D, textureID);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		render.renderEastFace(block, 0.0D, 0.0D, 0.0D, textureID);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		render.renderWestFace(block, 0.0D, 0.0D, 0.0D, textureID);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		render.renderNorthFace(block, 0.0D, 0.0D, 0.0D, textureID);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		render.renderSouthFace(block, 0.0D, 0.0D, 0.0D, textureID);
		tessellator.draw();*/
		
		RenderDirtSlope renderSlope = new RenderDirtSlope();
		
		renderSlope.renderInventoryBlock(item.getItemDamage(), render);
		
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		//block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	/** IItemRenderer implementation **/

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch (type) {
		case ENTITY:
			return true;
		case EQUIPPED:
			return true;
		case INVENTORY:
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch (type) {
		case ENTITY:
			renderSlopeItem((RenderBlocks) data[0], item, -0.5f, -0.5f, -0.5f);
			break;
		case EQUIPPED:
			//renderSlopeItem((RenderBlocks) data[0], item, -0.4f, 0.50f, 0.35f);
			renderSlopeItem((RenderBlocks) data[0], item, 0f, 1f, 0f);
			break;
		case INVENTORY:
			renderSlopeItem((RenderBlocks) data[0], item, -0.5f, -0.5f, -0.5f);
			break;
		default:
		}
	}
}

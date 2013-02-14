package net.arcadian83;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxyArc extends CommonProxyArc{

	public final static RenderSlopeItem slopeItemRenderer = new RenderSlopeItem();
	
	@Override
	public void registerRenderInformation() 
    {
		MinecraftForgeClient.preloadTexture("/Arc/terrain.png");
		
		RenderGrassSlab renderGrassSlab = new RenderGrassSlab();
		RenderingRegistry.registerBlockHandler(renderGrassSlab);
	    
		RenderDirtSlope renderDirtSlope = new RenderDirtSlope();
		RenderingRegistry.registerBlockHandler(renderDirtSlope);
		RenderDirtSlope2 renderDirtSlope2 = new RenderDirtSlope2();
		RenderingRegistry.registerBlockHandler(renderDirtSlope2);
		RenderDirtSlope3 renderDirtSlope3 = new RenderDirtSlope3();
		RenderingRegistry.registerBlockHandler(renderDirtSlope3);
		
		MinecraftForgeClient.registerItemRenderer(Arc.slopeItemId, slopeItemRenderer);
    }
}

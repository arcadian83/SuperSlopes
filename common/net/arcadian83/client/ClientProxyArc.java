package net.arcadian83.client;

import net.arcadian83.common.CommonProxyArc;
import net.arcadian83.common.RenderDirtSlope;
import net.arcadian83.common.RenderGrassSlab;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxyArc extends CommonProxyArc{

	@Override
	public void registerRenderInformation() 
    {
		MinecraftForgeClient.preloadTexture("/Arc/terrain.png");
		
		RenderGrassSlab renderGrassSlab = new RenderGrassSlab();
		RenderingRegistry.registerBlockHandler(renderGrassSlab);
	    
		RenderDirtSlope renderDirtSlope = new RenderDirtSlope();
		RenderingRegistry.registerBlockHandler(renderDirtSlope);
		
		// This variable renderId? this thing has got to be static somewhere. not necessarily public, but we need it available form everywhere. at the minimum we need public accessors. I personally have this variable as a public int in my common @mod class.
	    // now set in Arc.common.Arc
		// Arc.common.Arc.grassSlabRenderId = renderer.getRenderId();
    }
	
	
}

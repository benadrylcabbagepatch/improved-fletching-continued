package dontneg.improvedfletching;

import dontneg.improvedfletching.entity.ArrowEntityTypes;
import dontneg.improvedfletching.entity.BlazeArrowEntityRenderer;
import dontneg.improvedfletching.entity.CarrotArrowEntityRenderer;
import dontneg.improvedfletching.entity.HoneyArrowEntityRenderer;
import dontneg.improvedfletching.entity.QuartzArrowEntityRenderer;
import dontneg.improvedfletching.screen.FletchingScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@SuppressWarnings("unused")
public class ClientImprovedFletching implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HandledScreens.register(ImprovedFletching.FLETCHING_TABLE_SCREEN_HANDLER, FletchingScreen::new);
        EntityRendererRegistry.register(ArrowEntityTypes.ARROW_BLAZE, BlazeArrowEntityRenderer::new);
        EntityRendererRegistry.register(ArrowEntityTypes.ARROW_CARROT, CarrotArrowEntityRenderer::new);
        EntityRendererRegistry.register(ArrowEntityTypes.ARROW_HONEY, HoneyArrowEntityRenderer::new);
        EntityRendererRegistry.register(ArrowEntityTypes.ARROW_QUARTZ, QuartzArrowEntityRenderer::new);
    }
}

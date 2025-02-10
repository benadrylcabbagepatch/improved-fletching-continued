package benadrylcabbagepatch.improvedfletchingcontinued;

import benadrylcabbagepatch.improvedfletchingcontinued.entity.ArrowEntityTypes;
import benadrylcabbagepatch.improvedfletchingcontinued.entity.BlazeArrowEntityRenderer;
import benadrylcabbagepatch.improvedfletchingcontinued.entity.CarrotArrowEntityRenderer;
import benadrylcabbagepatch.improvedfletchingcontinued.entity.HoneyArrowEntityRenderer;
import benadrylcabbagepatch.improvedfletchingcontinued.entity.QuartzArrowEntityRenderer;
import benadrylcabbagepatch.improvedfletchingcontinued.screen.FletchingScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@SuppressWarnings("unused")
public class ClientImprovedFletching implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HandledScreens.register(ImprovedFletchingContinued.FLETCHING_TABLE_SCREEN_HANDLER, FletchingScreen::new);
        EntityRendererRegistry.register(ArrowEntityTypes.ARROW_BLAZE, BlazeArrowEntityRenderer::new);
        EntityRendererRegistry.register(ArrowEntityTypes.ARROW_CARROT, CarrotArrowEntityRenderer::new);
        EntityRendererRegistry.register(ArrowEntityTypes.ARROW_HONEY, HoneyArrowEntityRenderer::new);
        EntityRendererRegistry.register(ArrowEntityTypes.ARROW_QUARTZ, QuartzArrowEntityRenderer::new);
    }
}

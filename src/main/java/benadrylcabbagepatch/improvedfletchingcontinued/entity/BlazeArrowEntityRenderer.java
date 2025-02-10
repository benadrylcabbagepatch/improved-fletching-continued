package benadrylcabbagepatch.improvedfletchingcontinued.entity;

import benadrylcabbagepatch.improvedfletchingcontinued.ImprovedFletchingContinued;
import benadrylcabbagepatch.improvedfletchingcontinued.arrows.blaze.BlazeArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class BlazeArrowEntityRenderer extends ProjectileEntityRenderer<BlazeArrowEntity> {
    public static final Identifier TEXTURE = ImprovedFletchingContinued.id("textures/entity/projectiles/arrow_blaze.png");

    public BlazeArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(BlazeArrowEntity entity) {
        return TEXTURE;
    }
}

package benadrylcabbagepatch.improvedfletchingcontinued.entity;

import benadrylcabbagepatch.improvedfletchingcontinued.ImprovedFletchingContinued;
import benadrylcabbagepatch.improvedfletchingcontinued.arrows.quartz.QuartzArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class QuartzArrowEntityRenderer extends ProjectileEntityRenderer<QuartzArrowEntity> {
    public static final Identifier TEXTURE = ImprovedFletchingContinued.id("textures/entity/projectiles/arrow_quartz.png");

    public QuartzArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(QuartzArrowEntity entity) {
        return TEXTURE;
    }
}

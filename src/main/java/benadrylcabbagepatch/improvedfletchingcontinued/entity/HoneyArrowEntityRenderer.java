package benadrylcabbagepatch.improvedfletchingcontinued.entity;

import benadrylcabbagepatch.improvedfletchingcontinued.ImprovedFletchingContinued;
import benadrylcabbagepatch.improvedfletchingcontinued.arrows.honey.HoneyArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class HoneyArrowEntityRenderer extends ProjectileEntityRenderer<HoneyArrowEntity> {
    public static final Identifier TEXTURE = ImprovedFletchingContinued.id("textures/entity/projectiles/arrow_honey.png");

    public HoneyArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(HoneyArrowEntity entity) {
        return TEXTURE;
    }


}

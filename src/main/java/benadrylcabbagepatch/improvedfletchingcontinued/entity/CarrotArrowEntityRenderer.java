package benadrylcabbagepatch.improvedfletchingcontinued.entity;

import benadrylcabbagepatch.improvedfletchingcontinued.ImprovedFletchingContinued;
import benadrylcabbagepatch.improvedfletchingcontinued.arrows.carrot.CarrotArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class CarrotArrowEntityRenderer extends ProjectileEntityRenderer<CarrotArrowEntity> {
    public static final Identifier TEXTURE = ImprovedFletchingContinued.id("textures/entity/projectiles/arrow_carrot.png");

    public CarrotArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(CarrotArrowEntity entity) {
        return TEXTURE;
    }


}

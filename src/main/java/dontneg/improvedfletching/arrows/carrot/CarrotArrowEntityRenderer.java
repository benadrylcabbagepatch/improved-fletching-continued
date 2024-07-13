package dontneg.improvedfletching.arrows.carrot;

import dontneg.improvedfletching.ImprovedFletching;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.Identifier;

public class CarrotArrowEntityRenderer extends ArrowEntityRenderer {
    public static final Identifier TEXTURE = Identifier.of(ImprovedFletching.MODID,"textures/entity/arrow_carrot");

    public CarrotArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ArrowEntity arrowEntity) {
        return TEXTURE;
    }


}

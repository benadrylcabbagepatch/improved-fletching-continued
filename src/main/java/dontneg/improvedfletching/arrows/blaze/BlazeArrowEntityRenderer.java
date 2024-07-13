package dontneg.improvedfletching.arrows.blaze;

import dontneg.improvedfletching.ImprovedFletching;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.Identifier;

public class BlazeArrowEntityRenderer extends ArrowEntityRenderer {
    public static final Identifier TEXTURE = Identifier.of(ImprovedFletching.MODID,"textures/entity/arrow_blaze");

    public BlazeArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ArrowEntity arrowEntity) {
        return TEXTURE;
    }


}

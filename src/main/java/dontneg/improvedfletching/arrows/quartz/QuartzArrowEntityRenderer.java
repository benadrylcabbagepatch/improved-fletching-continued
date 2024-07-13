package dontneg.improvedfletching.arrows.quartz;

import dontneg.improvedfletching.ImprovedFletching;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.Identifier;

public class QuartzArrowEntityRenderer extends ArrowEntityRenderer {
    public static final Identifier TEXTURE = Identifier.of(ImprovedFletching.MODID,"textures/entity/arrow_quartz");

    public QuartzArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ArrowEntity arrowEntity) {
        return TEXTURE;
    }


}

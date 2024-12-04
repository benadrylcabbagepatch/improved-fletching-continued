package dontneg.improvedfletching.entity;

import dontneg.improvedfletching.ImprovedFletching;
import dontneg.improvedfletching.arrows.honey.HoneyArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.state.ArrowEntityRenderState;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;
import net.minecraft.util.Identifier;

public class HoneyArrowEntityRenderer extends ProjectileEntityRenderer<HoneyArrowEntity, ProjectileEntityRenderState> {
    public static final Identifier TEXTURE = Identifier.of(ImprovedFletching.MODID,"textures/entity/projectiles/arrow_honey.png");

    public HoneyArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public ProjectileEntityRenderState createRenderState() {
        return new ArrowEntityRenderState();
    }

    @Override
    protected Identifier getTexture(ProjectileEntityRenderState state) {
        return TEXTURE;
    }


}

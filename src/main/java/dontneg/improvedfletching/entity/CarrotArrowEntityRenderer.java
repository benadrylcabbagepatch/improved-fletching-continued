package dontneg.improvedfletching.entity;

import dontneg.improvedfletching.ImprovedFletching;
import dontneg.improvedfletching.arrows.carrot.CarrotArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.state.ArrowEntityRenderState;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;
import net.minecraft.util.Identifier;

public class CarrotArrowEntityRenderer extends ProjectileEntityRenderer<CarrotArrowEntity, ProjectileEntityRenderState> {
    public static final Identifier TEXTURE = Identifier.of(ImprovedFletching.MODID,"textures/entity/projectiles/arrow_carrot.png");

    public CarrotArrowEntityRenderer(EntityRendererFactory.Context context) {
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

package dontneg.improvedfletching.arrows.quartz;

import dontneg.improvedfletching.entity.ArrowEntityTypes;
import dontneg.improvedfletching.item.ArrowItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class QuartzArrowEntity extends PersistentProjectileEntity {

    public QuartzArrowEntity(EntityType<? extends QuartzArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public QuartzArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(ArrowEntityTypes.ARROW_QUARTZ, x, y, z, world, stack, shotFrom);
    }

    public QuartzArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(ArrowEntityTypes.ARROW_QUARTZ, owner, world, stack, shotFrom);
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE,1,0,true,false,false));
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ArrowItems.ARROW_QUARTZ);
    }
}

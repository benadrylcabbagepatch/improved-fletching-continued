package dontneg.improvedfletching.arrows.blaze;

import dontneg.improvedfletching.entity.ArrowEntityTypes;
import dontneg.improvedfletching.item.ArrowItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlazeArrowEntity extends PersistentProjectileEntity {

    public BlazeArrowEntity(EntityType<? extends BlazeArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public BlazeArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(ArrowEntityTypes.ARROW_BLAZE, x, y, z, world, stack, shotFrom);
    }

    public BlazeArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(ArrowEntityTypes.ARROW_BLAZE, owner, world, stack, shotFrom);
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        target.setOnFireFor(5f);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ArrowItems.ARROW_BLAZE);
    }
}

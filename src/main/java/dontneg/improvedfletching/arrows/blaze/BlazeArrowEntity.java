package dontneg.improvedfletching.arrows.blaze;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlazeArrowEntity extends ArrowEntity {
    public BlazeArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(world, owner, stack, shotFrom);
    }

    public BlazeArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(world, x, y, z, stack, shotFrom);
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        target.setOnFireFor(5f);
    }
}

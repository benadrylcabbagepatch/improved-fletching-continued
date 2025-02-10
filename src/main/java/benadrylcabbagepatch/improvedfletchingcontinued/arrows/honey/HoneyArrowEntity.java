package benadrylcabbagepatch.improvedfletchingcontinued.arrows.honey;

import benadrylcabbagepatch.improvedfletchingcontinued.entity.ArrowEntityTypes;
import benadrylcabbagepatch.improvedfletchingcontinued.item.ArrowItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class HoneyArrowEntity extends PersistentProjectileEntity {

    public HoneyArrowEntity(EntityType<? extends HoneyArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public HoneyArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(ArrowEntityTypes.ARROW_HONEY, x, y, z, world, stack, shotFrom);
    }

    public HoneyArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(ArrowEntityTypes.ARROW_HONEY, owner, world, stack, shotFrom);
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,60,1,true,false,false));
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ArrowItems.ARROW_HONEY);
    }


}

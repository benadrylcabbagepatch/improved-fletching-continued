package dontneg.improvedfletching.arrows.carrot;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CarrotArrowEntity extends ArrowEntity {

    public CarrotArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(world, owner, stack, shotFrom);
    }

    public CarrotArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(world, x, y, z, stack, shotFrom);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        BlockState ageProperty = this.getWorld().getBlockState(blockHitResult.getBlockPos());
        this.getWorld().setBlockState(
            blockHitResult.getBlockPos(),
            ageProperty.with(
                    Properties.AGE_3,
                    ageProperty.get(Properties.AGE_3) + MathHelper.nextInt(this.getWorld().random,1,3-ageProperty.get(Properties.AGE_3))));
    }
}

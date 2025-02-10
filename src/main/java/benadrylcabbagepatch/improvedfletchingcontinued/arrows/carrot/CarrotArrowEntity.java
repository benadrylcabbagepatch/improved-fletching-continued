package benadrylcabbagepatch.improvedfletchingcontinued.arrows.carrot;

import benadrylcabbagepatch.improvedfletchingcontinued.entity.ArrowEntityTypes;
import benadrylcabbagepatch.improvedfletchingcontinued.item.ArrowItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CarrotArrowEntity extends PersistentProjectileEntity {

    public CarrotArrowEntity(EntityType<? extends CarrotArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public CarrotArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(ArrowEntityTypes.ARROW_CARROT, x, y, z, world, stack, shotFrom);
    }

    public CarrotArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(ArrowEntityTypes.ARROW_CARROT, owner, world, stack, shotFrom);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        World world = this.getWorld();
        BlockPos pos = blockHitResult.getBlockPos();
        BlockState blockState = world.getBlockState(pos.up());
        if(!world.getBlockState(pos).getBlock().equals(Blocks.FARMLAND)) return;
        for(IntProperty property: new IntProperty[]{Properties.AGE_3,Properties.AGE_7}){
            try{
                int maxAge = Integer.parseInt(String.valueOf(property.toString().charAt(property.toString().length()-3)));
                int currentAge = blockState.get(property);
                if(currentAge==maxAge) return;
                if(world.isClient()){
                    for(int i = 0;i<20;i++){
                        world.addParticle(ParticleTypes.HAPPY_VILLAGER,
                                pos.getX()+Math.random(),
                                pos.getY()+1+Math.random()-(1-currentAge /10.0),
                                pos.getZ()+Math.random(),
                                0,0,0
                        );
                    }
                }else{
                    world.setBlockState(pos.up(), blockState.with(property, currentAge +
                            MathHelper.nextInt(world.random,1,maxAge-currentAge)));
                }
            }catch(IllegalArgumentException ignored){}
        }
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ArrowItems.ARROW_CARROT);
    }
}

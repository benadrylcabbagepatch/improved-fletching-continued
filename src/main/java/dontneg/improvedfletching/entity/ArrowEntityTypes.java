package dontneg.improvedfletching.entity;

import dontneg.improvedfletching.ImprovedFletching;
import dontneg.improvedfletching.arrows.blaze.BlazeArrowEntity;
import dontneg.improvedfletching.arrows.carrot.CarrotArrowEntity;
import dontneg.improvedfletching.arrows.honey.HoneyArrowEntity;
import dontneg.improvedfletching.arrows.quartz.QuartzArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ArrowEntityTypes {
    public static final EntityType.Builder<BlazeArrowEntity> ARROW_BLAZE_UNBUILT = EntityType.Builder.create(BlazeArrowEntity::new,SpawnGroup.MISC);
    public static final EntityType.Builder<CarrotArrowEntity> ARROW_CARROT_UNBUILT = EntityType.Builder.create(CarrotArrowEntity::new,SpawnGroup.MISC);
    public static final EntityType.Builder<HoneyArrowEntity> ARROW_HONEY_UNBUILT = EntityType.Builder.create(HoneyArrowEntity::new,SpawnGroup.MISC);
    public static final EntityType.Builder<QuartzArrowEntity> ARROW_QUARTZ_UNBUILT = EntityType.Builder.create(QuartzArrowEntity::new,SpawnGroup.MISC);
    public static final EntityType<BlazeArrowEntity> ARROW_BLAZE = ArrowEntityTypes.ARROW_BLAZE_UNBUILT
            .dimensions(0.5F, 0.5F).eyeHeight(0.13F).maxTrackingRange(4).trackingTickInterval(20).build();
    public static final EntityType<CarrotArrowEntity> ARROW_CARROT = ArrowEntityTypes.ARROW_CARROT_UNBUILT
            .dimensions(0.5F, 0.5F).eyeHeight(0.13F).maxTrackingRange(4).trackingTickInterval(20).build();
    public static final EntityType<HoneyArrowEntity> ARROW_HONEY = ArrowEntityTypes.ARROW_HONEY_UNBUILT
            .dimensions(0.5F, 0.5F).eyeHeight(0.13F).maxTrackingRange(4).trackingTickInterval(20).build();
    public static final EntityType<QuartzArrowEntity> ARROW_QUARTZ = ArrowEntityTypes.ARROW_QUARTZ_UNBUILT
            .dimensions(0.5F, 0.5F).eyeHeight(0.13F).maxTrackingRange(4).trackingTickInterval(20).build();

    public static void registerTypes(){
        Registry.register(Registries.ENTITY_TYPE, Identifier.of(ImprovedFletching.MODID,"arrow_blaze"),ARROW_BLAZE);
        Registry.register(Registries.ENTITY_TYPE, Identifier.of(ImprovedFletching.MODID,"arrow_carrot"),ARROW_CARROT);
        Registry.register(Registries.ENTITY_TYPE, Identifier.of(ImprovedFletching.MODID,"arrow_honey"),ARROW_HONEY);
        Registry.register(Registries.ENTITY_TYPE, Identifier.of(ImprovedFletching.MODID,"arrow_quartz"),ARROW_QUARTZ);
    }
}
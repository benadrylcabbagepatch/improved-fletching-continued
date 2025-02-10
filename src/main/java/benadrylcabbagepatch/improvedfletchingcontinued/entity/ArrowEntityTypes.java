package benadrylcabbagepatch.improvedfletchingcontinued.entity;

import benadrylcabbagepatch.improvedfletchingcontinued.ImprovedFletchingContinued;
import benadrylcabbagepatch.improvedfletchingcontinued.arrows.blaze.BlazeArrowEntity;
import benadrylcabbagepatch.improvedfletchingcontinued.arrows.carrot.CarrotArrowEntity;
import benadrylcabbagepatch.improvedfletchingcontinued.arrows.honey.HoneyArrowEntity;
import benadrylcabbagepatch.improvedfletchingcontinued.arrows.quartz.QuartzArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ArrowEntityTypes {
    private final static Identifier blazeID = Identifier.of(ImprovedFletchingContinued.MODID,"arrow_blaze");
    private final static Identifier carrotID = Identifier.of(ImprovedFletchingContinued.MODID,"arrow_carrot");
    private final static Identifier honeyID = Identifier.of(ImprovedFletchingContinued.MODID,"arrow_honey");
    private final static Identifier quartzID = Identifier.of(ImprovedFletchingContinued.MODID,"arrow_quartz");
    public static final EntityType.Builder<BlazeArrowEntity> ARROW_BLAZE_UNBUILT = EntityType.Builder.create(BlazeArrowEntity::new,SpawnGroup.MISC);
    public static final EntityType.Builder<CarrotArrowEntity> ARROW_CARROT_UNBUILT = EntityType.Builder.create(CarrotArrowEntity::new,SpawnGroup.MISC);
    public static final EntityType.Builder<HoneyArrowEntity> ARROW_HONEY_UNBUILT = EntityType.Builder.create(HoneyArrowEntity::new,SpawnGroup.MISC);
    public static final EntityType.Builder<QuartzArrowEntity> ARROW_QUARTZ_UNBUILT = EntityType.Builder.create(QuartzArrowEntity::new,SpawnGroup.MISC);
    public static final EntityType<BlazeArrowEntity> ARROW_BLAZE = ArrowEntityTypes.ARROW_BLAZE_UNBUILT
            .dimensions(0.5F, 0.5F).maxTrackingRange(4).trackingTickInterval(20).build();
    public static final EntityType<CarrotArrowEntity> ARROW_CARROT = ArrowEntityTypes.ARROW_CARROT_UNBUILT
            .dimensions(0.5F, 0.5F).maxTrackingRange(4).trackingTickInterval(20).build();
    public static final EntityType<HoneyArrowEntity> ARROW_HONEY = ArrowEntityTypes.ARROW_HONEY_UNBUILT
            .dimensions(0.5F, 0.5F).maxTrackingRange(4).trackingTickInterval(20).build();
    public static final EntityType<QuartzArrowEntity> ARROW_QUARTZ = ArrowEntityTypes.ARROW_QUARTZ_UNBUILT
            .dimensions(0.5F, 0.5F).maxTrackingRange(4).trackingTickInterval(20).build();

    public static void registerTypes(){
        Registry.register(Registries.ENTITY_TYPE, blazeID, ARROW_BLAZE);
        Registry.register(Registries.ENTITY_TYPE, carrotID, ARROW_CARROT);
        Registry.register(Registries.ENTITY_TYPE, honeyID, ARROW_HONEY);
        Registry.register(Registries.ENTITY_TYPE, quartzID, ARROW_QUARTZ);
    }
}
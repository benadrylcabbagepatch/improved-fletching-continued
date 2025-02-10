package benadrylcabbagepatch.improvedfletchingcontinued.item;

import benadrylcabbagepatch.improvedfletchingcontinued.ImprovedFletchingContinued;
import benadrylcabbagepatch.improvedfletchingcontinued.arrows.blaze.BlazeArrow;
import benadrylcabbagepatch.improvedfletchingcontinued.arrows.carrot.CarrotArrow;
import benadrylcabbagepatch.improvedfletchingcontinued.arrows.honey.HoneyArrow;
import benadrylcabbagepatch.improvedfletchingcontinued.arrows.quartz.QuartzArrow;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class ArrowItems {
    public static final Item ARROW_BLAZE = registerItem("arrow_blaze",
            new BlazeArrow(new Item.Settings()));
    public static final Item ARROW_CARROT = registerItem("arrow_carrot",
            new CarrotArrow(new Item.Settings()));
    public static final Item ARROW_HONEY = registerItem("arrow_honey",
            new HoneyArrow(new Item.Settings()));
    public static final Item ARROW_QUARTZ = registerItem("arrow_quartz",
            new QuartzArrow(new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, ImprovedFletchingContinued.id(name), item);
    }

    public static void registerItems() {}
}

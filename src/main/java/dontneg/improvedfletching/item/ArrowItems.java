package dontneg.improvedfletching.item;

import dontneg.improvedfletching.ImprovedFletching;
import dontneg.improvedfletching.arrows.blaze.BlazeArrow;
import dontneg.improvedfletching.arrows.carrot.CarrotArrow;
import dontneg.improvedfletching.arrows.honey.HoneyArrow;
import dontneg.improvedfletching.arrows.quartz.QuartzArrow;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class ArrowItems {
    public static final Item ARROW_BLAZE = registerItem("arrow_blaze",
            new BlazeArrow(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, ImprovedFletching.GetIdentifier("arrow_blaze")))));
    public static final Item ARROW_CARROT = registerItem("arrow_carrot",
            new CarrotArrow(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, ImprovedFletching.GetIdentifier("arrow_carrot")))));
    public static final Item ARROW_HONEY = registerItem("arrow_honey",
            new HoneyArrow(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, ImprovedFletching.GetIdentifier("arrow_honey")))));
    public static final Item ARROW_QUARTZ = registerItem("arrow_quartz",
            new QuartzArrow(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, ImprovedFletching.GetIdentifier("arrow_quartz")))));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, ImprovedFletching.GetIdentifier(name), item);
    }

    public static void registerItems() {}
}

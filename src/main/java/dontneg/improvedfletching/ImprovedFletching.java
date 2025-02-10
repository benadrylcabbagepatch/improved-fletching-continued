package dontneg.improvedfletching;

import dontneg.improvedfletching.codec.FletchingData;
import dontneg.improvedfletching.creativetab.ImprovedFletchingTab;
import dontneg.improvedfletching.entity.*;
import dontneg.improvedfletching.item.ArrowItems;
import dontneg.improvedfletching.screen.FletchingScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.List;

public class ImprovedFletching implements ModInitializer {
	public static final String MODID = "improvedfletching";
	@SuppressWarnings("unused")
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final Identifier FLETCHING_ID = Identifier.of(MODID, "fletching_table");
	public static final BlockEntityType<FletchingTableBlockEntity> FLETCHING_TABLE_ENTITY = FabricBlockEntityTypeBuilder.create(FletchingTableBlockEntity::new, Blocks.FLETCHING_TABLE).build();
	public static final ScreenHandlerType<FletchingScreenHandler> FLETCHING_TABLE_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(FletchingScreenHandler::new, FletchingData.PACKET_CODEC);
	private static final List<Item> modifiers = Arrays.asList(
			ItemStack.EMPTY.getItem(),
			Items.BLAZE_POWDER,
			Items.CARROT,
			Items.HONEY_BOTTLE,
			Items.QUARTZ
	);

	public static Identifier GetIdentifier(String name){
		return Identifier.of(MODID, name);
	}

	@Override
	public void onInitialize() {
		ArrowItems.registerItems();
		ImprovedFletchingTab.registerCreativeTabs();
		ArrowEntityTypes.registerTypes();
		Registry.register(Registries.BLOCK_ENTITY_TYPE, FLETCHING_ID, FLETCHING_TABLE_ENTITY);
		Registry.register(Registries.SCREEN_HANDLER, FLETCHING_ID, FLETCHING_TABLE_SCREEN_HANDLER);
	}

	public static List<Item> getModifiers(){
		return modifiers;
	}
}
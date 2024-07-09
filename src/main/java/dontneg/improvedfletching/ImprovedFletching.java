package dontneg.improvedfletching;

import dontneg.improvedfletching.screen.FletchingScreen;
import dontneg.improvedfletching.screen.FletchingScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImprovedFletching implements ModInitializer {
	public static final String MODID = "improvedfletching";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final Identifier FLETCHING_ID = Identifier.of(MODID, "fletching_table");
	public static final BlockEntityType<FletchingTableBlockEntity> FLETCHING_TABLE_ENTITY = BlockEntityType.Builder.create(FletchingTableBlockEntity::new, Blocks.FLETCHING_TABLE).build();
	public static final ScreenHandlerType<FletchingScreenHandler> FLETCHING_TABLE_SCREEN_HANDLER = new ScreenHandlerType<>(FletchingScreenHandler::new, FeatureSet.empty());

	@Override
	public void onInitialize() {
		Registry.register(Registries.BLOCK_ENTITY_TYPE, FLETCHING_ID, FLETCHING_TABLE_ENTITY);
		Registry.register(Registries.SCREEN_HANDLER, FLETCHING_ID, FLETCHING_TABLE_SCREEN_HANDLER);
		HandledScreens.register(ImprovedFletching.FLETCHING_TABLE_SCREEN_HANDLER, FletchingScreen::new);
	}
}
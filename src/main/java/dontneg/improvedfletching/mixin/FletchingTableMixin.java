package dontneg.improvedfletching.mixin;

import com.mojang.datafixers.types.templates.Check;
import dontneg.improvedfletching.FletchingTableBlockEntity;
import dontneg.improvedfletching.ImprovedFletching;
import dontneg.improvedfletching.codec.FletchingData;
import dontneg.improvedfletching.screen.FletchingScreenHandler;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.*;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FletchingTableBlock.class)
public class FletchingTableMixin extends CraftingTableBlock implements BlockEntityProvider {
	public FletchingTableMixin(Settings settings) {
		super(settings);
	}
	private static final Text TITLE = Text.of("Fletching Table");
	private static final BooleanProperty FILLED = BooleanProperty.of("filled");
	@Inject(method = "<init>", at = @At(value = "TAIL"))
	public void injectConstructorFletching(AbstractBlock.Settings settings, CallbackInfo ci) {
		this.setDefaultState(this.getDefaultState().with(FILLED, false));
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		if (!world.isClient) {
			NamedScreenHandlerFactory screenHandlerFactory = (FletchingTableBlockEntity) world.getBlockEntity(pos);
			if (screenHandlerFactory != null) {
				player.openHandledScreen(screenHandlerFactory);
			}
		}
		return ActionResult.SUCCESS;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FILLED);
	}

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(FILLED, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()));
	}

//	@Override
//	protected NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
//		return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) ->
//				new FletchingScreenHandler(syncId, inventory, new FletchingData(pos)), TITLE);
//	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new FletchingTableBlockEntity(pos,state);
	}



	@Override
	protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof FletchingTableBlockEntity) {
				ItemScatterer.spawn(world, pos, (FletchingTableBlockEntity)blockEntity);
				world.updateComparators(pos,this);
			}
			super.onStateReplaced(state, world, pos, newState, moved);
		}
	}
}
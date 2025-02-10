package benadrylcabbagepatch.improvedfletchingcontinued;

import benadrylcabbagepatch.improvedfletchingcontinued.codec.FletchingData;
import benadrylcabbagepatch.improvedfletchingcontinued.interfaces.ImplementedInventory;
import benadrylcabbagepatch.improvedfletchingcontinued.item.ArrowItems;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import benadrylcabbagepatch.improvedfletchingcontinued.screen.FletchingScreenHandler;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@SuppressWarnings({"rawtypes"})
public class FletchingTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);
    public static final BooleanProperty FILLED = BooleanProperty.of("filled");
    private final DefaultedList<ItemStack> oldInventory = DefaultedList.ofSize(5, ItemStack.EMPTY);
    public final List<Item> arrowTypes = Arrays.asList(
            ItemStack.EMPTY.getItem(),
            Items.ARROW,
            ArrowItems.ARROW_BLAZE,
            ArrowItems.ARROW_CARROT,
            ArrowItems.ARROW_HONEY,
            ArrowItems.ARROW_QUARTZ
    );

    public FletchingTableBlockEntity(BlockPos pos, BlockState state){
        this(ImprovedFletchingContinued.FLETCHING_TABLE_ENTITY,pos,state);
    }

    public FletchingTableBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("Fletching Table");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FletchingScreenHandler(syncId, playerInventory, this, ScreenHandlerContext.create(world,pos));
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public int size() {
        return 5;
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
    }

    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
    }

    @Override
    public void markDirty() {
        assert world != null;
        world.updateListeners(pos,getCachedState(),getCachedState(),3);
        super.markDirty();
    }

    public static void tick(World world, BlockPos pos, BlockState state, FletchingTableBlockEntity blockEntity) {
        if(world.isClient()) return;
        DefaultedList<ItemStack> inventory = blockEntity.inventory;
        boolean changed = false;
        for(int o = 0;o<blockEntity.size();o++){
            if (!inventory.get(o).getItem().equals(blockEntity.oldInventory.get(o).getItem()) ||
                    !(inventory.get(o).getCount() == blockEntity.oldInventory.get(o).getCount())) {
                changed = true;
                break;
            }
        }
        if(changed){
            if(blockEntity.inventory.get(3).isEmpty()){
                Objects.requireNonNull(blockEntity.getWorld()).setBlockState(pos,state.with(FILLED,false));
            }else{
                Objects.requireNonNull(blockEntity.getWorld()).setBlockState(pos,state.with(FILLED,true));
            }
            for(int i = 0;i<6;i++){
                boolean hasModifier = i > 1;
                if(arrowReady(blockEntity.inventory)==i){
                    craft(blockEntity,blockEntity.arrowTypes.get(i).getDefaultStack(),hasModifier);
                    break;
                }
            }
        }

        for(int i = 0;i<blockEntity.size();i++){
            blockEntity.oldInventory.set(i,new ItemStack(inventory.get(i).getItem(),inventory.get(i).getCount()));
        }
    }

    private static void craft(FletchingTableBlockEntity blockEntity, ItemStack output, boolean hasModifier){
        int[] indexes = hasModifier ? new int[]{0,1,2,3} : new int[]{0,1,2};
        int min = 64;
        for(int index: indexes){
            int slotCount = blockEntity.inventory.get(index).getCount();
            if(slotCount<min){
                min = slotCount;
            }
        }
        if(blockEntity.inventory.get(4).getCount() == min && blockEntity.inventory.get(4).getItem() == output.getItem()) return;
        blockEntity.markDirty();
        blockEntity.setStack(4,new ItemStack(output.getItem(), min*4));
    }

    public static boolean inventoryReady(DefaultedList<ItemStack> inventory){
        return inventory.get(0).isOf(Items.FEATHER) && inventory.get(1).isOf(Items.STICK) && inventory.get(2).isOf(Items.FLINT);
    }

    public static int arrowReady(DefaultedList<ItemStack> inventory) {
        List<Item> modifiers = ImprovedFletchingContinued.getModifiers();
        if (inventoryReady(inventory)) {
            for(int i = 0;i<modifiers.size();i++){
                if(inventory.get(3).getItem().equals(modifiers.get(i))) return i+1;
            }
        }
        return 0;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }

    @Override
    public Object getScreenOpeningData(ServerPlayerEntity player) {
        return new FletchingData(getPos());
    }
}
package dontneg.improvedfletching;

import dontneg.improvedfletching.codec.FletchingData;
import dontneg.improvedfletching.interfaces.ImplementedInventory;
import dontneg.improvedfletching.item.ArrowItems;
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
import dontneg.improvedfletching.screen.FletchingScreenHandler;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@SuppressWarnings({"rawtypes"})
public class FletchingTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory;
    public static final BooleanProperty FILLED = BooleanProperty.of("filled");
    private static final List<Item> arrowTypes = Arrays.asList(
            Items.ARROW,
            ArrowItems.ARROW_BLAZE,
            ArrowItems.ARROW_CARROT,
            ArrowItems.ARROW_HONEY,
            ArrowItems.ARROW_QUARTZ
    );

    public FletchingTableBlockEntity(BlockPos pos, BlockState state){
        this(ImprovedFletching.FLETCHING_TABLE_ENTITY,pos,state);
    }

    public FletchingTableBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Fletching Table");
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

    @SuppressWarnings("unused")
    public static void tick(World world, BlockPos pos, BlockState state, FletchingTableBlockEntity blockEntity) {
        if(world.isClient()) return;
        if(blockEntity.inventory.get(3).isEmpty()){
            Objects.requireNonNull(blockEntity.getWorld()).setBlockState(pos,blockEntity.getCachedState().with(FILLED,false));
        }else{
            Objects.requireNonNull(blockEntity.getWorld()).setBlockState(pos,blockEntity.getCachedState().with(FILLED,true));
        }
        DefaultedList<ItemStack> inventory = blockEntity.inventory;
        for(int i = -1;i<5;i++){
            if(arrowReady(inventory)==-1) break;
            else if(arrowReady(inventory)==i){
                craft(blockEntity,arrowTypes.get(i).getDefaultStack());
                break;
            }
        }
//        switch(arrowReady(inventory)){
//            case -1 -> {}
//            case 0 -> craft(blockEntity,Items.ARROW.getDefaultStack(), 0, 1, 2);
//            case 1 -> throw new NotImplementedException("ARROW_CARROT");
//            case 2 -> throw new NotImplementedException("ARROW_QUARTZ");
//            case 3 -> throw new NotImplementedException("ARROW_ECHO");
//            case 4 -> throw new NotImplementedException("ARROW_BLAZE");
//            case 5 -> throw new NotImplementedException("ARROW_HONEY");
//            case 6 -> throw new NotImplementedException("ARROW_REDSTONE");
//        }
    }

    private static void craft(FletchingTableBlockEntity blockEntity, ItemStack output){
        int min = 64;
        for(int index: new int[]{0,1,2,3}){
            int slotCount = blockEntity.getStack(index).getCount();
            if(slotCount<min){
                min = slotCount;
            }
        }
        if(blockEntity.inventory.get(4).getCount() == min && blockEntity.inventory.get(4).getItem() == output.getItem()) return;
        blockEntity.markDirty();
        blockEntity.setStack(4,new ItemStack(output.getItem(), min));
    }

    public static boolean inventoryReady(DefaultedList<ItemStack> inventory){
        return inventory.get(0).isOf(Items.FEATHER) && inventory.get(1).isOf(Items.STICK) && inventory.get(2).isOf(Items.FLINT);
    }

    public static int arrowReady(DefaultedList<ItemStack> inventory){
        List<Item> modifiers = ImprovedFletching.getModifiers();
        if(inventoryReady(inventory)){
            if(inventory.get(3).isEmpty()){
                return 0;
            }else {
                if(inventory.get(3).getItem().equals(Items.CARROT)){
                    return 1;
                }else if(inventory.get(3).equals(modifiers.get(1).getDefaultStack())){
                    return 2;
                }else if(inventory.get(3).equals(modifiers.get(2).getDefaultStack())){
                    return 3;
                }else if(inventory.get(3).equals(modifiers.get(3).getDefaultStack())){
                    return 4;
                }else if(inventory.get(3).equals(modifiers.get(4).getDefaultStack())){
                    return 5;
                }else if(inventory.get(3).equals(modifiers.getLast().getDefaultStack())){
                    return 6;
                }
            }
        }
        return -1;
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
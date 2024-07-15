package dontneg.improvedfletching.screen;

import dontneg.improvedfletching.FletchingTableBlockEntity;
import dontneg.improvedfletching.ImprovedFletching;
import dontneg.improvedfletching.codec.FletchingData;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;

public class FletchingScreenHandler extends ScreenHandler {
    private final ScreenHandlerContext context;
    public final Inventory inventory;
    public final FletchingTableBlockEntity blockEntity;

    public FletchingScreenHandler(int syncId, PlayerInventory inventory, FletchingData data) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(data.pos()),
                ScreenHandlerContext.create(inventory.player.getWorld(),inventory.player.getBlockPos()));
    }

    public FletchingScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, ScreenHandlerContext context) {
        super(ImprovedFletching.FLETCHING_TABLE_SCREEN_HANDLER, syncId);
        checkSize((Inventory) blockEntity, 5);
        this.inventory = (Inventory) blockEntity;
        inventory.onOpen(playerInventory.player);
        this.blockEntity = (FletchingTableBlockEntity) blockEntity;
        this.context = context;
        this.addSlot(new FletchingSlot(this.inventory, 0, 11, 34, Items.FEATHER, null));
        this.addSlot(new FletchingSlot(this.inventory, 1, 54, 34, Items.STICK, null));
        this.addSlot(new FletchingSlot(this.inventory, 2, 97, 22, Items.FLINT, null));
        this.addSlot(new FletchingSlot(this.inventory, 3, 97, 46, null, ImprovedFletching.getModifiers()));
        this.addSlot(new FletchingSlot(this.inventory, 4, 148, 34, null, null){
            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                FletchingScreenHandler.this.onTakeOutput(stack);
                this.markDirty();
            }
        });
        addInventory(playerInventory);
        addHotbar(playerInventory);
    }

    public void onTakeOutput(ItemStack stack){
        decrementSlots(stack, 0,1,2,3);
    }

    public void decrementSlots(ItemStack stack, int... slotIndexes){
        for(int index: slotIndexes){
            if(this.inventory.getStack(index).isEmpty()) continue;
            this.inventory.getStack(index).decrement(stack.getCount());
        }
    }

    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, Blocks.FLETCHING_TABLE);
    }

    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        if(player.getWorld().isClient()) return ItemStack.EMPTY;
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }
            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        if(invSlot==4) decrementSlots(newStack, 0,1,2,3);
        return newStack;
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        this.sendContentUpdates();
        super.onContentChanged(inventory);
    }

    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        boolean returnItems = false;
        if(!returnItems) return;
        this.context.run((world, pos) -> this.dropInventory(player, this.inventory));
    }

    private void addInventory(PlayerInventory playerInventory){
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    private void addHotbar(PlayerInventory playerInventory){
        for(int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
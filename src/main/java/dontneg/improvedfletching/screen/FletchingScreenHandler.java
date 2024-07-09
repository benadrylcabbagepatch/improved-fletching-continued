package dontneg.improvedfletching.screen;

import dontneg.improvedfletching.ImprovedFletching;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.CartographyTableScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;

import java.util.ArrayList;

public class FletchingScreenHandler extends ScreenHandler {
    private final ArrayList<Item> modifiers = new ArrayList<>();
    private final ScreenHandlerContext context;
    public final Inventory inventory;
    private final CraftingResultInventory resultInventory;

    public FletchingScreenHandler(int syncId, PlayerInventory inventory) {
        this(syncId, inventory, ScreenHandlerContext.EMPTY);
    }

    public FletchingScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ImprovedFletching.FLETCHING_TABLE_SCREEN_HANDLER, syncId);
        addModifiers();
        this.inventory = new SimpleInventory(5) {
            public void markDirty() {
                FletchingScreenHandler.this.onContentChanged(this);
                super.markDirty();
            }
        };
        this.resultInventory = new CraftingResultInventory() {
            public void markDirty() {
                FletchingScreenHandler.this.onContentChanged(this);
                super.markDirty();
            }
        };
        this.context = context;
        this.addSlot(new FletchingSlot(this.inventory, 0, 11, 34, Items.FEATHER, null));
        this.addSlot(new FletchingSlot(this.inventory, 1, 54, 34, Items.STICK, null));
        this.addSlot(new FletchingSlot(this.inventory, 2, 97, 22, Items.FLINT, null));
        this.addSlot(new FletchingSlot(this.inventory, 3, 97, 46, null, modifiers));
        this.addSlot(new FletchingSlot(this.resultInventory, 4, 148, 34, null, null));
        addInventory(playerInventory);
        addHotbar(playerInventory);
    }
    
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, Blocks.FLETCHING_TABLE);
    }

    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return slot.inventory != this.resultInventory && super.canInsertIntoSlot(stack, slot);
    }

    public ItemStack quickMove(PlayerEntity player, int invSlot) {
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
        return newStack;
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        this.sendContentUpdates();
    }

    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.context.run((world, pos) -> this.dropInventory(player, this.inventory));
    }

    private void addModifiers() {
        modifiers.add(Items.CARROT);
        modifiers.add(Items.QUARTZ);
        modifiers.add(Items.ECHO_SHARD);
        modifiers.add(Items.BLAZE_POWDER);
        modifiers.add(Items.HONEY_BOTTLE);
        modifiers.add(Items.REDSTONE);
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
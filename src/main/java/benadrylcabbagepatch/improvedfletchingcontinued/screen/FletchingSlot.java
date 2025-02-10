package benadrylcabbagepatch.improvedfletchingcontinued.screen;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FletchingSlot extends Slot {

    private final Item item;
    private final List<Item> items;

    public FletchingSlot(Inventory inventory, int index, int x, int y, @Nullable Item item, @Nullable List<Item> items) {
        super(inventory, index, x, y);
        this.item = item;
        this.items = items;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        if(items!=null){
            for(Item modifier: items){
                if(stack.isOf(modifier)){
                    return true;
                }
            }
            return false;
        }else if(item!=null){
            return stack.isOf(item);
        }else{
            return false;
        }
    }
}

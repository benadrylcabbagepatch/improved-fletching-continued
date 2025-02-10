package benadrylcabbagepatch.improvedfletchingcontinued.screen;

import benadrylcabbagepatch.improvedfletchingcontinued.ImprovedFletchingContinued;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.Identifier;
import net.minecraft.text.Text;

public class FletchingScreen extends HandledScreen<FletchingScreenHandler> {
    private final Identifier TEXTURE = Identifier.of(ImprovedFletchingContinued.MODID, "textures/gui/fletching_table_filled_slot_three.png");

    public FletchingScreen(FletchingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
	protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
		int x = (width - backgroundWidth) / 2;
		int y = (height - backgroundHeight) / 2;
		context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
	}

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }


}
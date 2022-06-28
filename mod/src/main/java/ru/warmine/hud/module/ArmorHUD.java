package ru.warmine.hud.module;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.client.gui.GuiIngame.itemRenderer;


public class ArmorHUD implements HUD {


    @Override
    public void render(Minecraft minecraft, FontRenderer renderer, ScaledResolution sr) {
        List<ItemStack> items = new ArrayList<>();
        if(minecraft.thePlayer.inventory.getCurrentItem() != null) {
            items.add(minecraft.thePlayer.inventory.getCurrentItem());
        }
        ItemStack[] armors = minecraft.thePlayer.inventory.armorInventory;

        for (ItemStack armor : armors) {
            if (armor != null) {
                items.add(armor);
            }
        }

        int x = sr.getScaledWidth() - 18;
        int y = sr.getScaledHeight() - 18;


        for (int index = 0; index < items.size(); index++) {
            ItemStack item = items.get(index);

            String text = item.getMaxDamage() > 1 ?
                    (item.getMaxDamage() - item.getItemDamage()) + "/" + item.getMaxDamage() : String.valueOf(item.stackSize);

            minecraft.ingameGUI.drawString(minecraft.fontRenderer, text, x - renderer.getStringWidth(text) - 2, y - (index * 14) + 5, 16777215);
            drawItemStack(minecraft, minecraft.fontRenderer, item, x, y - (index * 14));
        }

    }

    private void drawItemStack(Minecraft mc, FontRenderer renderer, ItemStack itemStack, int x, int y)
    {
        itemRenderer.zLevel = 200.0F;
        FontRenderer font = null;
        if (itemStack != null) font = itemStack.getItem().getFontRenderer(itemStack);
        if (font == null) font = renderer;
        itemRenderer.renderItemAndEffectIntoGUI(font, mc.getTextureManager(), itemStack, x, y);
        itemRenderer.zLevel = 0.0F;
    }

    @Override
    public int startPosX() {
        return 0;
    }

    @Override
    public int startPosY() {
        return 0;
    }
}

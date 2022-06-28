package ru.warmine.hud.module;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
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
        int y = sr.getScaledHeight() - 5;

        for (int i = 0; i < items.size(); i++) {
            ItemStack item = items.get(i);
            String text = item.getMaxDamage() > 1 ?
                    (item.getMaxDamage() - item.getItemDamage()) + "/" + item.getMaxDamage() : String.valueOf(item.stackSize);

            minecraft.ingameGUI.drawString(minecraft.fontRenderer, text, x - renderer.getStringWidth(text) - 2, y - (i * 14) + 5, 16777215);

            drawItemStack(minecraft, minecraft.fontRenderer, item, x, y - (i * 14));
        }
    }

    private void drawItemStack(Minecraft mc, FontRenderer renderer, ItemStack par1ItemStack, int par2, int par3)
    {
        //хз зачем
        itemRenderer.zLevel = 200.0F;
        FontRenderer font = null;
        if (par1ItemStack != null) font = par1ItemStack.getItem().getFontRenderer(par1ItemStack);
        if (font == null) font = renderer;
        itemRenderer.renderItemAndEffectIntoGUI(font, mc.getTextureManager(), par1ItemStack, par2, par3);

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

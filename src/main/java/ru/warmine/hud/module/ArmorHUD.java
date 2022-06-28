package ru.warmine.hud.module;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.client.renderer.ItemRenderer.renderItemIn2D;


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

        int index = 1;
        for (ItemStack item : items) {

            String text = "";
            if(item.getMaxDamage() > 1) {
                text = (item.getMaxDamage() - item.getItemDamage()) + "/" + item.getMaxDamage();
            } else {
                text = item.stackSize + "";
            }

            minecraft.ingameGUI.drawString(minecraft.fontRenderer, text, x - renderer.getStringWidth(text) - 2, y - (index * 14) + 5, 16777215);
            drawItemStack(minecraft, minecraft.fontRenderer, item, x, y - (index * 14));
            index++;
        }
    }

    private void drawItemStack(Minecraft mc, FontRenderer renderer, ItemStack par1ItemStack, int par2, int par3)
    {
        //itemRenderer.zLevel = 200.0F;
        //FontRenderer font = null;
        //if (par1ItemStack != null) font = par1ItemStack.getItem().getFontRenderer(par1ItemStack);
        //if (font == null) font = renderer;
        //itemRenderer.renderItemAndEffectIntoGUI(font, mc.getTextureManager(), par1ItemStack, par2, par3);
//
        //itemRenderer.zLevel = 0.0F;

        TextureManager textureManager = mc.getTextureManager();

        textureManager.bindTexture(textureManager.getResourceLocation(par1ItemStack.getItemSpriteNumber()));

        int iconSize = 18;

        Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect(100, 100, 18 % 8 * iconSize, 198 + 18 / 8 * iconSize, iconSize, iconSize);

        RenderItem.getInstance().renderItemAndEffectIntoGUI(mc.fontRenderer, textureManager, par1ItemStack, par2, par3);


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

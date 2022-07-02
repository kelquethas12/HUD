package ru.warmine.hud.module;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.client.renderer.ItemRenderer.renderItemIn2D;
import static net.minecraft.client.renderer.RenderHelper.enableStandardItemLighting;


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

            String text = item.getMaxDamage() > 1 ? (item.getMaxDamage() - item.getItemDamage()) + "/" + item.getMaxDamage() : String.valueOf(item.stackSize);

            minecraft.ingameGUI.drawString(minecraft.fontRenderer, text, x - renderer.getStringWidth(text) - 2, y - (index * 14) + 5, 16777215);

            renderStart();
                drawItemStack(minecraft, minecraft.fontRenderer, item, x, y - (index * 14));
            renderStop();

            index++;
        }
    }

    private void drawItemStack(Minecraft mc, FontRenderer renderer, ItemStack par1ItemStack, int par2, int par3)
    {
        TextureManager textureManager = mc.getTextureManager();
        textureManager.bindTexture(textureManager.getResourceLocation(par1ItemStack.getItemSpriteNumber()));
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

    @Override
    public void renderStart() {
        GL11.glPushMatrix();
        //GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //GL11.glEnable(GL11.GL_LIGHTING);
        //GL11.glDisable(GL11.GL_DEPTH_TEST);
        enableStandardItemLighting();
    }

    @Override
    public void renderStop() {
        RenderHelper.disableStandardItemLighting();
        GL11.glPopMatrix();
    }
}

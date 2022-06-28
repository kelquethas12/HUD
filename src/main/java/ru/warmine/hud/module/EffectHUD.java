package ru.warmine.hud.module;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import java.util.Collection;

public class EffectHUD implements HUD {

    ResourceLocation field_147001_a = new ResourceLocation("textures/gui/container/inventory.png");


    @Override
    public void render(Minecraft minecraft, FontRenderer renderer, ScaledResolution sr) {
        if(Minecraft.getMinecraft().currentScreen == null || Minecraft.getMinecraft().currentScreen instanceof GuiChat) {
            Collection<PotionEffect> activePotions = minecraft.thePlayer.getActivePotionEffects();
            if(!activePotions.isEmpty()) {
                int indexPotion = 1;

                for (PotionEffect potionEffect : activePotions) {

                    minecraft.getTextureManager().bindTexture(field_147001_a);

                    int potionIcon = Potion.potionTypes[potionEffect.getPotionID()].getStatusIconIndex();

                    int iconSize = 18;

                    int x = sr.getScaledWidth() - 18;
                    int y = ((sr.getScaledHeight() / 2) - (activePotions.size() * 12)) + (indexPotion * 18);

                    minecraft.getTextureManager().bindTexture(field_147001_a);

                    Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect(x, y, potionIcon % 8 * iconSize, 198 + potionIcon / 8 * iconSize, iconSize, iconSize);
                    String amplifier = String.valueOf(potionEffect.getAmplifier())
                            .replace("0", "I")
                            .replace("1", "II")
                            .replace("2", "III")
                            .replace("3", "IV")
                            .replace("4", "V");

                    String text = I18n.format(Potion.potionTypes[potionEffect.getPotionID()].getName()) + " " + amplifier;

                    String duration = Potion.getDurationString(potionEffect);

                    renderer.drawString(text, x - renderer.getStringWidth(text) - 2, y + 1, 16777215);
                    renderer.drawString(duration, x - renderer.getStringWidth(duration) - 2, y + 1 +  renderer.FONT_HEIGHT, 16777215);


                    indexPotion++;

                }
            }
        }
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

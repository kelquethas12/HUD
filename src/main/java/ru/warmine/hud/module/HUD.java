package ru.warmine.hud.module;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

public interface HUD {
    void render(Minecraft minecraft, FontRenderer renderer, ScaledResolution sr);

    int startPosX();
    int startPosY();
}

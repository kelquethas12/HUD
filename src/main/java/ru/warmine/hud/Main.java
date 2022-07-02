package ru.warmine.hud;


import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

import ru.warmine.hud.module.ArmorHUD;
import ru.warmine.hud.module.EffectHUD;
import ru.warmine.hud.module.HUD;


import java.util.*;

@Mod(modid = "warmine_huds")
public class Main {


    List<HUD> renders = Arrays.asList(
            new ArmorHUD(),
            new EffectHUD()
    );


    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent.Post event) {
        if(!renders.isEmpty()) {
            ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
            renders.forEach(
                    hud -> hud.render(Minecraft.getMinecraft(), Minecraft.getMinecraft().fontRenderer, sr)
            );
        }
    }

}

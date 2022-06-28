package ru.warmine.hud;


import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
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

    @ForgeSubscribe
    public void render(RenderGameOverlayEvent.Post event) {
        if(!renders.isEmpty()) {
            ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);

            GL11.glPushMatrix();
            GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
            RenderHelper.enableGUIStandardItemLighting();

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_ALPHA_TEST);

            renders.forEach( hud -> hud.render(Minecraft.getMinecraft(), Minecraft.getMinecraft().fontRenderer, sr));

            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);

            RenderHelper.enableStandardItemLighting();
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
    }

}

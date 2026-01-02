package windclan.halflifemenu.transformers;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.LoadingOverlay;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.gui.GuiGraphics;
import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;
import windclan.halflifemenu.MenuTexture;

@Patch.Class("net.minecraft.client.gui.screens.LoadingOverlay")
public class LoadingOverlayTransformer extends MiniTransformer {
    @Patch.Method("registerTextures(Lnet/minecraft/client/Minecraft;)V")
    public void patchRegisterTextures(PatchContext ctx) {
        ctx.jumpToStart();
        ctx.add(
                ALOAD(0),
                INVOKESTATIC("windclan/halflifemenu/transformers/LoadingOverlayTransformer$Hooks", "loadTexutres", "(Lnet/minecraft/client/Minecraft;)V")
        );
    }
    @Patch.Method("render(Lnet/minecraft/client/gui/GuiGraphics;IIF)V")
    public void patchRender(PatchContext ctx) {
        ctx.jumpToLastReturn();
        ctx.add(
                ALOAD(0),
                ALOAD(1),
                INVOKESTATIC("windclan/halflifemenu/transformers/LoadingOverlayTransformer$Hooks", "render", "(Lnet/minecraft/client/gui/GuiGraphics;)V")
        );
    }
    public static class Hooks {
        public static void loadTexutres(Minecraft mc) {
            MenuTexture.registerTexture(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/loading/gray.png"));
            MenuTexture.registerTexture(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/loading/lambda.png"));
            MenuTexture.registerTexture(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/loading/blank.png"));
            MenuTexture.registerTexture(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/loading/mrvalve.png"));
            MenuTexture.registerTexture(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/bkg.png"));
            MenuTexture.registerTexture(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/minecraft.png"));
            MenuTexture.registerTexture(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/minceraft.png"));
        }
        public static void render(LoadingOverlay this1, GuiGraphics context) {
            int width = this1.minecraft.getWindow().getGuiScaledWidth();
            int height = this1.minecraft.getWindow().getGuiScaledHeight();
            float f = this1.fadeOutStart > -1L ? (float)(Util.getEpochMillis() - this1.fadeOutStart) / 1000.0F : -1.0F;
            if(this1.fadeIn) {
                context.blit(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/loading/gray.png"),0,0,0,0,width,height,16,16);
                context.blit(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/loading/lambda.png"),(width/2)-64,(height/2)-64,0,0,128,128,128,128);
            } else {
                context.blit(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/loading/blank.png"),0,0,0,0,width,height,16,16);
                context.blit(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/loading/mrvalve.png"),(width/2)-125,(height/2)-(187/2),0,0,250,187,250,187);
            }
            if (f >= 1.0F) {
                this1.minecraft.setOverlay(null);
            }
        }
    }
}

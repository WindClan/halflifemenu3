package windclan.halflifemenu.transformers;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.resources.ResourceLocation;
import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("net.minecraft.client.renderer.PanoramaRenderer")
public class PanoramaRendererTransformer extends MiniTransformer {
    @Patch.Method("render(Lnet/minecraft/client/gui/GuiGraphics;IIFF)V")
    public void patchRender(PatchContext ctx) {
        ctx.jumpToLastReturn();
        ctx.add(
                ALOAD(0),
                ALOAD(1),
                ILOAD(2),
                ILOAD(3),
                FLOAD(4),
                FLOAD(5),
                INVOKESTATIC("windclan/halflifemenu/transformers/PanoramaRendererTransformer$Hooks", "render", "(Lnet/minecraft/client/renderer/PanoramaRenderer;Lnet/minecraft/client/gui/GuiGraphics;IIFF)V")
        );
    }
    public static class Hooks {
        public static void render(PanoramaRenderer this1, GuiGraphics context, int width, int height, float a, float b) {
            RenderSystem.enableBlend();
            context.flush();
            context.blit(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/bkg.png"),0,0,0,0,width,height,width,height);
            RenderSystem.disableBlend();
        }
    }
}

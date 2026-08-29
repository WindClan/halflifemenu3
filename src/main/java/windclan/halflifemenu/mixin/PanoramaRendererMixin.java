package windclan.halflifemenu.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PanoramaRenderer.class)
public class PanoramaRendererMixin {
    @Unique
    private static ResourceLocation bkg = ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/bkg.png");
    @Inject(method="render",at=@At("HEAD"),cancellable = true)
    public void extractRenderState(GuiGraphics context, int width, int height, float a, float b, CallbackInfo ci) {
        RenderSystem.enableBlend();
        context.flush();
        context.blit(bkg,0,0,0,0,width,height,width,height);
        RenderSystem.disableBlend();
        ci.cancel();
    }
}

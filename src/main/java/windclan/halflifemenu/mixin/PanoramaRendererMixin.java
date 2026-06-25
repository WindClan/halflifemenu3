package windclan.halflifemenu.mixin;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.Panorama;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Panorama.class)
public class PanoramaRendererMixin {
    @Unique
    private static Identifier bkg = Identifier.fromNamespaceAndPath("halflifemenu","textures/bkg.png");
    @Inject(method="extractRenderState",at=@At("HEAD"),cancellable = true)
    public void extractRenderState(GuiGraphicsExtractor graphics, int width, int height, CallbackInfo ci) {
        graphics.blit(RenderPipelines.GUI_TEXTURED,bkg,0,0,0,0,width,height,width,height);
        ci.cancel();
    }
}

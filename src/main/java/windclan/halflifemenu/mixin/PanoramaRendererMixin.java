package windclan.halflifemenu.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import windclan.halflifemenu.MenuTexture;

@Mixin(PanoramaRenderer.class)
public class PanoramaRendererMixin {
    @Unique
    private static Identifier bkg = Identifier.fromNamespaceAndPath("halflifemenu","textures/bkg.png");
    /**
     * @author WindClan
     * @reason Basically I need to be able to replace all backgrounds
     */
    @Overwrite
    public void render(GuiGraphics context, int width, int height, boolean spin) {
        context.blit(RenderPipelines.GUI_TEXTURED,bkg,0,0,0,0,width,height,width,height);
    }
    @Inject(at = @At("TAIL"), method = "registerTextures")
    public void registerTextures(TextureManager textureManager, CallbackInfo ci) {
        textureManager.register(bkg,new MenuTexture(bkg));
    }

}

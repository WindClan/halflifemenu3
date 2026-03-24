package windclan.halflifemenu.mixin;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.Panorama;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Panorama.class)
public class PanoramaRendererMixin {
    @Unique
    private static Identifier bkg = Identifier.fromNamespaceAndPath("halflifemenu","textures/bkg.png");
    /**
     * @author WindClan
     * @reason Basically I need to be able to replace all backgrounds
     */
    @Overwrite
    public void extractRenderState(final GuiGraphicsExtractor graphics, final int width, final int height, final boolean shouldSpin) {
        graphics.blit(RenderPipelines.GUI_TEXTURED,bkg,0,0,0,0,width,height,width,height);
    }
}

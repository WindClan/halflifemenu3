package windclan.halflifemenu.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.LoadingOverlay;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import windclan.halflifemenu.MenuTexture;

@Mixin(LoadingOverlay.class)
public abstract class LoadingOverlayMixin {
    @Final
    @Shadow
    private Minecraft minecraft;
    @Shadow
    private long fadeOutStart;
    @Final @Shadow
    private boolean fadeIn;
    @Unique
    private final static Identifier blank = Identifier.fromNamespaceAndPath("halflifemenu","textures/loading/blank.png");
    @Unique
    private final static Identifier gray = Identifier.fromNamespaceAndPath("halflifemenu","textures/loading/gray.png");
    @Unique
    private final static Identifier mrvalve = Identifier.fromNamespaceAndPath("halflifemenu","textures/loading/mrvalve.png");
    @Unique
    private final static Identifier openyoureyes = Identifier.fromNamespaceAndPath("halflifemenu","textures/loading/openyoureyes.png");
    @Unique
    private final static Identifier lambda = Identifier.fromNamespaceAndPath("halflifemenu","textures/loading/lambda.png");
    @Inject(method="registerTextures", at=@At("TAIL"))
    private static void registerTextures(final TextureManager textureManager, CallbackInfo ci) {
        textureManager.registerAndLoad(blank,new MenuTexture(blank));
        textureManager.registerAndLoad(gray,new MenuTexture(gray));
        textureManager.registerAndLoad(mrvalve,new MenuTexture(mrvalve));
        textureManager.registerAndLoad(openyoureyes,new MenuTexture(openyoureyes));
        textureManager.registerAndLoad(lambda,new MenuTexture(lambda));
    }

    @Inject(method="extractRenderState", at=@At("TAIL"))
    public void extractRenderState(final GuiGraphicsExtractor graphics, final int mouseX, final int mouseY, final float a, CallbackInfo ci) {
        int width = minecraft.getWindow().getGuiScaledWidth();
        int height = minecraft.getWindow().getGuiScaledHeight();
        float f = this.fadeOutStart > -1L ? (float)(Util.getEpochMillis() - this.fadeOutStart) / 1000.0F : -1.0F;
        if(this.fadeIn) {
            graphics.blit(RenderPipelines.GUI_TEXTURED,gray,0,0,0,0,width,height,16,16);
            graphics.blit(RenderPipelines.GUI_TEXTURED,lambda,(width/2)-64,(height/2)-64,0,0,128,128,128,128);
        } else {
            graphics.blit(RenderPipelines.GUI_TEXTURED,blank,0,0,0,0,width,height,16,16);
            graphics.blit(RenderPipelines.GUI_TEXTURED,mrvalve,(width/2)-125,(height/2)-(187/2),0,0,250,187,250,187);
        }
        if (f >= 1.0F) {
            this.minecraft.setOverlay(null);
        }
    }
}

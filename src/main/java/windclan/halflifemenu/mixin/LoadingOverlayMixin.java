package windclan.halflifemenu.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.LoadingOverlay;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.Util;
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
    @Inject(method="registerTextures", at=@At("TAIL"))
    private static void registerTextures(Minecraft minecraft, CallbackInfo ci) {
        MenuTexture.registerTexture(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/loading/gray.png"));
        MenuTexture.registerTexture(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/loading/lambda.png"));
        MenuTexture.registerTexture(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/loading/blank.png"));
        MenuTexture.registerTexture(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/loading/mrvalve.png"));
        MenuTexture.registerTexture(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/bkg.png"));
        MenuTexture.registerTexture(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/minecraft.png"));
        MenuTexture.registerTexture(ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/minceraft.png"));
    }

    @Unique
    private static ResourceLocation gray = ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/loading/gray.png");
    @Unique
    private static ResourceLocation lambda = ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/loading/lambda.png");
    @Unique
    private static ResourceLocation blank = ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/loading/blank.png");
    @Unique
    private static ResourceLocation mrvalve = ResourceLocation.fromNamespaceAndPath("halflifemenu","textures/loading/mrvalve.png");

    @Inject(method="render", at=@At("TAIL"))
    public void render(GuiGraphics context, int i, int j, float f1, CallbackInfo ci) {
        int width = minecraft.getWindow().getGuiScaledWidth();
        int height = minecraft.getWindow().getGuiScaledHeight();
        float f = this.fadeOutStart > -1L ? (float)(Util.getEpochMillis() - this.fadeOutStart) / 1000.0F : -1.0F;
        if(this.fadeIn) {
            context.blit(gray,0,0,0,0,width,height,16,16);
            context.blit(lambda,(width/2)-64,(height/2)-64,0,0,128,128,128,128);
        } else {
            context.blit(blank,0,0,0,0,width,height,16,16);
            context.blit(mrvalve,(width/2)-125,(height/2)-(187/2),0,0,250,187,250,187);
        }
        if (f >= 1.0F) {
            this.minecraft.setOverlay(null);
        }
    }
}

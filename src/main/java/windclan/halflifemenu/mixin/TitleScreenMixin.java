package windclan.halflifemenu.mixin;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Unique;
import windclan.halflifemenu.HalfLifeTitle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import windclan.halflifemenu.MenuTexture;


@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Unique
    private final Minecraft client = Minecraft.getInstance();
    @Inject(at = @At("HEAD"), method = "init",cancellable = true)
    public void init(CallbackInfo ci) {
        if(!this.client.isDemo()) {
            this.client.setScreen(new HalfLifeTitle());
            ci.cancel();
        }
        else {
            client.close();
        }
    }
    @Inject(at = @At("HEAD"), method = "extractRenderState",cancellable = true)
    public void extractRenderState(final GuiGraphicsExtractor graphics, final int mouseX, final int mouseY, final float a, CallbackInfo ci) {
        ci.cancel();
    }
    @Inject(at = @At("TAIL"), method = "registerTextures")
    private static void registerTextures(final TextureManager textureManager, CallbackInfo ci) {
        textureManager.register(Identifier.fromNamespaceAndPath("halflifemenu","textures/minceraft.png"),new MenuTexture(Identifier.fromNamespaceAndPath("halflifemenu","textures/minceraft.png")));
        textureManager.register(Identifier.fromNamespaceAndPath("halflifemenu","textures/minecraft.png"),new MenuTexture(Identifier.fromNamespaceAndPath("halflifemenu","textures/minecraft.png")));
        textureManager.register(Identifier.fromNamespaceAndPath("halflifemenu","textures/bkg.png"),new MenuTexture(Identifier.fromNamespaceAndPath("halflifemenu","textures/bkg.png")));
    }
}

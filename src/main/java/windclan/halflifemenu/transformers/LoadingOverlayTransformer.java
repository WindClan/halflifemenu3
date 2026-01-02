package windclan.halflifemenu.transformers;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
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
    }
}

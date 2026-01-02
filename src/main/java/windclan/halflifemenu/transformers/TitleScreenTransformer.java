package windclan.halflifemenu.transformers;

import net.minecraft.client.Minecraft;
import nilloader.api.ClassRetransformer;
import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;
import windclan.halflifemenu.HalfLifeTitle;

@Patch.Class("net.minecraft.client.gui.screens.TitleScreen")
public class TitleScreenTransformer extends MiniTransformer implements ClassRetransformer {
    @Patch.Method("init()V")
    public void patchInit(PatchContext ctx) {
        ctx.jumpToLastReturn();
        ctx.add(
                // This is the recommended way to do ASM hooks in NilLoader - invoke a helper defined
                // in an inner class for your transformer.
                INVOKESTATIC("windclan/halflifemenu/transformers/TitleScreenTransformer$Hooks", "onInit", "()V")
        );

    }

    public static class Hooks {

        public static void onInit() {
            Minecraft.getInstance().setScreen(new HalfLifeTitle());
        }

    }
}

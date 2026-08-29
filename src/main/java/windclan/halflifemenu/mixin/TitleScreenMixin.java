package windclan.halflifemenu.mixin;

import org.spongepowered.asm.mixin.Unique;
import windclan.halflifemenu.HalfLifeTitle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
}

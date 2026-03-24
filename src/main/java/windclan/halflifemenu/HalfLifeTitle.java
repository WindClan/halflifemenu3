package windclan.halflifemenu;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.Date;
import java.util.Random;

public class HalfLifeTitle extends Screen {
    private final Minecraft client = Minecraft.getInstance();
    private final Date time = new Date();
    private final Random logoRandom = new Random(time.getTime()/1000);
    private final Identifier logo;
    public HalfLifeTitle() {
        super(Component.literal("Title screen"));
        if(logoRandom.nextInt(100) == 0) {
            logo = Identifier.fromNamespaceAndPath("halflifemenu","textures/minceraft.png");
        } else {
            logo = Identifier.fromNamespaceAndPath("halflifemenu","textures/minecraft.png");
        }
    }
    private final static int buttonHeight = 15;
    private final static int buttonWidth = 100;

    @Override
    protected void init() {
        int baseHeight = this.height;
        int multiplaterOffset = 2;
        boolean multiplayerAllowed = true;
        if (!client.allowsMultiplayer()) {
            multiplayerAllowed = false;
            multiplaterOffset = 0;
        }

        this.addRenderableWidget(Button.builder(Component.literal("New Game").withStyle(ChatFormatting.WHITE), button -> CreateWorldScreen.openFresh(minecraft,() -> {this.minecraft.setScreen(this);}))
                .bounds(13,  baseHeight - (buttonHeight+2)*(7+multiplaterOffset), buttonWidth, buttonHeight)
                .build());
        this.addRenderableWidget(Button.builder(Component.literal("Load Game").withStyle(ChatFormatting.WHITE), button -> this.minecraft.setScreen(new SelectWorldScreen(this)))
                .bounds(13,  baseHeight - (buttonHeight+2)*(6+multiplaterOffset), buttonWidth, buttonHeight)
                .build());
        if (multiplayerAllowed) {
            this.addRenderableWidget(Button.builder(Component.literal("Find Servers").withStyle(ChatFormatting.WHITE), button -> this.minecraft.setScreen(new JoinMultiplayerScreen(this)))
                    .bounds(13,  baseHeight - (buttonHeight+2)*6, buttonWidth, buttonHeight)
                    .build());
        }
        this.addRenderableWidget(Button.builder(Component.literal("Options...").withStyle(ChatFormatting.WHITE), button -> this.minecraft.setScreen(new OptionsScreen(this, this.minecraft.options,false)))
                .bounds(13,  baseHeight - (buttonHeight+2)*4, buttonWidth, buttonHeight)
                .build());
        this.addRenderableWidget(Button.builder(Component.literal("Quit Game").withStyle(ChatFormatting.WHITE), button -> this.minecraft.stop())
                .bounds(13,  baseHeight - (buttonHeight+2)*3, buttonWidth, buttonHeight)
                .build());
    }

    @Override
    public void extractRenderState(final GuiGraphicsExtractor graphics, final int mouseX, final int mouseY, final float a) {
        super.extractRenderState(graphics,mouseX,mouseY,a);
        int baseHeight = this.height;
        graphics.blit(RenderPipelines.GUI_TEXTURED,logo,15,(int)(baseHeight-(buttonHeight+2)*1.5),0,0,219/2,buttonHeight/2,219/2,buttonHeight/2);
    }

    @Override
    public void extractBackground(final GuiGraphicsExtractor graphics, final int mouseX, final int mouseY, final float a) {
        super.extractPanorama(graphics,a);
    }
}

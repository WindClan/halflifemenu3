package windclan.halflifemenu;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.metadata.texture.TextureMetadataSection;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import java.io.IOException;
import java.io.InputStream;

public class MenuTexture extends SimpleTexture {
    public MenuTexture(ResourceLocation resourceId) {
        super(resourceId);
        this.resourceId = resourceId;
    }

    protected ResourceLocation resourceId;
    @Override
    public void load(ResourceManager resourceManager) throws IOException {
        ClassLoader loader = this.getClass().getClassLoader();
        InputStream stream = loader.getResourceAsStream("assets/"+resourceId.getNamespace()+"/"+resourceId.getPath());
        TextureMetadataSection meta = new TextureMetadataSection(false,false);
        this.doLoad(NativeImage.read(stream),false,false);
    }

    public static void registerTexture(ResourceLocation a) {
        TextureManager tm = Minecraft.getInstance().getTextureManager();
        tm.register(a, new MenuTexture(a));
    }
}

package windclan.halflifemenu;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.MipmapStrategy;
import net.minecraft.client.renderer.texture.ReloadableTexture;
import net.minecraft.client.renderer.texture.TextureContents;
import net.minecraft.client.resources.metadata.texture.TextureMetadataSection;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

public class MenuTexture extends ReloadableTexture {
    public MenuTexture(Identifier resourceId) {
        super(resourceId);
    }

    @Override
    public TextureContents loadContents(ResourceManager resourceManager) throws IOException {
        ClassLoader loader = this.getClass().getClassLoader();
        InputStream stream = loader.getResourceAsStream("assets/"+this.resourceId().getNamespace()+"/"+this.resourceId().getPath());
        TextureMetadataSection meta = new TextureMetadataSection(false,false, MipmapStrategy.AUTO,1);
        return new TextureContents(NativeImage.read(stream),meta);
    }
}

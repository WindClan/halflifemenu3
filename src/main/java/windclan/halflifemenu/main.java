package windclan.halflifemenu;

import nilloader.api.ClassTransformer;
import nilloader.api.ModRemapper;
import nilloader.api.NilLogger;
import windclan.halflifemenu.transformers.LoadingOverlayTransformer;
import windclan.halflifemenu.transformers.PanoramaRendererTransformer;
import windclan.halflifemenu.transformers.TitleScreenTransformer;

public class main implements Runnable {
	public static boolean ran = false;
	public NilLogger log = NilLogger.get("halflifemenu");
	@Override
	public void run() {
		if (!ran) {
			int mappings = 0;
			try {
				Class.forName("net.fabricmc.loader.FabricLoader");
				mappings = 1;
			} catch (Exception ignored){}
			try {
				Class.forName("net.neoforged.neoforge.internal.BrandingControl");
				mappings = 2;
			} catch (Exception ignored){}
			if (mappings == 1) {
				ModRemapper.setTargetMapping("net.fabricmc.intermediary-1.21.1");
				log.debug("Detected fabric mappings!");
			} else if(mappings == 2) {
				ModRemapper.setTargetMapping(null);
				log.debug("Detected moj mappings!");
			}
			ClassTransformer.register(new PanoramaRendererTransformer());
			ClassTransformer.register(new TitleScreenTransformer());
			ClassTransformer.register(new LoadingOverlayTransformer());
			ran = true;
		}
	}

}

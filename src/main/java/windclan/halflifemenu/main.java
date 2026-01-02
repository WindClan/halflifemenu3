package windclan.halflifemenu;

import nilloader.api.ClassTransformer;
import windclan.halflifemenu.transformers.LoadingOverlayTransformer;
import windclan.halflifemenu.transformers.PanoramaRendererTransformer;
import windclan.halflifemenu.transformers.TitleScreenTransformer;

public class main implements Runnable {
	@Override
	public void run() {
		ClassTransformer.register(new PanoramaRendererTransformer());
		ClassTransformer.register(new TitleScreenTransformer());
		ClassTransformer.register(new LoadingOverlayTransformer());
	}

}

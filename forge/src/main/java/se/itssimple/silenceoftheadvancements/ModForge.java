package se.itssimple.silenceoftheadvancements;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import se.itssimple.silenceoftheadvancements.config.ConfigReloadListener;
import se.itssimple.silenceoftheadvancements.util.Reference;
import net.minecraftforge.fml.common.Mod;

/**
 * The Forge version of the mod
 */
@Mod(Reference.MOD_ID)
public class ModForge {

    /**
     * Registering the mod with ModCommon
     */
	public ModForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::loadComplete);
        MinecraftForge.EVENT_BUS.addListener((AddReloadListenerEvent event) -> event.addListener(new ConfigReloadListener()));
	}

    /**
     * Load completed!
     * @param event -
    */
    private void loadComplete(final FMLLoadCompleteEvent event) {
        ModCommon.init();
    }

}
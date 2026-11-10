package se.itssimple.silenceoftheadvancements;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddServerReloadListenersEvent;
import se.itssimple.silenceoftheadvancements.config.ConfigReloadListener;
import se.itssimple.silenceoftheadvancements.util.Reference;

/**
 * The NeoForge version of the mod
 */
@Mod(Reference.MOD_ID)
public class ModNeoForge {
    /**
     * Default constructor
     *
     * @param eventBus -
     */
    public ModNeoForge(IEventBus eventBus) {
        eventBus.addListener(this::loadComplete);
        NeoForge.EVENT_BUS.addListener((AddServerReloadListenersEvent event) -> event.addListener(ConfigReloadListener.ID, new ConfigReloadListener()));
    }

    /**
     *
     * @param event -
     */
    private void loadComplete(final FMLLoadCompleteEvent event) {
        ModCommon.init();
    }
}

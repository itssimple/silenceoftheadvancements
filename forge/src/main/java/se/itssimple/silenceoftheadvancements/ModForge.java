package se.itssimple.silenceoftheadvancements;

import net.minecraftforge.event.AddReloadListenerEvent;
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
    public ModForge(FMLJavaModLoadingContext modLoadingContext) {
        ModCommon.init();
        AddReloadListenerEvent.BUS.addListener(event -> event.addListener(new ConfigReloadListener()));
    }
}
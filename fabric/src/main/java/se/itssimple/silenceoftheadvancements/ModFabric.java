package se.itssimple.silenceoftheadvancements;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;
import se.itssimple.silenceoftheadvancements.fabric.config.FabricConfig;

/** The Fabric version for the mod */
public class ModFabric implements ModInitializer {

	/** Register the entity load event to fix the goal loading */
	public ModFabric()
	{
	}

	/** Initializer of stuff */
	@Override
	public void onInitialize() {
		ModCommon.init();
		ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new FabricConfig());
	}
}
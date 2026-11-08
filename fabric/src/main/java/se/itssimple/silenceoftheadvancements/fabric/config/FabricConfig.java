package se.itssimple.silenceoftheadvancements.fabric.config;

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import se.itssimple.silenceoftheadvancements.config.ConfigReloadListener;
import se.itssimple.silenceoftheadvancements.util.Reference;

public class FabricConfig extends ConfigReloadListener implements IdentifiableResourceReloadListener {

    @Override
    public ResourceLocation getFabricId() {
        return ResourceLocation.fromNamespaceAndPath(
                Reference.MOD_ID,
                "silenced_advancements"
        );
    }
}

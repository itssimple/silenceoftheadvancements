package se.itssimple.silenceoftheadvancements.fabric.config;

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.Identifier;
import se.itssimple.silenceoftheadvancements.config.ConfigReloadListener;
import se.itssimple.silenceoftheadvancements.util.Reference;

public class FabricConfig extends ConfigReloadListener implements IdentifiableResourceReloadListener {

    @Override
    public Identifier getFabricId() {
        return Identifier.fromNamespaceAndPath(
                Reference.MOD_ID,
                "silenced_advancements"
        );
    }
}

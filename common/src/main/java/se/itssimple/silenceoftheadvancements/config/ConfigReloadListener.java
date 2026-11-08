package se.itssimple.silenceoftheadvancements.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import se.itssimple.silenceoftheadvancements.AdvancementSilencer;
import se.itssimple.silenceoftheadvancements.data.Constants;
import se.itssimple.silenceoftheadvancements.util.Reference;

import java.io.Reader;

public class ConfigReloadListener extends SimplePreparableReloadListener<SilenceConfig> {
    private static final Gson GSON = new GsonBuilder().create();

    private static final ResourceLocation CONFIG_FILE =
            ResourceLocation.fromNamespaceAndPath(
                    Reference.MOD_ID,
                    "silenced_advancements.json"
            );

    @Override
    protected SilenceConfig prepare(
            ResourceManager resourceManager,
            ProfilerFiller profiler
    ) {
        Constants.LOG.info("Loading advancement silence configuration: {}", CONFIG_FILE);

        var resource = resourceManager.getResource(CONFIG_FILE);

        if (resource.isEmpty()) {
            Constants.LOG.warn("Could not find advancement silence configuration: {}", CONFIG_FILE);
            return SilenceConfig.empty();
        }

        try (Reader reader = resource.get().openAsReader()) {
            SilenceConfig config = GSON.fromJson(reader, SilenceConfig.class);

            if (config == null) {
                Constants.LOG.warn("Advancement silence configuration was empty or null");
                return SilenceConfig.empty();
            }

            Constants.LOG.info(
                    "Loaded advancement silence configuration: {} namespaces, {} advancements, {} patterns",
                    config.namespaces().size(),
                    config.advancements().size(),
                    config.patterns().size()
            );

            return config;

        } catch (Exception e) {
            Constants.LOG.error("Failed to load advancement silence configuration", e);
            return SilenceConfig.empty();
        }
    }

    @Override
    protected void apply(SilenceConfig config, ResourceManager manager, ProfilerFiller profiler) {
        AdvancementSilencer.setConfig(config);
    }
}

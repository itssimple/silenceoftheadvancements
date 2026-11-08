package se.itssimple.silenceoftheadvancements;

import net.minecraft.resources.ResourceLocation;
import se.itssimple.silenceoftheadvancements.config.SilenceConfig;
import se.itssimple.silenceoftheadvancements.data.Constants;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class AdvancementSilencer {
    private static volatile CompiledConfig config = CompiledConfig.empty();

    private AdvancementSilencer() {}

    public static void setConfig(SilenceConfig newConfig)
    {
        config = new CompiledConfig(
                Set.copyOf(newConfig.namespaces()),
                Set.copyOf(newConfig.advancements()),
                List.of()
        );

        for(String pattern : newConfig.patterns())
        {
            try {
                Pattern p = Pattern.compile(pattern);
                config.patterns.add(p);
            } catch (PatternSyntaxException pse) {
                Constants.LOG.error("Invalid advancement silence regex '{}': {}", pattern, pse);
            }
        }
    }

    public static boolean isSilenced(ResourceLocation id)
    {
        CompiledConfig current = config;

        Constants.LOG.debug("Checking if {} is silenced", id);

        return current.namespaces().contains(id.getNamespace()) ||
            current.advancements().contains(id.toString()) ||
            current.patterns().stream().anyMatch(pattern -> pattern.matcher(id.toString()).matches());
    }

    private record CompiledConfig(
            Set<String> namespaces,
            Set<String> advancements,
            List<Pattern> patterns
    ) {
        private static CompiledConfig empty() {
            return new CompiledConfig(
                    Set.of(),
                    Set.of(),
                    List.of()
            );
        }
    }
}

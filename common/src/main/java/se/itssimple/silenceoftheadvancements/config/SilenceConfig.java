package se.itssimple.silenceoftheadvancements.config;

import java.util.List;

public record SilenceConfig(
        List<String> namespaces,
        List<String> advancements,
        List<String> patterns
) {
    public SilenceConfig{
        namespaces = namespaces != null ? List.copyOf(namespaces) : List.of();
        advancements = advancements != null ? List.copyOf(advancements) : List.of();
        patterns = patterns != null ? List.copyOf(patterns) : List.of();
    }

    public static SilenceConfig empty() {
        return new SilenceConfig(List.of(), List.of(), List.of());
    }
}

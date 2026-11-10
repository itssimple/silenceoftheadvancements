package se.itssimple.silenceoftheadvancements.mixin;

import com.mojang.logging.LogUtils;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.server.PlayerAdvancements;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import se.itssimple.silenceoftheadvancements.AdvancementSilencer;

import java.util.Optional;
import java.util.function.Consumer;

@Mixin(PlayerAdvancements.class)
public abstract class PlayerAdvancementsMixin {
    private static final Logger LOGGER = LogUtils.getLogger();

    @Redirect(method = "award", at = @At(value = "INVOKE", target= "Ljava/util/Optional;ifPresent(Ljava/util/function/Consumer;)V"), remap = false)
    private void award(Optional<DisplayInfo> display,
                       Consumer<? super DisplayInfo> consumer,
                       AdvancementHolder advancement,
                       String criterionKey)
    {
        if(!AdvancementSilencer.isSilenced(advancement.id())) {
            display.ifPresent(consumer);
        }
    }
}

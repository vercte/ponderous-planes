package net.vercte.ponderous_planes.mixin;

import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.presets.WorldPresets;
import net.vercte.ponderous_planes.PonderousWorldPresets;
import net.vercte.ponderous_planes.worldgen.PonderflatLevelSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(WorldPresets.class)
public class WorldPresetsMixin {
    @Inject(method = "lambda$fromSettings$0", at = @At("RETURN"), cancellable = true)
    private static void bnb$fromSettings$0(final LevelStem levelStem, final CallbackInfoReturnable<Optional<?>> cir) {
        if (cir.getReturnValue().isEmpty()) {
            if (levelStem.generator() instanceof PonderflatLevelSource) {
                cir.setReturnValue(Optional.of(PonderousWorldPresets.PONDER));
            }
        }
    }
}

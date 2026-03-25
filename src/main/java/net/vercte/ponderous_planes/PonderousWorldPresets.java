package net.vercte.ponderous_planes;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.presets.WorldPreset;

public class PonderousWorldPresets {
    public static final ResourceKey<WorldPreset> PONDER = ResourceKey.create(Registries.WORLD_PRESET, PonderousPlanes.at("ponderous_planes"));
}

package net.vercte.ponderous_planes.client;

import net.minecraft.client.gui.screens.worldselection.WorldCreationContext;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterPresetEditorsEvent;
import net.vercte.ponderous_planes.PonderousPlanes;
import net.vercte.ponderous_planes.PonderousWorldPresets;
import net.vercte.ponderous_planes.worldgen.PonderflatEditor;
import net.vercte.ponderous_planes.worldgen.PonderflatGeneratorSettings;
import net.vercte.ponderous_planes.worldgen.PonderflatLevelSource;

@Mod(value = PonderousPlanes.ID, dist = Dist.CLIENT)
public class PonderousPlanesClient {
    public PonderousPlanesClient(IEventBus modEventBus) {
        modEventBus.addListener(PonderousPlanesClient::registerPresetEditors);
    }

    public static void registerPresetEditors(final RegisterPresetEditorsEvent event) {
        event.register(PonderousWorldPresets.PONDER,
                (lastScreen, context) -> new PonderflatEditor(
                        lastScreen, context,
                        settings -> lastScreen.getUiState().updateDimensions(ponderflatWorldConfigurator(settings))
                )
        );
    }

    private static WorldCreationContext.DimensionsUpdater ponderflatWorldConfigurator(final PonderflatGeneratorSettings settings) {
        return (p_255454_, p_255455_) -> {
            final Holder.Reference<Biome> voidBiome = p_255454_.registryOrThrow(Registries.BIOME).getHolderOrThrow(Biomes.THE_VOID);
            final ChunkGenerator chunkgenerator = new PonderflatLevelSource(voidBiome, settings);
            return p_255455_.replaceOverworldGenerator(p_255454_, chunkgenerator);
        };
    }
}

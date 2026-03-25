package net.vercte.ponderous_planes;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.common.Mod;

@Mod(PonderousPlanes.ID)
public class PonderousPlanes {
    public static final String ID = "ponderous_planes";

    public static ResourceLocation at(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }
}

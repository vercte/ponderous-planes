# Ponderous Planes
A quick ponder world dev dependency for convenience. Doesn't rely on Create or Ponder.  
Code ripped DIRECTLY and COMPLETELY from https://github.com/aztech-modding/create-bits-n-bobs

## Depending
It should be fine to just use runtimeOnly to have this in your development environment.  
You can also download from [GitHub Releases](https://github.com/vercte/ponderous-planes/releases)
and put it into your `run/mods` folder.

You can get `${ponderous_planes_version}` from the GitHub Releases.
```
repositories {
    maven { url = "https://maven.vercte.net/releases" } // Vercte's Maven (Ponderous Planes)
}

dependencies {
    runtimeOnly "net.vercte.ponderous_planes:ponderous_planes-mc${minecraft-version}:${ponderous_planes_version}"
}
```
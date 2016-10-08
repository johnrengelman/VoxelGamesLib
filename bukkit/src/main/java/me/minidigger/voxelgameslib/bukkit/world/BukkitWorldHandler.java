package me.minidigger.voxelgameslib.bukkit.world;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import me.MiniDigger.VoxelGamesLib.api.map.Map;
import me.MiniDigger.VoxelGamesLib.api.world.WorldHandler;

/**
 * Created by Martin on 07.10.2016.
 */
public class BukkitWorldHandler extends WorldHandler {

    @Override
    public void loadWorld(Map map) {
        super.loadWorld(map);

        WorldCreator wc = new WorldCreator(map.getWorldName());
        wc.environment(World.Environment.NORMAL); //TODO do we need support for environment in maps?
        wc.generateStructures(false);
        wc.type(WorldType.NORMAL);
        wc.generator(new CleanRoomChunkGenerator());
        wc.generatorSettings("");
        wc.createWorld();
    }

    @Override
    public void unloadWorld(Map map) {
        Bukkit.unloadWorld(map.getWorldName(), false);
        super.unloadWorld(map);
    }
}
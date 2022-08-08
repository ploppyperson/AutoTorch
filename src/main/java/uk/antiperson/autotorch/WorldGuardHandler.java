package uk.antiperson.autotorch;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class WorldGuardHandler {

    public boolean canPlaceTorch(Player player, Block block) {
        RegionContainer rc = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery regionQuery = rc.createQuery();
        return regionQuery.testState(BukkitAdapter.adapt(block.getLocation()), WorldGuardPlugin.inst().wrapPlayer(player), Flags.BLOCK_PLACE);
    }
}

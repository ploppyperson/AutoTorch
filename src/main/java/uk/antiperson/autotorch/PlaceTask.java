package uk.antiperson.autotorch;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlaceTask extends BukkitRunnable {

    private final AutoTorch autoTorch;

    public PlaceTask(AutoTorch autoTorch) {
        this.autoTorch = autoTorch;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            TorchPlacer torchPlacer = autoTorch.getPlacerManager().getTorchPlacer(player);
            torchPlacer.placeTorch();
        }
    }
}

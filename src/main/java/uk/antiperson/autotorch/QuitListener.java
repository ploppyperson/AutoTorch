package uk.antiperson.autotorch;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    private final AutoTorch autoTorch;
    public QuitListener(AutoTorch autoTorch) {
        this.autoTorch = autoTorch;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        TorchPlacer placer = autoTorch.getPlacerManager().getTorchPlacer(event.getPlayer());
        if (placer == null) return;
        autoTorch.getPlacerManager().remove(placer);
    }
}

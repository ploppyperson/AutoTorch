package uk.antiperson.autotorch;

import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class PlacerManager {

    private final HashMap<UUID, TorchPlacer> placer;
    private final AutoTorch autoTorch;

    public PlacerManager(AutoTorch autoTorch) {
        this.autoTorch = autoTorch;
        this.placer = new HashMap<>();
    }

    public TorchPlacer getTorchPlacer(Player player) {
        TorchPlacer torchPlacer = placer.get(player.getUniqueId());
        if (torchPlacer == null) {
            torchPlacer = new TorchPlacer(autoTorch, player);
            addTorchPlacer(torchPlacer);
        }
        return torchPlacer;
    }

    public void addTorchPlacer(TorchPlacer torchPlacer) {
        placer.put(torchPlacer.getPlayer().getUniqueId(), torchPlacer);
    }

    public void remove(TorchPlacer torchPlacer) {
        TorchPlacer tp = placer.remove(torchPlacer.getPlayer().getUniqueId());
        try {
            tp.getPlayerConfig().save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

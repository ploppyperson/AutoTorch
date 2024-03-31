package uk.antiperson.autotorch;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class AdminCommand implements CommandExecutor {

    private final AutoTorch autoTorch;

    public AdminCommand(AutoTorch autoTorch) {
        this.autoTorch = autoTorch;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("autotorch.admin")) {
            sender.sendMessage("No permission");
            return false;
        }
        sender.sendMessage("AutoTorch v" + autoTorch.getDescription().getVersion() + " by antiPerson");
        sender.sendMessage("Discord: https://discord.gg/GadyA9j");
        sender.sendMessage("Bugs? Report on GitHub: https://github.com/Nathat23/AutoTorch");
        return false;
    }
}

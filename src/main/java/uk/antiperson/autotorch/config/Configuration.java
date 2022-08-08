package uk.antiperson.autotorch.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import uk.antiperson.autotorch.AutoTorch;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public abstract class Configuration {

    private FileConfiguration fileConfiguration;
    private final File file;
    private final AutoTorch autoTorch;
    private Map<String, Class<? extends Enum<?>>> enumRegistry;

    public Configuration(AutoTorch autoTorch, String fileName) {
        this.autoTorch = autoTorch;
        this.file = new File(autoTorch.getDataFolder(), fileName);
        this.enumRegistry = new HashMap<>();
    }

    public FileConfiguration getFileConfiguration() {
        if (fileConfiguration == null) {
            throw new RuntimeException("Config file " + file.getName() + " is not initialised!");
        }
        return fileConfiguration;
    }

    public File getFile() {
        return file;
    }

    public void init() throws IOException {
        if (!file.exists()) {
            copyConfig();
            autoTorch.getLogger().info("Created config file " + file.getName());
        }
        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    private void copyConfig() throws IOException {
        InputStream inputStream = autoTorch.getResource(file.getName());
        if (inputStream == null) {
            throw new RuntimeException("Cannot load " + getFile().getName() + " in jar");
        }
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            Files.createDirectories(parentFile.toPath());
        }
        Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    <T extends Enum<T>> void addToEnumRegistry(String path, Class<T> clazz) {
        enumRegistry.put(path, clazz);
    }

    public Class<? extends Enum<?>> getFromEnumRegistry(String path) {
        return enumRegistry.get(path);
    }
}

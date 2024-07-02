package uk.antiperson.autotorch.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import uk.antiperson.autotorch.AutoTorch;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public abstract class Configuration {

    private FileConfiguration fileConfiguration;
    private final File file;
    private final AutoTorch autoTorch;
    private final Map<String, ConfigItem> configRegistry;
    private final boolean fromJar;

    public Configuration(AutoTorch autoTorch, String fileName, boolean fromJar) {
        this.autoTorch = autoTorch;
        this.file = new File(autoTorch.getDataFolder(), fileName);
        this.configRegistry = new HashMap<>();
        this.fromJar = fromJar;
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
            createFile();
            autoTorch.getLogger().info("Created config file " + file.getName());
        }
        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (fromJar) {
            updateDefaults(loadDefaults());
        }
    }

    void updateDefaults(Configuration configuration) throws IOException {
        updateDefaults(configuration.getFileConfiguration());
    }

    void updateDefaults(FileConfiguration configuration) throws IOException {
        boolean update = false;
        for (String path : configuration.getKeys(true)) {
            if (getFileConfiguration().isSet(path)) {
                continue;
            }
            getFileConfiguration().set(path, configuration.get(path));
            getFileConfiguration().setComments(path, configuration.getComments(path));
            update = true;
        }
        if (update) {
            save();
            autoTorch.getLogger().info("Updated config " + file.getPath() + " with new values");
        }
    }

    FileConfiguration loadDefaults() {
        InputStream inputStream = autoTorch.getResource(file.getName());
        if (inputStream == null) {
            throw new RuntimeException("Cannot load " + getFile().getName() + " in jar");
        }
        return YamlConfiguration.loadConfiguration(new InputStreamReader(inputStream));
    }

    void createFile() throws IOException {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            Files.createDirectories(parentFile.toPath());
        }
        if (!fromJar) {
            Files.createFile(file.toPath());
            return;
        }
        InputStream inputStream = autoTorch.getResource(file.getName());
        if (inputStream == null) {
            throw new RuntimeException("Cannot load " + getFile().getName() + " in jar");
        }
        Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public void save() throws IOException {
        getFileConfiguration().save(file);
    }

    public void addToConfigRegistry(ConfigItem item) {
        configRegistry.put(item.getPath(), item);
    }

    public ConfigItem getFromConfigRegistry(String path) {
        return configRegistry.get(path);
    }

}

package uk.antiperson.autotorch.config;

import uk.antiperson.autotorch.AutoTorch;

import java.io.IOException;

public class PlayerTranslateConfig extends Configuration {

    public PlayerTranslateConfig(AutoTorch autoTorch) {
        super(autoTorch, "player-translate.yml");
    }

    @Override
    public void init() throws IOException {
        super.init(true);
    }
}

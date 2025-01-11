package dev.siebrenvde.staffchat.config;

import com.electronwill.nightconfig.core.serde.ObjectDeserializer;
import com.electronwill.nightconfig.core.serde.annotations.SerdeKey;
import dev.siebrenvde.staffchat.api.config.BaseConfig;

import java.nio.file.Path;

public class MainConfig extends BaseConfig {

    public static MainConfig load(Path path) {
        return ObjectDeserializer.standard().deserializeFields(load(path, "config.toml"), MainConfig::new);
    }

    @SerdeKey("config_version")
    public int configVersion;

    @SerdeKey("staff_channel")
    public String staffChannel;

    @SerdeKey("verbose_logging")
    public boolean verboseLogging;

}

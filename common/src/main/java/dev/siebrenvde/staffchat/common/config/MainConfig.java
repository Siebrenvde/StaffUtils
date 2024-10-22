package dev.siebrenvde.staffchat.common.config;

import com.electronwill.nightconfig.core.serde.ObjectDeserializer;
import com.electronwill.nightconfig.core.serde.annotations.SerdeKey;

import java.nio.file.Path;

public class MainConfig extends BaseConfig {

    public static MainConfig load(Path path) {
        return ObjectDeserializer.standard().deserializeFields(load(path, "config.toml"), MainConfig::new);
    }

    @SerdeKey("config_version")
    public int configVersion;

    @SerdeKey("staff_channel")
    public String staffChannel;

}

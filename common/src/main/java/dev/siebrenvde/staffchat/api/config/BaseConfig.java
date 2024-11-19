package dev.siebrenvde.staffchat.api.config;

import com.electronwill.nightconfig.core.file.FileConfig;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class BaseConfig {

    public static FileConfig load(Path path, String fileName) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Path file = path.resolve(fileName);

        if(Files.notExists(file)) {
            try (InputStream in = BaseConfig.class.getClassLoader().getResourceAsStream(fileName)) {
                if(in != null) Files.copy(in, file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        FileConfig fileConfig = FileConfig.of(file);
        fileConfig.load();

        return fileConfig;
    }

}

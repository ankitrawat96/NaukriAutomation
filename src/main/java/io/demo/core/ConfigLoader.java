package io.demo.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.InputStream;

public class ConfigLoader {
    private static Config instance;

    public static Config load() {
        if (instance == null) {
            try (InputStream is = ConfigLoader.class.getClassLoader().getResourceAsStream("config.yaml")) {
                ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                instance = mapper.readValue(is, Config.class);
            } catch (Exception e) {
                throw new RuntimeException("Failed to load config.yaml", e);
            }
        }
        return instance;
    }
}

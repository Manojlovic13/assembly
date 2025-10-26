package com.assembly.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Returns full content from 'application.config' as String value.
 */
public final class ConfigProvider02 {
    private static final String DEFAULT_CONFIG_FILE = "application.config";
    private static final Path[] CONFIG_SEARCH_PATHS = {
        Path.of("../configuration"),
        Path.of("configuration")
    };
    
    private final Map<String, String> fileCache = new HashMap<>();
    
    public Optional<String> readConfigFile(String fileName) {
        return Optional.ofNullable(fileCache.computeIfAbsent(fileName, this::loadFileContent));
    }
    
    public Optional<String> readExampleConfig() {
        return readConfigFile(DEFAULT_CONFIG_FILE);
    }
    
    private String loadFileContent(String fileName) {
        for (Path searchPath : CONFIG_SEARCH_PATHS) {
            Path configFilePath = searchPath.resolve(fileName);
            
            if (Files.exists(configFilePath) && Files.isReadable(configFilePath)) {
                try {
                    return Files.readString(configFilePath, StandardCharsets.UTF_8);
                } catch (IOException e) {
                    throw new ConfigurationException("Failed to load configuration from: " + fileName, e);
                }
            }
        }
        return null;
    }
}

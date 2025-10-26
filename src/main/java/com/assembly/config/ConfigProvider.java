package com.assembly.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public final class ConfigProvider {
    private static final String CONFIG_FILE_NAME = "application.config";
    private static final String EXAMPLE_CONFIG_KEY = "this.is.example.config";
    
    private static final Path[] CONFIG_SEARCH_PATHS = {
        Path.of("../configuration/" + CONFIG_FILE_NAME),
        Path.of("configuration/" + CONFIG_FILE_NAME)
    };
    
    private static ConfigProvider instance;
    
    private final Map<String, String> configCache = new HashMap<>();
    private final Properties properties;
    
    private ConfigProvider() throws ConfigurationException {
        this.properties = loadConfiguration();
        initializeCache();
    }

    public static ConfigProvider getInstance() throws ConfigurationException {
        if (instance == null) {
            instance = new ConfigProvider();
        }
        return instance;
    }
    
    public Optional<String> getExampleConfig() {
        return getProperty(EXAMPLE_CONFIG_KEY);
    }
    
    private Properties loadConfiguration() throws ConfigurationException {
        Properties props = new Properties();
        
        for (Path configPath : CONFIG_SEARCH_PATHS) {
            if (Files.exists(configPath)) {
                try (InputStream inputStream = Files.newInputStream(configPath)) {
                    props.load(inputStream);
                    return props;
                } catch (IOException e) {
                    throw new ConfigurationException("Failed to load configuration from: " + configPath, e);
                }
            }
        }
        
        throw new ConfigurationException("No configuration file found in any of the search paths");
    }
    
    private void initializeCache() {
        properties.stringPropertyNames().forEach(key -> {
            String value = properties.getProperty(key);
            if (value != null) {
                configCache.put(key, value.strip());
            }
        });
    }

    private Optional<String> getProperty(String key) {
        Objects.requireNonNull(key, "Configuration key cannot be null");
        return Optional.ofNullable(configCache.get(key));
    }

    private String getProperty(String key, String defaultValue) {
        return getProperty(key).orElse(defaultValue);
    }
}

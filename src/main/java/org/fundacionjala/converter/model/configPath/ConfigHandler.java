package org.fundacionjala.converter.model.configPath;

import java.io.IOException;
import java.util.Properties;

final class ConfigHandler {
    private static ConfigHandler configPath = null;
    private static Properties properties;
    private static final String CONFIG_FILE_WINDOWS = "application.properties";
    private static final String CONFIG_FILE_LINUX = "linux.application.properties";
    private ConfigHandler() {
        try {
            this.properties = new Properties();
            if ((System.getProperty("os.name").toLowerCase()).contains("win")) {
                this.properties.load(getClass().getClassLoader().getResourceAsStream(CONFIG_FILE_WINDOWS));
            } else {
                this.properties.load(getClass().getClassLoader().getResourceAsStream(CONFIG_FILE_LINUX));
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ConfigHandler getConfigPathInstance() {
        if (configPath == null) {
            configPath = new ConfigHandler();
        }
        return configPath;
    }

    public String getValueAsString(final String key) {
        return properties.getProperty(key);
    }
}

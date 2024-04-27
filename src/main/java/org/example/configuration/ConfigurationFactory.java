package org.example.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

public class ConfigurationFactory {
    public static ObjectMapper objectMapper;
    public static ObjectMapper initializeObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    public static Configuration configuration;
    public static void initializeConfiguration() {
        try {
            Yaml yaml = new Yaml();
            objectMapper = initializeObjectMapper();
            FileInputStream inputStream = new FileInputStream("src/main/resources/application.yml");
            Map<String, Object> obj = yaml.load(inputStream);
            configuration = objectMapper.convertValue(obj, Configuration.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

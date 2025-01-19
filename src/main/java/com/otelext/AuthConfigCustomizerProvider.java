package com.otelext;

import com.google.auto.service.AutoService;
import io.opentelemetry.sdk.autoconfigure.spi.AutoConfigurationCustomizer;
import io.opentelemetry.sdk.autoconfigure.spi.AutoConfigurationCustomizerProvider;
import java.util.HashMap;
import java.util.Map;

@AutoService(AutoConfigurationCustomizerProvider.class)
public class AuthConfigCustomizerProvider implements AutoConfigurationCustomizerProvider {

    private String apiToken = "123123132123";

    @Override
    public void customize(AutoConfigurationCustomizer autoConfiguration) {
        System.out.println("-------------------------- invoking the customize() -----------------------------------");
        autoConfiguration.addPropertiesSupplier(this::getDefaultProperties);
    }

    private Map<String, String> getDefaultProperties() {

        for (Object k : System.getProperties().keySet()) {
            String value = System.getProperty(k.toString());
            System.out.println(String.format("%s = %s", k.toString(), value));
        }

        Map<String, String> properties = new HashMap<>();
        properties.put("otel.exporter.otlp.headers", "x-ibm-client-id=" + apiToken);
        return properties;
    }
}

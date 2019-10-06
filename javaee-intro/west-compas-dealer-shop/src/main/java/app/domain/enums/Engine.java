package app.domain.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

public enum Engine {

    GASOLINE, DIESEL;

    public static Engine fromString(String string) {
        return Stream.of(Engine.values())
                .filter(t -> t.name().equalsIgnoreCase(string))
                .findAny()
                .orElse(null);
    }

    @Converter
    public static class EngineConverter implements AttributeConverter<Engine, String> {
        @Override
        public String convertToDatabaseColumn(Engine engine) {
            return engine == null ? null : engine.toString().toUpperCase();
        }

        @Override
        public Engine convertToEntityAttribute(String dbValue) {
            return Engine.fromString(dbValue);
        }
    }
}

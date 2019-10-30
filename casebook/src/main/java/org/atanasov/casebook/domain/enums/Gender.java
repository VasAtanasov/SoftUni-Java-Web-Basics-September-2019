package org.atanasov.casebook.domain.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

public enum Gender {
    MALE, FEMALE;

    public static Gender fromString(String string) {
        return Stream.of(Gender.values())
                .filter(t -> t.name().equalsIgnoreCase(string))
                .findAny()
                .orElse(null);
    }

    @Converter
    public static class GenderConverter implements AttributeConverter<Gender, String> {
        @Override
        public String convertToDatabaseColumn(Gender engine) {
            return engine == null ? null : engine.toString().toUpperCase();
        }

        @Override
        public Gender convertToEntityAttribute(String dbValue) {
            return Gender.fromString(dbValue);
        }
    }

    public String getLabel() {
        return this.name().substring(0, 1) + this.name().substring(1).toLowerCase();
    }

    public String getImageFileName() {
        return this.name().toLowerCase() + ".png";
    }
}

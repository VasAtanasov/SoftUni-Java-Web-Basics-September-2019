package org.atanasov.sboj.domain.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

public enum Sector {

    MEDICINE,
    CAR,
    FOOD,
    DOMESTIC,
    SECURITY;

    public static Sector fromString(String string) {
        return Stream.of(Sector.values())
                .filter(t -> t.name().equalsIgnoreCase(string))
                .findAny()
                .orElse(null);
    }

    @Converter
    public static class SectorConverter implements AttributeConverter<Sector, String> {
        @Override
        public String convertToDatabaseColumn(Sector engine) {
            return engine == null ? null : engine.toString().toUpperCase();
        }

        @Override
        public Sector convertToEntityAttribute(String dbValue) {
            return Sector.fromString(dbValue);
        }
    }

    public String getLabel() {
        return this.name().substring(0, 1) + this.name().substring(1).toLowerCase();
    }

    public String getImageFileName() {
        return this.name().toLowerCase() + ".jpg";
    }
}

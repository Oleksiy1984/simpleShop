package com.alex.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DefaultConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute.equals("")) {
            return "/resources/image/default.jpg";
        } else {
            return attribute;
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}

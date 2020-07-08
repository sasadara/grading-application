package com.sasadara.gradingapplication.dbadapterjpa.question;

import com.sasadara.gradingapplication.entities.question.Results;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ResultsConverter implements AttributeConverter<Results, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Results results) {
        if (results == null) {
            return null;
        }
        return results.getValue();
    }

    @Override
    public Results convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }

        return Stream.of(Results.values())
                .filter(results -> results.getValue() == value)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

package com.movie.booking.util;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

public class LocalDateTimeListConverter implements AttributeConverter<List<Timestamp>, String> {

	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	
	private static final TypeReference<List<Timestamp>> ROLE_SET_TYPE = new TypeReference<List<Timestamp>>() {
    };
	
    @SneakyThrows
	@Override
	public String convertToDatabaseColumn(List<Timestamp> attribute) {
		return JSON_MAPPER.writeValueAsString(attribute);
	}

	@Override
	@SneakyThrows
	public List<Timestamp> convertToEntityAttribute(String dbData) {
		return JSON_MAPPER.readValue(dbData, ROLE_SET_TYPE);
	}

}

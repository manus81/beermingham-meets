package com.beermingham.meets.infrastructure.client;

import java.io.InputStream;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Deprecated
public class MockUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(MockUtils.class);
	private static final ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
	}

	public static <T> T mapObjectfromFile(String file, Class<T> container) {
		try {
			T object = mapper.readValue(getFile(file), container);
			return object;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	private static InputStream getFile(String file) throws URISyntaxException {
		return MockUtils.class.getClassLoader().getResourceAsStream(file);
	}

}

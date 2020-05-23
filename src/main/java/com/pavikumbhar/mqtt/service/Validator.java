package com.pavikumbhar.mqtt.service;

import static org.springframework.util.StringUtils.isEmpty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.pavikumbhar.mqtt.exception.BussinessException;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Validator {

	public <T> T requireNonNull(T entity, String message) {

		if (entity == null) {
			throw new BussinessException(message);
		} else if (entity instanceof Collection) {
			if (CollectionUtils.isEmpty((Collection<?>) entity)) {
				throw new BussinessException(message);
			}
		} else if (entity instanceof String) {
			if (isEmpty(entity)) {
				throw new BussinessException(message);
			}
		} else if (entity instanceof Optional) {
			if (!((Optional<?>) entity).isPresent()) {
				throw new BussinessException(message);
			}
		} else if (entity instanceof Long && ((Long) entity) <= 0) {
			throw new BussinessException(message);
		} else if (entity instanceof Integer && ((Integer) entity) <= 0) {
			throw new BussinessException(message);
		}

		return entity;
	}

	public <T> void requireNull(T entity, String message) {
		if (entity instanceof Optional) {
			if (((Optional<?>) entity).isPresent()) {
				throw new BussinessException(message);
			}
		} else if (entity instanceof Collection) {
			if (!CollectionUtils.isEmpty((Collection<?>) entity)) {
				throw new BussinessException(message);
			}
		} else if (entity instanceof String) {
			if (!isEmpty(entity)) {
				throw new BussinessException(message);
			}
		} else if (entity != null) {
			throw new BussinessException(message);
		}
	}

	public <T> T validateAgainstNotEquals(T expectedValue, T valueValidatedAgainst, String message) {
		if (!expectedValue.equals(valueValidatedAgainst)) {
			throw new BussinessException(message);
		}
		return valueValidatedAgainst;
	}

	public <T> T validateAgainstEquals(T expectedValue, T valueValidatedAgainst, String message) {
		if (valueValidatedAgainst.equals(expectedValue)) {
			throw new BussinessException(message);
		}
		return valueValidatedAgainst;
	}

	public void validateInRange(int lowerValue, int upperValue, int entityValue, String message) {
		if (!(lowerValue <= entityValue && entityValue <= upperValue)) {
			throw new BussinessException(message);
		}
	}

	public void validateInRange(long lowerValue, long upperValue, long entityValue, String message) {
		if (!(lowerValue <= entityValue && entityValue <= upperValue)) {
			throw new BussinessException(message);
		}
	}

	public void validateAnyMatch(String entityValue, String[] valueValidatedAgainst, String message) {
		List<String> list = Arrays.stream(valueValidatedAgainst).collect(Collectors.toCollection(ArrayList::new));
		if (list.stream().anyMatch(value -> value.equals(entityValue))) {
			throw new BussinessException(message);
		}
	}

	public void validateNoneMatch(String entityValue, String[] valueValidatedAgainst, String message) {
		List<String> list = Arrays.stream(valueValidatedAgainst).collect(Collectors.toCollection(ArrayList::new));
		if (list.stream().noneMatch(value -> value.equals(entityValue))) {
			throw new BussinessException(message);
		}
	}
}
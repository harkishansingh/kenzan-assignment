package com.kenzan.utility;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.json.JSONObject;

import com.kenzan.model.FizzBuzz;
import com.kenzan.model.FizzBuzzType;

public class Utils {

	public static <T> Function<Object, Object> overWriter(int periodicity, T type) {
		return value -> (((FizzBuzz) value).getInteger() % periodicity) == 0
				? new FizzBuzz(((FizzBuzz) value).getInteger(), (FizzBuzzType) type) : (FizzBuzz) value;

	}

	public static JSONObject convertMapToJSONObject(Map<FizzBuzzType, List<Integer>> map) {
		return new JSONObject(map);
	}
}

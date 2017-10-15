package com.kenzan.handler;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.exception.NotANumberException;
import org.json.JSONObject;

import com.google.inject.Inject;
import com.kenzan.model.FizzBuzzType;
import com.kenzan.service.IFizzBuzzService;
import com.kenzan.utility.Utils;

public class FizzBuzzHandler implements IFizzBuzzHandler {
	@Inject
	private IFizzBuzzService iFizzBuzzService;

	@Override
	public JSONObject getFizzBuzzCombinations(String number) {
		if (!StringUtils.isNumeric(number)) {
			throw new NotANumberException();
		}

		Map<FizzBuzzType, List<Integer>> map = iFizzBuzzService.computeFizzBuzzCombinations(number);

		return Utils.convertMapToJSONObject(map);
	}

}

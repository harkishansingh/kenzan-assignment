package com.kenzan.service;

import java.util.List;
import java.util.Map;


import com.kenzan.model.FizzBuzzType;

public interface IFizzBuzzService {
	Map<FizzBuzzType, List<Integer>> computeFizzBuzzCombinations(String number);
}

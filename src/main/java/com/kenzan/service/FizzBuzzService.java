package com.kenzan.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.kenzan.model.FizzBuzz;
import com.kenzan.model.FizzBuzzType;
import com.kenzan.utility.Utils;

public class FizzBuzzService implements IFizzBuzzService {

	@Override
	public Map<FizzBuzzType, List<Integer>> computeFizzBuzzCombinations(String number) {

		Stream<FizzBuzz> ints = IntStream.range(1, Integer.parseInt(number) + 1)
				.mapToObj(d -> new FizzBuzz(d, FizzBuzzType.OTHER));
		
		Function<Object, Object> fizzBuzzOverWriter = Utils.overWriter(3, FizzBuzzType.FIZZ)
				.andThen(Utils.overWriter(5, FizzBuzzType.BUZZ)).andThen(Utils.overWriter(15, FizzBuzzType.FIZZBUZZ));


		Map<FizzBuzzType, List<Integer>> map = ints.map(fizzBuzzOverWriter).map(d -> (FizzBuzz) d)
				.filter(d -> !FizzBuzzType.OTHER.equals(d.getType())).collect(Collectors.groupingBy(FizzBuzz::getType,
						Collectors.mapping(FizzBuzz::getInteger, Collectors.toList())));
		return map;
	}

}

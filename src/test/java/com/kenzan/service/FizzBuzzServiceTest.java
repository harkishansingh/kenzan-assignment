package com.kenzan.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.collection.IsMapContaining;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.kenzan.model.FizzBuzzType;

public class FizzBuzzServiceTest {
	
	private IFizzBuzzService iFizzBuzzService = new FizzBuzzService();

	@Test
	public void computeFizzBuzzCombinationsTest() {
		Map<FizzBuzzType, List<Integer>> expectedMap = new HashMap<>();
		List<Integer> list1 = Arrays.asList(3,6,9,12);
		List<Integer> list2 = Arrays.asList(5,10);
		List<Integer> list3 = Arrays.asList(15);
		expectedMap.put(FizzBuzzType.FIZZ, list1);
		expectedMap.put(FizzBuzzType.BUZZ, list2);
		expectedMap.put(FizzBuzzType.FIZZBUZZ, list3);
		
		Map<FizzBuzzType, List<Integer>> map = iFizzBuzzService.computeFizzBuzzCombinations("15");
		Assert.assertNotNull(map);
		
		 //1. Test equal, ignore order
        assertThat(map, is(expectedMap));

        //2. Test size
        assertThat(map.size(), is(3));

        //3. Test map entry, best!
        assertThat(map, IsMapContaining.hasEntry(FizzBuzzType.FIZZ, list1));
        assertThat(map, IsMapContaining.hasEntry(FizzBuzzType.BUZZ, list2));
        assertThat(map, IsMapContaining.hasEntry(FizzBuzzType.FIZZBUZZ, list3));

        assertThat(map, not(IsMapContaining.hasEntry(FizzBuzzType.FIZZBUZZ, list1)));

        //4. Test map key
        assertThat(map, IsMapContaining.hasKey(FizzBuzzType.FIZZ));
        assertThat(map, IsMapContaining.hasKey(FizzBuzzType.BUZZ));
        assertThat(map, IsMapContaining.hasKey(FizzBuzzType.FIZZBUZZ));

        //5. Test map value
        assertThat(map, IsMapContaining.hasValue(list1));
        assertThat(map, IsMapContaining.hasValue(list2));
        assertThat(map, IsMapContaining.hasValue(list3));
	}
}

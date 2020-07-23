package com.linus.test.util;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import junit.framework.Assert;

public class OptionalTest {

	@Test
	public void givenOptional_whenMapWorks_thenCorrect() {
	    List<String> companyNames = Arrays.asList(
	      "paypal", "oracle", "", "microsoft", "", "apple");
	    Optional<List<String>> listOptional = Optional.of(companyNames);
	 
	    int size = listOptional
	      .map(List::size)
	      .orElse(0);
	    Assert.assertEquals(6, size);
	}
}

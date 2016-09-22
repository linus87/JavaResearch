package com.linus.pojo;

import java.util.Set;

public class City<T> extends Division{
	private Set<? extends County> counties;
}

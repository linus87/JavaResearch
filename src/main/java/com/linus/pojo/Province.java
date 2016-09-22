package com.linus.pojo;

import java.util.Set;

public class Province<T> extends Division{
	protected Set<? extends City> cities;
	
	
	public Set<? extends City> getCities() {
		return cities;
	}

	public void setCities(Set<? extends City> cities) {
		this.cities = cities;
	}

}

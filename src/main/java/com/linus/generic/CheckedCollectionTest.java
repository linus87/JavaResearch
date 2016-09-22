package com.linus.generic;

import java.lang.reflect.TypeVariable;
import java.util.HashSet;
import java.util.Set;

import com.linus.pojo.City;
import com.linus.pojo.Province;
import com.linus.pojo.Region;

public class CheckedCollectionTest {
	
	
	public static void main(String[] args) {
//		nosafeCheck();
		
		Country<Province, Region, City> c = new Country<Province, Region, City>();
		Province p = new Province();
		
		c.addProvince(p);
		
		TypeVariable<Class<Country>>[] t = Country.class.getTypeParameters();
		
		for (int i = 0; i < t.length; i ++) {
			System.out.println(t[i].getName());
			Class<Country> cc = t[i].getGenericDeclaration();
			System.out.println(cc.getName());
		}
		
		System.out.println(t.length);
	}
	
	public static void nosafeCheck() {
		Set strSet = new HashSet();
		strSet.add(new Integer(2));
		strSet.add("hello");
	}
}

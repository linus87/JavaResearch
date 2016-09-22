package com.linus.generic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.linus.pojo.City;
import com.linus.pojo.Division;
import com.linus.pojo.Province;
import com.linus.pojo.Region;

/**
 * 
 * @author lyan2
 *
 * @param <P> Province
 * @param <D> District
 * @param <C> City
 */
public class Country<P extends Province, R extends Region, C extends City> extends Division {
	protected Set<P> provinces = new HashSet<P>();
	protected Set<R> regions = new HashSet<R>();
	protected Set<C> cities = new HashSet<C>();
	
	public boolean addProvince(P p) {
		return provinces.add(p);
	}
	
	public P getProvince(String name) {
		Iterator<P> iter = provinces.iterator();
		while (iter.hasNext()) {
			P p = iter.next();
			if (p.getName().equalsIgnoreCase(name)) {
				return p;
			}
		}
		
		return null;
	}
	
	public R getRegion(String name) {
		Iterator<R> iter = regions.iterator();
		while (iter.hasNext()) {
			R r = iter.next();
			if (r.getName().equalsIgnoreCase(name)) {
				return r;
			}
		}
		
		return null;
	}
	
	public C getMunicipality(String name) {
		Iterator<C> iter = cities.iterator();
		while (iter.hasNext()) {
			C c = iter.next();
			if (c.getName().equalsIgnoreCase(name)) {
				return c;
			}
		}
		
		return null;
	}

	public Set<P> getProvinces() {
		return provinces;
	}

	public void setProvinces(Set<P> provinces) {
		this.provinces = provinces;
	}	
	
	
	
}

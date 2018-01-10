package com.linus.test.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import junit.framework.Assert;

import org.junit.Test;

public class MethodTest {
	
	@Test
	public void testGeneralMethod() throws NoSuchMethodException, SecurityException {
		Method m = User.class.getDeclaredMethod("findUser", String.class);
		Assert.assertEquals("findUser", m.getName());
		
		Assert.assertEquals(User.class, m.getReturnType());
		Assert.assertEquals(User.class, m.getAnnotatedReturnType().getType());
		
		Assert.assertEquals(null, m.getDefaultValue());
	}
	
	@Test
	public void testPrimitiveMethod() throws NoSuchMethodException, SecurityException {
		Method m = User.class.getDeclaredMethod("getAge");
		Assert.assertEquals("getAge", m.getName());
		
		Assert.assertEquals(int.class, m.getReturnType());
		Assert.assertEquals(int.class, m.getAnnotatedReturnType().getType());
		
		Assert.assertEquals(null, m.getDefaultValue());
	}
	
	@Test
	public void testReturnValue() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method m = User.class.getDeclaredMethod("getAge");
		Assert.assertEquals("getAge", m.getName());
		
		Assert.assertEquals(int.class, m.getReturnType());
		Assert.assertEquals(int.class, m.getAnnotatedReturnType().getType());
		
		User u = new User();
		
		Assert.assertEquals(23, (int)m.invoke(u));
		// primitive return value will be wrapped.
		Assert.assertEquals(Integer.class, m.invoke(u).getClass());
	}
	
	public static class User {
		
		public int getAge() {
			return 23;
		}
		
		@SuppressWarnings("unused")
		private User findUser(String userId) {
			User u = new User();
			return u;
		}
		
		@SuppressWarnings("unused")
		private <T> T findSomething(T userId) {
			User u = new User();
			return null;
		}
	}
}

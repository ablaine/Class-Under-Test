package com.ablaine.bad;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ablaine.classundertest.annotation.ClassUnderTest;
import com.ablaine.somepackage.SomeClass;

@ClassUnderTest(value = SomeClass.class, suffix = "IT")
public class SomeClassIT {
	@Test
	public void testApp() {
		assertEquals("Hello World!", SomeClass.someMethod());
	}
}

package com.ablaine.somepackage.subpackage;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ablaine.classundertest.annotations.ClassUnderTest;
import com.ablaine.somepackage.SomeClass;

@ClassUnderTest(value = SomeClass.class, prefix = "subpackage.", suffix = "IT")
public class SomeClassIT {
	@Test
	public void testApp() {
		assertEquals("Hello World!", SomeClass.someMethod());
	}
}

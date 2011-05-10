package com.ablaine.somepackage;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ablaine.classundertest.annotations.ClassUnderTest;

@ClassUnderTest(value = SomeClass.class, prefix = "Prefixed", suffix = "")
public class PrefixedSomeClass {
	@Test
	public void testApp() {
		assertEquals("Hello World!", SomeClass.someMethod());
	}
}

package com.ablaine.somepackage;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ablaine.classundertest.annotations.ClassUnderTest;

@ClassUnderTest(SomeClass.class)
public class BadClassNameTest {
	@Test
	public void testApp() {
		assertEquals("Hello World!", SomeClass.someMethod());
	}
}

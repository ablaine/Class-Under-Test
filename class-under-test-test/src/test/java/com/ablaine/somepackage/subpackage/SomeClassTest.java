package com.ablaine.somepackage.subpackage;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ablaine.classundertest.annotation.ClassUnderTest;
import com.ablaine.somepackage.SomeClass;

@ClassUnderTest(value = SomeClass.class, prefix = "subpackage.")
public class SomeClassTest {
	@Test
	public void testApp() {
		assertEquals("Hello World!", SomeClass.someMethod());
	}
}

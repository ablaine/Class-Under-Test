package com.ablaine.bad;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ablaine.classundertest.annotation.ClassUnderTest;
import com.ablaine.somepackage.SomeClass;

@ClassUnderTest(SomeClass.class)
public class SomeClassTest {
	@Test
	public void testApp() {
		assertEquals("Hello World!", SomeClass.someMethod());
	}
}

Class Under Test
================

Requirements
------------
- Java 6
- Maven (to build)

Installation
------------

1. Checkout the project
2. Run `mvn clean install`

Include in your pom.xml:

	<dependency>
		<groupId>com.ablaine</groupId>
		<artifactId>class-under-test-core</artifactId>
		<version>0.1.0-SNAPSHOT</version>
		<scope>test</scope>
	</dependency>

Usage
-----

Then use the `@ClassUnderTest` annotation in your code:

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

The Java compiler should issue a warning if the test class and the class being tested are out of sync.

	[WARNING] /Users/ablaine/Projects/Eclipse/Other/class-under-test-parent/class-under-test-test/src/test/java/com/ablaine/bad/SomeClassTest.java:[11,7] Expected com.ablaine.somepackage.SomeClassTest, got com.ablaine.bad.SomeClassTest


package com.ablaine.classundertest.internal;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;

import org.junit.Test;

import com.ablaine.classundertest.annotation.ClassUnderTest;

public class TestClassNameTest {
	@Test(expected = NullPointerException.class)
	public void testConstructor_null() {
		new TestClassName(null);
	}

	@Test
	public void testToString_allFields() {
		final TestClassName tcn = new TestClassName(new ClassUnderTestMock(String.class, "SomePrefix", "SomeSuffix"));
		assertEquals("java.lang.SomePrefixStringSomeSuffix", tcn.toString());
	}

	@Test
	public void testToString_justValue() {
		final TestClassName tcn = new TestClassName(new ClassUnderTestMock(String.class, "", ""));
		assertEquals("java.lang.String", tcn.toString());
	}

	@SuppressWarnings("all")
	private static class ClassUnderTestMock implements ClassUnderTest {
		private final Class<?> value;
		private final String prefix;
		private final String suffix;

		public ClassUnderTestMock(Class<?> value, String prefix, String suffix) {
			this.value = value;
			this.prefix = prefix;
			this.suffix = suffix;
		}

		@Override
		public Class<? extends Annotation> annotationType() {
			return null;
		}

		@Override
		public Class<?> value() {
			return value;
		}

		@Override
		public String prefix() {
			return prefix;
		}

		@Override
		public String suffix() {
			return suffix;
		}
	}
}

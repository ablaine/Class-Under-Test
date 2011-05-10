package com.ablaine.classundertest;

import com.ablaine.classundertest.annotations.ClassUnderTest;

public class TestClassName {
	// [package].[prefix][Class][suffix].java
	private final String package_;
	private final String class_;
	private final String prefix;
	private final String suffix;

	public TestClassName(ClassUnderTest cls) {
		this(cls.value().getCanonicalName(), cls.prefix(), cls.suffix());
	}

	private TestClassName(String value, String prefix, String suffix) {
		this.prefix = prefix;
		this.suffix = suffix;

		final int lastDotIndex = value.lastIndexOf('.');
		package_ = value.substring(0, lastDotIndex);
		class_ = value.substring(lastDotIndex + 1, value.length());
	}

	@Override
	public String toString() {
		final String prefix_ = prefix == null ? "" : prefix;
		return package_ + "." + prefix_ + class_ + suffix;
	}
}

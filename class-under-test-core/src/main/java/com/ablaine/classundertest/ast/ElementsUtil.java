package com.ablaine.classundertest.ast;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Map.Entry;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.util.Elements;

public class ElementsUtil {
	private final Elements elements_;

	public static ElementsUtil newInstance(Elements elements) {
		return new ElementsUtil(elements);
	}

	public Object getDefaultValue(AnnotationMirror ann, String method) {
		final Map<? extends ExecutableElement, ? extends AnnotationValue> elemValues = elements_
				.getElementValuesWithDefaults(ann);
		for (Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elemValues.entrySet()) {
			if (entry.getKey().getSimpleName().contentEquals(method)) {
				return entry.getValue().getValue();
			}
		}
		return null;
	}

	public <K> K asProxy(final AnnotationMirror ann) {
		final Element elem = ann.getAnnotationType().asElement();
		final String className = elem.toString();
		final Class<?> cls = forName(className);
		return ElementsUtil.<K> uncheckedCast(Proxy.newProxyInstance(cls.getClassLoader(), new Class[] { cls },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						final Object obj = getDefaultValue(ann, method.getName());
						/*
						 * Strange issue where obj is actually an instance of
						 * com.sun.tools.javac.code.Type$ClassType. Instead, check first to see what
						 * the return type is, if java.lang.Class, get that class using forName.
						 */
						final Class<?> returnCls = method.getReturnType();
						if (Class.class.getName().equals(returnCls.getName())) {
							return forName(obj.toString());
						}
						return obj;
					}
				}));
	}

	private static Class<?> forName(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Error resolving " + className, e);
		}
	}

	@SuppressWarnings("unchecked")
	private static <K> K uncheckedCast(Object obj) {
		return (K) obj;
	}

	private ElementsUtil(Elements elements) {
		this.elements_ = elements;
	}
}

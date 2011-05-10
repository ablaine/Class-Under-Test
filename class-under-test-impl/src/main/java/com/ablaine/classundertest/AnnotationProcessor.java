package com.ablaine.classundertest;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

import com.ablaine.classundertest.annotations.ClassUnderTest;
import com.ablaine.classundertest.ast.ElementsUtil;

@SupportedAnnotationTypes("com.ablaine.classundertest.annotations.ClassUnderTest")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class AnnotationProcessor extends AbstractProcessor {
	private Messager messager;
	private ElementsUtil elementsUtil;

	@Override
	public void init(ProcessingEnvironment env) {
		messager = env.getMessager();
		elementsUtil = ElementsUtil.newInstance(env.getElementUtils());
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		for (TypeElement typeElement : annotations) {
			for (Element element : roundEnv.getElementsAnnotatedWith(typeElement)) {
				processAnnotatedElement(element);
			}
		}
		return true;
	}

	private void processAnnotatedElement(Element element) {
		final String actualClassName = element.asType().toString();
		for (AnnotationMirror annMirror : element.getAnnotationMirrors()) {
			final ClassUnderTest clsUnderTest = elementsUtil.asProxy(annMirror);
			final String expectedClassName = new TestClassName(clsUnderTest).toString();
			if (!actualClassName.equals(expectedClassName)) {
				messager.printMessage(Kind.MANDATORY_WARNING,
						String.format("Expected %s, got %s", expectedClassName, actualClassName), element);
			}
		}
	}
}

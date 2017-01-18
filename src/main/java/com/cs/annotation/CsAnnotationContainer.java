package com.cs.annotation;

import java.lang.annotation.*;

/**
 * Created by admin on 2017/1/18.
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CsAnnotationContainer {
	CsAnnotation[] value();
}

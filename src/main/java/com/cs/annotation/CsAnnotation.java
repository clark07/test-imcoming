package com.cs.annotation;

import java.lang.annotation.*;

/**
 * Created by admin on 2017/1/18.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Documented
@Repeatable(CsAnnotationContainer.class)
public @interface CsAnnotation {
/*	1.所有基本数据类型（int,float,boolean,byte,double,char,long,short)
	2.String类型
3.Class类型
4.enum类型
5.Annotation类型
6.以上所有类型的数组*/
	int value();
	String name() default "name";
	Class cls() default CsAnnotation.class;
	BaseAnnotation baseAnnotation() default @BaseAnnotation;
	AnEnum anEnum() default AnEnum.A;
	int[] array() default {};
}

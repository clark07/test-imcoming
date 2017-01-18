package com.cs.annotation;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by admin on 2017/1/18.
 */

public class AnBean {

	@CsAnnotation(value = 1, name = "normal", array = {1}, baseAnnotation = @BaseAnnotation, anEnum = AnEnum.A,cls = AnBean.class)
	private final static int normal=1;

	@CsAnnotation(value = 2, name = "repeat", array = {1,2}, baseAnnotation = @BaseAnnotation, anEnum = AnEnum.B,cls = AnBean.class)
	@CsAnnotation(value = 3, name = "repeat", array = {1,2}, baseAnnotation = @BaseAnnotation, anEnum = AnEnum.C,cls = AnBean.class)
	private final static int repeat=2;

	public static void main(String[] args) throws Exception{
		{
			System.out.println(String.format("------单个注解------"));
			CsAnnotation annotation = AnBean.class.getDeclaredField("normal").getAnnotation(CsAnnotation.class);

			int value = annotation.value();
			System.out.println(String.format("value-->%s", value));
			String name = annotation.name();
			System.out.println(String.format("name-->%s", name));
			AnEnum anEnum = annotation.anEnum();
			System.out.println(String.format("anEnum-->%s", anEnum.name()));

			BaseAnnotation baseAnnotation = annotation.baseAnnotation();
			System.out.println(String.format("baseAnnotation-->%s", baseAnnotation.annotationType().getName()));
			Class cls = annotation.cls();
			System.out.println(String.format("cls-->%s", cls.getName()));

			int[] array = annotation.array();
			System.out.println(String.format("array-->%s", JSON.toJSONString(array)));
		}

		{
			System.out.println(String.format("------可重复注解------"));
			CsAnnotation[] annotationsByType = AnBean.class.getDeclaredField("repeat").getAnnotationsByType(CsAnnotation.class);
			if (ArrayUtils.isNotEmpty(annotationsByType)) {
				for (int i = 0; i < annotationsByType.length; i++) {
					CsAnnotation annotation = annotationsByType[i];
					System.out.println(String.format("-------------%s-------------", i));

					int value = annotation.value();
					System.out.println(String.format("value-->%s", value));
					String name = annotation.name();
					System.out.println(String.format("name-->%s", name));
					AnEnum anEnum = annotation.anEnum();
					System.out.println(String.format("anEnum-->%s", anEnum.name()));

					BaseAnnotation baseAnnotation = annotation.baseAnnotation();
					System.out.println(String.format("baseAnnotation-->%s", baseAnnotation.annotationType().getName()));
					Class cls = annotation.cls();
					System.out.println(String.format("cls-->%s", cls.getName()));

					int[] array = annotation.array();
					System.out.println(String.format("array-->%s", JSON.toJSONString(array)));
				}
			}
		}
	}
}

package com.artemis.annotations;

import com.artemis.ComponentType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.FIELD )
public @interface Mapper
{
  String classifier() default ComponentType.DEFAULT_CLASSIFIER;
}

package com.tcGroup.trainingCenter.utility.searchCriteria.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.tcGroup.trainingCenter.utility.searchCriteria.enumeration.SearchOperation;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SearchField {
    
    public String field();

    public SearchOperation operation() default SearchOperation.EQUAL;
}
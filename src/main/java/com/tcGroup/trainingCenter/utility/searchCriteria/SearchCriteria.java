package com.tcGroup.trainingCenter.utility.searchCriteria;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.tcGroup.trainingCenter.utility.searchCriteria.annotation.SearchField;
import com.tcGroup.trainingCenter.utility.searchCriteria.enumeration.SearchOperation;

import org.springframework.data.jpa.domain.Specification;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class SearchCriteria<T> implements Specification<T> {

    @SearchField(field = "id")
    private Long id;

    private boolean includeRemoved = false;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object fieldValue = field.get(this);

                if (field.isAnnotationPresent(SearchField.class)) {
                    SearchField searchField = field.getAnnotation(SearchField.class);
                    SearchOperation operation = searchField.operation();
                    String fieldName = searchField.field();
                    
                    if (fieldValue != null) {
                        if (operation.equals(SearchOperation.EQUAL)) {
                            predicates.add(builder.equal(root.get(fieldName), fieldValue));
                        } else if (operation.equals(SearchOperation.GREATER_THAN)) {
                            predicates.add(builder.greaterThan(root.get(fieldName), fieldValue.toString()));
                        } else if (operation.equals(SearchOperation.GREATER_THAN_EQUAL)) {
                            predicates.add(builder.greaterThanOrEqualTo(root.get(fieldName), fieldValue.toString()));
                        } else if (operation.equals(SearchOperation.IN)) {
                            if (fieldValue instanceof List<?>){
                                In<Object> inClause = builder.in(root.get(fieldName));

                                for (Object listedObject : (List<?>) fieldValue) {
                                    inClause.value(listedObject);
                                }

                                predicates.add(inClause);
                            } else {
                                predicates.add(builder.in(root.get(fieldName)).value(fieldValue));
                            }
                        } else if (operation.equals(SearchOperation.LESS_THAN)) {
                            predicates.add(builder.lessThan(root.get(fieldName), fieldValue.toString()));
                        } else if (operation.equals(SearchOperation.LESS_THAN_EQUAL)) {
                            predicates.add(builder.greaterThanOrEqualTo(root.get(fieldName), fieldValue.toString()));
                        } else if (operation.equals(SearchOperation.MATCH)) {
                            predicates.add(builder.like(builder.lower(root.get(fieldName)),
                                    "%" + fieldValue.toString().toLowerCase() + "%"));
                        } else if (operation.equals(SearchOperation.MATCH_END)) {
                            predicates.add(builder.like(builder.lower(root.get(fieldName)),
                                    "%" + fieldValue.toString().toLowerCase()));
                        } else if (operation.equals(SearchOperation.MATCH_START)) {
                            predicates.add(builder.like(builder.lower(root.get(fieldName)),
                                    fieldValue.toString().toLowerCase() + "%"));
                        } else if (operation.equals(SearchOperation.NOT_EQUAL)) {
                            predicates.add(builder.notEqual(root.get(fieldName), fieldValue));
                        } else if (operation.equals(SearchOperation.NOT_IN)) {
                            if (fieldValue instanceof List<?>){
                                In<Object> inClause = builder.in(root.get(fieldName));

                                for (Object listedObject : (List<?>) fieldValue) {
                                    inClause.value(listedObject);
                                }
                                
                                predicates.add(builder.not(inClause));
                            } else {
                                predicates.add(builder.not(root.get(fieldName)).in(fieldValue));
                            }
                        } else if (operation.equals(SearchOperation.IS_NULL)) {
                            predicates.add(builder.isNull(root.get(fieldName)));
                        } else if (operation.equals(SearchOperation.NOT_NULL)) {
                            predicates.add(builder.isNotNull(root.get(fieldName)));
                        }
                    }
                }

                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                field.setAccessible(false);
                e.printStackTrace();
                return null;
            }
        }

        if (!includeRemoved) {
            predicates.add(builder.isNull(root.get("auditRD")));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
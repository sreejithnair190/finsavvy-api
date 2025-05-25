package com.finsavvy.api.finsavvy_api.v1.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

@Slf4j
public class Util {
    private Util() {
        throw new AssertionError("Constants class should not be instantiated");
    }

    public static Pageable generatePageable(
            Integer pageNumber,
            Integer size,
            String sortBy,
            String sortDir,
            String[] sortable
    ){
        Sort.Direction direction = (sortDir.equals("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC;

        String validatedSortBy = sortBy;
        if (sortable != null && sortBy != null) {
            boolean isValidSortBy = Arrays.asList(sortable).contains(sortBy);
            if (!isValidSortBy) {
                log.warn("Invalid sortBy parameter '{}'. Available options: {}. Defaulting to 'createdAt'",
                        sortBy, Arrays.toString(sortable));
                validatedSortBy = "createdAt";
            }
        } else if (sortBy == null) {
            validatedSortBy = "createdAt";
        }

        Sort sort = Sort.by(direction, validatedSortBy, "createdAt");
        pageNumber = (pageNumber != null && pageNumber > 0) ? --pageNumber : 0;

        return PageRequest.of(pageNumber, size, sort);
    }

}

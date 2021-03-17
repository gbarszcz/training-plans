package com.tcGroup.trainingCenter.utility.logic;

import com.tcGroup.trainingCenter.utility.context.UserContext;

import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AbstractService {

    protected UserContext getUserContext() {
        Object userContext = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userContext instanceof UserContext) {
            return (UserContext) userContext;
        }

        return null;
    }
}
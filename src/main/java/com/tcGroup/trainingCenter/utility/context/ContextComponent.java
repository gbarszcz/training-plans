package com.tcGroup.trainingCenter.utility.context;

import org.springframework.security.core.context.SecurityContextHolder;

public abstract class ContextComponent {
    
    protected UserContext getUserContext() {
        Object userContext = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userContext instanceof UserContext) {
            return (UserContext) userContext;
        }

        return null;
    }
}
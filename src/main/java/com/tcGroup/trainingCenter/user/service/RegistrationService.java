package com.tcGroup.trainingCenter.user.service;

import com.tcGroup.trainingCenter.user.request.RegistrationRequest;

public interface RegistrationService {
    
    Long register(RegistrationRequest registrationRequest);
}
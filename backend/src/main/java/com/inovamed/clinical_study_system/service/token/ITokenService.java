package com.inovamed.clinical_study_system.service.token;

import com.inovamed.clinical_study_system.model.user.User;

public interface ITokenService {
    String generateToken(User user);
}

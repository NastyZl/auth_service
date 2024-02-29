package com.example.guide.service;

import com.example.guide.exception.LoginException;
import com.example.guide.exception.RegistrationException;

public interface ClientService {
    void register(String clientId, String clientSecret) throws RegistrationException;
    void checkCredentials(String clientId, String clientSecret) throws LoginException;
}

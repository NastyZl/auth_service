package com.example.guide.service;

import com.example.guide.entity.ClientEntity;
import com.example.guide.exception.LoginException;
import com.example.guide.exception.RegistrationException;
import com.example.guide.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultClientService implements ClientService{
    private final ClientRepository clientRepository;
    @Override
    public void register(String clientId, String clientSecret) throws RegistrationException {
        if (clientRepository.findById(clientId).isPresent()) {
            throw new RegistrationException("Client with id= " + clientId + "already registered");
        }
        String hash = BCrypt.hashpw(clientSecret, BCrypt.gensalt());
        clientRepository.save(new ClientEntity(clientId, hash));
    }

    @Override
    public void checkCredentials(String clientId, String clientSecret) throws LoginException {
        Optional<ClientEntity> optionalClientEntity = clientRepository.findById(clientId);
        if (optionalClientEntity.isEmpty()) {
            throw new LoginException("Client with id= " + clientId + "not found");
        }
        ClientEntity client = optionalClientEntity.get();
        if (!BCrypt.checkpw(clientSecret, client.getHash())) {
            throw new LoginException("Secret is incorrect!");
        }
    }
}

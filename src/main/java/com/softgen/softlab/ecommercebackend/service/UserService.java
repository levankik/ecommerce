package com.softgen.softlab.ecommercebackend.service;

import com.softgen.softlab.ecommercebackend.api.model.LoginBody;
import com.softgen.softlab.ecommercebackend.api.model.RegistrationBody;
import com.softgen.softlab.ecommercebackend.exception.UserAlreadyExistsException;
import com.softgen.softlab.ecommercebackend.model.LocalUser;
import com.softgen.softlab.ecommercebackend.model.dao.LocalUserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final LocalUserDAO localUserDAO;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;

    public LocalUser registerUser (RegistrationBody registrationBody) throws UserAlreadyExistsException {
        if (localUserDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
        || localUserDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        LocalUser user = new LocalUser();
        user.setEmail(registrationBody.getEmail());
        user.setUsername(registrationBody.getUsername());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        return localUserDAO.save(user);
    }

    public String loginUser(LoginBody loginBody) {
        Optional<LocalUser> opUser = localUserDAO.findByUsernameIgnoreCase(loginBody.getUsername());
        if (opUser.isPresent()) {
          LocalUser user = opUser.get();
          if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
              return jwtService.generateJWT(user);
          }
        }
        return null;
    }
}

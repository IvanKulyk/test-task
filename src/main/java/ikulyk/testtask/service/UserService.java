package ikulyk.testtask.service;

import ikulyk.testtask.controller.UserController;
import ikulyk.testtask.model.User;
import ikulyk.testtask.repo.UserRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final static String PASSWORD = "It's a strong password 12qwert&^%@";
    private final String salt = KeyGenerators.string().generateKey();
    private final TextEncryptor encryptor = Encryptors.text(PASSWORD, salt);

    private static Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String getUsernameById (Long id) {

        String name = userRepo.findById(id).map(User::getName).orElse("NULL");

        String encryptedMessage = getEncryptedMessage(name);
        String decryptedMessage = getDecryptedMessage(encryptedMessage);

        logger.info("Encrypted username: " + encryptedMessage);
        logger.info("Decrypted username: " + decryptedMessage);

        return name;

    }

    private String getEncryptedMessage(String message) {
        return encryptor.encrypt(message);
    }

    private String getDecryptedMessage(String encryptedMessage) {
        return encryptor.decrypt(encryptedMessage);
    }
}

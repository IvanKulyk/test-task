package ikulyk.testtask.controller;

import ikulyk.testtask.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);
    private final UserService userService;

    public UserController(ikulyk.testtask.service.UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/user")
    public String getUsername(Long id) throws Exception {

        logger.info("POST request for id: " + id);
        return userService.getUsernameById(id);

    }
}

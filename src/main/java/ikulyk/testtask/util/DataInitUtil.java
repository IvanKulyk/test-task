package ikulyk.testtask.util;

import ikulyk.testtask.model.User;
import ikulyk.testtask.repo.UserRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitUtil implements ApplicationRunner {

    private final UserRepo userRepo;
    private List<User> users = new ArrayList<>();

    static Logger logger = Logger.getLogger(DataInitUtil.class);

    @Autowired
    public DataInitUtil(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Setting data into DB");

        long count = userRepo.count();

        if (count == 0) {

            User user1 = new User("Mike Myers");
            User user2 = new User("Catherine Zeta-Jones");
            User user3 = new User("Jeffrey Leon Bridges");
            User user4 = new User("Whoopi Goldberg");

            users.add(user1);
            users.add(user2);
            users.add(user3);
            users.add(user4);

            userRepo.saveAll(users);
        }
    }
}

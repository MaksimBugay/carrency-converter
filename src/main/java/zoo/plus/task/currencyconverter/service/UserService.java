package zoo.plus.task.currencyconverter.service;

import zoo.plus.task.currencyconverter.model.security.User;

public interface UserService {
    User findUserByEmail(String email);
    void saveUser(User user);
}

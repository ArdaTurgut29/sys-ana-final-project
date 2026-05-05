package Controller;

import DAO.UserDAO;
import Model.User;
import java.util.List;

public class UserController {

    private final UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    public boolean createAccount(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return false;
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return false;
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return false;
        }
        return userDAO.createUser(user);
    }

    public boolean deleteAccount(int userId) {
        return userDAO.deleteUser(userId);
    }

    public boolean resetPassword(int userId, String newPassword) {
        if (newPassword == null || newPassword.trim().isEmpty()) {
            return false;
        }
        return userDAO.updatePassword(userId, newPassword.trim());
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean assignRole(int userId, int userType) {
        User user = userDAO.getUserById(userId);
        if (user == null) return false;
        user.setUserType(userType);
        return userDAO.updateUser(user);
    }
}

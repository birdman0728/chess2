package dataaccess;

import model.UserData;
import org.eclipse.jetty.server.Authentication;

import javax.xml.crypto.Data;

public interface UserDAO {
    void createUser(UserData u);
    UserData getUser(String userName) throws DataAccessException;
}

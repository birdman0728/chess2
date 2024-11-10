package dataaccess;

import model.UserData;
import org.eclipse.jetty.server.Authentication;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.HashSet;

public class MemoryUserDAO implements UserDAO {
    HashSet<UserData> tempDataBase = new HashSet<>();

    @Override
    public void createUser(UserData u) {
        tempDataBase.add(u);
    }

    @Override
    public UserData getUser(String userName) throws DataAccessException {
        for (UserData data : tempDataBase) {
            if (data.username().equals(userName)) {
                return data;
            }
        }
        throw new DataAccessException("This item doesn't exist");
    }
}
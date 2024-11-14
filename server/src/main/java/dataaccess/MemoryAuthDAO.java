package dataaccess;

import model.AuthData;
import model.GameData;

import javax.xml.crypto.Data;
import java.util.HashSet;

public class MemoryAuthDAO implements AuthDAO{

    HashSet<AuthData> tempDataBase = new HashSet<>();

    @Override
    public void createAuth(AuthData u) throws DataAccessException{
        tempDataBase.add(u);
    }

    @Override
    public AuthData getAuth(String auth) throws DataAccessException {
        for(AuthData data : tempDataBase){
            if(data.authToken().equals(auth)){
                return data;
            }
        }
        throw new DataAccessException("Account does not exist");
    }

    @Override
    public void deleteAuth(AuthData u) throws DataAccessException {
        for(AuthData data : tempDataBase){
            if(data.equals(u)){
                tempDataBase.remove(u);
                return;
            }
        }
        throw new DataAccessException("Account does not exist");
    }
}

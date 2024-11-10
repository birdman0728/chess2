package dataaccess;

import model.GameData;
import model.UserData;

import javax.xml.crypto.Data;
import java.util.HashSet;
import java.util.List;

public class MemoryGameDAO implements GameDAO{
    HashSet<GameData> tempDataBase = new HashSet<>();
    @Override
    public void createGame(GameData u) {
        tempDataBase.add(u);
    }

    @Override
    public GameData getGame(int ID) throws DataAccessException {
        for(GameData data : tempDataBase){
            if(data.gameID() == ID){
                return data;
            }
        }
        throw new DataAccessException("Game does not exist");
    }

    @Override
    public List<GameData> listGame() {
        List<GameData> listed = null;
        listed.addAll(tempDataBase);
        return listed;
    }

    @Override
    public void updateGame(int gameID, GameData newGame) throws DataAccessException{
        boolean found = false;
        for(GameData data : tempDataBase){
            if(data.gameID() == gameID){
                tempDataBase.remove(data);
                tempDataBase.add(newGame);
                found = true;
            }
        }
        if(!found){
            throw new DataAccessException("The game you are trying to update does not exist");
        }

    }
}

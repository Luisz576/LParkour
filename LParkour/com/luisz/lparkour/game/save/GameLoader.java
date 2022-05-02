package com.luisz.lparkour.game.save;

import com.lib576.Lib576;
import com.lib576.libs.LConfig;
import com.sun.istack.internal.NotNull;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class GameLoader {

    private final LConfig config;

    public GameLoader(String saveName) {
        this.config = new LConfig(saveName, Lib576.getInstance());
    }

    //Save
    public final void saveGame(@NotNull GameData gameData){
        config.setValue(GameSaveKeys.GAME_NAME.toString(), gameData.gameName);
        config.setValue(GameSaveKeys.GAME_MAX_PLAYERS.toString(), gameData.maxPlayers);
        config.setValue(GameSaveKeys.GAME_WAIT_LOCATION.toString(), gameData.waitLocation);
        config.setValue(GameSaveKeys.GAME_START_LOCATION.toString(), gameData.startLocation);
        saveListOfCheckpoints(gameData);
        config.setValue(GameSaveKeys.GAME_FINISH_LINE.toString(), gameData.finishLine);
        config.save();
    }

    private void saveListOfCheckpoints(@NotNull GameData gameData){
        int index = 0;
        config.setValue(GameSaveKeys.GAME_CHECKPOINTS + ".size", gameData.checkpoints.size());
        for(Location checkpoint : gameData.checkpoints){
            config.setValue(GameSaveKeys.GAME_CHECKPOINTS + "." + index, checkpoint);
            index++;
        }
    }

    //Load
    public final GameData loadData(){
        String gameName = config.getString("game_name");
        if(gameName != null && !gameName.isEmpty()){
            GameData gameData = new GameData(gameName);
            gameData.maxPlayers = config.getInt(GameSaveKeys.GAME_MAX_PLAYERS.toString());
            gameData.waitLocation = config.getLocation(GameSaveKeys.GAME_WAIT_LOCATION.toString());
            gameData.startLocation = config.getLocation(GameSaveKeys.GAME_START_LOCATION.toString());
            gameData.checkpoints = loadListOfCheckpoints();
            gameData.finishLine = config.getArea(GameSaveKeys.GAME_FINISH_LINE.toString());
            return gameData;
        }
        return null;
    }

    private List<Location> loadListOfCheckpoints(){
        List<Location> checkpoints = new ArrayList<>();
        int size = config.getInt(GameSaveKeys.GAME_CHECKPOINTS + ".size");
        for(int i = 0; i < size; i++)
            checkpoints.add(config.getLocation(GameSaveKeys.GAME_CHECKPOINTS + "." + i));
        return checkpoints;
    }

}
package com.luisz.lparkour.game.save;

import com.lib576.Lib576;
import com.lib576.libs.LConfig;
import com.sun.istack.internal.NotNull;

public class GameLoader {

    private final LConfig config;

    public GameLoader(String saveName) {
        this.config = new LConfig(saveName, Lib576.getInstance());
    }

    //Save
    public final void saveGame(@NotNull GameData gameData){
        config.setValue(GameSaveKeys.GAME_NAME.toString(), gameData.gameName);
        config.setValue(GameSaveKeys.GAME_MAX_PLAYERS.toString(), gameData.maxPlayers);
        config.save();
    }

    //Load
    public final GameData loadData(){
        String gameName = config.getString("game_name");
        if(gameName != null && !gameName.isEmpty()){
            GameData gameData = new GameData(gameName);
            //TODO:
            return gameData;
        }
        return null;
    }

}
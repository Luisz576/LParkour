package com.luisz.lparkour.game.save;

public enum GameSaveKeys {
    GAME_NAME("game_name"),
    GAME_MAX_PLAYERS("max_players"),
    GAME_WAIT_LOCATION("wait_location"),
    GAME_START_LOCATION("start_location"),
    GAME_CHECKPOINTS("checkpoints"),
    GAME_FINISH_LINE("finish_line");

    private final String key;
    GameSaveKeys(String key){
        this.key = key;
    }
    @Override
    public String toString() {
        return this.key;
    }
}
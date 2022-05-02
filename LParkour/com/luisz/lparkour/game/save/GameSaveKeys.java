package com.luisz.lparkour.game.save;

public enum GameSaveKeys {
    GAME_NAME("game_name"),
    GAME_MAX_PLAYERS("max_players");

    private final String key;
    GameSaveKeys(String key){
        this.key = key;
    }
    @Override
    public String toString() {
        return this.key;
    }
}
package com.luisz.lparkour.game.sign;

import com.lib576.Lib576;
import com.lib576.libs.LConfig;

public class SignsSave {

    private final LConfig config;

    public SignsSave(){
        config = new LConfig("signs", Lib576.getInstance());
        loadSigns();
    }

    private void loadSigns(){
        //TODO:
    }

}
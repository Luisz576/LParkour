package com.luisz.lparkour.game.sign;

import com.lib576.Lib576;
import com.lib576.libs.LConfig;
import com.luisz.lparkour.game.sign.events.SignsDataChangeEvent;

import java.util.ArrayList;
import java.util.List;

public class SignsSave {

    private final LConfig config;

    public SignsSave(){
        config = new LConfig("signs", Lib576.getInstance());
        loadSigns();
    }

    public void addNewSign(SignSave signSave){
        //TODO:
        Lib576.callEvent(new SignsDataChangeEvent());
    }

    public void removeSign(SignSave signSave){
        //TODO:
        Lib576.callEvent(new SignsDataChangeEvent());
    }

    public List<SignSave> loadSigns(){
        //TODO:
        List<SignSave> signsSaves = new ArrayList<>();
        return signsSaves;
    }

}
package com.luisz.lparkour.game.sign;

import com.lib576.Lib576;
import com.lib576.libs.LConfig;
import com.luisz.lparkour.game.sign.events.SignsDataChangeEvent;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class SignsSave {

    private final List<Integer> _ids = new ArrayList<>();
    private final LConfig config;

    public SignsSave(){
        config = new LConfig("signs", Lib576.getInstance());
        _ids.addAll(_loadIds());
    }

    public void addNewSign(SignSave signSave){
        for(SignSave s : loadSigns())
            if(s.id == signSave.id)
                return;
        _ids.add(signSave.id);
        config.setValue("signs." + signSave.id + ".game_name", signSave.gameName);
        config.setValue("signs." + signSave.id + ".sign_location", signSave.signLocation);
        _saveData();
    }

    public void removeSign(SignSave signSave){
        config.remove("signs." + signSave.id + ".game_name");
        config.remove("signs." + signSave.id + ".sign_location");
        _ids.remove(signSave.id);
        _saveData();
    }

    private void _saveData(){
        config.setValue("signs_ids", _ids);
        config.save();
        Lib576.callEvent(new SignsDataChangeEvent());
    }

    public List<SignSave> loadSigns(){
        List<SignSave> signsSaves = new ArrayList<>();
        for(int id : _ids)
            signsSaves.add(loadSign(id));
        return signsSaves;
    }

    private SignSave loadSign(int id){
        String gameName = config.getString("signs." + id + ".game_name");
        Location location = config.getLocation("signs." + id + ".sign_location");
        return new SignSave(id, gameName, location);
    }

    private List<Integer> _loadIds(){
        return (List<Integer>) config.getList("signs_ids");
    }

}
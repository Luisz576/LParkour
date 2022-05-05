package com.luisz.lparkour.services;

import com.lib576.Lib576;
import com.lib576.libs.LConfig;

public class ApiConfig {

    private final LConfig lConfig;

    public ApiConfig(){
        lConfig = new LConfig("api_config", Lib576.getInstance());
        lConfig.save();
        getApiBase();
    }

    public String getApiBase(){
        if(lConfig.hasKey("api_base"))
            return lConfig.getString("api_base");
        return "";
    }

}
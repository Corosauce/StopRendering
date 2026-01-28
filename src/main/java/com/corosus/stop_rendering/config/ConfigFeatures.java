package com.corosus.stop_rendering.config;

import com.corosus.modconfig.ConfigComment;
import com.corosus.modconfig.IConfigCategory;
import com.corosus.stop_rendering.StopRendering;

import java.io.File;


public class ConfigFeatures implements IConfigCategory {



    @ConfigComment("Path to check for the curios class to cancel non player renders for")
    public static String curiosClassPath = "top.theillusivec4.curios.client.render.CuriosLayer";

    @Override
    public String getName() {
        return "features";
    }

    @Override
    public String getRegistryName() {
        return StopRendering.MODID + getName();
    }

    @Override
    public String getConfigFileName() {
        return StopRendering.MODID + File.separator + getName();
    }

    @Override
    public String getCategory() {
        return StopRendering.MODID + ": " + getName();
    }

    @Override
    public void hookUpdatedValues() {

    }
}

package com.corosus.stop_rendering.config;

import com.corosus.modconfig.ConfigComment;
import com.corosus.modconfig.IConfigCategory;
import com.corosus.stop_rendering.StopRendering;

import java.io.File;


public class ConfigFeatures implements IConfigCategory {

    @ConfigComment("Enables or disables every feature this mod provides. Handy for testing how much it helps.")
    public static boolean modActive = true;

    @ConfigComment("Path to check for the curios class to cancel non player renders for")
    public static String mod_Curios_classpath = "top.theillusivec4.curios.client.render.CuriosLayer";

    @ConfigComment("Hyper optimizes curios use to only run for players not all entities, might help a bit if you have a large number of entities")
    public static boolean mod_Curios_disableOnNonPlayers = true;

    @ConfigComment("Same as disableCuriosOnNonPlayers but for Artifacts mod")
    public static boolean mod_Artifacts_disableOnNonPlayers = true;

    @ConfigComment("The mods expensive mob stacking code needlessly runs on the client causing a frame stutter 20 times a second, this stops it")
    public static boolean mod_EnhancedHordes_disableClientHordeTickProcedure = true;

    @ConfigComment("Stops more expensive unneeded client side calculations for non players")
    public static boolean mod_ArmorSetBonuses_disableClientBonusApplyingOnNonPlayers = true;

    @ConfigComment("Alternative to disableCuriosOnNonPlayers, not as extensive")
    public static boolean disableZombieAndHuskExtraRenderLayers = true;

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

package com.corosus.stop_rendering;

import com.corosus.modconfig.ConfigMod;
import com.corosus.stop_rendering.config.ConfigFeatures;
import com.corosus.stop_rendering.config.MobListsConfig;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class StopRendering
{
    public static final String MODID = "stop_rendering";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static String lastWanderTime = "lastWanderTime";
    public static int wanderDelay = 20*5;
    public static int wanderDistTrigger = 20*5;

    public static boolean modActive = true;
    public static boolean testSpawningActive = false;
    private static int cancels = 0;

    //avoid excessive forge config lookups incase its slow
    private static HashMap<EntityType, Boolean> mobProcessCache = new HashMap<>();

    public StopRendering()
    {
        new File("./config/" + MODID).mkdirs();
        ConfigMod.addConfigFile(MODID, new ConfigFeatures());

        generateEntityTickList();
    }

    public static boolean canConfigEntity(EntityType ent) {
        return true;
    }

    public static boolean canProcessEntity(EntityType ent) {

        Boolean processCache = mobProcessCache.get(ent);
        if (processCache == null) {
            if (canConfigEntity(ent)) {
                String str = BuiltInRegistries.ENTITY_TYPE.getKey(ent).toString();
                if (MobListsConfig.GENERAL.mobsList.get().contains(str)) {
                    processCache = !MobListsConfig.GENERAL.useWhitelistAsBlacklist.get();
                } else {
                    processCache = MobListsConfig.GENERAL.useWhitelistAsBlacklist.get();
                }
            } else {
                processCache = false;
            }
            mobProcessCache.put(ent, processCache);
        }
        return processCache;
    }

    public static void generateEntityTickList() {
        for(Map.Entry<ResourceKey<EntityType<?>>, EntityType<?>> entry : BuiltInRegistries.ENTITY_TYPE.entrySet()) {
            boolean canConfig = canConfigEntity(entry.getValue());
            if (canConfig) {
                MobListsConfig.usableMobsForList.add(entry.getKey().location().toString());
            }
        }
    }
}

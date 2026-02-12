package com.corosus.stop_rendering;

import com.corosus.modconfig.ConfigMod;
import com.corosus.stop_rendering.config.ConfigFeatures;
import com.corosus.stop_rendering.config.MobListsConfig;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import org.slf4j.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class StopRendering
{
    public static final String MODID = "stop_rendering";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static StopRendering instance;

    private static int cancels = 0;

    //avoid excessive forge config lookups incase its slow
    private static HashMap<EntityType, Boolean> mobProcessCache = new HashMap<>();

    public static StopRendering getInstance() {
        return instance;
    }

    public StopRendering()
    {
        instance = this;
        new File("./config/" + MODID).mkdirs();
        ConfigMod.addConfigFile(MODID, new ConfigFeatures());
    }

    public abstract String getRegistryName(EntityType type);

    public abstract Set<Map.Entry<ResourceKey<EntityType<?>>, EntityType<?>>> getEntityRegistry();

    public static boolean canConfigEntity(EntityType ent) {
        return true;
    }

    /**
     * process = allow to continue with default behavior, if its false we cancel whatever were trying to cancel
     * @param ent
     * @return
     */
    public static boolean canProcessEntity(EntityType ent) {

        String str2 = StopRendering.getInstance().getRegistryName(ent);
        Boolean processCache = mobProcessCache.get(ent);
        if (processCache == null) {
            if (canConfigEntity(ent)) {
                String str = StopRendering.getInstance().getRegistryName(ent);
                if (MobListsConfig.GENERAL.mobsList.get().contains(str)) {
                    processCache = !MobListsConfig.GENERAL.useWhitelistAsBlacklist.get();
                } else {
                    processCache = MobListsConfig.GENERAL.useWhitelistAsBlacklist.get();
                }
            } else {
                processCache = false;
            }
            mobProcessCache.put(ent, processCache);
        } else {
        }
        return processCache;
    }

    public static boolean isCuriosRenderLayer(RenderLayer renderLayer) {
        if (renderLayer.getClass().getCanonicalName() == null) return false;
        return renderLayer.getClass().getCanonicalName().equals(ConfigFeatures.mod_Curios_classpath);
    }

    public static void generateEntityTickList() {
        MobListsConfig.usableMobsForList.clear();
        for(Map.Entry<ResourceKey<EntityType<?>>, EntityType<?>> entry : StopRendering.getInstance().getEntityRegistry()) {
            boolean canConfig = canConfigEntity(entry.getValue());
            if (canConfig) {
                MobListsConfig.usableMobsForList.add(entry.getKey().location().toString());
            }
        }

        MobListsConfig.GENERAL.usableMobsForList.set(MobListsConfig.usableMobsForList);
    }
}

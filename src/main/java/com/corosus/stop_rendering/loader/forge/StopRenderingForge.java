package com.corosus.stop_rendering.loader.forge;

import com.corosus.stop_rendering.StopRendering;
import com.corosus.stop_rendering.config.MobListsConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Mod(StopRendering.MODID)
public class StopRenderingForge extends StopRendering {

    public StopRenderingForge() {
        super();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.register(StopRendering.class);
        modEventBus.addListener(this::loadComplete);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MobListsConfig.CONFIG, StopRendering.MODID + File.separator + "MobsWhitelist.toml");

        for(Map.Entry<ResourceKey<EntityType<?>>, EntityType<?>> entry : BuiltInRegistries.ENTITY_TYPE.entrySet()) {
            boolean canConfig = canConfigEntity(entry.getValue());
            if (canConfig) {
                MobListsConfig.usableMobsForList.add(entry.getKey().location().toString());
            }
        }

        Iterator<EntityType<?>> it = ForgeRegistries.ENTITY_TYPES.iterator();

        for(Map.Entry<ResourceKey<EntityType<?>>, EntityType<?>> entry : ForgeRegistries.ENTITY_TYPES.getEntries()) {
            boolean canConfig = canConfigEntity(entry.getValue());
            if (canConfig) {
                MobListsConfig.usableMobsForList.add(entry.getKey().location().toString());
            }
        }
    }

    

    @Override
    public String getRegistryName(EntityType type) {
        return ForgeRegistries.ENTITY_TYPES.getKey(type).toString();
    }

    @Override
    public Set<Map.Entry<ResourceKey<EntityType<?>>, EntityType<?>>> getEntityRegistry() {
        return ForgeRegistries.ENTITY_TYPES.getEntries();
    }

    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event) {
        //CommandMisc.register(event.getDispatcher());
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void loadComplete(final FMLLoadCompleteEvent event)
    {
        StopRendering.generateEntityTickList();
    }
}

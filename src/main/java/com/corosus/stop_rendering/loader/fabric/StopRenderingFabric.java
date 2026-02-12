package com.corosus.stop_rendering.loader.fabric;

import com.corosus.stop_rendering.StopRendering;
import com.corosus.stop_rendering.config.MobListsConfig;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.fml.config.ModConfig;

import java.io.File;
import java.util.Map;
import java.util.Set;

public class StopRenderingFabric extends StopRendering implements ModInitializer {

	public static MinecraftServer minecraftServer = null;

	public StopRenderingFabric() {
		super();

		ForgeConfigRegistry.INSTANCE.register(MODID, ModConfig.Type.COMMON, MobListsConfig.CONFIG, StopRenderingFabric.MODID + File.separator + "MobsWhitelist.toml");
	}

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STARTED.register((minecraftServer) -> {
			StopRenderingFabric.minecraftServer = minecraftServer;
		});


	}

	@Override
	public String getRegistryName(EntityType type) {
		LOGGER.error("FIX ME");
		return "";
		//return ForgeRegistries.ENTITY_TYPES.getKey(type).toString();
	}

	@Override
	public Set<Map.Entry<ResourceKey<EntityType<?>>, EntityType<?>>> getEntityRegistry() {
		//TODO: verify this actually contains modded entities, it doesnt for forge, but afaik fabric doesnt have a different way to access it
		return BuiltInRegistries.ENTITY_TYPE.entrySet();
	}
}
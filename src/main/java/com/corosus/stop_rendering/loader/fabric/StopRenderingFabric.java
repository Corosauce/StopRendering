package com.corosus.stop_rendering.loader.fabric;

import com.corosus.stop_rendering.StopRendering;
import com.corosus.stop_rendering.config.MobListsConfig;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.config.ModConfig;

import java.io.File;

public class StopRenderingFabric extends StopRendering implements ModInitializer {

	public static MinecraftServer minecraftServer = null;

	public StopRenderingFabric() {
		super();

		ForgeConfigRegistry.INSTANCE.register(MODID, ModConfig.Type.COMMON, MobListsConfig.CONFIG, StopRenderingFabric.MODID + File.separator + "MobsBlacklist.toml");
	}

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STARTED.register((minecraftServer) -> {
			StopRenderingFabric.minecraftServer = minecraftServer;
		});


	}
}
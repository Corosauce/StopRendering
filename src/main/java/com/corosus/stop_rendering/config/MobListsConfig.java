package com.corosus.stop_rendering.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;

import java.util.ArrayList;
import java.util.List;

public class MobListsConfig {

    public static List<String> mobsList = new ArrayList<>();
    public static List<String> usableMobsForList = new ArrayList<>();

    static {
        String mc = "minecraft:";
        mobsList.add(mc + "player");
    }

    private static final Builder BUILDER = new Builder();

    public static final CategoryGeneral GENERAL = new CategoryGeneral();

    public static final class CategoryGeneral {

        public final ForgeConfigSpec.ConfigValue<List<? extends String>> mobsList;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> usableMobsForList;
        public final ForgeConfigSpec.ConfigValue<Boolean> useWhitelistAsBlacklist;

        private CategoryGeneral() {

            BUILDER.comment("General mod settings").push("general");

            mobsList = BUILDER.comment("Mobs to allow rendering of curios").defineList("whitelistMobs", MobListsConfig.mobsList,
                    it -> it instanceof String);

            usableMobsForList = BUILDER.comment("These are all the mobs from your modpack you can choose from when adding to the whitelist, use this to find the mob you need, then add it to the array for the whitelistMobs config option.").defineList("mobsYouCanWhitelist", MobListsConfig.usableMobsForList,
                    it -> it instanceof String);

            useWhitelistAsBlacklist = BUILDER.comment("Set the whitelist to behave as a blacklist instead").define("useWhitelistAsBlacklist", false);

            BUILDER.pop();
        }
    }
    public static final ForgeConfigSpec CONFIG = BUILDER.build();
}

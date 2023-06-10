package pepjebs.no_more_purple.client;

import com.mojang.brigadier.arguments.StringArgumentType;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.DyeColor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pepjebs.no_more_purple.config.NoMorePurpleConfig;

import java.util.Arrays;

public class NoMorePurpleClientMod implements ClientModInitializer {

    public static final String MOD_ID = "no_more_purple";
    public static final String COMMAND_ID = "glint_color";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static NoMorePurpleConfig CONFIG = null;

    @Override
    public void onInitializeClient() {
        // Register config
        AutoConfig.register(NoMorePurpleConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(NoMorePurpleConfig.class).getConfig();

        // Register the "/glintcolor <color|on|off>" command
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                    ClientCommandManager.literal(COMMAND_ID)
                            .then(ClientCommandManager.literal("off").executes(context -> {
                                NoMorePurpleClientMod.CONFIG.enabled=false;
                                return 1;
                            })).then(ClientCommandManager.literal("on").executes(context -> {
                                NoMorePurpleClientMod.CONFIG.enabled=true;
                                return 1;
                            }))
                            .then(ClientCommandManager.argument(COMMAND_ID, StringArgumentType.string())
                                .suggests(new GlintColorSuggestionProvider())
                                .executes(context -> {
                                    CONFIG.glintColor = context.getArgument(COMMAND_ID, String.class);
                                    AutoConfig.getConfigHolder(NoMorePurpleConfig.class).save();
                                    return 0;
                                })
                            )
            );
        });
    }

    public static String[] listGlintColors() {
        return new String[]{
                "white",
                "orange",
                "magenta",
                "light_blue",
                "yellow",
                "lime",
                "pink",
                "gray",
                "light_gray",
                "cyan",
                "purple",
                "blue",
                "brown",
                "green",
                "red",
                "black",
                "rainbow",
                "light",
                "none"
        };
    }

    private static int changeColor() {
        String confColor = CONFIG.glintColor.toLowerCase();
        int color = Arrays.stream(DyeColor.values())
                .filter(d -> d.getName().compareTo(confColor)==0)
                .findFirst()
                .map(DyeColor::getId)
                .orElse(10); //Default Purple
        if (confColor.compareTo("rainbow") == 0) {
            color = DyeColor.values().length;
        } else if (confColor.compareTo("light") == 0){
            color = DyeColor.values().length + 1;
        } else if (confColor.compareTo("none") == 0){
            color = DyeColor.values().length + 2;
        }
        return color;
    }

    @Environment(EnvType.CLIENT)
    public static RenderLayer getGlint() {
        int color = changeColor();
        return GlintRenderLayer.glintColor.get(color);
    }

    @Environment(EnvType.CLIENT)
    public static RenderLayer getEntityGlint() {
        int color = changeColor();
        return GlintRenderLayer.entityGlintColor.get(color);
    }

    @Environment(EnvType.CLIENT)
    public static RenderLayer getGlintDirect() {
        int color = changeColor();
        return GlintRenderLayer.glintDirectColor.get(color);
    }

    @Environment(EnvType.CLIENT)
    public static RenderLayer getEntityGlintDirect() {
        int color = changeColor();
        return GlintRenderLayer.entityGlintDirectColor.get(color);
    }

    @Environment(EnvType.CLIENT)
    public static RenderLayer getArmorGlint() {
        int color = changeColor();
        return GlintRenderLayer.armorGlintColor.get(color);
    }

    @Environment(EnvType.CLIENT)
    public static RenderLayer getArmorEntityGlint() {
        int color = changeColor();
        return GlintRenderLayer.armorEntityGlintColor.get(color);
    }


    @Environment(EnvType.CLIENT)
    public static RenderLayer getGlintTransluncent() {
        int color = changeColor();
        return GlintRenderLayer.translucentGlintColor.get(color);
    }
}

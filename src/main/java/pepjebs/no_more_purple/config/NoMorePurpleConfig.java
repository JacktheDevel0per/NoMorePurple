package pepjebs.no_more_purple.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "no_more_purple")
public class NoMorePurpleConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip()
    @Comment("Set the color of your client's glint.")
    public String glintColor = "red";


    @ConfigEntry.Gui.Tooltip()
    @Comment("Main Toggle for the mod, when disabled the mod's hooks are \"disabled\"")
    public Boolean enabled = true;
}
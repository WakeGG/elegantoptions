package io.github.srvenient.elegantoptions.plugin.translation.placeholder;

import dev.srvenient.freya.abstraction.configuration.Configuration;

import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Named;

import static io.github.srvenient.elegantoptions.plugin.translation.Translation.colorize;

public class PlaceholderWrapper extends PlaceholderExpansion {

    @Inject private UserMatcher userMatcher;

    @Inject @Named("language") private Configuration language;

    @Override
    public @NotNull String getIdentifier() {
        return "user";
    }

    @Override
    public @NotNull String getAuthor() {
        return "SrVenient";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) {
            return "";
        }

        User user = userMatcher.getUserId(player.getUniqueId());

        if (user == null) {
            return "";
        }

        switch (params) {
            case "name":
                return player.getName();
            case "visibility":
                switch (user.getVisibility()) {
                    case ON:
                        return colorize(player, language.getString("commons.visibility-on"));
                    case OFF:
                        return colorize(player, language.getString("commons.visibility-off"));
                    case ONLY_RANK:
                        return colorize(player, language.getString("commons.visibility-only-rank"));
                    case NO_PERMISSION:
                        return colorize(player, language.getString("commons.visibility-no-permission"));
                }

                return "";
            case "chat":
                switch (user.getChat()) {
                    case ON:
                        return colorize(player, language.getString("commons.chat-on"));
                    case OFF:
                        return colorize(player, language.getString("commons.chat-off"));
                    case NO_PERMISSION:
                        return colorize(player, language.getString("commons.chat-no-permission"));
                }

                return "";
            case "doublejump":
                switch (user.getDoubleJump()) {
                    case ON:
                        return colorize(player, language.getString("commons.double-jump-on"));
                    case OFF:
                        return colorize(player, language.getString("commons.double-jump-off"));
                    case NO_PERMISSION:
                        return colorize(player, language.getString("commons.double-jump-no-permission"));
                }

                return "";
            case "mount":
                switch (user.getMount()) {
                    case ON:
                        return colorize(player, language.getString("commons.mount-on"));
                    case OFF:
                        return colorize(player, language.getString("commons.mount-off"));
                    case NO_PERMISSION:
                        return colorize(player, language.getString("commons.mount-no-permission"));
                }

                return "";
            case "fly":
                switch (user.getFly()) {
                    case ON:
                        return colorize(player, language.getString("commons.fly-on"));
                    case OFF:
                        return colorize(player, language.getString("commons.fly-off"));
                    case NO_PERMISSION:
                        return colorize(player, language.getString("commons.fly-no-permission"));
                }

                return "";
            case "messagejoin":
                switch (user.getMessageJoin()) {
                    case ON:
                        return colorize(player, language.getString("commons.message-join-on"));
                    case OFF:
                        return colorize(player, language.getString("commons.message-join-off"));
                    case NO_PERMISSION:
                        return colorize(player, language.getString("commons.message-join-no-permission"));
                }

                return "";
            case "effectjoin":
                switch (user.getEffectJoin()) {
                    case NOTHING:
                        return colorize(player, language.getString("commons.effect-join-nothing"));
                    case FIREWORK:
                        return colorize(player, language.getString("commons.effect-join-firework"));
                    case ZEUS:
                        return colorize(player, language.getString("commons.effect-join-zeus"));
                    case SHEEP:
                        return colorize(player, language.getString("commons.effect-join-sheeps"));
                }
            default:
                return "";
        }
    }
}

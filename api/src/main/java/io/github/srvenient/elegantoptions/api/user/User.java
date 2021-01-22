package io.github.srvenient.elegantoptions.api.user;

import dev.srvenient.freya.abstraction.model.StorableModel;

import io.github.srvenient.elegantoptions.api.Enums;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.beans.ConstructorProperties;
import java.util.UUID;

public class User extends StorableModel {

    private final String id;

    private String playerName;

    private Enums.TypeStatus visibility;
    private Enums.TypeStatus chat;
    private Enums.TypeStatus doubleJump;
    private Enums.TypeStatus mount;
    private Enums.TypeStatus fly;
    private Enums.TypeStatus messageJoin;
    private Enums.Effects effectJoin;

    public User(String playerId, String playerName) {
        this.id = playerId;

        this.playerName = playerName;

        this.visibility = Enums.TypeStatus.ON;
        this.chat = Enums.TypeStatus.ON;
        this.doubleJump = Enums.TypeStatus.OFF;
        this.mount = Enums.TypeStatus.ON;
        this.fly = Enums.TypeStatus.NO_PERMISSION;
        this.messageJoin = Enums.TypeStatus.NO_PERMISSION;
        this.effectJoin = Enums.Effects.NOTHING;
    }

    @ConstructorProperties({
            "id", "playerName", "visibility", "chat", "doubleJump", "mount", "fly", "messageJoin", "effectJoin"
    })
    public User(String id,
                String playerName,
                Enums.TypeStatus visibility,
                Enums.TypeStatus chat,
                Enums.TypeStatus doubleJump,
                Enums.TypeStatus mount,
                Enums.TypeStatus fly,
                Enums.TypeStatus messageJoin,
                Enums.Effects effectJoin) {

        this.id = id;

        this.playerName = playerName;

        this.visibility = visibility;
        this.chat = chat;
        this.doubleJump = doubleJump;
        this.mount = mount;
        this.fly = fly;
        this.messageJoin = messageJoin;
        this.effectJoin = effectJoin;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Player getRawPlayer() {
        return Bukkit.getPlayer(UUID.fromString(id));
    }

    public Enums.TypeStatus getVisibility() {
        return visibility;
    }

    public void setVisibility(Enums.TypeStatus visibility) {
        this.visibility = visibility;
    }

    public Enums.TypeStatus getChat() {
        return chat;
    }

    public void setChat(Enums.TypeStatus chat) {
        this.chat = chat;
    }

    public Enums.TypeStatus getDoubleJump() {
        return doubleJump;
    }

    public void setDoubleJump(Enums.TypeStatus doubleJump) {
        this.doubleJump = doubleJump;
    }

    public Enums.TypeStatus getMount() {
        return mount;
    }

    public void setMount(Enums.TypeStatus mount) {
        this.mount = mount;
    }

    public Enums.TypeStatus getFly() {
        return fly;
    }

    public void setFly(Enums.TypeStatus fly) {
        this.fly = fly;
    }

    public Enums.TypeStatus getMessageJoin() {
        return messageJoin;
    }

    public void setMessageJoin(Enums.TypeStatus messageJoin) {
        this.messageJoin = messageJoin;
    }

    public Enums.Effects getEffectJoin() {
        return effectJoin;
    }

    public void setEffectJoin(Enums.Effects effectJoin) {
        this.effectJoin = effectJoin;
    }
}

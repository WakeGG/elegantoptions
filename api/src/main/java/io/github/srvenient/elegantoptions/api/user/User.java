package io.github.srvenient.elegantoptions.api.user;

import dev.srvenient.freya.abstraction.model.StorableModel;

import io.github.srvenient.elegantoptions.api.Enums;

import org.bukkit.entity.Player;

public class User extends StorableModel {

    private final String id;

    private Enums.TypeStatus visibility;
    private Enums.TypeStatus chat;
    private Enums.TypeStatus doubleJump;
    private Enums.TypeStatus mount;
    private Enums.TypeStatus fly;
    private Enums.TypeStatus messageJoin;


    public User(Player player) {
        this.id = player.getUniqueId().toString();

        this.visibility = Enums.TypeStatus.ON;
        this.chat = Enums.TypeStatus.ON;
        this.doubleJump = Enums.TypeStatus.OFF;
        this.mount = Enums.TypeStatus.ON;
        this.fly = Enums.TypeStatus.OFF;
        this.messageJoin = Enums.TypeStatus.OFF;
    }

    @Override
    public String getId() {
        return id;
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
}

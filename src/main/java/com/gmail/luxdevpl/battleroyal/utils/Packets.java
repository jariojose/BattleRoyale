package com.gmail.luxdevpl.battleroyal.utils;

import net.minecraft.server.v1_9_R2.IChatBaseComponent;
import net.minecraft.server.v1_9_R2.PacketPlayOutChat;
import net.minecraft.server.v1_9_R2.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Packets {

    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int fadeOut, int time){
        PacketPlayOutTitle playOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}"), fadeIn, time, fadeOut);
        PacketPlayOutTitle playOutSubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}"), fadeIn, time, fadeOut);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(playOutTitle);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(playOutSubtitle);
    }

    public static void sendActionBar(Player player, String s){
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + s + "\"}"), (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}

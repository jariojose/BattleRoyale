/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.utils;

import org.bukkit.ChatColor;

public class StringUtils {

    public static String color(String s){
        return s != null ? ChatColor.translateAlternateColorCodes('&', s) : null;
    }

}

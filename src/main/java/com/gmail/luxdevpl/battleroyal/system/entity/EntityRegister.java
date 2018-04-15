/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.system.entity;

import net.minecraft.server.v1_9_R2.EntityTypes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EntityRegister {

    public boolean registerEntity() {
        try {
            Class<EntityTypes> entityTypeClass = EntityTypes.class;

            Field c = entityTypeClass.getDeclaredField("c");

            c.setAccessible(true);

            Map<String, Class<?>> c_map = (HashMap<String, Class<?>>) c.get(null);
            c_map.put("CustomEnderDragonBus", CustomEnderDragonBus.class);

            Field d = entityTypeClass.getDeclaredField("d");

            d.setAccessible(true);

            Map<Class<?>, String> d_map = (HashMap<Class<?>, String>) d.get(null);
            d_map.put(CustomEnderDragonBus.class, "CustomEnderDragonBus");

            Field e = entityTypeClass.getDeclaredField("e");

            e.setAccessible(true);

            Map<Integer, Class<?>> e_map = (HashMap<Integer, Class<?>>) e.get(null);
            e_map.put(63, CustomEnderDragonBus.class);

            Field f = entityTypeClass.getDeclaredField("f");

            f.setAccessible(true);

            Map<Class<?>, Integer> f_map = (HashMap<Class<?>, Integer>) f.get(null);
            f_map.put(CustomEnderDragonBus.class, 63);

            Field g = entityTypeClass.getDeclaredField("g");

            g.setAccessible(true);

            Map<String, Integer> g_map = (HashMap<String, Integer>) g.get(null);
            g_map.put("CustomEnderDragonBus", 63);

            return true;
        } catch (Exception e) {

            Class<?>[] paramTypes = new Class[]{Class.class, String.class, int.class};

            try {
                Method method = EntityTypes.class.getDeclaredMethod("addMapping", paramTypes);

                method.setAccessible(true);

                method.invoke(null, CustomEnderDragonBus.class, "CustomEnderDragonBus", 63);
                return true;
            } catch (Exception ex) {
                e.addSuppressed(ex);
            }
            try {
                for (Method method : EntityTypes.class.getDeclaredMethods()) {
                    if (Arrays.equals(paramTypes, method.getParameterTypes())) {
                        method.invoke(null, CustomEnderDragonBus.class, "CustomEnderDragonBus", 63);
                        return true;
                    }
                }
            } catch (Exception ex) {
                e.addSuppressed(ex);
            }
            e.printStackTrace();
        }
        return false;
    }

}

/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.rs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;
import org.sponge.util.Logger;

public class Reflection {

  public static boolean printDebugMessages = false;
  public static Map<String, Class<?>> classMap = new HashMap<>();
  static HashMap<String, String> refmap;
  static Logger logger = new Logger("Reflection");
  private static int mappedClasses = 0;

  static {
    try {
      HashMap<String, String> type = new HashMap<>();
      try (Reader reader = new InputStreamReader(Reflection.class
          .getResourceAsStream("/osrs-refmap.json"))) {
        refmap = (HashMap<String, String>) new Gson().fromJson(reader, type.getClass());
      }
      for (String s : refmap.keySet())
      {
        try {
          Class<?> clazz = Class.forName(refmap.get(s));
          ObfuscatedName obfuscatedName = clazz
              .getAnnotation(ObfuscatedName.class);
          if (obfuscatedName != null) {
            classMap.put(obfuscatedName.value(), clazz);
            mappedClasses++;
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }

      if (mappedClasses < 300) {
        JOptionPane.showMessageDialog(null, "Obfuscated Mapping failure" +
            "!!!Jagex servers will deny total interaction with entities!!!\n" +
            "!!!Client will exit immediately!!!");
        System.exit(-1);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }



  public static Class<?> findClass(String name) throws ClassNotFoundException {
    Class<?> clazz = classMap.get(name);

    if (clazz != null) {
      return clazz;
    }

    return Class.forName(name);
  }

  public static Field findField(Class<?> clazz, String name) throws NoSuchFieldException {
    for (Field f : clazz.getDeclaredFields()) {
      ObfuscatedName annotation = f.getAnnotation(ObfuscatedName.class);
      if (annotation != null && annotation.value().equals(name)) {
        if (printDebugMessages) {
          logger.info("[Get] " + f.getName() + " in " + clazz);
        }
        return f;
      }
    }

    return clazz.getDeclaredField(name);
  }

  public static String getMethodName(Method method) {
    ObfuscatedName annotation = method.getAnnotation(ObfuscatedName.class);
    if (annotation != null) {
      return annotation.value();
    }
    return method.getName();
  }

  public static Class<?>[] getParameterTypes(Method method) {
    ObfuscatedSignature sig = method.getAnnotation(ObfuscatedSignature.class);
    Class<?>[] types = method.getParameterTypes();

    if (sig == null) {
      return types;
    }

    String s = sig.descriptor();
    int i = s.lastIndexOf(')');
    char c = s.charAt(i - 1);

    Class<?> last;

    switch (c) {
      case 'B':
        last = byte.class;
        break;
      case 'I':
        last = int.class;
        break;
      case 'S':
        last = short.class;
        break;
      default:
        throw new IllegalStateException();
    }

    types = Arrays.copyOf(types, types.length + 1);
    types[types.length - 1] = last;
    return types;
  }

  public static int getInt(Field field, Object obj)
      throws IllegalArgumentException, IllegalAccessException {
    if (printDebugMessages) {
      logger.info("[Get] " + field);
    }

    boolean unset = false;
    if ((field.getModifiers() & Modifier.PRIVATE) == 0) {
      // we're outside of the package so set it accessable
      // to behave like we are in the package
      field.setAccessible(true);
      unset = true;
    }

    int i;
    try {
      i = field.getInt(obj);
    } catch (Exception ex) {
      throw ex;
    } finally {
      if (unset) {
        field.setAccessible(false);
      }
    }

    ObfuscatedGetter og = field.getAnnotation(ObfuscatedGetter.class);
    if (og != null) {
      int getter = og.intValue();
      int setter = modInverse(getter);

      // encrypt
      i *= setter;
    }
    return i;
  }

  public static void setInt(Field field, Object obj, int value)
      throws IllegalArgumentException, IllegalAccessException {
    if (printDebugMessages) {
      logger.error("Jagex SetField - " + field + " to " + value);
    }

    ObfuscatedGetter og = field.getAnnotation(ObfuscatedGetter.class);
    if (og != null) {
      int getter = og.intValue();

      // decrypt
      value *= getter;
    }

    boolean unset = false;
    if ((field.getModifiers() & Modifier.PRIVATE) == 0) {
      // we're outside of the package so set it accessable
      // to behave like we are in the package
      field.setAccessible(true);
      unset = true;
    }

    try {
      field.setInt(obj, value);
    } finally {
      if (unset) {
        field.setAccessible(false);
      }
    }
  }

  public static BigInteger modInverse(BigInteger val, int bits) {
    BigInteger shift = BigInteger.ONE.shiftLeft(bits);
    return val.modInverse(shift);
  }

  public static int modInverse(int val) {
    return modInverse(BigInteger.valueOf(val), 32).intValue();
  }

  public static Object invoke(Method method, Object object, Object[] args)
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    if (printDebugMessages) {
      logger.error("Jagex InvokeMethod - " + method);
    }

    try {
      return method.invoke(object, args);
    } catch (Throwable ex) {
      throw ex;
    }
  }
}
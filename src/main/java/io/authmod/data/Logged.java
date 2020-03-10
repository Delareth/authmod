package io.authmod.data;

import io.authmod.AuthMod;

import java.util.ArrayList;
import java.util.List;

public class Logged {
  private static List<String> players = new ArrayList<>();

  public static void Add(String sUsername)
  {
    players.add(sUsername);
  }

  public static void Remove(String sUsername)
  {
    players.remove(sUsername);
  }

  public static boolean IsLogged(String sUsername)
  {
    return players.contains(sUsername);
  }
}

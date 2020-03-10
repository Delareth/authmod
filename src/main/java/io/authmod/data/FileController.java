package io.authmod.data;

import com.owlike.genson.Genson;
import io.authmod.AuthMod;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileController {
  private static Map<String, String> players;
  private static File authFile;
  private static final Logger LOGGER = AuthMod.LOGGER;

  private static Genson genson;

  public static void Init(File file)
  {
    authFile = file;
    genson = new Genson();

    try (BufferedReader bf = new BufferedReader(new FileReader(authFile)))
    {
      String json = bf.readLine();
      LOGGER.info("loading json: " + json);

      if (json == null)
      {
        players = new HashMap<>();
        return;
      }

      players = genson.deserialize(json, Map.class);

      LOGGER.info("Loaded " + players.size() + " registered players");
    }
    catch (IOException e)
    {
      LOGGER.catching(e);
    }
  }

  public static boolean IsRegistered(String sUsername)
  {
    return players.containsKey(sUsername);
  }

  public static void RegisterNew(String sUsername, String sPassword)
  {
    players.put(sUsername, sPassword);

    SaveFile();
  }

  public static String GetPassword(String sUsername)
  {
    return players.get(sUsername);
  }

  public static void SaveFile()
  {
    String json = genson.serialize(players);

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(authFile)))
    {
      bw.write(json);

      bw.flush();
    }
    catch (IOException e)
    {
      LOGGER.catching(e);
    }
  }
}

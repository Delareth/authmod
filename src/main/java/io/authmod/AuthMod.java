package io.authmod;

import io.authmod.command.LoggedCommand;
import io.authmod.command.LoginCommand;
import io.authmod.command.RegisterCommand;
import io.authmod.freeze.Handler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

import io.authmod.data.FileController;

import java.io.File;
import java.nio.file.Paths;

@Mod(modid = AuthMod.ModID, name = AuthMod.NAME, version = AuthMod.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class AuthMod {
  public static final String ModID = "authmod";
  static final String NAME = "AuthMod";
  static final String VERSION = "1.0";
  public static Logger LOGGER = FMLLog.log;

  public static Handler Handler;

  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent event) throws Exception {
    AuthMod.LOGGER = event.getModLog();
    LOGGER.info(ModID + " " + VERSION + " loading mod...");

    File file = Paths.get(event.getModConfigurationDirectory().getAbsolutePath(), ModID + "_players.json").toFile();

    if(!file.exists())
    {
      file.createNewFile();
    }

    FileController.Init(file);
  }

  @Mod.EventHandler
  public void serverStarting(FMLServerStartingEvent event) {
    LOGGER.info("Registering AuthMod event handler");
    this.Handler = new Handler();
    MinecraftForge.EVENT_BUS.register(this.Handler);

    LOGGER.info("Registering command /register");
    event.registerServerCommand(new RegisterCommand());

    LOGGER.info("Registering command /login");
    event.registerServerCommand(new LoginCommand());

    LOGGER.info("Registering command /logged");
    event.registerServerCommand(new LoggedCommand());
  }
}

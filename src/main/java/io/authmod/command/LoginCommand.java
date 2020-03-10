package io.authmod.command;

import io.authmod.AuthMod;
import io.authmod.config.AuthModConfig;
import io.authmod.data.FileController;
import io.authmod.data.Validator;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class LoginCommand implements ICommand {
  private static final Logger LOGGER = AuthMod.LOGGER;
  private final List<String> aliases;

  public LoginCommand()
  {
    this.aliases = new ArrayList<>();
    this.aliases.add("log");
  }

  @Override
  public String getName() {
    return "login";
  }

  @Override
  public String getUsage(ICommandSender sender) {
    return AuthModConfig.LoginUsage;
  }

  @Override
  public List<String> getAliases() {
    return aliases;
  }

  @Override
  public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
    EntityPlayer player = (EntityPlayer) sender;

    if (args.length != 1)
    {
      sender.sendMessage(new TextComponentString(AuthModConfig.LoginUsage));
      return;
    }

    if (!FileController.IsRegistered(player.getName()))
    {
      sender.sendMessage(new TextComponentString(AuthModConfig.UsernameNotExits));
      return;
    }

    if (!Validator.IsPasswordsEqual(FileController.GetPassword(player.getName()), args[0]))
    {
      sender.sendMessage(new TextComponentString(AuthModConfig.InvalidLoginPassword));
      return;
    }

    AuthMod.Handler.AuthorizePlayer(player);

    sender.sendMessage(new TextComponentString(AuthModConfig.SuccessfulLogin));

    LOGGER.info(player.getName() + " successful login");
  }

  @Override
  public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
    return true;
  }

  @Override
  public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
    return new ArrayList<>();
  }

  @Override
  public boolean isUsernameIndex(String[] args, int index) {
    return true;
  }

  @Override
  public int compareTo(ICommand iCommand) {
    return this.getName().compareTo(iCommand.getName());
  }
}

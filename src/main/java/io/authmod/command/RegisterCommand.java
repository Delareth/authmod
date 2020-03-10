package io.authmod.command;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import io.authmod.config.AuthModConfig;
import io.authmod.data.FileController;
import io.authmod.data.Validator;
import org.apache.logging.log4j.Logger;

import io.authmod.AuthMod;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class RegisterCommand implements ICommand {
  private static final Logger LOGGER = AuthMod.LOGGER;
  private final List<String> aliases;

  public RegisterCommand() {
    aliases = new ArrayList<>();
    aliases.add("reg");
  }

  @Override
  public String getName() {
    return "register";
  }

  @Override
  public String getUsage(ICommandSender sender) {
    return AuthModConfig.RegisterUsage;
  }

  @Override
  public List<String> getAliases() {
    return aliases;
  }

  @Override
  public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
    EntityPlayer player = (EntityPlayer) sender;

    if(FileController.IsRegistered(player.getName()))
    {
      sender.sendMessage(new TextComponentString(AuthModConfig.UsernameExist));
      return;
    }

    if(args.length != 2)
    {
      sender.sendMessage(new TextComponentString(AuthModConfig.RegisterUsage));
      return;
    }

    String sIsNameValid = Validator.IsNameValid(player.getName());

    if(sIsNameValid != "correct")
    {
      sender.sendMessage(new TextComponentString(sIsNameValid));
      return;
    }

    String sIsPasswordValid = Validator.IsPasswordValid(args[0]);

    if(sIsPasswordValid != "correct")
    {
      sender.sendMessage(new TextComponentString(sIsPasswordValid));
      return;
    }

    if(!Validator.IsPasswordsEqual(args[0], args[1]))
    {
      sender.sendMessage(new TextComponentString(AuthModConfig.InvalidPasswordRepeat));
      return;
    }

    FileController.RegisterNew(player.getName(), args[0]);
    AuthMod.Handler.AuthorizePlayer(player);

    sender.sendMessage(new TextComponentString(AuthModConfig.SuccessfulRegister));

    LOGGER.info(player.getName() + " successful registered");
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

package io.authmod.command;

import io.authmod.config.AuthModConfig;
import io.authmod.data.Logged;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class LoggedCommand implements ICommand {
  private final List<String> aliases;

  public LoggedCommand() {
    this.aliases = new ArrayList<>();
    this.aliases.add("logged?");
  }

  @Override
  public String getName() {
    return "logged";
  }

  @Override
  public String getUsage(ICommandSender sender) {
    return AuthModConfig.LoggedUsage;
  }

  @Override
  public List<String> getAliases() {
    return aliases;
  }

  @Override
  public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
    EntityPlayer player = (EntityPlayer) sender;
    boolean logged = Logged.IsLogged(player.getName());
    sender.sendMessage(new TextComponentString(logged ? AuthModConfig.LoggedYes : AuthModConfig.LoggedNo));
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

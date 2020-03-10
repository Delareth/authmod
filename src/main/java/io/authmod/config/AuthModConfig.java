package io.authmod.config;

import io.authmod.AuthMod;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;

@Config(modid = AuthMod.ModID)
public class AuthModConfig {
  public static String UsernameContainsSpace = "Имя содержит пробелы";
  public static String UsernameContainsCyrillic = "Имя содержит русские буквы";
  public static String UsernameEmpty = "Некорректное имя";
  public static String UsernameExist = "Данный аккаунт уже зарегистрирован на нашем сервере";
  public static String UsernameNotExits = "Данный аккаунт еще не зарегистрирован на нашем сервере, используйте /register";

  public static String InvalidLoginPassword = "Неверный пароль. Попробуй еще разок";
  public static String InvalidPasswordLength = "Длина пароля должна быть от 4 до 16 символов";
  public static String InvalidPasswordRepeat = "Повторение пароля не совпадает, попробуй еще разок.";
  public static String InvalidPasswordCyrillic = "Пароль не должен содержать русские символы.";

  public static String SuccessfulRegister = "Вы успешно прошли регистрацию! Приятной игры.";
  public static String SuccessfulLogin = "Вы успешно вошли в аккаунт! Приятной игры.";

  public static String RegisterUsage = "/register <пароль> <повторение пароля> - Будь осторожен при выборе пароля, он вводится при каждом входе в игру.";
  public static String LoginUsage = "/login <пароль> - позволяет авторизоваться на сервере";
  public static String LoggedUsage = "/logged - вошли вы в аккаунт или нет";

  public static String WelcomeMsg = "Добро пожаловать на наш сервер! Используй /register для регистрации или /login для входа в игру.";

  public static String LoggedYes = "Ты авторизован";
  public static String LoggedNo = "Ты еще не авторизован";

  public static String authDelayMsg = "Не спи, у тебя только %s секунд для входа в игру";
  public static int authDelay = 60;
}

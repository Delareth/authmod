package io.authmod.data;

import io.authmod.config.AuthModConfig;

import java.util.regex.Pattern;

public class Validator {
  public static String IsNameValid(String sUsername)
  {
    if (sUsername.split(" ").length != 1) return AuthModConfig.UsernameContainsSpace;
    if (Pattern.matches(".*\\p{InCyrillic}.*", sUsername)) return AuthModConfig.UsernameContainsCyrillic;
    if (sUsername.isEmpty()) return AuthModConfig.UsernameEmpty;

    return "correct";
  }

  public static String IsPasswordValid(String sPassword)
  {
    if (sPassword.length() < 4 || sPassword.length() > 16) return AuthModConfig.InvalidPasswordLength;
    if (Pattern.matches(".*\\p{InCyrillic}.*", sPassword)) return AuthModConfig.InvalidPasswordCyrillic;

    return "correct";
  }

  public static boolean IsPasswordsEqual(String sFirstPassword, String sSecondPassword)
  {
    return sFirstPassword.equals(sSecondPassword);
  }
}

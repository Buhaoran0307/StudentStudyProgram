package util;

import java.util.regex.Pattern;

public class InputValidation {
    /**
     * this method is used to check whether input phone number is 8 numbers integer.
     * @param input input phone number
     * @return valid phone number or not
     */
    public static boolean checkPhoneNumber(String input)
    {
        String regex = "\\d{8}";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }

    /**
     * this method check password
     * whether it contains at least one uppercase letter, one lowercase letter, and one digit, and no other symbols
     * @param input input password
     * @return valid password or not
     */
    public static boolean checkPassword(String input)
    {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }
}

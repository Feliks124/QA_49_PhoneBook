package utils;

import dto.User;
import net.datafaker.Faker;

import java.util.*;


public class UserFactory {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*";
    private static final String ALL = UPPER + LOWER + DIGITS + SPECIAL;

    public static String generatePassword(int length) {
        Random random = new Random();

        if (length < 1) {
            throw new IllegalArgumentException("Min length: 1 character");
        }
        List<Character> passwordChars = new ArrayList<>();
        if (length >= 4) {
            passwordChars.add(UPPER.charAt(random.nextInt(UPPER.length())));
            passwordChars.add(LOWER.charAt(random.nextInt(LOWER.length())));
            passwordChars.add(DIGITS.charAt(random.nextInt(DIGITS.length())));
            passwordChars.add(SPECIAL.charAt(random.nextInt(SPECIAL.length())));

            for (int i = 4; i < length; i++) {
                passwordChars.add(ALL.charAt(random.nextInt(ALL.length())));
            }
        } else for (int i = 0; i < length; i++) {
            passwordChars.add(ALL.charAt(random.nextInt(ALL.length())));
        }
        Collections.shuffle(passwordChars);
        StringBuilder password = new StringBuilder();
        for (char c : passwordChars) {
            password.append(c);
        }
        return password.toString();
    }

    static Faker faker = new Faker();

    public static User randomUser (int lengthPassword) {
        return new User(faker.internet().emailAddress(), generatePassword(8));
    }

    public static String randomValidEmail(){
        return faker.internet().emailAddress();
    }

}

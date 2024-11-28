package kr.co.shortenurlservice.domain;

import java.util.Random;

public class RandomShortKeyGenerator implements ShortKeyGenerator {
    private static final String CHARACTERS = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private static final int KEY_LENGTH = 5;
    Random random = new Random();

    @Override
    public String generateKey() {
        StringBuilder keyBuilder = new StringBuilder(KEY_LENGTH);
        for (int i = 0; i < KEY_LENGTH; i++) {
            keyBuilder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return keyBuilder.toString();
    }
}

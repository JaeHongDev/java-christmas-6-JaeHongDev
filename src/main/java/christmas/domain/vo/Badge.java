package christmas.domain.vo;

import java.util.Arrays;
import java.util.Collections;

public enum Badge {
    NONE("없음", 0),
    STAR("별", 5000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);
    private final String name;
    private final int price;

    Badge(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static Badge of(int price) {
        return Arrays.stream(values())
                .sorted(Collections.reverseOrder())
                .filter(badge -> badge.price <= price)
                .findFirst()
                .orElse(NONE);
    }
}

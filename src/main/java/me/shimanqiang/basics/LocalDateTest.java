package me.shimanqiang.basics;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2020/3/29.
 */

public class LocalDateTest {

    /**
     * @DateTimeFormat
     * @param args
     */
    public static void main(String[] args) {

        LocalDate now = LocalDate.now();
        System.out.println(now);
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now1);
    }
}

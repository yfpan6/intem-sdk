package com.intemanage.sdk.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/**
 * @Author panyunfeng04
 * @Date 2019-11-01
 */
@Slf4j
public class Times {

    public static final class Millis {
        public static final long ONE_SECOND_MILLIS = 1000;
        public static final long ONE_MINUTE_MILLIS = 60 * ONE_SECOND_MILLIS;
        public static final long ONE_HOUR_MILLIS = 60 * ONE_MINUTE_MILLIS;
        public static final long ONE_DAY_HOUR_MILLIS = 24 * ONE_HOUR_MILLIS;

        public static void sleep(long millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                log.error("Interrupted when sleeping.", e);
            }
        }

        public static void sleep(long millis, Consumer<InterruptedException> exceptionHandler) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                exceptionHandler.accept(e);
            }
        }

    }

    public static final class Second {
        public static final long ONE_SECOND = 1;
        public static final long ONE_MINUTE_SECONDS = 60;
        public static final long ONE_HOUR_SECONDS = 60 * ONE_MINUTE_SECONDS;
        public static final long ONE_DAY_SECONDS = 24 * ONE_HOUR_SECONDS;

        public static void sleep(long second) {
            Millis.sleep(second * Millis.ONE_SECOND_MILLIS);
        }

        public static void sleep(long second, Consumer<InterruptedException> exceptionHandler) {
            Millis.sleep(second * Millis.ONE_SECOND_MILLIS, exceptionHandler);
        }

        public static long toMills(long second) {
            return second * Millis.ONE_SECOND_MILLIS;
        }
    }

    public static final class Minute {
        public static final int ONE_MINUTE = 1;
        public static final int ONE_HOUR_MINUTES = 60;
        public static final int ONE_DAY_MINUTES = 24 * ONE_HOUR_MINUTES;

        public static void sleep(int minute) {
            Millis.sleep(minute * Millis.ONE_MINUTE_MILLIS);
        }

        public static void sleep(long minute, Consumer<InterruptedException> exceptionHandler) {
            Millis.sleep(minute * Millis.ONE_MINUTE_MILLIS, exceptionHandler);
        }

        public static long toSeconds(int minute) {
            return minute * Second.ONE_MINUTE_SECONDS;
        }

        public static long toMills(int minute) {
            return minute * Millis.ONE_MINUTE_MILLIS;
        }
    }

    public static final class Hour {
        public static final int ONE_HOUR = 1;
        public static final int ONE_DAY_HOURS= 24;

        public static void sleep(int hour) {
            Millis.sleep(hour * Millis.ONE_HOUR_MILLIS);
        }

        public static void sleep(int hour, Consumer<InterruptedException> exceptionHandler) {
            Millis.sleep(hour * Millis.ONE_HOUR_MILLIS, exceptionHandler);
        }

        public static int toMinutes(int hour) {
            return hour * Minute.ONE_HOUR_MINUTES;
        }

        public static long toSeconds(int hour) {
            return hour * Second.ONE_HOUR_SECONDS;
        }

        public static long toMills(int hour) {
            return hour * Millis.ONE_HOUR_MILLIS;
        }

    }

}

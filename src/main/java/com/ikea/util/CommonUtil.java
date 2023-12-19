package com.ikea.util;

import java.util.concurrent.ThreadLocalRandom;

public class CommonUtil {

  public static String generateAuthNum(int size) {
    int origin = (int) Math.pow(10, (size - 1));
    int bound = (int) Math.pow(10, size);
    return Integer.toString(ThreadLocalRandom.current().nextInt(origin, bound));
  }

}

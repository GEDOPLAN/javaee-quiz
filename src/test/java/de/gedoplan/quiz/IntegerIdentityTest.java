package de.gedoplan.quiz;

import org.junit.Test;

public class IntegerIdentityTest {

  @Test
  public void testIntegerIdentity() {
    Integer i = 1;
    Integer j;
    do {
      j = i + 1;
      i++;
    } while (i == j);

    System.out.println(i);
  }
}

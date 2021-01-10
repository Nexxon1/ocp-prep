package de.mino.chapter5.string;

import java.time.LocalTime;

public class StringPlayground {

  public static void main(String[] args) {
    comparingStrings();

    concatenation();

    commonMethods();

    usingStringBuilder();
  }

  private static void comparingStrings() {
    System.out.println("--- Comparing Strings ---");
    String s1 = "bunny";
    String s2 = "bunny";
    String s3 = new String("bunny");
    // The s1 and s2 references point to the same literal in the String pool
    System.out.println(s1 == s2); // true
    // Line 12 creates a new object in memory. So s1 and s3 point to different objects in memory
    System.out.println(s1 == s3); // false
    // Comparing s1 and s3 returns true because here we look at the value, not if the references
    // point to the same object.
    System.out.println(s1.equals(s3)); // true
  }

  private static void concatenation() {
    System.out.println("\n--- Concatenation of Strings ---");
    System.out.println("The operators are processed from left to right.");
    System.out.println("A String concatenated with anything else is a String");
    String s1 = "1" + 2 + 3;
    String s2 = 1 + 2 + "3";
    System.out.println(s1); // 123
    System.out.println(s2); // 33
    LocalTime t = null;
    String s3 = "foo" + t;
    System.out.println(s3); // foonull
  }

  private static void commonMethods() {
    System.out.println("\n--- Common String methods ---");
    String s = "abcde ";
    System.out.println(s.trim().length()); // 5
    System.out.println(s.charAt(4)); // e
    System.out.println(s.indexOf('e')); // 4
    System.out.println(s.indexOf("de")); // 3
    System.out.println(s.substring(2, 4).toUpperCase()); // CD
    System.out.println(s.replace('a', '1')); // '1bcde '
    System.out.println(s.contains("DE")); // false
    System.out.println(s.startsWith("a")); // true
  }

  private static void usingStringBuilder() {
    System.out.println("\n--- Common StringBuilder methods ---");
    System.out.println("StringBuilder is mutable and can change value and increase in capacity");
    System.out.println(
        "StringBuilder should be the preferred option when updating the value in a loop");
    StringBuilder b = new StringBuilder();
    b.append(12345).append('-');
    System.out.println(b); // 12345-
    System.out.println(b.length()); // 6
    System.out.println(b.indexOf("-")); // 5
    System.out.println(b.charAt(2)); // 3

    // Reverse the StringBuilder and return a reference tot he same object. --> b & b2 point to the
    // same object.
    StringBuilder b2 = b.reverse();
    System.out.println(b.toString()); // -54321
    System.out.println(b == b2); // true

    StringBuilder s = new StringBuilder("abcde");
    // Insert '-' at index 1 and then remove the char at index 3
    s.insert(1, '-').delete(3, 4);
    System.out.println(s); // a-bde
    System.out.println(s.subSequence(2, 4)); // bd
  }
}

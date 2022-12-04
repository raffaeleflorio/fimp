package io.github.raffaeleflorio.fimp;

/**
 * A textual term such as a word
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public interface Term {

  /**
   * @return Its textual representation
   */
  String text();

  /**
   * A stub useful for testing
   *
   * @author Raffaele Florio (raffaeleflorio@protonmail.com)
   * @since 1.0.0
   */
  final class Stub implements Term {

    private final String text;


    /**
     * @param text The textual representation
     */
    public Stub(final String text) {
      this.text = text;
    }

    @Override
    public String text() {
      return this.text;
    }
  }
}

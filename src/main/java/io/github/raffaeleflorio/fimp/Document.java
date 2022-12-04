package io.github.raffaeleflorio.fimp;

import java.util.UUID;

/**
 * A textual document
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public interface Document {

  /**
   * @return Its unique id
   */
  UUID id();

  /**
   * @return Its terms
   */
  Terms terms();

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
  final class Stub implements Document {

    private final UUID id;
    private final Terms terms;
    private final String text;

    /**
     * @param id    The id
     * @param terms The terms
     * @param text  The text
     */
    public Stub(final UUID id, final Terms terms, final String text) {
      this.id = id;
      this.terms = terms;
      this.text = text;
    }

    @Override
    public UUID id() {
      return this.id;
    }

    @Override
    public Terms terms() {
      return this.terms;
    }

    @Override
    public String text() {
      return this.text;
    }
  }
}

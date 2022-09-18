package io.github.raffaeleflorio.fimp;

import java.util.UUID;

/**
 * A text
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public interface Text {

  /**
   * Builds its tokens
   *
   * @return Its tokens
   */
  Tokens tokens();

  String asString();

  /**
   * A text useful for testing
   *
   * @author Raffaele Florio (raffaeleflorio@protonmail.com)
   * @since 1.0.0
   */
  final class Fake implements Text {

    private final Tokens tokens;
    private final String string;

    /**
     * Builds a random fake
     */
    public Fake() {
      this(UUID.randomUUID().toString());
    }

    /**
     * Builds a fake with a random string representation, but with given tokens
     *
     * @param tokens The tokens
     */
    public Fake(final String... tokens) {
      this(new Tokens.Fake(tokens), UUID.randomUUID().toString());
    }

    /**
     * Builds a fake with given tokens and string representation
     *
     * @param tokens The tokens
     * @param string The string representation
     */
    public Fake(final Tokens tokens, final String string) {
      this.tokens = tokens;
      this.string = string;
    }

    @Override
    public Tokens tokens() {
      return this.tokens;
    }

    @Override
    public String asString() {
      return this.string;
    }
  }
}

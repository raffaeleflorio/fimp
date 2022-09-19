package io.github.raffaeleflorio.fimp;

import java.util.UUID;

/**
 * A text is a human-readable object which shapes itself around identification tokens (e.g. words)
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public interface Text {

  /**
   * Provides its tokens
   *
   * @return The tokens
   */
  Tokens tokens();

  /**
   * Provides its human-readable representation
   *
   * @return The representation
   */
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
     * Builds a fake with a random human-readable representation, but with given tokens
     *
     * @param tokens The tokens
     */
    public Fake(final String... tokens) {
      this(new Tokens.Fake(tokens), UUID.randomUUID().toString());
    }

    /**
     * Builds a fake with given tokens and human-readable representation
     *
     * @param tokens The tokens
     * @param string The human-readable representation
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

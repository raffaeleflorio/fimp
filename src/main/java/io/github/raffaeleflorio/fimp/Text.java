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

  /**
   * Builds its char sequence representation
   *
   * @return Its charsequence representation
   */
  CharSequence asCharSequence();

  /**
   * A text useful for testing
   *
   * @author Raffaele Florio (raffaeleflorio@protonmail.com)
   * @since 1.0.0
   */
  final class Fake implements Text {

    private final Tokens tokens;
    private final CharSequence charSequence;

    /**
     * Builds a random fake
     */
    public Fake() {
      this(UUID.randomUUID().toString());
    }


    /**
     * Builds a fake with a random char sequence representation, but with given its tokens
     *
     * @param tokens The tokens
     */
    public Fake(final String... tokens) {
      this(new Tokens.Fake(tokens), UUID.randomUUID().toString());
    }

    /**
     * Builds a fake with given tokens and char sequence representation
     *
     * @param tokens       The tokens
     * @param charSequence The char sequence representation
     */
    public Fake(final Tokens tokens, final CharSequence charSequence) {
      this.tokens = tokens;
      this.charSequence = charSequence;
    }

    @Override
    public Tokens tokens() {
      return this.tokens;
    }

    @Override
    public CharSequence asCharSequence() {
      return this.charSequence;
    }
  }
}

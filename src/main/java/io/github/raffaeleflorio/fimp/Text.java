package io.github.raffaeleflorio.fimp;

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
}

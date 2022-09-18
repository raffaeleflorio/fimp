package io.github.raffaeleflorio.fimp;

import java.util.stream.Stream;

/**
 * Set of token
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public interface Tokens extends Iterable<CharSequence> {

  /**
   * Builds its stream of tokens
   *
   * @return Its stream
   */
  Stream<CharSequence> stream();
}

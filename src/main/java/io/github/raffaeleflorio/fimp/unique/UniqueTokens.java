package io.github.raffaeleflorio.fimp.unique;

import io.github.raffaeleflorio.fimp.Tokens;

import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Group of distinct tokens
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public final class UniqueTokens implements Tokens {

  private final Tokens origin;

  /**
   * Builds unique tokens
   *
   * @param origin The tokens
   */
  UniqueTokens(final Tokens origin) {
    this.origin = origin;
  }

  @Override
  public Stream<String> stream() {
    return this.origin.stream().distinct();
  }

  @Override
  public Iterator<String> iterator() {
    return this.stream().iterator();
  }
}

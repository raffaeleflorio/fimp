package io.github.raffaeleflorio.fimp.simple;

import io.github.raffaeleflorio.fimp.Tokens;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * An immutable implementation of tokens
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public final class ConstTokens implements Tokens {

  private final String[] tokens;
  private final Function<String[], Stream<String>> streamFn;

  /**
   * Builds tokens
   *
   * @param tokens The human-readable tokens
   */
  public ConstTokens(final String[] tokens) {
    this(tokens, Arrays::stream);
  }

  ConstTokens(final String[] tokens, final Function<String[], Stream<String>> streamFn) {
    this.tokens = tokens;
    this.streamFn = streamFn;
  }

  @Override
  public Stream<String> stream() {
    return this.streamFn.apply(this.tokens);
  }

  @Override
  public Iterator<String> iterator() {
    return this.stream().iterator();
  }
}

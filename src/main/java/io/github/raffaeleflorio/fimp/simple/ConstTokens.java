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

  private final CharSequence[] tokens;
  private final Function<CharSequence[], Stream<CharSequence>> streamFn;

  /**
   * Builds tokens
   *
   * @param tokens The tokens
   */
  public ConstTokens(final CharSequence[] tokens) {
    this(tokens, Arrays::stream);
  }

  ConstTokens(final CharSequence[] tokens, final Function<CharSequence[], Stream<CharSequence>> streamFn) {
    this.tokens = tokens;
    this.streamFn = streamFn;
  }

  @Override
  public Stream<CharSequence> stream() {
    return this.streamFn.apply(this.tokens).distinct();
  }

  @Override
  public Iterator<CharSequence> iterator() {
    return this.stream().iterator();
  }
}

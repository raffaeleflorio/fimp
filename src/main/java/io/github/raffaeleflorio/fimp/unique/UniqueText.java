package io.github.raffaeleflorio.fimp.unique;

import io.github.raffaeleflorio.fimp.Text;
import io.github.raffaeleflorio.fimp.Tokens;

import java.util.function.Function;

/**
 * A text with distinct tokens
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public final class UniqueText implements Text {

  private final Text origin;
  private final Function<Tokens, Tokens> uniqueTokensFn;

  /**
   * Builds an unique text
   *
   * @param origin The text
   */
  public UniqueText(final Text origin) {
    this(origin, UniqueTokens::new);
  }

  UniqueText(final Text origin, final Function<Tokens, Tokens> uniqueTokensFn) {
    this.origin = origin;
    this.uniqueTokensFn = uniqueTokensFn;
  }

  @Override
  public Tokens tokens() {
    return this.uniqueTokensFn.apply(this.origin.tokens());
  }

  @Override
  public String asString() {
    return this.origin.asString();
  }
}

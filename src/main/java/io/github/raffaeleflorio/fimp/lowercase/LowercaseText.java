package io.github.raffaeleflorio.fimp.lowercase;

import io.github.raffaeleflorio.fimp.Text;
import io.github.raffaeleflorio.fimp.Tokens;

import java.util.Locale;
import java.util.function.Function;

/**
 * A text with lowercase tokens
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public final class LowercaseText implements Text {

  private final Text origin;
  private final Locale locale;
  private final Function<Tokens, Tokens> lowercaseTokensFn;

  /**
   * Builds lowercase text in a default locale
   *
   * @param origin The text
   */
  public LowercaseText(final Text origin) {
    this(origin, Locale.getDefault());
  }

  /**
   * Builds lowercase localised text
   *
   * @param origin The text
   * @param locale The locale
   */
  public LowercaseText(final Text origin, final Locale locale) {
    this(origin, locale, tokens -> new LowercaseTokens(tokens, locale));
  }

  LowercaseText(final Text origin, final Locale locale, final Function<Tokens, Tokens> lowercaseTokensFn) {
    this.origin = origin;
    this.locale = locale;
    this.lowercaseTokensFn = lowercaseTokensFn;
  }

  @Override
  public Tokens tokens() {
    return this.lowercaseTokensFn.apply(
      this.origin.tokens()
    );
  }

  @Override
  public String asString() {
    return this.origin.asString();
  }
}

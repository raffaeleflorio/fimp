package io.github.raffaeleflorio.fimp.lowercase;

import io.github.raffaeleflorio.fimp.Tokens;

import java.util.Iterator;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * Lowercase group of tokens
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public final class LowercaseTokens implements Tokens {

  private final Tokens origin;
  private final Locale locale;


  /**
   * Builds localised lowercase tokens
   *
   * @param origin The tokens
   * @param locale The locale
   */
  public LowercaseTokens(final Tokens origin, final Locale locale) {
    this.origin = origin;
    this.locale = locale;
  }

  @Override
  public Stream<String> stream() {
    return this.origin.stream().map(token -> token.toLowerCase(this.locale));
  }

  @Override
  public Iterator<String> iterator() {
    return this.stream().iterator();
  }
}

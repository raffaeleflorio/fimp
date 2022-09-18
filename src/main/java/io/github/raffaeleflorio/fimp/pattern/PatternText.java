package io.github.raffaeleflorio.fimp.pattern;

import io.github.raffaeleflorio.fimp.Text;
import io.github.raffaeleflorio.fimp.Tokens;
import io.github.raffaeleflorio.fimp.simple.ConstTokens;

import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * A text separating its tokens according a pattern
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public final class PatternText implements Text {

  private final String text;
  private final Pattern pattern;
  private final Function<String[], Tokens> tokensFn;

  /**
   * Builds a text
   *
   * @param text    The text
   * @param pattern The pattern
   */
  public PatternText(final String text, final String pattern) {
    this(text, Pattern.compile(pattern));
  }

  /**
   * Builds a text
   *
   * @param text    The text
   * @param pattern The pattern
   */
  public PatternText(final String text, final Pattern pattern) {
    this(text, pattern, ConstTokens::new);
  }

  PatternText(final String text, final Pattern pattern, final Function<String[], Tokens> tokensFn) {
    this.text = text;
    this.pattern = pattern;
    this.tokensFn = tokensFn;
  }

  @Override
  public Tokens tokens() {
    return this.tokensFn.apply(
      this.pattern.split(this.text)
    );
  }

  @Override
  public String asString() {
    return this.text;
  }
}

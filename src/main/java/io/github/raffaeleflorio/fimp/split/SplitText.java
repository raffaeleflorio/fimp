package io.github.raffaeleflorio.fimp.split;

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
public final class SplitText implements Text {

  private final CharSequence text;
  private final Pattern pattern;
  private final Function<CharSequence[], Tokens> tokensFn;

  /**
   * Builds a text
   *
   * @param text    The text
   * @param pattern The pattern
   */
  public SplitText(final CharSequence text, final Pattern pattern) {
    this(text, pattern, ConstTokens::new);
  }

  SplitText(final CharSequence text, final Pattern pattern, final Function<CharSequence[], Tokens> tokensFn) {
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
  public CharSequence asCharSequence() {
    return this.text;
  }
}

package io.github.raffaeleflorio.fimp.whitespace;

import io.github.raffaeleflorio.fimp.Text;
import io.github.raffaeleflorio.fimp.Tokens;
import io.github.raffaeleflorio.fimp.split.SplitText;

import java.util.regex.Pattern;

/**
 * A text which distinguishes its tokens according whitespaces
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public final class WhitespaceText implements Text {

  private final Text origin;

  /**
   * Builds a text
   *
   * @param text The text
   */
  public WhitespaceText(final CharSequence text) {
    this(new SplitText(text, Pattern.compile("\\s+")));
  }

  WhitespaceText(final Text origin) {
    this.origin = origin;
  }

  @Override
  public Tokens tokens() {
    return this.origin.tokens();
  }

  @Override
  public CharSequence asCharSequence() {
    return this.origin.asCharSequence();
  }
}

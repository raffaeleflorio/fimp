package io.github.raffaeleflorio.fimp.lowercase;

import io.github.raffaeleflorio.fimp.Text;
import io.github.raffaeleflorio.fimp.Tokens;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LowercaseTextTest {

  @Test
  void shouldNotMutate_Its_String_Representation() {
    var expected = "THIS IS UPPERCASE, more OR lESS";
    assertThat(
      new LowercaseText(
        new Text.Fake(new Tokens.Fake(), expected)
      ).asString()
    ).isEqualTo(expected);
  }

  @Test
  void shouldLowercase_Its_Tokens() {
    assertThat(
      new LowercaseText(
        new Text.Fake("THIS", "IS", "UPPERCASE", "more", "OR", "lESS")
      ).tokens()
    ).containsExactly("this", "is", "uppercase", "more", "or", "less");
  }
}

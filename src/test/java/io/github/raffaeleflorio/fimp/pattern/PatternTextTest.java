package io.github.raffaeleflorio.fimp.pattern;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PatternTextTest {

  @Test
  void shouldTokenize_According_ItsPattern() {
    assertThat(
      new PatternText("This text'll be tokenized_to 7, tokens.", "\\W+").tokens()
    ).containsExactly("This", "text", "ll", "be", "tokenized_to", "7", "tokens");
  }

  @Test
  void shouldNotMutate_Its_String_Representation() {
    var expected = "This,is,splitted,when,comma,is,encountered,!";
    assertThat(
      new PatternText(expected, ",+").asString()
    ).isEqualTo(expected);
  }
}

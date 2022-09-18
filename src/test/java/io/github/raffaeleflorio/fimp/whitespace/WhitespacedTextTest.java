package io.github.raffaeleflorio.fimp.whitespace;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WhitespacedTextTest {

  @Test
  void shouldNotMutate_Its_CharSequence_Representation() {
    var expected = "This text shouldn't mutate.";
    assertThat(
      new WhitespacedText(expected).asCharSequence()
    ).isEqualTo(expected);
  }

  @Test
  void shouldTokenize_Using_Space() {
    assertThat(
      new WhitespacedText("This text'll Be tokenized accorDing WhiteSPACE!").tokens()
    ).containsExactly("This", "text'll", "Be", "tokenized", "accorDing", "WhiteSPACE!");
  }

  @Test
  void shouldTokenize_Using_All_Whitespace_Characters() {
    assertThat(
      new WhitespacedText("This\ttext'll\nBe\rtokenized\taccorDing\u000bWhiteSPACE!\fEND").tokens()
    ).containsExactly("This", "text'll", "Be", "tokenized", "accorDing", "WhiteSPACE!", "END");
  }

  @Test
  void shouldNotInclude_Empty_Tokens() {
    assertThat(
      new WhitespacedText("a  b   c").tokens()
    ).containsExactly("a", "b", "c");
  }

  @Test
  void shouldInclude_NumberTokens() {
    assertThat(
      new WhitespacedText("this 1's good").tokens()
    ).containsExactly("this", "1's", "good");
  }
}

package io.github.raffaeleflorio.fimp.unique;

import io.github.raffaeleflorio.fimp.Text;
import io.github.raffaeleflorio.fimp.Tokens;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UniqueTextTest {

  @Test
  void shouldNotMutate_Its_String_Representation() {
    var expected = "this this should not not not mutate mutante";
    assertThat(
      new UniqueText(new Text.Fake(new Tokens.Fake(), expected)).asString()
    ).isEqualTo(expected);
  }

  @Test
  void shouldFilterOut_Duplicate_Tokens() {
    assertThat(
      new UniqueText(new Text.Fake("single", "token", "token", "single")).tokens()
    ).containsExactly("single", "token");
  }
}

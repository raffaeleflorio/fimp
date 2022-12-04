package io.github.raffaeleflorio.fimp;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TermTest {

  @Nested
  class StubTest {

    @Test
    void provides_the_stubbed_textual_representation_without_changes() {
      var text = "stubbed text";

      assertThat(new Term.Stub(text).text())
        .isEqualTo(text);
    }
  }
}

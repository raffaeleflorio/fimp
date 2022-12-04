package io.github.raffaeleflorio.fimp;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class TermsTest {

  @Nested
  class StubTest {

    @Test
    void iterates_over_the_provided_terms() {
      var iterable = new Term[]{
        this.aTerm(),
        this.aTerm(),
        this.aTerm(),
        this.aTerm()
      };

      assertThat(new Terms.Stub(iterable))
        .containsExactly(iterable);
    }

    private Term aTerm() {
      return new Term.Stub(this.aText());
    }

    private String aText() {
      return UUID.randomUUID().toString();
    }

    @Test
    void is_empty_when_no_terms_are_provided() {
      assertThat(new Terms.Stub())
        .isEmpty();
    }
  }
}

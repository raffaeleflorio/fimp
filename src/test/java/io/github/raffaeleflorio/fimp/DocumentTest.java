package io.github.raffaeleflorio.fimp;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class DocumentTest {

  @Nested
  class StubTest {

    @Test
    void provides_the_stubbed_id_without_changes() {
      var id = UUID.fromString("055f8237-0cd7-4a6c-bbcf-6cbf5c6c4f6e");

      assertThat(new Document.Stub(id, this.aTerms(), this.aText()).id())
        .isEqualTo(id);
    }

    private Terms aTerms() {
      return new Terms.Stub();
    }

    private String aText() {
      return UUID.randomUUID().toString();
    }

    @Test
    void provides_the_stubbed_terms_without_changes() {
      var terms = new Terms.Stub(
        this.aTerm(),
        this.aTerm(),
        this.aTerm()
      );

      assertThat(new Document.Stub(this.anId(), terms, this.aText()).terms())
        .isEqualTo(terms);
    }

    private Term aTerm() {
      return new Term.Stub(this.aText());
    }

    private UUID anId() {
      return UUID.randomUUID();
    }

    @Test
    void provides_the_stubbed_textual_representation_without_changes() {
      var text = "a stubbed text for the document";

      assertThat(new Document.Stub(this.anId(), this.aTerms(), text).text())
        .isEqualTo(text);
    }
  }
}

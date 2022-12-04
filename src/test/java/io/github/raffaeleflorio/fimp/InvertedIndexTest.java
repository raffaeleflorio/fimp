package io.github.raffaeleflorio.fimp;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class InvertedIndexTest {


  @Nested
  class StubTest {

    @Test
    void provides_the_stubbed_documents_regardless_the_term() {
      var documents = new Documents.Stub(
        this.aSetOfDocuments(),
        this.aSetOfDocuments(),
        this.aSetOfDocuments(),
        this.aSetOfDocuments()
      );

      assertThat(new InvertedIndex.Stub(documents).documents(this.aTerm()))
        .isEqualTo(documents);
    }

    private Set<Document> aSetOfDocuments() {
      return Set.of();
    }

    private Term aTerm() {
      return new Term.Stub(this.aText());
    }

    private String aText() {
      return UUID.randomUUID().toString();
    }

    @Test
    void indexes_does_not_change_stubbed_documents() {
      var documents = new Documents.Stub(
        this.aSetOfDocuments(),
        this.aSetOfDocuments(),
        this.aSetOfDocuments(),
        this.aSetOfDocuments()
      );
      var invertedIndex = new InvertedIndex.Stub(documents);

      invertedIndex.index(this.aDocument());

      assertThat(invertedIndex.documents(this.aTerm()))
        .isEqualTo(documents);
    }

    private Document aDocument() {
      return new Document.Stub(this.anId(), this.aTerms(), this.aText());
    }

    private UUID anId() {
      return UUID.randomUUID();
    }

    private Terms aTerms() {
      return new Terms.Stub();
    }
  }
}

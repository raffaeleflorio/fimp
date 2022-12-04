package io.github.raffaeleflorio.fimp;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class DocumentsTest {

  @Nested
  class StubTest {

    @Test
    void iterates_over_the_provided_documents() {
      var documents = Set.of(
        this.aDocument(),
        this.aDocument()
      );

      assertThat(new Documents.Stub(documents, this.aSetOfDocuments(), this.aSetOfDocuments(), this.aSetOfDocuments()))
        .containsExactlyElementsOf(documents);
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

    private String aText() {
      return UUID.randomUUID().toString();
    }

    private Set<Document> aSetOfDocuments() {
      return Set.of();
    }

    @Test
    void union_is_equal_to_the_provided_one_regardless_the_other_documents() {
      var union = Set.of(
        this.aDocument(),
        this.aDocument(),
        this.aDocument()
      );

      assertThat(new Documents.Stub(this.aSetOfDocuments(), union, this.aSetOfDocuments(), this.aSetOfDocuments())
        .union(this.aDocuments())
      ).containsExactlyElementsOf(union);
    }

    private Documents aDocuments() {
      return new Documents.Stub(
        this.aSetOfDocuments(),
        this.aSetOfDocuments(),
        this.aSetOfDocuments(),
        this.aSetOfDocuments()
      );
    }

    @Test
    void intersection_is_equal_to_the_provided_one_regardless_the_other_documents() {
      var intersection = Set.of(
        this.aDocument()
      );

      assertThat(new Documents.Stub(this.aSetOfDocuments(), this.aSetOfDocuments(), intersection, this.aSetOfDocuments())
        .intersection(this.aDocuments())
      ).containsExactlyElementsOf(intersection);
    }

    @Test
    void difference_is_equal_to_the_provided_one_regardless_the_other_documents() {
      var difference = Set.of(
        this.aDocument(),
        this.aDocument(),
        this.aDocument(),
        this.aDocument()
      );

      assertThat(new Documents.Stub(this.aSetOfDocuments(), this.aSetOfDocuments(), this.aSetOfDocuments(), difference)
        .difference(this.aDocuments())
      ).containsExactlyElementsOf(difference);
    }
  }
}

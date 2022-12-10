package io.github.raffaeleflorio.fimp;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class BooleanQueryTest {

  @Nested
  class StubTest {

    @Test
    void provides_the_stubbed_evaluation_result_regardless_the_invertedIndex() {
      var evaluated = new Documents.Stub(
        this.anIterableOfDocument(),
        this.anIterableOfDocument(),
        this.anIterableOfDocument(),
        this.anIterableOfDocument()
      );

      assertThat(new BooleanQuery.Stub(evaluated, this.aDescription()).evaluated(this.anInvertedIndex()))
        .isEqualTo(evaluated);
    }

    private Iterable<Document> anIterableOfDocument() {
      return Set.of();
    }

    private InvertedIndex anInvertedIndex() {
      return new InvertedIndex.Stub(this.aDocuments());
    }

    private Documents aDocuments() {
      return new Documents.Stub(
        this.anIterableOfDocument(),
        this.anIterableOfDocument(),
        this.anIterableOfDocument(),
        this.anIterableOfDocument()
      );
    }

    private String aDescription() {
      return UUID.randomUUID().toString();
    }

    @Test
    void provides_the_stubbed_description_without_changes() {
      var description = "a description of the query";

      assertThat(new BooleanQuery.Stub(this.aDocuments(), description).description())
        .isEqualTo(description);
    }
  }
}

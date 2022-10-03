package io.github.raffaeleflorio.fimp.bool;

import io.github.raffaeleflorio.fimp.Document;
import io.github.raffaeleflorio.fimp.Documents;
import io.github.raffaeleflorio.fimp.InvertedIndex;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TermTest {

  @Test
  void shouldEvaluate_ToDocumentsRelated_TheTerm() {
    var index = new InvertedIndex.Fake(
      token -> new Documents.Fake(
        List.of(
          new Document.Fake(),
          new Document.Fake()
        )
      )
    );

    var term = new Term("related token");

    assertThat(term.evaluated(index))
      .hasSize(2);
  }

  @Test
  void shouldEvaluate_ToEmptyDocumentsWith_UnrelatedTerm() {
    var index = new InvertedIndex.Fake();

    var term = new Term("any unrelated token");

    assertThat(term.evaluated(index))
      .isEmpty();
  }
}

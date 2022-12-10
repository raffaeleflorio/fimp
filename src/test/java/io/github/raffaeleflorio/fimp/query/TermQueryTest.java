package io.github.raffaeleflorio.fimp.query;

import io.github.raffaeleflorio.fimp.Document;
import io.github.raffaeleflorio.fimp.Documents;
import io.github.raffaeleflorio.fimp.InvertedIndex;
import io.github.raffaeleflorio.fimp.Term;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class TermQueryTest {

  @Nested
  class DescriptionTest {

    @Test
    void encloses_in_double_quotes_the_textual_representation_of_the_term() {
      var term = new Term.Stub("term");

      assertThat(new TermQuery(term).description())
        .isEqualTo("\"term\"");
    }

    @Test
    void escapes_all_the_double_quotes_of_the_textual_representation_of_the_term() {
      var term = new Term.Stub("\"\"");

      assertThat(new TermQuery(term).description())
        .isEqualTo("\"\\\"\\\"\"");
    }

    @Test
    void is_only_double_quotes_when_empty() {
      assertThat(new TermQuery(new Term.Stub("")).description())
        .isEqualTo("\"\"");
    }
  }

  @Nested
  class EvaluatedTest {

    @Test
    void evaluates_itself_to_all_documents_in_the_inverted_index_containing_the_term() {
      var term = new Term.Stub("a term");
      var documents = new Documents.Stub(
        this.aSetOfDocuments(),
        this.aSetOfDocuments(),
        this.aSetOfDocuments(),
        this.aSetOfDocuments()
      );
      var invertedIndex = new InvertedIndex.Stub(documents);

      assertThat(new TermQuery(term).evaluated(invertedIndex))
        .isEqualTo(documents);
    }

    private Set<Document> aSetOfDocuments() {
      return Set.of();
    }
  }
}

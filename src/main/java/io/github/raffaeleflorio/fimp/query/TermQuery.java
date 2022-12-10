package io.github.raffaeleflorio.fimp.query;

import io.github.raffaeleflorio.fimp.BooleanQuery;
import io.github.raffaeleflorio.fimp.Documents;
import io.github.raffaeleflorio.fimp.InvertedIndex;
import io.github.raffaeleflorio.fimp.Term;

/**
 * A query that evaluates to all documents containing a term
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public final class TermQuery implements BooleanQuery {

  private final Term term;

  /**
   * @param term The term
   */
  public TermQuery(final Term term) {
    this.term = term;
  }

  @Override
  public Documents evaluated(final InvertedIndex invertedIndex) {
    return invertedIndex.documents(this.term);
  }

  @Override
  public String description() {
    return "\"".concat(this.term.text().replace("\"", "\\\"")).concat("\"");
  }
}

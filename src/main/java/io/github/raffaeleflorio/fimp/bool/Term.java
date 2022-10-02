package io.github.raffaeleflorio.fimp.bool;

import io.github.raffaeleflorio.fimp.BoolQuery;
import io.github.raffaeleflorio.fimp.Documents;
import io.github.raffaeleflorio.fimp.Index;

/**
 * A term query
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public final class Term implements BoolQuery {

  private final String term;

  /**
   * Builds a term
   *
   * @param term The term
   */
  public Term(final String term) {
    this.term = term;
  }

  @Override
  public Documents evaluated(final Index index) {
    return index.documents(this.term);
  }
}

package io.github.raffaeleflorio.fimp;

/**
 * A boolean query
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @see <a href="https://en.wikipedia.org/wiki/Boolean_model_of_information_retrieval">Wikipedia about boolean model</a>
 * @since 1.0.0
 */
public interface BoolQuery {

  /**
   * Evaluates itself to a set of documents given an index
   *
   * @param index The index
   * @return The documents
   */
  Documents evaluated(Index index);
}

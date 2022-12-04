package io.github.raffaeleflorio.fimp;

/**
 * A boolean query
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public interface BooleanQuery {

  /**
   * @param invertedIndex The inverted index
   * @return Its evaluation over the inverted index as a set of documents
   */
  Documents evaluated(InvertedIndex invertedIndex);

  /**
   * @return Its description
   */
  String description();

  /**
   * A stub useful for testing
   *
   * @author Raffaele Florio (raffaeleflorio@protonmail.com)
   * @since 1.0.0
   */
  final class Stub implements BooleanQuery {

    private final Documents evaluated;
    private final String description;

    /**
     * @param evaluated   The evaluation result
     * @param description The description
     */
    public Stub(final Documents evaluated, final String description) {
      this.evaluated = evaluated;
      this.description = description;
    }

    @Override
    public Documents evaluated(final InvertedIndex invertedIndex) {
      return this.evaluated;
    }

    @Override
    public String description() {
      return this.description;
    }
  }
}

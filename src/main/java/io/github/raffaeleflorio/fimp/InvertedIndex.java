package io.github.raffaeleflorio.fimp;


/**
 * Inverted index
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @see <a href="https://en.wikipedia.org/wiki/Inverted_index">Wikipedia about the Inverted Index</a>
 * @since 1.0.0
 */
public interface InvertedIndex {

  /**
   * Indexes a document
   *
   * @param document The document to index
   */
  void index(Document document);

  /**
   * @param term The term
   * @return All the documents containing the term
   */
  Documents documents(Term term);

  /**
   * A stub useful for testing
   *
   * @author Raffaele Florio (raffaeleflorio@protonmail.com)
   * @since 1.0.0
   */
  final class Stub implements InvertedIndex {

    private final Documents documents;

    /**
     * @param documents The documents
     */
    public Stub(final Documents documents) {
      this.documents = documents;
    }

    @Override
    public void index(final Document document) {

    }

    @Override
    public Documents documents(final Term term) {
      return this.documents;
    }
  }
}

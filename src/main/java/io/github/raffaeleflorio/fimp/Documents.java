package io.github.raffaeleflorio.fimp;

import java.util.Iterator;
import java.util.Set;

/**
 * Set of {@link Document}, where each document is identified by its id
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public interface Documents extends Iterable<Document> {

  /**
   * @param documents The other documents
   * @return The union between itself and the other documents
   */
  Documents union(Documents documents);

  /**
   * @param documents The other documents
   * @return The intersection between itself and the other documents
   */
  Documents intersection(Documents documents);

  /**
   * @param documents The other documents
   * @return The difference between itself and the other documents
   */
  Documents difference(Documents documents);

  /**
   * A stub useful for testing
   *
   * @author Raffaele Florio (raffaeleflorio@protonmail.com)
   * @since 1.0.0
   */
  final class Stub implements Documents {

    private final Set<Document> documents;
    private final Set<Document> union;
    private final Set<Document> intersection;
    private final Set<Document> difference;

    /**
     * @param documents    The set of documents
     * @param union        The union
     * @param intersection The intersection
     * @param difference   The difference
     */
    public Stub(
      final Set<Document> documents,
      final Set<Document> union,
      final Set<Document> intersection,
      final Set<Document> difference
    ) {
      this.documents = documents;
      this.union = union;
      this.intersection = intersection;
      this.difference = difference;
    }

    @Override
    public Documents union(final Documents documents) {
      return new Documents.Stub(this.union, this.union, this.intersection, this.difference);
    }

    @Override
    public Documents intersection(final Documents documents) {
      return new Documents.Stub(this.intersection, this.union, this.intersection, this.difference);
    }

    @Override
    public Documents difference(final Documents documents) {
      return new Documents.Stub(this.difference, this.union, this.intersection, this.difference);
    }

    @Override
    public Iterator<Document> iterator() {
      return this.documents.iterator();
    }
  }
}

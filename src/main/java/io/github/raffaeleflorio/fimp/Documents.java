package io.github.raffaeleflorio.fimp;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * A group of {@link Document}
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public interface Documents extends Iterable<Document> {

  /**
   * Streams its documents
   *
   * @return The stream
   */
  Stream<Document> stream();

  /**
   * Documents useful for testing
   *
   * @author Raffaele Florio (raffaeleflorio@protonmail.com)
   * @since 1.0.0
   */
  final class Fake implements Documents {

    private final Collection<Document> documents;

    /**
     * Builds an empty fake
     */
    public Fake() {
      this(List.of());
    }

    /**
     * Builds a fake
     *
     * @param documents The documents
     */
    public Fake(final Collection<Document> documents) {
      this.documents = documents;
    }

    @Override
    public Stream<Document> stream() {
      return this.documents.stream();
    }

    @Override
    public Iterator<Document> iterator() {
      return this.stream().iterator();
    }
  }
}

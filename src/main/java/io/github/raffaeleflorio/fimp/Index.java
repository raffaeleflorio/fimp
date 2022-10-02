package io.github.raffaeleflorio.fimp;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

/**
 * An index
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @see <a href="https://en.wikipedia.org/wiki/Search_engine_indexing#Index_data_structures">Wikipedia about search index</a>
 * @since 1.0.0
 */
public interface Index {

  /**
   * Indexes or reindex a document
   *
   * @param document The document
   */
  void index(Document document);

  /**
   * Deletes a document given its id
   *
   * @param id The id
   */
  void delete(UUID id);

  /**
   * Estimates its size
   *
   * @return The size
   */
  Long size();

  /**
   * Provides an indexed document given its id
   *
   * @param id The id
   * @return The document if indexed, otherwise empty
   */
  Optional<Document> document(UUID id);

  /**
   * Provides all indexed documents related to a token
   *
   * @param token The token
   * @return The documents
   */
  Documents documents(String token);

  /**
   * An index useful for testing
   *
   * @author Raffaele Florio (raffaeleflorio@protonmail.com)
   * @since 1.0.0
   */
  final class Fake implements Index {

    private final Function<String, Documents> documentsFn;
    private final Function<UUID, Optional<Document>> documentFn;
    private final Long size;

    /**
     * Builds an empty fake
     */
    public Fake() {
      this(token -> new Documents.Fake(), id -> Optional.empty(), 0L);
    }


    /**
     * Builds a fake with a size of zero and unable to find document by id
     *
     * @param documentsFn The function to maps token to documents
     */
    public Fake(final Function<String, Documents> documentsFn) {
      this(
        documentsFn,
        id -> Optional.empty(),
        0L
      );
    }

    /**
     * Builds a fake
     *
     * @param documentsFn The function to maps token to documents
     * @param documentFn  The function to map id to document
     * @param size        The size
     */
    public Fake(final Function<String, Documents> documentsFn, final Function<UUID, Optional<Document>> documentFn, final Long size) {
      this.documentsFn = documentsFn;
      this.documentFn = documentFn;
      this.size = size;
    }

    @Override
    public void index(final Document document) {

    }

    @Override
    public void delete(final UUID id) {

    }

    @Override
    public Long size() {
      return this.size;
    }

    @Override
    public Optional<Document> document(final UUID id) {
      return this.documentFn.apply(id);
    }

    @Override
    public Documents documents(final String token) {
      return this.documentsFn.apply(token);
    }
  }
}

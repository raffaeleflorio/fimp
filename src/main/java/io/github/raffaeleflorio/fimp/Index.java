package io.github.raffaeleflorio.fimp;

import java.util.Optional;
import java.util.UUID;

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
}

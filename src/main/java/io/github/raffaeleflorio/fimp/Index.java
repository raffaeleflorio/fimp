package io.github.raffaeleflorio.fimp;

import java.util.Optional;
import java.util.UUID;

/**
 * An index
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @see <a href="https://en.wikipedia.org/wiki/Search_engine_indexing">Wikipedia about search engine index</a>
 * @since 1.0.0
 */
public interface Index {

  /**
   * Indexes a document given a text
   *
   * @param text The text
   * @return The document
   */
  Document document(Text text);

  /**
   * Finds a document by its id
   *
   * @param id The id
   * @return The document if found otherwise empty
   */
  Optional<Document> document(UUID id);

  /**
   * Finds one or more documents related a text
   *
   * @param text The text
   * @return The documents
   */
  Documents documents(Text text);

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
}

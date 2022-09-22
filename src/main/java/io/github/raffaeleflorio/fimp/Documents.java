package io.github.raffaeleflorio.fimp;

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
}

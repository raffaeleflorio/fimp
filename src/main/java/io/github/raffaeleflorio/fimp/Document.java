package io.github.raffaeleflorio.fimp;

import java.util.UUID;

/**
 * A text identified by an unique id
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public interface Document {

  /**
   * Provides its text representation
   *
   * @return The representation
   */
  Text text();

  /**
   * Provides its id
   *
   * @return The id
   */
  UUID id();
}

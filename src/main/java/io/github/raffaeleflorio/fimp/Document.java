package io.github.raffaeleflorio.fimp;

import java.util.UUID;

/**
 * A text identified by an id
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public interface Document {

  /**
   * Builds its text representation
   *
   * @return The text
   */
  Text text();

  /**
   * Builds its id
   *
   * @return Its id
   */
  UUID id();
}

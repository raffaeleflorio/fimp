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
   * Updates its text representation by building a new Document
   *
   * @param text The text representation
   * @return The new document
   */
  Document with(Text text);

  /**
   * Provides its id
   *
   * @return The id
   */
  UUID id();

  /**
   * A fake useful for testing
   *
   * @author Raffaele Florio (raffaeleflorio@protonmail.com)
   * @since 1.0.0
   */
  final class Fake implements Document {

    private final UUID id;
    private final Text text;

    /**
     * Builds a random fake
     */
    public Fake() {
      this(UUID.randomUUID());
    }

    /**
     * Builds a document with a random text
     *
     * @param id The id
     */
    public Fake(final UUID id) {
      this(id, new Text.Fake());
    }

    /**
     * Builds a fake
     *
     * @param id   The id
     * @param text The text
     */
    public Fake(final UUID id, final Text text) {
      this.id = id;
      this.text = text;
    }

    @Override
    public Text text() {
      return this.text;
    }

    @Override
    public Document with(final Text text) {
      return new Fake(this.id, text);
    }

    @Override
    public UUID id() {
      return this.id;
    }
  }
}

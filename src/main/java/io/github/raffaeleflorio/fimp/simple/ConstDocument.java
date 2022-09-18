package io.github.raffaeleflorio.fimp.simple;

import io.github.raffaeleflorio.fimp.Document;
import io.github.raffaeleflorio.fimp.Text;

import java.util.UUID;

/**
 * A constant implementation of a document
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public final class ConstDocument implements Document {

  private final UUID id;
  private final Text text;

  /**
   * Builds a document
   *
   * @param id   The document id
   * @param text The document text
   */
  public ConstDocument(final UUID id, final Text text) {
    this.id = id;
    this.text = text;
  }

  @Override
  public Text text() {
    return this.text;
  }

  @Override
  public UUID id() {
    return this.id;
  }
}

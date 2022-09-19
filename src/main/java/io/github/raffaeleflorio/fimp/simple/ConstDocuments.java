package io.github.raffaeleflorio.fimp.simple;

import io.github.raffaeleflorio.fimp.Document;
import io.github.raffaeleflorio.fimp.Documents;

import java.util.Iterator;
import java.util.Set;

/**
 * An immutable implementation of documents
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public final class ConstDocuments implements Documents {

  private final Set<Document> documents;

  /**
   * Builds documents
   *
   * @param documents The documents
   */
  public ConstDocuments(final Set<Document> documents) {
    this.documents = documents;
  }

  @Override
  public Iterator<Document> iterator() {
    return this.documents.stream().iterator();
  }
}

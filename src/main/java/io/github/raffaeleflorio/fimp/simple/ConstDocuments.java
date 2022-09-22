package io.github.raffaeleflorio.fimp.simple;

import io.github.raffaeleflorio.fimp.Document;
import io.github.raffaeleflorio.fimp.Documents;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * An immutable implementation of documents
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public final class ConstDocuments implements Documents {

  private final Collection<Document> documents;

  /**
   * Builds documents
   *
   * @param documents The documents
   */
  public ConstDocuments(final Collection<Document> documents) {
    this.documents = documents;
  }

  @Override
  public Iterator<Document> iterator() {
    return this.stream().iterator();
  }

  @Override
  public Stream<Document> stream() {
    return this.documents.stream();
  }
}

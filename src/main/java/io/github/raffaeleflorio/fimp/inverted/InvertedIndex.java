package io.github.raffaeleflorio.fimp.inverted;

import io.github.raffaeleflorio.fimp.Document;
import io.github.raffaeleflorio.fimp.Index;
import io.github.raffaeleflorio.fimp.multivaluemap.ConcurrentMultiValueMap;
import io.github.raffaeleflorio.fimp.multivaluemap.JdkConcurrentMultiValueMap;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * An in-memory inverted index
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @see <a href="https://en.wikipedia.org/wiki/Inverted_index">Wikipedia about inverted index</a>
 * @since 1.0.0
 */
public final class InvertedIndex implements Index {

  private final ConcurrentMap<UUID, Document> documentsMap;
  private final ConcurrentMultiValueMap<String, UUID> tokensMap;

  /**
   * Builds an empty inverted index
   */
  public InvertedIndex() {
    this(
      new ConcurrentHashMap<>(),
      new JdkConcurrentMultiValueMap<>()
    );
  }

  InvertedIndex(
    final ConcurrentMap<UUID, Document> documentsMap,
    final ConcurrentMultiValueMap<String, UUID> tokensMap
  ) {
    this.documentsMap = documentsMap;
    this.tokensMap = tokensMap;
  }

  @Override
  public void index(final Document document) {
    this.delete(document.id());
    this.documentsMap.put(document.id(), document);
    document.text().tokens().forEach(token -> this.tokensMap.add(token, document.id()));
  }

  @Override
  public Optional<Document> document(final UUID id) {
    return Optional.ofNullable(this.documentsMap.get(id));
  }

  @Override
  public void delete(final UUID id) {
    this.documentsMap.remove(id);
    this.tokensMap.remove(id);
  }

  @Override
  public Long size() {
    return (long) this.documentsMap.size();
  }
}

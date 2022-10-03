package io.github.raffaeleflorio.fimp.simple;

import io.github.raffaeleflorio.fimp.Document;
import io.github.raffaeleflorio.fimp.Documents;
import io.github.raffaeleflorio.fimp.InvertedIndex;
import io.github.raffaeleflorio.fimp.multivaluemap.ConcurrentMultiValueMap;
import io.github.raffaeleflorio.fimp.multivaluemap.JdkConcurrentMultiValueMap;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collector;

/**
 * An in-memory inverted index
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public final class InMemoryInvertedIndex implements InvertedIndex {

  private final ConcurrentMap<UUID, Document> documentsMap;
  private final ConcurrentMultiValueMap<String, UUID> tokensMap;
  private final Collector<Optional<Document>, ConcurrentMap<UUID, Document>, Documents> documentsCollector;

  /**
   * Builds an empty inverted index
   */
  public InMemoryInvertedIndex() {
    this(
      new ConcurrentHashMap<>(),
      new JdkConcurrentMultiValueMap<>(),
      Collector.of(
        ConcurrentHashMap::new,
        (map, document) -> document.ifPresent(value -> map.put(value.id(), value)),
        (left, right) -> {
          left.putAll(right);
          return left;
        },
        map -> new ConstDocuments(map.values()),
        Collector.Characteristics.CONCURRENT,
        Collector.Characteristics.UNORDERED
      )
    );
  }

  InMemoryInvertedIndex(
    final ConcurrentMap<UUID, Document> documentsMap,
    final ConcurrentMultiValueMap<String, UUID> tokensMap,
    final Collector<Optional<Document>, ConcurrentMap<UUID, Document>, Documents> documentsCollector
  ) {
    this.documentsMap = documentsMap;
    this.tokensMap = tokensMap;
    this.documentsCollector = documentsCollector;
  }

  @Override
  public void index(final Document document) {
    this.delete(document.id());
    this.documentsMap.put(document.id(), document);
    document.text().tokens().forEach(token -> this.tokensMap.add(token, document.id()));
  }

  @Override
  public void delete(final UUID id) {
    this.documentsMap.remove(id);
    this.tokensMap.remove(id);
  }

  @Override
  public Optional<Document> document(final UUID id) {
    return Optional.ofNullable(this.documentsMap.get(id));
  }

  @Override
  public Documents documents(final String token) {
    return this.tokensMap
      .values(token)
      .stream()
      .map(this::document)
      .collect(this.documentsCollector);
  }

  @Override
  public Long size() {
    return (long) this.documentsMap.size();
  }
}

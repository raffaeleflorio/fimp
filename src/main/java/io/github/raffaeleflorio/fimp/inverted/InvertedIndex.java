package io.github.raffaeleflorio.fimp.inverted;

import io.github.raffaeleflorio.fimp.Document;
import io.github.raffaeleflorio.fimp.Documents;
import io.github.raffaeleflorio.fimp.Index;
import io.github.raffaeleflorio.fimp.Text;
import io.github.raffaeleflorio.fimp.multivaluemap.ConcurrentMultiValueMap;
import io.github.raffaeleflorio.fimp.multivaluemap.JdkConcurrentMultiValueMap;
import io.github.raffaeleflorio.fimp.simple.ConstDocument;
import io.github.raffaeleflorio.fimp.simple.ConstDocuments;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
  private final Supplier<UUID> idSupplier;
  private final BiFunction<UUID, Text, Document> documentFn;
  private final Function<Collection<Document>, Documents> documentsFn;

  /**
   * Builds an empty inverted index
   */
  public InvertedIndex() {
    this(
      new ConcurrentHashMap<>(),
      new JdkConcurrentMultiValueMap<>(),
      UUID::randomUUID,
      ConstDocument::new,
      ConstDocuments::new
    );
  }

  InvertedIndex(
    final ConcurrentMap<UUID, Document> documentsMap,
    final ConcurrentMultiValueMap<String, UUID> tokensMap,
    final Supplier<UUID> idSupplier,
    final BiFunction<UUID, Text, Document> documentFn,
    final Function<Collection<Document>, Documents> documentsFn
  ) {
    this.documentsMap = documentsMap;
    this.tokensMap = tokensMap;
    this.idSupplier = idSupplier;
    this.documentFn = documentFn;
    this.documentsFn = documentsFn;
  }

  @Override
  public Document index(final Text text) {
    return this.index(this.documentFn.apply(this.idSupplier.get(), text));
  }

  @Override
  public Document index(final Document document) {
    this.delete(document.id());
    this.documentsMap.put(document.id(), document);
    document.text().tokens().forEach(token -> this.tokensMap.add(token, document.id()));
    return document;
  }

  @Override
  public Optional<Document> document(final UUID id) {
    return Optional.ofNullable(this.documentsMap.get(id));
  }

  @Override
  public Documents documents(final Text text) {
    return this.documentsFn.apply(
      text.tokens().stream()
        .map(this.tokensMap::values)
        .flatMap(Set::stream)
        .map(this.documentsMap::get)
        .collect(Collectors.toUnmodifiableSet())
    );
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

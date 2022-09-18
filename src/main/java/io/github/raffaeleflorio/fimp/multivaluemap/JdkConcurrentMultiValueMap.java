package io.github.raffaeleflorio.fimp.multivaluemap;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A concurrent multi value map implemented using only JDK classes
 *
 * @param <K> The key type
 * @param <V> The values type
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public final class JdkConcurrentMultiValueMap<K, V> implements ConcurrentMultiValueMap<K, V> {

  private final ConcurrentMap<K, Set<V>> map;
  private final Supplier<Set<V>> emptySetSupplier;
  private final Function<Set<V>, Set<V>> immutableFn;

  /**
   * Builds an empty map
   *
   * @since 1.0.0
   */
  public JdkConcurrentMultiValueMap() {
    this(
      new ConcurrentHashMap<>(),
      HashSet::new,
      Collections::unmodifiableSet
    );
  }

  JdkConcurrentMultiValueMap(
    final ConcurrentMap<K, Set<V>> map,
    final Supplier<Set<V>> emptySetSupplier,
    final Function<Set<V>, Set<V>> immutableFn
  ) {
    this.map = map;
    this.emptySetSupplier = emptySetSupplier;
    this.immutableFn = immutableFn;
  }

  @Override
  public void add(final K key, final V value) {
    this.map.compute(key, (k, v) -> {
      var result = v == null ? this.emptySetSupplier.get() : v;
      result.add(value);
      return result;
    });
  }

  @Override
  public Set<V> get(final K key) {
    var result = this.map.containsKey(key) ? this.map.get(key) : this.emptySetSupplier.get();
    return this.immutableFn.apply(result);
  }

  @Override
  public void remove(final V value) {
    this.map.forEach((key, values) -> values.remove(value));
  }
}

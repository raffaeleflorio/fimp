package io.github.raffaeleflorio.fimp.multivaluemap;

import java.util.Set;

/**
 * A concurrent map that associates a key to a set of values
 *
 * @param <K> The key type
 * @param <V> The values type
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public interface ConcurrentMultiValueMap<K, V> {

  /**
   * Adds a value
   *
   * @param key   The key
   * @param value The value
   */
  void add(K key, V value);

  /**
   * Provides the values associated to a key or an empty set
   *
   * @param key The key
   * @return The values as an immutable set
   */
  Set<V> values(K key);

  /**
   * Removes a value from any associated key
   *
   * @param value The value to remove
   */
  void remove(V value);
}

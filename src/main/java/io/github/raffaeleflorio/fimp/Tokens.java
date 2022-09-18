package io.github.raffaeleflorio.fimp;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Group of tokens
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public interface Tokens extends Iterable<String> {

  /**
   * Streams the tokens
   *
   * @return The stream
   */
  Stream<String> stream();

  /**
   * Tokens useful for testing purpose
   *
   * @author Raffaele Florio (raffaeleflorio@protonmail.com)
   * @since 1.0.0
   */
  final class Fake implements Tokens {

    private final Collection<String> tokens;

    /**
     * Builds a fake using an array
     *
     * @param tokens The tokens
     */
    public Fake(final String... tokens) {
      this(List.of(tokens));
    }

    /**
     * Builds a fake using a list
     *
     * @param tokens The set
     */
    public Fake(final List<String> tokens) {
      this.tokens = tokens;
    }

    @Override
    public Stream<String> stream() {
      return this.tokens.stream();
    }

    @Override
    public Iterator<String> iterator() {
      return this.tokens.iterator();
    }
  }
}

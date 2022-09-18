package io.github.raffaeleflorio.fimp;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Set of token
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public interface Tokens extends Iterable<CharSequence> {

  /**
   * Builds its stream of tokens
   *
   * @return Its stream
   */
  Stream<CharSequence> stream();

  /**
   * Tokens useful for testing purpose
   *
   * @author Raffaele Florio (raffaeleflorio@protonmail.com)
   * @since 1.0.0
   */
  final class Fake implements Tokens {

    private final Set<CharSequence> tokens;

    /**
     * Builds a fake using an array
     *
     * @param tokens The tokens
     */
    public Fake(final String... tokens) {
      this(Set.of(tokens));
    }

    /**
     * Builds a fake using a set
     *
     * @param tokens The set
     */
    public Fake(final Set<CharSequence> tokens) {
      this.tokens = tokens;
    }

    @Override
    public Stream<CharSequence> stream() {
      return this.tokens.stream();
    }

    @Override
    public Iterator<CharSequence> iterator() {
      return this.tokens.iterator();
    }
  }
}

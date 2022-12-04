package io.github.raffaeleflorio.fimp;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Group of {@link Term}
 *
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public interface Terms extends Iterable<Term> {

  /**
   * A stub useful for testing
   *
   * @author Raffaele Florio (raffaeleflorio@protonmail.com)
   * @since 1.0.0
   */
  final class Stub implements Terms {

    private final Iterable<Term> terms;

    /**
     * @param terms Terms
     */
    public Stub(final Term... terms) {
      this(Arrays.asList(terms));
    }

    /**
     * @param terms Iterable of terms
     */
    public Stub(final Iterable<Term> terms) {
      this.terms = terms;
    }

    @Override
    public Iterator<Term> iterator() {
      return this.terms.iterator();
    }
  }
}

# FIMP

FIMP (**F**inding **I**s **M**y **P**urpose) is an in-memory search engine written in Java for fun.

It's written with object-oriented principles in mind, and it has no dependencies. This means, you can integrate it
easily in any Java project.

# Examples

Here some usage examples:

```java
import io.github.raffaeleflorio.fimp.inverted.InvertedIndex;
import io.github.raffaeleflorio.fimp.lowercase.LowercaseText;
import io.github.raffaeleflorio.fimp.unique.UniqueText;
import io.github.raffaeleflorio.fimp.whitespace.WhitespaceText;

public final class Examples {

  public static void main(final String[] args) {
    var texts = new InvertedIndex();
    texts.index(text("A TEXT TO INDEX, it could be anything!"));
    texts.index(text("Another stuff to index"));
    texts.index(text("This will not be found."));
    texts.index(
      texts.index(text("OLD")).with(text("updated that will match"))
    );

    for (var document : texts.documents(query("TExT to SeArCh, it shouldn't match 1:1"))) {
      System.out.println(document.text().asString());
    }
    // => A TEXT TO INDEX, it could be anything!
    // => Another stuff to index
    // => updated that will match
  }

  private static Text text(final String text) {
    return new LowercaseText(
      new UniqueText(
        new WhitespaceText(text)
      )
    );
  }

  private static Text query(final String text) {
    return text(text);
  }
}
```

# Demo

WIP

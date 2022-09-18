# FIMP

FIMP (**F**inding **I**s **M**y **P**urpose) is an in-memory search engine written in Java for fun.

It's written with object-oriented principles in mind, and it has no dependencies. This means, you can integrate it
easily in any Java project.

# Examples

Here some usage examples:

```java
import io.github.raffaeleflorio.fimp.inverted.InvertedIndex;
import io.github.raffaeleflorio.fimp.whitespace.WhitespaceText;

public final class Examples {

  public static void main(final String[] args) {
    var index = new InvertedIndex();
    index.document(new WhitespaceText("A text to index, it can be anything!"));
    index.document(new WhitespaceText("Another stuff to search"));
    index.document(new WhitespaceText("This will be not returned"));
    for (var document : index.documents(new WhitespaceText("text to search, it shouldn't match 1:1"))) {
      System.out.println(document.text().asCharSequence());
    }
    // => A text to index, it can be anything!
    // => Another stuff to search
  }
}
```

# REST interface

A REST interface is available [in this Quarkus project](TODO)

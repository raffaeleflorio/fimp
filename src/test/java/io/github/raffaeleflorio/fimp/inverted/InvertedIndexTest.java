package io.github.raffaeleflorio.fimp.inverted;

import io.github.raffaeleflorio.fimp.Document;
import io.github.raffaeleflorio.fimp.Text;
import io.github.raffaeleflorio.fimp.Tokens;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class InvertedIndexTest {

  @Test
  void shouldNotProvide_AnUnindexedDocument() {
    assertThat(
      new InvertedIndex().document(this.aDocumentId())
    ).isEmpty();
  }

  private UUID aDocumentId() {
    return UUID.randomUUID();
  }

  @Test
  void shouldProvide_AnIndexedDocument_ByItsId() {
    var index = new InvertedIndex();
    assertThat(
      index.document(
        index.index(new Text.Fake()).id()
      )
    ).isNotEmpty();
  }

  @Test
  void shouldProvide_AnIndexedDocument_ByItsText() {
    var index = new InvertedIndex();
    assertThat(
      index.documents(
        index.index(new Text.Fake("these", "are", "tokens")).text()
      )
    ).isNotEmpty();
  }

  @Test
  void shouldProvide_AnIndexedDocument_ByItsPartialText() {
    var index = new InvertedIndex();
    index.index(new Text.Fake("document", "tokens"));
    assertThat(
      index.documents(new Text.Fake("partial", "tokens", "match"))
    ).isNotEmpty();
  }

  @Test
  void shouldProvide_AnIndexedDocument_BySingleToken() {
    var index = new InvertedIndex();

    index.index(new Text.Fake("these", "are", "few", "tokens", "but", "only", "one", "will", "match"));

    assertThat(
      index.documents(new Text.Fake("one"))
    ).isNotEmpty();
  }

  @Test
  void shouldNotProvide_AnUnindexedDocument_ByOtherText() {
    var index = new InvertedIndex();

    index.index(new Text.Fake("the", "answer"));

    assertThat(
      index.documents(
        new Text.Fake("42")
      )
    ).isEmpty();
  }

  @Test
  void shouldNotProvide_AnUnindexedDocument_ByOtherId() {
    var index = new InvertedIndex();

    index.index(new Text.Fake());

    assertThat(
      index.document(this.aDocumentId())
    ).isEmpty();
  }

  @Test
  void shouldNotProvide_AnIndexedDocument_ByItsId_AfterDeletion() {
    var index = new InvertedIndex();

    var document = index.index(new Text.Fake());
    index.delete(document.id());

    assertThat(
      index.document(document.id())
    ).isEmpty();
  }

  @Test
  void shouldNotProvide_AnIndexedDocument_ByItsText_AfterDeletion() {
    var index = new InvertedIndex();

    var document = index.index(new Text.Fake());
    index.delete(document.id());

    assertThat(
      index.documents(document.text())
    ).isEmpty();
  }

  @Test
  void shouldProvide_OneDocument_BetweenMany() {
    var index = new InvertedIndex();

    index.index(new Text.Fake("good"));
    index.index(new Text.Fake("bad"));
    index.index(new Text.Fake("ugly"));

    assertThat(
      index.documents(new Text.Fake("good"))
    ).hasSize(1);
  }

  @Test
  void shouldEstimate_Size_According_IndexedDocuments() {
    var index = new InvertedIndex();

    index.index(new Text.Fake("first"));
    index.index(new Text.Fake("second"));
    index.index(new Text.Fake("third"));
    index.index(new Text.Fake("fourth"));

    assertThat(index.size()).isEqualTo(4L);
  }

  @Test
  void shouldProvide_ACorrectTextOfAnIndexedDocument_ByItsId() {
    var index = new InvertedIndex();
    var expected = "first second";

    assertThat(
      index.document(
        index.index(
          new Text.Fake(
            new Tokens.Fake("first", "second"),
            expected
          )
        ).id()
      ).map(Document::text).map(Text::asString)
    ).contains(expected);
  }

  @Test
  void shouldNotProvide_ByOldText_AReindexDocument_WithNewText() {
    var index = new InvertedIndex();

    index.index(
      new Document.Fake(
        index.index(new Text.Fake("old")).id(),
        new Text.Fake("new"))
    );

    assertThat(
      index.documents(new Text.Fake("old"))
    ).isEmpty();
  }

  @Test
  void shouldProvide_ByNewText_AReindexedDocument_WithNewText() {
    var index = new InvertedIndex();

    index.index(
      new Document.Fake(
        index.index(new Text.Fake("old")).id(),
        new Text.Fake("new"))
    );

    assertThat(
      index.documents(new Text.Fake("new"))
    ).isNotEmpty();
  }

  @Test
  void shouldProvide_AReindexedDocument_ByItsId() {
    var index = new InvertedIndex();

    var id = index.index(new Text.Fake("old")).id();
    index.index(new Document.Fake(id, new Text.Fake("new")));

    assertThat(index.document(id)).isNotEmpty();
  }
}

package io.github.raffaeleflorio.fimp.inverted;

import io.github.raffaeleflorio.fimp.Document;
import io.github.raffaeleflorio.fimp.Text;
import io.github.raffaeleflorio.fimp.Tokens;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class InvertedIndexTest {

  @Test
  void shouldNotFind_AnUnindexedDocument() {
    assertThat(
      new InvertedIndex().document(this.aDocumentId())
    ).isEmpty();
  }

  private UUID aDocumentId() {
    return UUID.randomUUID();
  }

  @Test
  void shouldFind_AnIndexedDocument_ByItsId() {
    var index = new InvertedIndex();
    assertThat(
      index.document(
        index.document(new Text.Fake()).id()
      )
    ).isNotEmpty();
  }

  @Test
  void shouldFind_AnIndexedDocument_ByItsText() {
    var index = new InvertedIndex();
    assertThat(
      index.documents(
        index.document(new Text.Fake("these", "are", "tokens")).text()
      )
    ).isNotEmpty();
  }

  @Test
  void shouldFind_AnIndexedDocument_ByItsPartialText() {
    var index = new InvertedIndex();
    index.document(new Text.Fake("document", "tokens"));
    assertThat(
      index.documents(new Text.Fake("partial", "tokens", "match"))
    ).isNotEmpty();
  }

  @Test
  void shouldFind_AnIndexedDocument_BySingleToken() {
    var index = new InvertedIndex();
    index.document(new Text.Fake("these", "are", "few", "tokens", "but", "only", "one", "will", "match"));
    assertThat(
      index.documents(new Text.Fake("one"))
    ).isNotEmpty();
  }

  @Test
  void shouldNotFind_AnUnindexedDocument_ByOtherText() {
    var index = new InvertedIndex();
    index.document(new Text.Fake("the", "answer"));
    assertThat(
      index.documents(
        new Text.Fake("42")
      )
    ).isEmpty();
  }

  @Test
  void shouldNotFind_AnUnindexedDocument_ByOtherId() {
    var index = new InvertedIndex();
    index.document(new Text.Fake());
    assertThat(
      index.document(this.aDocumentId())
    ).isEmpty();
  }

  @Test
  void shouldNotFind_AnIndexedDocument_ByItsId_AfterDeletion() {
    var index = new InvertedIndex();
    var document = index.document(new Text.Fake());
    index.delete(document.id());
    assertThat(
      index.document(document.id())
    ).isEmpty();
  }

  @Test
  void shouldNotFind_AnIndexedDocument_ByItsText_AfterDeletion() {
    var index = new InvertedIndex();
    var document = index.document(new Text.Fake());
    index.delete(document.id());
    assertThat(
      index.documents(document.text())
    ).isEmpty();
  }

  @Test
  void shouldFind_OneDocument_BetweenMany() {
    var index = new InvertedIndex();
    index.document(new Text.Fake("good"));
    index.document(new Text.Fake("bad"));
    index.document(new Text.Fake("ugly"));
    assertThat(
      index.documents(new Text.Fake("good"))
    ).hasSize(1);
  }

  @Test
  void shouldEstimate_Size_According_IndexedDocuments() {
    var index = new InvertedIndex();
    index.document(new Text.Fake("first"));
    index.document(new Text.Fake("second"));
    index.document(new Text.Fake("third"));
    index.document(new Text.Fake("fourth"));
    assertThat(index.size()).isEqualTo(4L);
  }

  @Test
  void shouldFind_ACorrectTextOfAnIndexedDocument_ByItsId() {
    var index = new InvertedIndex();
    var text = "first second";
    assertThat(
      index.document(
        index.document(
          new Text.Fake(
            new Tokens.Fake("first", "second"),
            text
          )
        ).id()
      ).map(Document::text).map(Text::asCharSequence)
    ).contains(text);
  }
}

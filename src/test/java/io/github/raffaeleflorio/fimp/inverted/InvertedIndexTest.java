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
    assertThat(new InvertedIndex().document(this.aDocumentId()))
      .isEmpty();
  }

  private UUID aDocumentId() {
    return UUID.randomUUID();
  }

  @Test
  void shouldProvide_AnIndexedDocument() {
    var index = new InvertedIndex();
    var document = new Document.Fake();

    index.index(document);

    assertThat(index.document(document.id()))
      .isNotEmpty();
  }

  @Test
  void shouldNotProvide_AnIndexedDocument_ByAnotherId() {
    var index = new InvertedIndex();

    index.index(new Document.Fake(this.aDocumentId()));

    assertThat(index.document(this.aDocumentId()))
      .isEmpty();
  }

  @Test
  void shouldNotProvide_AnIndexedDocument_AfterDeletion() {
    var index = new InvertedIndex();
    var document = new Document.Fake();

    index.index(document);
    index.delete(document.id());

    assertThat(index.document(document.id()))
      .isEmpty();
  }

  @Test
  void shouldEstimate_Size_According_IndexedDocuments() {
    var index = new InvertedIndex();

    index.index(new Document.Fake());
    index.index(new Document.Fake());
    index.index(new Document.Fake());
    index.index(new Document.Fake());

    assertThat(index.size())
      .isEqualTo(4L);
  }

  @Test
  void shouldProvide_AReindexedDocument() {
    var index = new InvertedIndex();
    var document = new Document.Fake(this.aDocumentId(), new Text.Fake("old"));

    index.index(document);
    index.index(document.with(new Text.Fake("new")));

    assertThat(index.document(document.id()))
      .isNotEmpty();
  }

  @Test
  void shouldProvide_CorrectTextOfAReindexedDocument() {
    var index = new InvertedIndex();
    var document = new Document.Fake();
    var expected = "new";

    index.index(document);
    index.index(document.with(new Text.Fake(new Tokens.Fake(), expected)));

    assertThat(index.document(document.id()).map(Document::text).map(Text::asString))
      .contains(expected);
  }

  @Test
  void shouldProvide_DocumentsOnlyRelatedToAToken() {
    var index = new InvertedIndex();

    index.index(new Document.Fake(this.aDocumentId(), new Text.Fake("token")));
    index.index(new Document.Fake(this.aDocumentId(), new Text.Fake("token")));
    index.index(new Document.Fake(this.aDocumentId(), new Text.Fake("another token")));

    assertThat(index.documents("token"))
      .hasSize(2);
  }
}

package io.github.raffaeleflorio.fimp.simple;

import io.github.raffaeleflorio.fimp.Document;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ConstDocumentsTest {

  @Test
  void shouldNotDistinct_Documents() {
    var document = new Document.Fake();

    assertThat(
      new ConstDocuments(
        List.of(
          document,
          document,
          document
        )
      )
    ).hasSize(3);
  }
}

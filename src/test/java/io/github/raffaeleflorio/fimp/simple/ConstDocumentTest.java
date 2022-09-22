package io.github.raffaeleflorio.fimp.simple;

import io.github.raffaeleflorio.fimp.Text;
import io.github.raffaeleflorio.fimp.Tokens;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ConstDocumentTest {

  @Test
  void shouldUpdate_ItsText() {
    assertThat(
      new ConstDocument(this.anId(), new Text.Fake(new Tokens.Fake(), "old text"))
        .with(new Text.Fake(new Tokens.Fake(), "new text"))
        .text()
        .asString()
    ).isEqualTo("new text");
  }

  private UUID anId() {
    return UUID.randomUUID();
  }
}

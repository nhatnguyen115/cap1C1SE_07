package com.arkdev.z9tkvtu.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Grammar}
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarRequest implements Serializable {
    @NotNull(message = "Grammar name must be not null")
    @Size(max = 255, message = "Grammar name is too long")
    String grammarName;

    String grammarText;
}
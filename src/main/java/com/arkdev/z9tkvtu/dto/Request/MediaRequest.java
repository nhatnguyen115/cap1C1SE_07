package com.arkdev.z9tkvtu.dto.Request;

import com.arkdev.z9tkvtu.util.MediaType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Media}
 */
@AllArgsConstructor
@Getter
public class MediaRequest implements Serializable {
    @NotNull(message = "Media type must be not null")
    MediaType mediaType;
    
    @NotNull(message = "Url must be not null")
    String url;
    
    Integer duration;
    
    String description;
    
    String transcript;
}
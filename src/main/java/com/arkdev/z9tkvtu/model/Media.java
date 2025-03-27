package com.arkdev.z9tkvtu.model;

import com.arkdev.z9tkvtu.util.MediaType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "media")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "media_id",
                nullable = false))
})
public class Media extends AbstractEntity<Integer> {
    @Column(name = "media_type")
    private MediaType mediaType;

    @Column(name = "url", nullable = false, length = 512)
    private String url;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "transcript", length = Integer.MAX_VALUE)
    private String transcript;

}
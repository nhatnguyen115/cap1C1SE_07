package com.arkdev.z9tkvtu.model;

import com.arkdev.z9tkvtu.util.MediaType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name = "media")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "media_id",
                nullable = false))
})
public class Media extends AbstractEntity<Integer> {
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "media_type")
    private MediaType mediaType;

    @Column(name = "url")
    private String url;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "description")
    private String description;

    @Column(name = "transcript")
    private String transcript;
}
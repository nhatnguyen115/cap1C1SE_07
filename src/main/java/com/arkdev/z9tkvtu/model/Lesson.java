package com.arkdev.z9tkvtu.model;

import com.arkdev.z9tkvtu.util.Content;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lesson")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "lesson_id",
                nullable = false))
})
public class Lesson extends AbstractEntity<Integer> {

    @Column(name = "lesson_name", nullable = false, length = 255)
    private String lessonName;

    @Column(name = "content_type")
    private Content contentType;

    @Column(name = "article_text", length = Integer.MAX_VALUE)
    private String articleText;

    @Column(name = "duration")
    private Integer duration;

    @NotNull
    @Column(name = "order_number", nullable = false)
    private Integer orderNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "media_id")
    private Media media;
}
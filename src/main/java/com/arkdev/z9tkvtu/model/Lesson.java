package com.arkdev.z9tkvtu.model;

import com.arkdev.z9tkvtu.util.Content;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name = "lesson")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "lesson_id",
                nullable = false))
})
public class Lesson extends AbstractEntity<Integer> {

    @Column(name = "lesson_name")
    private String lessonName;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "content_type")
    private Content contentType;

    @Column(name = "article_text")
    private String articleText;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "order_number")
    private Integer orderNumber;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @OneToOne
    @JoinColumn(name = "media_id")
    private Media media;
}
package ru.lazarev.springcourse.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("comments")
public class Comment {
    @Id
    private Long id;

    @Column("text")
    private String text;

    @ToString.Exclude
    private Book book;
}

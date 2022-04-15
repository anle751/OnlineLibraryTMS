package com.tms.web.entities.library;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "book_chapter")
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String chapterName;

    @Type(type = "text")
    private String chapterText;

    @ManyToOne
    private Book book;

    @CreationTimestamp
    @Column(name = "`create`")
    private Date creation;
    @UpdateTimestamp
    @Column(name = "`update`")
    private Date lastUpdate;
    @Version
    private Long version;



}

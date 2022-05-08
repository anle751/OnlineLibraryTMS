package com.tms.web.entities.library;

import com.tms.web.entities.security.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "userLibraries")
public class UserLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.MERGE)
    private User user;

    @ManyToOne
    private Book book;

    private Long chapterId;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "book_chapter_mapping",
//        joinColumns = {@JoinColumn(table ="books", name = "book_id",referencedColumnName = "id")},
//        inverseJoinColumns = {@JoinColumn(table ="book_chapter", name = "chapter_id",referencedColumnName = "id")})
//    @MapKeyJoinColumn(name = "book")
//    private Map<Book,Chapter> readProgress;



}

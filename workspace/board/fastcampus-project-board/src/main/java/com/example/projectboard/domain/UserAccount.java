package com.example.projectboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
//@Table(indexes = {
//        @Index(columnList = "title"),
//        @Index(columnList = "hashtag"),
//        @Index(columnList = "createdAt"),
//        @Index(columnList = "createdBy")
//})
@Table
@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Setter
    private String name;
    @Setter
    private String nickname;

}

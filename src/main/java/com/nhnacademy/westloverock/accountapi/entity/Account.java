package com.nhnacademy.westloverock.accountapi.entity;

import com.nhnacademy.westloverock.accountapi.domain.State;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "Accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    // ToDo 값이 정확히 들어오는지 확인 요망
    @OneToMany(mappedBy = "account")
    List<LoginLogs> loginLogsList = new ArrayList<>();

    @Builder
    public Account(String userId, String password, String name, String nickname, State state, String email, String phoneNumber) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.state = state;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
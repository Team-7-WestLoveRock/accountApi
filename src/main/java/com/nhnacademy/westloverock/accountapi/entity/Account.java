package com.nhnacademy.westloverock.accountapi.entity;

import com.nhnacademy.westloverock.accountapi.domain.State;
import com.nhnacademy.westloverock.accountapi.request.AccountStateRequest;
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
    @Column
    private Long idx;

    @Column(name = "user_id")
    private String userId;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String nickname;

    @Enumerated(EnumType.STRING)
    private State state;

    @Column
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    // ToDo 값이 정확히 들어오는지 확인 요망
    @OneToMany(mappedBy = "account")
    List<LoginLog> loginLogList = new ArrayList<>();

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
    public void modifyStatus(AccountStateRequest accountStateRequest){
        this.state = State.getState(accountStateRequest.getState());
    }
}
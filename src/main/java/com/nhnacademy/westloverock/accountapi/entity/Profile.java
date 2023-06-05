package com.nhnacademy.westloverock.accountapi.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "Profiles")
public class Profile {

    @Id
    @Column(name = "account_idx")
    private Long accountIdx;
    @Column(name = "image_path")
    private String imagePath;

    @OneToOne
    @MapsId("accountIdx")
    @JoinColumn(name = "account_idx")
    private Account account;

    @Builder
    public Profile(String imagePath, Account account) {
        this.account = account;
        this.accountIdx = account.getIdx();
        this.imagePath = imagePath;
    }
}
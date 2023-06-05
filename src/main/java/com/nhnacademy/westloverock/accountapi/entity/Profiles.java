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
public class Profiles {

    @Id
    @Column(name = "account_idx")
    private Long accountIdx;
    @Column
    private String imagePath;

    @OneToOne
    @MapsId("accountIdx")
    @JoinColumn(name = "account_idx")
    private Accounts accounts;

    @Builder
    public Profiles(String imagePath, Accounts accounts) {
        this.accounts = accounts;
        this.accountIdx = accounts.getIdx();
        this.imagePath = imagePath;
    }
}
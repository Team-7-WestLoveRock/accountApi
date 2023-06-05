package com.nhnacademy.westloverock.accountapi.entity;

import lombok.*;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "Login_Logs")
public class LoginLogs {
    @Id
    @Column(name = "account_idx")
    private Long accountIdx;

    @Column
    private LocalDateTime loginDate;

    @Column
    private String ipAddress;

    @ManyToOne
    @MapsId("accountIdx")
    @JoinColumn(name = "account_idx")
    private Accounts accounts;

    @Builder
    public LoginLogs(LocalDateTime loginDate, String ipAddress, Accounts accounts) {
        this.loginDate = loginDate;
        this.ipAddress = ipAddress;
        this.accounts = accounts;
        this.accountIdx = accounts.getIdx();
    }

}

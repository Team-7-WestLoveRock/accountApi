package com.nhnacademy.westloverock.accountapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Entity
@Generated
@Table(name = "Login_Logs")
public class LoginLog {
    @Id
    @Column(name = "account_idx")
    private Long accountIdx;

    @Column(name = "login_date")
    private LocalDateTime loginDate;

    @Column(name = "ip_address")
    private String ipAddress;

    @ManyToOne
    @MapsId("accountIdx")
    @JoinColumn(name = "account_idx")
    private Account account;

    @Builder
    public LoginLog(LocalDateTime loginDate, String ipAddress, Account account) {
        this.loginDate = loginDate;
        this.ipAddress = ipAddress;
        this.account = account;
        this.accountIdx = account.getIdx();
    }

}

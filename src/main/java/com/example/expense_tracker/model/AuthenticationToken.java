package com.example.expense_tracker.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tokens")
public class AuthenticationToken {

    public AuthenticationToken(Date createdDate, String token, LocalDateTime expiresAt, LocalDateTime confirmedAt, User user) {
        this.createdDate = createdDate;
        this.token = token;
        this.expiresAt = expiresAt;
        this.confirmedAt = confirmedAt;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "created_date")
    private Date createdDate;


    private String token;



    private LocalDateTime expiresAt;


    private LocalDateTime confirmedAt;


    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public AuthenticationToken(User user) {
        this.user = user;
        this.createdDate = new Date();
        this.token = UUID.randomUUID().toString();
    }

    public AuthenticationToken(String token, LocalDateTime now, LocalDateTime plusMinutes, User user) {
    }

//    public AuthenticationToken(Integer id, String Token, Date createdDate, User user) {
//        this.id = id;
//        this.token = Token;
//        this.createdDate = createdDate;
//        this.user = user;
//    }





}

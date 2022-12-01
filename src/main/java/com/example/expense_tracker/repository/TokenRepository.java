package com.example.expense_tracker.repository;

import com.example.expense_tracker.model.User;
import com.example.expense_tracker.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {


    static AuthenticationToken findByToken(String token) {
        return null;
    }

    AuthenticationToken findByUser(User user);

    static int updateConfirmedAt(String token,
                                 LocalDateTime confirmedAt) {
        return 0;
    }
}


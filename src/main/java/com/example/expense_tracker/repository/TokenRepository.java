package com.example.expense_tracker.repository;
import com.example.expense_tracker.model.AuthenticationToken;
import com.example.expense_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken,Integer> {

    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);


}
package com.example.expense_tracker.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Data
@Entity
@Table(name="tbl_expenses")
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expenseId;

    @Column(name = "expense_name")
    @NotBlank(message = "name must not be null")
    @Size(min = 3, message = "name must be 3 characters")
    private String expenseName;

    @Column(name = "description")
    @NotBlank(message = "desc must not be null")
    private String comments;

    @Column(name = "expense_amount")
    @NotNull(message = "amount must not be null")
    private Double amount;

    @Column(name = "category")
    @NotBlank(message = "category must not be null")
    private Integer categoryId;


    @Column(name = "created_on", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdDate;


    @Column(name = "user_id")
    @NotNull
    private Integer userId;


    public Expense(Integer expenseId, String expenseName, double amount, Date createdDate, String comments) {
        super();
        this.expenseId = expenseId;
        this.expenseName = expenseName;
        this.amount = amount;
        this.createdDate = createdDate;
        this.comments = comments;

    }

//    @ManyToOne(fetch = FetchType.LAZY , optional = false)
//    @JoinColumn(name = "user_id",nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private User user;


}
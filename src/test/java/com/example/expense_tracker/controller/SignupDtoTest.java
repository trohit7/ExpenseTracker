package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.user.SignupDto;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;





class SignupDtoTest {


    SignupDto sr = new SignupDto();

    @Test
    void getFirstNameTest(){
        sr.setFirstName("Rohit");
        assertTrue(sr.getFirstName().equals("Rohit"));

    }

    void getLastNameTest(){
        sr.setLastName("thottempudi");
        assertTrue(sr.getLastName().equals("thottempudi"));

    }

    void getemailTest(){
        sr.setEmail("Rohitthottempudi@gmail.com");
        assertTrue(sr.getEmail().equals("Rohit"));

    }

    void getpasswordTest(){
        sr.setPassword("abc@11");
        assertTrue(sr.getFirstName().equals("abc@11"));

    }



    }



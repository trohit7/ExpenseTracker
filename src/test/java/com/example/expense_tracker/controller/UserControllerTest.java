package com.example.expense_tracker.controller;
import com.example.expense_tracker.dto.email.EmailRequest;
import com.example.expense_tracker.dto.email.OtpRequest;
import com.example.expense_tracker.dto.user.SignInDto;
import com.example.expense_tracker.dto.user.SignupDto;
import com.example.expense_tracker.dto.user.UpdatePassword;
import com.example.expense_tracker.model.User;
import com.example.expense_tracker.repository.UserRepository;
import com.example.expense_tracker.service.AuthenticationService;
import com.example.expense_tracker.service.EmailService;
import com.example.expense_tracker.service.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters=false)
@WebMvcTest(value = UserController.class,  excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class UserControllerTest {

    @MockBean
    UserController userController;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    UserRepository userRepository;

    @MockBean
    AuthenticationService authenticationService;

    @MockBean
    EmailService emailService;

    @MockBean
    User user;

    @MockBean
    UserService userService;



    @Autowired
    private MockMvc mockMvc;


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    SignupDto signupDto  = new SignupDto("Rohit","thottempudi","rohitthottempudi@gmail.com","abc@11");


    @Test
    public void signup() throws Exception{

        var response = new ObjectMapper().writeValueAsString(signupDto);
           val result =      mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/signUp")
                        .content(response)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    SignInDto signInDto =  new SignInDto("rohitthottempudi@gmail.com","abc@11");
    @Test
    public void signIn() throws Exception{

        var response = new ObjectMapper().writeValueAsString(signInDto);
        val result =      mockMvc.perform(MockMvcRequestBuilders
                .post("/user/signIn")
                .content(response)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    EmailRequest er = new EmailRequest("rohitthottempudi@gmail.com");
    @Test
    public void forgotUserPassword() throws Exception{

        var response = new ObjectMapper().writeValueAsString(er);
        val result =      mockMvc.perform(MockMvcRequestBuilders
                .post("/user/api/auth/forgotpassword")
                .content(response)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    OtpRequest otpRequest = new OtpRequest("rohitthottempudi@gmail.com","12345");
    @Test
    public void verifyUserOtp() throws Exception{

        var response = new ObjectMapper().writeValueAsString(otpRequest);
        val result =      mockMvc.perform(MockMvcRequestBuilders
                .post("/user/api/auth/verifyOtp")
                .content(response)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }


    UpdatePassword updatePassword = new UpdatePassword("rohitthottempudi@gmail.com","abc@22");
    @Test
    public void updatePassword() throws Exception{
        var response = new ObjectMapper().writeValueAsString(updatePassword);
        val result =      mockMvc.perform(MockMvcRequestBuilders
                .post("/user/api/auth/updatePassword")
                .content(response)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }
}

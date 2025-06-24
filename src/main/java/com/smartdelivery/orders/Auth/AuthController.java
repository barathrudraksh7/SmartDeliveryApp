package com.smartdelivery.orders.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartdelivery.orders.Util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping("/login")
    public String login(@RequestBody AuthRequest request){

        Authentication auth = authManager
                                .authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
       
        // //extract the authenticated user
        // User user = (User) auth.getPrincipal();

        //extracting authenticated user
        UserDetails userDet = (UserDetails) auth.getPrincipal();

        //Generate JWT token
        return jwtUtil.generateToken(userDet.getUsername());
    }
}

package com.example.qlnvproject.controller;

import com.example.qlnvproject.dto.ResponseTokenDTO;
import com.example.qlnvproject.dto.SinginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController{
    @PostMapping("/singin")
    public ResponseEntity<?> singin(@RequestBody SinginDto singinDto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(singinDto.getUsername(), singinDto.getPass()));
        }catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails = accountService.loadUserByUsername(singinDto.getUsername());

        final String token = jwtUtil.GenerateToken(userDetails);

        return ResponseEntity.ok(new ResponseTokenDTO(token));
    }
}

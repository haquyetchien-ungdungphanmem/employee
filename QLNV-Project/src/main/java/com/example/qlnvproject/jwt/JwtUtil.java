package com.example.qlnvproject.jwt;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JwtUtil {

    private static final String giamDoc = "ROLE_giamdoc";
    private static final String truongPhong = "ROLE_truongphong";
    private static final String nhanVienNhanSu = "ROLE_nhanviennhansu";
    private static final String nhanVien = "ROLE_nhanvien";


    private int refreshExpirationDateInMs;

    private String secret;

    private int jwtExpirationInMs;

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        this.secret = secret;
    }
    @Value("${jwt.jwtExpirationInMs}")
    public void setJwtExpirationInMs(int jwtExpirationInMs) {
        this.jwtExpirationInMs = jwtExpirationInMs;
    }

    public String doGenerateToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String GenerateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
        if (roles.contains(new SimpleGrantedAuthority(giamDoc))){
            claims.put("isGiamDoc", true);
        }
        if (roles.contains(new SimpleGrantedAuthority(nhanVienNhanSu))){
            claims.put("isNhanVienNhanSu", true);
        }
        if (roles.contains(new SimpleGrantedAuthority(truongPhong))){
            claims.put("isTruongPhong", true);
        }
        if (roles.contains(new SimpleGrantedAuthority(nhanVien))){
            claims.put("isNhanVien", true);
        }



        return doGenerateToken(claims, userDetails.getUsername());

    }

    public boolean validateToken(String token){
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException exception){
            throw new BadCredentialsException("INVALID_CREDENTIALS",exception);
        }catch (ExpiredJwtException e){
            throw e;
        }
    }

    public String getUsernameByToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public List<SimpleGrantedAuthority> getRoleByToken(String token){
        List<SimpleGrantedAuthority> roles = null;
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        Boolean isGiamDoc = claims.get("isGiamDoc", Boolean.class);
        Boolean isTruongPhong = claims.get("isTruongPhong", Boolean.class);
        Boolean isNhanVienNhanSu = claims.get("isNhanVienNhanSu", Boolean.class);
        Boolean isNhanVien = claims.get("isNhanVien", Boolean.class);

        if (isGiamDoc != null && isGiamDoc == true){
            roles = Arrays.asList(new SimpleGrantedAuthority(giamDoc));
        }
        if (isTruongPhong != null && isTruongPhong == true){
            roles = Arrays.asList(new SimpleGrantedAuthority(truongPhong));
        }
        if (isNhanVienNhanSu != null && isNhanVienNhanSu == true){
            roles = Arrays.asList(new SimpleGrantedAuthority(nhanVienNhanSu));
        }
        if (isNhanVien != null && isNhanVien == true){
            roles = Arrays.asList(new SimpleGrantedAuthority(nhanVien));
        }

        return roles;
    }
}

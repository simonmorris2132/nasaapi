package com.careerdevs.nasaapi.security.jwt;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.careerdevs.nasaapi.security.services.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
    
    private static Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${nasa.prop.jwtsecret}")
    private String jwtSecret;

//    @Value("${muzick.props.jwtExpirationMs}")
//    private int jwtExpirationMs;

    public boolean validationJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (Error e) {
            logger.error("Error {}", e.getMessage());
        }
        return false;
    }

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.ES512, jwtSecret).compact();//.setExpiration(new Date().getTime() + jwtExpirationMs)
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

}

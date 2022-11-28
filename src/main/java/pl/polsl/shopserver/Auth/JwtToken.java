package pl.polsl.shopserver.Auth;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pl.polsl.shopserver.Exception.AuthorizationFailed;
import pl.polsl.shopserver.Exception.TokenExpired;
import pl.polsl.shopserver.UserControl.Reference;
import pl.polsl.shopserver.dbentity.User;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;

public class JwtToken {
    static public String creatToken(User user){
        long curretnTimeMili=System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("roles","user")
                .setIssuedAt(new Date(curretnTimeMili))
                .setExpiration(new Date(curretnTimeMili+1800000))
                .signWith(SignatureAlgorithm.HS256, Reference.JWTSecret)
                .compact();
    }
    static public String validateToke(String token){

        try {
            String email =Jwts.parser().setSigningKey(Reference.JWTSecret).parseClaimsJws(token).getBody().getSubject();
            return email;
        }catch (ExpiredJwtException e){
            throw new TokenExpired();
        }
        catch (Exception e){
            throw new AuthorizationFailed();
        }

    }
}

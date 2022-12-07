package pl.polsl.shopserver.Auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pl.polsl.shopserver.User.Reference;
import pl.polsl.shopserver.model.entities.dbentity.User;

import java.util.Date;

public class RefreshToken {
    static public String creatToken(User user){
        long curretnTimeMili=System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date(curretnTimeMili))
                .setExpiration(new Date(curretnTimeMili+1209600000))
                .signWith(SignatureAlgorithm.HS256, Reference.JwtRefresh)
                .compact();
    }
}

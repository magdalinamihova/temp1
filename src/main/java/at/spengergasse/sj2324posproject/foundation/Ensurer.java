package at.spengergasse.sj2324posproject.foundation;

import static org.springframework.util.Assert.isNull;

public class Ensurer {

    public static <T> T isNotNull(T argument){
        return isNotNull(argument, "argument");
    }

    public static <T> T isNotNull(T argument, String argumentName){
       if(argument==null){
           throw new IllegalArgumentException("%s must not be null!".formatted(argumentName));
       }
       return argument;
    }

}

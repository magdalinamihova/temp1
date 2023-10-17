package at.spengergasse.sj2324posproject.persistance.exception;

public class DataQualityException extends RuntimeException{

    private DataQualityException(String message){
        super(message);
    }
    public static DataQualityException forInvalidValue(String v, Class<? extends Enum> enumClass){
        return new DataQualityException("%s is not a valid value for %s".formatted(v,enumClass.getSimpleName()));
    }

}

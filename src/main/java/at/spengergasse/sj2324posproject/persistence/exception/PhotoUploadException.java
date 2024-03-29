package at.spengergasse.sj2324posproject.persistence.exception;

public class PhotoUploadException extends RuntimeException {
    private final String photoName;

    public PhotoUploadException(String photoName, String message) {
        super(message);
        this.photoName = photoName;
    }

    public String getPhotoName() {
        return photoName;
    }
}

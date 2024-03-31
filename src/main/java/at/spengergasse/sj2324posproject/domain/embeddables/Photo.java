package at.spengergasse.sj2324posproject.domain.embeddables;

import at.spengergasse.sj2324posproject.persistence.exception.PhotoUploadException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Embeddable
public class Photo {
    private static final Logger LOGGER = LoggerFactory.getLogger(Photo.class);
    @Column(length = 64, nullable = false)
    private @NotNull @NotEmpty String name;

    @Column(length = 1024, nullable = false)
    private String description;

    private @NotNull @PositiveOrZero int width;
    private @NotNull @PositiveOrZero int height;
    private @NotNull @NotEmpty String filetype;

    @Column(length = 255, nullable = false)
    private @NotNull @NotEmpty String filePath;

    public void uploadPhoto(byte[] photoBytes) {
        Path path = Paths.get("src/main/resources/images", this.name);
        try {
            Files.write(path, photoBytes);
            this.filePath = path.toString();
        } catch (IOException e) {
            LOGGER.error("Error uploading photo: {}", e.getMessage(), e);
            throw new PhotoUploadException(this.name, "Error uploading photo: " + e.getMessage());
        }
    }
}

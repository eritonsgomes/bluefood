package br.com.softblue.bluefood.infrastructure.web.validator;

import br.com.softblue.bluefood.util.FileType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public class LogotipoRestauranteUploadValidator implements ConstraintValidator<LogotipoRestauranteUploadConstraint,
    MultipartFile> {

    private List<FileType> acceptedTypes;

    @Override
    public void initialize(LogotipoRestauranteUploadConstraint constraintAnnotation) {
        this.acceptedTypes = List.of(constraintAnnotation.acceptedTypes());
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile == null) {
            return true;
        }

        boolean hasFoundFileType = acceptedTypes.stream().anyMatch(
                fileType -> fileType.sameOf(multipartFile.getContentType())
        );

        if (hasFoundFileType) {
            return Boolean.TRUE;
        }

        return false;
    }

}

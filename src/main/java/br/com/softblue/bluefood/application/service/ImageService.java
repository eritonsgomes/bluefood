package br.com.softblue.bluefood.application.service;

import br.com.softblue.bluefood.application.exception.ApplicationServiceException;
import br.com.softblue.bluefood.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${bluefood.files.restaurante.logotipo}")
    public String dirLogotipoRestaurante;

    public void uploadLogotipo(MultipartFile multipartFile, String fileName) {
        try {
            IOUtils.copy(multipartFile.getInputStream(), fileName, dirLogotipoRestaurante);
        } catch (IOException e) {
            throw new ApplicationServiceException(e);
        }
    }

}

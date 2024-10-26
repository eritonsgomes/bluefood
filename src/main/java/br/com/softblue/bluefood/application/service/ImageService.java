package br.com.softblue.bluefood.application.service;

import br.com.softblue.bluefood.application.exception.ApplicationServiceException;
import br.com.softblue.bluefood.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${bluefood.files.logotipos}")
    public String dirLogotipoRestaurante;

    @Value("${bluefood.files.categorias}")
    public String dirCategorias;

    @Value("${bluefood.files.comidas}")
    public String dirComidas;

    public void uploadLogotipo(MultipartFile multipartFile, String fileName) {
        try {
            IOUtils.copy(multipartFile.getInputStream(), fileName, dirLogotipoRestaurante);
        } catch (IOException e) {
            throw new ApplicationServiceException(e);
        }
    }

    public byte[] getImage(String type, String imageName) {
        try {
            String dir;

            if ("comidas".equals(type)) {
                dir = dirComidas;
            } else if ("logotipos".equals(type)) {
                dir = dirLogotipoRestaurante;
            } else if ("categorias".equals(type)) {
                dir = dirCategorias;
            } else {
                throw new Exception(type + " não é um tipo de imagem válido");
            }

            return IOUtils.getBytes(Paths.get(dir, imageName));
        } catch (Exception e) {
            throw new ApplicationServiceException(e);
        }
    }

}

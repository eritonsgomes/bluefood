package br.com.softblue.bluefood.infrastructure.web.controller;

import br.com.softblue.bluefood.application.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @ResponseBody
    @GetMapping(path = "/{type}/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable("type") String type, @PathVariable("imageName") String imageName) {
        return imageService.getImage(type, imageName);
    }

}

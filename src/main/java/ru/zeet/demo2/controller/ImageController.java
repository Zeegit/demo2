package ru.zeet.demo2.controller;



import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.zeet.demo2.domain.Image;
import ru.zeet.demo2.repo.ImageRepo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/image")
public class ImageController {
    private ImageRepo imageRepo;

    public ImageController(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    @GetMapping
    public String getFormToLoadImage(Model model) {
        model.addAttribute("message", "File too lage");
        return "image";
    }

    @PostMapping
    public String loadImage(@RequestParam("description") String description,
                            @RequestParam("file") MultipartFile file) throws IOException {
        Byte[] bArray = new Byte[file.getBytes().length];
        int i = 0;
        for(byte b : file.getBytes()) {
            bArray[i++] = b;
        }
        imageRepo.save(new Image(description, bArray));
        return "redirect:/image";
    }

    @GetMapping("/{id}")
    public void renderImage(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        Image image = imageRepo.findById(Long.valueOf(id)).get();
        byte[] bArray = new byte[image.getImage().length];
        int i = 0;
        for(byte b : image.getImage()) {
            bArray[i++] = b;
        }
        InputStream is = new ByteArrayInputStream(bArray);
        IOUtils.copy(is, response.getOutputStream());
    }
}

package com.ecom.ecom.controller;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.activation.FileTypeMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class ImageController {

    private final String allowedClientUrl = "https://ns471.csb.app";//"http://localhost:3000";


    @CrossOrigin(origins = allowedClientUrl)
    @GetMapping("images/{path}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable String path)throws IOException {
        File img = new File("images/"+path);
        return ResponseEntity.ok().
                contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img)))
                .body(Files.readAllBytes(img.toPath()));
    }

}

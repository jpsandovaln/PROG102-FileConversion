package org.fundacionjala.converter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/file_video")
    public String convertVideo(){
        return "video/video";
    }

    @GetMapping("/file_audio")
    public String converertAudio(){
        return "audio/audio";
    }

    @GetMapping("/file_docs")
    public String converertDocs(){
        return "docs/docs";
    }

    @GetMapping("/file_img")
    public String converertImg(){
        return "img/image";
    }

    @GetMapping("/file_metadata")
    public String viewMetadata(){
        return "metadata/metadata";
    }
}

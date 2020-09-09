package org.fundacionjala.converter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Julia Escalante
 * @version 0.1
 */
@Controller
public class ViewController {
    /**
    * displays video view
    * @return video view
    */
    @GetMapping("/file_video")
    public String convertVideo() {
        return "video/video";
    }
    /**
    * displays audio view
    * @return audio view
    */
    @GetMapping("/file_audio")
    public String converertAudio() {
        return "audio/audio";
    }
    /**
    * displays docs view
    * @return docs view
    */
    @GetMapping("/file_docs")
    public String converertDocs(){
        return "docs/docs";
    }
    /**
    * displays image view
    * @return image view
    */
    @GetMapping("/file_img")
    public String converertImg(){
        return "img/image";
    }
    /**
    * displays metadata view
    * @return metadata view
    */
    @GetMapping("/file_metadata")
    public String viewMetadata(){
        return "metadata/metadata";
    }
}

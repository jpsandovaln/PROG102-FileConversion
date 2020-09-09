package org.fundacionjala.converter.controller;
import org.fundacionjala.converter.model.ImageModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {

    @RequestMapping(value = "/image/resize", method = RequestMethod.POST)
    public String Convert(@RequestParam("checkSum") String checkSum, @RequestParam("file") MultipartFile file, @RequestParam("newName") String newName, @RequestParam("Extension") String extension, @RequestParam("height") int height, @RequestParam("weight") int weight, @RequestParam("thumbnail") int thumbnail ) {
        ImageModel img =  new ImageModel();
        return img.execute(file, newName, height, weight, extension, thumbnail);
    }

    @RequestMapping(value = "/image/crop", method = RequestMethod.POST)
    public String cropImage(@RequestParam("checkSum") String checkSum, @RequestParam("file") MultipartFile file, @RequestParam("newName") String newName, @RequestParam("Extension") String extension, @RequestParam("height") int height, @RequestParam("weight") int weight, @RequestParam("positionX")  int positionX, @RequestParam("positionY") int positionY) {
        ImageModel img =  new ImageModel();
        return img.selectingImageRegion(file,newName,height,weight,positionX,positionY, extension);
    }

    @RequestMapping(value = "/image/gris_scale", method = RequestMethod.POST)
    public String grisScaleImage(@RequestParam("checkSum") String checkSum, @RequestParam("file") MultipartFile file, @RequestParam("newName") String newName, @RequestParam("Extension") String extension, @RequestParam("height") int height, @RequestParam("weight") int weight) {
        ImageModel img =  new ImageModel();
        return img.grayScale(file, newName, extension);
    }
}

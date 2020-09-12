package org.fundacionjala.converter.controller;
import org.fundacionjala.converter.model.command.ImageModel;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {

    /**
     *
     * @param checkSum
     * @param file
     * @param newName
     * @param extension
     * @param height
     * @param weight
     * @param thumbnail
     * @param forceResize
     * @return
     */
    @RequestMapping(value = "/image/resize", method = RequestMethod.POST)
    public String convert(@RequestParam("file") final MultipartFile file, @RequestParam("newName") final String newName, @RequestParam("Extension") final String extension, @RequestParam("height") final int height, @RequestParam("weight") final int weight, @RequestParam("thumbnail") final int thumbnail, @RequestParam("forceResize") final int forceResize) {
        ImageModel img =  new ImageModel();
        return img.convert(file, newName, height, weight, extension, thumbnail, forceResize);
    }

    /**
     *
     * @param checkSum
     * @param file
     * @param newName
     * @param extension
     * @param height
     * @param weight
     * @param positionX
     * @param positionY
     * @return
     */
    @RequestMapping(value = "/image/crop", method = RequestMethod.POST)
    public String cropImage(@RequestParam("file") final MultipartFile file, @RequestParam("newName") final String newName, @RequestParam("Extension") final String extension, @RequestParam("height") final int height, @RequestParam("weight") final int weight, @RequestParam("positionX")  final int positionX, @RequestParam("positionY") final int positionY) {
        ImageModel img =  new ImageModel();
        return img.selectingImageRegion(file, newName, height, weight, positionX, positionY, extension);
    }

    /**
     *
     * @param checkSum
     * @param file
     * @param newName
     * @param extension
     * @param height
     * @param weight
     * @return
     */
    @RequestMapping(value = "/image/gris_scale", method = RequestMethod.POST)
    public String grisScaleImage(@RequestParam("checkSum") final String checkSum, @RequestParam("file") final MultipartFile file, @RequestParam("newName") final String newName, @RequestParam("Extension") final String extension, @RequestParam("height") final int height, @RequestParam("weight") final int weight) {
        ImageModel img =  new ImageModel();
        return img.grayScale(file, newName, extension);
    }
}

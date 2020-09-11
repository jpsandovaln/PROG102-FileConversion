/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;
import org.fundacionjala.converter.controller.request.RequestImageParameter;
import org.fundacionjala.converter.model.entity.File;
import org.fundacionjala.converter.model.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class ImageController {

  /*  /**
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
/*    @RequestMapping(value = "/image/resize", method = RequestMethod.POST)
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
   /* @RequestMapping(value = "/image/crop", method = RequestMethod.POST)
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
   /* @RequestMapping(value = "/image/gris_scale", method = RequestMethod.POST)
    public String grisScaleImage(@RequestParam("checkSum") final String checkSum, @RequestParam("file") final MultipartFile file, @RequestParam("newName") final String newName, @RequestParam("Extension") final String extension, @RequestParam("height") final int height, @RequestParam("weight") final int weight) {
        ImageModel img =  new ImageModel();
        return img.grayScale(file, newName, extension);
    }*/
  @Autowired
  private FileService fileService;
    @Value("${tempFiles.path}")
    private String temporal;

    /**
     *
     * @param requestImageParameter
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "convertImage")
    public String convertImage(final RequestImageParameter requestImageParameter) throws IOException {

        //ImageParameter imageParameter;
        String result = "Error";
       // Executor exec;
        String path = temporal + requestImageParameter.getFile().getOriginalFilename();
        Files.copy(requestImageParameter.getFile().getInputStream(), Paths.get(path));
        if (requestImageParameter.validate()) {
            fileService.saveFile(new File(path, requestImageParameter.generateMD5(path)));
            //imageParameter = new ImageParameter();
            //result = exec.executer(imageParameter);
        }
        Files.delete(Paths.get(path));
        return result;
    }
}

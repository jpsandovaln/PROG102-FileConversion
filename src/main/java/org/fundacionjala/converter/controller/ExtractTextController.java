package org.fundacionjala.converter.controller;


import org.fundacionjala.converter.model.ExtractorModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
public class ExtractTextController {
    @Value("${ocr.path}")
    private String tesseracInstallPath;
    @Value("${inputFiles.path}")
    private String input;
    @Value("${convertedFiles.path}")
    private String converted;

    /**
     * extract the text of the text of the image
     * @param file the image's text that will be extracted
     * @param language the language that will be the text
     * @param type the type of document that will be transformed
     * @return the text extracted
     */
    @RequestMapping(method = RequestMethod.POST, value = "/extractText")
    public String extractTextFromImage(final @RequestParam("file") MultipartFile file, final  @RequestParam(required = false) String language,
                                       final @RequestParam(required = false) String type) throws Exception {
        String fileName = file.getOriginalFilename();
        String result = "";
        String source = input + fileName;
        String target = converted + fileName;
        Files.copy(file.getInputStream(), Paths.get(input + fileName), StandardCopyOption.REPLACE_EXISTING);
        ExtractorModel model = new ExtractorModel();
        result = model.convert(source, target, language, type, tesseracInstallPath);
        return result;
    }


}

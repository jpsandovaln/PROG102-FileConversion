/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Elizabeth Bravo Flores
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
    public String convertAudio() {
        return "audio/audio";
    }
    /**
    * displays docs view
    * @return docs view
    */
    @GetMapping("/file_extractText")
    public String extractText() {
        return "extract/extractText";
    }
    /**
    * displays image view
    * @return image view
    */
    @GetMapping("/file_img")
    public String convertImage() {
        return "img/image";
    }
    /**
    * displays metadata view
    * @return metadata view
    */
    @GetMapping("/file_metadata")
    public String viewMetadata() {
        return "metadata/metadata";
    }

    /**
     * displays login view
     * @return metadata view
     */
    @GetMapping("/login")
    public String login(final Model model) {
        return "auth/login";
    }
    /**
     * displays view user logged
     * @return user view
     */
    @GetMapping("/welcome")
    public String homeUser() {
        return "auth/welcome";
    }

    /**
     * displays account creation
     * @return user view
     */
    @GetMapping("/createAccount")
    public String createAccount(final Model model) {
        return "auth/createAccount";
    }
}

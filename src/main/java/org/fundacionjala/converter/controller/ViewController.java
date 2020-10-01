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
    * Displays video view
    * @return video/video - the reference String to fiel video
    */
    @GetMapping("/file_video")
    public String convertVideo() {
        return "video/video";
    }
    /**
    * Displays audio view
    * @return audio/audio - the reference String to file audio
    */
    @GetMapping("/file_audio")
    public String convertAudio() {
        return "audio/audio";
    }
    /**
    * Displays docs view
    * @return extract/extractText - the reference String to file extractText
    */
    @GetMapping("/file_extractText")
    public String extractText() {
        return "extract/extractText";
    }
    /**
    * Displays image view
    * @return img/image - the reference String to file image
    */
    @GetMapping("/file_img")
    public String convertImage() {
        return "img/image";
    }
    /**
    * Displays metadata view
    * @return metadata/metadata - the reference String to file metadata
    */
    @GetMapping("/file_metadata")
    public String viewMetadata() {
        return "metadata/metadata";
    }

    /**
     * Displays login view
     * @return auth/login - the reference String to login
     */
    @GetMapping("/login")
    public String login(final Model model) {
        return "auth/login";
    }
    /**
     * Displays view user logged
     * @return auth/welcome - the reference String to welcome
     */
    @GetMapping("/welcome")
    public String homeUser() {
        return "auth/welcome";
    }

    /**
     * Displays account creation
     * @return auth/createAccount - the reference String to createAccount
     */
    @GetMapping("/createAccount")
    public String createAccount(final Model model) {
        return "auth/createAccount";
    }
}

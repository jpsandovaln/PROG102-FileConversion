/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.parameter.extractText;

import org.fundacionjala.converter.model.parameter.ModelParameter;

public class ExtractTextParameter extends ModelParameter {
    private String language;
    private String type;

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(final String language) {
        //this.language = language;
        if (language.equals("español")) {
            this.language = "-l spa";
        } else {
            this.language = "";
        }
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(final String type) {
        switch (type) {
            case "word":
                this.type = ".docx";
                break;
            case "pdf":
                this.type = ".pdf";
                break;
            case "SS":
                this.type = ".txt";
                break;
            case "text":
                this.type = ".txt";
                break;
            default:
                this.type = ".txt";
                System.out.println("Format not supported please insert a valid format");
                break;
        }
    }

    /**
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "ExtractTextParameter [language=" + language + ", type=" + type + "]";
    }
}

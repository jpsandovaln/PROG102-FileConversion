package org.fundacionjala.converter.model.parameter.extractText;

import java.util.List;
import org.fundacionjala.converter.model.parameter.ModelParameter;

public class ExtractTextParameter extends ModelParameter {
    private String language;
    private String type;

	/**
     * List<String> getParameter()
     */
    @Override
	public List<String> getParameter() {
        return null;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
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
    public void setType(String type) {
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "ExtractTextParameter [language=" + language + ", type=" + type + "]";
    }
}

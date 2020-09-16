package org.fundacionjala.converter.model.parameter.extractText;

import java.util.List;

import org.fundacionjala.converter.model.parameter.ModelParameter;

public class ExtractTextParameter extends ModelParameter {
    private String language;
    private String type;

    public ExtractTextParameter(String inputFile, String outputFile, String md5) {
        super(inputFile, outputFile, md5);
    }

	/**
     * List<String> getParameter()
     */
    @Override
	public List<String> getParameter() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "ExtractTextParameter []";
    }
}

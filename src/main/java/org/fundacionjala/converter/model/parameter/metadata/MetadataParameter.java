package org.fundacionjala.converter.model.parameter.metadata;

import org.fundacionjala.converter.model.parameter.ModelParameter;

public class MetadataParameter extends ModelParameter {

    private String format;
    private String detail;

    /**
     *
     * @return format
     */
    public String getFormat() {
        return format;
    }

    /**
     *
     * @return detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * List<String> getParameter()
     */

    public MetadataParameter(final String inputFile, final String format, final String detail, final String outputFile,
            final String md5) {
        super(inputFile, outputFile, md5);
        this.detail = detail;
        this.format = format;
    }
}

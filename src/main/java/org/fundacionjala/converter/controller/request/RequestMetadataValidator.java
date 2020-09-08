package org.fundacionjala.converter.controller.request;


import org.apache.commons.io.FilenameUtils;
import java.util.ArrayList;
import java.util.List;

public class RequestMetadataValidator {
    private List<String> validTypes;
    private List<String> validExportFormat;
    private List<String> validDetailFormat;

    public RequestMetadataValidator() {
        validTypes = new ArrayList<String>();
        validTypes.add("mp4");
        validTypes.add("MP4");
        validTypes.add("avi");
        validTypes.add("AVI");
        validTypes.add("jpeg");
        validTypes.add("JPEG");
        validTypes.add("png");
        validTypes.add("PNG");
        validTypes.add("jpg");
        validTypes.add("JPG");
        validTypes.add("wav");
        validTypes.add("ogg");
        validTypes.add("mkv");
        validExportFormat = new ArrayList<String>();
        validExportFormat.add("j");
        validExportFormat.add("t");
        validExportFormat.add("T");
        validExportFormat.add("h");
        validExportFormat.add("x");
        validDetailFormat = new ArrayList<String>();
        validDetailFormat.add("c");
        validDetailFormat.add("v");
    }

    public boolean isValid(RequestMetadataParam param) {
        return isValidFile(param.getPathFile()) && isValidExportFormat(param.getExportFormat()) && isValidDetail(param.getDetail());
    }
    public boolean isValidFile(final String filePath) {
        String extFile = FilenameUtils.getExtension(filePath);
        return validTypes.contains(extFile);
    }
    public boolean isValidExportFormat(final String exportFormat) {
        return validExportFormat.contains(exportFormat);
    }
    public boolean isValidDetail(final String detail) {
        return validDetailFormat.contains(detail);
    }
}

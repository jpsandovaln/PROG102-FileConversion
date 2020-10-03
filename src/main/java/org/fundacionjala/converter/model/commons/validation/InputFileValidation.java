package org.fundacionjala.converter.model.commons.validation;

import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import java.awt.Image;
import javax.swing.ImageIcon;

public class InputFileValidation implements IValidationStrategy {
    private String inputFile;

    public InputFileValidation(final String inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * @throws InvalidDataException
     */
    @Override
    public void validate() throws InvalidDataException {
        Image image = new ImageIcon(inputFile).getImage();
        if (image.getWidth(null) == -1) {
            throw new InvalidDataException("Invalid input file");
        }
    }
}

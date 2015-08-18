/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.util;

/**
 * Class which provides useful operations on file or file name.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public final class FileUtil {

    /** reserved filename and folders on win systems. */
    private static final String[] WINDOWS_RESERVED_FILE_AND_FOLDER_NAME = { "com1", "com2", "com3", "com4", "com5", "com6", "com7", "com8", "com9", "lpt1", "lpt2", "lpt3", "lpt4", "lpt5", "lpt6", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$
            "lpt7", "lpt8", "lpt9", "con", "nul", "prn", }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

    /** max length for file name on windows. */
    private static final int WINDOWS_FILENAME_MAX_LENGTH = 255;

    // CHECKSTYLE:OFF
    /** illegal characters for all os. */
    private static final char[] ILLEGAL_CHARACTERS = { '/', '\n', '\r', '\t', '\0', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':' };

    // CHECKSTYLE:ON

    private String filename;

    private InvalidFilenameError error;

    /**
     * Construct a new instance.
     * 
     * @param filename
     *            the file name
     */
    public FileUtil(final String filename) {
        this.filename = filename;
        this.error = InvalidFilenameError.NO_ERROR;
    }

    /**
     * Check if the path is valid or not.
     * 
     * @return <code>true</code> if the path is valid, <code>false</code>
     *         otherwise
     * @see FileUtil#getError() to retrieve the error if path is not valid
     */
    public boolean isValid() {
        boolean valid = false;
        if (containsIllegalCharacter(filename)) {
            error = InvalidFilenameError.ILLEGAL_CHARACTERS;
        } else if (isAReservedFilename(filename)) {
            error = InvalidFilenameError.RESERVED_FILENAME;
        } else if (exceedMaxLength(filename)) {
            error = InvalidFilenameError.MAX_LENGTH;
        } else {
            valid = true;
        }
        return valid;
    }

    private boolean containsIllegalCharacter(final String file) {
        for (final Character illegalChar : ILLEGAL_CHARACTERS) {
            if ((file.indexOf(illegalChar)) != -1) {
                return true;
            }
        }
        return false;
    }

    private boolean isAReservedFilename(String file) {
        for (final String reservedFilename : WINDOWS_RESERVED_FILE_AND_FOLDER_NAME) {
            if (reservedFilename.equals(file)) {
                return true;
            }
        }
        return false;
    }

    private boolean exceedMaxLength(String file) {
        if (file.length() > WINDOWS_FILENAME_MAX_LENGTH) {
            return true;
        }
        return false;
    }

    /**
     * Get the error.
     * 
     * @return the error
     */
    public InvalidFilenameError getError() {
        return error;
    }

    /**
     * Get a valid path from the original one. Characters could be removed and
     * path could be truncated.
     * 
     * @return a valid path.
     */
    public String getValidFilename() {
        String newFilename = removeIllegalCharacters();

        while (isAReservedFilename(newFilename)) {
            newFilename = newFilename.concat("-"); //$NON-NLS-1$
        }
        while (exceedMaxLength(newFilename)) {
            newFilename = newFilename.substring(0, newFilename.length() - 2);
        }
        return newFilename;
    }

    private String removeIllegalCharacters() {
        String safe = filename;
        for (final Character illegalChar : ILLEGAL_CHARACTERS) {
            if ((safe.indexOf(illegalChar)) != -1) {
                safe = safe.replace(illegalChar, '-');
            }
        }
        return safe;
    }

    /**
     * Error kind if if file is invalid.
     * 
     * @author mchauvin
     */
    public enum InvalidFilenameError {
        /** there was no error. */
        NO_ERROR,
        /** there was at least one illegal character in the file name. */
        ILLEGAL_CHARACTERS,
        /** the filename used is reserved. */
        RESERVED_FILENAME,
        /** the filename length exceeds the max length. */
        MAX_LENGTH
    }
}

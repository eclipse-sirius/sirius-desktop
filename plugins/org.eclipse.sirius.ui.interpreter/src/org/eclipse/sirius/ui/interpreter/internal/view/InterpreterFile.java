/*******************************************************************************
 * Copyright (c) 2011, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.view;

/**
 * If a language interpreter generates files, this can be used to represent them. This class is only used as an
 * in-memory representation; the file itself will never be serialized.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class InterpreterFile {
    /** Content of the generated file. */
    private final String fileContent;

    /** Name of the generated file. */
    private final String fileName;

    /**
     * Instantiates a generated file given its name and content.
     * 
     * @param fileName
     *            Name of the generated file.
     * @param fileContent
     *            Content of the generated file.
     */
    public InterpreterFile(String fileName, String fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    /**
     * Returns the content of this generated file.
     * 
     * @return The content of this generated file.
     */
    public String getFileContent() {
        return fileContent;
    }

    /**
     * Returns the name of this generated file.
     * 
     * @return The name of this generated file.
     */
    public String getFileName() {
        return fileName;
    }
}

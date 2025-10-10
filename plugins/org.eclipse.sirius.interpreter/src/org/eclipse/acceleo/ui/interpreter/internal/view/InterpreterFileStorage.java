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
package org.eclipse.acceleo.ui.interpreter.internal.view;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.acceleo.ui.interpreter.view.InterpreterFile;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.PlatformObject;

/**
 * This implementation of an IStorage will allow us to open editors for {@link InterpreterFile}s, i.e an in-memory
 * representation of a generated file as displayed in the result view of the Interpreter.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class InterpreterFileStorage extends PlatformObject implements IStorage {
    /** The interpreter file that will be opened in an editor. */
    private final InterpreterFile target;

    /**
     * Instantiates a storage for the given InterpreterFile.
     * 
     * @param target
     *            The interpreter file that will be opened in an editor.
     */
    public InterpreterFileStorage(InterpreterFile target) {
        this.target = target;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.resources.IStorage#getContents()
     */
    public InputStream getContents() throws CoreException {
        return new ByteArrayInputStream(target.getFileContent().getBytes());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.resources.IStorage#getFullPath()
     */
    public IPath getFullPath() {
        return new Path(target.getFileName());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.resources.IStorage#getName()
     */
    public String getName() {
        return target.getFileName();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.resources.IStorage#isReadOnly()
     */
    public boolean isReadOnly() {
        return true;
    }
}

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

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.PlatformUI;

/**
 * This editor input will be used in order to open editors for the "generated files" displayed as result of evaluations
 * from the interpreter.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class StorageEditorInput extends PlatformObject implements IStorageEditorInput {
    /**
     * Storage associated with this editor input.
     */
    private IStorage storage;

    /**
     * Constructs an editor input on the given storage.
     * 
     * @param targetStorage
     *            The storage associated with this editor input.
     */
    public StorageEditorInput(IStorage targetStorage) {
        storage = targetStorage;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        return object instanceof StorageEditorInput && getStorage().equals(((StorageEditorInput) object).getStorage());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IEditorInput#exists()
     */
    public boolean exists() {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
     */
    public ImageDescriptor getImageDescriptor() {
        return PlatformUI.getWorkbench().getEditorRegistry().getImageDescriptor(storage.getName());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IEditorInput#getName()
     */
    public String getName() {
        return getStorage().getName();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IEditorInput#getPersistable()
     */
    public IPersistableElement getPersistable() {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IStorageEditorInput#getStorage()
     */
    public IStorage getStorage() {
        return storage;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IEditorInput#getToolTipText()
     */
    public String getToolTipText() {
        return getStorage().getFullPath().toOSString();
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return getStorage().hashCode();
    }
}

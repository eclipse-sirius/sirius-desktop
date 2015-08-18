/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;

/**
 * Class able to tell whether a save operation on a resource can succeed or not,
 * and if the resulting file will change.
 * 
 * Please use with care as we basically save the resource in a temporary
 * resource to know whether it will change the file or not.
 * 
 * @author cbrun
 */
public class ResourceSaveDiagnose {

    private final Resource resourcetoSave;

    /**
     * Create a new diagnose for the given resource.
     * 
     * @param res
     *            resource to diagnose.
     */
    public ResourceSaveDiagnose(final Resource res) {
        this.resourcetoSave = res;
    }

    /**
     * return true if the resource might be saved, false otherwise.
     * 
     * @return true if the resource might be saved, false otherwise.
     */
    public boolean isSaveable() {
        boolean isSaveable = !ResourceSetSync.isReadOnly(resourcetoSave);
        return isSaveable;
    }

    /**
     * return true if the resource will get a different serialization than the
     * one on the disk.
     * 
     * @param options
     *            save options.
     * @return true if the resource will get a different serialization than the
     *         one on the disk.
     * @throws IOException
     *             on error while saving.
     */
    public boolean hasDifferentSerialization(final Map<?, ?> options) throws IOException {
        // CHECKSTYLE:OFF : code coming from
        // ResourceImpl.saveOnlyIfChangedWithFileBuffer
        resourcetoSave.eSetDeliver(false);
        final File temporaryFile = File.createTempFile("ResourceSaveHelper", null); //$NON-NLS-1$
        boolean equal = true;
        try {
            final URI temporaryFileURI = URI.createFileURI(temporaryFile.getPath());

            final URIConverter uriConverter = resourcetoSave.getResourceSet() == null ? new ResourceSetImpl().getURIConverter() : resourcetoSave.getResourceSet().getURIConverter();
            final OutputStream temporaryFileOutputStream = uriConverter.createOutputStream(temporaryFileURI);

            long preSaveTimestamp = resourcetoSave.getTimeStamp();
            boolean preSaveIsModified = resourcetoSave.isModified();
            try {
                resourcetoSave.save(temporaryFileOutputStream, options);
            } finally {
                temporaryFileOutputStream.close();
                /*
                 * saving the resource instance even in an external buffer will
                 * change the session state, notably isModified and the
                 * timestamp (see the implementation of ResourceImpl). We don't
                 * want to have this side effect, the best we can do is undo it
                 * aftersaving.
                 */
                resourcetoSave.setModified(preSaveIsModified);
                resourcetoSave.setTimeStamp(preSaveTimestamp);
            }

            InputStream oldContents = null;

            try {
                oldContents = uriConverter.createInputStream(resourcetoSave.getURI());
            } catch (final IOException exception) {
                equal = false;
            }
            final byte[] newContentBuffer = new byte[4000];
            if (oldContents != null) {
                try {
                    final InputStream newContents = uriConverter.createInputStream(temporaryFileURI);
                    try {
                        final byte[] oldContentBuffer = new byte[4000];
                        LOOP: for (int oldLength = oldContents.read(oldContentBuffer), newLength = newContents.read(newContentBuffer); (equal = oldLength == newLength)
                                && oldLength > 0; oldLength = oldContents.read(oldContentBuffer), newLength = newContents.read(newContentBuffer)) {
                            for (int i = 0; i < oldLength; ++i) {
                                if (oldContentBuffer[i] != newContentBuffer[i]) {
                                    equal = false;
                                    break LOOP;
                                }
                            }
                        }
                    } finally {
                        newContents.close();
                    }
                } finally {
                    oldContents.close();
                }
            }
        } finally {
            temporaryFile.delete();
            resourcetoSave.eSetDeliver(true);
        }
        // CHECKSTYLE:ON
        return !equal;
    }
}

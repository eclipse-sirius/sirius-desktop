/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.part;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;

/**
 * @was-generated
 */
public class SiriusMatchingStrategy implements IEditorMatchingStrategy {

    /**
     * @was-generated
     */
    @Override
    public boolean matches(IEditorReference editorRef, IEditorInput input) {
        IEditorInput editorInput;
        try {
            editorInput = editorRef.getEditorInput();
        } catch (PartInitException e) {
            return false;
        }
        if (editorInput == null) {
            return false;
        }

        if (editorInput.equals(input)) {
            return true;
        }
        // Manage FileEditorInput to support Sirius editor opening from a marker
        if (input instanceof FileEditorInput && editorInput instanceof URIEditorInput) {
            String uriEditorInputPath = ((URIEditorInput) editorInput).getURI().toPlatformString(false);
            String fileEditorInputPath = ((FileEditorInput) input).getFile().getFullPath().toString();
            return ((uriEditorInputPath != null) && (fileEditorInputPath != null) && (uriEditorInputPath.equals(fileEditorInputPath)));
        }
        return false;
    }
}

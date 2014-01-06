/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.sample.ecore.design.editor;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.session.SessionSpecificEditorInput;

public class EcoreEntitiesMatchingStrategy implements IEditorMatchingStrategy {

    public boolean matches(IEditorReference editorRef, IEditorInput input) {
        boolean result = false;
        SessionSpecificEditorInput sseiRef = getSessionSpecificEditorInput(editorRef);

        if (sseiRef != null) {
            if (sseiRef.equals(input)) {
                result = true;
            } else if (input instanceof IFileEditorInput) {
                Session sessionRef = sseiRef.getSession();
                URI inputUri = URI.createPlatformResourceURI(((IFileEditorInput) input).getFile().getFullPath().toString(), true);

                if (sessionRef != null && sessionRef.isOpen()) {
                    Resource currentRes = sessionRef.getTransactionalEditingDomain().getResourceSet().getResource(inputUri, false);
                    result = currentRes != null && sessionRef.getSemanticResources().contains(currentRes);
                }
            }
        }

        return result;
    }

    private SessionSpecificEditorInput getSessionSpecificEditorInput(IEditorReference editorRef) {
        SessionSpecificEditorInput ssei = null;
        try {
            IEditorInput editorInput = editorRef.getEditorInput();
            if (editorInput instanceof SessionSpecificEditorInput) {
                ssei = (SessionSpecificEditorInput) editorInput;
            }
        } catch (PartInitException e) {
            // DO nothing.
        }
        return ssei;
    }

}

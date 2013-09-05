/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.helper.task;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import com.google.common.base.Predicate;

import org.eclipse.sirius.business.api.helper.task.AbstractDeleteDRepresentationElementTask;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * A task to delete a DDiagramElement.
 * 
 * @author mchauvin
 */
public class DeleteDDiagramElementTask extends AbstractDeleteDRepresentationElementTask {

    private static final Predicate<EReference> VIEW_ELEMENT_REFERENCE_TO_IGNORE_PREDICATE = new Predicate<EReference>() {

        public boolean apply(EReference eReference) {
            // We should ignore View.element reference because not doing
            // that have the effect to have the EditParts having
            // "semantic element" the DSemanticDiagram because
            // View.getElement() if View.element == null returns the
            // View.element of the parent
            // It is not a problem to leave a dangling reference on
            // View.element because the canonical refresh in precommit will
            // remove these Views referencing a deleted DDiagramElement
            return GMF_REFERENCE_NAME_TO_IGNORE.equals(eReference.getName()) && GMF_REFERENCE_CONTAINER_NAME_TO_IGNORE.equals(eReference.getContainerClass().getName());
        }
    };

    /**
     * Name of the GMF View feature to ignore in the remove dangling references.
     */
    private static final String GMF_REFERENCE_NAME_TO_IGNORE = "element";

    /**
     * Container name of the GMF View feature to ignore in the remove dangling
     * references.
     */
    private static final String GMF_REFERENCE_CONTAINER_NAME_TO_IGNORE = "org.eclipse.gmf.runtime.notation.View";

    /**
     * Default Constructor.
     * 
     * @param objectsToDelete
     *            the objects to delete
     * @param modelAccessor
     *            the model accessor.
     */
    public DeleteDDiagramElementTask(EObject objectsToDelete, ModelAccessor modelAccessor) {
        super(objectsToDelete, modelAccessor, VIEW_ELEMENT_REFERENCE_TO_IGNORE_PREDICATE);
    }

}

/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.business.api.helper.task.AbstractDeleteDRepresentationElementTask;
import org.eclipse.sirius.business.internal.session.danalysis.DanglingRefRemovalTrigger;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * A task to delete a DDiagramElement.
 * 
 * @author mchauvin
 */
public class DeleteDDiagramElementTask extends AbstractDeleteDRepresentationElementTask {

    /**
     * Default Constructor.
     * 
     * @param objectsToDelete
     *            the objects to delete
     * @param modelAccessor
     *            the model accessor.
     */
    public DeleteDDiagramElementTask(EObject objectsToDelete, ModelAccessor modelAccessor) {
        super(objectsToDelete, modelAccessor, DanglingRefRemovalTrigger.NOTATION_VIEW_ELEMENT_REFERENCE_TO_IGNORE_PREDICATE);
    }
}
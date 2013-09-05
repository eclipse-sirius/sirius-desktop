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
package org.eclipse.sirius.tree.business.internal.helper;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.sirius.business.api.helper.task.AbstractDeleteDRepresentationElementTask;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * A task to delete a {@link DTableElement}. Only {@link DLine} or
 * {@link DTargetColumn} can be deleted.
 * 
 * @author nlepine
 */
public class DeleteTreeElementTask extends AbstractDeleteDRepresentationElementTask {

    /**
     * Default Constructor.
     * 
     * @param objectToDelete
     *            the object to delete
     * @param modelAccessor
     *            the model accessor.
     */
    public DeleteTreeElementTask(final EObject objectToDelete, final ModelAccessor modelAccessor) {
        super(objectToDelete, modelAccessor);
    }

    public String getLabel() {
        return "delete tree element task";
    }

}

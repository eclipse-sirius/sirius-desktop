/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.internal.helper.task;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.sirius.business.api.helper.task.AbstractDeleteDRepresentationElementTask;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * A task to delete a
 * {@link org.eclipse.sirius.table.metamodel.table.DTableElement}. Only
 * {@link org.eclipse.sirius.table.metamodel.table.DLine} or
 * {@link org.eclipse.sirius.table.metamodel.table.DTargetColumn} can be
 * deleted.
 * 
 * @author lredor
 */
public class DeleteTableElementTask extends AbstractDeleteDRepresentationElementTask {

    /**
     * Default Constructor.
     * 
     * @param objectToDelete
     *            the objects to delete
     * @param modelAccessor
     *            the {@link ModelAccessor} to use to perform the deletion
     */
    public DeleteTableElementTask(final EObject objectToDelete, final ModelAccessor modelAccessor) {
        super(objectToDelete, modelAccessor);
    }

    public String getLabel() {
        return "delete table element task";
    }

}

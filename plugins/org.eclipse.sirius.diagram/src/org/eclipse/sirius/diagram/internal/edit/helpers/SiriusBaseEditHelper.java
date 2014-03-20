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
package org.eclipse.sirius.diagram.internal.edit.helpers;

import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelper;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;

//CHECKSTYLE:OFF
/**
 * @was-generated
 */
public class SiriusBaseEditHelper extends AbstractEditHelper {

    /**
     * @was-generated
     */
    public static final String EDIT_POLICY_COMMAND = "edit policy command"; //$NON-NLS-1$

    /**
     * @was-generated
     */
    protected ICommand getInsteadCommand(IEditCommandRequest req) {
        ICommand epCommand = (ICommand) req.getParameter(EDIT_POLICY_COMMAND);
        req.setParameter(EDIT_POLICY_COMMAND, null);
        ICommand ehCommand = super.getInsteadCommand(req);
        if (epCommand == null) {
            return ehCommand;
        }
        if (ehCommand == null) {
            return epCommand;
        }
        CompositeCommand command = new CompositeCommand(null);
        command.add(epCommand);
        command.add(ehCommand);
        return command;
    }

    /**
     * @was-generated
     */
    protected ICommand getCreateCommand(CreateElementRequest req) {
        return null;
    }

    /**
     * @was-generated
     */
    protected ICommand getCreateRelationshipCommand(CreateRelationshipRequest req) {
        return null;
    }

    /**
     * @was-generated
     */
    protected ICommand getDestroyElementCommand(DestroyElementRequest req) {
        return null;
    }

    /**
     * @was-generated
     */
    protected ICommand getDestroyReferenceCommand(DestroyReferenceRequest req) {
        return null;
    }
    // CHECKSTYLE:ON
}

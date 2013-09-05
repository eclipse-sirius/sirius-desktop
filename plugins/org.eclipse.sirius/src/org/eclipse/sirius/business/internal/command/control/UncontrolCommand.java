/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.command.control;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.util.TransactionUtil;

/**
 * A recording version of the standard EMF "Uncontrol" action. In addition to
 * being transactional, this version also separates Control (see
 * {@link ControlCommand}) from Uncontrol and does not do user-interaction (so
 * it can be called from automated tests for example).
 * 
 * @author pcdavid
 */
public class UncontrolCommand extends RecordingCommand {
    /**
     * The model object to uncontrol (i.e. to move back into its parent's
     * resource).
     */
    protected final EObject semanticElementToUncontrol;

    /**
     * Create a new {@link UncontrolCommand}.
     * 
     * @param eObject
     *            the model object to uncontrol (i.e. to move back into its
     *            parent's resource).
     */
    public UncontrolCommand(final EObject eObject) {
        super(TransactionUtil.getEditingDomain(eObject));
        this.semanticElementToUncontrol = eObject;
    }

    /**
     * Check that the object to uncontrol is actually controlled, and thus that
     * the command seems executable without error.
     * 
     * @return <code>true</code> if the object to uncontrol is controlled.
     * 
     * @see org.eclipse.emf.transaction.RecordingCommand#prepare()
     */
    @Override
    protected boolean prepare() {
        return super.prepare() && AdapterFactoryEditingDomain.isControlled(semanticElementToUncontrol);
    }

    /**
     * Executes the uncontrol command.
     */
    @Override
    protected void doExecute() {
        /*
         * Simply removing the object from the controlled resource re-integrates
         * it into the parent resource.
         */
        semanticElementToUncontrol.eResource().getContents().remove(semanticElementToUncontrol);
        EcoreUtil.resolveAll(TransactionUtil.getEditingDomain(semanticElementToUncontrol).getResourceSet());
    }
}

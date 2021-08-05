/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.command.control;

import java.text.MessageFormat;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.tools.api.Messages;

/**
 * A recording version of the standard EMF "Control" action. In addition to being transactional, this version also
 * separates Control from Uncontrol (see {@link UncontrolCommand}) and does not do user-interaction (so it can be called
 * from automated tests for example).
 * 
 * @author pcdavid
 */
public class ControlCommand extends RecordingCommand {
    /**
     * The {@link org.eclipse.emf.edit.domain.EditingDomain} in which to perform the change.
     */
    protected final TransactionalEditingDomain domain;

    /**
     * The model object to control (i.e. to move into a separate resource).
     */
    protected final EObject semanticObjectToControl;

    /**
     * The URI of the resource in which to move the object to control.
     */
    protected final URI destination;

    /**
     * The resource in which the controlled element ha been moved, after the command is successfully executed.
     */
    protected Resource controlledResource;

    /**
     * Create a new {@link ControlCommand}.
     * 
     * @param semanticObjectToControl
     *            the model object to control (i.e. to move into a separate resource).
     * @param destination
     *            the resource in which the controlled element ha been moved, after the command is successfully
     *            executed.
     */
    public ControlCommand(final EObject semanticObjectToControl, final URI destination) {
        super(TransactionUtil.getEditingDomain(semanticObjectToControl));
        this.domain = TransactionUtil.getEditingDomain(semanticObjectToControl);
        this.semanticObjectToControl = semanticObjectToControl;
        this.destination = destination;
    }

    /**
     * Check that the specified object is controllable, and thus that the command seems executable without error.
     * 
     * @return <code>true</code> if the specified object is controllable.
     * 
     * @see org.eclipse.emf.transaction.RecordingCommand#prepare()
     */
    @Override
    protected boolean prepare() {
        return super.prepare() && domain.isControllable(semanticObjectToControl);
    }

    /**
     * Executes the control command.
     */
    @Override
    protected void doExecute() {
        move(semanticObjectToControl, destination);
        EcoreUtil.resolveAll(domain.getResourceSet());
    }

    /**
     * Move the specified object as a root of the resource identified by the URI. If the target resource does not exist,
     * it is created. After successful completion, the target resource is loaded in the resource-set.
     * 
     * @param root
     *            the root element to control.
     * @param uri
     *            the URI of the controlled/child resource to move the element into.
     */
    protected void move(final EObject root, final URI uri) {
        controlledResource = getOrCreateChildResource(root.eResource(), uri);
        if (controlledResource != null) {
            controlledResource.getContents().add(root);
        } else {
            throw new RuntimeException(MessageFormat.format(Messages.ControlCommand_moveErrorMsg, uri));
        }
    }

    /**
     * Returns the child resource specified by its URI in which to move the controlled element.
     * 
     * @param parent
     *            the parent resource, which currently contains the element to control.
     * @param uri
     *            the URI of the controlled/child resource to move the element into.
     * @return the child resource in which the element to control can be moved, or <code>null</code> if the specified
     *         target is invalid or can not be created.
     */
    protected Resource getOrCreateChildResource(final Resource parent, final URI uri) {
        final Resource existingResource = parent.getResourceSet().getResource(uri, false);
        if (existingResource != null) {
            return isValidControlTarget(parent, existingResource) ? existingResource : null;
        } else {
            return parent.getResourceSet().createResource(uri);
        }
    }

    private boolean isValidControlTarget(final Resource parent, final Resource child) {
        return child != parent && !domain.isReadOnly(child);
    }
}

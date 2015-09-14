/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.policy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * This edit policy is composed of many edit policies.
 *
 * @author ymortier
 */
public class CompoundEditPolicy implements EditPolicy {

    /** The delegated edit policies. */
    private List<EditPolicy> delegatedEditPolicies = new LinkedList<EditPolicy>();

    /** The host. */
    private EditPart host;

    /**
     * if <code>true</code> then null command are added to the resulted compound
     * command (so the command is unexecutable).
     */
    private boolean allowNullCommand;

    /**
     * <code>true</code> if the resulted command can contain null value.
     *
     * @param allowNullCommand
     *            <code>true</code> if the resulted command can contain null
     *            value.
     */
    public void setAllowNullCommand(final boolean allowNullCommand) {
        this.allowNullCommand = allowNullCommand;
    }

    /**
     * Returns <code>true</code> if the resulted command can contain null value.
     *
     * @return <code>true</code> if the resulted command can contain null value.
     */
    public boolean isAllowNullCommand() {
        return allowNullCommand;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.gef.EditPolicy#activate()
     */
    @Override
    public void activate() {
        final Iterator<EditPolicy> iterEditPolicies = this.delegatedEditPolicies.iterator();
        while (iterEditPolicies.hasNext()) {
            iterEditPolicies.next().activate();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.gef.EditPolicy#deactivate()
     */
    @Override
    public void deactivate() {
        final Iterator<EditPolicy> iterEditPolicies = this.delegatedEditPolicies.iterator();
        while (iterEditPolicies.hasNext()) {
            iterEditPolicies.next().deactivate();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.gef.EditPolicy#eraseSourceFeedback(org.eclipse.gef.Request)
     */
    @Override
    public void eraseSourceFeedback(final Request request) {
        final Iterator<EditPolicy> iterEditPolicies = this.delegatedEditPolicies.iterator();
        while (iterEditPolicies.hasNext()) {
            iterEditPolicies.next().eraseSourceFeedback(request);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.gef.EditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
     */
    @Override
    public void eraseTargetFeedback(final Request request) {
        final Iterator<EditPolicy> iterEditPolicies = this.delegatedEditPolicies.iterator();
        while (iterEditPolicies.hasNext()) {
            iterEditPolicies.next().eraseTargetFeedback(request);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.gef.EditPolicy#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(final Request request) {
        CompoundCommand ret = null;
        final Iterator<EditPolicy> iterEditPolicies = this.delegatedEditPolicies.listIterator();
        while (iterEditPolicies.hasNext()) {
            final EditPolicy editPolicy = iterEditPolicies.next();
            final Command command = editPolicy.getCommand(request);
            if (command != null || isAllowNullCommand()) {
                if (ret == null) {
                    ret = new CompoundCommand();
                }
                ret.add(command);
            }
        }
        /*
         * If there are only null commands in the result, we should return null.
         */
        boolean containsOnlyNullCommands = true;
        if (ret != null) {
            for (final Object c : ret.getCommands()) {
                if (c != null) {
                    containsOnlyNullCommands = false;
                    break;
                }
            }
        }
        return containsOnlyNullCommands ? null : ret;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.gef.EditPolicy#getHost()
     */
    @Override
    public EditPart getHost() {
        return this.host;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.gef.EditPolicy#getTargetEditPart(org.eclipse.gef.Request)
     */
    @Override
    public EditPart getTargetEditPart(final Request request) {
        EditPart res = null;
        final Iterator<EditPolicy> iterEditPolicies = this.delegatedEditPolicies.iterator();
        while (iterEditPolicies.hasNext() && res == null) {
            final EditPolicy next = iterEditPolicies.next();
            res = next.getTargetEditPart(request);
        }
        return res;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.gef.EditPolicy#setHost(org.eclipse.gef.EditPart)
     */
    @Override
    public void setHost(final EditPart editpart) {
        this.host = editpart;
        final Iterator<EditPolicy> iterEditPolicies = this.delegatedEditPolicies.iterator();
        while (iterEditPolicies.hasNext()) {
            iterEditPolicies.next().setHost(editpart);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.gef.EditPolicy#showSourceFeedback(org.eclipse.gef.Request)
     */
    @Override
    public void showSourceFeedback(final Request request) {
        final Iterator<EditPolicy> iterEditPolicies = this.delegatedEditPolicies.iterator();
        while (iterEditPolicies.hasNext()) {
            iterEditPolicies.next().showSourceFeedback(request);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.gef.EditPolicy#showTargetFeedback(org.eclipse.gef.Request)
     */
    @Override
    public void showTargetFeedback(final Request request) {
        final Iterator<EditPolicy> iterEditPolicies = this.delegatedEditPolicies.iterator();
        while (iterEditPolicies.hasNext()) {
            iterEditPolicies.next().showTargetFeedback(request);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.gef.EditPolicy#understandsRequest(org.eclipse.gef.Request)
     */
    @Override
    public boolean understandsRequest(final Request request) {
        boolean res = false;
        final Iterator<EditPolicy> iterEditPolicies = this.delegatedEditPolicies.iterator();
        while (iterEditPolicies.hasNext() && !res) {
            res = iterEditPolicies.next().understandsRequest(request);
        }
        return res;
    }

    /**
     * Adds an edit policy.
     *
     * @param editPolicy
     *            the edit policy to add.
     */
    public void addEditPolicy(final EditPolicy editPolicy) {
        if (editPolicy == null) {
            throw new IllegalArgumentException(Messages.CompoundEditPolicy_nullEditPolicyMsg);
        }
        this.delegatedEditPolicies.add(editPolicy);
    }

    /**
     * Removes an edit policy.
     *
     * @param policy
     *            the policy to remove.
     */
    public void removeEditPolicy(final EditPolicy policy) {
        this.delegatedEditPolicies.remove(policy);
    }

    /**
     * Return all edit policies.
     *
     * @return all edit policies.
     */
    public List<EditPolicy> getEditPolicies() {
        return this.delegatedEditPolicies;
    }

}

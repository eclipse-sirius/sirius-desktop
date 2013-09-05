/*******************************************************************************
 * Copyright (c) 2007, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.layout.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider;

/**
 * A compound layout provider.
 * 
 * @author ymortier
 */
public class CompoundLayoutProvider extends AbstractLayoutProvider {

    /** the list of delegated providers. */
    private final List<AbstractLayoutEditPartProvider> delegatedProviders = new LinkedList<AbstractLayoutEditPartProvider>();

    /** The list of providers that provides for the next operation. */
    private final List<AbstractLayoutEditPartProvider> realDelegatedProviders = new LinkedList<AbstractLayoutEditPartProvider>();

    /**
     * Adds a provider.
     * 
     * @param provider
     *            the provider to add.
     */
    public void addProvider(final AbstractLayoutEditPartProvider provider) {
        this.delegatedProviders.add(provider);
        this.realDelegatedProviders.add(provider);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider#layoutEditParts(java.util.List,
     *      org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    public Command layoutEditParts(final List selectedObjects, final IAdaptable layoutHint) {
        final CompoundCommand cc = new CompoundCommand();
        final ArrayList<AbstractLayoutEditPartProvider> inverse = new ArrayList<AbstractLayoutEditPartProvider>(this.realDelegatedProviders);
        final Iterator<AbstractLayoutEditPartProvider> iterRealDelegatedProviders = inverse.listIterator();
        while (iterRealDelegatedProviders.hasNext()) {
            final AbstractLayoutEditPartProvider provider = iterRealDelegatedProviders.next();
            if (provider instanceof AbstractLayoutProvider) {
                ((AbstractLayoutProvider) provider).setViewsToChangeBoundsRequest(this.getViewsToChangeBoundsRequest());
            }
            final Command command = provider.layoutEditParts(new ArrayList(selectedObjects), layoutHint);
            if (command != null && command.canExecute()) {
                cc.add(command);
            }
            if (provider instanceof AbstractLayoutProvider) {
                this.getViewsToChangeBoundsRequest().putAll(((AbstractLayoutProvider) provider).getViewsToChangeBoundsRequest());
            }
        }
        this.getViewsToChangeBoundsRequest().clear();
        return cc;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.provider.AbstractLayoutProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
     */
    @Override
    public boolean provides(final IOperation operation) {
        boolean result = false;
        this.realDelegatedProviders.clear();
        final Iterator<AbstractLayoutEditPartProvider> iterDelegatedProviders = this.delegatedProviders.listIterator();
        while (iterDelegatedProviders.hasNext()) {
            final AbstractLayoutEditPartProvider currentProvider = iterDelegatedProviders.next();
            if (currentProvider.provides(operation)) {
                result = true;
                this.realDelegatedProviders.add(currentProvider);
            }
        }
        return result;
    }

}

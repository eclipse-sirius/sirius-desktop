/*******************************************************************************
 * Copyright (c) 2010, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.internal.metamodel.spec;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.tree.DTreeElementSynchronizer;
import org.eclipse.sirius.tree.description.StyleUpdater;
import org.eclipse.sirius.tree.description.TreeItemUpdater;
import org.eclipse.sirius.tree.description.TreeMapping;
import org.eclipse.sirius.tree.impl.DTreeItemImpl;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Implementation of DTreeItem.
 * 
 * @author nlepine
 */
public class DTreeItemSpec extends DTreeItemImpl {

    private Adapter targetListener;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.impl.DTreeItemImpl#getMapping()
     */
    @Override
    public RepresentationElementMapping getMapping() {
        return getActualMapping();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.impl.DTreeItemImpl#basicGetTreeElementMapping()
     */
    @Override
    public TreeMapping basicGetTreeElementMapping() {
        return (TreeMapping) getMapping();
    }

    /**
     * 
     * {@inheritDoc}
     * @see org.eclipse.sirius.tree.impl.DTreeItemImpl#getTreeElementMapping()
     */
    public TreeMapping getTreeElementMapping() {
        TreeMapping treeElementMapping = basicGetTreeElementMapping();
        return treeElementMapping != null && treeElementMapping.eIsProxy() ? (TreeMapping) eResolveProxy((InternalEObject) treeElementMapping) : treeElementMapping;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.impl.DTreeItemImpl#basicGetStyleUpdater()
     */
    @Override
    public StyleUpdater basicGetStyleUpdater() {
        StyleUpdater updater = null;
        if (getActualMapping() != null) {
            updater = getActualMapping();
        }
        return updater;
    }
    
    /**
     * 
     * {@inheritDoc}
     * @see org.eclipse.sirius.tree.impl.DTreeItemImpl#getStyleUpdater()
     */
    public StyleUpdater getStyleUpdater() {
        StyleUpdater styleUpdater = basicGetStyleUpdater();
        return styleUpdater != null && styleUpdater.eIsProxy() ? (StyleUpdater) eResolveProxy((InternalEObject) styleUpdater) : styleUpdater;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public TreeItemUpdater getUpdater() {
        TreeItemUpdater updater = basicGetUpdater();
        return updater != null && updater.eIsProxy() ? (TreeItemUpdater) eResolveProxy((InternalEObject) updater) : updater;
    }
    
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public TreeItemUpdater basicGetUpdater() {
        TreeItemUpdater updater = null;
        if (getActualMapping() != null) {
            updater = getActualMapping();
        }
        return updater;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void activate(final DTreeElementSynchronizer sync) {
        for (final EObject semantic : getSemanticElements()) {
            semantic.eAdapters().add(getOrCreateListener(sync));
        }
        if (getTarget() != null && !getSemanticElements().contains(getTarget())) {
            getTarget().eAdapters().add(getOrCreateListener(sync));
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void deactivate() {
        if (targetListener != null) {
            for (final EObject semantic : getSemanticElements()) {
                semantic.eAdapters().remove(targetListener);
            }
            if (getTarget() != null && !getSemanticElements().contains(getTarget())) {
                getTarget().eAdapters().remove(targetListener);
            }
        }
        targetListener = null;
    }

    private Adapter getOrCreateListener(final DTreeElementSynchronizer sync) {
        if (targetListener == null) {
            targetListener = new AdapterImpl() {
                /**
                 * 
                 * {@inheritDoc}
                 */
                @Override
                public void notifyChanged(final Notification msg) {
                    if (msg.getEventType() != Notification.REMOVING_ADAPTER
                            && !Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false, null)) {
                        sync.refresh(DTreeItemSpec.this);
                    }
                }
            };
        }
        return targetListener;
    }
}

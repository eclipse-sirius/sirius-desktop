/*******************************************************************************
 * Copyright (c) 2007, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.internal.metamodel.spec;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.sirius.business.api.preferences.DesignerPreferencesKeys;
import org.eclipse.sirius.table.metamodel.table.DTableElementSynchronizer;
import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.table.metamodel.table.impl.DCellImpl;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Specialization of DCell.
 * 
 * @author cbrun
 */
public class DCellSpec extends DCellImpl {

    private Adapter targetListener;

    /**
     * {@inheritDoc}
     */
    @Override
    public void activate(DTableElementSynchronizer sync) {
        for (final EObject semantic : getSemanticElements()) {
            semantic.eAdapters().add(getOrCreateListener(sync));
        }
        if (getTarget() != null && !getSemanticElements().contains(getTarget())) {
            getTarget().eAdapters().add(getOrCreateListener(sync));
        }
    }

    private Adapter getOrCreateListener(final DTableElementSynchronizer sync) {
        if (targetListener == null) {
            targetListener = new AdapterImpl() {
                /**
                 * 
                 * {@inheritDoc}
                 */
                @Override
                public void notifyChanged(final Notification msg) {
                    if (msg.getEventType() != Notification.REMOVING_ADAPTER
                            && !Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, DesignerPreferencesKeys.PREF_AUTO_REFRESH.name(), false, null)) {
                        // ISSUE on tree/table : when having local tree/table
                        // rep
                        // with shared semantic, receiving a remote semantic
                        // change has the effect to calls this in manual/auto
                        // refresh then the CDOTransactionChangeRecorder is
                        // notifier => IllegalStateException cannot modify
                        // resource without transaction
                        // TODO : we could do like diagram using task
                        sync.refresh(DCellSpec.this);
                    }
                }
            };
        }
        return targetListener;
    }

    /**
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

    /**
     * {@inheritDoc}
     */
    @Override
    public CellUpdater basicGetUpdater() {
        CellUpdater updater = null;
        if (getColumn() != null && getColumn().getOriginMapping() instanceof CellUpdater) {
            updater = (CellUpdater) getColumn().getOriginMapping();
        }
        if (getIntersectionMapping() != null) {
            updater = getIntersectionMapping();
        }
        return updater;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RepresentationElementMapping getMapping() {
        RepresentationElementMapping result = null;
        CellUpdater cellUpdater = getUpdater();
        if (cellUpdater instanceof FeatureColumnMapping) {
            result = (FeatureColumnMapping) cellUpdater;
        } else if (cellUpdater instanceof IntersectionMapping) {
            result = (IntersectionMapping) cellUpdater;
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.impl.DCellImpl#getTableElementMapping()
     */
    @Override
    public TableMapping getTableElementMapping() {
        TableMapping tableElementMapping = basicGetTableElementMapping();
        return tableElementMapping != null && tableElementMapping.eIsProxy() ? (TableMapping) eResolveProxy((InternalEObject) tableElementMapping) : tableElementMapping;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableMapping basicGetTableElementMapping() {
        return (TableMapping) getMapping();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.impl.DCellImpl#getUpdater()
     */
    public CellUpdater getUpdater() {
        CellUpdater updater = basicGetUpdater();
        return updater != null && updater.eIsProxy() ? (CellUpdater) eResolveProxy((InternalEObject) updater) : updater;
    }
}

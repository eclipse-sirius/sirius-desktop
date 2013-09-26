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

import java.util.Collection;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.business.api.preferences.DesignerPreferencesKeys;
import org.eclipse.sirius.table.business.internal.metamodel.operations.DColumnOperations;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DTableElementSynchronizer;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Specialization of DTargetColumn.
 * 
 * @author cbrun
 */
public class DTargetColumnSpec extends DTargetColumnImpl {

    private Adapter targetListener;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public EList<DCell> getOrderedCells() {
        final Collection<DCell> result = DColumnOperations.getOrderedCells(this);
        return new EcoreEList.UnmodifiableEList<DCell>(eInternalContainer(), TablePackage.eINSTANCE.getDColumn_OrderedCells(), result.size(), result.toArray());
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void activate(final DTableElementSynchronizer sync) {
        if (getTarget() != null) {
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
                        sync.refresh(DTargetColumnSpec.this);
                    }
                }
            };
        }
        return targetListener;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void deactivate() {
        if (getTarget() != null) {
            getTarget().eAdapters().remove(targetListener);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl#getMapping()
     */
    @Override
    public RepresentationElementMapping getMapping() {
        return getOriginMapping();
    }
    
    /**
     * 
     * {@inheritDoc}
     * @see org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl#getTableElementMapping()
     */
    public TableMapping getTableElementMapping() {
        TableMapping tableElementMapping = basicGetTableElementMapping();
        return tableElementMapping != null && tableElementMapping.eIsProxy() ? (TableMapping) eResolveProxy((InternalEObject) tableElementMapping) : tableElementMapping;
    }
    
    /**
     * 
     * {@inheritDoc}
     * @see org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl#basicGetTableElementMapping()
     */
    public TableMapping basicGetTableElementMapping() {
        return (TableMapping) getMapping();
    }
}

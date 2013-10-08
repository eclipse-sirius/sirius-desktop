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

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreEList;

import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DTableElementSynchronizer;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.table.metamodel.table.impl.DLineImpl;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

//TOODOCBR comment this !
/**
 * .
 * 
 * @author cbrun
 */
public class DLineSpec extends DLineImpl {

    private Adapter targetListener;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void activate(final DTableElementSynchronizer sync) {
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
                            && !Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false, null)) {
                        sync.refresh(DLineSpec.this);
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
    public EList<DCell> getOrderedCells() {
        final Map<DColumn, Integer> columnIndices = Maps.newHashMap();
        int i = 0;
        for (DColumn col : TableHelper.getTable(this).getColumns()) {
            columnIndices.put(col, i++);
        }
        Ordering<DCell> ordering = Ordering.from(new Comparator<DCell>() {
            public int compare(DCell a, DCell b) {
                int result = 0;
                DColumn columnA = a.getColumn();
                DColumn columnB = b.getColumn();
                // column.eContainer can be null in case of deletion in progress
                if (columnA == null || columnA.eContainer() == null) {
                    result = -1;
                } else if (columnB == null || columnB.eContainer() == null) {
                    result = 1;
                } else {
                    Integer aIndex = columnIndices.get(columnA);
                    Integer bIndex = columnIndices.get(columnB);
                    if (aIndex == null || bIndex == null) {
                        throw new RuntimeException("Should not happen.");
                    }
                    return aIndex - bIndex;
                }
                return result;
            }
        });
        DCell[] data = new DCell[getCells().size()];
        getCells().toArray(data);
        Arrays.sort(data, ordering);
        return new EcoreEList.UnmodifiableEList<DCell>(eInternalContainer(), TablePackage.eINSTANCE.getDLine_OrderedCells(), data.length, data);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.impl.DLineImpl#getMapping()
     */
    @Override
    public RepresentationElementMapping getMapping() {
        return getOriginMapping();
    }
    
    /**
     * 
     * {@inheritDoc}
     * @see org.eclipse.sirius.table.metamodel.table.impl.DLineImpl#basicGetTableElementMapping()
     */
    public TableMapping basicGetTableElementMapping() {
        return (TableMapping) getMapping();
    }
    
    /**
     * 
     * {@inheritDoc}
     * @see org.eclipse.sirius.table.metamodel.table.impl.DLineImpl#getTableElementMapping()
     */
    public TableMapping getTableElementMapping() {
        TableMapping tableElementMapping = basicGetTableElementMapping();
        return tableElementMapping != null && tableElementMapping.eIsProxy() ? (TableMapping) eResolveProxy((InternalEObject) tableElementMapping) : tableElementMapping;
    }
}

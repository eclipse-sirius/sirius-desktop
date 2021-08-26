/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.table.model.business.internal.spec;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.table.metamodel.table.impl.DCellImpl;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Specialization of DCell.
 * 
 * @author cbrun
 */
public class DCellSpec extends DCellImpl {

    /**
     * {@inheritDoc}
     */
    @Override
    public CellUpdater basicGetUpdater() {
        CellUpdater updater = null;
        DColumn dColumn = getColumn();
        if (dColumn != null) {
            ColumnMapping originMapping = dColumn.getOriginMapping();
            if (originMapping instanceof CellUpdater) {
                updater = (CellUpdater) originMapping;
            }
        }
        IntersectionMapping intersectMapping = getIntersectionMapping();
        if (intersectMapping != null) {
            updater = intersectMapping;
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
    @Override
    public CellUpdater getUpdater() {
        CellUpdater updater = basicGetUpdater();
        return updater != null && updater.eIsProxy() ? (CellUpdater) eResolveProxy((InternalEObject) updater) : updater;
    }
}

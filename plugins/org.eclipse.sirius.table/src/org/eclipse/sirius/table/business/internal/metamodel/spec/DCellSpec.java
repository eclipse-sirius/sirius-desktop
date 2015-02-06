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
package org.eclipse.sirius.table.business.internal.metamodel.spec;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.sirius.table.metamodel.table.DTableElementSynchronizer;
import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;
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
    public void activate(DTableElementSynchronizer sync) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deactivate() {
        // do nothing
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

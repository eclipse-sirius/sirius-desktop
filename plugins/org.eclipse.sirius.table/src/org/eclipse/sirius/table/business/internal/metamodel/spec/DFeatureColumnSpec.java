/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.table.business.internal.metamodel.operations.DColumnOperations;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.table.metamodel.table.impl.DFeatureColumnImpl;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Specialization of DFeatureColumn.
 * 
 * @author cbrun
 */
public class DFeatureColumnSpec extends DFeatureColumnImpl {
    /**
     * {@inheritDoc}
     */
    @Override
    public EList<DCell> getOrderedCells() {
        Collection<DCell> result = DColumnOperations.getOrderedCells(this);
        return new EcoreEList.UnmodifiableEList<DCell>(eInternalContainer(), TablePackage.eINSTANCE.getDColumn_OrderedCells(), result.size(), result.toArray());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RepresentationElementMapping getMapping() {
        return getOriginMapping();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.impl.DTableElementImpl#getTableElementMapping()
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
}

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

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.table.metamodel.table.impl.DLineImpl;
import org.eclipse.sirius.table.tools.internal.Messages;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

/**
 * Specialization of DLine.
 *
 * @author cbrun
 */
public class DLineSpec extends DLineImpl {

    @Override
    public EList<DCell> getOrderedCells() {
        Ordering<DCell> ordering = Ordering.from(new Comparator<DCell>() {
            Map<DColumn, Integer> columnIndices;

            @Override
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
                    if (columnIndices == null) {
                        columnIndices = Maps.newHashMap();
                        int i = 0;
                        for (DColumn col : columnA.getTable().getColumns()) {
                            columnIndices.put(col, i++);
                        }
                    }
                    Integer aIndex = columnIndices.get(columnA);
                    Integer bIndex = columnIndices.get(columnB);
                    if (aIndex == null || bIndex == null) {
                        throw new RuntimeException(Messages.Table_UnexpectedExceptionMessage);
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

    @Override
    public RepresentationElementMapping getMapping() {
        return getOriginMapping();
    }

    @Override
    public TableMapping basicGetTableElementMapping() {
        return (TableMapping) getMapping();
    }

    @Override
    public TableMapping getTableElementMapping() {
        TableMapping tableElementMapping = basicGetTableElementMapping();
        return tableElementMapping != null && tableElementMapping.eIsProxy() ? (TableMapping) eResolveProxy((InternalEObject) tableElementMapping) : tableElementMapping;
    }
}

/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.internal.metamodel.spec;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreEList;

import com.google.common.collect.Iterables;

import org.eclipse.sirius.ext.emf.AllContents;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.internal.query.DModelElementInternalQuery;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTableElementSynchronizer;
import org.eclipse.sirius.table.metamodel.table.DTableElementUpdater;
import org.eclipse.sirius.table.metamodel.table.impl.DTableImpl;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DAnnotation;

/**
 * Specialization of DTable.
 * 
 * @author cbrun
 */
public class DTableSpec extends DTableImpl {
    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh() {
        DialectManager.INSTANCE.refresh(this, new NullProgressMonitor());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activate(DTableElementSynchronizer sync) {
        for (DTableElementUpdater updater : Iterables.filter(AllContents.of(this), DTableElementUpdater.class)) {
            updater.activate(sync);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deactivate() {
        for (DTableElementUpdater updater : Iterables.filter(AllContents.of(this), DTableElementUpdater.class)) {
            updater.deactivate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EList<DRepresentationElement> getOwnedRepresentationElements() {
        EList<DRepresentationElement> result = new BasicEList<DRepresentationElement>(getColumns().size() + getLines().size());
        result.addAll(getColumns());
        result.addAll(getLines());
        return new EcoreEList.UnmodifiableEList<DRepresentationElement>(eInternalContainer(), ViewpointPackage.eINSTANCE.getDRepresentation_OwnedRepresentationElements(), result.size(),
                result.toArray());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EList<DRepresentationElement> getRepresentationElements() {
        EList<DRepresentationElement> result = new BasicEList<DRepresentationElement>();
        result.addAll(getColumns());
        for (DLine line : getLines()) {
            result.addAll(getRepresentationElements(line));
        }
        return new EcoreEList.UnmodifiableEList<DRepresentationElement>(eInternalContainer(), ViewpointPackage.eINSTANCE.getDRepresentation_RepresentationElements(), result.size(), result.toArray());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DAnnotation getDAnnotation(String source) {
        return new DModelElementInternalQuery(this).getDAnnotation(source);
    }

    /**
     * Return the subLines and the cells of this line.
     * 
     * @param line
     *            The line
     * @return List of representations elements
     */
    private EList<DRepresentationElement> getRepresentationElements(DLine line) {
        EList<DRepresentationElement> result = new BasicEList<DRepresentationElement>();
        result.add(line);
        result.addAll(line.getCells());
        for (DLine subLine : line.getLines()) {
            result.addAll(getRepresentationElements(subLine));
        }
        return result;
    }
}

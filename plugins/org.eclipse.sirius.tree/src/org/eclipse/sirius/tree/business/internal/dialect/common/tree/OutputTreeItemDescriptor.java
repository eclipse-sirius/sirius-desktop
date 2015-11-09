/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.internal.dialect.common.tree;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.synchronizer.OutputDescriptor;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.description.TreeItemMapping;

/**
 * A {@link OutputDescriptor}.
 * 
 * @author cbrun
 */
class OutputTreeItemDescriptor implements OutputDescriptor {

    private TreeItemMapping mapping;

    private EObject from;

    private int position;

    private TreeMappingProvider provider;

    private DTreeItemContainer container;

    /**
     * Default constructor.
     * 
     * @param container
     *            a {@link DTreeItemContainer}
     * @param from
     *            an {@link EObject}
     * @param nm
     *            a {@link TreeItemMapping}
     * @param position
     *            the position
     * @param provider
     *            a {@link TreeMappingProvider}
     */
    OutputTreeItemDescriptor(DTreeItemContainer container, EObject from, TreeItemMapping nm, int position, TreeMappingProvider provider) {
        this.from = from;
        this.mapping = nm;
        this.position = position;
        this.provider = provider;
        this.container = container;

    }

    public DTreeItemContainer getViewContainer() {
        return container;
    }

    @Override
    public int getIndex() {
        return position;
    }

    @Override
    public EObject getSourceElement() {
        return from;
    }

    @Override
    public RTreeItemMapping getMapping() {
        return provider.getOrCreate(mapping);
    }

}

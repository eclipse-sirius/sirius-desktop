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
import org.eclipse.sirius.synchronizer.Mapping;
import org.eclipse.sirius.synchronizer.OutputDescriptor;
import org.eclipse.sirius.tree.description.TreeDescription;

/**
 * A {@link OutputDescriptor}.
 * 
 * @author cbrun
 */
class OutputDTreeDescriptor implements OutputDescriptor {

    private EObject from;

    private TreeDescription mapping;

    private TreeMappingProvider provider;

    /**
     * Default constructor.
     * 
     * @param from
     *            an {@link EObject}
     * @param nm
     *            a {@link TreeDescription}
     * @param provider
     *            a {@link TreeMappingProvider}
     */
    OutputDTreeDescriptor(EObject from, TreeDescription nm, TreeMappingProvider provider) {
        this.from = from;
        this.mapping = nm;
        this.provider = provider;

    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public EObject getSourceElement() {
        return from;
    }

    @Override
    public Mapping getMapping() {
        return provider.getOrCreate(mapping);
    }

    public boolean isSame(OutputDescriptor other) {

        return false;
    }

}

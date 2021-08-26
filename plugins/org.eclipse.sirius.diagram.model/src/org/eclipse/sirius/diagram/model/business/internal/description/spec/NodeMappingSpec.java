/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.model.business.internal.description.spec;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.impl.NodeMappingImpl;
import org.eclipse.sirius.diagram.model.business.internal.description.extensions.INodeMappingExt;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * The implementation of NodeMapping. The actual code should be placed in
 * {@link org.eclipse.sirius.diagram.business.internal.metamodel.helper.NodeMappingHelper} so that it can be reused
 * without copy/paste by specialized node mapping types.
 * 
 * @author ymortier, pcdavid
 */
public class NodeMappingSpec extends NodeMappingImpl implements INodeMappingExt {

    private final Map<EObject, EList<DSemanticDecorator>> viewNodesDone = new HashMap<EObject, EList<DSemanticDecorator>>();

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<EObject, EList<DSemanticDecorator>> getViewNodesDone() {
        return viewNodesDone;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EList<DDiagramElement> findDNodeFromEObject(final EObject object) {
        throw new UnsupportedOperationException();
    }

    /*
     * Behavior inherited from DiagramElementMapping
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new StringBuffer(getClass().getName()).append(" ").append(getName()).toString(); //$NON-NLS-1$
    }
}

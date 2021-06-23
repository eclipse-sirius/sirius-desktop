/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.util.EObjectCouple;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.extensions.INodeMappingExt;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.NodeMappingHelper;
import org.eclipse.sirius.diagram.sequence.description.impl.ExecutionMappingImpl;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Implementation of <code>ExecutionMapping</code>.
 * 
 * @author pcdavid
 */
public class ExecutionMappingSpec extends ExecutionMappingImpl implements INodeMappingExt {

    private final Map<EObject, EList<DSemanticDecorator>> viewNodesDone = new HashMap<EObject, EList<DSemanticDecorator>>();

    private final Map<EObjectCouple, EList<EObject>> candidatesCache = new WeakHashMap<EObjectCouple, EList<EObject>>();

    @Override
    public Map<EObject, EList<DSemanticDecorator>> getViewNodesDone() {
        return viewNodesDone;
    }

    @Override
    public Map<EObjectCouple, EList<EObject>> getCandidatesCache() {
        return candidatesCache;
    }


    @Override
    public EList<DDiagramElement> findDNodeFromEObject(final EObject object) {
        return NodeMappingHelper.findDNodeFromEObject(this, object);
    }


    @Override
    public String toString() {
        return new StringBuffer(getClass().getName()).append(" ").append(getName()).toString(); //$NON-NLS-1$
    }


}

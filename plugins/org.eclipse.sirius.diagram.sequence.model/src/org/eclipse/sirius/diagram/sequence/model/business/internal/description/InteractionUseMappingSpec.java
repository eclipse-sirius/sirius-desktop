/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.model.business.internal.description;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.model.business.internal.description.extensions.IContainerMappingExt;
import org.eclipse.sirius.diagram.model.business.internal.helper.MappingExtHelper;
import org.eclipse.sirius.diagram.sequence.description.impl.InteractionUseMappingImpl;

/**
 * Implementation of InteractionUseMapping.
 * 
 * @author pcdavid
 */
public class InteractionUseMappingSpec extends InteractionUseMappingImpl implements IContainerMappingExt {

    private final Map<EObject, EList<DDiagramElement>> viewContainerDone = new HashMap<>();

    @Override
    public Map<EObject, EList<DDiagramElement>> getViewContainerDone() {
        return viewContainerDone;
    }

    @Override
    public EList<DDiagramElement> findDNodeFromEObject(final EObject object) {
        return MappingExtHelper.findDNodeFromEObject(this, object);
    }

    @Override
    public String toString() {
        return new StringBuffer(getClass().getName()).append(" ").append(getName()).toString(); //$NON-NLS-1$
    }
}

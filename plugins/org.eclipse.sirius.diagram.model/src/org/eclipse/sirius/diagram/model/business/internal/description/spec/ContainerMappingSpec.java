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
import org.eclipse.sirius.diagram.description.impl.ContainerMappingImpl;
import org.eclipse.sirius.diagram.model.business.internal.description.extensions.IContainerMappingExt;

/**
 * The implementation of ContainerMapping.
 * 
 * @author cbrun, mchauvin, ymortier, pcdavid
 */
public class ContainerMappingSpec extends ContainerMappingImpl implements IContainerMappingExt {

    private final Map<EObject, EList<DDiagramElement>> viewContainerDone = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<EObject, EList<DDiagramElement>> getViewContainerDone() {
        return viewContainerDone;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new StringBuffer(getClass().getName()).append(" ").append(getName()).toString(); //$NON-NLS-1$
    }

}

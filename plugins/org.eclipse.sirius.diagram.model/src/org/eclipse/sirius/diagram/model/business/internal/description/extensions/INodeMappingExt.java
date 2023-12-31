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
package org.eclipse.sirius.diagram.model.business.internal.description.extensions;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.NodeMapping;

/**
 * Extension to the ContainerMapping interface used by the actual implementation.
 * 
 * @author pcdavid
 */
public interface INodeMappingExt extends NodeMapping {
    /**
     * Returns the 'viewNodesDone' map for this mapping.
     * 
     * @return the 'viewNodesDone' map for this mapping.
     */
    Map<EObject, EList<DDiagramElement>> getViewNodesDone();

}

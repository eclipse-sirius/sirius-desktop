/*******************************************************************************
 * Copyright (c) 2007, 20018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.description.spec;

import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContentHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.DiagramDescriptionHelper;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * Implementation of DiagramDescriptionImpl.java.
 * 
 * @author cbrun
 */
public class DiagramDescriptionSpec extends DiagramDescriptionImpl {
    /**
     * {@inheritDoc}
     */
    @Override
    public DSemanticDiagram createDiagram() {
        // Create a plain DSemanticDiagram by default.
        return DiagramFactory.eINSTANCE.createDSemanticDiagram();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramDescriptionImpl#getAllTools()
     */
    @Override
    public EList<AbstractToolDescription> getAllTools() {
        final Set<AbstractToolDescription> result = DiagramDescriptionHelper.getAllTools(this);
        return new EcoreEList.UnmodifiableEList<AbstractToolDescription>(eInternalContainer(), DescriptionPackage.eINSTANCE.getDiagramDescription_AllTools(), result.size(), result.toArray());
    }



    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramDescriptionImpl#getAllEdgeMappings()
     */
    @Override
    public EList<EdgeMapping> getAllEdgeMappings() {
        return ContentHelper.getAllEdgeMappings(this, false);
    }

}

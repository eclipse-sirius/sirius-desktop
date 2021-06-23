/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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

import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContentHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.DiagramDescriptionHelper;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.sequence.SequenceFactory;
import org.eclipse.sirius.diagram.sequence.description.impl.SequenceDiagramDescriptionImpl;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * Specialized version of <code>SequenceDiagramDescriptionImpl</code>.
 * 
 * @author pcdavid
 */
public class SequenceDiagramDescriptionSpec extends SequenceDiagramDescriptionImpl {
    /**
     * {@inheritDoc}
     * 
     * SequenceDiagramDescription produce <code>SequenceDDiagram</code>s at
     * runtime.
     */
    @Override
    public DSemanticDiagram createDiagram() {
        return SequenceFactory.eINSTANCE.createSequenceDDiagram();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramDescriptionImpl#getAllTools()
     */
    @Override
    public EList<AbstractToolDescription> getAllTools() {
        final Set<AbstractToolDescription> result = DiagramDescriptionHelper.getAllTools(this);
        return new EcoreEList.UnmodifiableEList<AbstractToolDescription>(eInternalContainer(), org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getDiagramDescription_AllTools(),
                result.size(), result.toArray());
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

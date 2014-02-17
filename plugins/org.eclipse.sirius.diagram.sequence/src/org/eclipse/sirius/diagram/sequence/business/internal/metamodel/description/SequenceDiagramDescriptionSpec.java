/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description;

import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContentHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.DiagramDescriptionHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
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
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramDescriptionImpl#getAllActivatedTools()
     */
    @Override
    public EList<AbstractToolDescription> getAllActivatedTools() {
        return new BasicEList<AbstractToolDescription>();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramDescriptionImpl#getAllLayers()
     */
    @Override
    public EList<Layer> getAllLayers() {
        return LayerHelper.getAllLayers(this);
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramDescriptionImpl#getAllContainerMappings()
     */
    @Override
    public EList<ContainerMapping> getAllContainerMappings() {
        return ContentHelper.getAllContainerMappings(this, false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramDescriptionImpl#getAllNodeMappings()
     */
    @Override
    public EList<NodeMapping> getAllNodeMappings() {
        return ContentHelper.getAllNodeMappings(this, false);
    }
}

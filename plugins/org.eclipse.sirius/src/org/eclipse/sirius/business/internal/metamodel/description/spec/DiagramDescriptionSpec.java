/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.metamodel.description.spec;

import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreEList;

import org.eclipse.sirius.DSemanticDiagram;
import org.eclipse.sirius.SiriusFactory;
import org.eclipse.sirius.business.internal.metamodel.helper.ContentHelper;
import org.eclipse.sirius.business.internal.metamodel.helper.DiagramDescriptionHelper;
import org.eclipse.sirius.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.description.ContainerMapping;
import org.eclipse.sirius.description.DescriptionPackage;
import org.eclipse.sirius.description.EdgeMapping;
import org.eclipse.sirius.description.Layer;
import org.eclipse.sirius.description.NodeMapping;
import org.eclipse.sirius.description.impl.DiagramDescriptionImpl;
import org.eclipse.sirius.description.tool.AbstractToolDescription;

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
        return SiriusFactory.eINSTANCE.createDSemanticDiagram();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.description.impl.DiagramDescriptionImpl#getAllTools()
     */
    @Override
    public EList<AbstractToolDescription> getAllTools() {
        final Set<AbstractToolDescription> result = DiagramDescriptionHelper.getAllTools(this);
        return new EcoreEList.UnmodifiableEList<AbstractToolDescription>(eInternalContainer(), DescriptionPackage.eINSTANCE.getDiagramDescription_AllTools(), result.size(), result.toArray());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.description.impl.DiagramDescriptionImpl#getAllActivatedTools()
     */
    @Override
    public EList<AbstractToolDescription> getAllActivatedTools() {
        // TODOMCH implement this
        return new BasicEList<AbstractToolDescription>();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.description.impl.DiagramDescriptionImpl#getAllLayers()
     */
    @Override
    public EList<Layer> getAllLayers() {
        return LayerHelper.getAllLayers(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.description.impl.DiagramDescriptionImpl#getAllEdgeMappings()
     */
    @Override
    public EList<EdgeMapping> getAllEdgeMappings() {
        return ContentHelper.getAllEdgeMappings(this, false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.description.impl.DiagramDescriptionImpl#getAllActivatedEdgeMappings()
     */
    @Override
    public EList<EdgeMapping> getAllActivatedEdgeMappings() {
        return ContentHelper.getAllActivatedEdgeMappings(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.description.impl.DiagramDescriptionImpl#getAllContainerMappings()
     */
    @Override
    public EList<ContainerMapping> getAllContainerMappings() {
        return ContentHelper.getAllContainerMappings(this, false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.description.impl.DiagramDescriptionImpl#getAllNodeMappings()
     */
    @Override
    public EList<NodeMapping> getAllNodeMappings() {
        return ContentHelper.getAllNodeMappings(this, false);
    }

}

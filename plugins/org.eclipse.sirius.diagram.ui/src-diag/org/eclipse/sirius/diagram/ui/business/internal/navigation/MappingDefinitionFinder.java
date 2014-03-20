/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.navigation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.util.DiagramSwitch;

/**
 * Class responsible for retrieving a definition from any EObject instance.
 * 
 * @author cbrun
 * 
 */
public class MappingDefinitionFinder extends DiagramSwitch<EObject> {
    /**
     * return the element definition.
     * 
     * @param selection
     *            any element.
     * @return the element definition.
     */
    public EObject getDefinition(final EObject selection) {
        return doSwitch(selection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseDDiagram(final DDiagram object) {
        return object.getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseDDiagramElement(final DDiagramElement object) {
        return object.getDiagramElementMapping();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseDNode(final DNode object) {
        return object.getActualMapping();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseDNodeContainer(final DNodeContainer object) {
        return object.getActualMapping();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseDEdge(final DEdge object) {
        return object.getActualMapping();
    }
}

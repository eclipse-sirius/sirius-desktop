/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.services.diagram.internal.converter;

import java.util.Optional;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.services.diagram.api.entities.AbstractSiriusDiagramElement;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramLabel;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramListElementNode;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramRGBColor;

/**
 * The DNodeListElement converter.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramDNodeListElementConverter implements ISiriusDiagramElementConverter {

    /**
     * The DNodeListElement.
     */
    private DNodeListElement dNodeListElement;

    /**
     * The constructor.
     *
     * @param dNodeListElement
     *            The DNodeListElement
     */
    public SiriusDiagramDNodeListElementConverter(DNodeListElement dNodeListElement) {
        this.dNodeListElement = dNodeListElement;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.services.diagram.internal.converter.ISiriusDiagramElementConverter#convert()
     */
    @Override
    public Optional<AbstractSiriusDiagramElement> convert() {
        String identifier = EcoreUtil.getURI(this.dNodeListElement).toString();
        String semanticElementIdentifier = EcoreUtil.getURI(this.dNodeListElement.getTarget()).toString();
        SiriusDiagramListElementNode node = new SiriusDiagramListElementNode(identifier, semanticElementIdentifier);
        node.getChildren().add(new SiriusDiagramLabel(identifier + "__label", this.dNodeListElement.getName(), new SiriusDiagramRGBColor(0, 0, 0))); //$NON-NLS-1$
        return Optional.of(node);
    }

}

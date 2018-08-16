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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.services.diagram.api.entities.AbstractSiriusDiagramElement;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramImageNode;
import org.eclipse.sirius.services.diagram.internal.SiriusDiagramPlugin;
import org.eclipse.sirius.viewpoint.Style;

/**
 * The DNode converter.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramDNodeConverter implements ISiriusDiagramElementConverter {

    /**
     * The DNode.
     */
    private DNode dNode;

    /**
     * The constructor.
     *
     * @param dNode
     *            The DNode
     */
    public SiriusDiagramDNodeConverter(DNode dNode) {
        this.dNode = dNode;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.services.diagram.internal.converter.ISiriusDiagramElementConverter#convert()
     */
    @Override
    public Optional<AbstractSiriusDiagramElement> convert() {
        // @formatter:off
        List<AbstractSiriusDiagramElement> borderedNodes = this.dNode.getOwnedBorderedNodes().stream()
                .map(new SiriusDiagramElementSwitch()::doSwitch)
                .map(ISiriusDiagramElementConverter::convert)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        // @formatter:on

        Style style = this.dNode.getStyle();
        if (style instanceof WorkspaceImage) {
            WorkspaceImage workspaceImage = (WorkspaceImage) style;

            String identifier = EcoreUtil.getURI(this.dNode).toString();
            String semanticElementIdentifier = EcoreUtil.getURI(this.dNode.getTarget()).toString();

            String imagePath = SiriusDiagramPlugin.getPlugin().getImagePathProvider().flatMap(provider -> provider.getStaticImagePath(workspaceImage)).orElse(""); //$NON-NLS-1$

            SiriusDiagramImageNode node = new SiriusDiagramImageNode(identifier, semanticElementIdentifier, imagePath);
            node.getChildren().addAll(borderedNodes);
            return Optional.of(node);
        }
        return Optional.empty();
    }

}

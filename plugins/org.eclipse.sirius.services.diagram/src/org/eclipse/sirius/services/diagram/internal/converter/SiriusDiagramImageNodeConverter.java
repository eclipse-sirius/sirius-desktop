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

import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.services.diagram.api.entities.AbstractSiriusDiagramElement;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramImageNode;
import org.eclipse.sirius.services.diagram.internal.SiriusDiagramPlugin;

/**
 * The converter used for DNodes with a WorkspaceImage style.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramImageNodeConverter extends AbstractSiriusDiagramNodeConverter {

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
    public SiriusDiagramImageNodeConverter(DNode dNode) {
        this.dNode = dNode;
    }

    @Override
    protected AbstractDNode getDNode() {
        return this.dNode;
    }

    @Override
    public Optional<AbstractSiriusDiagramElement> convert() {
        // @formatter:off
        Optional<WorkspaceImage> optionalStyle = Optional.of(this.dNode.getStyle())
                .filter(WorkspaceImage.class::isInstance)
                .map(WorkspaceImage.class::cast);

        return optionalStyle.map(style -> {
            String identifier = this.getIdentifier();

            return SiriusDiagramImageNode.newImageNode(identifier, this.getSemanticElementIdentifier())
                    .label(this.getLabel(identifier, style))
                    .imagePath(this.getImagePath(style))
                    .ports(this.getPorts())
                    .build();
        });
        // @formatter:on
    }

    /**
     * Returns the path of the image.
     *
     * @param style
     *            The style
     * @return The path of the image
     */
    private String getImagePath(WorkspaceImage style) {
        // @formatter:off
        return SiriusDiagramPlugin.getPlugin().getImagePathProvider()
                    .flatMap(provider -> provider.getStaticImagePath(style))
                    .orElse(null);
        // @formatter:on
    }

}

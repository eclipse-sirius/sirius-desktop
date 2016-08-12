/*******************************************************************************
 * Copyright (c) 2009, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.format.semantic;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.ui.tools.internal.format.NodeFormatDataKey;

/**
 * Kind of key use to store the format data corresponding to a semantic element
 * represented by a {@link org.eclipse.sirius.viewpoint.AbstractDNode} or a
 * {@link org.eclipse.sirius.diagram.DDiagram}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class SemanticNodeFormatDataKey extends AbstractSemanticFormatDataKey implements NodeFormatDataKey {

    /**
     * Constructor.
     * 
     * @param uriFragment
     *            String to use.
     */
    public SemanticNodeFormatDataKey(final String uriFragment) {
        super(uriFragment);
    }

    /**
     * Default constructor.
     * 
     * @param semanticElement
     *            The element to build the key
     */
    public SemanticNodeFormatDataKey(final EObject semanticElement) {
        super(semanticElement);
    }
}

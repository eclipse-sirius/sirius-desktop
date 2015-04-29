/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.api.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.sirius.diagram.DDiagramElementContainer;

/**
 * A custom ItemProvider to add the label of DNodeContainer and DNodeList. This
 * ItemProvider "simulates" a new child for those containers.
 * 
 * @author <a href="mailto:maxime.porhel@obeo.fr@obeo.fr">Maxime Porhel</a>
 * 
 */
public class DDiagramElementContainerLabelItemProvider extends AbstractDDiagramElementLabelItemProvider {

    /**
     * Default constructor.
     * 
     * @param adapterFactory
     *            The factory is used as a key so that we always know which
     *            factory created this adapter.
     * @param parentNode
     *            The parent of the label
     */
    public DDiagramElementContainerLabelItemProvider(AdapterFactory adapterFactory, DDiagramElementContainer parentNode) {
        super(adapterFactory, parentNode);
    }
}

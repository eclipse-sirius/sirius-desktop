/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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

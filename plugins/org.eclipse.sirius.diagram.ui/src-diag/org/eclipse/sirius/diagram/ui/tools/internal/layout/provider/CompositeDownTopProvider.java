/*******************************************************************************
 * Copyright (c) 2007, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.layout.provider;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.AbstractLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.CompoundLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.LayoutProvider;

/**
 * {@link org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutNodeProvider}
 * with a Down/Top organization.
 * 
 * @author ymortier
 */
public class CompositeDownTopProvider implements LayoutProvider {

    /** The delegated GMF provider. */
    private AbstractLayoutEditPartProvider layoutNodeProvider;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.provider.LayoutProvider#getLayoutNodeProvider(org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart)
     */
    @Override
    public AbstractLayoutEditPartProvider getLayoutNodeProvider(final IGraphicalEditPart container) {
        if (this.layoutNodeProvider == null) {
            final CompoundLayoutProvider clp = new CompoundLayoutProvider();
            final CompositeDownTopLayoutProvider cdtp = new CompositeDownTopLayoutProvider();

            clp.addProvider(cdtp);
            clp.addProvider(new PinnedElementsLayoutProvider(cdtp));
            if (ENABLE_BORDERED_NODES_ARRANGE_ALL) {
                // ArrangeSelectionLayoutProvider wrap all providers to manage
                // the selected diagram element on diagram "Arrange all"
                AbstractLayoutProvider abstractLayoutProvider = new BorderItemAwareLayoutProvider(clp, true);
                this.layoutNodeProvider = new ArrangeSelectionLayoutProvider(abstractLayoutProvider);
            } else {
                this.layoutNodeProvider = new ArrangeSelectionLayoutProvider(clp);
            }
        }
        return this.layoutNodeProvider;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.provider.LayoutProvider#provides(org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart)
     */
    @Override
    public boolean provides(final IGraphicalEditPart container) {
        /*
         * we should always provide even if container is not the diagram, to
         * handle the case of arrange all action when an element in a container
         * is selected
         */
        return true;
    }

    @Override
    public boolean isDiagramLayoutProvider() {
        return true;
    }

}

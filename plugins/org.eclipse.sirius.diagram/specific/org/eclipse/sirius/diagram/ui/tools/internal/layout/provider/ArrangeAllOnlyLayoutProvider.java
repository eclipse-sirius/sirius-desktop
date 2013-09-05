/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.layout.provider;

import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.AbstractLayoutProvider;

/**
 * {@link ArrangeSelectionLayoutProvider} that <b>only accepts ArrangeAll
 * actions</b> (and does nothing if the current Action is an Arrange Selection
 * Action).
 * <p>
 * Layout provider that add a list of diagram elements to keep fixed in the
 * LayoutHint, during arrange selection action. This information will be used
 * later in the PinnedElementHandler and PinnedElementLayoutProvider. It is
 * executed before the generic arrange operation.
 * </p>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ArrangeAllOnlyLayoutProvider extends ArrangeSelectionLayoutProvider {

    /**
     * Creates a new ArrangeAllOnlyLayoutProvider.
     * 
     * @param clp
     *            The layout provider to call after finding diagram element to
     *            keep fixed on arrange all
     */
    public ArrangeAllOnlyLayoutProvider(AbstractLayoutProvider clp) {
        super(clp);
    }
}

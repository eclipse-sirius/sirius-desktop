/*******************************************************************************
 * Copyright (c) 2011, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.layout;

import org.eclipse.sirius.diagram.DDiagram;

/**
 * Interface used be the extension point
 * <code>org.eclipse.sirius.diagram.ui.layoutDataManager</code> to implements to
 * override the default behavior of Copy/Paste layout actions.
 * 
 * @author <a href="mailto:maxime.porhel@obeo.fr">Maxime Porhel</a>
 * @deprecated since Sirius 4.1.0. Use
 *             {@link org.eclipse.sirius.diagram.ui.tools.api.format.IFormatDataManagerProvider}
 *             instead.
 */
@Deprecated
public interface ILayoutDataManagerProvider {

    /**
     * Returns <code>true</code> if this provider provides a specific layout
     * data manager for the given diagram.
     * 
     * @param diagram
     *            the current diagram.
     * @return <code>true</code> if this provider provides a specific layout
     *         data manager for the given diagram.
     */
    boolean provides(DDiagram diagram);

    /**
     * Provides its specific layout data manager. It will be called once.
     * 
     * @return the extension of the refresh mechanism.
     */
    SiriusLayoutDataManager getLayoutDataManager();
}

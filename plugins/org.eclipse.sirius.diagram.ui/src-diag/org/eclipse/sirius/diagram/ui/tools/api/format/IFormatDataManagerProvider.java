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
package org.eclipse.sirius.diagram.ui.tools.api.format;

import org.eclipse.sirius.diagram.DDiagram;

/**
 * Interface used be the extension point
 * <code>org.eclipse.sirius.diagram.ui.formatDataManager</code> to implements to
 * override the default behavior of Copy/Paste format actions.
 * 
 * @author <a href="mailto:maxime.porhel@obeo.fr">Maxime Porhel</a>
 */
public interface IFormatDataManagerProvider {

    /**
     * Returns <code>true</code> if this provider provides a specific format
     * data manager for the given diagram.
     * 
     * @param diagram
     *            the current diagram.
     * @return <code>true</code> if this provider provides a specific format
     *         data manager for the given diagram.
     */
    boolean provides(DDiagram diagram);

    /**
     * Provides its specific format data manager. It will be called once.
     * 
     * @return the extension of the refresh mechanism.
     */
    SiriusFormatDataManager getFormatDataManager();
}

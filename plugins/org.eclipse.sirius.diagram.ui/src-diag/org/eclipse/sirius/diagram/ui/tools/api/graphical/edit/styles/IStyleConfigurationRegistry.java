/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles;

import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.styles.StyleConfigurationRegistry;
import org.eclipse.sirius.viewpoint.Style;

/**
 * A registry for {@link StyleConfiguration}s.
 * 
 * @author ymortier
 */
public interface IStyleConfigurationRegistry {

    /**
     * The Default registry.
     */
    IStyleConfigurationRegistry INSTANCE = StyleConfigurationRegistry.getInstance();

    /**
     * Return the {@link StyleConfiguration} corresponding to the specified
     * {@link DiagramElementMapping} and {@link Style}.
     * 
     * @param vpElementMapping
     *            the mapping of the viewpoint element.
     * @param style
     *            the style.
     * @return the style configuration
     */
    StyleConfiguration getStyleConfiguration(DiagramElementMapping vpElementMapping, Style style);

}

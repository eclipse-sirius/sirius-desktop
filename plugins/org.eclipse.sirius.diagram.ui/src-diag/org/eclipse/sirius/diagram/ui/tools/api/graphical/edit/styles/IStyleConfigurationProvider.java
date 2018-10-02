/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles;

import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.Style;

/**
 * A provider of style configuration.
 * 
 * @author ymortier
 */
public interface IStyleConfigurationProvider {

    /**
     * Return <code>true</code> if this provider provides a configuration for
     * the specified {@link DiagramElementMapping} and {@link Style}.
     * 
     * @param mapping
     *            the mapping.
     * @param style
     *            the style.
     * @return <code>true</code> if this provider provides a configuration for
     *         the specified {@link DiagramElementMapping} and {@link Style}.
     */
    boolean provides(DiagramElementMapping mapping, Style style);

    /**
     * Create a style configuration for the specified style and mapping.
     * 
     * @param mapping
     *            the mapping.
     * @param style
     *            the style.
     * @return the new style configuration.
     */
    StyleConfiguration createStyleConfiguration(DiagramElementMapping mapping, Style style);

}

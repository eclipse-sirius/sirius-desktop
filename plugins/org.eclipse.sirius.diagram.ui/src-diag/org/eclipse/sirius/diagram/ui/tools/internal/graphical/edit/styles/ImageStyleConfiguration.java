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
package org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.styles;

import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.SimpleStyleConfiguration;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;

/**
 * {@link org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration}
 * for images.
 * 
 * @author ymortier
 */
public class ImageStyleConfiguration extends SimpleStyleConfiguration {
    /**
     * {@inheritDoc}
     */
    @Override
    public AnchorProvider getAnchorProvider() {
        return null;
    }
}

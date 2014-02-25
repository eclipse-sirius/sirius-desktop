/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.styles;

import org.eclipse.sirius.diagram.ui.tools.api.figure.anchor.AnchorProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.SimpleStyleConfiguration;

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

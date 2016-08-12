/*******************************************************************************
 * Copyright (c) 2009, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.layout;

/**
 * Interface for all kind of key use to store layoutData (
 * {@link org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * @deprecated since Sirius 4.1.0. Use
 *             {@link org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey}
 *             instead.
 */
@Deprecated
public interface LayoutDataKey {

    /**
     * Get the ID of this key.
     * 
     * @return The ID of this key.
     */
    String getId();
}

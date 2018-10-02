/*******************************************************************************
 * Copyright (c) 2009, 2016 THALES GLOBAL SERVICES.
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

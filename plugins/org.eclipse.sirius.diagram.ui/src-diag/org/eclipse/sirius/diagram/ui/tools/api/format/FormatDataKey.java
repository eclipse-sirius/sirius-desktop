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
package org.eclipse.sirius.diagram.ui.tools.api.format;

/**
 * Interface for all kind of key use to store formatData (
 * {@link org.eclipse.sirius.diagram.formatdata.AbstractFormatData}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public interface FormatDataKey {

    /**
     * Get the ID of this key.
     * 
     * @return The ID of this key.
     */
    String getId();
}

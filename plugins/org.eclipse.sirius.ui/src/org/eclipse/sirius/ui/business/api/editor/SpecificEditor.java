/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.editor;

import org.eclipse.emf.common.util.URI;

/**
 * Specific editor.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public interface SpecificEditor {

    /**
     * Get the viewpoint uri containing the diagram description used to create
     * the diagram.
     * 
     * @return the viewpoint name
     */
    URI getViewpointURI();

    /**
     * The diagram description name to use to create the diagram.
     * 
     * @return the diagram description name
     */
    String getDiagramDescriptionName();

    /**
     * Should the session file automatically created be stored in the workspace
     * (i.e visible to the end user) or not.
     * 
     * @return <code>true</code> if the session file should be stored in the
     *         workspace, <code>false</code> otherwise
     */
    boolean isSessionStoredInWorkspace();

}

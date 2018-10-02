/*******************************************************************************
 * Copyright (c) 2018 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.api.management;

/**
 * 
 * Provides tools constants.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public interface ToolConstants {
    /**
     * The Generic Connection Creation Tool ID.
     */
    String TOOL_GENERIC_CONNECTION_CREATION = "GenericConnectionCreationTool"; //$NON-NLS-1$

    /**
     * The note attachment tool ID.
     */
    String TOOL_NOTEATTACHMENT = "noteattachmentTool"; //$NON-NLS-1$

    /**
     * The link note tool ID.
     */
    String TOOL_DIAGRAMLINK = "linkNoteTool"; //$NON-NLS-1$

    /**
     * The pinning tool ID.
     */
    String TOOL_PINNING = "Pinning"; //$NON-NLS-1$

    /**
     * The default tool separator ID.
     */
    String TOOL_SEPARATOR_DEFAULT = "defaultTools"; //$NON-NLS-1$

    /**
     * The default section containing default tools.
     */
    String DEFAULT_SECTION_ID = "defaultSectionId"; //$NON-NLS-1$
}

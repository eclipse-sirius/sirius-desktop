/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * This interface store Resources handle for {@link DiagramOutlinePage}.
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public interface IDiagramOutlinePage {

    /** The outline tip text. */
    String OUTLINE_VIEW_OUTLINE_TIP_TEXT = Messages.IDiagramOutlinePage_outlineTooltip;

    /** The overview tip text. */
    String OUTLINE_VIEW_OVERVIEW_TIP_TEXT = Messages.IDiagramOutlinePage_overviewTooltip;

    /** The outline icon descriptor. */
    ImageDescriptor DESC_OUTLINE = DiagramUIPlugin.Implementation.getBundledImageDescriptor("icons/outline.gif"); //$NON-NLS-1$

    /** the overview icon descriptor. */
    ImageDescriptor DESC_OVERVIEW = DiagramUIPlugin.Implementation.getBundledImageDescriptor("icons/overview.gif"); //$NON-NLS-1$

}

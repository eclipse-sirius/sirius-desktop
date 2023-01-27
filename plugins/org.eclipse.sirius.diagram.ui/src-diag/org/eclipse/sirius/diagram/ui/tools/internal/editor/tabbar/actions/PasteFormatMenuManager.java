/*******************************************************************************
 * Copyright (c) 2016, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions;

import org.eclipse.gmf.runtime.common.ui.action.ActionMenuManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;

/**
 * The paste format menu manager. It contains all paste format related actions
 * (format, layout and style).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class PasteFormatMenuManager extends ActionMenuManager {

    /**
     * The paste format menu action containing the UI for the paste format menu
     * manager
     */
    private static class PasteFormatMenuAction extends Action {
        PasteFormatMenuAction() {
            setText(Messages.PasteFormatAction_text);
            setToolTipText(Messages.PasteFormatAction_toolTipText_diagram);
            ImageDescriptor imageDesc = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.PASTE_FORMAT_ICON);
            setImageDescriptor(imageDesc);
            setHoverImageDescriptor(imageDesc);
        }
    }

    /**
     * Creates a new instance of the distribute menu manager.
     */
    public PasteFormatMenuManager() {
        super(ActionIds.MENU_PASTE_FORMAT, new PasteFormatMenuAction(), true);
    }
}

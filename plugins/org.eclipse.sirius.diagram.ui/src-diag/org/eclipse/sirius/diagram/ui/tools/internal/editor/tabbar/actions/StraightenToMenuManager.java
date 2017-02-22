/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
 * The straighten menu manager. It contains all straighten-related actions.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class StraightenToMenuManager extends ActionMenuManager {

    /**
     * The straighten menu action containing the UI for the straighten menu
     * manager
     */
    private static class StraightenMenuAction extends Action {
        StraightenMenuAction() {
            setText(Messages.StraightenToMenuAction_text);
            setToolTipText(Messages.StraightenToMenuAction_tooltip);
            ImageDescriptor imageDesc = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.STRAIGHTEN_TO_TOP);
            setImageDescriptor(imageDesc);
            setHoverImageDescriptor(imageDesc);
        }
    }

    /**
     * Creates a new instance of the straighten menu manager.
     */
    public StraightenToMenuManager() {
        super(ActionIds.MENU_STRAIGHTEN_TO, new StraightenMenuAction(), true);
    }
}

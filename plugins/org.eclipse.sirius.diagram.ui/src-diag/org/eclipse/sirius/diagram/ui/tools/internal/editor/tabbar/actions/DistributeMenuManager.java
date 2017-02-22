/*******************************************************************************
 * Copyright (c) 2014, 2015 THALES GLOBAL SERVICES and others.
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
 * The distribute menu manager. It contains all distribute-related actions.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DistributeMenuManager extends ActionMenuManager {

    /**
     * The distribute menu action containing the UI for the distribute menu
     * manager
     */
    private static class DistributeMenuAction extends Action {
        DistributeMenuAction() {
            setText(Messages.DistributeMenuAction_text);
            setToolTipText(Messages.DistributeMenuAction_tooltip);
            ImageDescriptor imageDesc = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DISTRIBUTE_WITH_UNIFORM_GAPS_HORIZONTALLY);
            setImageDescriptor(imageDesc);
            setHoverImageDescriptor(imageDesc);
        }
    }

    /**
     * Creates a new instance of the distribute menu manager.
     */
    public DistributeMenuManager() {
        super(ActionIds.MENU_DISTRIBUTE, new DistributeMenuAction(), true);
    }
}

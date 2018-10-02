/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.api.image;

import org.eclipse.jface.window.Window;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.SelectDiagramElementBackgroundImageDialog;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * A default {@link ImageSelector} to get Image from the workspace.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class WorkspaceImageSelector implements ImageSelector {

    /**
     * {@inheritDoc}
     */
    public String selectImage(BasicLabelStyle basicLabelStyle) {
        String imagePath = null;
        Shell activeShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
        SelectDiagramElementBackgroundImageDialog dialog = new SelectDiagramElementBackgroundImageDialog(activeShell, basicLabelStyle);
        if (dialog.open() == Window.OK) {
            imagePath = dialog.getImagePath();
        }
        return imagePath;
    }

}

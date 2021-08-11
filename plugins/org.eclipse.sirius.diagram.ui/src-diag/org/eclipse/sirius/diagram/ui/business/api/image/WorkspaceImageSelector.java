/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES.
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

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.SelectDiagramElementBackgroundImageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * A default {@link ImageSelector} to get Image from the workspace.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class WorkspaceImageSelector implements ImageSelector {

    @Override
    public List<String> selectImages(EObject eObject, ImageSelector.SelectionMode selectionMode) {
        String imagePath = null;
        Shell activeShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
        SelectDiagramElementBackgroundImageDialog dialog = new SelectDiagramElementBackgroundImageDialog(activeShell, eObject);
        if (dialog.open() == Window.OK) {
            imagePath = dialog.getImagePath();
        }
        if (imagePath == null) {
            return Collections.<String> emptyList();
        } else {
            return Collections.<String> singletonList(imagePath);
        }
    }

}

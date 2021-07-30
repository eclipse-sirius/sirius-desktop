/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.dialogs;

import java.io.File;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE.SharedImages;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * A label provider that manages a {@link java.io.File} structure for images in workspace.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
public class ImageWorkspaceLabelProvider extends BaseLabelProvider implements ILabelProvider {

    private WorkbenchLabelProvider wlp;

    /**
     * Create a new instance of {@link ImageWorkspaceLabelProvider}.
     */
    public ImageWorkspaceLabelProvider() {
        this.wlp = new WorkbenchLabelProvider();
    }

    @Override
    public Image getImage(Object element) {
        Image result = null;
        if (element instanceof File) {
            File file = (File) element;
            if (file.isDirectory()) {
                String filePath = file.getParentFile().getPath();
                String workspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();
                if (filePath.equals(workspacePath)) {
                    result = PlatformUI.getWorkbench().getSharedImages().getImage(SharedImages.IMG_OBJ_PROJECT);
                } else {
                    result = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
                }
            } else {
                ImageDescriptor imgDesc = DiagramUIPlugin.Implementation.getBundledImageDescriptor("icons/image_obj.gif"); //$NON-NLS-1$
                result = DiagramUIPlugin.getPlugin().getImage(imgDesc);
            }
        }
        return result;
    }

    @Override
    public String getText(Object element) {
        String result = wlp.getText(element);
        if (result == null || result.isEmpty()) {
            if (element instanceof File) {
                File file = (File) element;
                result = file.getName();
            }
        }
        return result;
    }
}
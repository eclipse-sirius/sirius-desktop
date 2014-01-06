/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.actions.style;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ImagesPath;
import org.eclipse.sirius.diagram.business.api.image.ImageSelector;
import org.eclipse.sirius.diagram.business.api.image.ImageSelectorService;
import org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PlatformUI;

/**
 * This action changes the style of an AbstractDNode to a WorkspaceImage style.
 * 
 * 
 * @author Maxime Porhel (mporhel)
 */
public class SetStyleToWorkspaceImageAction extends Action {

    /** Action id for the "Set style to workspace image". */
    public static final String SET_STYLE_TO_WORKSPACE_IMAGE_ACTION_ID = "org.eclipse.sirius.diagram.tools.internal.actions.style";

    /** Action name for the "Set style to workspace image". */
    public static final String SET_STYLE_TO_WORKSPACE_IMAGE_ACTION_NAME = "Set style to workspace image";

    /**
     * Create an new {@link SetStyleToWorkspaceImageAction}.
     */
    public SetStyleToWorkspaceImageAction() {
        super(SET_STYLE_TO_WORKSPACE_IMAGE_ACTION_NAME);
        setId(SET_STYLE_TO_WORKSPACE_IMAGE_ACTION_ID);
        final ImageDescriptor enabledImage = SiriusDiagramEditorPlugin.getBundledImageDescriptor(ImagesPath.IMAGE_ICON);
        final ImageDescriptor disabledImage = ImageDescriptor.createWithFlags(enabledImage, SWT.IMAGE_DISABLE);
        this.setImageDescriptor(enabledImage);
        this.setDisabledImageDescriptor(disabledImage);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.actions.RetargetAction#run()
     */
    @Override
    public void run() {
        ImageSelector imageSelector = ImageSelectorService.INSTANCE.getImageSelector();
        List<BasicLabelStyle> styles = getStyles();
        for (BasicLabelStyle basicLabelStyle : styles) {
            String imagePath = imageSelector.selectImage(basicLabelStyle);
            if (imagePath != null) {
                ImageSelectorService.INSTANCE.updateStyle(basicLabelStyle, imagePath);
            }
        }
    }

    private List<BasicLabelStyle> getStyles() {
        List<BasicLabelStyle> styles = new ArrayList<BasicLabelStyle>();
        ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            for (Object selectedEditPart : structuredSelection.toArray()) {
                if (selectedEditPart instanceof IDiagramElementEditPart) {
                    IDiagramElementEditPart diagramElementEditPart = (IDiagramElementEditPart) selectedEditPart;
                    DDiagramElement dde = diagramElementEditPart.resolveDiagramElement();
                    DDiagramElementQuery ddeQuery = new DDiagramElementQuery(dde);
                    Option<BasicLabelStyle> oldStyle = ddeQuery.getLabelStyle();
                    if (oldStyle.some()) {
                        BasicLabelStyle basicLabelStyle = oldStyle.get();
                        styles.add(basicLabelStyle);
                    }
                }
            }
        }
        return styles;
    }

}

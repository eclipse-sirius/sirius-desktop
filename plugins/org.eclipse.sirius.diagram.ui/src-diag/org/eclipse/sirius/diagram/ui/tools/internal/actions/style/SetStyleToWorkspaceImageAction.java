/*******************************************************************************
 * Copyright (c) 2007, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.style;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.business.api.image.ImageSelectionDialog;
import org.eclipse.sirius.diagram.ui.business.api.image.ImageSelector;
import org.eclipse.sirius.diagram.ui.business.api.image.ImageSelectorService;
import org.eclipse.sirius.diagram.ui.business.api.image.WorkspaceImageHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

/**
 * This action changes the style of an AbstractDNode to a WorkspaceImage style.
 * 
 * 
 * @author Maxime Porhel (mporhel)
 */
public class SetStyleToWorkspaceImageAction extends Action {

    /** Action id for the "Set style to workspace image". */
    public static final String SET_STYLE_TO_WORKSPACE_IMAGE_ACTION_ID = "org.eclipse.sirius.diagram.tools.internal.actions.style"; //$NON-NLS-1$

    /** Action name for the "Set style to workspace image". */
    public static final String SET_STYLE_TO_WORKSPACE_IMAGE_ACTION_NAME = Messages.SetStyleToWorkspaceImageAction_text;

    /**
     * Create an new {@link SetStyleToWorkspaceImageAction}.
     */
    public SetStyleToWorkspaceImageAction() {
        super(SET_STYLE_TO_WORKSPACE_IMAGE_ACTION_NAME);
        setId(SET_STYLE_TO_WORKSPACE_IMAGE_ACTION_ID);
        final ImageDescriptor enabledImage = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.IMAGE_ICON);
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
            String workspacePath = null;
            if (basicLabelStyle instanceof WorkspaceImage img) {
                workspacePath = img.getWorkspacePath();
            }
            List<String> imagePaths = imageSelector.selectImages(basicLabelStyle, ImageSelector.SelectionMode.MONO_SELECTION, workspacePath, false);
            if (imagePaths.size() == 1) {
                if (imagePaths.get(0).equals(ImageSelectionDialog.NO_IMAGE_PATH_TEXT)) {
                    WorkspaceImageHelper.INSTANCE.resetStyle(basicLabelStyle);
                } else {
                    WorkspaceImageHelper.INSTANCE.updateStyle(basicLabelStyle, imagePaths.get(0));
                }
            }
        }
    }

    /**
     * Return the style of all selected nodes.
     * 
     * @return the list of style of all selected nodes
     */
    public static List<BasicLabelStyle> getStyles() {
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

    /**
     * Return if all selected can have workspace image style.
     * 
     * @return true if all selected can have workspace image style
     */
    public static boolean selectionCanHaveWorkspaceImage() {
        boolean result = false;

        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        if (page != null && page.getSelection() instanceof IStructuredSelection) {
            IStructuredSelection selection = (IStructuredSelection) page.getSelection();
            if (!selection.isEmpty()) {
                result = true;

                final Iterator<?> it = selection.iterator();
                while (it.hasNext() && result) {
                    Object o = it.next();
                    if (o instanceof IAbstractDiagramNodeEditPart) {
                        // check permission
                        IAbstractDiagramNodeEditPart diagramEditPart = (IAbstractDiagramNodeEditPart) o;
                        View view = (View) diagramEditPart.getModel();
                        EObject element = view.getElement();
                        IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(element);
                        if (permissionAuthority != null && !permissionAuthority.canEditInstance(element)) {
                            result = false;
                        }
                    } else {
                        result = false;
                    }
                }
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isEnabled() {
        return selectionCanHaveWorkspaceImage();
    }
}

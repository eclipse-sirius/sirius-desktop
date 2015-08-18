/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions.style;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.Disposable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.ResetStylePropertiesToDefaultValuesCommand;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;

import com.google.common.collect.Iterables;

/**
 * This action reset the style properties to their default values as defined in
 * the VSM or in the diagram preference page.
 * 
 * @author mchauvin
 */
public class ResetStylePropertiesToDefaultValuesAction extends Action implements Disposable {

    /** The action name. */
    public static final String ACTION_NAME = "Reset style properties to default values";

    /** The action id. */
    public static final String ID = "RESET_STYLE_PROPERTIES_TO_DEFAULT_VALUES_ACTION_ID"; //$NON-NLS-1$

    private IWorkbenchPage page;

    private ISelection selection;

    /**
     * Create a new {@link ResetStylePropertiesToDefaultValuesAction}.
     * 
     * @param page
     *            the {@link IWorkbenchPage}
     */
    public ResetStylePropertiesToDefaultValuesAction(IWorkbenchPage page) {
        super(ACTION_NAME);
        this.page = page;
        final ImageDescriptor enabledImage = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.UNDO_ICON);
        final ImageDescriptor disabledImage = ImageDescriptor.createWithFlags(enabledImage, SWT.IMAGE_DISABLE);
        this.setId(ID);
        this.setImageDescriptor(enabledImage);
        this.setDisabledImageDescriptor(disabledImage);
        this.setEnabled(isEnabled());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        boolean result = false;
        IEditorPart activeEditor = page.getActiveEditor();
        if (activeEditor instanceof DDiagramEditor) {
            selection = activeEditor.getEditorSite().getSelectionProvider().getSelection();
            if (selection instanceof StructuredSelection) {
                for (final IGraphicalEditPart part : Iterables.filter(((StructuredSelection) selection).toList(), IGraphicalEditPart.class)) {
                    View view = part.getNotationView();
                    EObject element = view.getElement();
                    if (element instanceof DDiagramElement) {
                        DDiagramElement dDiagramElement = (DDiagramElement) element;
                        if (shouldBeEnable(dDiagramElement)) {
                            result = true;
                            break;
                        }
                    }

                    ViewQuery viewQuery = new ViewQuery(view);
                    if (viewQuery.isCustomized()) {
                        result = true;
                        break;
                    }
                } // for
            }
        }
        return result;
    }

    private boolean shouldBeEnable(DDiagramElement dDiagramElement) {
        boolean result = false;
        DDiagramElementQuery dDiagramElementQuery = new DDiagramElementQuery(dDiagramElement);
        if (dDiagramElementQuery.isCustomized()) {
            // check permission
            IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(dDiagramElement);
            if (permissionAuthority == null || permissionAuthority.canEditInstance(dDiagramElement)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.actions.RetargetAction#run()
     */
    @Override
    public void run() {
        IEditorPart activeEditor = page.getActiveEditor();
        if (activeEditor instanceof DDiagramEditor) {
            DDiagramEditor dDiagramEditor = (DDiagramEditor) activeEditor;
            DDiagram dDiagram = (DDiagram) dDiagramEditor.getRepresentation();
            Map<View, DDiagramElement> customizedViews = getSelectedCustomizedViews();
            if (!customizedViews.isEmpty()) {
                // Reset style processing
                TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(dDiagram);
                domain.getCommandStack().execute(new ResetStylePropertiesToDefaultValuesCommand(domain, dDiagram, customizedViews));
            }
        }
    }

    private Map<View, DDiagramElement> getSelectedCustomizedViews() {
        Map<View, DDiagramElement> customizedViews = new LinkedHashMap<View, DDiagramElement>();
        if (selection instanceof StructuredSelection) {
            StructuredSelection structuredSelection = (StructuredSelection) selection;
            for (Object obj : structuredSelection.toList()) {
                if (obj instanceof IGraphicalEditPart) {
                    IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) obj;
                    View view = graphicalEditPart.getNotationView();
                    EObject element = view.getElement();
                    if (element instanceof DDiagramElement) {
                        DDiagramElement dDiagramElement = (DDiagramElement) element;
                        if (new DDiagramElementQuery(dDiagramElement).isCustomized() || new ViewQuery(view).isCustomized()) {
                            customizedViews.put(view, dDiagramElement);
                        }
                    } else if (element == null && new ViewQuery(view).isCustomized()) {
                        customizedViews.put(view, null);
                    }
                }
            }
        }
        return customizedViews;
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        this.selection = null;
        this.page = null;
    }

}

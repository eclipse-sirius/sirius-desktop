/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.Disposable;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramDescriptionProvider;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor;
import org.eclipse.sirius.diagram.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DiagramOutlinePage;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Hide a {@link DDiagramElement} on a {@link org.eclipse.sirius.diagram.DDiagram}.
 * 
 * @author cbrun
 * 
 */
public class HideDDiagramElementAction extends Action implements IObjectActionDelegate, Disposable {

    /** The selection. */
    private ISelection selection;

    private IWorkbenchPart representationPart;

    /**
     * Constructor.
     */
    public HideDDiagramElementAction() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param text
     *            String
     */
    public HideDDiagramElementAction(final String text) {
        this(text, DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.HIDE_ELEMENT_IMG));
    }

    /**
     * Constructor.
     * 
     * @param text
     *            String
     * @param image
     *            ImageDescriptor
     */
    public HideDDiagramElementAction(final String text, final ImageDescriptor image) {
        super(text, image);
        setId(text);
    }

    /**
     * Empty. {@inheritDoc}
     * 
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    @Override
    public void setActivePart(final IAction action, final IWorkbenchPart targetPart) {
        // empty.
    }

    /**
     * Set representationPart from action part.
     * 
     * @param actionPart
     *            the action part attached to this action.
     */
    public void setActionPart(IWorkbenchPart actionPart) {
        this.representationPart = actionPart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isEnabled() {
        boolean result = false;

        ISelection currentSelection = selection;
        if (selection == null) {
            currentSelection = getCurrentSelection();
        }

        if (currentSelection instanceof IStructuredSelection && !currentSelection.isEmpty()) {
            Collection<DDiagramElement> ddes = new HashSet<>();
            for (Object selected : ((IStructuredSelection) currentSelection).toList()) {
                if (selected instanceof IDiagramElementEditPart) {
                    IDiagramElementEditPart diagramElementEditPart = (IDiagramElementEditPart) selected;
                    if (diagramElementEditPart.isActive()) {
                        DDiagramElement dDiagramElement = diagramElementEditPart.resolveDiagramElement();
                        if (dDiagramElement != null) {
                            ddes.add(dDiagramElement);
                        }
                    }
                } else if (selected instanceof DDiagramElement) {
                    ddes.add((DDiagramElement) selected);
                }
            }

            if (!ddes.isEmpty()) {
                DDiagram parentDiagram = ddes.iterator().next().getParentDiagram();
                Predicate<DDiagramElement> allowsHideReveal = allowsHideReveal(parentDiagram);
                Predicate<DDiagramElement> notDirectlyHidden = dde -> dde.isVisible();
                return Iterables.all(ddes, Predicates.and(allowsHideReveal, notDirectlyHidden));
            }

        }

        return result;
    }

    /**
     * Get the current selection.
     * 
     * @return The current selection.
     */
    private ISelection getCurrentSelection() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run(final IAction action) {
        if (this.selection instanceof IStructuredSelection) {
            final IStructuredSelection structuredSelection = (IStructuredSelection) this.selection;
            final Set<Object> minimizedSelection = new HashSet<Object>(Arrays.asList(structuredSelection.toArray()));
            if (minimizedSelection.size() > 0) {
                final Object nextSelected = minimizedSelection.iterator().next();

                if (nextSelected instanceof EditPart) {

                    final RootEditPart root = ((EditPart) nextSelected).getRoot();
                    final DDiagramEditor diagramEditor = (DDiagramEditor) ((EditPart) nextSelected).getViewer().getProperty(DDiagramEditor.EDITOR_ID);

                    runHideCommand(root, diagramEditor, partsToSemantic(Arrays.asList(structuredSelection.toArray())));
                } else if (nextSelected instanceof DDiagramElement) {
                    final Set<EObject> eObjectSelection = new HashSet<EObject>();
                    final Iterator<Object> it = minimizedSelection.iterator();
                    while (it.hasNext()) {
                        final Object obj = it.next();
                        if (obj instanceof EObject) {
                            eObjectSelection.add((EObject) obj);
                        }
                    }
                    run(eObjectSelection);
                }

            }
        }
    }

    /**
     * Empty. {@inheritDoc} Used from button.
     * 
     * @see org.eclipse.jface.action#run(org.eclipse.jface.action)
     */
    @Override
    public void run() {
        this.selection = getCurrentSelection();
        run(this);
        this.selection = null;
    }

    private void run(final Set<EObject> minimizedSelection) {
        if (this.selection instanceof DiagramOutlinePage.TreeSelectionWrapper) {

            final DiagramOutlinePage.TreeSelectionWrapper wrapper = (DiagramOutlinePage.TreeSelectionWrapper) this.selection;

            final RootEditPart root = wrapper.getRoot();
            final DDiagramEditor diagramEditor = (DDiagramEditor) wrapper.getViewer().getProperty(DDiagramEditor.EDITOR_ID);

            runHideCommand(root, diagramEditor, minimizedSelection);
        }
    }

    private void runHideCommand(final RootEditPart root, final DDiagramEditor editor, final Set<EObject> elements) {

        final Object adapter = editor.getAdapter(IDiagramCommandFactoryProvider.class);
        final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(editor.getEditingDomain().getResourceSet());
        final IDiagramCommandFactory emfCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);
        final Command cmd = emfCommandFactory.buildHideCommand(elements);

        ((TransactionalEditingDomain) editor.getAdapter(EditingDomain.class)).getCommandStack().execute(cmd);
    }

    private Set<EObject> partsToSemantic(final List<Object> asList) {
        final Set<EObject> result = new HashSet<EObject>();
        final Iterator<Object> it = asList.iterator();
        while (it.hasNext()) {
            final Object obj = it.next();
            if (obj instanceof IGraphicalEditPart) {
                final IGraphicalEditPart part = (IGraphicalEditPart) obj;
                final EObject element = part.resolveSemanticElement();
                if (element != null) {
                    result.add(element);
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void selectionChanged(final IAction action, final ISelection s) {
        IWorkbenchPart selectedPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
        if (representationPart != null && !representationPart.equals(selectedPart)) {
            return;
        }
        this.selection = s;
        setEnabled(isEnabled());
    }

    /**
     * Indicates if the given ddiagram is allowing hide/reveal.
     * 
     * @param diagram
     *            the diagram to inspect
     * @return true if the given ddiagram is allowing layouting mode, false otherwise
     */
    public static Predicate<DDiagramElement> allowsHideReveal(DDiagram diagram) {
        // default return value is true for non-Region element (for basic
        // DDiagram that are not handled
        // by any DiagramDescriptionProvider).
        Predicate<DDiagramElement> result = new Predicate<DDiagramElement>() {
            @Override
            public boolean apply(DDiagramElement dde) {
                if (dde instanceof DDiagramElementContainer) {
                    DDiagramElementContainerExperimentalQuery query = new DDiagramElementContainerExperimentalQuery((DDiagramElementContainer) dde);
                    return !query.isRegion();
                }
                return true;
            }
        };

        // If an aird has been opened from the Package Explorer View, then
        // we return false as no diagram is associated to this editor
        if (diagram == null || diagram.getDescription() == null || !isEditable(diagram)) {
            return Predicates.alwaysFalse();
        }

        // If diagram is not null, we search for a possible
        // DiagramDescriptionProvider handling this type of diagram
        for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
            if (diagramTypeDescriptor.getDiagramDescriptionProvider().handles(diagram.getDescription().eClass().getEPackage())) {
                // This DiagramDescriptionProvider may forbid hide/reveal
                // actions.
                final IDiagramDescriptionProvider provider = diagramTypeDescriptor.getDiagramDescriptionProvider();
                result = new Predicate<DDiagramElement>() {
                    @Override
                    public boolean apply(DDiagramElement input) {
                        return provider.allowsHideReveal(input);
                    }
                };
                break;
            }
        }

        return result;
    }

    private static boolean isEditable(DDiagram diagram) {
        boolean isEditable = false;
        Resource resource = diagram.eResource();
        if (resource != null) {
            IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(resource.getResourceSet());
            isEditable = permissionAuthority.canEditInstance(diagram);
        }
        return isEditable;
    }

    @Override
    public void dispose() {
        this.representationPart = null;
        this.selection = null;
    }
}

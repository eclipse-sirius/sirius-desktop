/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.business.api.provider.AbstractDDiagramElementLabelItemProvider;
import org.eclipse.sirius.diagram.ui.business.api.provider.DEdgeBeginLabelItemProvider;
import org.eclipse.sirius.diagram.ui.business.api.provider.DEdgeEndLabelItemProvider;
import org.eclipse.sirius.diagram.ui.business.api.provider.DEdgeLabelItemProvider;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DiagramOutlinePage;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Hide the label of a {@link DDiagramElement}.
 * 
 * @author lredor
 * 
 */
public class HideDDiagramElementLabelAction extends Action implements IObjectActionDelegate, Disposable {

    private static Predicate<Object> isEnabledPredicate = new Predicate<Object>() {
        @Override
        public boolean apply(Object input) {
            boolean result = false;
            if (input instanceof IGraphicalEditPart) {
                result = HideDDiagramElementLabelAction.isEnabled((IGraphicalEditPart) input);
            } else if (input instanceof DDiagramElement) {
                result = HideDDiagramElementLabelAction.isEnabled((DDiagramElement) input);
            } else if (input instanceof AbstractDDiagramElementLabelItemProvider) {
                Option<DDiagramElement> optionTarget = ((AbstractDDiagramElementLabelItemProvider) input).getDiagramElementTarget();
                if (optionTarget.some()) {
                    if (input instanceof DEdgeBeginLabelItemProvider) {
                        result = HideDDiagramElementLabelAction.isEnabled(optionTarget.get(), DEdgeBeginNameEditPart.VISUAL_ID);
                    } else if (input instanceof DEdgeLabelItemProvider) {
                        result = HideDDiagramElementLabelAction.isEnabled(optionTarget.get(), DEdgeNameEditPart.VISUAL_ID);
                    } else if (input instanceof DEdgeEndLabelItemProvider) {
                        result = HideDDiagramElementLabelAction.isEnabled(optionTarget.get(), DEdgeEndNameEditPart.VISUAL_ID);
                    } else {
                        result = HideDDiagramElementLabelAction.isEnabled(optionTarget.get());
                    }
                }
            }
            return result;
        }
    };

    /** The selection. */
    private ISelection selection;

    private TabbarRevealLabelsAction oppositeAction;

    private Map<EObject, List<Integer>> semanticToLabelsVisualIDToHideMap = new HashMap<EObject, List<Integer>>();

    /**
     * Constructor.
     */
    public HideDDiagramElementLabelAction() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param text
     *            String
     */
    public HideDDiagramElementLabelAction(final String text) {
        this(text, DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.HIDE_LABEL_ELEMENT_IMG));
    }

    /**
     * Constructor.
     * 
     * @param text
     *            String
     * @param image
     *            ImageDescriptor
     */
    public HideDDiagramElementLabelAction(final String text, final ImageDescriptor image) {
        super(text, image);
        setId(text);
    }

    @Override
    public boolean isEnabled() {
        if (this.selection instanceof IStructuredSelection) {
            return isEnabled(((IStructuredSelection) this.selection).toList());
        }
        return super.isEnabled();
    }

    /**
     * Check if at least on element has a label that can be hide.
     * 
     * @param elementsToCheck
     *            The elements to check.
     * @return true if at least on element has a label that can be hide, false
     *         otherwise
     */
    public static boolean isEnabled(Collection<?> elementsToCheck) {
        return Iterables.any(elementsToCheck, isEnabledPredicate);
    }

    private static boolean isEnabled(IGraphicalEditPart graphicalEditPart) {
        if (graphicalEditPart.isActive() && graphicalEditPart.resolveSemanticElement() instanceof DDiagramElement) {
            return HideDDiagramElementLabelAction.isEnabled((DDiagramElement) graphicalEditPart.resolveSemanticElement());
        }
        return false;
    }

    private static boolean isEnabled(DDiagramElement diagramElement) {
        DDiagram dDiagram = diagramElement.getParentDiagram();
        DDiagramElementQuery query = new DDiagramElementQuery(diagramElement);
        return (dDiagram != null && isEditable(dDiagram)) && query.canHideLabel() && !query.isLabelHidden();
    }

    private static boolean isEnabled(DDiagramElement diagramElement, int labelVisualID) {
        DDiagram dDiagram = diagramElement.getParentDiagram();
        DDiagramElementQuery query = new DDiagramElementQuery(diagramElement);
        return (dDiagram != null && isEditable(dDiagram)) && query.canHideLabel() && !query.isLabelHidden(labelVisualID);
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

                    List<Object> selectionList = Arrays.asList(structuredSelection.toArray());
                    if (containsEdgeLabelsInSelection(selectionList)) {
                        Set<EObject> partsToSemantic = partsToSemantic(selectionList);
                        runHideLabelSelectionCommand(root, diagramEditor, partsToSemantic, collectLabelsToHideVisualIds(partsToSemantic, selectionList));
                    } else {
                        semanticToLabelsVisualIDToHideMap.clear();
                        runHideLabelCommand(root, diagramEditor, partsToSemantic(selectionList));
                    }
                } else if (nextSelected instanceof DDiagramElement || nextSelected instanceof AbstractDDiagramElementLabelItemProvider) {
                    runForNoEditPartSelection(minimizedSelection);
                }
                // We activate the Show Label Action if needed.
                if (oppositeAction != null) {
                    oppositeAction.setEnabled(oppositeAction.isEnabled());
                }
                setEnabled(isEnabled());
            }
        }
    }

    private Map<EObject, List<Integer>> collectLabelsToHideVisualIds(final Set<EObject> partsToSemantic, final List<Object> edgeLabelsEditParts) {
        for (EObject eObject : partsToSemantic) {
            semanticToLabelsVisualIDToHideMap.put(eObject, new LinkedList<>());
        }
        for (IGraphicalEditPart object : edgeLabelsEditParts.stream().filter(IGraphicalEditPart.class::isInstance).map(IGraphicalEditPart.class::cast).collect(Collectors.toSet())) {

            final EObject element = object.resolveSemanticElement();
            if (object instanceof DEdgeBeginNameEditPart) {
                semanticToLabelsVisualIDToHideMap.get(element).add(DEdgeBeginNameEditPart.VISUAL_ID);
            } else if (object instanceof DEdgeNameEditPart) {
                semanticToLabelsVisualIDToHideMap.get(element).add(DEdgeNameEditPart.VISUAL_ID);
            } else if (object instanceof DEdgeEndNameEditPart) {
                semanticToLabelsVisualIDToHideMap.get(element).add(DEdgeEndNameEditPart.VISUAL_ID);
            }
        }
        return semanticToLabelsVisualIDToHideMap;
    }

    private boolean containsEdgeLabelsInSelection(final List<Object> asList) {
        for (Object object : asList) {
            if (object instanceof AbstractDEdgeNameEditPart) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param minimizedSelection
     */
    private void runForNoEditPartSelection(final Set<Object> minimizedSelection) {
        final Set<EObject> eObjectSelection = new HashSet<EObject>();
        final Iterator<Object> it = minimizedSelection.iterator();
        semanticToLabelsVisualIDToHideMap.clear();
        while (it.hasNext()) {
            final Object obj = it.next();
            if (isEnabledPredicate.apply(obj)) {
                if (obj instanceof EObject) {
                    eObjectSelection.add((EObject) obj);
                } else if (obj instanceof AbstractDDiagramElementLabelItemProvider && ((AbstractDDiagramElementLabelItemProvider) obj).getDiagramElementTarget().some()) {
                    DDiagramElement dDiagramElement = ((AbstractDDiagramElementLabelItemProvider) obj).getDiagramElementTarget().get();
                    eObjectSelection.add(dDiagramElement);
                    if (obj instanceof DEdgeBeginLabelItemProvider || obj instanceof DEdgeLabelItemProvider || obj instanceof DEdgeEndLabelItemProvider) {
                        if (!semanticToLabelsVisualIDToHideMap.keySet().contains(dDiagramElement)) {
                            semanticToLabelsVisualIDToHideMap.put(dDiagramElement, new LinkedList<>());
                        }
                        if (obj instanceof DEdgeBeginLabelItemProvider) {
                            semanticToLabelsVisualIDToHideMap.get(dDiagramElement).add(DEdgeBeginNameEditPart.VISUAL_ID);
                        } else if (obj instanceof DEdgeLabelItemProvider) {
                            semanticToLabelsVisualIDToHideMap.get(dDiagramElement).add(DEdgeNameEditPart.VISUAL_ID);
                        } else if (obj instanceof DEdgeEndLabelItemProvider) {
                            semanticToLabelsVisualIDToHideMap.get(dDiagramElement).add(DEdgeEndNameEditPart.VISUAL_ID);
                        }
                    }
                }
            }
        }
        run(eObjectSelection);
    }

    /**
     * Empty. {@inheritDoc} Used from button of the tabbar.
     * 
     * @see org.eclipse.jface.action#run(org.eclipse.jface.action)
     */
    @Override
    public void run() {
        this.selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
        run(this);
    }

    private void run(final Set<EObject> minimizedSelection) {
        if (this.selection instanceof DiagramOutlinePage.TreeSelectionWrapper) {

            final DiagramOutlinePage.TreeSelectionWrapper wrapper = (DiagramOutlinePage.TreeSelectionWrapper) this.selection;

            final RootEditPart root = wrapper.getRoot();
            final DDiagramEditor diagramEditor = (DDiagramEditor) wrapper.getViewer().getProperty(DDiagramEditor.EDITOR_ID);

            runHideLabelCommand(root, diagramEditor, minimizedSelection);
        }
    }

    private void runHideLabelCommand(final RootEditPart root, final DDiagramEditor editor, final Set<EObject> elements) {

        final Object adapter = editor.getAdapter(IDiagramCommandFactoryProvider.class);
        final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(editor.getEditingDomain().getResourceSet());
        final IDiagramCommandFactory emfCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);
        final Command cmd;
        if (semanticToLabelsVisualIDToHideMap.isEmpty()) {
            cmd = emfCommandFactory.buildHideLabelCommand(elements);
        } else {
            cmd = emfCommandFactory.buildHideLabelSelectionCommand(elements, semanticToLabelsVisualIDToHideMap);
        }

        ((TransactionalEditingDomain) editor.getAdapter(EditingDomain.class)).getCommandStack().execute(cmd);
    }

    private void runHideLabelSelectionCommand(final RootEditPart root, final DDiagramEditor editor, final Set<EObject> elements, final Map<EObject, List<Integer>> selectedLabelVisualIds) {

        final Object adapter = editor.getAdapter(IDiagramCommandFactoryProvider.class);
        final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(editor.getEditingDomain().getResourceSet());
        final IDiagramCommandFactory emfCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);
        final Command cmd = emfCommandFactory.buildHideLabelSelectionCommand(elements, selectedLabelVisualIds);

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
        this.selection = s;
        this.setEnabled(true);
        if (s instanceof DiagramOutlinePage.TreeSelectionWrapper) {
            // Action of the outline
            this.setEnabled(HideDDiagramElementLabelAction.isEnabled(((DiagramOutlinePage.TreeSelectionWrapper) s).toList()));
        } else if (s instanceof IStructuredSelection) {
            // Action of the tabbar or of the contextual menu
            this.setEnabled(HideDDiagramElementLabelAction.isEnabled(((IStructuredSelection) s).toList()));
        }
    }

    @Override
    public void dispose() {
        selection = null;
        this.oppositeAction = null;
    }

    /**
     * Set the opposite Show Label action.
     * 
     * @param revealOutlineLabelsAction
     *            the Show Label action.
     */
    public void setOppositeRevealAction(TabbarRevealLabelsAction revealOutlineLabelsAction) {
        this.oppositeAction = revealOutlineLabelsAction;
    }
}

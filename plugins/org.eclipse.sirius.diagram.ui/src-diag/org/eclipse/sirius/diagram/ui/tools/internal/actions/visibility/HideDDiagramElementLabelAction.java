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
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.business.api.provider.AbstractDDiagramElementLabelItemProvider;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DiagramOutlinePage;
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
                    result = HideDDiagramElementLabelAction.isEnabled(optionTarget.get());
                }
            }
            return result;
        }
    };

    /** The selection. */
    private ISelection selection;

    private TabbarRevealLabelsAction oppositeAction;

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
        DDiagramElementQuery query = new DDiagramElementQuery(diagramElement);
        return query.canHideLabel() && !query.isLabelHidden();
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

                    runHideLabelCommand(root, diagramEditor, partsToSemantic(Arrays.asList(structuredSelection.toArray())));
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

    /**
     * @param minimizedSelection
     */
    private void runForNoEditPartSelection(final Set<Object> minimizedSelection) {
        final Set<EObject> eObjectSelection = new HashSet<EObject>();
        final Iterator<Object> it = minimizedSelection.iterator();
        while (it.hasNext()) {
            final Object obj = it.next();
            if (isEnabledPredicate.apply(obj)) {
                if (obj instanceof EObject) {
                    eObjectSelection.add((EObject) obj);
                } else if (obj instanceof AbstractDDiagramElementLabelItemProvider) {
                    Option<DDiagramElement> optionTarget = ((AbstractDDiagramElementLabelItemProvider) obj).getDiagramElementTarget();
                    if (optionTarget.some()) {
                        eObjectSelection.add(optionTarget.get());
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
        final Command cmd = emfCommandFactory.buildHideLabelCommand(elements);

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

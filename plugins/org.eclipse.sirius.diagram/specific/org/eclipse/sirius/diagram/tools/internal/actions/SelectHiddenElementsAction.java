/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.actions;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.DDiagramElement;
import org.eclipse.sirius.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.sirius.diagram.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.tools.internal.actions.visibility.HideDDiagramElementAction;
import org.eclipse.sirius.diagram.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.DiagramElementsSelectionDialog;
import org.eclipse.sirius.ui.business.api.provider.AbstractDDiagramElementLabelItemProvider;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;

/**
 * Action to open a dialog box where the user can select/deselect the diagram
 * elements which should be hidden.
 * 
 * @author pcdavid
 */
public class SelectHiddenElementsAction extends DiagramAction {

    private final class HiddenElementsSelectionCommand extends AbstractTransactionalCommand {
        private final DDiagram diagram;

        private HiddenElementsSelectionCommand(TransactionalEditingDomain domain, String label, DDiagram diagram) {
            super(domain, label, null);
            this.diagram = diagram;
        }

        @Override
        protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
            DiagramElementsSelectionDialog dlg = new DiagramElementsSelectionDialog(TITLE, MESSAGE);
            dlg.setSelectionPredicate(isVisible);
            dlg.setSelectedAction(revealElement);
            dlg.setDeselectedAction(hideElement);
            dlg.setGrayedPredicate(getNonSelectablePredicate());
            boolean executed = dlg.open(getShell(), diagram, true);
            if (executed) {
                return CommandResult.newOKCommandResult();
            } else {
                return CommandResult.newCancelledCommandResult();
            }
        }

        private Predicate<Object> getNonSelectablePredicate() {
            final Predicate<DDiagramElement> allowsHideReveal = HideDDiagramElementAction.allowsHideReveal(diagram);
            if (Predicates.alwaysTrue().equals(allowsHideReveal)) {
                return Predicates.alwaysFalse();
            }

            return new Predicate<Object>() {
                public boolean apply(Object input) {
                    if (input instanceof DDiagramElement) {
                        return !allowsHideReveal.apply((DDiagramElement) input);
                    }
                    return false;
                }
            };
        }
    }

    private static final String TITLE = "Diagram elements visibility";

    private static final String MESSAGE = "Visible diagram elements are checked.";

    private static final String TOOLTIP = "Show/Hide";

    private static final String ICON_PATH = "icons/categoryWizard.gif";

    /** The hide icon descriptor. */
    private static final ImageDescriptor DESC_HIDE = SiriusDiagramEditorPlugin.getBundledImageDescriptor(ICON_PATH);

    private final Predicate<Object> isVisible = new Predicate<Object>() {
        public boolean apply(Object input) {
            boolean result = false;
            if (input instanceof DDiagramElement) {
                result = !(new DDiagramElementQuery((DDiagramElement) input).isHidden());
            } else if (input instanceof AbstractDDiagramElementLabelItemProvider) {
                Option<DDiagramElement> optionTarget = ((AbstractDDiagramElementLabelItemProvider) input).getDiagramElementTarget();
                if (optionTarget.some()) {
                    result = !(new DDiagramElementQuery(optionTarget.get()).isLabelHidden());
                }
            }
            return result;
        }
    };

    private final Function<Object, Void> hideElement = new Function<Object, Void>() {
        public Void apply(Object from) {
            if (from instanceof DDiagramElement) {
                HideFilterHelper.INSTANCE.hide((DDiagramElement) from);
            } else if (from instanceof AbstractDDiagramElementLabelItemProvider) {
                Option<DDiagramElement> optionTarget = ((AbstractDDiagramElementLabelItemProvider) from).getDiagramElementTarget();
                if (optionTarget.some()) {
                    HideFilterHelper.INSTANCE.hideLabel(optionTarget.get());
                }
            }
            return null;
        }
    };

    private final Function<Object, Void> revealElement = new Function<Object, Void>() {
        public Void apply(Object from) {
            if (from instanceof DDiagramElement) {
                HideFilterHelper.INSTANCE.reveal((DDiagramElement) from);
            } else if (from instanceof AbstractDDiagramElementLabelItemProvider) {
                HideFilterHelper.INSTANCE.revealLabel((DDiagramElement) ((AbstractDDiagramElementLabelItemProvider) from).getTarget());
            }
            return null;
        }
    };

    /**
     * Constructor.
     * 
     * @param workbenchPage
     *            the workbench page.
     */
    public SelectHiddenElementsAction(IWorkbenchPage workbenchPage) {
        super(workbenchPage);
        setText(TOOLTIP);
        setId(ActionIds.SELECT_HIDDEN_ELEMENTS);
        setToolTipText(TOOLTIP);
        setImageDescriptor(DESC_HIDE);
    }

    /**
     * Constructor.
     * 
     * @param workbenchPage
     *            the workbench page.
     * @param part
     *            the workbench part.
     */
    public SelectHiddenElementsAction(IWorkbenchPage workbenchPage, IDiagramWorkbenchPart part) {
        super(workbenchPage);
        setWorkbenchPart(part);
        setText(TOOLTIP);
        setId(ActionIds.SELECT_HIDDEN_ELEMENTS);
        setToolTipText(TOOLTIP);
        setImageDescriptor(getImage());
    }

    /**
     * Get the decorated image
     */
    private ImageDescriptor getImage() {
        if (getWorkbenchPart() instanceof DDiagramEditorImpl) {
            final Diagram gmfDiagram = ((DDiagramEditorImpl) getWorkbenchPart()).getDiagram();
            if (gmfDiagram != null) {
                EObject diagram = gmfDiagram.getElement();
                if (diagram instanceof DDiagram) {
                    if (!((DDiagram) diagram).getHiddenElements().isEmpty()) {
                        return SiriusDiagramEditorPlugin.getDecoratedCheckedImageDescriptor(DESC_HIDE);
                    }
                }
            }
        }
        return DESC_HIDE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Request createTargetRequest() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isSelectionListener() {
        return false;
    }

    private Shell getShell() {
        return getWorkbenchPart().getSite().getShell();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getCommand() {
        Command result = UnexecutableCommand.INSTANCE;
        // Avoid NPE in getDiagramEditPart when diagramGraphialViewer is not
        // ready.
        if (getDiagramGraphicalViewer() != null) {
            DiagramEditPart diagramEditPart = getDiagramEditPart();
            if (diagramEditPart instanceof IDDiagramEditPart) {
                result = getElementsSelectionCommand((IDDiagramEditPart) diagramEditPart);
            }
        }
        return result;
    }

    private Command getElementsSelectionCommand(IDDiagramEditPart diagramEditPart) {
        EObject semanticElement = diagramEditPart.resolveSemanticElement();
        if (semanticElement instanceof DDiagram) {
            DDiagram diagram = (DDiagram) semanticElement;
            return new ICommandProxy(new HiddenElementsSelectionCommand(diagramEditPart.getEditingDomain(), TOOLTIP, diagram));
        } else {
            return UnexecutableCommand.INSTANCE;
        }
    }

    @Override
    protected boolean calculateEnabled() {
        boolean canEditInstance = true;
        if (getWorkbenchPart() instanceof DDiagramEditor && ((DDiagramEditor) getWorkbenchPart()).getRepresentation() instanceof DDiagram) {
            final DDiagramEditor editor = (DDiagramEditor) getWorkbenchPart();
            final DDiagram editorDiagram = (DDiagram) editor.getRepresentation();
            IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(editor.getSession().getSessionResource().getResourceSet());
            canEditInstance = permissionAuthority.canEditInstance(editorDiagram);
        }
        return canEditInstance && super.calculateEnabled();
    }
}

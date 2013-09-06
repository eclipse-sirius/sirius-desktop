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

import java.util.Iterator;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.DDiagramElement;
import org.eclipse.sirius.diagram.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.sirius.diagram.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.diagram.ui.tools.api.layout.PinHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.DiagramElementsSelectionDialog;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;

/**
 * Action to open a dialog box where the user can select/deselect the diagram
 * elements which should be pinned.
 * 
 * @author pcdavid
 */
public class SelectPinnedElementsAction extends DiagramAction {
    private final class PinnedElementsSelectionCommand extends AbstractTransactionalCommand {
        private final DDiagram diagram;

        private PinnedElementsSelectionCommand(TransactionalEditingDomain domain, String label, DDiagram diagram) {
            super(domain, label, null);
            this.diagram = diagram;
        }

        @Override
        protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
            DiagramElementsSelectionDialog dlg = new DiagramElementsSelectionDialog(TITLE, MESSAGE);
            dlg.setSelectionPredicate(isPinned);
            dlg.setSelectedAction(pinElement);
            dlg.setDeselectedAction(unpinElement);
            dlg.setGrayedPredicate(getNonSelectablePredicate());
            boolean executed = dlg.open(getShell(), diagram, false);
            if (executed) {
                return CommandResult.newOKCommandResult();
            } else {
                return CommandResult.newCancelledCommandResult();
            }
        }

        private Predicate<Object> getNonSelectablePredicate() {
            final Predicate<DDiagramElement> allowsPinUnpin = PinHelper.allowsPinUnpin(diagram);

            if (Predicates.alwaysTrue().equals(allowsPinUnpin)) {
                return Predicates.alwaysFalse();
            }

            return new Predicate<Object>() {
                public boolean apply(Object input) {
                    if (input instanceof DDiagramElement) {
                        return !allowsPinUnpin.apply((DDiagramElement) input);
                    }
                    return false;
                }
            };
        }
    }

    private static final String ACTION_ID = "selectPinnedElementsAction";

    private static final String TITLE = "Diagram elements pinning";

    private static final String MESSAGE = "Pinned diagram elements are checked.";

    private static final String TOOLTIP = "Pin/Unpin";

    private static final String ICON_PATH = "icons/pinWizard.gif";

    /** The pin icon descriptor. */
    private static final ImageDescriptor DESC_PIN = SiriusDiagramEditorPlugin.getBundledImageDescriptor(ICON_PATH);

    private final Predicate<Object> isPinned = new Predicate<Object>() {
        public boolean apply(Object input) {
            if (input instanceof DDiagramElement) {
                return new PinHelper().isPinned((DDiagramElement) input);
            }
            return false;
        }
    };

    private final Function<Object, Void> pinElement = new Function<Object, Void>() {
        public Void apply(Object from) {
            if (from instanceof DDiagramElement) {
                new PinHelper().markAsPinned((DDiagramElement) from);
            }
            return null;
        }
    };

    private final Function<Object, Void> unpinElement = new Function<Object, Void>() {
        public Void apply(Object from) {
            if (from instanceof DDiagramElement) {
                new PinHelper().markAsUnpinned((DDiagramElement) from);
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
    public SelectPinnedElementsAction(IWorkbenchPage workbenchPage) {
        super(workbenchPage);
        setText(TITLE);
        setToolTipText(TOOLTIP);
        setId(ACTION_ID);
        setImageDescriptor(SiriusDiagramEditorPlugin.getDecoratedCheckedImageDescriptor(DESC_PIN));
    }

    /**
     * Constructor.
     * 
     * @param workbenchPage
     *            the workbench page.
     * @param part
     *            the workbench part.
     */
    public SelectPinnedElementsAction(IWorkbenchPage workbenchPage, IWorkbenchPart part) {
        super(workbenchPage);
        setWorkbenchPart(part);
        setText(TITLE);
        setToolTipText(TOOLTIP);
        setId(ACTION_ID);
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
                    if (hasPinnedElements((DDiagram) diagram)) {
                        return SiriusDiagramEditorPlugin.getDecoratedCheckedImageDescriptor(DESC_PIN);
                    }
                }
            }
        }
        return DESC_PIN;
    }

    private boolean hasPinnedElements(DDiagram dDiagram) {
        Iterator<DDiagramElement> iterator = dDiagram.getDiagramElements().iterator();
        PinHelper pinHelper = new PinHelper();
        while (iterator.hasNext()) {
            final DDiagramElement element = iterator.next();
            if (pinHelper.isPinned(element)) {
                return true;
            }
        }
        return false;
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
            return new ICommandProxy(new PinnedElementsSelectionCommand(diagramEditPart.getEditingDomain(), TITLE, diagram));
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
            Resource sessionResource = editor.getSession().getSessionResource();
            if (sessionResource != null) {
                IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(sessionResource.getResourceSet());
                canEditInstance = permissionAuthority.canEditInstance(editorDiagram);
            }
        }
        return canEditInstance && super.calculateEnabled();
    }
}

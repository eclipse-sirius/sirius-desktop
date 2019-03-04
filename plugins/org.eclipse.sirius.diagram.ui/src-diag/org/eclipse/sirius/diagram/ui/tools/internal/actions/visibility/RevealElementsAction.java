/*******************************************************************************
 * Copyright (c) 2009, 2019 THALES GLOBAL SERVICES.
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

import java.util.Iterator;
import java.util.Optional;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DiagramOutlinePage;

/**
 * Action to reveal elements in outline and diagram (both contextual menu and tabbar).
 * 
 * @author dlecan
 */
public class RevealElementsAction extends AbstractRevealElementsAction<Object> {

    /**
     * Constructor.
     */
    public RevealElementsAction() {
        super(Messages.RevealOutlineElementsAction_label);
    }

    /**
     * Constructor.
     * 
     * @param text the label
     */
    public RevealElementsAction(final String text) {
        super(text);
    }

    /**
     * Tests whether this action should be active. The action is active if the given {@link IDiagramElementEditPart} is
     * hidden.
     * 
     * @param selectedElement
     *            The current selection
     * @return true if the selected element is hidden.
     */
    public static boolean isActive(IDiagramElementEditPart selectedElement) {
        boolean result = true;
        DDiagramElement diagramElement = selectedElement.resolveDiagramElement();
        if (diagramElement == null) {
            result = false;
        } else {
            result = !diagramElement.isVisible();
        }
        return result;
    }

    /**
     * Tests whether this action should be active. The action is active if the given selection contains at least one
     * hidden diagram graphical element.
     * 
     * @param selectedElements
     *            The current selection
     * @return true if all selected elements are hidden.
     */
    public static boolean isActive(IStructuredSelection selectedElements) {
        final Iterator<?> iterator = selectedElements.iterator();
        while (iterator.hasNext()) {
            final Object selectedElement = iterator.next();
            if (selectedElement instanceof IDiagramElementEditPart && isActive((IDiagramElementEditPart) selectedElement)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean doRun(final Object element) {
        run(element);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<Object> getElementType() {
        return Object.class;
    }

    private void run(final Object vpe) {
        if (vpe instanceof DDiagramElement && this.selection instanceof DiagramOutlinePage.TreeSelectionWrapper) {
            final DiagramOutlinePage.TreeSelectionWrapper wrapper = (DiagramOutlinePage.TreeSelectionWrapper) this.selection;
            final RootEditPart root = wrapper.getRoot();
            final DDiagramEditor diagramEditor = (DDiagramEditor) wrapper.getViewer().getProperty(DDiagramEditor.EDITOR_ID);
            runRevealCommand(root, diagramEditor, (DDiagramElement) vpe);
        } else if (vpe instanceof IDiagramElementEditPart) {
            if (isActive((IDiagramElementEditPart) vpe)) {
                Optional<DDiagramElement> optionalDiagramElement = Optional.of((IGraphicalEditPart) vpe).map(IGraphicalEditPart::resolveSemanticElement).filter(DDiagramElement.class::isInstance)
                        .map(DDiagramElement.class::cast);
                if (optionalDiagramElement.isPresent()) {
                    IDiagramElementEditPart diagramElementEditPart = (IDiagramElementEditPart) vpe;
                    SelectionRequest request = new SelectionRequest();
                    request.setType(RequestConstants.REQ_OPEN);
                    diagramElementEditPart.performRequest(request);
                }
            }
        }
    }

    private void runRevealCommand(final RootEditPart root, final DDiagramEditor editor, final DDiagramElement vpe) {

        final Object adapter = editor.getAdapter(IDiagramCommandFactoryProvider.class);
        final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(editor.getEditingDomain().getResourceSet());
        final IDiagramCommandFactory emfCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);
        final Command cmd = emfCommandFactory.buildRevealCommand(vpe);

        CompoundCommand allInOne = new CompoundCommand(cmd.getLabel());
        allInOne.append(cmd);

        transactionalEditingDomain.getCommandStack().execute(allInOne);
    }
}

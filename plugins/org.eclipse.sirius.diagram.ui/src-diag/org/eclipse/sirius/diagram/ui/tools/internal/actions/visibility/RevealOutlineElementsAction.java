/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.RootEditPart;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DiagramOutlinePage;

/**
 * Action to reveal elements in outline.
 * 
 * @author dlecan
 */
public class RevealOutlineElementsAction extends AbstractRevealElementsAction<DDiagramElement> {
    /**
     * Constructor.
     */
    public RevealOutlineElementsAction() {
        super(Messages.RevealOutlineElementsAction_label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean doRun(final DDiagramElement element) {
        run(element);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<DDiagramElement> getElementType() {
        return DDiagramElement.class;
    }

    private void run(final DDiagramElement vpe) {
        if (this.selection instanceof DiagramOutlinePage.TreeSelectionWrapper) {

            final DiagramOutlinePage.TreeSelectionWrapper wrapper = (DiagramOutlinePage.TreeSelectionWrapper) this.selection;

            final RootEditPart root = wrapper.getRoot();
            final DDiagramEditor diagramEditor = (DDiagramEditor) wrapper.getViewer().getProperty(DDiagramEditor.EDITOR_ID);

            runRevealCommand(root, diagramEditor, vpe);
        }
    }

    private void runRevealCommand(final RootEditPart root, final DDiagramEditor editor, final DDiagramElement vpe) {

        final Object adapter = editor.getAdapter(IDiagramCommandFactoryProvider.class);
        final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(editor.getEditingDomain().getResourceSet());
        final IDiagramCommandFactory emfCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);
        final Command cmd = emfCommandFactory.buildRevealCommand(vpe);

        final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(vpe);

        CompoundCommand allInOne = new CompoundCommand(cmd.getLabel());
        allInOne.append(cmd);

        domain.getCommandStack().execute(allInOne);
    }
}

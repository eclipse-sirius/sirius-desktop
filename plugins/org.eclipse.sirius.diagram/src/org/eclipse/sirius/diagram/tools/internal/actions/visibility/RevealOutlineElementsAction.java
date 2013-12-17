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
package org.eclipse.sirius.diagram.tools.internal.actions.visibility;

import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.tools.internal.editor.DiagramOutlinePage;
import org.eclipse.sirius.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.tools.api.command.IDiagramCommandFactoryProvider;

/**
 * Action to reveal elements in outline.
 * 
 * @author dlecan
 */
public class RevealOutlineElementsAction extends AbstractRevealElementsAction<DDiagramElement> {

    /**
     * Action label.
     */
    public static final String REVEAL_ELEMENT_LABEL = "Show element";

    /**
     * Constructor.
     */
    public RevealOutlineElementsAction() {
        super(REVEAL_ELEMENT_LABEL);
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

    /**
     * Specific command to reveal elements from outline.
     * 
     * @author mporhel
     */
    private static class RevealOutlineElementsCommand extends RecordingCommand {

        private final RootEditPart root;

        /**
         * Constructor.
         * 
         * @param domain
         *            the editing domain
         * @param root
         */
        public RevealOutlineElementsCommand(final TransactionalEditingDomain domain, RootEditPart root) {
            super(domain, "Reveal elements from outline");
            this.root = root;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void doExecute() {
            refreshChild(root);
        }

        /**
         * Refresh all the childs.
         * 
         * @param part
         *            parent {@link EditPart}
         */
        private void refreshChild(final EditPart part) {
            part.refresh();
            final Iterator<?> it = part.getChildren().iterator();
            while (it.hasNext()) {
                final Object obj = it.next();
                if (obj instanceof GraphicalEditPart) {
                    refreshChild((GraphicalEditPart) obj);
                }
            }
        }
    }
}

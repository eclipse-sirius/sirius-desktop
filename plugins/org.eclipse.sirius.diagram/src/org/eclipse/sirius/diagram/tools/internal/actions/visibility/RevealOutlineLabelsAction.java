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
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.diagram.ImagesPath;
import org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.sirius.diagram.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.tools.internal.editor.DiagramOutlinePage;
import org.eclipse.sirius.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.ui.business.api.provider.AbstractDDiagramElementLabelItemProvider;
import org.eclipse.sirius.viewpoint.DDiagramElement;

/**
 * Action to reveal labels in outline.
 * 
 * @author lredor
 */
public class RevealOutlineLabelsAction extends AbstractRevealElementsAction<Object> {

    /**
     * Action label.
     */
    public static final String REVEAL_LABEL_LABEL = "Show label";

    /**
     * Constructor.
     */
    public RevealOutlineLabelsAction() {
        this(REVEAL_LABEL_LABEL);
    }

    /**
     * Constructor.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     */
    public RevealOutlineLabelsAction(final String text) {
        this(text, SiriusDiagramEditorPlugin.getBundledImageDescriptor(ImagesPath.REVEAL_LABEL_IMG));
    }

    /**
     * Constructor.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     * @param image
     *            the action's image, or <code>null</code> if there is no image
     */
    public RevealOutlineLabelsAction(final String text, final ImageDescriptor image) {
        super(text, image);
        setId(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean doRun(final Object element) {
        if (element instanceof DDiagramElement) {
            run((DDiagramElement) element);
        } else if (element instanceof AbstractDDiagramElementLabelItemProvider) {
            Option<DDiagramElement> optionTarget = ((AbstractDDiagramElementLabelItemProvider) element).getDiagramElementTarget();
            if (optionTarget.some()) {
                run(optionTarget.get());
            }
        } else if (element instanceof IDiagramElementEditPart) {
            IDiagramElementEditPart diagramElementEditPart = (IDiagramElementEditPart) element;
            RootEditPart root = diagramElementEditPart.getRoot();
            final DDiagramEditor diagramEditor = (DDiagramEditor) root.getViewer().getProperty(DDiagramEditor.EDITOR_ID);
            runRevealCommand(root, diagramEditor, diagramElementEditPart.resolveDiagramElement());
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<Object> getElementType() {
        return Object.class;
    }

    private void run(final DDiagramElement diagramElement) {
        if (this.selection instanceof DiagramOutlinePage.TreeSelectionWrapper) {

            final DiagramOutlinePage.TreeSelectionWrapper wrapper = (DiagramOutlinePage.TreeSelectionWrapper) this.selection;

            final RootEditPart root = wrapper.getRoot();
            final DDiagramEditor diagramEditor = (DDiagramEditor) wrapper.getViewer().getProperty(DDiagramEditor.EDITOR_ID);

            runRevealCommand(root, diagramEditor, diagramElement);
        }
    }

    private void runRevealCommand(final RootEditPart root, final DDiagramEditor editor, final DDiagramElement vpe) {

        final Object adapter = editor.getAdapter(IDiagramCommandFactoryProvider.class);
        final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(editor.getEditingDomain().getResourceSet());
        final IDiagramCommandFactory emfCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);

        final Command cmd = emfCommandFactory.buildRevealLabelCommand(vpe);

        final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(vpe);

        CompoundCommand allInOne = new CompoundCommand(cmd.getLabel());
        allInOne.append(cmd);

        domain.getCommandStack().execute(allInOne);
    }

    /**
     * Specific command to reveal elements from outline.
     * 
     * @author lredor
     */
    private static class RevealOutlineLabelsCommand extends RecordingCommand {

        private final RootEditPart root;

        /**
         * Constructor.
         * 
         * @param domain
         *            the editing domain
         * @param root
         */
        public RevealOutlineLabelsCommand(final TransactionalEditingDomain domain, RootEditPart root) {
            super(domain, "Reveal labels");
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
         * Refresh all the children.
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

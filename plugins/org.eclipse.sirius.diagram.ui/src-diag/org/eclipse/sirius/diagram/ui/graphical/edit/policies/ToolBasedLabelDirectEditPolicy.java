/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy;
import org.eclipse.gmf.runtime.gef.ui.internal.parts.TextCellEditorEx;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

/**
 * {@link org.eclipse.gef.EditPolicy} used to handle Labels direct edits.
 * 
 * @author cbrun
 * 
 */
public class ToolBasedLabelDirectEditPolicy extends LabelDirectEditPolicy {

    private TransactionalEditingDomain domain;

    /**
     * Create a new {@link org.eclipse.gef.EditPolicy}.
     * 
     * @param domain
     *            current {@link org.eclipse.emf.edit.domain.EditingDomain}.
     */
    public ToolBasedLabelDirectEditPolicy(final TransactionalEditingDomain domain) {
        this.domain = domain;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
     */
    @Override
    protected Command getDirectEditCommand(final DirectEditRequest edit) {
        if (edit.getCellEditor() instanceof TextCellEditorEx) {
            if (!((TextCellEditorEx) edit.getCellEditor()).hasValueChanged()) {
                return null;
            }
        }

        final String labelText = (String) edit.getCellEditor().getValue();

        Command cmd = null;

        // for CellEditor, null is always returned for invalid values
        if (labelText != null) {
            if (getHost() instanceof GraphicalEditPart) {
                final EObject element = ((GraphicalEditPart) getHost()).resolveSemanticElement();
                if (element instanceof DRepresentationElement && element instanceof DMappingBased) {
                    DirectEditLabel directEditTool = getDirectEditTool((DMappingBased) element);
                    if (directEditTool != null) {
                        final DRepresentationElement repElement = (DRepresentationElement) element;
                        final DDiagramEditor diagramEditor = (DDiagramEditor) this.getHost().getViewer().getProperty(DDiagramEditor.EDITOR_ID);
                        final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
                        final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
                        final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(element);
                        final IDiagramCommandFactory diagramCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);
                        final org.eclipse.emf.common.command.Command command = diagramCommandFactory.buildDirectEditLabelFromTool(repElement, directEditTool, labelText);
                        final CompoundCommand cc = new CompoundCommand();
                        cc.add(new ICommandProxy(new GMFCommandWrapper(domain, command)));
                        cmd = cc;
                    }
                }
            }
        }
        if (cmd == null) {
            cmd = new ICommandProxy(new GMFCommandWrapper(domain, UnexecutableCommand.INSTANCE));
        }
        return cmd;
    }

    private DirectEditLabel getDirectEditTool(final DMappingBased mappingBased) {
        if (mappingBased.getMapping() != null && mappingBased instanceof DDiagramElement) {
            return ((DDiagramElement) mappingBased).getDiagramElementMapping().getLabelDirectEdit();
        }
        return null;
    }

}

/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.RootEditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.business.api.provider.AbstractDDiagramElementLabelItemProvider;
import org.eclipse.sirius.diagram.ui.business.api.provider.DEdgeBeginLabelItemProvider;
import org.eclipse.sirius.diagram.ui.business.api.provider.DEdgeEndLabelItemProvider;
import org.eclipse.sirius.diagram.ui.business.api.provider.DEdgeLabelItemProvider;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DiagramOutlinePage;

/**
 * Action to reveal labels in outline.
 * 
 * @author lredor
 */
public class RevealOutlineLabelsAction extends AbstractRevealElementsAction<Object> {

    private Map<EObject, List<Integer>> semanticToLabelsVisualIDToHideMap = new HashMap<EObject, List<Integer>>();

    /**
     * Constructor.
     */
    public RevealOutlineLabelsAction() {
        this(Messages.RevealOutlineLabelsAction_label);
    }

    /**
     * Constructor.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     */
    public RevealOutlineLabelsAction(final String text) {
        this(text, DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.REVEAL_LABEL_IMG));
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
        } else if (element instanceof AbstractDDiagramElementLabelItemProvider && ((AbstractDDiagramElementLabelItemProvider) element).getDiagramElementTarget().some()) {
            semanticToLabelsVisualIDToHideMap.clear();
            DDiagramElement dDiagramElement = ((AbstractDDiagramElementLabelItemProvider) element).getDiagramElementTarget().get();
            if (!semanticToLabelsVisualIDToHideMap.keySet().contains(dDiagramElement)) {
                semanticToLabelsVisualIDToHideMap.put(dDiagramElement, new LinkedList<>());
            }
            List<Integer> visualIDToHideList = semanticToLabelsVisualIDToHideMap.get(dDiagramElement);
            if (element instanceof DEdgeBeginLabelItemProvider) {
                visualIDToHideList.add(DEdgeBeginNameEditPart.VISUAL_ID);
            } else if (element instanceof DEdgeLabelItemProvider) {
                visualIDToHideList.add(DEdgeNameEditPart.VISUAL_ID);
            } else if (element instanceof DEdgeEndLabelItemProvider) {
                visualIDToHideList.add(DEdgeEndNameEditPart.VISUAL_ID);
            }
            run(dDiagramElement);
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

        if (vpe != null && new DDiagramElementQuery(vpe).hasAnyHiddenLabel()) {
            final Object adapter = editor.getAdapter(IDiagramCommandFactoryProvider.class);
            final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
            final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(editor.getEditingDomain().getResourceSet());
            final IDiagramCommandFactory emfCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);

            final Command cmd;
            if (semanticToLabelsVisualIDToHideMap.isEmpty()) {
                cmd = emfCommandFactory.buildRevealLabelCommand(vpe);
            } else {
                cmd = emfCommandFactory.buildRevealLabelSelectionCommand(vpe, semanticToLabelsVisualIDToHideMap);
            }

            final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(vpe);

            CompoundCommand allInOne = new CompoundCommand(cmd.getLabel());
            allInOne.append(cmd);

            domain.getCommandStack().execute(allInOne);
        }
    }
}

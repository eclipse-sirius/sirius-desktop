/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.commands.emf;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.common.ui.tools.api.selection.EObjectPaneBasedSelectionWizard;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.ImmutableSet;

/**
 * A command to display a selection wizard.
 * 
 * @author mchauvin
 */
public class PaneBasedSelectionWizardCommand extends AbstractSelectionWizardCommand {

    private final IDiagramCommandFactory factory;

    private final PaneBasedSelectionWizardDescription tool;

    private final TreeItemWrapper input;

    private final DSemanticDecorator containerView;

    /**
     * Default constructor.
     * 
     * @param factory
     *            the command factory.
     * @param tool
     *            the wizard description tool reference.
     * @param input
     *            the candidates objects to select in the wizard.
     * @param containerView
     *            the view of the container.
     */
    public PaneBasedSelectionWizardCommand(final IDiagramCommandFactory factory, final PaneBasedSelectionWizardDescription tool, final TreeItemWrapper input, final DSemanticDecorator containerView) {
        super(TransactionUtil.getEditingDomain(containerView));
        this.factory = factory;
        this.tool = tool;
        this.input = input;
        this.containerView = containerView;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    public void doExecute() {
        computeInput();
        final Collection<EObject> preSelection = computePreSelection();
        Shell shell = null;
        boolean createdShell = false;
        if (PlatformUI.getWorkbench() != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
            shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        }
        if (shell == null) {
            shell = new Shell();
            createdShell = true;
        }
        String windowTitleLocalized = MessageTranslator.INSTANCE.getMessage(this.tool, this.tool.getWindowTitle());
        String messageLocalized = MessageTranslator.INSTANCE.getMessage(this.tool, this.tool.getMessage());
        String choiceOfValuesMessageLocalized = MessageTranslator.INSTANCE.getMessage(this.tool, this.tool.getChoiceOfValuesMessage());
        String selectedValuesMessageLocalized = MessageTranslator.INSTANCE.getMessage(this.tool, this.tool.getSelectedValuesMessage());
        final EObjectPaneBasedSelectionWizard wizard = new EObjectPaneBasedSelectionWizard(windowTitleLocalized, messageLocalized, getImage(), choiceOfValuesMessageLocalized,
                selectedValuesMessageLocalized, DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory());
        wizard.init(input, preSelection);
        final WizardDialog dlg = new WizardDialog(shell, wizard);
        dlg.setMinimumPageSize(200, 300);
        final int result = dlg.open();
        if (result == Window.OK) {
            final Collection<EObject> selectedElements = wizard.getSelectedEObjects();
            final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(containerView.getTarget());
            // variables
            interpreter.setVariable(tool.getContainerView().getName(), containerView);
            Option<DDiagram> diagram = new EObjectQuery(containerView).getParentDiagram();
            if (diagram.some()) {
                interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram.get());
            } else {
                interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, null);
            }
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, containerView);
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, containerView.getTarget());

            final org.eclipse.emf.common.command.Command command = factory.buildPaneBasedSelectionWizardCommandFromTool(tool, containerView, selectedElements);
            command.execute();

            interpreter.unSetVariable(tool.getContainerView().getName());
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
        } else {
            if (containerView instanceof AbstractDNode) {
                SiriusLayoutDataManager.INSTANCE.getData((AbstractDNode) containerView);
            }
        }
        if (createdShell) {
            shell.dispose();
        }
    }

    private ImageDescriptor getImage() {
        if (StringUtil.isEmpty(tool.getWindowImagePath())) {
            return null;
        } else {
            return DiagramUIPlugin.Implementation.findImageDescriptor(tool.getWindowImagePath());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.command.AbstractCommand#getLabel()
     */
    @Override
    public String getLabel() {
        return this.tool.getName();
    }

    private void computeInput() {
        EObject container;
        container = containerView.getTarget();
        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(container);

        if (AbstractSelectionWizardCommand.checkPrecondition(tool, containerView, container)) {

            // variables
            interpreter.setVariable(tool.getContainerView().getName(), containerView);
            Option<DDiagram> diagram = new EObjectQuery(containerView).getParentDiagram();
            if (diagram.some()) {
                interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram.get());
            } else {
                interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, null);
            }
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, containerView);
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, container);

            PaneBasedSelectionWizardCommand.computeInput(tool, container, interpreter, input);

            interpreter.unSetVariable(tool.getContainerView().getName());
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);

        }

    }

    private Collection<EObject> computePreSelection() {
        EObject container;
        Collection<EObject> preSelection = Collections.<EObject> emptyList();
        container = containerView.getTarget();
        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(container);
        if (AbstractSelectionWizardCommand.checkPrecondition(tool, containerView, container)) {
            // variables
            interpreter.setVariable(tool.getContainerView().getName(), containerView);
            Option<DDiagram> diagram = new EObjectQuery(containerView).getParentDiagram();
            if (diagram.some()) {
                interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram.get());
            } else {
                interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, null);
            }
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, containerView);
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, container);

            preSelection = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateCollection(container, tool,
                    ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_PreSelectedCandidatesExpression());

            interpreter.unSetVariable(tool.getContainerView().getName());
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
        }
        return preSelection;
    }

    /**
     * Compute the TreeItemWrapper corresponding to this {@link PaneBasedSelectionWizardDescription}.
     * 
     * @param paneBasedSelectionWizardDescription
     *            the selection description
     * @param container
     *            the semantic element
     * @param interpreter
     *            the interpreter used to compute expressions of the selection description
     * @param input
     *            the TreeItemWrapper to complete
     */
    private static void computeInput(final PaneBasedSelectionWizardDescription paneBasedSelectionWizardDescription, final EObject container, final IInterpreter interpreter,
            final TreeItemWrapper input) {

        final Collection<EObject> referencingENode = ImmutableSet.copyOf(RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateCollection(container, paneBasedSelectionWizardDescription,
                ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_CandidatesExpression()));
        if (paneBasedSelectionWizardDescription.isTree()) {
            final Collection<EObject> referencingRoots = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateCollection(container, paneBasedSelectionWizardDescription,
                    ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_RootExpression());
            final Iterator<EObject> iterRoots = referencingRoots.iterator();
            while (iterRoots.hasNext()) {
                final EObject refRoot = iterRoots.next();
                if (referencingENode.contains(refRoot)) {
                    final TreeItemWrapper treeItem = new TreeItemWrapper(refRoot, input);
                    input.getChildren().add(treeItem);
                    PaneBasedSelectionWizardCommand.computeChildren(paneBasedSelectionWizardDescription, referencingENode, interpreter, treeItem, refRoot);
                }
            }
        } else {
            final Iterator<EObject> iterRoots = referencingENode.iterator();
            while (iterRoots.hasNext()) {
                final EObject refRoot = iterRoots.next();
                final TreeItemWrapper treeItem = new TreeItemWrapper(refRoot, input);
                input.getChildren().add(treeItem);
            }
        }
    }

    private static void computeChildren(final PaneBasedSelectionWizardDescription paneBasedSelectionWizardDescription, final Collection<EObject> referencingENode, final IInterpreter interpreter,
            final TreeItemWrapper parent, final EObject refParent) {
        final Collection<EObject> referencingChilds = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateCollection(refParent, paneBasedSelectionWizardDescription,
                ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_ChildrenExpression());
        final Iterator<EObject> iterchilds = referencingChilds.iterator();
        while (iterchilds.hasNext()) {
            final EObject refElement = iterchilds.next();
            if (referencingENode.contains(refElement) && !parent.knownThisAsAncestor(refElement)) {
                final TreeItemWrapper treeItem = new TreeItemWrapper(refElement, parent);
                parent.getChildren().add(treeItem);
                PaneBasedSelectionWizardCommand.computeChildren(paneBasedSelectionWizardDescription, referencingENode, interpreter, treeItem, refElement);
            }
        }
    }
}

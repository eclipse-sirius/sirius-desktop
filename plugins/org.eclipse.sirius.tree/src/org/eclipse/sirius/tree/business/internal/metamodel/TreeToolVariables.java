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
package org.eclipse.sirius.tree.business.internal.metamodel;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.tree.business.internal.helper.TreeHelper;
import org.eclipse.sirius.tree.description.DescriptionFactory;
import org.eclipse.sirius.tree.description.PrecedingSiblingsVariables;
import org.eclipse.sirius.tree.description.TreeCreationDescription;
import org.eclipse.sirius.tree.description.TreeItemContainerDropTool;
import org.eclipse.sirius.tree.description.TreeItemCreationTool;
import org.eclipse.sirius.tree.description.TreeItemDeletionTool;
import org.eclipse.sirius.tree.description.TreeItemEditionTool;
import org.eclipse.sirius.tree.description.TreeItemTool;
import org.eclipse.sirius.tree.description.TreeNavigationDescription;
import org.eclipse.sirius.tree.description.TreeVariable;
import org.eclipse.sirius.tree.description.util.DescriptionSwitch;
import org.eclipse.sirius.tree.tools.api.interpreter.IInterpreterSiriusTreeVariables;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementDropVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementSelectVariable;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.NameVariable;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;

/**
 * This processing switch will add the needed variable description in the tree
 * tools.
 * 
 * @author nlepine
 * 
 */
public class TreeToolVariables extends DescriptionSwitch<Object> {

    private void addVariableDescriptor(final TreeItemTool tool, final String name, final String documentation) {
        if (TreeHelper.getVariable(tool, name) == null) {
            final TreeVariable newVar = DescriptionFactory.eINSTANCE.createTreeVariable();
            newVar.setName(name);
            newVar.setDocumentation(documentation);
            tool.getVariables().add(newVar);
            final ModelOperation initialOperation = ToolFactory.eINSTANCE.createInitialOperation().getFirstModelOperations();
            tool.setFirstModelOperation(initialOperation);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.description.util.DescriptionSwitch#caseTreeItemDeletionTool(org.eclipse.sirius.tree.description.TreeItemDeletionTool)
     */
    @Override
    public Object caseTreeItemDeletionTool(TreeItemDeletionTool object) {
        addVariableDescriptor(object, IInterpreterSiriusVariables.ELEMENT, "The currently edited element.");
        addVariableDescriptor(object, IInterpreterSiriusVariables.ROOT, "The semantic element corresponding to the current tree.");
        return super.caseTreeItemDeletionTool(object);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Object caseTreeItemCreationTool(final TreeItemCreationTool object) {
        addVariableDescriptor(object, IInterpreterSiriusVariables.ROOT, "The semantic element of the tree.");
        addVariableDescriptor(object, IInterpreterSiriusVariables.ELEMENT, "The semantic currently edited element.");
        addVariableDescriptor(object, IInterpreterSiriusVariables.CONTAINER, "The semantic element corresponding to the view container.");
        return super.caseTreeItemCreationTool(object);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.description.util.DescriptionSwitch#caseTreeItemEditionTool(org.eclipse.sirius.tree.description.TreeItemEditionTool)
     */
    @Override
    public Object caseTreeItemEditionTool(final TreeItemEditionTool object) {
        object.setMask(ToolFactory.eINSTANCE.createEditMaskVariables());

        ElementDropVariable elementVariableForLabelEditTool = ToolFactory.eINSTANCE.createElementDropVariable();
        elementVariableForLabelEditTool.setName(IInterpreterSiriusVariables.ELEMENT);
        object.setElement(elementVariableForLabelEditTool);

        ElementDropVariable rootVariableForLabelEditTool = ToolFactory.eINSTANCE.createElementDropVariable();
        rootVariableForLabelEditTool.setName(IInterpreterSiriusVariables.ROOT);
        object.setRoot(rootVariableForLabelEditTool);
        return super.caseTreeItemEditionTool(object);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.description.util.DescriptionSwitch#caseTreeItemContainerDropTool(org.eclipse.sirius.tree.description.TreeItemContainerDropTool)
     */
    @Override
    public Object caseTreeItemContainerDropTool(final TreeItemContainerDropTool object) {
        DropContainerVariable oldContainerVariable = ToolFactory.eINSTANCE.createDropContainerVariable();
        oldContainerVariable.setName(IInterpreterSiriusVariables.CONTAINER_OLD);
        object.setOldContainer(oldContainerVariable);

        DropContainerVariable newContainerVariable = ToolFactory.eINSTANCE.createDropContainerVariable();
        newContainerVariable.setName(IInterpreterSiriusVariables.CONTAINER_NEW);
        object.setNewContainer(newContainerVariable);

        ElementDropVariable elementVariable = ToolFactory.eINSTANCE.createElementDropVariable();
        elementVariable.setName(IInterpreterSiriusVariables.ELEMENT);
        object.setElement(elementVariable);

        ContainerViewVariable newContainerView = ToolFactory.eINSTANCE.createContainerViewVariable();
        newContainerView.setName(IInterpreterSiriusVariables.CONTAINER_VIEW_NEW);
        object.setNewViewContainer(newContainerView);

        PrecedingSiblingsVariables precedingSiblings = DescriptionFactory.eINSTANCE.createPrecedingSiblingsVariables();
        precedingSiblings.setName(IInterpreterSiriusTreeVariables.PRECEDING_SIBLINGS);
        precedingSiblings.setDocumentation("The list of all the preceding siblings in a Drag and Drop operation");
        object.setPrecedingSiblings(precedingSiblings);

        return super.caseTreeItemContainerDropTool(object);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.description.util.DescriptionSwitch#caseTreeCreationDescription(org.eclipse.sirius.tree.description.TreeCreationDescription)
     */
    @Override
    public Object caseTreeCreationDescription(final TreeCreationDescription object) {
        final ContainerViewVariable containerViewVariable = ToolFactory.eINSTANCE.createContainerViewVariable();
        containerViewVariable.setName("containerView"); //$NON-NLS-1$
        object.setContainerViewVariable(containerViewVariable);
        final NameVariable treeNameVariable = ToolFactory.eINSTANCE.createNameVariable();
        treeNameVariable.setName("treeName"); //$NON-NLS-1$
        object.setRepresentationNameVariable(treeNameVariable);
        final InitialOperation initialOperation = ToolFactory.eINSTANCE.createInitialOperation();
        object.setInitialOperation(initialOperation);
        return super.caseTreeCreationDescription(object);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.description.util.DescriptionSwitch#caseTreeNavigationDescription(org.eclipse.sirius.tree.description.TreeNavigationDescription)
     */
    @Override
    public Object caseTreeNavigationDescription(final TreeNavigationDescription object) {
        final ContainerViewVariable containerViewVariable = ToolFactory.eINSTANCE.createContainerViewVariable();
        containerViewVariable.setName("containerView"); //$NON-NLS-1$
        object.setContainerViewVariable(containerViewVariable);
        final ElementSelectVariable containerVariable = ToolFactory.eINSTANCE.createElementSelectVariable();
        containerVariable.setName("container"); //$NON-NLS-1$
        object.setContainerVariable(containerVariable);
        final NameVariable treeNameVariable = ToolFactory.eINSTANCE.createNameVariable();
        treeNameVariable.setName("treeName"); //$NON-NLS-1$
        object.setRepresentationNameVariable(treeNameVariable);
        return super.caseTreeNavigationDescription(object);
    }
}

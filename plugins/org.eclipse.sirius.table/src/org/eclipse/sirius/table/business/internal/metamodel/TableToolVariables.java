/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.internal.metamodel;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.description.CellEditorTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateTool;
import org.eclipse.sirius.table.metamodel.table.description.DeleteTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory;
import org.eclipse.sirius.table.metamodel.table.description.LabelEditTool;
import org.eclipse.sirius.table.metamodel.table.description.TableCreationDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.table.metamodel.table.description.TableVariable;
import org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch;
import org.eclipse.sirius.table.tools.api.interpreter.IInterpreterSiriusTableVariables;
import org.eclipse.sirius.table.tools.internal.Messages;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementSelectVariable;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.NameVariable;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;

/**
 * This processing switch will add the needed variable description in the table tools.
 *
 * @author cbrun
 */
public class TableToolVariables extends DescriptionSwitch<Object> {
    /**
     * {@inheritDoc}
     */
    @Override
    public Object caseCreateCellTool(CreateCellTool object) {
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.LINE_SEMANTIC, Messages.TableToolVariables_SemanticLineElement);
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.COLUMN_SEMANTIC, Messages.TableToolVariables_SemanticColumnElement);
        addVariableDescriptor(object, IInterpreterSiriusVariables.ROOT, Messages.TableToolVariables_SemanticRootElement);
        return super.caseCreateCellTool(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object caseCreateTool(CreateTool object) {
        addVariableDescriptor(object, IInterpreterSiriusVariables.ROOT, Messages.TableToolVariables_SemanticRootElement);
        addVariableDescriptor(object, IInterpreterSiriusVariables.ELEMENT, Messages.TableToolVariables_CurrentSemanticElement);
        addVariableDescriptor(object, IInterpreterSiriusVariables.CONTAINER, Messages.TableToolVariables_SemanticElementOfContainerView);
        return super.caseCreateTool(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object caseLabelEditTool(LabelEditTool object) {
        addVariableDescriptor(object, IInterpreterSiriusVariables.ELEMENT, Messages.TableToolVariables_CurrentSemanticElement);
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.TABLE, Messages.TableToolVariables_TableElement);
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.LINE, Messages.TableToolVariables_LineElement);
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.LINE_SEMANTIC, Messages.TableToolVariables_SemanticLineElement);
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.COLUMN_SEMANTIC, Messages.TableToolVariables_SemanticColumnElement);
        addVariableDescriptor(object, IInterpreterSiriusVariables.ROOT, Messages.TableToolVariables_SemanticRootElement);
        return super.caseLabelEditTool(object);
    }

    @Override
    public Object caseCellEditorTool(CellEditorTool object) {
        addVariableDescriptor(object, IInterpreterSiriusVariables.ELEMENT, Messages.TableToolVariables_CurrentSemanticElement);
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.TABLE, Messages.TableToolVariables_TableElement);
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.LINE, Messages.TableToolVariables_LineElement);
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.LINE_SEMANTIC, Messages.TableToolVariables_SemanticLineElement);
        addVariableDescriptor(object, IInterpreterSiriusVariables.ROOT, Messages.TableToolVariables_SemanticRootElement);
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.CELL_EDITOR_RESULT, Messages.TableToolVariables_CellEditorResult);
        return super.caseCellEditorTool(object);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Object caseDeleteTool(DeleteTool object) {
        addVariableDescriptor(object, IInterpreterSiriusVariables.ELEMENT, Messages.TableToolVariables_CurrentSemanticElement);
        addVariableDescriptor(object, IInterpreterSiriusVariables.ROOT, Messages.TableToolVariables_SemanticRootElement);
        return super.caseDeleteTool(object);
    }

    /**
     *
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseTableCreationDescription(org.eclipse.sirius.table.metamodel.table.description.TableCreationDescription)
     */
    @Override
    public Object caseTableCreationDescription(final TableCreationDescription object) {
        final ContainerViewVariable containerViewVariable = ToolFactory.eINSTANCE.createContainerViewVariable();
        containerViewVariable.setName("containerView"); //$NON-NLS-1$
        object.setContainerViewVariable(containerViewVariable);
        final NameVariable tableNameVariable = ToolFactory.eINSTANCE.createNameVariable();
        tableNameVariable.setName("tableName"); //$NON-NLS-1$
        object.setRepresentationNameVariable(tableNameVariable);
        final InitialOperation initialOperation = ToolFactory.eINSTANCE.createInitialOperation();
        object.setInitialOperation(initialOperation);
        return super.caseTableCreationDescription(object);
    }

    /**
     *
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseTableNavigationDescription(org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription)
     */
    @Override
    public Object caseTableNavigationDescription(final TableNavigationDescription object) {
        final ContainerViewVariable containerViewVariable = ToolFactory.eINSTANCE.createContainerViewVariable();
        containerViewVariable.setName("containerView"); //$NON-NLS-1$
        object.setContainerViewVariable(containerViewVariable);
        final ElementSelectVariable containerVariable = ToolFactory.eINSTANCE.createElementSelectVariable();
        containerVariable.setName("container"); //$NON-NLS-1$
        object.setContainerVariable(containerVariable);
        final NameVariable tableNameVariable = ToolFactory.eINSTANCE.createNameVariable();
        tableNameVariable.setName("tableName"); //$NON-NLS-1$
        object.setRepresentationNameVariable(tableNameVariable);
        return super.caseTableNavigationDescription(object);
    }

    private void addVariableDescriptor(TableTool tool, String name, String documentation) {
        if (TableHelper.getVariable(tool, name) == null) {
            TableVariable newVar = DescriptionFactory.eINSTANCE.createTableVariable();
            newVar.setName(name);
            newVar.setDocumentation(documentation);
            tool.getVariables().add(newVar);
            final ModelOperation initialOperation = ToolFactory.eINSTANCE.createInitialOperation().getFirstModelOperations();
            tool.setFirstModelOperation(initialOperation);
        }
    }
}

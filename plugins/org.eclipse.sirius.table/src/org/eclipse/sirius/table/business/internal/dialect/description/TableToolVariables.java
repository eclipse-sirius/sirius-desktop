/*******************************************************************************
 * Copyright (c) 2007, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.business.internal.dialect.description;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.eclipse.sirius.viewpoint.description.tool.NameVariable;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;

/**
 * This processing switch will add the needed variable description in the table tools.
 *
 * @author cbrun
 */
public class TableToolVariables extends DescriptionSwitch<Object> {
    
    private static final Map<String, String> DOCUMENTATIONS = new HashMap<>();
    static {
        DOCUMENTATIONS.put(IInterpreterSiriusTableVariables.TABLE, Messages.TableToolVariables_TableElement);
        DOCUMENTATIONS.put(IInterpreterSiriusVariables.ROOT, Messages.TableToolVariables_SemanticRootElement);

        DOCUMENTATIONS.put(IInterpreterSiriusVariables.CONTAINER_VIEW, Messages.TableToolVariables_ContainerView);
        DOCUMENTATIONS.put(IInterpreterSiriusVariables.CONTAINER, Messages.TableToolVariables_SemanticElementOfContainerView);

        DOCUMENTATIONS.put(IInterpreterSiriusVariables.VIEW, Messages.TableToolVariables_CurrentViewElement);
        DOCUMENTATIONS.put(IInterpreterSiriusVariables.ELEMENT, Messages.TableToolVariables_CurrentSemanticElement);

        DOCUMENTATIONS.put(IInterpreterSiriusTableVariables.LINE, Messages.TableToolVariables_LineElement);
        DOCUMENTATIONS.put(IInterpreterSiriusTableVariables.LINE_SEMANTIC, Messages.TableToolVariables_SemanticLineElement);
        
        DOCUMENTATIONS.put(IInterpreterSiriusTableVariables.COLUMN, Messages.TableToolVariables_ColumnElement);
        DOCUMENTATIONS.put(IInterpreterSiriusTableVariables.COLUMN_SEMANTIC, Messages.TableToolVariables_SemanticColumnElement);
        DOCUMENTATIONS.put(IInterpreterSiriusTableVariables.CELL_EDITOR_RESULT, Messages.TableToolVariables_CellEditorResult);
     }
 
    private static final List<String> AXIS_VARIABLE_NAMES = Arrays.asList(// should be java9 List.of()
            IInterpreterSiriusTableVariables.TABLE, 
            IInterpreterSiriusVariables.ROOT,
            IInterpreterSiriusVariables.CONTAINER_VIEW,
            IInterpreterSiriusVariables.CONTAINER,
            IInterpreterSiriusVariables.VIEW,
            IInterpreterSiriusVariables.ELEMENT
    );
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Object caseCreateCellTool(CreateCellTool object) {
        addCellVariables(object);
        return super.caseCreateCellTool(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object caseCreateTool(CreateTool object) {
        // create line, column and cross column
        AXIS_VARIABLE_NAMES.forEach(name -> addVariableDescriptor(object, name));
        return super.caseCreateTool(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object caseLabelEditTool(LabelEditTool object) {
        addVariableDescriptor(object, IInterpreterSiriusVariables.ELEMENT);
        addCellVariables(object);
        return super.caseLabelEditTool(object);
    }

    @Override
    public Object caseCellEditorTool(CellEditorTool object) {
        addVariableDescriptor(object, IInterpreterSiriusVariables.ELEMENT);
        addCellVariables(object);
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.CELL_EDITOR_RESULT);
        return super.caseCellEditorTool(object);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Object caseDeleteTool(DeleteTool object) {
        AXIS_VARIABLE_NAMES.forEach(name -> addVariableDescriptor(object, name));
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
        containerViewVariable.setName(IInterpreterSiriusVariables.CONTAINER_VIEW);
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
        containerViewVariable.setName(IInterpreterSiriusVariables.CONTAINER_VIEW);
        object.setContainerViewVariable(containerViewVariable);
        final ElementSelectVariable containerVariable = ToolFactory.eINSTANCE.createElementSelectVariable();
        containerVariable.setName(IInterpreterSiriusVariables.CONTAINER);
        object.setContainerVariable(containerVariable);
        final NameVariable tableNameVariable = ToolFactory.eINSTANCE.createNameVariable();
        tableNameVariable.setName("tableName"); //$NON-NLS-1$
        object.setRepresentationNameVariable(tableNameVariable);
        return super.caseTableNavigationDescription(object);
    }

    private void addCellVariables(TableTool object) {
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.TABLE);
        addVariableDescriptor(object, IInterpreterSiriusVariables.ROOT);
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.LINE);
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.LINE_SEMANTIC);
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.COLUMN);
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.COLUMN_SEMANTIC);
    }
    
    
    private void addVariableDescriptor(TableTool tool, String name) {
        if (TableHelper.getVariable(tool, name) == null) {
            TableVariable newVar = DescriptionFactory.eINSTANCE.createTableVariable();
            newVar.setName(name);
            newVar.setDocumentation(DOCUMENTATIONS.get(name));
            tool.getVariables().add(newVar);
        }
    }
}

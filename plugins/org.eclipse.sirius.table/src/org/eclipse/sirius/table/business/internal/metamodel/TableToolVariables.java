/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.internal.metamodel;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
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
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementSelectVariable;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.NameVariable;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;

/**
 * This processing switch will add the needed variable description in the table
 * tools.
 * 
 * @author cbrun
 */
public class TableToolVariables extends DescriptionSwitch<Object> {
    /**
     * {@inheritDoc}
     */
    @Override
    public Object caseCreateCellTool(CreateCellTool object) {
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.LINE_SEMANTIC, "The semantic element corresponding to the line.");
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.COLUMN_SEMANTIC, "The semantic element corresponding to the column.");
        addVariableDescriptor(object, IInterpreterSiriusVariables.ROOT, "The semantic root element of the table.");
        return super.caseCreateCellTool(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object caseCreateTool(CreateTool object) {
        addVariableDescriptor(object, IInterpreterSiriusVariables.ROOT, "The semantic element of the table.");
        addVariableDescriptor(object, IInterpreterSiriusVariables.ELEMENT, "The semantic currently edited element.");
        addVariableDescriptor(object, IInterpreterSiriusVariables.CONTAINER, "The semantic element corresponding to the view container.");
        return super.caseCreateTool(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object caseLabelEditTool(LabelEditTool object) {
        addVariableDescriptor(object, IInterpreterSiriusVariables.ELEMENT, "The currently edited element.");
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.LINE_SEMANTIC, "The semantic element corresponding to the line.");
        addVariableDescriptor(object, IInterpreterSiriusTableVariables.COLUMN_SEMANTIC, "The semantic element corresponding to the column.");
        addVariableDescriptor(object, IInterpreterSiriusVariables.ROOT, "The semantic element of the table.");
        return super.caseLabelEditTool(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object caseDeleteTool(DeleteTool object) {
        addVariableDescriptor(object, IInterpreterSiriusVariables.ELEMENT, "The currently edited element.");
        addVariableDescriptor(object, IInterpreterSiriusVariables.ROOT, "The semantic element corresponding to the current table.");
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

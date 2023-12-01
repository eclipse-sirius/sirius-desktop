/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.table.unit.vsm.editor;

import static org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables.CONTAINER;
import static org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables.CONTAINER_VIEW;
import static org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables.ELEMENT;
import static org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables.ROOT;
import static org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables.TABLE;
import static org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables.VIEW;

import static org.eclipse.sirius.table.tools.api.interpreter.IInterpreterSiriusTableVariables.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateCrossColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateLineTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableVariable;
import org.eclipse.sirius.table.metamodel.table.description.provider.DescriptionItemProviderAdapterFactory;
import org.eclipse.sirius.table.ui.business.internal.dialect.TableDialectUIServices;

import junit.framework.TestCase;

/**
 * Ensures that the variables created by default when creating a tool are as
 * expected.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class DefaultVariablesOnToolsTest extends TestCase {
	
	/** Variables for tool in axis creation or removal. */
	public static final Set<String> AXIS_TOOL_VARIABLES = setOF(
			// See TableToolVariables
			ROOT, TABLE, ELEMENT, VIEW, CONTAINER, CONTAINER_VIEW
	);

	/** Variables for cell label edition. */
	public static final Set<String> CELL_LABEL_VARIABLES = setOF(
			// See TableToolVariables
			ROOT, TABLE, LINE, LINE_SEMANTIC, COLUMN, COLUMN_SEMANTIC, ELEMENT
	);

	/** Variables for cell creation. */
	public static final Set<String> CELL_CREATION_VARIABLES = setOF(
			// See TableToolVariables
			ROOT, TABLE, LINE, LINE_SEMANTIC, COLUMN, COLUMN_SEMANTIC
	);

	/** Variables for cell label edition. */
	public static final Set<String> CELL_EDITION_VARIABLES = setOF(
			// See TableToolVariables
			ROOT, TABLE, LINE, LINE_SEMANTIC, COLUMN, COLUMN_SEMANTIC, ELEMENT, CELL_EDITOR_RESULT
	);

	@SafeVarargs
	private static <T> Set<T> setOF(T... values) {
		// On Java9 update: Set.of(...) 
		return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(values)));
	}
	
    /**
     * Ensures that when getting the new child descriptors of a
     * {@link CreateLineTool} through the default adapter factories, it contains
     * the expected default variables.
     */
    public void testDefaultVariablesOnLineCreationToolOnCrossTable() {
        CreateLineTool createLineTool = DescriptionFactory.eINSTANCE.createCreateLineTool();
        doTestDefaultVariablesOnTableTool(createLineTool, true);
    }

    /**
     * Ensures that when getting the new child descriptors of a
     * {@link CreateLineTool} through the default adapter factories, it contains
     * the expected default variables.
     */
    public void testDefaultVariablesOnLineCreationToolOnEditionTable() {
        CreateLineTool createLineTool = DescriptionFactory.eINSTANCE.createCreateLineTool();
        doTestDefaultVariablesOnTableTool(createLineTool, false);
    }

    /**
     * Ensures that when getting the new child descriptors of a
     * {@link CreateColumnTool} through the default adapter factories, it
     * contains the expected default variables.
     */
    public void testDefaultVariablesOnColumnCreationTool() {
        CreateColumnTool createColumnTool = DescriptionFactory.eINSTANCE.createCreateColumnTool();
        doTestDefaultVariablesOnTableTool(createColumnTool, true);
    }

    /**
     * Ensures that when getting the new child descriptors of a
     * {@link CreateCrossColumnTool} through the default adapter factories, it
     * contains the expected default variables.
     */
    public void testDefaultVariablesOnCrossColumnCreationTool() {
        CreateCrossColumnTool createCrossColumnTool = DescriptionFactory.eINSTANCE.createCreateCrossColumnTool();
        doTestDefaultVariablesOnTableTool(createCrossColumnTool, true);
    }

    /**
     * Ensures that when getting the new child descriptors of the given
     * {@link CreateTool} through the default adapter factories, it contains the
     * expected default variables.
     * 
     * @param createTool
     *            the table {@link CreateTool} to test
     * @param crossTable
     *            indicates if it is a tool appliable on crosstables (if false
     *            then it targets {@link EditionTableDescription}s)
     */
    private void doTestDefaultVariablesOnTableTool(CreateTool createTool, boolean crossTable) {
        // Step 1: Get the table adapter factory used by the TableDialect UI
        // Service
        ComposedAdapterFactory tableFactory = (ComposedAdapterFactory) new TableDialectUIServices().createAdapterFactory();
        assertEquals("Wrong adapter factory for CreateColumnTool", DescriptionItemProviderAdapterFactory.class, tableFactory.getFactoryForType(createTool).getClass());
        DescriptionItemProviderAdapterFactory tableDescriptionAdapterFactory = (DescriptionItemProviderAdapterFactory) tableFactory.getFactoryForType(createTool);

        // Step 2: get new child descriptors and the TableVariables that should
        // be created for the given createTool
        ItemProviderAdapter tableDescriptionAdapter = null;
        if (crossTable) {
            // For CreateColumnTools, the target is not the Table but one of its
            // line
            if (createTool instanceof CreateColumnTool) {
                tableDescriptionAdapter = (ItemProviderAdapter) tableDescriptionAdapterFactory.createElementColumnMappingAdapter();
            } else {
                tableDescriptionAdapter = (ItemProviderAdapter) tableDescriptionAdapterFactory.createCrossTableDescriptionAdapter();
            }
        } else {
            tableDescriptionAdapter = (ItemProviderAdapter) tableDescriptionAdapterFactory.createEditionTableDescriptionAdapter();
        }
        Collection<TableVariable> variables = new LinkedHashSet<>();
        for (Object child : tableDescriptionAdapter.getNewChildDescriptors(createTool, null, null)) {
            if (child instanceof CommandParameter) {
                if (((CommandParameter) child).getValue() instanceof CreateTool) {
                    CreateTool childAsCreateTool = (CreateTool) ((CommandParameter) child).getValue();
                    if (childAsCreateTool.eClass().equals(createTool.eClass())) {
                        variables.addAll(childAsCreateTool.getVariables());
                    }
                }
            }
        }
        assertVariables(createTool.eClass().getName(), AXIS_TOOL_VARIABLES, variables);
    }
    
    public static void assertVariables(String context, Set<String> expectedNames, 
    		Collection<? extends TableVariable> variables) {
        // Validate content
    	assertEquals("Invalid variables for " + context, expectedNames, 
        		variables.stream()
        			.map(it -> it.getName())
        			.collect(Collectors.toSet()));
        // Validate unique
    	assertEquals("Invalid variables for " + context, expectedNames.size(),
    			variables.size());
    }
    
}

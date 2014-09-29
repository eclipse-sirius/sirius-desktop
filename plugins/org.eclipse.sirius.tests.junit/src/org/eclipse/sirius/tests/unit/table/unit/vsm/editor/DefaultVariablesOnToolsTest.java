/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.table.unit.vsm.editor;

import java.util.Collection;

import junit.framework.TestCase;

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

import com.google.common.collect.Sets;

/**
 * Ensures that the variables created by default when creating a tool are as
 * expected.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class DefaultVariablesOnToolsTest extends TestCase {

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
        Collection<TableVariable> variables = Sets.newLinkedHashSet();
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

        // => 3 default variables should have been created
        assertEquals("The " + createTool.eClass().getName() + " should have 3 variables (root, element and container)", 3, variables.size());
    }
}

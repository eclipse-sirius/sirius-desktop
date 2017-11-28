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
import java.util.Iterator;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.diagram.description.tool.provider.ToolItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.description.tool.provider.ToolSectionItemProvider;
import org.eclipse.sirius.table.metamodel.table.description.TableCreationDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription;

import junit.framework.TestCase;

/**
 * Check the custom menu for the vsm editor.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class PopupMenuTest extends TestCase {
    public void testNavigationMenuOnToolSection() {
        // Create a fake ToolSection
        ToolSection toolSection = ToolFactory.eINSTANCE.createToolSection();

        ToolItemProviderAdapterFactory toolItemProviderAdapterFactory = new ToolItemProviderAdapterFactory();
        Adapter adapter = toolItemProviderAdapterFactory.createToolSectionAdapter();
        assertTrue("The adapter must be a ToolSectionItemProvider.", adapter instanceof ToolSectionItemProvider);
        ToolSectionItemProvider toolSectionItemProvider = (ToolSectionItemProvider) adapter;
        Collection<?> children = toolSectionItemProvider.getNewChildDescriptors(toolSection, null, null);
        assertEquals("It must be one child to add a table navigation.", 1, getNbChildren(children, TableNavigationDescription.class));
    }

    public void testCreationMenuOnToolSection() {
        // Create a fake ToolSection
        ToolSection toolSection = ToolFactory.eINSTANCE.createToolSection();

        ToolItemProviderAdapterFactory toolItemProviderAdapterFactory = new ToolItemProviderAdapterFactory();
        Adapter adapter = toolItemProviderAdapterFactory.createToolSectionAdapter();
        assertTrue("The adapter must be a ToolSectionItemProvider.", adapter instanceof ToolSectionItemProvider);
        ToolSectionItemProvider toolSectionItemProvider = (ToolSectionItemProvider) adapter;
        Collection<?> children = toolSectionItemProvider.getNewChildDescriptors(toolSection, null, null);
        assertEquals("It must be one child to add a table creation.", 1, getNbChildren(children, TableCreationDescription.class));
    }

    /**
     * Get the number of child descriptor which have a value instance of
     * <code>classOfValue</code>.
     * 
     * @param newChildDescriptors
     *            List of child descriptors
     * @param classOfValue
     *            The searched type
     */
    protected int getNbChildren(Collection<?> newChildDescriptors, Class<? extends Object> classOfValue) {
        int nbChildren = 0;
        for (Iterator<?> iterator = newChildDescriptors.iterator(); iterator.hasNext();) {
            Object object = (Object) iterator.next();
            if (object instanceof CommandParameter && classOfValue.isInstance(((CommandParameter) object).getValue())) {
                nbChildren++;
            }
        }
        return nbChildren;
    }
}

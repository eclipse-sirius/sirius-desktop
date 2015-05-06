/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.business.internal.metamodel.helper.EClassHelper;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;

public class SubMenusPrioritiesTest extends TestCase {
    public void testViewpointDescriptions() {
        validateChildrenHavePriority(org.eclipse.sirius.viewpoint.description.DescriptionPackage.eINSTANCE);
    }

    public void testDiagramDescriptions() {
        validateChildrenHavePriority(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE);
    }

    public void testTreeDescriptions() {
        validateChildrenHavePriority(org.eclipse.sirius.tree.description.DescriptionPackage.eINSTANCE);
    }

    public void testTableDescriptions() {
        validateChildrenHavePriority(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.eINSTANCE);
    }

    public void testSequenceDescriptions() {
        validateChildrenHavePriority(org.eclipse.sirius.diagram.sequence.description.DescriptionPackage.eINSTANCE);
    }

    private void validateChildrenHavePriority(EPackage ePackage) {
        List<EClass> list = new ArrayList<EClass>();
        TreeIterator<EObject> iterator = ePackage.eAllContents();
        while (iterator.hasNext()) {
            EObject eObj = iterator.next();
            if (eObj instanceof EClass) {
                list.add((EClass) eObj);
            }
        }
        for (EClass eClass : list) {
            String key = EClassHelper.getPath(eClass);
            try {
                Integer.parseInt(SiriusEditorPlugin.INSTANCE.getString(key));
            } catch (MissingResourceException mre) {
                fail("The sub-menu priority key '" + key + "' is missing in the plugin.properties file of the sirius.editor plugin");
            } catch (NumberFormatException nfe) {
                fail("The value associated with the key '" + key + "' in the plugin.properties file of the sirius.editor plugin is not an int");
            }
        }
    }
}

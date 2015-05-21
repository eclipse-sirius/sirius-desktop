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

import java.util.List;
import java.util.MissingResourceException;
import java.util.Set;

import junit.framework.TestCase;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.business.internal.metamodel.helper.EClassHelper;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.ext.emf.AllContents;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Check that the priorities for the elements in the VSM editor's context menu
 * are correctly defined.
 */
public class SubMenusPrioritiesTest extends TestCase {
    /**
     * It should not be considered an error if some EClass does not have an
     * explicit priority set in the properties file, as the code handles this
     * case fine with a default value. However it may be interesting to run the
     * test with this flag on from time to time and manually review the missing
     * keys to verify if some important type is not missing.
     */
    private static final boolean REPORT_MISSING_KEYS = false;

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

    @SuppressWarnings("unused")
    private void validateChildrenHavePriority(EPackage ePackage) {
        Set<String> missing = Sets.newTreeSet();
        Set<String> invalid = Sets.newTreeSet();

        // Collect concrete classes which can have priorities associated
        List<EClass> classes = Lists.newArrayList();
        for (EClass klass : Iterables.filter(AllContents.of(ePackage, true), EClass.class)) {
            if (!klass.isAbstract() && !klass.isInterface()) {
                classes.add(klass);
            }
        }

        // Check the priorities are defined and valid
        for (EClass eClass : classes) {
            String key = EClassHelper.getPath(eClass);
            try {
                Integer.parseInt(SiriusEditorPlugin.INSTANCE.getString(key).trim());
            } catch (MissingResourceException mre) {
                missing.add(key);
            } catch (NumberFormatException nfe) {
                invalid.add(key);
            }
        }
        
        // Report any errors or warnings
        if (missing.size() > 0 && REPORT_MISSING_KEYS) {
            System.err.println("The following concrete types do not have any priority set; they will get the default value from AbstractMenuBuilder.DEFAULT_PRIORITY:");
            System.err.println("* " + Joiner.on("\n* ").join(missing));
        }
        if (invalid.size() > 0) {
            fail("The following keys have malformed values which can not be parsed as integers: " + Joiner.on(", ").join(invalid));
        }
    }
}

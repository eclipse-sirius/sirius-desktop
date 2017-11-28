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
package org.eclipse.sirius.tests.unit.diagram.filter;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.common.ui.tools.api.selection.page.EObjectSelectionFilter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import junit.framework.TestCase;

/**
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class EObjectSelectionFilterTest extends TestCase {

    /**
     * The display used for the GUI.
     */
    private static Display display;

    protected EObjectSelectionFilter selectionFilter;

    protected TreeViewer treeViewer;

    protected EPackage p1;

    protected EClass c1;

    protected EClass c2;
    
    protected EClass c3;

    /**
     * Gets all the thread in the VM.
     * 
     * @return all the threads in the VM.
     */
    public static Thread[] allThreads() {
        ThreadGroup threadGroup = primaryThreadGroup();

        Thread[] threads = new Thread[64];
        int enumerate = threadGroup.enumerate(threads, true);

        Thread[] result = new Thread[enumerate];
        System.arraycopy(threads, 0, result, 0, enumerate);

        return result;
    }

    /**
     * Gets the primary thread group.
     * 
     * @return the top level thread group.
     */
    public static ThreadGroup primaryThreadGroup() {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        while (threadGroup.getParent() != null)
            threadGroup = threadGroup.getParent();
        return threadGroup;
    }

    /**
     * Caches the display for later use.
     * 
     * @return the display.
     */
    public static Display display() {
        if ((display == null) || display.isDisposed()) {
            display = null;
            Thread[] allThreads = allThreads();
            for (Thread thread : allThreads) {
                Display d = Display.findDisplay(thread);
                if (d != null)
                    display = d;
            }
            if (display == null)
                throw new IllegalStateException("Could not find a display"); //$NON-NLS-1$
        }
        return display;
    }

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        assertNotNull("There is no current display!", display());
        Shell shell = null;
        if (display().getActiveShell() == null) {
            shell = new Shell(display());
        } else {
            shell = display().getActiveShell();
        }
        assertNotNull("There is no active shell!", shell);
        // Create a fake treeViewer only to access to the label provider in the
        // selectionFilter
        treeViewer = new TreeViewer(shell);
        treeViewer.setLabelProvider(new ILabelProvider() {

            /**
             * Get the name of the named element.
             * 
             * {@inheritDoc}
             * 
             * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
             */
            public String getText(Object element) {
                if (element instanceof ENamedElement) {
                    return ((EClassifier) element).getName();
                }
                return null;
            }

            public void removeListener(ILabelProviderListener listener) {
            }

            public boolean isLabelProperty(Object element, String property) {
                return false;
            }

            public void dispose() {
            }

            public void addListener(ILabelProviderListener listener) {
            }

            public Image getImage(Object element) {
                return null;
            }
        });
        selectionFilter = new EObjectSelectionFilter();
        selectionFilter.setTreeViewer(treeViewer);
        // Create semantic elements to deal with
        p1 = EcoreFactory.eINSTANCE.createEPackage();
        p1.setName("p1");
        c1 = EcoreFactory.eINSTANCE.createEClass();
        c1.setName("Class1");
        p1.getEClassifiers().add(c1);
        c2 = EcoreFactory.eINSTANCE.createEClass();
        c2.setName("Class2");
        p1.getEClassifiers().add(c2);
        
        c3= EcoreFactory.eINSTANCE.createEClass();
        c3.setName("Screen 1");
        p1.getEClassifiers().add(c3);
        
    }

    public void testFullName() {
        checkSelection("Class1", c1, true, c2, false, c3, false);
        checkSelection("class1", c1, true, c2, false, c3, false);
    }

    public void testStartNameWithStar() {
        checkSelection("Clas*", c1, true, c2, true, c3, false);
        checkSelection("clas*", c1, true, c2, true, c3, false);
    }

    public void testStartNameWithoutStar() {
        checkSelection("Clas", c1, true, c2, true, c3, false);
        checkSelection("clas", c1, true, c2, true, c3, false);
    }

    public void testEndNameWithStar() {
        checkSelection("*s1", c1, true, c2, false, c3, false);
        checkSelection("*S1", c1, true, c2, false, c3, false);
    }

    public void testEndNameWithQuestionMark() {
        checkSelection("Class?", c1, true, c2, true, c3, false);
        checkSelection("class?", c1, true, c2, true, c3, false);
    }
    
    public void testWithQuestionMark() {
        checkSelection("Clas?1", c1, true, c2, false, c3, false);
        checkSelection("clas?1", c1, true, c2, false, c3, false);
        checkSelection("Clas?3", c1, false, c2, false, c3, false);
        checkSelection("clas?3", c1, false, c2, false, c3, false);
    }

    public void testEndNameWithBlank() {
        checkSelection("Screen ", c1, false, c2, false, c3, true);
        checkSelection("Class1 ", c1, true, c2, false, c3, false);
    }
    
    protected void checkSelection(String regExp, EClass firstClassToCheck, boolean firstResultExpected, EClass secondClassToCheck, boolean secondResultExpected, EClass thirdClassToCheck, boolean thirdResultExpected) {
        selectionFilter.setPrefix(regExp);
        if (firstResultExpected) {
            assertTrue(firstClassToCheck.getName() + " must be selected with regExp " + "\"" + regExp + "\"", selectionFilter.select(null, p1, firstClassToCheck));
        } else {
            assertFalse(firstClassToCheck.getName() + " must not be selected with regExp " + "\"" + regExp + "\"", selectionFilter.select(null, p1, firstClassToCheck));
        }
        if (secondResultExpected) {
            assertTrue(secondClassToCheck.getName() + " must be selected with regExp " + "\"" + regExp + "\"", selectionFilter.select(null, p1, secondClassToCheck));
        } else {
            assertFalse(secondClassToCheck.getName() + " must not be selected with regExp " + "\"" + regExp + "\"", selectionFilter.select(null, p1, secondClassToCheck));
        }
        if (thirdResultExpected) {
            assertTrue(thirdClassToCheck.getName() + " must be selected with regExp " + "\"" + regExp + "\"", selectionFilter.select(null, p1, thirdClassToCheck));
        } else {
            assertFalse(thirdClassToCheck.getName() + " must not be selected with regExp " + "\"" + regExp + "\"", selectionFilter.select(null, p1, thirdClassToCheck));
        }
    }
}

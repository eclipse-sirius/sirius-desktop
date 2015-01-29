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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.DiagramElementsSelectionDialogPatternMatcher;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Checks that the Pattern Matcher used by the DiagramElementSelectionDialog
 * recognizes the right elements.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class DiagramElementSelectionDialogPatternMatcherTest extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/filter/vp-1191/vp-1191.ecore";

    private static final String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/filter/vp-1191/vp-1191.aird";

    /**
     * Mapping between a DiagramElement and its name.
     */
    private Map<String, DDiagramElement> nameToDiagramElements;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);

        DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        nameToDiagramElements = Maps.newHashMap();
        Iterator<DDiagramElement> iterator = diagram.getDiagramElements().iterator();
        while (iterator.hasNext()) {
            DDiagramElement next = iterator.next();
            nameToDiagramElements.put(next.getName(), next);
        }
    }

    /**
     * Ensures the matcher works correctly with empty patterns.
     * <p>
     * Null or empty regular expression is equivalent to '*'
     * </p>
     */
    public void testMatcherWithEmptyPattern() {
        checkMatcher("", Sets.newHashSet("p1", "p2", "p3", "4", "Ed", "Eddy", "Merks", "Mum", "Mom", "M with 'simple' quotes", "M with spaces s", "M with \"quotes\" s", "Mummy", "mummY"));
        checkMatcher(null, Sets.newHashSet("p1", "p2", "p3", "4", "Ed", "Eddy", "Merks", "Mum", "Mom", "M with 'simple' quotes", "M with spaces s", "M with \"quotes\" s", "Mummy", "mummY"));
    }

    /**
     * Ensures the matcher works correctly without any regular Expression.
     * <p>
     * Expected behavior :
     * <ul>
     * <li>expreg : 'XYZ' recognizes 'XYZ'</li>
     * <li>expreg : 'XY' recognizes 'XYZ'</li>
     * </p>
     */
    public void testMatcherWithoutRegularExpressions() {
        // Test with full names
        checkMatcher("p1", Sets.newHashSet("p1"));
        checkMatcher("Eddy", Sets.newHashSet("Eddy"));
        checkMatcher("M with 'simple' quotes", Sets.newHashSet("M with 'simple' quotes"));

        // Test with beginning of names
        checkMatcher("Me", Sets.newHashSet("Merks"));
        checkMatcher("E", Sets.newHashSet("Ed", "Eddy"));
        checkMatcher("X", new HashSet<String>());

    }

    /**
     * Ensures the matcher works correctly with the one-char jocker ('?').
     * <p>
     * Expected behavior :
     * <ul>
     * <li>expreg : 'X?' recognizes 'XY', 'XZ' but not 'XYZ'</li>
     * <li>expreg : '?' recognizes 'XYZ'</li>
     * <li>expreg : 'X?Z' recognizes 'XYZ', 'XZZ' but not 'XZ' neither 'XYYZ'</li>
     * </p>
     */
    public void testMatchWithOneCharJockers() {
        checkMatcher("p?", Sets.newHashSet("p1", "p2", "p3"));
        checkMatcher("?", Sets.newHashSet("p1", "p2", "p3", "4", "Ed", "Eddy", "Merks", "Mum", "Mom", "M with 'simple' quotes", "M with spaces s", "M with \"quotes\" s", "Mummy", "mummY"));
        checkMatcher("M?", Sets.newHashSet("Merks", "Mum", "Mom", "M with 'simple' quotes", "M with spaces s", "M with \"quotes\" s", "Mummy", "mummY"));
        checkMatcher("Mu?", Sets.newHashSet("Mum", "Mummy", "mummY"));
        checkMatcher("M?m", Sets.newHashSet("Mum", "Mom", "Mummy", "mummY"));

        checkMatcher("?m", new HashSet<String>());
        checkMatcher("Edd?", Sets.newHashSet("Eddy"));
        checkMatcher("Eddy?", new HashSet<String>());

    }

    /**
     * Ensures the matcher works correctly with the string jocker ('*'). *
     * Expected behavior :
     * <ul>
     * <li>regexp : 'X*Z' recognizes 'XZ', 'XZZ', 'XYYZZZ'...</li>
     * </ul>
     */
    public void testMatchWithStringJockers() {
        checkMatcher("*", Sets.newHashSet("p1", "p2", "p3", "4", "Ed", "Eddy", "Merks", "Mum", "Mom", "M with 'simple' quotes", "M with spaces s", "M with \"quotes\" s", "Mummy", "mummY"));
        checkMatcher("* *", Sets.newHashSet("M with 'simple' quotes", "M with spaces s", "M with \"quotes\" s"));
        checkMatcher("* * ", Sets.newHashSet("M with 'simple' quotes", "M with spaces s", "M with \"quotes\" s"));
        checkMatcher("E*y", Sets.newHashSet("Eddy"));
        checkMatcher("E*d*", Sets.newHashSet("Eddy", "Ed"));
        checkMatcher("E*d", Sets.newHashSet("Eddy", "Ed"));
        checkMatcher("Edd*", Sets.newHashSet("Eddy"));
    }

    /**
     * Regroups all the test related to space located at the end of a regexp.
     * <p>
     * Expected behavior :
     * <ul>
     * <li>regexp : 'XYZ ' recognizes 'XYZ' but not 'XYZX'</li>
     * <li>regexp : 'X*Z ' recognizes 'XZ', but not 'XZZ', 'XYYZZZ'...</li>
     * <li>regexp : '? ' recognizes 'X' or 'Y' but not 'XZ', 'XYZ'...</li>
     * </ul>
     * </p>
     */
    public void testMatchWithSpacesAtTheEnd() {
        checkMatcher("E*d ", Sets.newHashSet("Ed"));
        checkMatcher("* ", Sets.newHashSet("p1", "p2", "p3", "4", "Ed", "Eddy", "Merks", "Mum", "Mom", "M with 'simple' quotes", "M with spaces s", "M with \"quotes\" s", "Mummy", "mummY"));
        checkMatcher("? ", Sets.newHashSet("4"));
        checkMatcher("E ", new HashSet<String>());
        checkMatcher("Eddy ", Sets.newHashSet("Eddy"));
        checkMatcher("M?m ", Sets.newHashSet("Mum", "Mom"));

    }

    /**
     * Ensures that the Matcher doesn't consider the case for elements or
     * regexp.
     */
    public void testMatchWithCases() {
        checkMatcher("mummy", Sets.newHashSet("mummY", "Mummy"));
        checkMatcher("MUMMY", Sets.newHashSet("mummY", "Mummy"));
    }

    /**
     * Checks that the matcher defined with the given regexp is matching the
     * {@link DDiagramElement}s with the given names, and only those elements.
     * 
     * @param regexp
     *            the regualar expression to define the matcher with
     * @param matchingElementsNames
     *            the name of all elements that should be recognized by this
     *            matcher
     */
    public void checkMatcher(String regexp, Set<String> matchingElementsNames) {
        // Step 1 : get the matchPredicate using the patterMatcher with the
        // given regexp
        DiagramElementsSelectionDialogPatternMatcher matcher = new DiagramElementsSelectionDialogPatternMatcher(regexp);
        Predicate<Object> matchPredicate = matcher.getMatchPredicate();

        // Step 2 : ensure that all expected matching elements are recognized by
        // this predicate
        for (Object matchingElementName : matchingElementsNames) {
            DDiagramElement matchingElement = this.nameToDiagramElements.get(matchingElementName);
            assertNotNull(matchingElementName + " does not exist in the Diagram", matchingElement);
            assertTrue(matchingElementName + " should match the regexp '" + regexp + "'", matchPredicate.apply(matchingElement));
        }

        // Step 3 : ensure that all expected unmatching elements are not
        // recognized by this predicate
        for (Object unmatchingElementName : Sets.difference(this.nameToDiagramElements.keySet(), matchingElementsNames)) {
            DDiagramElement unmatchingElement = this.nameToDiagramElements.get(unmatchingElementName);
            assertNotNull(unmatchingElementName + " does not exist in the Diagram", unmatchingElement);
            assertFalse(unmatchingElementName + " should not match the regexp '" + regexp + "'", matchPredicate.apply(unmatchingElement));
        }
    }
}

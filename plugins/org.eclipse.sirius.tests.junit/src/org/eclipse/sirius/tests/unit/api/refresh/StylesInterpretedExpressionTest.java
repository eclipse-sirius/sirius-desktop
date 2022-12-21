/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.refresh;

import java.util.Collection;

import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.BracketEdgeStyle;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingWithInterpreterHelper;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.style.BracketEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.BundledImageDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * Check that the interpreter variables are correctly set for style computation.
 * 
 * @author Laurent Fasani
 *
 */
public class StylesInterpretedExpressionTest extends SiriusDiagramTestCase {
    private static final String REPRESENTATION_DESC_NAME = "ClassDiagram"; //$NON-NLS-1$

    private static final String VIEWPOINT_NAME = "StyleCustoVP"; //$NON-NLS-1$

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Copy in temporary test project
        copyAllFiles(SiriusTestsPlugin.PLUGIN_ID, "/data/unit/refresh/style/bestStyle", TEMPORARY_PROJECT_NAME);

        genericSetUp("/" + TEMPORARY_PROJECT_NAME + "/" + "My.ecore", "/" + TEMPORARY_PROJECT_NAME + "/" + "StyleCustoTest.odesign", "/" + TEMPORARY_PROJECT_NAME + "/" + "representations.aird");
        // Activate auto refresh
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        activateViewpoint(VIEWPOINT_NAME);
    }

    /**
     * Check that all necessary interpreter variables are present for Conditional styles.
     */
    public void testVariableAvaibilityForConditionalStyles() {
        DDiagram diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).iterator().next();
        Collection<DDiagramElement> allDiagramElements = new DDiagramQuery(diagram).getAllDiagramElements();
        DNode dNode = (DNode) allDiagramElements.stream().filter(DNode.class::isInstance).findFirst().get();

        StyleDescription bestStyleDescription = new MappingWithInterpreterHelper(interpreter).getBestStyleDescription(dNode.getActualMapping(), dNode.getTarget(), dNode, diagram, diagram);

        // check that the bestStyleDescription is the BundledImageDescription conditional style that uses the expression
        // aql:diagram!=null and view!=null and container!=null and containerView != null
        // This check ensures that all variables are available
        assertTrue(bestStyleDescription instanceof BundledImageDescription);
        assertTrue(dNode.getStyle() instanceof BundledImage);

        DEdge dEdge = (DEdge) allDiagramElements.stream().filter(DEdge.class::isInstance).findFirst().get();

        bestStyleDescription = new MappingWithInterpreterHelper(interpreter).getBestStyleDescription((DiagramElementMapping) dEdge.getActualMapping(), dEdge.getTarget(), dEdge, diagram, diagram);

        // check that the bestStyleDescription is the EdgeStyleDescription conditional style that uses the
        // expression
        // aql:diagram!=null and view!=null and container!=null and containerView != nullaql:diagram!=null and
        // view!=null and container!=null and containerView!=null and sourceView!=null and targetView!=null
        assertFalse(bestStyleDescription instanceof BracketEdgeStyleDescription);
        assertFalse(dEdge.getStyle() instanceof BracketEdgeStyle);
    }

    /**
     * Check that all necessary interpreter variables are present for Style Customization.
     */
    public void testVariableAvaibilityForStyleCustomization() {
        DDiagram diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).iterator().next();
        Collection<DDiagramElement> allDiagramElements = new DDiagramQuery(diagram).getAllDiagramElements();
        DNode dNode = (DNode) allDiagramElements.stream().filter(DNode.class::isInstance).findFirst().get();

        Style style = dNode.getStyle();

        // check that the Style Customization that uses the expression
        // aql:diagram!=null and view!=null and container!=null and containerView != null
        // This check ensures that all variables are available
        assertTrue(style instanceof BorderedStyle);
        // check that the color is red
        assertEquals(RGBValues.create(239, 41, 41), ((BorderedStyle) style).getBorderColor());

        DEdge dEdge = (DEdge) allDiagramElements.stream().filter(DEdge.class::isInstance).findFirst().get();
        style = dEdge.getStyle();

        // 1- check that the Style Customization that uses the expression
        // aql:diagram!=null and view!=null and container!=null and containerView != nullaql:diagram!=null and
        // view!=null and container!=null and containerView!=null and sourceView!=null and targetView!=null
        // 2- check that the Property Customization that uses the expression
        // aql:if diagram!=null and view!=null and container!=null and containerView!=null and sourceView!=null and
        // targetView!=null then diagram::LineStyle::dash_dot else null endif

        assertTrue(style instanceof EdgeStyle && LineStyle.DASH_DOT_LITERAL.equals(((EdgeStyle) style).getLineStyle()));
    }
}

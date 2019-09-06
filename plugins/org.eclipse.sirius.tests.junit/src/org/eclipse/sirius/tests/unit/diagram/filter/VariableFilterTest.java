/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.description.TypedVariable;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

public class VariableFilterTest extends SiriusDiagramTestCase {
    private static final String GMF_NODE = "The GMF Node ";

    private static final String SHOULD_BE_VISIBLE = " should be visible";

    private static final String DIAGRAM_ELEMENT = "The diagram element ";

    private static final String FILTER_NAME = "Classifier";

    private static final String FILTER_NAME_2 = "FilterWithTypedVariables";

    private static final String TEST_SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/variablefilter/ticketvp1063/vp-1063.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/variablefilter/ticketvp1063/vp-1063.odesign";

    private static final String DESIGN_VIEWPOINT_NAME = "vp1063";

    private static final String ENTITIES_DESC_NAME = "vp1063";

    private static final String DIAGRAM_DESC_NAME_2 = "vpForFilterWithTypedVariable";

    private static final String DIAGRAM_DESC_NAME_3 = "vpTwoFilters";

    private DDiagram diagram;

    private DiagramEditor editor;

    /**
     * {@inheritDoc}
     * 
     * @Override
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);

    }

    private void openDiagram(String diagramDescriptionName) {
        diagram = (DDiagram) createRepresentation(diagramDescriptionName);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);
    }

    /**
     * Test multiple variable filter on two selected elements.
     */
    public void testMultipleSelection() {
        openDiagram(ENTITIES_DESC_NAME);

        activeMultipleSelectionFilter();
        final Collection<String> elementNames = new ArrayList<String>(2);
        elementNames.add("Class");
        elementNames.add("EEnum");
        checkActiveFilter(elementNames);
        deactivateFilter(diagram, FILTER_NAME);
        checkDesactiveFilter();
    }

    /**
     * Test multiple variable filter on one selected element.
     */
    public void testSimpleSelection() {
        openDiagram(ENTITIES_DESC_NAME);

        activateSimpleSelectionFilter();
        final Collection<String> elementNames = new ArrayList<String>(1);
        elementNames.add("Class");
        checkActiveFilter(elementNames);
        deactivateFilter(diagram, FILTER_NAME);
        checkDesactiveFilter();
    }

    /**
     * Test that activated filters are sorted each time a filter is added in the list.
     */
    public void testFilterSorting() {
        openDiagram(DIAGRAM_DESC_NAME_3);
        activeMultipleSelectionFilter();
        activateTypedVariableFilter();
        List<FilterDescription> activatedFiltersAfterActivation = new ArrayList<>(diagram.getActivatedFilters());
        assertEquals("Classifier", activatedFiltersAfterActivation.get(0).getName());
        assertEquals("FilterWithTypedVariables", activatedFiltersAfterActivation.get(1).getName());
    }

    /**
     * Test multiple variable filter on two and next one selected element.
     */
    public void testMultipleAndSimpleSelection() {
        testMultipleSelection();
        testSimpleSelection();
    }

    /**
     * Test variable filter typed Variable.
     */
    public void testFilterWithTypedFilter() {
        openDiagram(DIAGRAM_DESC_NAME_2);

        activateTypedVariableFilter();

        // Check that nodes are not visible
        // It means that variables are created with the good type and
        // interpreted at runtime. The filter condition expression is:
        // aql:not (rGBVariable.oclIsKindOf(viewpoint::RGBValues) and
        // stringVariable.contains('textDefault') and intVariable+5=10)
        assertTrue(diagram.getDiagramElements().size() != 0);
        for (DDiagramElement diagramElement : diagram.getDiagramElements()) {
            assertFalse(DIAGRAM_ELEMENT + diagramElement.getName() + " should be hidden", diagramElement.isVisible());
        }

        deactivateFilter(diagram, FILTER_NAME_2);
        for (DDiagramElement diagramElement : diagram.getDiagramElements()) {
            assertTrue(DIAGRAM_ELEMENT + diagramElement.getName() + SHOULD_BE_VISIBLE, diagramElement.isVisible());
        }
    }

    private void activateTypedVariableFilter() {
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback() {
            List<String> stringValues = new ArrayList<String>();

            @Override
            public List<String> askForTypedVariable(List<TypedVariable> typedVariableList, List<String> defaultValues) {
                stringValues.add("113,113,113");
                stringValues.add("textDefault");
                stringValues.add("5");
                return stringValues;
            }
        });
        activateFilter(diagram, FILTER_NAME_2);
    }

    private void activateSimpleSelectionFilter() {
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback() {
            Collection<EObject> selectedEObjects = new ArrayList<EObject>();

            @Override
            public Collection<EObject> askForEObjects(String message, TreeItemWrapper input, AdapterFactory factory) throws InterruptedException {
                selectedEObjects.add(((EPackage) semanticModel).getEClassifiers().get(0));
                return selectedEObjects;
            }

        });
        activateFilter(diagram, FILTER_NAME);
    }

    private void checkDesactiveFilter() {
        for (DDiagramElement diagramElement : diagram.getDiagramElements()) {
            // DDiagramElement
            assertTrue(DIAGRAM_ELEMENT + diagramElement.getName() + SHOULD_BE_VISIBLE, diagramElement.isVisible());
            // GMF
            final Node node = getGmfNode(diagramElement);
            assertTrue(GMF_NODE + node + SHOULD_BE_VISIBLE, node.isVisible());
        }
    }

    private void checkActiveFilter(final Collection<String> names) {
        for (DDiagramElement diagramElement : diagram.getDiagramElements()) {
            if (names.contains(diagramElement.getName())) {
                // DDiagramElement
                assertTrue(DIAGRAM_ELEMENT + diagramElement.getName() + SHOULD_BE_VISIBLE, diagramElement.isVisible());
                // GMF
                final Node node = getGmfNode(diagramElement);
                assertEquals(GMF_NODE + node + SHOULD_BE_VISIBLE, true, node.isVisible());
            } else {
                // DDiagramElement
                assertFalse(DIAGRAM_ELEMENT + diagramElement.getName() + " should not be visible", diagramElement.isVisible());
                // GMF
                final Node node = getGmfNode(diagramElement);
                assertFalse(GMF_NODE + node + " should not be visible", node.isVisible());
            }
        }
    }

    private void activeMultipleSelectionFilter() {
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback() {
            Collection<EObject> selectedEObjects = new ArrayList<EObject>();

            @Override
            public Collection<EObject> askForEObjects(String message, TreeItemWrapper input, AdapterFactory factory) throws InterruptedException {

                selectedEObjects.add(((EPackage) semanticModel).getEClassifiers().get(0));
                selectedEObjects.add(((EPackage) semanticModel).getEClassifiers().get(1));
                return selectedEObjects;
            }

        });
        activateFilter(diagram, FILTER_NAME);
    }

    /**
     * {@inheritDoc}
     * 
     * @Override
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}

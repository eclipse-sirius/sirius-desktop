package org.eclipse.sirius.tests.unit.diagram.decorators;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.unit.diagram.GenericTestCase;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.GenericDecorationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Test the refresh of the decoration.
 * 
 * @author sthibaudeau
 */
public class DecorationRefreshTest extends GenericTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/decorators/refresh/refresh.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/decorators/refresh/refresh.odesign";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);

        final Viewpoint vp = getViewpointFromName("RefreshDecorations");
        activateViewpoint(vp.getName());

    }

    public void testRefreshOnListElement() {
        final DiagramDescription classDiag = findDiagramDescription("test");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        final Layer defaultLayer = classDiag.getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);

        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer.getDecorationDescriptionsSet());
        final GenericDecorationDescription decorationDescription = (GenericDecorationDescription) defaultLayer.getDecorationDescriptionsSet().getDecorationDescriptions().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, decorationDescription);

        initSynchronizer(classDiag, "test");
        final DDiagram diagram = getRefreshedDiagram();

        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("Bad number of DDiagramElement", 1, elements.size());

        // Check on container
        DNodeList list = (DNodeList) diagram.getContainers().get(0);
        checkDecorationNumber(list, 0);

        // Check on list element
        assertEquals("Bad number of DDiagramElement in the list", 1, list.getElements().size());
        DDiagramElement listElement = list.getElements().get(0);
        checkDecorationNumber(listElement, 1);

        EClass eClass = getSemanticElement(list, EClass.class);
        setName(eClass, "aClass"); // A decoration is added when the name starts with an "A" thus it should APPEAR now
        refresh(diagram);
        checkDecorationNumber(list, 1);

        EAttribute eAttribute = getSemanticElement(listElement, EAttribute.class);
        setName(eAttribute, "bttr"); // A decoration is added when the name starts with an "A" thus it should DISAPPEAR
                                     // now
        refresh(diagram);
        checkDecorationNumber(listElement, 0);
    }

    private void setName(ENamedElement element, String newName) {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                element.setName(newName);
            }
        });
    }

    private <T> T getSemanticElement(DSemanticDecorator diagramElement, Class<T> expectedClass) {
        EObject target = diagramElement.getTarget();
        assertTrue("Semantic element is not of the expected type (" + expectedClass.getName() + ")" + " but was of type (" + target.getClass().getName() + ")", expectedClass.isInstance(target));
        return expectedClass.cast(target);
    }

    private void checkDecorationNumber(final DDiagramElement diagramElement, int expectedNbDecorations) {
        int nbDecos = diagramElement.getDecorations().size() + diagramElement.getTransientDecorations().size();
        assertEquals("Bad decoration number", expectedNbDecorations, nbDecos);
    }
}

/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.mm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.tool.DiagramCreationDescription;
import org.eclipse.sirius.diagram.description.tool.DiagramNavigationDescription;
import org.eclipse.sirius.diagram.sequence.SequencePackage;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.TableCreationDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.tree.description.TreeCreationDescription;
import org.eclipse.sirius.tree.description.TreeNavigationDescription;
import org.eclipse.sirius.viewpoint.IdentifiedElement;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;

/**
 * Test some override of the MetaModel :
 * <UL>
 * <LI>VP-2534 : Problem of getMappings override (use of wrong crossReferencer in some cases).</LI>
 * </UL>
 * 
 * @author lredor
 */
public class MMTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/mappings/VP-2534/";

    private static final String SEMANTIC_MODEL_FILENAME = "My.ecore";

    private static final String REPRESENTATIONS_FILENAME = "My.aird";

    private static final String VSM_FILENAME = "My.odesign";

    private static final int NUMBER_OF_MAPPINGS = 4;

    private static final String REPRESENTATION_DESC__NAME = "Diagram";

    private static final List<EClass> TRANSIENT_OBJECTS_WHITE_LIST = Arrays.asList(ViewpointPackage.eINSTANCE.getDAnalysisSessionEObject(), ViewpointPackage.eINSTANCE.getUIState(),
            ViewpointPackage.eINSTANCE.getSessionManagerEObject());

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + REPRESENTATIONS_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + VSM_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + VSM_FILENAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + VSM_FILENAME, TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILENAME);
    }

    /**
     * This method checks that the number of mappings return by each specializations of method getMappings are OK.
     * 
     * VP-2534 : Problem of getMappings override (use of wrong crossReferencer in some cases).
     * 
     * @throws Exception
     *             in case of problem
     */
    public void testGetMappingsOverride() throws Exception {
        DiagramDescription desc = ((DDiagram) getRepresentations(REPRESENTATION_DESC__NAME, session).iterator().next()).getDescription();
        for (ToolEntry toolEntry : desc.getToolSection().getOwnedTools()) {
            if (toolEntry instanceof DiagramCreationDescription) {
                assertEquals("Bad number of mappings for DiagramCreationDescription)", NUMBER_OF_MAPPINGS, ((DiagramCreationDescription) toolEntry).getMappings().size());
            } else if (toolEntry instanceof TableCreationDescription) {
                assertEquals("Bad number of mappings for TableCreationDescription)", NUMBER_OF_MAPPINGS, ((TableCreationDescription) toolEntry).getMappings().size());
            } else if (toolEntry instanceof TreeCreationDescription) {
                assertEquals("Bad number of mappings for TreeCreationDescription)", NUMBER_OF_MAPPINGS, ((TreeCreationDescription) toolEntry).getMappings().size());
            } else if (toolEntry instanceof DiagramNavigationDescription) {
                assertEquals("Bad number of mappings for DiagramNavigationDescription)", NUMBER_OF_MAPPINGS, ((DiagramNavigationDescription) toolEntry).getMappings().size());
            } else if (toolEntry instanceof TableNavigationDescription) {
                assertEquals("Bad number of mappings for TableNavigationDescription)", NUMBER_OF_MAPPINGS, ((TableNavigationDescription) toolEntry).getMappings().size());
            } else if (toolEntry instanceof TreeNavigationDescription) {
                assertEquals("Bad number of mappings for TreeNavigationDescription)", NUMBER_OF_MAPPINGS, ((TreeNavigationDescription) toolEntry).getMappings().size());
            }
        }
    }

    /**
     * This test ensures that every serializable instance of Sirius meta-model inherits from {@link IdentifiedElement}
     * class so that they have an uid attribute. This test considers every classes of Sirius Package excluding some
     * EClass that we know that they are never serialized.
     */
    public void testIdentifiedElementInheritence() {
        int uuidLength = EcoreUtil.generateUUID().length();
        List<EClassifier> allClassifiers = new ArrayList<>(ViewpointPackage.eINSTANCE.getEClassifiers());
        allClassifiers.addAll(DiagramPackage.eINSTANCE.getEClassifiers());
        allClassifiers.addAll(TreePackage.eINSTANCE.getEClassifiers());
        allClassifiers.addAll(TablePackage.eINSTANCE.getEClassifiers());
        allClassifiers.addAll(SequencePackage.eINSTANCE.getEClassifiers());

        List<EClass> allClasses = allClassifiers.stream().filter(EClass.class::isInstance).map(EClass.class::cast).filter(cl -> !cl.isInterface()).collect(Collectors.toList());

        allClasses.removeAll(TRANSIENT_OBJECTS_WHITE_LIST);

        List<String> invalidEClasses = new ArrayList<>();
        List<String> invalidInstances = new ArrayList<>();
        for (EClass eClass : allClasses) {
            Class<?> instanceClass = eClass.getInstanceClass();
            if (!IdentifiedElement.class.isAssignableFrom(instanceClass)) {
                invalidEClasses.add(eClass.getName());
            } else if (!eClass.isAbstract() && !eClass.isInterface()) {
                IdentifiedElement identifiedElement = (IdentifiedElement) EcoreUtil.create(eClass);
              String uid=  identifiedElement.getUid();
               if(StringUtil.isEmpty(uid) || uid.length() != uuidLength) {
                   invalidInstances.add(eClass.getName());
               }
            }
        }

        assertTrue("The following classes should inherit from IdentifiedElement class:\n" + invalidEClasses, invalidEClasses.isEmpty());
        assertTrue("The instances of the following classes should have an uid after creation:\n" + invalidInstances, invalidInstances.isEmpty());
    }

}

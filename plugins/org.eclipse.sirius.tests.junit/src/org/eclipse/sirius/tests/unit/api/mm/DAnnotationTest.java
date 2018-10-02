/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.mm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;

/**
 * Test setting and getting DAnnotation
 * 
 * @author lfasani
 */
public class DAnnotationTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/mm/annotation/";

    private static final String SEMANTIC_MODEL_FILENAME = "annotation.ecore";

    private static final String REPRESENTATIONS_FILENAME = "annotation.aird";

    private static final String REPRESENTATION_DESC_PREFIX_NAME = "root package entities";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + REPRESENTATIONS_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILENAME);
        genericSetUp(Collections.singleton(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME), Collections.emptyList(), TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILENAME);
    }

    /**
     * This method checks that it is possible to set {@link DAnnotation}
     * referencing semantic elements on {@link DRepresentationDescriptor} and to
     * retrieve the impacted {@link DRepresentationDescriptor
     * DRepresentationDescriptors} from the semantic elements.
     * 
     * @throws Exception
     *             in case of problem
     */
    public void testDAnnotationOnDRepDesc() throws Exception {
        // annotation.aird contains 3 DRepresentationDescriptors : "root package
        // entities1", 2 and 3
        // "root package entities1" already contains DAnnotation with a
        // reference to NewEClass1

        // check that "root package entities1" is found inverseCrossreferencing
        // from NewEClass1
        assertEquals("Bad test data", 1, session.getSemanticResources().size());
        EClass newEClass1 = (EClass) session.getSemanticResources().iterator().next().getContents().get(0).eContents().get(0);

        List<DRepresentationDescriptor> dRepDescListToCheck = new ArrayList<>();
        dRepDescListToCheck.add(getRepDesc("1"));
        checkImpactedDRepDesc(newEClass1, dRepDescListToCheck, "dummy");

        // add DAnnotation on "root package entities2" and check the impacted
        // DRepresentationDescriptor
        DRepresentationDescriptor repDesc2 = getRepDesc("2");
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                DAnnotation dAnnotation = DescriptionFactory.eINSTANCE.createDAnnotation();
                dAnnotation.getReferences().add(newEClass1);
                repDesc2.getEAnnotations().add(dAnnotation);
            }
        });
        dRepDescListToCheck.add(getRepDesc("2"));
        checkImpactedDRepDesc(newEClass1, dRepDescListToCheck, null);

        // Check that the result is filtered when a parameter is used with
        // multiple possible impacted DRepresentation
        dRepDescListToCheck.remove(getRepDesc("2"));
        checkImpactedDRepDesc(newEClass1, dRepDescListToCheck, "dummy");
    }

    private void checkImpactedDRepDesc(EObject impactingEObject, List<DRepresentationDescriptor> expectedImpactedDRepDesc, String key) {
        List<DRepresentationDescriptor> impactedRepDescriptorFromDAnnotationData = new EObjectQuery(impactingEObject).getImpactedRepDescriptorFromDAnnotationData(key);
        assertEquals("Bad number of impacted DRepresentationDescriptor", expectedImpactedDRepDesc.size(), impactedRepDescriptorFromDAnnotationData.size());
        for (DRepresentationDescriptor dRepresentationDescriptor : expectedImpactedDRepDesc) {
            assertTrue(dRepresentationDescriptor.getName() + " should be found as impacted DRepresentationDescriptor.", impactedRepDescriptorFromDAnnotationData.contains(dRepresentationDescriptor));
        }
    }

    private DRepresentationDescriptor getRepDesc(String suffix) {
        Iterator<DRepresentationDescriptor> iterator = session.getOwnedViews().iterator().next().getOwnedRepresentationDescriptors().iterator();
        while (iterator.hasNext()) {
            DRepresentationDescriptor dRepDesc = iterator.next();
            if (dRepDesc.getName().equals(REPRESENTATION_DESC_PREFIX_NAME + suffix)) {
                return dRepDesc;
            }
        }
        fail("Bad test data");
        return null;
    }
}

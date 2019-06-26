/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.representation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.unit.common.command.RepresentationDeleterRecordingCommand;
import org.eclipse.sirius.tests.unit.diagram.GenericTestCase;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * @author lfasani
 * 
 *         Test Open representation menu.
 */
public class RepresentationCRUDTest extends GenericTestCase {

    private static final String PLUGIN = "/" + SiriusTestsPlugin.PLUGIN_ID;

    private static final String SEMANTIC_MODEL_PATH = PLUGIN + "/data/unit/representation/RepresentationCRUD.ecore";

    private static final String REP_MODEL_PATH = PLUGIN + "/data/unit/representation/representations.aird";

    private static final String DIAGRAM_NAME = "P0packageDiag2";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(Collections.singleton(SEMANTIC_MODEL_PATH), Collections.<String> emptyList(), REP_MODEL_PATH);
    }

    private DRepresentation getRepresentation(String name) {
        for (final DView dView : session.getOwnedViews()) {
            for (final Iterator<DRepresentationDescriptor> iterator = new DViewQuery(dView).getLoadedRepresentationsDescriptors().iterator(); iterator.hasNext();) {
                final DRepresentationDescriptor rep = iterator.next();
                if (name.equals(rep.getName())) {
                    return rep.getRepresentation();
                }
            }
        }
        return null;
    }

    public void testDeleteRepresentation() throws Exception {
        DRepresentation diagram = getRepresentation(DIAGRAM_NAME);
        DRepresentationDescriptor repDesc = new DRepresentationQuery(diagram).getRepresentationDescriptor();
        assertNotNull("The diagram " + DIAGRAM_NAME + " does not exist.", diagram);
        EObject rootPackage = ((DSemanticDecorator) diagram).getTarget();

        // Check that the root package is the diagram target
        checkRepresentation(rootPackage, diagram, true);

        // Delete Rep
        session.getTransactionalEditingDomain().getCommandStack().execute(new RepresentationDeleterRecordingCommand(session.getTransactionalEditingDomain(), repDesc, session));
        checkRepresentation(rootPackage, diagram, false);

        // undo
        undo();
        checkRepresentation(rootPackage, diagram, true);
    }

    /**
     * Check that the {@code representation} is correctly removed/present
     * including removed/prevent from/in the Sirius CrossReferencer.
     * 
     * @param target
     *            the semantic target
     * @param representation
     *            the representation to check
     * @param expected
     *            true if representation have to be found
     */
    private void checkRepresentation(EObject target, DRepresentation representation, boolean expected) {
        Collection<DRepresentation> representations = new ArrayList<>();
        ECrossReferenceAdapter xref = session.getSemanticCrossReferencer();
        for (EStructuralFeature.Setting setting : xref.getInverseReferences(target)) {
            if (ViewpointPackage.Literals.DREPRESENTATION.isInstance(setting.getEObject()) && setting.getEStructuralFeature() == ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET) {
                representations.add((DRepresentation) setting.getEObject());
            }
        }
        assertEquals("Can " + (expected ? "not " : "") + "find " + DIAGRAM_NAME + " DRepresentation with sirius cross referencer", expected, representations.contains(representation));

        representations = DialectManager.INSTANCE.getRepresentations(target, session);
        assertEquals("Can " + (expected ? "not " : "") + "find " + DIAGRAM_NAME + " DRepresentation with DialectManager.INSTANCE.getRepresentations", expected,
                representations.contains(representation));
    }
}

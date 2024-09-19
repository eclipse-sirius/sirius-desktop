/*******************************************************************************
 * Copyright (c) 2016, 2024 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.common.command.RepresentationDeleterRecordingCommand;
import org.eclipse.sirius.tests.unit.diagram.GenericTestCase;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.ui.IEditorPart;

/**
 * @author lfasani
 * 
 *         Test Open representation menu.
 */
public class RepresentationCRUDTest extends GenericTestCase {

    private static final String PATH = "/data/unit/representation/";

    private static final String SEMANTIC_MODEL_FILENAME = "RepresentationCRUD.ecore";

    private static final String SESSION_MODEL_FILENAME = "representations.aird";

    private static final String REP_MODEL_PATH = TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME;

    private static final String SEMANTIC_MODEL_FILENAME_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME;

    private static final String DIAGRAM_NAME = "P0packageDiag2";

    private boolean oldPropertyValue;

    protected static final String MODELER_PATH = "org.eclipse.sirius.sample.ecore.design/description/ecore.odesign";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME, SESSION_MODEL_FILENAME);
        genericSetUp(Collections.singleton(SEMANTIC_MODEL_FILENAME_PATH), Collections.<String> emptyList(), REP_MODEL_PATH);

        loadModeler(toURI(MODELER_PATH, ResourceURIType.RESOURCE_PLUGIN_URI), session.getTransactionalEditingDomain());
        initViewpoint("Design");

        oldPropertyValue = TestsUtil.isCreateRepresentationInSeparateResource();
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
        TestsUtil.synchronizationWithUIThread();
        undo();
        TestsUtil.synchronizationWithUIThread();
        checkRepresentation(rootPackage, diagram, true);
    }

    /**
     * Check that the {@code representation} is correctly removed/present including removed/prevent from/in the Sirius
     * CrossReferencer.
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

    /**
     * Checks that opening or adding a new representation will not make the other representations loaded.
     * 
     * @throws Exception
     */
    public void testRepLazyLoadAtRepCRUD() throws Exception {
        TestsUtil.setCreateRepresentationInSeparateResource(true);

        EObject rootPackage = session.getSemanticResources().stream().findFirst().map(res -> res.getContents().get(0)).get();
        createRepresentation("Entities", rootPackage);
        session.save(new NullProgressMonitor());
        session.close(new NullProgressMonitor());

        genericSetUp(Collections.singleton(SEMANTIC_MODEL_FILENAME_PATH), Collections.<String> emptyList(), REP_MODEL_PATH);

        rootPackage = session.getSemanticResources().stream().findFirst().map(res -> res.getContents().get(0)).get();
        loadModeler(toURI(MODELER_PATH, ResourceURIType.RESOURCE_PLUGIN_URI), session.getTransactionalEditingDomain());
        initViewpoint("Design");

        // 1- Check initial loading state
        // there are now 2 representations in the aird and 1 in its srm
        checkNumberOfLoadedRepresentations(2);

        // 2- Check after a representation has been opened
        DRepresentation diagram = getRepresentation(DIAGRAM_NAME);

        IEditorPart openEditor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // The representation in the srm is not loaded
        checkNumberOfLoadedRepresentations(2);

        // 3- Check after a representation has been created
        createRepresentation("Entities", rootPackage);

        // The representation in the srm is not loaded
        checkNumberOfLoadedRepresentations(3);

        // Save and close the diagram
        session.save(new NullProgressMonitor());
        checkNumberOfLoadedRepresentations(3);
        DialectUIManager.INSTANCE.closeEditor(openEditor, true);
    }

    private void checkNumberOfLoadedRepresentations(int expectedNbOfLoadedReps) {
        Collection<DRepresentationDescriptor> allRepresentationDescriptors = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);

        long nbLoadedRepresentation = allRepresentationDescriptors.stream().filter(repDesc -> repDesc.isLoadedRepresentation()).count();
        assertEquals("Bad number of loaded representations", expectedNbOfLoadedReps, nbLoadedRepresentation);
    }

    @Override
    protected void tearDown() throws Exception {
        try {
            super.tearDown();
        } finally {
            TestsUtil.setCreateRepresentationInSeparateResource(oldPropertyValue);
        }
    }
}

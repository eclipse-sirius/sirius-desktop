/*******************************************************************************
 * Copyright (c) 2020, 2021 Obeo.
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
package org.eclipse.sirius.tests.unit.diagram.format.data;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.format.MappingBasedSiriusFormatManagerFactory;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.format.data.manager.mappingbased.MappingBasedTestConfiguration;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

public class MappingBasedSiriusFormatDataManagerCallCheckTest extends AbstractMappingBasedSiriusFormatDataManagerTest {

    protected static final Diagram MB_DIAG_TYPE8_MYPACKAGE = new Diagram("DiagType8 of MyPackage", 16, 0);

    protected static final Diagram MB_DIAG_TYPE8_RAW = new Diagram("DiagType8 Raw of targetMyPackage", 16, 0, true);

    protected static final Representation MB_REPRES_TYPE8 = new Representation("DiagType8", MB_DIAG_TYPE8_MYPACKAGE, MB_DIAG_TYPE8_RAW);

    protected static final Diagram MB_DIAG_TYPE10_MYPACKAGE = new Diagram("DiagType10 of MyPackage", 16, 2);

    protected static final Diagram MB_DIAG_TYPE10_RAW = new Diagram("DiagType10 Raw of targetMyPackage", 16, 2, true);

    protected static final Representation MB_REPRES_TYPE10 = new Representation("DiagType10", MB_DIAG_TYPE10_MYPACKAGE, MB_DIAG_TYPE10_RAW);

    protected static final Predicate<Diagram> ONLY_RAW_DIAGRAM = new Predicate<Diagram>() {

        @Override
        public boolean test(final Diagram input) {
            return input.raw;
        }
    };

    @Parameters
    public static Collection<Object[]> data() {
        // We could use @Theories and @Datapoints but the theory stops as soon
        // as there is a failure. With parameters, we have feedback for all
        // scenarii.
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[] { MB_REPRES_TYPE8 });
        return data;
    }

    public MappingBasedSiriusFormatDataManagerCallCheckTest(Representation representationToCopyFormat) throws Exception {
        super(representationToCopyFormat);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testCallCheckEmptyMapping() throws Exception {
        if (TestsUtil.shouldSkipLongTests()) {
            return;
        }

        final DDiagram diagramToCopyFormat = getDiagram(MB_REPRES_TYPE8, MB_DIAG_TYPE8_MYPACKAGE);
        final DDiagram diagramToPasteFormat = getDiagram(MB_REPRES_TYPE8, MB_DIAG_TYPE8_RAW);

        try {
            applyPredefinedFormatDataOnRawDiagrams(diagramToCopyFormat, diagramToPasteFormat, getEmptyTestConfiguration(), session, session);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), Messages.MappingBasedSiriusFormatManagerFactory_ErrorMappingfunctionIsEmpty);
        }

        try {
            applyPredefinedFormatDataOnNewDiagram(diagramToCopyFormat, getEmptyTestConfiguration(), diagramToPasteFormat.getName(), diagramToPasteFormat.getDiagramElements().get(0), session, session);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), Messages.MappingBasedSiriusFormatManagerFactory_ErrorMappingfunctionIsEmpty);
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testCallCheckSameDiagrams() throws Exception {
        if (TestsUtil.shouldSkipLongTests()) {
            return;
        }

        final DDiagram diagramToCopyFormat = getDiagram(MB_REPRES_TYPE8, MB_DIAG_TYPE8_MYPACKAGE);
        final DDiagram diagramToPasteFormat = getDiagram(MB_REPRES_TYPE8, MB_DIAG_TYPE8_MYPACKAGE);

        try {
            applyPredefinedFormatDataOnRawDiagrams(diagramToCopyFormat, diagramToPasteFormat, getFullTestConfiguration(), session, session);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), Messages.MappingBasedSiriusFormatManagerFactory_ErrorSourceAndTargetDiagramsAreTheSame);
        }

        try {
            applyPredefinedFormatDataOnNewDiagram(diagramToCopyFormat, getFullTestConfiguration(), diagramToPasteFormat.getName(), diagramToPasteFormat.getDiagramElements().get(0), session, session);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), Messages.MappingBasedSiriusFormatManagerFactory_ErrorSourceAndTargetDiagramsAreTheSame);
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testCallCheckNullDiagrams() throws Exception {
        if (TestsUtil.shouldSkipLongTests()) {
            return;
        }

        final DDiagram diagramToCopyFormat = null;
        final DDiagram diagramToPasteFormat = getDiagram(MB_REPRES_TYPE8, MB_DIAG_TYPE8_MYPACKAGE);

        try {
            applyPredefinedFormatDataOnRawDiagrams(diagramToCopyFormat, diagramToPasteFormat, getFullTestConfiguration(), session, session);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), Messages.MappingBasedSiriusFormatManagerFactory_ErrorDiagramIsNull);
        }

        try {
            applyPredefinedFormatDataOnRawDiagrams(diagramToPasteFormat, diagramToCopyFormat, getFullTestConfiguration(), session, session);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), Messages.MappingBasedSiriusFormatManagerFactory_ErrorDiagramIsNull);
        }

        try {
            applyPredefinedFormatDataOnNewDiagram(diagramToCopyFormat, getFullTestConfiguration(), diagramToPasteFormat.getName(), diagramToPasteFormat.getDiagramElements().get(0), session, session);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), Messages.MappingBasedSiriusFormatManagerFactory_ErrorDiagramIsNull);
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testCallCheckNullOrEmptyDiagramName() throws Exception {
        if (TestsUtil.shouldSkipLongTests()) {
            return;
        }

        final DDiagram diagramToCopyFormat = getDiagram(MB_REPRES_TYPE8, MB_DIAG_TYPE8_MYPACKAGE);
        final DDiagram diagramToPasteFormat = getDiagram(MB_REPRES_TYPE8, MB_DIAG_TYPE8_MYPACKAGE);

        try {
            applyPredefinedFormatDataOnNewDiagram(diagramToCopyFormat, getFullTestConfiguration(), "", diagramToPasteFormat.getDiagramElements().get(0), session, session);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), Messages.MappingBasedSiriusFormatManagerFactory_ErrorTargetDiagramNameIsEmpty);
        }

        try {
            applyPredefinedFormatDataOnNewDiagram(diagramToCopyFormat, getFullTestConfiguration(), null, diagramToPasteFormat.getDiagramElements().get(0), session, session);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), Messages.MappingBasedSiriusFormatManagerFactory_ErrorTargetDiagramNameIsEmpty);
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testCallCheckDescriptionMatch() throws Exception {
        if (TestsUtil.shouldSkipLongTests()) {
            return;
        }

        final DDiagram diagramToCopyFormat = getDiagram(MB_REPRES_TYPE8, MB_DIAG_TYPE8_MYPACKAGE);
        final DDiagram diagramToPasteFormat = getDiagram(MB_REPRES_TYPE10, MB_DIAG_TYPE10_MYPACKAGE);

        try {
            applyPredefinedFormatDataOnRawDiagrams(diagramToCopyFormat, diagramToPasteFormat, getFullTestConfiguration(), session, session);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), MessageFormat.format(Messages.MappingBasedSiriusFormatManagerFactory_ErrorSourceAndTargetDiagramDecriptionsDoesNotMatch,
                    diagramToCopyFormat.getDescription().getName(), diagramToPasteFormat.getDescription().getName(), diagramToCopyFormat.getDescription(), diagramToPasteFormat.getDescription()));
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testCallCheckSessions() throws Exception {
        if (TestsUtil.shouldSkipLongTests()) {
            return;
        }

        final DDiagram diagramToCopyFormat = getDiagram(MB_REPRES_TYPE8, MB_DIAG_TYPE8_MYPACKAGE);
        final DDiagram diagramToPasteFormat = getDiagram(MB_REPRES_TYPE8, MB_DIAG_TYPE8_RAW);

        try {
            applyPredefinedFormatDataOnRawDiagrams(diagramToCopyFormat, diagramToPasteFormat, getFullTestConfiguration(), session, null);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), Messages.MappingBasedSiriusFormatManagerFactory_ErrorSourceAndOrTargetSessionsNull);
        }

        try {
            applyPredefinedFormatDataOnRawDiagrams(diagramToCopyFormat, diagramToPasteFormat, getFullTestConfiguration(), null, session);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), Messages.MappingBasedSiriusFormatManagerFactory_ErrorSourceAndOrTargetSessionsNull);
        }

        try {
            applyPredefinedFormatDataOnNewDiagram(diagramToCopyFormat, getFullTestConfiguration(), diagramToPasteFormat.getName(), diagramToPasteFormat.getDiagramElements().get(0), session, null);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), Messages.MappingBasedSiriusFormatManagerFactory_ErrorSourceAndOrTargetSessionsNull);
        }

        try {
            applyPredefinedFormatDataOnNewDiagram(diagramToCopyFormat, getFullTestConfiguration(), diagramToPasteFormat.getName(), diagramToPasteFormat.getDiagramElements().get(0), null, session);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), Messages.MappingBasedSiriusFormatManagerFactory_ErrorSourceAndOrTargetSessionsNull);
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testCallCheckNullTargetDiagramRoot() throws Exception {
        if (TestsUtil.shouldSkipLongTests()) {
            return;
        }

        final DDiagram diagramToCopyFormat = getDiagram(MB_REPRES_TYPE8, MB_DIAG_TYPE8_MYPACKAGE);
        final DDiagram diagramToPasteFormat = getDiagram(MB_REPRES_TYPE8, MB_DIAG_TYPE8_RAW);

        try {
            applyPredefinedFormatDataOnNewDiagram(diagramToCopyFormat, getFullTestConfiguration(), diagramToPasteFormat.getName(), null, session, session);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), Messages.MappingBasedSiriusFormatManagerFactory_ErrorTargetDiagramRootIsNull);
        }
    }

    private DDiagram getDiagram(Representation rep, Diagram diag) {

        List<DRepresentationDescriptor> allDDiagramDescriptors = getRepresentationDescriptors(rep.name, session).stream().collect(Collectors.toList());
        Collections.sort(allDDiagramDescriptors, USING_NAME);
        DRepresentationDescriptor dRepresentationDescriptorToFind = ViewpointFactory.eINSTANCE.createDRepresentationDescriptor();
        dRepresentationDescriptorToFind.setName(diag.name);
        final int search = Collections.binarySearch(allDDiagramDescriptors, dRepresentationDescriptorToFind, USING_NAME);

        assertTrue("Source Diagram " + dRepresentationDescriptorToFind.getName() + " is not found in representation", search > -1);

        return (DDiagram) allDDiagramDescriptors.get(search).getRepresentation();
    }

    protected void applyPredefinedFormatDataOnRawDiagrams(DDiagram diagramToCopyFormat, DDiagram diagramToPasteFormat, MappingBasedTestConfiguration mapTestConfiguration, Session sourceSession,
            Session targetSession) throws Exception {

        Map<EObject, EObject> map = mapTestConfiguration.getObjectsMap();

        final RecordingCommand command = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                MappingBasedSiriusFormatManagerFactory.getInstance().applyFormatOnDiagram(sourceSession, diagramToCopyFormat, map, targetSession, diagramToPasteFormat, false);
            }
        };

        // Execute the command to see if there is a problem
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        // Undo the command to let the session, more or less, in its previous state
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();
    }

    protected void applyPredefinedFormatDataOnNewDiagram(DDiagram diagramToCopyFormat, MappingBasedTestConfiguration mapTestConfiguration, String diagramName, EObject targetDiagramRoot,
            Session sourceSession, Session targetSession) throws Exception {

        Map<EObject, EObject> map = mapTestConfiguration.getObjectsMap();

        final RecordingCommand command = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                MappingBasedSiriusFormatManagerFactory.getInstance().applyFormatOnNewDiagram(sourceSession, diagramToCopyFormat, map, targetSession, diagramName, targetDiagramRoot, false);
            }
        };

        // Execute the command to see if there is a problem
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        // Undo the command to let the session, more or less, in its previous state
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Compute configuration for source to target EObjects mapping. Uses none of the model elements.
     * 
     * @return
     */
    protected MappingBasedTestConfiguration getEmptyTestConfiguration() {
        Map<String, String> full_map = new HashMap<String, String>();
        return new MappingBasedTestConfiguration(semanticModel, semanticTargetModel, full_map, null, "empty");
    }

}

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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Test class dedicated to indirect deletion of edges. See https://bugs.eclipse.org/bugs/show_bug.cgi?id=580590
 */
public class IndirectDeleteEdgeWithLabelSelectedTest extends AbstractHideRevealDiagramElementsLabelTest {

    private static final String REPRESENTATION_NAME_1LABEL = "577162-1label"; //$NON-NLS-1$

    private static final String REPRESENTATION_INSTANCE_NAME_1LABEL = "new " + REPRESENTATION_NAME_1LABEL; //$NON-NLS-1$

    private static final String NODE_NAMED_C2 = "C2"; //$NON-NLS-1$

    private static final String EDGE_NAMED_REFTOC1 = "refToC1"; //$NON-NLS-1$

    private static final String MODEL = "577162.ecore"; //$NON-NLS-1$

    private static final String SESSION_FILE = "representations.aird"; //$NON-NLS-1$

    private static final String VSM_FILE = "577162.odesign"; //$NON-NLS-1$

    private static final String DATA_UNIT_DIR = "data/unit/tools/hide-reveal/577162/"; //$NON-NLS-1$

    private EPackage semanticRoot;

    private EClass eClassC2;

    private EReference eReferenceRefToC1;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        semanticRoot = (EPackage) localSession.getOpenedSession().getSemanticResources().iterator().next().getContents().get(0);
        eClassC2 = (EClass) semanticRoot.getEClassifier(NODE_NAMED_C2);
        eReferenceRefToC1 = eClassC2.getEReferences().get(0);
    }

    /**
     * Ensures that indirect deletion of an edge whose label is selected does not throw NPEs which make the edit parts
     * de-activation fail.
     */
    public void testHideRevealWithSelectionOfEdgeAndLabelDifferentEdge_1Labels() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_1LABEL, REPRESENTATION_INSTANCE_NAME_1LABEL, DDiagram.class);

        // Select the edge refToC2 and the given label of the other edge, hide labels
        SWTBotGefEditPart editPartREFTOC1 = getSWTBotGefEditPart(eReferenceRefToC1);
        SWTBotGefEditPart editPartLabel = editor.getEditPart(EDGE_NAMED_REFTOC1);
        checkAllEdgeLabelsAreVisible(editPartREFTOC1);
        editor.select(editPartLabel);

        assertTrue("Edge label must be selected", editPartLabel.part() == ((IStructuredSelection) editor.getSelection()).iterator().next()); //$NON-NLS-1$

        errors.clear();

        ICondition done = new OperationDoneCondition();
        DEdge edge = (DEdge) ((DEdgeNameEditPart) editPartLabel.part()).resolveDiagramElement();
        TransactionalEditingDomain domain = localSession.getOpenedSession().getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                EcoreUtil.delete(edge.getTarget());
            }
        });

        // Waiting for command execution
        bot.waitUntil(done);

        assertNull("Edge must have been deleted.", edge.eContainer()); //$NON-NLS-1$

        // Waiting for ui refresh and selection change in the editor
        done = new CheckSelectedCondition(editor, editor.getDiagramEditPart());
        bot.waitUntil(done);

        Object selection = ((IStructuredSelection) editor.getSelection()).iterator().next(); // $NON-NLS-1$
        assertFalse("Indirect edge deletion must not throw errors", doesAnErrorOccurs()); //$NON-NLS-1$

        assertNotSame("Edge label must not be selected", editPartLabel.part(), selection); //$NON-NLS-1$
        assertEquals("Diagram must be selected.", editor.getDiagramEditPart(), selection); //$NON-NLS-1$

        assertFalse("Edge label edit part must have been deactivated.", editPartLabel.part().isActive()); //$NON-NLS-1$
        assertFalse("Edge edit part must have been deactivated.", editPartREFTOC1.part().isActive()); //$NON-NLS-1$

        assertNull("Edge edit part must have been deleted.", editPartREFTOC1.part().getParent()); //$NON-NLS-1$

    }

    private SWTBotGefEditPart getSWTBotGefEditPart(EReference eReference) {
        try {
            return editor.getEditPart(eReference.getName()).parent();
        } catch (WidgetNotFoundException e) {
            SWTBotGefEditPart sourceEditPart = editor.getEditPart(((EClass) eReference.eContainer()).getName()).parent();
            SWTBotGefEditPart targetEditPart = editor.getEditPart(eReference.getEType().getName()).parent();
            return editor.getConnectionEditPart(sourceEditPart, targetEditPart).get(0);
        }
    }

    /**
     * Ensures that all labels from the edge with the given name are visible.
     * 
     * @param label
     *            the label to search
     */
    private void checkAllEdgeLabelsAreVisible(SWTBotGefEditPart parent) {
        for (SWTBotGefEditPart labelSWTBotGefEditPart : parent.children()) {
            EditPart part = labelSWTBotGefEditPart.part();
            if (part instanceof AbstractDEdgeNameEditPart && !((AbstractDEdgeNameEditPart) part).getLabelText().isBlank()) {
                checkEdgeLabelIsVisible(((AbstractDEdgeNameEditPart) part).getLabelText());
            }
        }
    }

}

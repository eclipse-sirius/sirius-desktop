/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * A class to check the behavior when some semantic elements are deleted outside of Sirius editors.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class EntitiesDiagramDeleteFromOutsideEditorTests extends SiriusDiagramTestCase implements EcoreModeler {

    private DDiagram diagram;

    private IEditorPart editorPart;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, TEST_SEMANTIC_MODEL_PROJECT_RELATIVE_PATH, "/" + TEMPORARY_PROJECT_NAME + "/" + TEST_SEMANTIC_MODEL_FILENAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + TEST_SEMANTIC_MODEL_FILENAME, MODELER_PATH);

        initViewpoint(DESIGN_VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        TestsUtil.emptyEventsFromUIThread();
        assertTrue("The editor should be of type DialectEditor.", editorPart instanceof DialectEditor);
        // Disable dialogs with an automatic reloading/
        disableUICallBackOnDialectEditor((DialectEditor) editorPart);
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

    /**
     * This test check that there is no message in the error log when we select an element in the diagram that have its
     * target deleted outside the editor (in another resourceSet).
     */
    public void testNoMsgInErrorLogWhenSelectedADNodeListWithProxyTarget() {
        // Check the entry data
        EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model must be empty before the tool application.", ePackage.getEClassifiers().isEmpty());
        // Add a class in this package with diagram tool and save the session
        applyNodeCreationTool("Class", diagram, diagram);
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        refresh(diagram);
        session.save(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        // Load the semantic resource in another resource set, delete the
        // class and save the resource.
        TransactionalEditingDomain domain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        ResourceSet set = domain.getResourceSet();
        try {
            final EPackage ePackageInAnotherResourceSet = (EPackage) ModelUtils.load(ePackage.eResource().getURI(), set);
            assertFalse("The editing domain of each root semantic must be different.", domain.equals(TransactionUtil.getEditingDomain(ePackage)));

            domain.getCommandStack().execute(new RecordingCommand(domain, "Remove all classes") {

                @Override
                protected void doExecute() {
                    ePackageInAnotherResourceSet.getEClassifiers().clear();
                }
            });
            ePackageInAnotherResourceSet.eResource().save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            fail("Pb when saving the resource in another resourceSet : " + e.getMessage());
        }
        TestsUtil.synchronizationWithUIThread();

        // Activate the error catching (to detect msg during selection)
        platformProblemsListener.setErrorCatchActive(true);
        // Select the corresponding element in the diagram
        final DDiagramElement diagramElement = getFirstDiagramElement(diagram, eClass);
        final IGraphicalEditPart editPart = getEditPart(diagramElement, editorPart);
        editPart.setFocus(true);

        // Deactivate the error catching (the error detection is done during the
        // super.tearDown)
        platformProblemsListener.setErrorCatchActive(false);
    }
}

/*******************************************************************************
 * Copyright (c) 2016, 2024 Obeo.
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
package org.eclipse.sirius.tests.unit.multipageeditor;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.diagram.tools.api.command.DiagramCommandFactoryService;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.MultiPageEditorPart;

/**
 * Tests the Sirius editors behavior inside a {@link MultiPageEditorPart} that is inside an IWorkbenchPartSite with
 * empty id.
 * 
 * This only test we don't have NPE when the VSM editor is included in a part not providing id. But we don't officially
 * support this kind of integration.
 * 
 * See 502053
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class SiriusMultiPageEditorTest extends SiriusTestCase {
    private static final String SIRIUS_MULTI_PAGE_EDITOR_ID = "org.eclipse.sirius.tests.unit.SiriusMultiPageEditor";

    public static final String PATH = "/data/unit/initialization/";

    private static final String SEMANTIC_MODEL_FILENAME = "/org.eclipse.sirius.tests.junit/data/unit/layoutingMode/vp2120.ecore";

    private static final String MODELER_MODEL_FILENAME = "/org.eclipse.sirius.tests.junit/data/unit/layoutingMode/vp2120.odesign";

    private static final String SESSION_PATH = "/org.eclipse.sirius.tests.junit/data/unit/layoutingMode/vp2120.aird";

    private IDiagramCommandFactory commandFactory;

    private IEditorPart openEditor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_FILENAME, MODELER_MODEL_FILENAME, SESSION_PATH);

    }

    @Override
    protected void tearDown() throws Exception {
        if (openEditor != null) {
            openEditor.dispose();
        }
        super.tearDown();

    }

    /**
     * Tests that {@link DDiagramEditorImpl} opens correctly inside a {@link MultiPageEditorPart} without exceptions.
     * This tests particularly the behavior of the Sirius editor opening in case it is embedded inside a part inside a
     * IWorkbenchPartSite that provides empty id. See 502053.
     * 
     * @throws Exception
     */
    public void testDiagramEditorOpeningInsideMultiPageEditor() throws Exception {
        DRepresentationDescriptor representationDescriptor = getRepresentationDescriptors("LayoutingMode Diagram").iterator().next();
        DRepresentation representation = representationDescriptor.getRepresentation();

        EObject gmfDiag = session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, representation).iterator().next();
        URI uri = EcoreUtil.getURI(gmfDiag);
        String editorName = DialectUIManager.INSTANCE.getEditorName(representation);
        final IEditorInput editorInput = new SessionEditorInput(uri, editorName, session);
        final IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        assertFalse(platformProblemsListener.doesAnErrorOccurs());

        RunnableWithResult<IEditorPart> result = new RunnableWithResult<IEditorPart>() {
            private IEditorPart resultEditor;

            @Override
            public void run() {
                try {
                    resultEditor = activePage.openEditor(editorInput, SIRIUS_MULTI_PAGE_EDITOR_ID);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public IEditorPart getResult() {
                return resultEditor;
            }

            @Override
            public void setStatus(IStatus status) {

            }

            @Override
            public IStatus getStatus() {
                return null;
            }
        };
        PlatformUI.getWorkbench().getDisplay().syncExec(result);
        openEditor = result.getResult();
        assertFalse("No error should have occurs during opening of the Sirius Diagram editor inside a multi page editor.", platformProblemsListener.doesAnErrorOccurs());
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        if (commandFactory == null) {
            commandFactory = DiagramCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
            commandFactory.setUserInterfaceCallBack(new NoUICallback());
        }
        return commandFactory;
    }
}

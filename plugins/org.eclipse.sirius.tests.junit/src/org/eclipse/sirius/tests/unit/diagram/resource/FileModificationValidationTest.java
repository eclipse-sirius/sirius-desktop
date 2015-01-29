/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.resource;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.common.tools.api.resource.IFileModificationValidator;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

public class FileModificationValidationTest extends SiriusDiagramTestCase implements EcoreModeler {

    private IFileModificationValidator mock;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(PACKAGES_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);

        mock = createMock(IFileModificationValidator.class);
        ResourceSetSync.getOrInstallResourceSetSync(session.getTransactionalEditingDomain()).addFileModificationValidator(mock);
    }

    /**
     * Check that the specific validator code is not called when there is no
     * read only files.
     * 
     * @throws Exception
     *             If problem
     */
    public void testFileModificationValidationReadWrite() throws Exception {
        DDiagram diagram = (DDiagram) createRepresentation(ENTITIES_DESC_NAME, semanticModel);
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // start recording

        // No read only files, the validator is not called

        replay(mock);
        // stop recording

        applyNodeCreationTool("Class", diagram, diagram);

        session.save(new NullProgressMonitor());

        verify(mock);
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }
}

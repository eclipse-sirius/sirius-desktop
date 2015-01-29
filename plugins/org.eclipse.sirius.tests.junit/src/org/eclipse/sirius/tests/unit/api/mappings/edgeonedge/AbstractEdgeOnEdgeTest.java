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
package org.eclipse.sirius.tests.unit.api.mappings.edgeonedge;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * An abstract class that provides facilities for manipulating and testing Edges
 * on Edges.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class AbstractEdgeOnEdgeTest extends SiriusDiagramTestCase {

    protected static final String FOLDER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/mappings/edges_on_edges/";

    private static final String DEFAULT_SEMANTIC_MODEL_PATH = FOLDER_PATH + "2182.ecore";

    private static final String DEFAULT_SESSION_FILE_PATH = FOLDER_PATH + "2182.aird";

    private static final String DEFAULT_MODELER_PATH = FOLDER_PATH + "2182.odesign";

    private static final String VIEWPOINT_NAME = "doremi_2182";

    protected static final String REPRESENTATION_DECRIPTION_NAME = "doremi-2182_TC1";

    protected DDiagram diagram;

    protected IEditorPart editor;

    protected EPackage semanticRoot;

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.support.api.SiriusTestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(getSemanticModelPath(), getModelerPath(), getSessionFilePath());
        initViewpoint(VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DECRIPTION_NAME).toArray()[0];
        assertNotNull(diagram);
        editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        TestsUtil.emptyEventsFromUIThread();
        semanticRoot = (EPackage) semanticModel;
    }

    /**
     * Returns the Annotation held by the semanticRoot or the given container if
     * none find, with the given source.
     * 
     * @param container
     *            the container to search the annotation into if none found in
     *            the semantic Root
     * @param source
     *            the searched source
     * @return the Annotation held by the semanticRoot or the given container if
     *         none find, with the given source
     */
    protected EAnnotation getAnnotationFromSource(EModelElement container, String source) {
        EAnnotation returnedAnnotation = semanticRoot.getEAnnotation(source);
        if (returnedAnnotation == null) {
            returnedAnnotation = container.getEAnnotation(source);
        }
        return returnedAnnotation;
    }

    /**
     * Unsynchronize all mappings and diagram except the mapping with the given
     * name.
     * 
     * @param mappingName
     *            the mapping name
     */
    protected void unsynchronizeAllMappingsExcept(String... mappingNames) {
        for (String mappingName : mappingNames) {
            DiagramElementMapping mapping = null;
            try {
                mapping = getNodeMapping(getLayer(diagram, "Default"), mappingName);
            } catch (IllegalArgumentException e) {
                mapping = getEdgeMapping(getLayer(diagram, "Default"), mappingName);
            }
            final DiagramElementMapping repMapping = mapping;
            session.getTransactionalEditingDomain().getCommandStack().execute(new SiriusCommand(session.getTransactionalEditingDomain(), "Unsynchronizing " + mappingName) {
                @Override
                protected void doExecute() {
                    repMapping.setSynchronizationLock(true);
                }
            });
        }

        unsynchronizeDiagram(diagram);
    }

    /**
     * Closes the current editor and opens a new one on the same diagram.
     */
    protected void closeAndReopenEditor() {
        DialectUIManager.INSTANCE.closeEditor(editor, true);
        TestsUtil.emptyEventsFromUIThread();

        diagram = (DDiagram) getRepresentations(REPRESENTATION_DECRIPTION_NAME).toArray()[0];
        assertNotNull(diagram);
        editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        TestsUtil.emptyEventsFromUIThread();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.support.api.SiriusTestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.emptyEventsFromUIThread();
        diagram = null;
        editor = null;
        semanticRoot = null;
        super.tearDown();
    }

    /**
     * Returns the path of the aird file to open. Subclasses can override this
     * method.
     * 
     * @return the path of the aird file to open
     */
    protected String getSessionFilePath() {
        return DEFAULT_SESSION_FILE_PATH;
    }

    /**
     * Returns the path of the semantic model file to open. Subclasses can
     * override this method.
     * 
     * @return the path of the semantic model file to open
     */
    protected String getSemanticModelPath() {
        return DEFAULT_SEMANTIC_MODEL_PATH;
    }

    /**
     * Returns the path of the modeler path to use. Subclasses can override this
     * method.
     * 
     * @return the path of the semantic model file to open
     */
    protected String getModelerPath() {
        return DEFAULT_MODELER_PATH;
    }
}

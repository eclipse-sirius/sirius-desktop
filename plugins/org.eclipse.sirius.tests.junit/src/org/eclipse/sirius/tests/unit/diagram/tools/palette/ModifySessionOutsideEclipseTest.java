/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.tools.palette;

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.diagram.ui.tools.internal.palette.SectionPaletteDrawer;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

import com.google.common.collect.Iterables;

/**
 * This class test, modifies the session outside of eclipse and checks that the
 * behavior is OK (ie no exception when session is modified outside of Eclipse).
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class ModifySessionOutsideEclipseTest extends AbstractPaletteManagerTest {

    private static final String MODIFY_SESSION_PATH = "/data/unit/session/modifySession/";

    private static final String PATH_COPY = "/data/unit/session/modifySession/copy/";

    protected static final String MODELER_PATH = "My.odesign";

    private static final String SEMANTIC_RESOURCE_NAME = "My.ecore";

    private static final String SESSION_RESOURCE_NAME = "representations.aird";

    private DDiagramEditorImpl editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, MODIFY_SESSION_PATH, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME, MODELER_PATH);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_PATH, TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);

        setSiriusDiagramAndGMFDiagram();
    }

    /**
     * Ensure that when a session is modified outside of Eclipse, there is no
     * error and the palette's components are already present.
     * <ol>
     * <li>Open a representation</li>
     * <li>Copy paste of the session, corresponds to the modification of a file
     * outside of Eclipse</li>
     * <li>Check that the palette is correctly composed</li>
     * </ol>
     * 
     * @throws Exception
     */
    public void testModifySessionOutsideEclipse() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /* 
            java.util.ConcurrentModificationException
            at org.eclipse.emf.common.util.AbstractEList$EIterator.checkModCount(AbstractEList.java:758)
            at org.eclipse.emf.common.util.AbstractEList$EIterator.doNext(AbstractEList.java:706)
            at org.eclipse.emf.common.util.AbstractEList$EIterator.next(AbstractEList.java:692)
            at java.util.AbstractCollection.addAll(AbstractCollection.java:305)
            at java.util.LinkedHashSet.<init>(LinkedHashSet.java:152)
            at com.google.common.collect.Sets.newLinkedHashSet(Sets.java:325)
            at org.eclipse.sirius.business.internal.session.SessionTransientAttachment.getSessionTransientAttachement(SessionTransientAttachment.java:72)
            at org.eclipse.sirius.business.internal.session.SessionManagerImpl.getSession(SessionManagerImpl.java:213)
            at org.eclipse.sirius.business.api.query.EObjectQuery.getSession(EObjectQuery.java:198)
            at org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation.removeNavigateDecoratorRefresher(DiagramElementEditPartOperation.java:451)
            at org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation.deactivate(DiagramElementEditPartOperation.java:479)
            at org.eclipse.sirius.diagram.ui.edit.api.part.AbstractBorderedDiagramElementEditPart.deactivate(AbstractBorderedDiagramElementEditPart.java:194)
            at org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart.deactivate(DNode3EditPart.java:107)
            at org.eclipse.gef.editparts.AbstractEditPart.deactivate(AbstractEditPart.java:293)
            at org.eclipse.gef.editparts.AbstractGraphicalEditPart.deactivate(AbstractGraphicalEditPart.java:354)
            at org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart.deactivate(GraphicalEditPart.java:315)
            at org.eclipse.gef.editparts.AbstractEditPart.deactivate(AbstractEditPart.java:293)
            at org.eclipse.gef.editparts.AbstractGraphicalEditPart.deactivate(AbstractGraphicalEditPart.java:354)
            at org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart.deactivate(GraphicalEditPart.java:315)
            at org.eclipse.sirius.diagram.ui.edit.api.part.AbstractBorderedDiagramElementEditPart.deactivate(AbstractBorderedDiagramElementEditPart.java:198)
            at org.eclipse.gef.editparts.AbstractEditPart.deactivate(AbstractEditPart.java:293)
            at org.eclipse.gef.editparts.AbstractGraphicalEditPart.deactivate(AbstractGraphicalEditPart.java:354)
            at org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart.deactivate(GraphicalEditPart.java:315)
            at org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDDiagramEditPart.deactivate(AbstractDDiagramEditPart.java:399)
            at org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart.deactivate(DDiagramEditPart.java:93)
            at org.eclipse.sirius.diagram.ui.business.internal.dialect.DiagramDialectUIServices.closeEditor(DiagramDialectUIServices.java:291)
            at org.eclipse.sirius.ui.business.internal.dialect.DialectUIManagerImpl.closeEditor(DialectUIManagerImpl.java:200)
            at org.eclipse.sirius.tests.unit.diagram.tools.palette.ModifySessionOutsideEclipseTest.tearDown(ModifySessionOutsideEclipseTest.java:131)
                 */
            return;
        }
        // Open representation
        editor = (DDiagramEditorImpl) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Retrieve Pallette
        PaletteRoot paletteRoot = ((DiagramEditDomain) editor.getDiagramEditDomain()).getPaletteViewer().getPaletteRoot();

        // Retrieve Pallette's components
        SectionPaletteDrawer sectionEntry = getSectionEntry(paletteRoot);

        final String pluginFilePath = PATH_COPY + SESSION_RESOURCE_NAME;
        final String wksPath = TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME;
        // Modify the session outside of eclipse (Copy the same file as existing
        // in project)
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, pluginFilePath, wksPath);
        TestsUtil.synchronizationWithUIThread();

        // Retrieve Pallette's components after reload
        SectionPaletteDrawer sectionEntryAfterReload = getSectionEntry(paletteRoot);

        // Check the palette is correctly composed
        assertEquals("The palette should be the same as before copy/paste", sectionEntry, sectionEntryAfterReload);
    }

    /**
     * Set the GMF diagram variable according to current session, description
     * name, representation name.
     */
    private void setSiriusDiagramAndGMFDiagram() {
        Collection<DRepresentationDescriptor> representationDescriptors = getRepresentationDescriptors(getRepresentationDescriptionName());
        for (DRepresentationDescriptor representationDescriptor : representationDescriptors) {
            if (representationDescriptor.getName().equals(getRepresentationDescriptionInstanceName())) {
                dDiagram = (DDiagram) representationDescriptor.getRepresentation();
                break;
            }
        }
        assertNotNull("DDiagram not found", dDiagram);
        Option<Diagram> optionalDiagram = new DDiagramGraphicalQuery(dDiagram).getAssociatedGMFDiagram();
        assertTrue("No GMF Diagram is associated to the current Sirius DDiagram.", optionalDiagram.some());
        diagram = optionalDiagram.get();
    }

    private SectionPaletteDrawer getSectionEntry(PaletteRoot paletteRoot) {
        return Iterables.getOnlyElement(Iterables.filter(paletteRoot.getChildren(), SectionPaletteDrawer.class));
    }

    @Override
    protected String getRepresentationDescriptionInstanceName() {
        return "new Diagram";
    }

    @Override
    protected String getRepresentationDescriptionName() {
        return "Diagram";
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        editor = null;
        super.tearDown();
    }

}

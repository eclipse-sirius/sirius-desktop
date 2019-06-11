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
 *    Felix Dorner <felix.dorner@gmail.com> - Bug 533002
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.NoteFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.DeleteRepresentationCommand;
import org.eclipse.sirius.business.api.dialect.command.RenameRepresentationCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeList2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusDescriptionCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusDiagramNameCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusNoteEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.ui.PartInitException;

/**
 * Test class for double click tool and navigation operation.
 * 
 * @author smonnier
 */
public class NoteCreationTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "root package entities";

    private static final String REPRESENTATION_NAME = "Entities";

    private static final String CLASSES_REPRESENTATION_NAME = "Classes";

    private static final String MODEL = "2083.ecore";

    private static final String SESSION_FILE = "2083.aird";

    private static final String DATA_UNIT_DIR = "data/unit/tools/creation/note/";

    private static final String FILE_DIR = "/";

    private static final String P2_PACKAGE_NAME = "p2";

    private static final String MY_NOTE = "My Note";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private static final String NOTE_TOOL = "Note";

    private static final String LINK_NOTE_TOOL = "Representation Link";

    private static final String LINK_TARGET_NEW_NAME = "Renamed Representation";

    private static final String SET_TARGET_REPRESENTATION = "Set target representation ...";
    
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        closeOutline();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);

        editor.setSnapToGrid(false);
    }

    @Override
    protected void tearDown() throws Exception {
        // Restore the default zoom level
        editor.click(1, 1); // Set the focus on the diagram
        editor.zoom(ZoomLevel.ZOOM_100);
        // Go to the origin to avoid scroll bar
        editor.scrollTo(0, 0);
        // Reopen outline
        new DesignerViews(bot).openOutlineView();
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInDiagramWithoutScroll() throws Exception {
        testNoteCreationInDiagramWithoutScroll(ZoomLevel.ZOOM_100);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDLinkNoteCreationInDiagramWithoutScroll() throws Exception {
        testNoteCreationInDiagramWithoutScroll(ZoomLevel.ZOOM_100, getDiagramLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTLinkNoteCreationInDiagramWithoutScroll() throws Exception {
        testNoteCreationInDiagramWithoutScroll(ZoomLevel.ZOOM_100, getTableLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInDiagramWithoutScrollAndChangeZoom() throws Exception {
        testNoteCreationInDiagramWithoutScroll(ZoomLevel.ZOOM_50);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDNoteCreationInDiagramWithoutScrollAndChangeZoom() throws Exception {
        testNoteCreationInDiagramWithoutScroll(ZoomLevel.ZOOM_50, getDiagramLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTNoteCreationInDiagramWithoutScrollAndChangeZoom() throws Exception {
        testNoteCreationInDiagramWithoutScroll(ZoomLevel.ZOOM_50, getTableLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInDiagramWithScroll() throws Exception {
        testNoteCreationInDiagramWithScroll(ZoomLevel.ZOOM_100);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDNoteCreationInDiagramWithScroll() throws Exception {
        testNoteCreationInDiagramWithScroll(ZoomLevel.ZOOM_100, getDiagramLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTNoteCreationInDiagramWithScroll() throws Exception {
        testNoteCreationInDiagramWithScroll(ZoomLevel.ZOOM_100, getTableLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInDiagramWithScrollAndChangeZoom() throws Exception {
        testNoteCreationInDiagramWithScroll(ZoomLevel.ZOOM_50);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDNoteCreationInDiagramWithScrollAndChangeZoom() throws Exception {
        testNoteCreationInDiagramWithScroll(ZoomLevel.ZOOM_50, getDiagramLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTNoteCreationInDiagramWithScrollAndChangeZoom() throws Exception {
        testNoteCreationInDiagramWithScroll(ZoomLevel.ZOOM_50, getTableLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInContainerWithoutScroll() throws Exception {
        testNoteCreationInPackageContainer(ZoomLevel.ZOOM_100, "p1");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDNoteCreationInContainerWithoutScroll() throws Exception {
        testNoteCreationInPackageContainer(ZoomLevel.ZOOM_100, "p1", getDiagramLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTNoteCreationInContainerWithoutScroll() throws Exception {
        testNoteCreationInPackageContainer(ZoomLevel.ZOOM_100, "p1", getTableLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInContainerWithoutScrollAndChangeZoom() throws Exception {
        testNoteCreationInPackageContainer(ZoomLevel.ZOOM_50, "p1");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDNoteCreationInContainerWithoutScrollAndChangeZoom() throws Exception {
        testNoteCreationInPackageContainer(ZoomLevel.ZOOM_50, "p1", getDiagramLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTNoteCreationInContainerWithoutScrollAndChangeZoom() throws Exception {
        testNoteCreationInPackageContainer(ZoomLevel.ZOOM_50, "p1", getTableLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInContainerWithScrollInDiagram() throws Exception {
        testNoteCreationInPackageContainer(ZoomLevel.ZOOM_100, "p2");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDNoteCreationInContainerWithScrollInDiagram() throws Exception {
        testNoteCreationInPackageContainer(ZoomLevel.ZOOM_100, "p2", getDiagramLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTNoteCreationInContainerWithScrollInDiagram() throws Exception {
        testNoteCreationInPackageContainer(ZoomLevel.ZOOM_100, "p2", getTableLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInContainerWithScrollInDiagramAndChangeZoom() throws Exception {
        testNoteCreationInPackageContainer(ZoomLevel.ZOOM_50, "p2");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDNoteCreationInContainerWithScrollInDiagramAndChangeZoom() throws Exception {
        testNoteCreationInPackageContainer(ZoomLevel.ZOOM_50, "p2", getDiagramLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTNoteCreationInContainerWithScrollInDiagramAndChangeZoom() throws Exception {
        testNoteCreationInPackageContainer(ZoomLevel.ZOOM_50, "p2", getTableLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInContainerWithScrollInContainer() throws Exception {
        testNoteCreationInClassContainer(ZoomLevel.ZOOM_100, "C31");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDNoteCreationInContainerWithScrollInContainer() throws Exception {
        testNoteCreationInClassContainer(ZoomLevel.ZOOM_100, "C31", getDiagramLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTNoteCreationInContainerWithScrollInContainer() throws Exception {
        testNoteCreationInClassContainer(ZoomLevel.ZOOM_100, "C31", getTableLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInContainerWithScrollInContainerAndChangeZoom() throws Exception {
        testNoteCreationInClassContainer(ZoomLevel.ZOOM_50, "C31");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDNoteCreationInContainerWithScrollInContainerAndChangeZoom() throws Exception {
        testNoteCreationInClassContainer(ZoomLevel.ZOOM_50, "C31", getDiagramLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTNoteCreationInContainerWithScrollInContainerAndChangeZoom() throws Exception {
        testNoteCreationInClassContainer(ZoomLevel.ZOOM_50, "C31", getDiagramLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInContainerWithScrollInContainerAndDiagram() throws Exception {
        testNoteCreationInClassContainer(ZoomLevel.ZOOM_100, "C41");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDNoteCreationInContainerWithScrollInContainerAndDiagram() throws Exception {
        testNoteCreationInClassContainer(ZoomLevel.ZOOM_100, "C41", getDiagramLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTNoteCreationInContainerWithScrollInContainerAndDiagram() throws Exception {
        testNoteCreationInClassContainer(ZoomLevel.ZOOM_100, "C41", getTableLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInContainerWithScrollInContainerAndDiagramAndChangeZoom() throws Exception {
        testNoteCreationInClassContainer(ZoomLevel.ZOOM_50, "C41");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDNoteCreationInContainerWithScrollInContainerAndDiagramAndChangeZoom() throws Exception {
        testNoteCreationInClassContainer(ZoomLevel.ZOOM_50, "C41", getDiagramLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTNoteCreationInContainerWithScrollInContainerAndDiagramAndChangeZoom() throws Exception {
        testNoteCreationInClassContainer(ZoomLevel.ZOOM_50, "C41", getTableLinkTarget());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    private void testNoteCreationInDiagramWithoutScroll(ZoomLevel zoomLevel) throws Exception {
        testNoteCreationInDiagramWithoutScroll(zoomLevel, null);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    private void testNoteCreationInDiagramWithoutScroll(ZoomLevel zoomLevel, DRepresentationDescriptor link) throws Exception {
        editor.zoom(zoomLevel);
        // Get the insertion location for the not
        Point location = new Point(2, 2);
        executeTool(location.getScaled(zoomLevel.getAmount()).x, location.getScaled(zoomLevel.getAmount()).y, link);
        validateNote(location, link);
    }

    private void testNoteCreationInDiagramWithScroll(ZoomLevel zoomLevel) throws Exception {
        testNoteCreationInDiagramWithScroll(zoomLevel, null);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    private void testNoteCreationInDiagramWithScroll(ZoomLevel zoomLevel, DRepresentationDescriptor link) throws Exception {
        editor.zoom(zoomLevel);
        // Reveal p2 (to scroll in the diagram)
        editor.select(editor.getSelectableEditPart(P2_PACKAGE_NAME));
        editor.reveal(editor.getEditPart(P2_PACKAGE_NAME).part());
        // Get the location of p2 package (relative the part visible on the
        // screen)
        Point p2Location = editor.getLocation(P2_PACKAGE_NAME, DNodeContainerEditPart.class);
        // Get the absolute location of p2 package from origin (0, 0)
        Point p2AbsoluteLocation = editor.getAbsoluteLocation(P2_PACKAGE_NAME, DNodeContainerEditPart.class);
        // Get the insertion location for the note (use the relative coordinate,
        // that's what is send to the request in reality)
        Point delta = new Point(-2, -2);
        Point location = p2Location.getTranslated(delta.getScaled(zoomLevel.getAmount()));
        executeTool(location.x, location.y, link);

        // Check note's label alignment
        checkNoteLabelAlignment(editor.getEditPart(MY_NOTE), link);
        validateNote(p2AbsoluteLocation.getTranslated(delta), link);
    }

    private void executeTool(int x, int y, DRepresentationDescriptor link) {
        if (link != null) {
            editor.activateTool(LINK_NOTE_TOOL);
        } else {
            editor.activateTool(NOTE_TOOL);
        }

        editor.click(x, y);

        if (link != null) {
            selectTargetRepresentation(link);
        }

        editor.directEditType(MY_NOTE);
    }

    // find the representation to link in the selection dialog
    private void selectTargetRepresentation(DRepresentationDescriptor link) {
        Viewpoint vp = (Viewpoint) link.getDescription().eContainer();
        int size = DialectManager.INSTANCE.getRepresentations(link.getDescription(), localSession.getOpenedSession()).size();
        SWTBot wizardBot = SWTBotSiriusHelper.getShellBot("Select representation");
        wizardBot.tree().expandNode(vp.getName(), link.getDescription().getName() + " (" + size + ")").getNode(link.getName()).select();
        wizardBot.button("OK").click();
    }

    // doubleclick should open the editor for the targeted representation
    private void validateLinkDoubleclick(DRepresentationDescriptor link) throws PartInitException {
        editor.doubleClick(editor.selectedEditParts().get(0));
        SessionEditorInput seip = (SessionEditorInput) bot.activeEditor().getReference().getEditorInput();
        assertEquals(EcoreUtil.getURI(link), seip.getRepDescUri());
        bot.activeEditor().close();
    }

    private Image getIcon(DRepresentationDescriptor descriptor) {
        AdapterFactoryEditingDomain domain = (AdapterFactoryEditingDomain) AdapterFactoryEditingDomain.getEditingDomainFor(descriptor);
        IItemLabelProvider provider = (IItemLabelProvider) domain.getAdapterFactory().adapt(descriptor, IItemLabelProvider.class);
        return ExtendedImageRegistry.INSTANCE.getImage(provider.getImage(descriptor));
    }

    private void validateNote(Point expectedLocation, DRepresentationDescriptor link) throws PartInitException {

        assertNoteAtLocation(MY_NOTE, expectedLocation);

        assertTrue(editor.selectedEditParts().size() == 1);

        if (link != null) {

            SWTBotGefEditPart note = editor.selectedEditParts().get(0);

            validateDiagramNameCompartment(note, link.getName(), getIcon(link));

            /* Doubleclick must open the target representation */
            validateLinkDoubleclick(link);

            /*
             * Now change the target representation to something else. This loses the selection, so select the note
             * again afterwards
             */
            DRepresentationDescriptor otherLink = getOtherLinkTarget(link);
            editor.clickContextMenu(SET_TARGET_REPRESENTATION);
            selectTargetRepresentation(otherLink);
            note = editor.getEditPart(MY_NOTE).parent().select();

            /* The header must have been updated to the other target name */
            validateDiagramNameCompartment(note, otherLink.getName(), getIcon(otherLink));

            /* Doubleclick must now open the other target */
            validateLinkDoubleclick(otherLink);

            /* Switch back to the original link target and reselect */
            editor.clickContextMenu(SET_TARGET_REPRESENTATION);
            selectTargetRepresentation(link);
            note = editor.getEditPart(MY_NOTE).parent().select();

            /*
             * Rename the target representation, this should update the note header label
             */
            TransactionalEditingDomain ted = localSession.getOpenedSession().getTransactionalEditingDomain();
            ted.getCommandStack().execute(new RenameRepresentationCommand(ted, link, LINK_TARGET_NEW_NAME));
            assertTrue(note.part().isActive());
            validateDiagramNameCompartment(note, LINK_TARGET_NEW_NAME, getIcon(link));

            /*
             * When the target representation is deleted, the note label and icon should reflect this. In particular,
             * the note is no longer deleted automatically, to avoid locks in collab and to give the user a chance to
             * decide what to do with the note text. See also https://bugs.eclipse.org/bugs/show_bug.cgi?id=535648
             */
            ted.getCommandStack().execute(new DeleteRepresentationCommand(localSession.getOpenedSession(), Collections.singleton(link)));

            validateDiagramNameCompartment(note, DiagramUIPlugin.getPlugin().getString("palettetool.linkNote.deletedLabel"),
                    DiagramUIPlugin.getPlugin().getImage(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DELETED_DIAG_ELEM_DECORATOR_ICON)));

            /*
             * When the deletion is undone, the note label and icon should flip back to the old state
             */
            ted.getCommandStack().undo();
            validateDiagramNameCompartment(note, LINK_TARGET_NEW_NAME, getIcon(link));

        }

    }

    private void validateDiagramNameCompartment(SWTBotGefEditPart note, String expectedLabel, Image expectedIcon) {

        SWTBotGefEditPart namePartBot = null;
        for (SWTBotGefEditPart child : note.children()) {
            if (child.part() instanceof SiriusDiagramNameCompartmentEditPart) {
                namePartBot = child;
            }
        }

        assertNotNull("No name compartment edit part found", namePartBot);
        SiriusDiagramNameCompartmentEditPart namePart = (SiriusDiagramNameCompartmentEditPart) namePartBot.part();

        if (expectedLabel != null) {
            bot.waitUntil(new DefaultCondition() {
                @Override
                public boolean test() throws Exception {
                    return expectedLabel.equals(namePart.getLabelDelegate().getText());
                }

                @Override
                public String getFailureMessage() {
                    return "LinkNote name label expected: '" + expectedLabel + "' Was: '" + namePart.getLabelDelegate().getText() + "'";
                }
            });
        }

        if (expectedIcon != null) {
            bot.waitUntil(new DefaultCondition() {

                @Override
                public boolean test() throws Exception {
                    return expectedIcon.equals(namePart.getLabelDelegate().getIcon(0));
                }

                @Override
                public String getFailureMessage() {
                    return "LinkNote name icon expected: '" + expectedIcon + "' Was: '" + namePart.getLabelDelegate().getIcon(0) + "'";
                }
            });
        }
    }

    private DRepresentationDescriptor getDiagramLinkTarget() {
        return getRepresentationDescriptorWithName(localSession.getOpenedSession(), REPRESENTATION_NAME, "link target 1");
    }

    private DRepresentationDescriptor getTableLinkTarget() {
        return getRepresentationDescriptorWithName(localSession.getOpenedSession(), CLASSES_REPRESENTATION_NAME, "link target 2");
    }

    private DRepresentationDescriptor getOtherLinkTarget(DRepresentationDescriptor current) {
        return current == getDiagramLinkTarget() ? getTableLinkTarget() : getDiagramLinkTarget();
    }

    /**
     * 
     */
    private void checkNoteLabelAlignment(SWTBotGefEditPart editPart, DRepresentationDescriptor link) {
        assertNotNull("The edit part shouldn't be null for the note label", editPart);
        EditPart part = editPart.part();
        assertTrue("The edit part of the note label should an instance of SiriusDescriptionCompartmentEditPart", part instanceof SiriusDescriptionCompartmentEditPart);
        // Retrieve the bounds of the Note
        EditPart parentPart = part.getParent();
        assertTrue("The edit part of the note should an instance of SiriusNoteEditPart", parentPart instanceof SiriusNoteEditPart);
        IFigure noteFigure = ((SiriusNoteEditPart) parentPart).getFigure();
        assertTrue("The figure of the note should an instance of NoteFigure", noteFigure instanceof NoteFigure);
        Rectangle noteFigureBounds = noteFigure.getBounds();
        Border noteFigureBorder = noteFigure.getBorder();
        Insets noteFigureInsets = noteFigureBorder.getInsets(noteFigure);
        // Retrieve the bounds of the label of the Note
        IFigure wrapLabelFigure = ((SiriusDescriptionCompartmentEditPart) part).getFigure();
        assertTrue("The figure of the note label should an instance of WrappingLabel", wrapLabelFigure instanceof WrappingLabel);
        List<?> childrenFigure = wrapLabelFigure.getChildren();
        assertSame("The figure of the note label should have only one child", 1, childrenFigure.size());
        Object flowPageFigure = childrenFigure.get(0);
        assertTrue("The figure of the note label child should be an instance of FlowPage", flowPageFigure instanceof FlowPage);
        Rectangle flowPageBounds = ((FlowPage) flowPageFigure).getBounds();
        // The label should be TOP & CENTER aligned.
        int middleOfTheNoteX = noteFigureBounds.x + ((noteFigureBounds.width + noteFigureInsets.left - noteFigureInsets.right) / 2);
        int middleOfTheLabelX = flowPageBounds.getCenter().x;
        assertEquals("The label should be centered in the note", middleOfTheNoteX, middleOfTheLabelX, 2);

        // if there's a link, the text is pushed below the horizontal centerline
        // and the test fails, so test this only for 'normal' notes.
        if (link == null) {
            int middleOfTheNoteY = noteFigureBounds.getCenter().y;
            int beginingOfTheLabelY = flowPageBounds.y;
            int endOfTheLabelY = flowPageBounds.y + flowPageBounds.height;
            assertTrue("The label should be at the top of the note", beginingOfTheLabelY < middleOfTheNoteY);
            assertTrue("The label should be at the top of the note", endOfTheLabelY < middleOfTheNoteY);
        }

        // We do all of this because the following commented lines don't works
        // assertSame("The note label should be centered",
        // PositionConstants.CENTER, ((SiriusDescriptionCompartmentEditPart)
        // part).getLabelDelegate().getTextJustification());
        // assertSame("The note label should be on top", PositionConstants.TOP,
        // ((SiriusDescriptionCompartmentEditPart)
        // part).getLabelDelegate().getAlignment());
    }

    /**
     * Test method.
     * 
     * @param zoomLevel
     *            The zoom factor
     * @param packageNameToReveal
     *            the name of the package to reveal before inserting the note.
     * @throws Exception
     *             Test error.
     */
    private void testNoteCreationInPackageContainer(ZoomLevel zoomLevel, String packageNameToReveal, DRepresentationDescriptor link) throws Exception {
        editor.zoom(zoomLevel);
        // Reveal the package (and eventually scroll in the diagram)
        editor.reveal(packageNameToReveal);
        // Get the location of the package (relative the part visible on the
        // screen)
        Point packageLocation = editor.getLocation(packageNameToReveal, DNodeContainerEditPart.class);
        // Get the absolute location of the package from origin (0, 0)
        Point packageAbsoluteLocation = editor.getAbsoluteLocation(packageNameToReveal, DNodeContainerEditPart.class);
        // Get the insertion location for the note (use the relative coordinate,
        // that's what is send to the request in reality)
        Point delta = new Point(30, 30);
        Point location = packageLocation.getTranslated(delta.getScaled(zoomLevel.getAmount()));

        executeTool(location.x, location.y, link);

        validateNote(packageAbsoluteLocation.getTranslated(delta), link);
    }

    private void testNoteCreationInPackageContainer(ZoomLevel zoomLevel, String packageNameToReveal) throws Exception {
        testNoteCreationInPackageContainer(zoomLevel, packageNameToReveal, (DRepresentationDescriptor) null);
    }

    /**
     * Test method.
     * 
     * @param zoomLevel
     *            The zoom factor
     * @param packageNameToReveal
     *            the name of the package to reveal before inserting the note.
     * @throws Exception
     *             Test error.
     */
    private void testNoteCreationInClassContainer(ZoomLevel zoomLevel, String classNameToReveal, DRepresentationDescriptor link) throws Exception {
        editor.zoom(zoomLevel);
        // Reveal the class (and eventually scroll in the diagram and in the
        // container)
        editor.reveal(classNameToReveal);
        // Get the location of the class (relative the part visible on the
        // screen)
        Point classLocation = editor.getLocation(classNameToReveal, DNodeList2EditPart.class);
        // Get the absolute location of the class from origin (0, 0)
        Point classAbsoluteLocation = editor.getAbsoluteLocation(classNameToReveal, DNodeList2EditPart.class);
        // Get the insertion location for the note (use the relative coordinate,
        // that's what is send to the request in reality)
        Point delta = new Point(-30, -30);
        Point location = classLocation.getTranslated(delta.getScaled(zoomLevel.getAmount()));

        executeTool(location.x, location.y, link);

        validateNote(classAbsoluteLocation.getTranslated(delta), link);
    }

    /**
     * Test method.
     * 
     * @param zoomLevel
     *            The zoom factor
     * @param packageNameToReveal
     *            the name of the package to reveal before inserting the note.
     * @throws Exception
     *             Test error.
     */
    private void testNoteCreationInClassContainer(ZoomLevel zoomLevel, String classNameToReveal) throws Exception {
        testNoteCreationInClassContainer(zoomLevel, classNameToReveal, null);
    }

    /**
     * Check that the note is near the expected location. We don't check precisely the location because the zoom can
     * have round effect on the location.
     * 
     * @param excpectedLocation
     *            the absolute position within the graphical viewer
     */
    private void assertNoteAtLocation(String noteLabel, Point excpectedLocation) {
        Point noteLocation = editor.getAbsoluteLocation(noteLabel, NoteEditPart.class);
        // assertTrue("The note has a bad location. expected:<" +
        // excpectedLocation.toString() + "> but was:<" + noteLocation + ">",
        // excpectedLocation.getDistance(noteLocation) < 2);
        assertEquals("The note has been created at wrong location.", adaptLocation(excpectedLocation), noteLocation);
    }

    /**
     * Possibility to adapt the expected location according to some parameters (snap to grid, ...).
     * 
     * @param expectedLocation
     *            The initial expected location
     * @return The adapted location
     */
    protected Point adaptLocation(Point expectedLocation) {
        return expectedLocation;
    }
}

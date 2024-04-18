/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.swtbot.sequence;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.diagramtype.HeaderData;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.header.DiagramHeaderComposite;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Freeze the top of the lifelines in Scenario.
 * <ul>
 * <li>Rename lifeLine</li>
 * <li>Resizing instance role</li>
 * <li>Moving instance role</li>
 * <li>Arrange All</li>
 * <li>Change instance role background color</li>
 * <li>Change instance role label color</li>
 * <li>Display Ruler</li>
 * <li>Change zoom</li>
 * </ul>
 * 
 * @author fbarbin
 */
public class HeaderSequenceDiagramTests extends AbstractDefaultModelSequenceTests {

    private static final int RULER_WIDTH = 22;

    private static final int DELTA_100 = 100;

    private List<HeaderData> headerDatas = new ArrayList<HeaderData>();

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        if (getSessionModel() == null) {
        } else {
            closeOutline();
            SWTBotUtils.waitAllUiEvents();

            sessionAirdResource = new UIResource(designerProject, FILE_DIR, getSessionModel());
            localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
            Option<String> dRepresentationName = getDRepresentationName();
            if (dRepresentationName.some()) {
                editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), getRepresentationId(), dRepresentationName.get(), DDiagram.class);
            }
        }

        initEditor();
    }

    /**
     * Test if header is correctly renamed after the lifeLine renaming.
     */
    public void testRenameLifeLine() {

        SWTBotGefEditPart editPart = editor.getEditPart(LIFELINE_A, InstanceRoleEditPart.class);
        editor.directEditType("test", editPart);
        RGBValues backgroundColor = createDefaultBgColor();
        RGBValues foregroundColor = createDefaultFgColor();
        Rectangle bounds = editor.getBounds(editPart).getCopy();
        checkHeader(new HeaderData("test : A", bounds.x, bounds.width, backgroundColor, foregroundColor), 0);
    }

    /**
     * Test if header is correctly resized after the lifeLine resizing.
     */
    public void testResizingInstanceRole() {
        SWTBotGefEditPart editPart = editor.getEditPart(LIFELINE_B);
        editPart.parent().select();
        Rectangle bounds = editor.getBounds(editPart).getCopy();
        editPart.parent().resize(PositionConstants.NORTH_WEST, bounds.width + DELTA_100, bounds.height + DELTA_100);
        ICondition condition = new CheckEditPartResized(editPart);
        bot.waitUntil(condition);

        RGBValues backgroundColor = createDefaultBgColor();
        RGBValues foregroundColor = createDefaultFgColor();

        checkHeader(new HeaderData(LIFELINE_B, bounds.x, bounds.width + DELTA_100, backgroundColor, foregroundColor), 1);
    }

    /**
     * Test if header is correctly moved after the lifeLine moving.
     */
    public void testMovingInstanceRole() {

        SWTBotGefEditPart editPart = editor.getEditPart(LIFELINE_B, InstanceRoleEditPart.class);

        ICondition condition = new CheckEditPartMoved(editPart);
        Rectangle bounds = editor.getBounds(editPart).getCopy();
        editor.drag(LIFELINE_B, bounds.x + DELTA_100, bounds.y);

        RGBValues backgroundColor = createDefaultBgColor();
        RGBValues foregroundColor = createDefaultFgColor();

        bot.waitUntil(condition);
        checkHeader(new HeaderData(LIFELINE_B, bounds.x + DELTA_100, bounds.width, backgroundColor, foregroundColor), 1);
    }

    /**
     * Test if headers have a correct position after an Arrange All.
     */
    public void testArrangeAll() {
        SWTBotGefEditPart editPartC = editor.getEditPart(LIFELINE_C, InstanceRoleEditPart.class);
        SWTBotGefEditPart editPartA = editor.getEditPart(LIFELINE_A, InstanceRoleEditPart.class);
        SWTBotGefEditPart editPartB = editor.getEditPart(LIFELINE_B, InstanceRoleEditPart.class);

        ICondition condition = new CheckEditPartMoved(editPartC);
        arrangeAll();
        RGBValues backgroundColor = createDefaultBgColor();
        RGBValues foregroundColor = createDefaultFgColor();

        bot.waitUntil(condition);

        Rectangle boundsA = editor.getBounds(editPartA).getCopy();
        Rectangle boundsB = editor.getBounds(editPartB).getCopy();
        Rectangle boundsC = editor.getBounds(editPartC).getCopy();
        checkHeader(new HeaderData(LIFELINE_A, boundsA.x, boundsA.width, backgroundColor, foregroundColor), 0);
        checkHeader(new HeaderData(LIFELINE_B, boundsB.x, boundsB.width, backgroundColor, foregroundColor), 1);
        checkHeader(new HeaderData(LIFELINE_C, boundsC.x, boundsC.width, backgroundColor, foregroundColor), 2);
    }

    /**
     * Test header color by changing instance role fill color.
     */
    public void testChangeBackgroundColor() {
        SWTBotGefEditPart editPart = editor.getEditPart(LIFELINE_B, InstanceRoleEditPart.class);
        editPart.select();
        Rectangle bounds = editor.getBounds(editPart).getCopy();
        SWTBotSiriusHelper.changeFillColorNavigationBar(bot);
        SWTBotShell colorPaletteShell = bot.shell(Messages.ColorPalettePopup_title);
        colorPaletteShell.bot().buttonWithTooltip("{252, 233, 79}").click();
        RGBValues backgroundColor = createColor(252, 233, 79);
        RGBValues foregroundColor = createDefaultFgColor();

        checkHeader(new HeaderData(LIFELINE_B, bounds.x, bounds.width, backgroundColor, foregroundColor), 1);
    }

    /**
     * Test header label color by changing instance role font color.
     */
    public void testChangeLabelColor() {
        SWTBotGefEditPart editPart = editor.getEditPart(LIFELINE_B, InstanceRoleEditPart.class);
        editPart.select();
        Rectangle bounds = editor.getBounds(editPart).getCopy();
        SWTBotSiriusHelper.changeFontColorNavigationBar(bot);
        SWTBotShell colorPaletteShell = bot.shell(Messages.ColorPalettePopup_title);
        colorPaletteShell.bot().buttonWithTooltip("{102, 204, 255}").click();
        RGBValues backgroundColor = createDefaultBgColor();
        RGBValues foregroundColor = createColor(102, 204, 255);

        checkHeader(new HeaderData(LIFELINE_B, bounds.x, bounds.width, backgroundColor, foregroundColor), 1);
    }

    /**
     * Test weather header position stay correct by activating ruler.
     */
    public void testDisplayRuler() {
        SWTBotGefEditPart editPartC = editor.getEditPart(LIFELINE_C, InstanceRoleEditPart.class);
        SWTBotGefEditPart editPartA = editor.getEditPart(LIFELINE_A, InstanceRoleEditPart.class);
        SWTBotGefEditPart editPartB = editor.getEditPart(LIFELINE_B, InstanceRoleEditPart.class);

        RGBValues backgroundColor = createDefaultBgColor();
        RGBValues foregroundColor = createDefaultFgColor();

        editor.click(1, 1);

        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Rulers & Grid", propertiesView.bot());
        bot.checkBox("Show Ruler").select();

        Rectangle boundsA = editor.getBounds(editPartA).getCopy();
        Rectangle boundsB = editor.getBounds(editPartB).getCopy();
        Rectangle boundsC = editor.getBounds(editPartC).getCopy();
        checkHeader(new HeaderData(LIFELINE_A, boundsA.x, boundsA.width, backgroundColor, foregroundColor), 0, true, false);
        checkHeader(new HeaderData(LIFELINE_B, boundsB.x, boundsB.width, backgroundColor, foregroundColor), 1, true, false);
        checkHeader(new HeaderData(LIFELINE_C, boundsC.x, boundsC.width, backgroundColor, foregroundColor), 2, true, false);

        bot.checkBox("Show Ruler").deselect();

        boundsA = editor.getBounds(editPartA).getCopy();
        boundsB = editor.getBounds(editPartB).getCopy();
        boundsC = editor.getBounds(editPartC).getCopy();
        checkHeader(new HeaderData(LIFELINE_A, boundsA.x, boundsA.width, backgroundColor, foregroundColor), 0);
        checkHeader(new HeaderData(LIFELINE_B, boundsB.x, boundsB.width, backgroundColor, foregroundColor), 1);
        checkHeader(new HeaderData(LIFELINE_C, boundsC.x, boundsC.width, backgroundColor, foregroundColor), 2);
    }

    /**
     * Test weather header is correctly scale by changing zoom level.
     */
    public void testChangeZoom() {
        SWTBotGefEditPart editPartC = editor.getEditPart(LIFELINE_C, InstanceRoleEditPart.class);
        SWTBotGefEditPart editPartA = editor.getEditPart(LIFELINE_A, InstanceRoleEditPart.class);
        SWTBotGefEditPart editPartB = editor.getEditPart(LIFELINE_B, InstanceRoleEditPart.class);

        editor.zoom(ZoomLevel.ZOOM_200);

        RGBValues backgroundColor = createDefaultBgColor();
        RGBValues foregroundColor = createDefaultFgColor();

        Rectangle boundsA = editor.getBounds(editPartA).getCopy();
        Rectangle boundsB = editor.getBounds(editPartB).getCopy();
        Rectangle boundsC = editor.getBounds(editPartC).getCopy();

        checkHeaderZoom(new HeaderData(LIFELINE_B, 0, boundsB.width + boundsB.x, backgroundColor, foregroundColor), 0);
        checkHeaderZoom(new HeaderData(LIFELINE_C, boundsC.x, boundsC.width, backgroundColor, foregroundColor), 1);

        editor.zoom(ZoomLevel.ZOOM_100);

        boundsA = editor.getBounds(editPartA).getCopy();
        boundsB = editor.getBounds(editPartB).getCopy();
        boundsC = editor.getBounds(editPartC).getCopy();
        checkHeader(new HeaderData(LIFELINE_A, boundsA.x, boundsA.width, backgroundColor, foregroundColor), 0);
        checkHeader(new HeaderData(LIFELINE_B, boundsB.x, boundsB.width, backgroundColor, foregroundColor), 1);
        checkHeader(new HeaderData(LIFELINE_C, boundsC.x, boundsC.width, backgroundColor, foregroundColor), 2);
    }

    private void checkHeader(HeaderData expectedHeaderData, int index) {
        checkHeader(expectedHeaderData, index, false, false);
    }

    private void checkHeaderZoom(HeaderData expectedHeaderData, int index) {
        checkHeader(expectedHeaderData, index, false, true);
    }

    private void checkHeader(HeaderData expectedHeaderData, int index, boolean rulerDisplayed, boolean zoom) {
        setHeaderLabel(rulerDisplayed, zoom);
        HeaderData data = headerDatas.get(index);
        if (rulerDisplayed) {
            assertEquals("Wrong header location", expectedHeaderData.getXLocation(), data.getXLocation() - RULER_WIDTH);
        } else {
            assertEquals("Wrong header location", expectedHeaderData.getXLocation(), data.getXLocation());
        }
        assertEquals("Wrong header width", expectedHeaderData.getWidth(), data.getWidth());
        assertEquals("Wrong header name", expectedHeaderData.getName(), data.getName());

        assertEquals("Wrong header background-color", expectedHeaderData.getBackgroundColor().getRed(), data.getBackgroundColor().getRed());
        assertEquals("Wrong header background-color", expectedHeaderData.getBackgroundColor().getGreen(), data.getBackgroundColor().getGreen());
        assertEquals("Wrong header background-color", expectedHeaderData.getBackgroundColor().getBlue(), data.getBackgroundColor().getBlue());

        assertEquals("Wrong header label-color", expectedHeaderData.getLabelColor().getRed(), data.getLabelColor().getRed());
        assertEquals("Wrong header label-color", expectedHeaderData.getLabelColor().getGreen(), data.getLabelColor().getGreen());
        assertEquals("Wrong header label-color", expectedHeaderData.getLabelColor().getBlue(), data.getLabelColor().getBlue());
    }

    private void setHeaderLabel(boolean rulerDisplayed, final boolean zoom) {
        final int delta;
        if (rulerDisplayed) {
            delta = 1;
        } else {
            delta = 0;
        }
        IWorkbenchPart part = editor.getReference().getPart(false);
        if (part instanceof DDiagramEditorImpl) {
            final DiagramHeaderComposite composite = ((DDiagramEditorImpl) part).getDiagramHeader();
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    Control[] controls = composite.getChildren();
                    Control[] labelContainers = ((Composite) controls[1]).getChildren();
                    headerDatas.clear();
                    if (zoom) {
                        headerDatas.add(createHeaderData((Composite) labelContainers[0]));
                        headerDatas.add(createHeaderData((Composite) labelContainers[2]));

                    } else {
                        headerDatas.add(createHeaderData((Composite) labelContainers[1 + delta]));
                        headerDatas.add(createHeaderData((Composite) labelContainers[3 + delta]));
                        headerDatas.add(createHeaderData((Composite) labelContainers[5 + delta]));
                    }
                }
            });
        }
    }

    private HeaderData createHeaderData(Composite labelContainer) {

        Label label = (Label) labelContainer.getChildren()[0];

        final RGBValues foregroundColor = RGBValues.create(label.getForeground().getRed(), label.getForeground().getGreen(), label.getForeground().getBlue());
        final RGBValues backgroundColor = RGBValues.create(labelContainer.getBackground().getRed(), labelContainer.getBackground().getGreen(), labelContainer.getBackground().getBlue());

        return new HeaderData(label.getText(), labelContainer.getBounds().x, labelContainer.getBounds().width, backgroundColor, foregroundColor);
    }

    private RGBValues createDefaultBgColor() {
        return createColor(114, 159, 207);
    }

    private RGBValues createDefaultFgColor() {
        return createColor(0, 0, 0);
    }

    private RGBValues createColor(int r, int g, int b) {
        return RGBValues.create(r, g, b);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        headerDatas = null;
        // Reopen outline
        new DesignerViews(bot).openOutlineView();
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

}

/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES and others.
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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.operations.DefaultOperationHistory;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDiagramElementContainerNameEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckDiagramSelected;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefViewer;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.junit.Assert;

import com.google.common.collect.Lists;

/**
 * A test for ensuring that the 'SelectAll' Action selects the right Edit Parts, and that elements can be correctly
 * deselected.
 * 
 * @author alagarde
 */
// defined for VP-1121 : Select Object not clearly update
public class SelectAllAndDeselectionTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "1121 Diagram Test";

    private static final String REPRESENTATION_NAME = "Entities";

    private static final String MODEL = "1121.ecore";

    private static final String SESSION_FILE = "1121.aird";

    private static final String DATA_UNIT_DIR = "data/unit/selection/";

    private static final String FILE_DIR = "/";

    private static final String PACKAGE_1_NAME = "p1";

    private static final String PACKAGE_2_NAME = "p2";

    private static final String PACKAGE_3_NAME = "p3";

    private static final String CLASS_1_NAME = "c1";

    private static final String CLASS_2_NAME = "c2";

    private static final String CLASS_3_NAME = "c3";

    private static final String CLASS_4_NAME = "c4";

    private static final String[] ALL_EDIT_PART_NAMES = { PACKAGE_1_NAME, PACKAGE_2_NAME, PACKAGE_3_NAME, CLASS_1_NAME, CLASS_2_NAME, CLASS_3_NAME, CLASS_4_NAME };

    /**
     * A map associating an edit Part Name with a location.
     */
    private static final Map<String, Point> LOCATIONS = new HashMap<>();

    static {
        LOCATIONS.put(PACKAGE_1_NAME, new Point(25, 70));
        LOCATIONS.put(PACKAGE_2_NAME, new Point(230, 70));
        LOCATIONS.put(PACKAGE_3_NAME, new Point(245, 110));
        LOCATIONS.put(CLASS_1_NAME, new Point(60, 106));
        LOCATIONS.put(CLASS_2_NAME, new Point(459, 125));
        LOCATIONS.put(CLASS_3_NAME, new Point(376, 172));
        LOCATIONS.put(CLASS_4_NAME, new Point(110, 195));
    }

    private Map<Point, SWTBotGefEditPart> locationToEditParts = new HashMap<>();

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);

        registerAllEditPartPositions();
    }

    /**
     * Ensures that the 'Select All' action selects the expected EditParts.
     */
    public void testSelectAll() {
        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();

        editor.bot().toolbarDropDownButtonWithTooltip("Select &All").click();
        checkSelectedEditParts(getEditPartsFromNames(PACKAGE_1_NAME, PACKAGE_2_NAME, PACKAGE_3_NAME, CLASS_1_NAME, CLASS_2_NAME, CLASS_3_NAME, CLASS_4_NAME));
    }

    /**
     * Ensures that the 'Select All' action selects the expected EditParts and the escape key deselects all (only
     * DDiagramEditPart remains selected).
     */
    public void testSelectAllAndDeselectAll() {
        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();

        editor.bot().toolbarDropDownButtonWithTooltip("Select &All").click();
        checkSelectedEditParts(getEditPartsFromNames(PACKAGE_1_NAME, PACKAGE_2_NAME, PACKAGE_3_NAME, CLASS_1_NAME, CLASS_2_NAME, CLASS_3_NAME, CLASS_4_NAME));
        SWTBotUtils.pressKeyboardKey(editor.getCanvas().widget, SWT.ESC);
        checkSelectedEditParts(Arrays.asList(editor.mainEditPart()));
    }

    /**
     * Ensures that the 'Select All' action selects the expected EditParts and that the attempt to select a container
     * label does not lead to IllegalArgumentException.
     */
    public void testSelectAllAndTryContainerLabelSelection() {
        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();

        editor.bot().toolbarDropDownButtonWithTooltip("Select &All").click();
        checkSelectedEditParts(getEditPartsFromNames(PACKAGE_1_NAME, PACKAGE_2_NAME, PACKAGE_3_NAME, CLASS_1_NAME, CLASS_2_NAME, CLASS_3_NAME, CLASS_4_NAME));

        SWTBotGefEditPart labelPart = editor.getEditPart(PACKAGE_1_NAME);
        assertTrue(labelPart.part() instanceof AbstractDiagramElementContainerNameEditPart);
        labelPart.click();

        // Check there is no more IllegalArgumentException when the user click
        // on the label of a secondary selected part.
        if (doesAnErrorOccurs()) {
            Assert.fail(getErrorLoggersMessage());
        }
        checkSelectedEditParts(Lists.newArrayList(getEditPartsFromNames(PACKAGE_1_NAME)));
    }

    /**
     * Ensures that container deselection works.
     */
    public void testSelectAllAndUnselectOneContainer() {
        // Deselecting p1
        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();
        editor.bot().toolbarDropDownButtonWithTooltip("Select &All").click();
        unselectElementsAtLocation(LOCATIONS.get(PACKAGE_1_NAME));
        checkSelectedEditParts(getEditPartsFromNames(PACKAGE_2_NAME, PACKAGE_3_NAME, CLASS_1_NAME, CLASS_2_NAME, CLASS_3_NAME, CLASS_4_NAME));
    }

    /**
     * Ensures that node deselection works.
     */
    public void testSelectAllAndUnselectOneNode() {
        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();
        // Deselecting p1
        editor.bot().toolbarDropDownButtonWithTooltip("Select &All").click();
        unselectElementsAtLocation(LOCATIONS.get(CLASS_4_NAME));
        checkSelectedEditParts(getEditPartsFromNames(PACKAGE_1_NAME, PACKAGE_2_NAME, PACKAGE_3_NAME, CLASS_1_NAME, CLASS_2_NAME, CLASS_3_NAME));
    }

    /**
     * Ensures that deselection works on container located inside another container.
     */
    public void testSelectAllAndUnselectOneContainerInContainer() {
        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();

        // Deselecting p3
        editor.bot().toolbarDropDownButtonWithTooltip("Select &All").click();
        unselectElementsAtLocation(LOCATIONS.get(PACKAGE_3_NAME));
        checkSelectedEditParts(getEditPartsFromNames(PACKAGE_1_NAME, PACKAGE_2_NAME, CLASS_1_NAME, CLASS_2_NAME, CLASS_3_NAME, CLASS_4_NAME));
    }

    /**
     * Ensures that it is possible to deselect a node located inside a container.
     */
    public void testSelectAllAndUnselectOneNodeInContainer() {
        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();

        // Deselecting c2
        editor.bot().toolbarDropDownButtonWithTooltip("Select &All").click();
        unselectElementsAtLocation(LOCATIONS.get(CLASS_2_NAME));
        checkSelectedEditParts(getEditPartsFromNames(PACKAGE_1_NAME, PACKAGE_2_NAME, PACKAGE_3_NAME, CLASS_1_NAME, CLASS_3_NAME, CLASS_4_NAME));
    }

    /**
     * Tests deselection on more complicated useCase.
     */
    public void testSelectAllAndUnselectTwoNodesInContainer() {
        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();

        // Deselecting c2 and c3
        editor.bot().toolbarDropDownButtonWithTooltip("Select &All").click();
        unselectElementsAtLocation(LOCATIONS.get(CLASS_2_NAME), LOCATIONS.get(CLASS_3_NAME));
        checkSelectedEditParts(getEditPartsFromNames(PACKAGE_1_NAME, PACKAGE_2_NAME, PACKAGE_3_NAME, CLASS_1_NAME, CLASS_4_NAME));
    }

    /**
     * Tests deselection on more complicated useCase.
     */
    public void testSelectAllAndUnselectSeveralNodesAndContainers() {
        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();

        // Deselecting c2 and c3
        editor.bot().toolbarDropDownButtonWithTooltip("Select &All").click();
        unselectElementsAtLocation(LOCATIONS.get(PACKAGE_1_NAME), LOCATIONS.get(CLASS_2_NAME), LOCATIONS.get(CLASS_3_NAME), LOCATIONS.get(CLASS_4_NAME));
        checkSelectedEditParts(getEditPartsFromNames(PACKAGE_2_NAME, PACKAGE_3_NAME, CLASS_1_NAME));
    }

    /**
     * Test for VP-1874.
     */
    public void test_element_selection_change_does_not_leak_listeners() {
        final DefaultOperationHistory doh = (DefaultOperationHistory) OperationHistoryFactory.getOperationHistory();
        SWTBotGefEditPart c1Part = editor.getEditPart(CLASS_1_NAME, AbstractBorderedShapeEditPart.class);

        // Do it once to get in a steady state, so that any lazy initialisation
        // is done
        cycleSelection(c1Part);
        final int countBefore = getListenersCount(doh);
        for (int i = 0; i < 5; i++) {
            cycleSelection(c1Part);
        }

        // An unidentified timing issue prevents the use of a simple assert
        // here, so we wait for the listeners list to be updated.
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                int countAfter = getListenersCount(doh);
                return countAfter == countBefore;
            }

            @Override
            public String getFailureMessage() {
                return "Listeners count " + getListenersCount(doh) + " was not reverted to its initial value of " + countBefore;
            }
        });
    }

    private void cycleSelection(SWTBotGefEditPart c1Part) {
        // Select an edit part
        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, c1Part.part());
        editor.getEditPart(CLASS_1_NAME, AbstractBorderedShapeEditPart.class).select();
        bot.waitUntil(checkc1Selected);
        // Select the diagram itself
        editor.select(editor.mainEditPart());
        bot.waitUntil(new CheckDiagramSelected(editor));
    }

    private int getListenersCount(DefaultOperationHistory doh) {
        try {
            Field field = doh.getClass().getDeclaredField("listeners");
            field.setAccessible(true);
            ListenerList listeners = (ListenerList) field.get(doh);
            return listeners.size();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Unselects the EditParts located at the given locations.
     * 
     * @param locations
     *            the location of elements to unselect
     */
    protected void unselectElementsAtLocation(Point... locations) {
        // FIXME ALA once this controlClickAtLocation method will work, use
        // this solution
        // SWTBotUtils.controlClickAtLocation(editor, locations[i]);

        // We use the reflection to access to the graphicalViewer
        boolean reflectiveExceptionOccured = false;

        try {
            Field field = SWTBotGefViewer.class.getDeclaredField("graphicalViewer");
            field.setAccessible(true);
            final GraphicalViewer viewer = (GraphicalViewer) field.get(editor.getSWTBotGefViewer());

            for (int i = 0; i < locations.length; i++) {
                // FIXME ALA here use SWTBotUtils.controlClickAtLocation(editor,
                // locations[i])
                // instead of calling the deselection on the viewer
                final SWTBotGefEditPart swtBotEditPart = getEditPartAtLocation(locations[i]);
                UIThreadRunnable.asyncExec(new VoidResult() {
                    @Override
                    public void run() {
                        viewer.deselect(swtBotEditPart.part());
                    }
                });

            }
        } catch (IllegalArgumentException e) {
            reflectiveExceptionOccured = true;
        } catch (IllegalAccessException e) {
            reflectiveExceptionOccured = true;
        } catch (SecurityException e) {
            reflectiveExceptionOccured = true;
        } catch (NoSuchFieldException e) {
            reflectiveExceptionOccured = true;
        }
        assertFalse(reflectiveExceptionOccured);
    }

    /**
     * Returns all the editParts associated to the given collection of names.
     * 
     * @param names
     *            the list of edit Part names to get Edit Parts from
     * @return all the editParts associated to the given collection of names
     */
    protected Collection<? extends SWTBotGefEditPart> getEditPartsFromNames(String... names) {
        List<SWTBotGefEditPart> editPartsThatSouldBeSelected = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            SWTBotGefEditPart editPart = editor.getEditPart(names[i], ShapeNodeEditPart.class);
            editPartsThatSouldBeSelected.add(editPart);
        }
        return editPartsThatSouldBeSelected;
    }

    /**
     * Called at setup to register all edit Parts positions in maps.
     */
    protected void registerAllEditPartPositions() {
        for (int i = 0; i < ALL_EDIT_PART_NAMES.length; i++) {
            registerLocation(editor.getEditPart(ALL_EDIT_PART_NAMES[i], ShapeNodeEditPart.class), ALL_EDIT_PART_NAMES[i]);
        }
    }

    /**
     * Registers the location of the given editPart with the given Name.
     * 
     * @param editPart
     *            the editPart to register
     * @param name
     *            the name associated to the editPart to register
     */
    protected void registerLocation(SWTBotGefEditPart editPart, String name) {
        this.locationToEditParts.put(LOCATIONS.get(name), editPart);
    }

    /**
     * Returns the EditPart registered at the given location
     * 
     * @param location
     *            the location of the searched editPart
     * @return the EditPart registered at the given location
     */
    protected SWTBotGefEditPart getEditPartAtLocation(Point location) {
        return this.locationToEditParts.get(location);
    }

    /**
     * Ensures that all editPart contained in the given list are selected in the diagram, and that no other
     * {@link EditPart} is selected.
     * 
     * @param editPartsSWTThatSouldBeSelected
     *            a collection containing all {@link EditPart}s that should be selected
     */
    protected void checkSelectedEditParts(final Collection<? extends SWTBotGefEditPart> editPartsSWTThatSouldBeSelected) {

        // Step 0 : we wait for all UIEvents to end
        // so that we are sure that all selection and deselection events have
        // been executed
        SWTBotUtils.waitAllUiEvents();

        // Step 1 : creating a collection containing all editParts
        // referenced by
        // the SWTBotEditParts that should be selected
        Iterator<? extends SWTBotGefEditPart> iterator = editPartsSWTThatSouldBeSelected.iterator();
        Collection<EditPart> editPartsThatShouldBeSelected = new ArrayList<>();
        while (iterator.hasNext()) {
            editPartsThatShouldBeSelected.add(iterator.next().part());
        }

        // Step 2 : we get the selected elements as a collection
        Iterator<?> selectedEditPartsIterator = ((IStructuredSelection) editor.getSelection()).iterator();
        Collection<EditPart> selectedEditPartsAsCollection = new ArrayList<>();
        while (selectedEditPartsIterator.hasNext()) {
            selectedEditPartsAsCollection.add((EditPart) selectedEditPartsIterator.next());
        }

        // Step 3 : For each selected elements, we check that it should be
        // selected
        for (EditPart selectedElement : selectedEditPartsAsCollection) {
            if (selectedElement instanceof IGraphicalEditPart) {
                IGraphicalEditPart selectedEditPart = (IGraphicalEditPart) selectedElement;
                assertTrue("Edit Part " + selectedEditPart.toString() + " should not be selected", (editPartsThatShouldBeSelected.contains(selectedEditPart)));
                editPartsThatShouldBeSelected.remove(selectedEditPart);
            }
        }

        // Step 4 : we finally check that no other elements shoud have been
        // selected
        assertTrue("Edit Part(s) " + editPartsThatShouldBeSelected + " should be selected but aren't", editPartsThatShouldBeSelected.isEmpty());
    }
}

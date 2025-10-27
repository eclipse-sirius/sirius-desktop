/*******************************************************************************
 * Copyright (c) 2016, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.swtbot.tabbar;

import java.util.List;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.bindings.keys.IKeyLookup;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.SetCurrentConcernCommand;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Bug 490384 : Have a "Reset to default filters" action in tabbar.
 * 
 * <ol>
 * <li>test action availability of action when there is a default concern.</li>
 * <li>the non availability when there is no default concern.</li>
 * <li>test the result of the action on activated filters.</li>
 * </ol>
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ResetToDefaultFiltersActionTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "data/unit/tabbar/bug490384/";

    private static final String MODELER_RESOURCE_NAME = "bug490384.odesign";

    private static final String SEMANTIC_RESOURCE_NAME = "bug490384.ecore";

    private static final String SESSION_RESOURCE_NAME = "bug490384.aird";

    private static final String DIAGRAM_DESCRIPTION_WITH_CONCERN = "Bug490384_DiagWithConcern";

    private static final String DIAGRAM_DESCRIPTION_WITHOUT_CONCERN = "Bug490384_DiagWithoutConcern";

    private SWTBotTreeItem modelElementItem;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME, MODELER_RESOURCE_NAME);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        modelElementItem = localSession.getLocalSessionBrowser().perSemantic().getItems()[0];
    }

    /**
     * Test the non visibility of "Reset to default filters" action as there is
     * not default concern.
     */
    public void testResetToDefaultFiltersActionUnavailability() {
        UIDiagramRepresentation newDiagramRepresentation = localSession.newDiagramRepresentation("new " + DIAGRAM_DESCRIPTION_WITHOUT_CONCERN, DIAGRAM_DESCRIPTION_WITHOUT_CONCERN).on(modelElementItem)
                .withDefaultName().ok();;
        editor = newDiagramRepresentation.getEditor();
        DRepresentation dRepresentation = editor.getDRepresentation();
        assertTrue(dRepresentation instanceof DDiagram);
        DDiagram dDiagram = (DDiagram) dRepresentation;
        List<FilterDescription> filters = dDiagram.getDescription().getFilters();
        SWTBotToolbarDropDownButton button = editor.bot().toolbarDropDownButtonWithTooltip("Filters");
        List<? extends SWTBotMenu> menuItems = button.menuItems(WidgetMatcherFactory.widgetOfType(MenuItem.class));
        assertEquals(filters.size(), menuItems.size());
        for (int i = 0; i < menuItems.size(); i++) {
            SWTBotMenu menuItem = menuItems.get(i);
            assertEquals(new IdentifiedElementQuery(filters.get(i)).getLabel(), menuItem.getText());
        }
        assertEquals("Any filter is activated, all classes must be visible.", 5, getNbOfVisibleDiagramElements(dDiagram));
        try {
            // The drop down menu is not closed, so use the Escape key as
            // workaround.
            button.pressShortcut(KeyStroke.getInstance(IKeyLookup.ESC_NAME));
            SWTBotUtils.waitAllUiEvents();
        } catch (ParseException e) {
        }
    }

    /**
     * Test the visibility of "Reset to default filters" action as there is a
     * default concern.
     */
    public void testResetToDefaultFiltersActionAvailability() {
        UIDiagramRepresentation newDiagramRepresentation = localSession.newDiagramRepresentation("new " + DIAGRAM_DESCRIPTION_WITH_CONCERN, DIAGRAM_DESCRIPTION_WITH_CONCERN).on(modelElementItem)
                .withDefaultName().ok();;
        editor = newDiagramRepresentation.getEditor();
        DRepresentation dRepresentation = editor.getDRepresentation();
        assertTrue(dRepresentation instanceof DDiagram);
        DDiagram dDiagram = (DDiagram) dRepresentation;
        List<FilterDescription> filters = dDiagram.getDescription().getFilters();
        final SWTBotToolbarDropDownButton filtersButton = editor.bot().toolbarDropDownButtonWithTooltip("Filters");
        List<? extends SWTBotMenu> menuItems = filtersButton.menuItems(WidgetMatcherFactory.widgetOfType(MenuItem.class));
        assertEquals(filters.size() + 1, menuItems.size());
        for (int i = 0; i < menuItems.size() - 1; i++) {
            SWTBotMenu menuItem = menuItems.get(i);
            assertEquals(new IdentifiedElementQuery(filters.get(i)).getLabel(), menuItem.getText());
        }
        SWTBotMenu menuItem = menuItems.get(menuItems.size() - 1);
        assertEquals(Messages.ResetToDefaultFiltersAction_text, menuItem.getText());
        assertFalse(menuItem.isEnabled());
        assertEquals("By default A filter is activated, only B classes must be visible.", 2, getNbOfVisibleDiagramElements(dDiagram));

        // Change default filters selection
        menuItems.get(0).click();
        try {
            // The drop down menu is not closed, so use the Escape key as
            // workaround.
            filtersButton.pressShortcut(KeyStroke.getInstance(IKeyLookup.ESC_NAME));
            SWTBotUtils.waitAllUiEvents();
        } catch (ParseException e) {
        }
        assertEquals(0, dDiagram.getActivatedFilters().size());
        assertEquals("Any filter is activated, all classes must be visible.", 5, getNbOfVisibleDiagramElements(dDiagram));

        // Test that "Reset to default filters" action is enabled
        menuItems = filtersButton.menuItems(WidgetMatcherFactory.widgetOfType(MenuItem.class));
        menuItem = menuItems.get(menuItems.size() - 1);
        assertEquals(Messages.ResetToDefaultFiltersAction_text, menuItem.getText());
        assertTrue(menuItem.isEnabled());

        // Test result of a reset
        menuItem.click();
        try {
            // The drop down menu is not closed, so use the Escape key as
            // workaround.
            filtersButton.pressShortcut(KeyStroke.getInstance(IKeyLookup.ESC_NAME));
            SWTBotUtils.waitAllUiEvents();
        } catch (ParseException e) {
        }
        assertEquals(dDiagram.getDescription().getDefaultConcern().getFilters(), dDiagram.getActivatedFilters());
        assertEquals(dDiagram.getDescription().getDefaultConcern(), dDiagram.getCurrentConcern());
        assertEquals("By default A filter is activated, only B classes must be visible.", 2, getNbOfVisibleDiagramElements(dDiagram));

        // Test that "Reset to default filters" action is disabled now
        menuItems = filtersButton.menuItems(WidgetMatcherFactory.widgetOfType(MenuItem.class));
        menuItem = menuItems.get(menuItems.size() - 1);
        assertEquals(Messages.ResetToDefaultFiltersAction_text, menuItem.getText());
        assertFalse(menuItem.isEnabled());
        assertEquals("By default A filter is activated, only B classes must be visible.", 2, getNbOfVisibleDiagramElements(dDiagram));
        try {
            // The drop down menu is not closed, so use the Escape key as
            // workaround.
            filtersButton.pressShortcut(KeyStroke.getInstance(IKeyLookup.ESC_NAME));
            SWTBotUtils.waitAllUiEvents();
        } catch (ParseException e) {
        }

        // Test "Reset to default filters" action on concern change
        Session session = localSession.getOpenedSession();
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new SetCurrentConcernCommand(domain, dDiagram, dDiagram.getDescription().getConcerns().getOwnedConcernDescriptions().get(1)));
        menuItems = filtersButton.menuItems(WidgetMatcherFactory.widgetOfType(MenuItem.class));
        menuItem = menuItems.get(menuItems.size() - 1);
        assertEquals(Messages.ResetToDefaultFiltersAction_text, menuItem.getText());
        assertTrue(menuItem.isEnabled());
        assertEquals("With second concern, B filter is activated, only A classes must be visible.", 3, getNbOfVisibleDiagramElements(dDiagram));

        // Test result of a reset
        menuItem.click();
        try {
            // The drop down menu is not closed, so use the Escape key as
            // workaround.
            filtersButton.pressShortcut(KeyStroke.getInstance(IKeyLookup.ESC_NAME));
            SWTBotUtils.waitAllUiEvents();
        } catch (ParseException e) {
        }
        assertEquals(dDiagram.getDescription().getDefaultConcern().getFilters(), dDiagram.getActivatedFilters());
        assertEquals(dDiagram.getDescription().getDefaultConcern(), dDiagram.getCurrentConcern());

    }

    private int getNbOfVisibleDiagramElements(DDiagram dDiagram) {
        return Iterables.size(Iterables.filter(dDiagram.getOwnedDiagramElements(), new Predicate<DDiagramElement>() {
            @Override
            public boolean apply(DDiagramElement input) {
                return input.isVisible();
            }
        }));
    }
}

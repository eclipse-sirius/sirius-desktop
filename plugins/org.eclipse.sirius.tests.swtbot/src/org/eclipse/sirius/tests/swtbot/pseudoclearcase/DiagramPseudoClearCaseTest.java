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
package org.eclipse.sirius.tests.swtbot.pseudoclearcase;

import java.util.Collections;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.view.SiriusOutlineView;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;

/**
 * Pseudo clearcase tests on diagrams.
 * 
 * @author dlecan
 */
public class DiagramPseudoClearCaseTest extends AbstractPseudoClearCaseTest<SWTBotSiriusDiagramEditor> {

    private static final String DIALOG_NAME = "Confirmation dialog";

    private static final String REPRESENTATION_NAME = "Entities";

    private static final String PACKAGE_SP1 = "sp1";

    // Point somewhere in diagram
    private static final Point SOMEWHERE_IN_DIAGRAM = new Point(395, 206);

    private static final Point IN_PACKAGE_SP1 = new Point(205, 103);

    private static final Point IN_ECLASS_EC2 = new Point(137, 102);

    private static final Point IN_ECLASS_EC1 = new Point(561, 72);

    private static final String CONTEXTUAL_MENU_HIDE_ELEMENT = "Hide element";

    private static final String CONTEXTUAL_MENU_REVEAL_ALL_ELTS = "Show all hidden elements";

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationInstanceName() {
        return "control package entities";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationName() {
        return REPRESENTATION_NAME;
    }

    @Override
    protected SWTBotSiriusDiagramEditor openAndGetEditor(Session session, String representationDescriptionName, String representationName) {
        return (SWTBotSiriusDiagramEditor) openRepresentation(session, representationDescriptionName, representationName, DDiagram.class);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNodeDirectEdit() throws Exception {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                editor.directNodeEditType("EC2", "New name but will be discard");
            }
        });
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testEdgeLabelDirectEdit() throws Exception {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                editor.directEdgeEditType("EC3", "EC2", "New name but will be discard");
            }
        });
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testMoveDiagramElement() throws Exception {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                editor.drag(PACKAGE_SP1, SOMEWHERE_IN_DIAGRAM.x, SOMEWHERE_IN_DIAGRAM.y);
            }
        });
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCreatePackageFromPaletteInDiagram() throws Exception {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                editor.activateTool("Package");
                editor.click(SOMEWHERE_IN_DIAGRAM.x, SOMEWHERE_IN_DIAGRAM.y);
            }
        });
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCreatePackageFromPaletteInPackage() throws Exception {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                editor.activateTool("Package");
                editor.click(IN_PACKAGE_SP1.x, IN_PACKAGE_SP1.y);
            }
        });
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCreateClassFromPaletteInDiagram() throws Exception {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                editor.activateTool("Class");
                editor.click(SOMEWHERE_IN_DIAGRAM.x, SOMEWHERE_IN_DIAGRAM.y);
            }
        });
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCreateClassFromPaletteInPackage() throws Exception {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                editor.activateTool("Class");
                editor.click(IN_PACKAGE_SP1.x, IN_PACKAGE_SP1.y);
            }
        });
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCreateOperationInClass() throws Exception {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                editor.activateTool("Operation");
                editor.click(IN_ECLASS_EC2.x, IN_ECLASS_EC2.y);
            }
        });
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCreateAttributeInClass() throws Exception {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                editor.activateTool("Attribute");
                editor.click(IN_ECLASS_EC2.x, IN_ECLASS_EC2.y);
            }
        });
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCreateSuperTypeBetweenClasses() throws Exception {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                editor.activateTool("SuperType");
                editor.click(IN_ECLASS_EC2.x, IN_ECLASS_EC2.y);
                editor.click(IN_ECLASS_EC1.x, IN_ECLASS_EC1.y);
            }
        });
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCreateReferenceBetweenClasses() throws Exception {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                editor.activateTool("Reference");
                editor.click(IN_ECLASS_EC2.x, IN_ECLASS_EC2.y);
                editor.click(IN_ECLASS_EC1.x, IN_ECLASS_EC1.y);
            }
        });
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testPinElementWithPalette() throws Exception {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                editor.activateTool("Pin");
                editor.click(IN_ECLASS_EC2.x, IN_ECLASS_EC2.y);
            }
        });
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideDiagramElement() throws Exception {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                final SWTBotGefEditPart hiddenEditPart = editor.getEditPart(PACKAGE_SP1);

                hiddenEditPart.select();
                editor.clickContextMenu(CONTEXTUAL_MENU_HIDE_ELEMENT);
            }
        });
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testRevealDiagramElement() throws Exception {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                final SWTBotGefEditPart diagramEditPart = editor.mainEditPart();

                diagramEditPart.select();
                editor.clickContextMenu(CONTEXTUAL_MENU_REVEAL_ALL_ELTS);
            }
        });
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testEnableLayer() throws Exception {
        final SiriusOutlineView outlineView = designerViews.openOutlineView().layers();
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                outlineView.activateLayer("Dynamic");
            }
        });
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDisableLayer() throws Exception {
        final SiriusOutlineView outlineView = designerViews.openOutlineView().layers();
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                // Already activated, so becomes deactivated
                outlineView.activateLayer("Package");
            }
        });
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAddFilter() throws Exception {
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Filters", propertiesView.bot());

        final SWTBotTable availableFilters = bot.table(0);
        availableFilters.getTableItem("Hide references").select();

        bot.button("Add >").click();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testRemoveFilter() throws Exception {
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Filters", propertiesView.bot());

        final SWTBotTable availableFilters = bot.table(1);
        availableFilters.getTableItem("Hide generalizations").select();

        bot.button("< Remove").click();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDisableSirius() throws Exception {
        localSession.changeViewpointSelection(Collections.<String> emptySet(), Collections.singleton("Design"));
        waitUntilClosingDiagramDialogOpens();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testEnableSirius() throws Exception {
        localSession.changeViewpointSelection(Collections.singleton("Quality"), Collections.<String> emptySet());
    }

    private void waitUntilClosingDiagramDialogOpens() {
        bot.waitUntil(Conditions.shellIsActive(DIALOG_NAME));

        final SWTBotShell shell = bot.shell(DIALOG_NAME);

        shell.setFocus();

        final SWTBotButton button = bot.button("Yes");
        bot.waitUntil(new ItemEnabledCondition(button));
        button.click();
    }
}

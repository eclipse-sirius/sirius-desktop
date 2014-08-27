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
package org.eclipse.sirius.tests.swtbot;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.common.ui.business.api.views.properties.tabbed.ILabelProviderProvider;
import org.eclipse.sirius.common.ui.business.internal.views.properties.tabbed.LabelProviderProviderDescriptor;
import org.eclipse.sirius.common.ui.business.internal.views.properties.tabbed.LabelProviderProviderRegistry;
import org.eclipse.sirius.common.ui.business.internal.views.properties.tabbed.StandaloneLabelProviderProviderDescriptor;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.tests.support.api.ImageEquality;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITableRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tree.DTreeElement;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCLabel;

import org.eclipse.sirius.tests.swtbot.LabelProviderProviderTests.DiagramLabelProviderProviderStub.DiagramLabelProvider;
import org.eclipse.sirius.tests.swtbot.LabelProviderProviderTests.TableLabelProviderProviderStub.TableLabelProvider;
import org.eclipse.sirius.tests.swtbot.LabelProviderProviderTests.TreeLabelProviderProviderStub.TreeLabelProvider;

/**
 * Tests that a contributed {@link ILabelProvider} customize the title
 * text/image of properties view on dialect editor element selection.
 * 
 * See VP-3852.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class LabelProviderProviderTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "data/unit/propertiesView/VP-3852/";

    private static final String SEMANTIC_RESOURCE_NAME = "VP-3852.ecore";

    private static final String MODELER_RESOURCE_NAME = "VP-3852.odesign";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "VP-3852.aird";

    private static final String VIEWPOINT_NAME = "VP-3852_Viewpoint";

    private static final String DIAGRAM_DESC_NAME = "VP-3852_Diagram";

    private static final String TREE_DESC_NAME = "VP-3852_Tree";

    private static final String TABLE_DESC_NAME = "VP-3852_Table";

    private final static Image DIAGRAM_IMAGE = SiriusEditPlugin.INSTANCE.getImageDescriptor("full/obj16/Sirius.gif").createImage();

    private final static Image TREE_IMAGE = DiagramUIPlugin.Implementation.getBundledImageDescriptor("icons/full/obj16/WorkspaceImage.gif").createImage();

    private final static Image TABLE_IMAGE = SiriusEditPlugin.INSTANCE.getImageDescriptor("full/obj16/VSMElementCustomization.gif").createImage();

    private final static String DEFAULT_LABEL = "test";

    private Image defaultImage;

    /**
     * Save extension to avoid side effect of other plugin because this pur unit
     * test is executed as JUnit Plugin test
     **/
    private List<LabelProviderProviderDescriptor> savedRegisteredExtensions;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        super.onSetUpBeforeClosingWelcomePage();

        defaultImage = UIThreadRunnable.syncExec(new Result<Image>() {
            public Image run() {
                return DiagramUIPlugin.getPlugin().getLabelProvider().getImage(EcorePackage.eINSTANCE);
            }
        });

        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, MODELER_RESOURCE_NAME, REPRESENTATIONS_RESOURCE_NAME);

    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        sessionAirdResource = new UIResource(designerProject, REPRESENTATIONS_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * Test the properties view title on diagram dialect element selection
     * without customization.
     */
    public void testPropertiesViewTitleOnDiagramDialectEditorWithoutContributions() {
        UIDiagramRepresentation diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(DIAGRAM_DESC_NAME)
                .selectRepresentationInstance("new " + DIAGRAM_DESC_NAME, UIDiagramRepresentation.class).open();
        editor = diagram.getEditor();
        editor.select(DEFAULT_LABEL);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        SWTBot propertiesViewBot = propertiesView.bot();
        SWTBotCLabel titleLabelBot = propertiesViewBot.clabel();
        String assertMessage = "On DDiagramElement selection, the DiagramLabelProvider should be used";
        assertEquals(assertMessage, DEFAULT_LABEL, titleLabelBot.getText());
        assertTrue(assertMessage, ImageEquality.areEqualImages(defaultImage, titleLabelBot.image()));
        editor.close();
    }

    /**
     * Test the properties view title on table dialect element selection without
     * customization.
     */
    public void testPropertiesViewTitleOnTableDialectEditorWithoutContributions() {
        UITableRepresentation table = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(TABLE_DESC_NAME)
                .selectRepresentationInstance("new " + TABLE_DESC_NAME, UITableRepresentation.class).open();
        SWTBotEditor tableEditorBot = table.getEditor();
        tableEditorBot.bot().tree().getAllItems()[0].select();
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        SWTBot propertiesViewBot = propertiesView.bot();
        SWTBotCLabel titleLabelBot = propertiesViewBot.clabel();
        String assertMessage = "On DTableElement selection, the TableLabelProvider should be used";
        assertEquals(assertMessage, DEFAULT_LABEL, titleLabelBot.getText());
        assertTrue(assertMessage, ImageEquality.areEqualImages(defaultImage, titleLabelBot.image()));
        tableEditorBot.close();
    }

    /**
     * Test the properties view title on tree dialect element selection without
     * customization.
     */
    public void testPropertiesViewTitleOnTreeDialectEditorWithoutContributions() {
        UITreeRepresentation tree = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(TREE_DESC_NAME)
                .selectRepresentationInstance("new " + TREE_DESC_NAME, UITreeRepresentation.class).open();
        SWTBotEditor treeEditorBot = tree.getEditor();
        treeEditorBot.bot().tree().getAllItems()[0].select();
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        SWTBot propertiesViewBot = propertiesView.bot();
        SWTBotCLabel titleLabelBot = propertiesViewBot.clabel();
        String assertMessage = "On DTreeElement selection, the TreeLabelProvider should be used";
        assertEquals(assertMessage, DEFAULT_LABEL, titleLabelBot.getText());
        assertTrue(assertMessage, ImageEquality.areEqualImages(defaultImage, titleLabelBot.image()));
        treeEditorBot.close();
    }

    /**
     * Test the customization of properties view title on diagram dialect
     * element selection.
     */
    public void testPropertiesViewTitleOnDiagramDialectEditorWithContributions() {
        addContributions();
        UIDiagramRepresentation diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(DIAGRAM_DESC_NAME)
                .selectRepresentationInstance("new " + DIAGRAM_DESC_NAME, UIDiagramRepresentation.class).open();
        editor = diagram.getEditor();
        editor.select("test");
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        SWTBot propertiesViewBot = propertiesView.bot();
        SWTBotCLabel titleLabelBot = propertiesViewBot.clabel();
        String assertMessage = "On DDiagramElement selection, the DiagramLabelProvider should be used";
        assertEquals(assertMessage, DiagramLabelProvider.LABEL, titleLabelBot.getText());
        assertTrue(assertMessage, ImageEquality.areEqualImages(DIAGRAM_IMAGE, titleLabelBot.image()));
        editor.close();
    }

    /**
     * Test the customization of properties view title on table dialect element
     * selection.
     */
    public void testPropertiesViewTitleOnTableDialectEditorWithContributions() {
        addContributions();
        UITableRepresentation table = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(TABLE_DESC_NAME)
                .selectRepresentationInstance("new " + TABLE_DESC_NAME, UITableRepresentation.class).open();
        SWTBotEditor tableEditorBot = table.getEditor();
        tableEditorBot.bot().tree().getAllItems()[0].select();
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        SWTBot propertiesViewBot = propertiesView.bot();
        SWTBotCLabel titleLabelBot = propertiesViewBot.clabel();
        String assertMessage = "On DTableElement selection, the TableLabelProvider should be used";
        assertEquals(assertMessage, TableLabelProvider.LABEL, titleLabelBot.getText());
        assertTrue(assertMessage, ImageEquality.areEqualImages(TABLE_IMAGE, titleLabelBot.image()));
        tableEditorBot.close();
    }

    /**
     * Test the customization of properties view title on tree dialect element
     * selection.
     */
    public void testPropertiesViewTitleOnTreeDialectEditorWithContributions() {
        addContributions();
        UITreeRepresentation tree = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(TREE_DESC_NAME)
                .selectRepresentationInstance("new " + TREE_DESC_NAME, UITreeRepresentation.class).open();
        SWTBotEditor treeEditorBot = tree.getEditor();
        treeEditorBot.bot().tree().getAllItems()[0].select();
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        SWTBot propertiesViewBot = propertiesView.bot();
        SWTBotCLabel titleLabelBot = propertiesViewBot.clabel();
        String assertMessage = "On DTreeElement selection, the TreeLabelProvider should be used";
        assertEquals(assertMessage, TreeLabelProvider.LABEL, titleLabelBot.getText());
        assertTrue(assertMessage, ImageEquality.areEqualImages(TREE_IMAGE, titleLabelBot.image()));
        treeEditorBot.close();
    }

    private void addContributions() {
        savedRegisteredExtensions = LabelProviderProviderRegistry.getRegisteredExtensions();
        LabelProviderProviderRegistry.clearRegistry();
        LabelProviderProviderRegistry.addExtension(new StandaloneLabelProviderProviderDescriptor("tree", new TreeLabelProviderProviderStub()));
        LabelProviderProviderRegistry.addExtension(new StandaloneLabelProviderProviderDescriptor("table", new TableLabelProviderProviderStub()));
        LabelProviderProviderRegistry.addExtension(new StandaloneLabelProviderProviderDescriptor("diagram", new DiagramLabelProviderProviderStub()));
    }

    @Override
    protected void tearDown() throws Exception {
        if (savedRegisteredExtensions != null) {
            LabelProviderProviderRegistry.clearRegistry();
            for (LabelProviderProviderDescriptor extension : savedRegisteredExtensions) {
                LabelProviderProviderRegistry.addExtension(extension);
            }
        }
        super.tearDown();
    }

    class TreeLabelProviderProviderStub implements ILabelProviderProvider {

        public ILabelProvider getLabelProvider() {
            return new TreeLabelProvider();
        }

        public boolean provides(Object selection) {
            return unwrap(selection) instanceof DTreeElement;
        }

        class TreeLabelProvider extends LabelProvider {

            public final static String LABEL = "treeLabel";

            @Override
            public String getText(Object element) {
                return LABEL;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public Image getImage(Object element) {
                return TREE_IMAGE;
            }
        }

    }

    class TableLabelProviderProviderStub implements ILabelProviderProvider {

        public ILabelProvider getLabelProvider() {
            return new TableLabelProvider();
        }

        public boolean provides(Object selection) {
            return unwrap(selection) instanceof DTableElement;
        }

        class TableLabelProvider extends LabelProvider {

            public final static String LABEL = "tableLabel";

            @Override
            public String getText(Object element) {
                return LABEL;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public Image getImage(Object element) {
                return TABLE_IMAGE;
            }
        }

    }

    class DiagramLabelProviderProviderStub implements ILabelProviderProvider {

        public ILabelProvider getLabelProvider() {
            return new DiagramLabelProvider();
        }

        public boolean provides(Object selection) {
            return unwrap(selection) instanceof DDiagramElement;
        }

        class DiagramLabelProvider extends LabelProvider {

            public final static String LABEL = "diagramLabel";

            @Override
            public String getText(Object element) {
                return LABEL;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public Image getImage(Object element) {
                return DIAGRAM_IMAGE;
            }
        }

    }

    private Object unwrap(Object element) {
        if (element instanceof IStructuredSelection) {
            return unwrap(((IStructuredSelection) element).getFirstElement());
        }
        if (element instanceof EditPart) {
            return unwrapEditPart((EditPart) element);
        }
        if (element instanceof IAdaptable) {
            View view = (View) ((IAdaptable) element).getAdapter(View.class);
            if (view != null) {
                return unwrapView(view);
            }
        }
        return element;
    }

    private Object unwrapEditPart(EditPart p) {
        if (p.getModel() instanceof View) {
            return unwrapView((View) p.getModel());
        }
        return p.getModel();
    }

    private Object unwrapView(View view) {
        return view.getElement() == null ? view : view.getElement();
    }

}

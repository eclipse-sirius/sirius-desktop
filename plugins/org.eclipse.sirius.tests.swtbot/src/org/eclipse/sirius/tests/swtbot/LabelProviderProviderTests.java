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
package org.eclipse.sirius.tests.swtbot;

import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.common.ui.business.api.views.properties.tabbed.ILabelProviderProvider;
import org.eclipse.sirius.common.ui.business.internal.views.properties.tabbed.LabelProviderProviderDescriptor;
import org.eclipse.sirius.common.ui.business.internal.views.properties.tabbed.LabelProviderProviderRegistry;
import org.eclipse.sirius.common.ui.business.internal.views.properties.tabbed.StandaloneLabelProviderProviderDescriptor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.internal.sheet.SiriusSheetLabelProvider;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.tests.support.api.ImageEquality;
import org.eclipse.sirius.tests.swtbot.LabelProviderProviderTests.DiagramLabelProviderProviderStub.DiagramLabelProvider;
import org.eclipse.sirius.tests.swtbot.LabelProviderProviderTests.TableLabelProviderProviderStub.TableLabelProvider;
import org.eclipse.sirius.tests.swtbot.LabelProviderProviderTests.TreeLabelProviderProviderStub.TreeLabelProvider;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeElement;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeEditor;
import org.eclipse.sirius.ui.tools.api.provider.DTableLabelProvider;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCLabel;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyRegistry;
import org.eclipse.ui.part.IPage;

/**
 * Tests that a contributed {@link ILabelProvider} customize the title
 * text/image of properties view on dialect editor element selection.
 * 
 * See VP-3852.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
@SuppressWarnings("restriction")
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
            @Override
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
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), DIAGRAM_DESC_NAME, "new " + DIAGRAM_DESC_NAME, DDiagram.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        SWTBot propertiesViewBot = propertiesView.bot();
        Object oldLabelProvider = null;
        try {
            oldLabelProvider = setPropertyViewLabelProvider(propertiesView);
            checkPropertiesLabelProvider(propertiesView, SiriusSheetLabelProvider.class);
            editor.setFocus();
            editor.select(DEFAULT_LABEL);
            SWTBotCLabel titleLabelBot = propertiesViewBot.clabel();
            String assertMessage = "On DDiagramElement selection, the DiagramLabelProvider should be used";
            assertEquals(assertMessage, DEFAULT_LABEL, titleLabelBot.getText());
            assertTrue(assertMessage, ImageEquality.areEqualImages(defaultImage, titleLabelBot.image()));
        } finally {
            resetPropertyViewLabelProvider(propertiesView, oldLabelProvider);
            editor.close();
        }
    }

    /**
     * Test the properties view title on table dialect element selection without
     * customization.
     */
    public void testPropertiesViewTitleOnTableDialectEditorWithoutContributions() {
        SWTBotEditor tableEditorBot = openRepresentation(localSession.getOpenedSession(), TABLE_DESC_NAME, "new " + TABLE_DESC_NAME, DTable.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        Object oldLabelProvider = null;
        try {
            oldLabelProvider = setPropertyViewLabelProvider(propertiesView);
            checkPropertiesLabelProvider(propertiesView, DTableLabelProvider.class);
            tableEditorBot.bot().tree().getAllItems()[0].select();
            SWTBot propertiesViewBot = propertiesView.bot();
            SWTBotCLabel titleLabelBot = propertiesViewBot.clabel();
            String assertMessage = "On DTableElement selection, the TableLabelProvider should be used";
            assertEquals(assertMessage, DEFAULT_LABEL, titleLabelBot.getText());
            assertTrue(assertMessage, ImageEquality.areEqualImages(defaultImage, titleLabelBot.image()));
        } finally {
            resetPropertyViewLabelProvider(propertiesView, oldLabelProvider);
            tableEditorBot.close();
        }
    }

    /**
     * Test the properties view title on tree dialect element selection without
     * customization.
     */
    public void testPropertiesViewTitleOnTreeDialectEditorWithoutContributions() {
        SWTBotEditor treeEditorBot = openRepresentation(localSession.getOpenedSession(), TREE_DESC_NAME, "new " + TREE_DESC_NAME, DTree.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        SWTBot propertiesViewBot = propertiesView.bot();
        Object oldLabelProvider = null;
        try {
            oldLabelProvider = setPropertyViewLabelProvider(propertiesView);
            checkPropertiesLabelProvider(propertiesView, DTableLabelProvider.class);
            treeEditorBot.bot().tree().getAllItems()[0].select();
            SWTBotCLabel titleLabelBot = propertiesViewBot.clabel();
            String assertMessage = "On DTreeElement selection, the TreeLabelProvider should be used";
            assertEquals(assertMessage, DEFAULT_LABEL, titleLabelBot.getText());
            assertTrue(assertMessage, ImageEquality.areEqualImages(defaultImage, titleLabelBot.image()));
        } finally {
            resetPropertyViewLabelProvider(propertiesView, oldLabelProvider);
            treeEditorBot.close();
        }
    }

    /**
     * Test the customization of properties view title on diagram dialect
     * element selection.
     */
    public void testPropertiesViewTitleOnDiagramDialectEditorWithContributions() {
        addContributions();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), DIAGRAM_DESC_NAME, "new " + DIAGRAM_DESC_NAME, DDiagram.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        SWTBot propertiesViewBot = propertiesView.bot();
        Object oldLabelProvider = null;
        try {
            oldLabelProvider = setPropertyViewLabelProvider(propertiesView);
            checkPropertiesLabelProvider(propertiesView, SiriusSheetLabelProvider.class);
            editor.select("test");
            editor.setFocus();
            SWTBotCLabel titleLabelBot = propertiesViewBot.clabel();
            String assertMessage = "On DDiagramElement selection, the DiagramLabelProvider should be used";
            assertEquals(assertMessage, DiagramLabelProvider.LABEL, titleLabelBot.getText());
            assertTrue(assertMessage, ImageEquality.areEqualImages(DIAGRAM_IMAGE, titleLabelBot.image()));
        } finally {
            resetPropertyViewLabelProvider(propertiesView, oldLabelProvider);
            editor.close();
        }
    }

    /**
     * Test the customization of properties view title on table dialect element
     * selection.
     */
    public void testPropertiesViewTitleOnTableDialectEditorWithContributions() {
        addContributions();
        SWTBotEditor tableEditorBot = openRepresentation(localSession.getOpenedSession(), TABLE_DESC_NAME, "new " + TABLE_DESC_NAME, DTable.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        Object oldLabelProvider = null;
        try {
            oldLabelProvider = setPropertyViewLabelProvider(propertiesView);
            checkPropertiesLabelProvider(propertiesView, DTableLabelProvider.class);
            tableEditorBot.bot().tree().getAllItems()[0].select();
            SWTBot propertiesViewBot = propertiesView.bot();
            SWTBotCLabel titleLabelBot = propertiesViewBot.clabel();
            String assertMessage = "On DTableElement selection, the TableLabelProvider should be used";
            assertEquals(assertMessage, TableLabelProvider.LABEL, titleLabelBot.getText());
            assertTrue(assertMessage, ImageEquality.areEqualImages(TABLE_IMAGE, titleLabelBot.image()));
        } finally {
            resetPropertyViewLabelProvider(propertiesView, oldLabelProvider);
            tableEditorBot.close();
        }
    }

    /**
     * Test the customization of properties view title on tree dialect element
     * selection.
     */
    public void testPropertiesViewTitleOnTreeDialectEditorWithContributions() {
        addContributions();
        SWTBotEditor treeEditorBot = openRepresentation(localSession.getOpenedSession(), TREE_DESC_NAME, "new " + TREE_DESC_NAME, DTree.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        SWTBot propertiesViewBot = propertiesView.bot();
        Object oldLabelProvider = null;
        try {
            oldLabelProvider = setPropertyViewLabelProvider(propertiesView);
            checkPropertiesLabelProvider(propertiesView, DTableLabelProvider.class);
            treeEditorBot.bot().tree().getAllItems()[0].select();
            SWTBotCLabel titleLabelBot = propertiesViewBot.clabel();
            String assertMessage = "On DTreeElement selection, the TreeLabelProvider should be used";
            assertEquals(assertMessage, TreeLabelProvider.LABEL, titleLabelBot.getText());
            assertTrue(assertMessage, ImageEquality.areEqualImages(TREE_IMAGE, titleLabelBot.image()));
        } finally {
            resetPropertyViewLabelProvider(propertiesView, oldLabelProvider);
            treeEditorBot.close();
        }
    }

    /**
     * Have LabelProviderProviderTests executed always with the Sirius
     * propertyContributor labelProviders for properties view instead of
     * EEFLabelProvider when EEF is in the runtime.
     */
    private Object setPropertyViewLabelProvider(SWTBotView propertiesView) {
        Object oldLabelProvider = null;
        if (Platform.getBundle("org.eclipse.emf.eef.runtime") != null) {
            IPage currentPage = ((org.eclipse.ui.views.properties.PropertySheet) propertiesView.getReference().getView(false)).getCurrentPage();
            Optional<Object> valueOption = ReflectionHelper.getFieldValueWithoutException(currentPage, "registry");
            assertTrue(valueOption.isPresent() && valueOption.get() instanceof TabbedPropertyRegistry);
            TabbedPropertyRegistry tabbedPropertyRegistry = (TabbedPropertyRegistry) valueOption.get();
            String contributorId = (String) ReflectionHelper.getFieldValueWithoutException(tabbedPropertyRegistry, "contributorId").get();
            oldLabelProvider = ReflectionHelper.getFieldValueWithoutException(tabbedPropertyRegistry, "labelProvider").get();
            Object newLabelProvider = null;
            if (DiagramUIPlugin.ID.equals(contributorId)) {
                newLabelProvider = new SiriusSheetLabelProvider();
            } else if ("org.eclipse.sirius.table.ui.EditorID".equals(contributorId) || DTreeEditor.ID.equals(contributorId)) {
                newLabelProvider = new DTableLabelProvider();
            }
            if (newLabelProvider != null) {
                ReflectionHelper.setFieldValueWithoutException(tabbedPropertyRegistry, "labelProvider", newLabelProvider);
            }
        }
        return oldLabelProvider;
    }

    private void checkPropertiesLabelProvider(SWTBotView propertiesView, Class<?> labelProviderType) {
        IPage currentPage = ((org.eclipse.ui.views.properties.PropertySheet) propertiesView.getReference().getView(false)).getCurrentPage();
        Optional<Object> valueOption = ReflectionHelper.getFieldValueWithoutException(currentPage, "registry");
        assertTrue(valueOption.isPresent() && valueOption.get() instanceof TabbedPropertyRegistry);
        TabbedPropertyRegistry tabbedPropertyRegistry = (TabbedPropertyRegistry) valueOption.get();
        assertEquals("The properties view labelProvider is not of the expected type", labelProviderType, tabbedPropertyRegistry.getLabelProvider().getClass());
    }

    private void resetPropertyViewLabelProvider(SWTBotView propertiesView, Object oldLabelProvider) {
        if (oldLabelProvider != null) {
            IPage currentPage = ((org.eclipse.ui.views.properties.PropertySheet) propertiesView.getReference().getView(false)).getCurrentPage();
            Optional<Object> valueOption = ReflectionHelper.getFieldValueWithoutException(currentPage, "registry");
            assertTrue(valueOption.isPresent() && valueOption.get() instanceof TabbedPropertyRegistry);
            TabbedPropertyRegistry tabbedPropertyRegistry = (TabbedPropertyRegistry) valueOption.get();
            ReflectionHelper.setFieldValueWithoutException(tabbedPropertyRegistry, "labelProvider", oldLabelProvider);
        }
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

        @Override
        public ILabelProvider getLabelProvider() {
            return new TreeLabelProvider();
        }

        @Override
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

        @Override
        public ILabelProvider getLabelProvider() {
            return new TableLabelProvider();
        }

        @Override
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

        @Override
        public ILabelProvider getLabelProvider() {
            return new DiagramLabelProvider();
        }

        @Override
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

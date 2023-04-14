/*******************************************************************************
 * Copyright (c) 2015, 2017, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.Decoration;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.ext.jface.viewers.IToolTipProvider;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;

import com.google.common.collect.Iterables;

/**
 * Tests for {@link IToolTipProvider} with different dialects and Model Explorer
 * view.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TooltipProviderTests extends SiriusTestCase {

    private static final String PATH = "/data/unit/tooltip/";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "Bug460075.aird";

    private static final String SEMANTIC_RESOURCE_NAME = "Bug460075.ecore";

    private static final String MODELER_RESOURCE_NAME = "Bug460075.odesign";

    private static final String DECORATOR_IMAGE_NAME = "decorator_lock.png";

    private EPackage rootEPackage;

    private AdapterFactory adapterFactory;

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_PRINT_DECORATION.name(), true);

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + MODELER_RESOURCE_NAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + SEMANTIC_RESOURCE_NAME, TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + REPRESENTATIONS_RESOURCE_NAME, TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + DECORATOR_IMAGE_NAME, TEMPORARY_PROJECT_NAME + "/" + DECORATOR_IMAGE_NAME);
        closeWelcomePage();
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().setPerspective(PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithLabel("Modeling"));
        TestsUtil.synchronizationWithUIThread();
        URI sessionResourceURI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_RESOURCE_NAME, true);
        session = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        Resource semanticResource = session.getSemanticResources().iterator().next();
        rootEPackage = (EPackage) semanticResource.getContents().get(0);

        adapterFactory = new AdapterFactory();
        Platform.getAdapterManager().registerAdapters(adapterFactory, EObject.class);
    }

    public void testTooltipOnDiagramDialect() {
        Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(session);
        DDiagram dDiagram = Iterables.getOnlyElement(Iterables.filter(allRepresentations, DDiagram.class));
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertTrue(editor instanceof DDiagramEditorImpl);
        DDiagramEditorImpl dDiagramEditorImpl = (DDiagramEditorImpl) editor;
        IFigure layer = dDiagramEditorImpl.getDiagramEditPart().getLayer(DiagramRootEditPart.DECORATION_PRINTABLE_LAYER);
        Iterable<Decoration> decorationsIterable = Iterables.filter(layer.getChildren(), Decoration.class);
        assertEquals(1, Iterables.size(decorationsIterable));
        Decoration decoration = Iterables.getOnlyElement(decorationsIterable);
        IFigure toolTip = getToolTip(decoration);
        assertTrue(toolTip instanceof Label);
        Label label = (Label) toolTip;
        assertEquals("The tooltip is not the one defined in VSM", "MyToolTip", label.getText());
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    private IFigure getToolTip(IFigure figure) {
        IFigure tooltip = null;
        IFigure currentFigure = figure;
        while (tooltip == null) {
            tooltip = currentFigure.getToolTip();
            List<? extends IFigure> children = currentFigure.getChildren();
            if (children.size() > 0) {
                currentFigure = children.get(0);
            } else {
                break;
            }
        }
        return tooltip;
    }

    public void testTooltipOnTableEditionDialect() {
        List<DRepresentation> allRepresentations = new ArrayList<DRepresentation>(DialectManager.INSTANCE.getAllRepresentations(session));
        DTable dEditionTable = Iterables.filter(allRepresentations, DTable.class).iterator().next();
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, dEditionTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertTrue(editor instanceof IViewerProvider);
        IViewerProvider viewerProvider = (IViewerProvider) editor;
        Viewer viewer = viewerProvider.getViewer();
        assertTrue(viewer instanceof TreeViewer);
        TreeViewer treeViewer = (TreeViewer) viewer;
        CellLabelProvider labelProvider = treeViewer.getLabelProvider(0);
        String toolTipText = labelProvider.getToolTipText(rootEPackage.getESubpackages().get(0));
        assertEquals(TooltipProvider.TOOLTIP, toolTipText);
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testTooltipOnCrossTableDialect() {
        List<DRepresentation> allRepresentations = new ArrayList<DRepresentation>(DialectManager.INSTANCE.getAllRepresentations(session));
        Iterator<DTable> iterator = Iterables.filter(allRepresentations, DTable.class).iterator();
        // The first is the edition table.
        iterator.next();
        DTable dCrossTable = iterator.next();
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, dCrossTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertTrue(editor instanceof IViewerProvider);
        IViewerProvider viewerProvider = (IViewerProvider) editor;
        Viewer viewer = viewerProvider.getViewer();
        assertTrue(viewer instanceof TreeViewer);
        TreeViewer treeViewer = (TreeViewer) viewer;
        CellLabelProvider labelProvider = treeViewer.getLabelProvider(0);
        String toolTipText = labelProvider.getToolTipText(rootEPackage.getESubpackages().get(0));
        assertEquals(TooltipProvider.TOOLTIP, toolTipText);
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testTooltipOnTreeDialect() {
        Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(session);
        DTree dTree = Iterables.getOnlyElement(Iterables.filter(allRepresentations, DTree.class));
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, dTree, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertTrue(editor instanceof IViewerProvider);
        IViewerProvider viewerProvider = (IViewerProvider) editor;
        Viewer viewer = viewerProvider.getViewer();
        assertTrue(viewer instanceof ContentViewer);
        ContentViewer contentViewer = (ContentViewer) viewer;
        IBaseLabelProvider labelProvider = contentViewer.getLabelProvider();
        assertTrue(labelProvider instanceof CellLabelProvider);
        CellLabelProvider cellLabelProvider = (CellLabelProvider) labelProvider;
        String toolTipText = cellLabelProvider.getToolTipText(rootEPackage.getESubpackages().get(0));
        assertEquals(TooltipProvider.TOOLTIP, toolTipText);
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void _testTooltipOnModelExplorer() {
        IViewPart modelExplorerView = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(IModelExplorerView.ID);
        assertNotNull(modelExplorerView);
        assertTrue(modelExplorerView instanceof CommonNavigator);
        CommonNavigator commonNavigator = (CommonNavigator) modelExplorerView;
        CommonViewer commonViewer = commonNavigator.getCommonViewer();
        IBaseLabelProvider labelProvider = commonViewer.getLabelProvider();
        assertTrue(labelProvider instanceof CellLabelProvider);
        CellLabelProvider cellLabelProvider = (CellLabelProvider) labelProvider;
        String toolTipText = cellLabelProvider.getToolTipText(rootEPackage.getESubpackages().get(0));
        assertEquals(TooltipProvider.TOOLTIP, toolTipText);
    }

    @Override
    protected void tearDown() throws Exception {
        Platform.getAdapterManager().unregisterAdapters(adapterFactory);
        adapterFactory = null;
        rootEPackage = null;
        session.close(new NullProgressMonitor());
        session = null;
        super.tearDown();
    }

    static class AdapterFactory implements IAdapterFactory {

        private static final Class<?>[] CLASSES = { IToolTipProvider.class };

        @Override
        public Object getAdapter(Object adaptableObject, @SuppressWarnings("rawtypes") Class adapterType) {
            if (adapterType == IToolTipProvider.class) {
                return adapterType.cast(new TooltipProvider());
            }
            return null;
        }

        @Override
        public Class<?>[] getAdapterList() {
            return CLASSES;
        }

    }

    static class TooltipProvider implements IToolTipProvider {

        public static final String TOOLTIP = "toolTip";

        @Override
        public String getToolTipText(Object element) {
            return TOOLTIP;
        }

    }

}

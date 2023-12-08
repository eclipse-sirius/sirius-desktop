/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.suite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.*;
import org.eclipse.sirius.tests.swtbot.celleditor.CellEditorExtensionTest;
import org.eclipse.sirius.tests.swtbot.clipboard.CustomClipboardSupportTest;
import org.eclipse.sirius.tests.swtbot.clipboard.DisabledSiriusClipboardSupportTest;
import org.eclipse.sirius.tests.swtbot.clipboard.GenericClipboardSupportTest;
import org.eclipse.sirius.tests.swtbot.clipboard.MultiSessionCopyPasteTest;
import org.eclipse.sirius.tests.swtbot.compartment.CompartmentsDragAndDropTest;
import org.eclipse.sirius.tests.swtbot.compartment.CompartmentsSizeTest;
import org.eclipse.sirius.tests.swtbot.compartment.CompartmentsTest;
import org.eclipse.sirius.tests.swtbot.compartment.CompartmentsWithComponentTest;
import org.eclipse.sirius.tests.swtbot.crossTable.CrossTableIntersectionExpressionTest;
import org.eclipse.sirius.tests.swtbot.crossTable.CrossTableIntersectionTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.BorderNodeSidePropertySectionTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.BorderSizeComputationExpressionTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.CompletionProposalInVSMTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.ContainerDropPropertySectionsTests;
import org.eclipse.sirius.tests.swtbot.editor.vsm.ContentAssistTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.CreateMandatoryElementsTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.CustomizationPropertySectionsTests;
import org.eclipse.sirius.tests.swtbot.editor.vsm.FeatureAssistTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.LayoutOptionsTests;
import org.eclipse.sirius.tests.swtbot.editor.vsm.MetamodelPropertyTabTests;
import org.eclipse.sirius.tests.swtbot.editor.vsm.MigrationOnVsmEditorReloadTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.OpeningContextTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.ResizeKindEditorTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.ServiceNavigationTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.VSMEditorPropertiesTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.VSMFieldTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.ValidationEmptyNameTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.ViewpointSpecificationProjectCreationTest;
import org.eclipse.sirius.tests.swtbot.layout.BorderedNodeCopyPasteFormatTest;
import org.eclipse.sirius.tests.swtbot.layout.ContainerAndNodeCopyPasteFormatTest;
import org.eclipse.sirius.tests.swtbot.layout.ContainerDefaultSizeLayoutTest;
import org.eclipse.sirius.tests.swtbot.layout.EdgeAndPortStabilityOnSemanticChangeTest;
import org.eclipse.sirius.tests.swtbot.layout.EdgeCopyPasteFormatTest;
import org.eclipse.sirius.tests.swtbot.layout.EdgeLabelsAlignAndDistributeTests;
import org.eclipse.sirius.tests.swtbot.layout.EdgeLayoutStabilityWithToolWizardTest;
import org.eclipse.sirius.tests.swtbot.layout.EdgeStabilityOnCopyPasteLayoutTest;
import org.eclipse.sirius.tests.swtbot.layout.LayoutStabilityOnManualRefreshTest;
import org.eclipse.sirius.tests.swtbot.layout.ModifyEdgeLayoutAfterRefreshTest;
import org.eclipse.sirius.tests.swtbot.layout.PackageLayoutStabilityOnManyViewsCreationToolTest;
import org.eclipse.sirius.tests.swtbot.layout.ResetOriginTest;
import org.eclipse.sirius.tests.swtbot.layout.StraightenToTest;
import org.eclipse.sirius.tests.swtbot.layout.ZOrderActionsTest;
import org.eclipse.sirius.tests.swtbot.modelexplorer.ContextualMenuTest;
import org.eclipse.sirius.tests.swtbot.modelexplorer.LinkWithEditorFeatureWithModelExplorerViewTest;
import org.eclipse.sirius.tests.swtbot.modelexplorer.LockedModelExplorerTest;
import org.eclipse.sirius.tests.swtbot.modelexplorer.ModelExplorerFilterTests;
import org.eclipse.sirius.tests.swtbot.modelexplorer.ModelExplorerInvalidRepTest;
import org.eclipse.sirius.tests.swtbot.modelexplorer.ProjectDependenciesTest;
import org.eclipse.sirius.tests.swtbot.modelexplorer.ProjectsConcurrentCloseTest;
import org.eclipse.sirius.tests.swtbot.modelexplorer.PropertyViewOnModelExplorerSelectionTests;
import org.eclipse.sirius.tests.swtbot.modelexplorer.PropertyViewOnModelExplorerSelectionWithLockedObjectTest;
import org.eclipse.sirius.tests.swtbot.modelexplorer.RepresentationVisibilityAfterSessionReloadTest;
import org.eclipse.sirius.tests.swtbot.propertypage.SiriusPreferencesPropertyPageTest;
import org.eclipse.sirius.tests.swtbot.propertypage.SiriusSessionDetailsPropertyPageTest;
import org.eclipse.sirius.tests.swtbot.sessioneditor.SessionEditorOpeningTests;
import org.eclipse.sirius.tests.swtbot.tabbar.LockedTabBarTest;
import org.eclipse.sirius.tests.swtbot.tabbar.NotInvisibleTabBarTest;
import org.eclipse.sirius.tests.swtbot.tabbar.ResetToDefaultFiltersActionTests;
import org.eclipse.sirius.tests.swtbot.tabbar.TabBarTest;
import org.eclipse.sirius.tests.swtbot.tabbar.TabbarContributorExtensionTest;
import org.eclipse.sirius.tests.swtbot.table.SetPropertyOfTableTreeByPropertiesViewTest;
import org.eclipse.sirius.tests.swtbot.uml.CopyPasteFormatOfLabelOfBorderedNodeTest;
import org.eclipse.sirius.tests.swtbot.uml.CopyPasteLayoutOfPortsWithConflictWithNotPastedPortsTest;
import org.eclipse.sirius.tests.swtbot.uml.CopyPasteLayoutOfPortsWithConflictWithPastedPortsTest;
import org.eclipse.sirius.tests.swtbot.uml.PortLocationAfterDragAndDropOnDiagramTest;
import org.eclipse.sirius.tests.swtbot.uml.PortLocationAfterDragAndDropTest;
import org.eclipse.sirius.tests.unit.common.EnvironmentReportTest;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * All SWTBot tests.
 *
 * @author lredor
 */
public class AllTestSuite extends TestCase {

    /**
     * Launches the test with the given arguments.
     *
     * @param args
     *            Arguments of the testCase.
     */
    public static void main(final String[] args) {
        TestRunner.run(suite());
    }

    @SuppressWarnings("javadoc")
    public static class SWTBotBundlesReport extends EnvironmentReportTest {
        public SWTBotBundlesReport() {
            super(Activator.getDefault().getBundle(), "SWTBot");
        }
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the test.<BR>
     * This suite can also launch a specific test by setting the environment variable TEST_CLASS_NAME to the qualified
     * name of the expected class to launch.
     *
     * @return The testsuite containing all the tests
     */
    @SuppressWarnings("unchecked")
    public static Test suite() {
        TestSuite suite = new TestSuite("Sirius SwtBot tests");

        String testClassQualifiedName = System.getenv("TEST_CLASS_NAME"); //$NON-NLS-1$
        if (testClassQualifiedName != null && testClassQualifiedName.length() > 0) {
            try {
                Class<?> testToLaunch = Activator.getDefault().getBundle().loadClass(testClassQualifiedName);
                if (TestCase.class.isAssignableFrom(testToLaunch)) {
                    suite.addTestSuite((Class<? extends TestCase>) testToLaunch);
                }
            } catch (ClassNotFoundException e) {
                fail("The class " + testClassQualifiedName + ", to launch for test specific case, has not been found.");
            }
        } else {
            addPart1(suite);
            addPart2(suite);
        }
        return suite;
    }

    /**
     * Add the first part of the SWTbot Gerrit tests to the specified suite.
     *
     * @param suite
     *            the suite into which to add the tests.
     */
    public static void addGerritPart1(TestSuite suite) {
        suite.addTest(new JUnit4TestAdapter(SWTBotBundlesReport.class));
        suite.addTestSuite(ContentAssistTest.class);
        suite.addTestSuite(FeatureAssistTest.class);
        suite.addTestSuite(ResizeKindEditorTest.class);
        suite.addTestSuite(CascadingSiriusURITest.class);
        suite.addTestSuite(AssociatedElementsOnPropertyViewTest.class);
        suite.addTestSuite(DiagramCreationDescriptionFromDNodeListElementTest.class);
        suite.addTestSuite(DiagramCreationDescriptionTest.class);
        suite.addTestSuite(DiagramDocumentationTest.class);
        suite.addTestSuite(DirectEditLabelTest.class);
        suite.addTestSuite(SetStyleToWorkspaceImageTests.class);
        suite.addTestSuite(SelectAllAndDeselectionTest.class);
        suite.addTestSuite(SessionCreationTest.class);
        suite.addTestSuite(LabelAlignmentRefreshTest.class);
        suite.addTestSuite(LabelProviderProviderTests.class);
        suite.addTestSuite(PinnedElementsOnTreeDiagramTest.class);
        suite.addTestSuite(PinnedNotesTest.class);
        suite.addTestSuite(RemoveEdgeBendpointsTest.class);
        suite.addTestSuite(RemoveNoteTextTest.class);
        suite.addTestSuite(RequestInterpreterTest.class);
        suite.addTestSuite(ResetStylePropertiesToDefaultValuesActionTests.class);
        suite.addTestSuite(MoveBorderNodeTest.class);
        suite.addTestSuite(LayoutingModeTest.class);
        suite.addTestSuite(EditModeTest.class);
        suite.addTestSuite(LabelSelectionTest.class);
        suite.addTestSuite(KeyboardDeleteFromDiagramTests.class);
        suite.addTestSuite(CustomClipboardSupportTest.class);
        suite.addTestSuite(KeyboardDeletionFromLabelTests.class);
        suite.addTestSuite(DragAndDropDifferentElementsTest.class);
        suite.addTestSuite(ShowTypeActionButtonTest.class);
        suite.addTestSuite(LineStyleTest.class);
        suite.addTestSuite(CompartmentsDragAndDropTest.class);
        suite.addTestSuite(EdgeSelectionTest.class);
        suite.addTestSuite(DiagramMouseZoomTest.class);
        suite.addTestSuite(DiagramZoomTest.class);
        suite.addTestSuite(EdgeLabelsMoveFromEdgeMoveTest.class);
        suite.addTestSuite(OpeningContextTest.class);
        suite.addTestSuite(NodeWithDecoratorSelectionTest.class);
        suite.addTestSuite(ManualAirdModificationTest.class);
        suite.addTestSuite(SpecificClosedOrNotClosedEditorTest.class);
        suite.addTestSuite(MigrationTest.class);
        suite.addTest(new JUnit4TestAdapter(DragNDropTest.class));
        suite.addTestSuite(OpenDiagramWithExceptionTest.class);
        suite.addTestSuite(CompartmentsSizeTest.class);
        suite.addTestSuite(SiriusPreferencesPropertyPageTest.class);
        suite.addTestSuite(SiriusSessionDetailsPropertyPageTest.class);
    }

    /**
     * Add the second part of the SWTbot Gerrit tests to the specified suite.
     *
     * @param suite
     *            the suite into which to add the tests.
     */
    public static void addGerritPart2(TestSuite suite) {
        suite.addTest(new JUnit4TestAdapter(SWTBotBundlesReport.class));
        suite.addTestSuite(NoteCreationTest.class);
        suite.addTestSuite(LinkNoteFragmentModelCreationTest.class);
        suite.addTestSuite(MigrationOnVsmEditorReloadTest.class);
        suite.addTestSuite(VSMFieldTest.class);
        suite.addTestSuite(VSMAndDiagramEditorSynchronisationTest.class);
        suite.addTestSuite(SVGImageBundleTest.class);
        suite.addTestSuite(SVGZImageTest.class);
        suite.addTestSuite(RoutingStyleTest.class);
        suite.addTestSuite(RepresentationGroupWithoutRepresentationInstanceTest.class);
        suite.addTestSuite(ReconnectEdgeWithChangedRoutingStyleTest.class);
        suite.addTestSuite(ChangedRoutingStyleTest.class);
        suite.addTestSuite(ReconnectEdgeBendpointStabilityTest.class);
        suite.addTestSuite(QuickStartScenario.class);
        suite.addTestSuite(NonVisibleLabelSelectionTest.class);
        suite.addTestSuite(NodeLabelPositionTest.class);
        suite.addTestSuite(NavigateToNewRepresentationTest.class);
        suite.addTestSuite(LockedAppearanceTabTest.class);
        suite.addTestSuite(GroupElementsInOneOtherTests.class);
        suite.addTestSuite(GroupElementsInOneOtherTestsWith200PercentOfZoomTests.class);
        suite.addTestSuite(GroupElementsInOneOtherTestsWith50PercentOfZoomTests.class);
        suite.addTestSuite(ExtraMappingEdgeCreationTest.class);
        suite.addTestSuite(ExportDiagramAsImageWhenManyRepresentationsHaveSameNameTest.class);
        suite.addTestSuite(EdgeCreationTest.class);
        suite.addTestSuite(DragAndDropFromTableAndTreeToDiagramTest.class);
        suite.addTestSuite(DeleteFromDiagramTest.class);
        suite.addTestSuite(DeleteDiagramWithListeningPaletteToolTest.class);
        suite.addTestSuite(BoldItalicFontSynchronizationTest.class);
        suite.addTestSuite(RepresentationRenamingTest.class);
        suite.addTestSuite(PopupMenuTest.class);
        suite.addTestSuite(NodeCreationTest.class);
        suite.addTestSuite(NodeCreationWithSnapToGridTest.class);
        suite.addTestSuite(GenericClipboardSupportTest.class);
        suite.addTestSuite(MultiSessionCopyPasteTest.class);
        suite.addTestSuite(CloseWithoutSavingTest.class);
        suite.addTestSuite(CreateMandatoryElementsTest.class);
        suite.addTestSuite(LockedModelExplorerTest.class);
        suite.addTestSuite(SnapAllShapesTest.class);
        if (!(System.getProperty("os.name").contains("Linux") && TestsUtil.is202003Platform())) {
            suite.addTestSuite(MoveEdgeGroupTest.class);
        }
        suite.addTestSuite(TabbarContributorExtensionTest.class);
        if (!(System.getProperty("os.name").contains("Linux") && TestsUtil.is202003Platform())) {
            suite.addTestSuite(BorderNodeSideTest.class);
        }
        suite.addTestSuite(SecurityExceptionPopupTest.class);
        suite.addTestSuite(CenteredEdgesRepairTest.class);
        suite.addTestSuite(MetamodelPropertyTabTests.class);
        suite.addTestSuite(DiagramPrintTest.class);
        suite.addTestSuite(PinnedElementsTest.class);
        suite.addTestSuite(StraightenToTest.class);
        suite.addTestSuite(ZOrderActionsTest.class);
        suite.addTest(new JUnit4TestAdapter(DndWorkspaceToAirdEditorTest.class));
        suite.addTestSuite(SessionEditorTest.class);
        suite.addTestSuite(SessionEditorPageProvidingTest.class);
        suite.addTestSuite(SessionEditorProjectRemovalTest.class);
        suite.addTest(new JUnit4TestAdapter(RefreshAfterViewCreationTest.class));
    }

    /**
     * Add the first part of the SWTbot tests to the specified suite. This corresponds roughly to the first half of the
     * execution time of the complete suite.
     *
     * @param suite
     *            the suite into which to add the tests.
     */
    public static void addPart1(TestSuite suite) {
        // This one should be executed as early as possible
        suite.addTestSuite(CellEditorExtensionTest.class);

        addGerritPart1(suite);

        suite.addTestSuite(EdgeReconnectionTests.class);
        suite.addTestSuite(ServiceNavigationTest.class);
        // suite.addTest(PseudoClearcaseSwtbotTestSuite.suite());
        STDSwtbotTestSuite.addPart1(suite);
        suite.addTest(UMLSwtbotTestSuite.suite());
        TableSwtbotTestSuite.addPart1(suite);

        suite.addTestSuite(CrossTableIntersectionTest.class);
        suite.addTestSuite(CrossTableIntersectionExpressionTest.class);

        // TODO MCH : Fix this test. This test is deactivate because the
        // build
        // fails on Hudson (Timeout Exception)
        // suite.addTestSuite(DndWorkspaceSupportTest.class);
        // suite.addTest(SequenceSwtBotTestSuite.suite());

        suite.addTestSuite(RefreshWithCustomizedStyleTests.class);
        suite.addTestSuite(ArrangeAllLinkedBorderedNodesLayoutStabilityAppTemoinTest.class);
        suite.addTestSuite(ArrangeAllLinkedBorderedNodesLayoutStabilityTest.class);
        suite.addTestSuite(ArrangeAllLinkedBorderedNodesWithSnapToGridTest.class);
        suite.addTestSuite(DistributeActionTests.class);
        suite.addTestSuite(EdgeLabelsAlignAndDistributeTests.class);
        suite.addTestSuite(CompartmentsTest.class);
        suite.addTestSuite(CompartmentsWithComponentTest.class);

        suite.addTestSuite(ArrangeAllTest.class);
        suite.addTestSuite(ArrangeAllWithSnapToGridTest.class);
        suite.addTest(new JUnit4TestAdapter(DragAndDropWithSnapToGridTest.class));
        suite.addTestSuite(EdgeStabilityOnBendpointsAlignmentTest.class);
        suite.addTestSuite(EdgeStabilityOnDragAndDropTest.class);
        suite.addTestSuite(EdgeLabelStabilityTest.class);
        suite.addTestSuite(EdgeLabelUpdateTest.class);
        suite.addTestSuite(EdgeLabelsMoveTest.class);
        suite.addTestSuite(EdgeStabilityOnLayerManagementTest.class);
        suite.addTestSuite(EdgeOnFigureWithAlphaAnchorTest.class);
        suite.addTestSuite(EditorSavingTest.class);
        suite.addTestSuite(EmptyPropertyViewAfterDeletionTest.class);
        suite.addTestSuite(RepresentationVisibilityAfterSessionReloadTest.class);
        suite.addTestSuite(HideRevealDiagramElementsLabelsTestWithOldUI.class);
        suite.addTestSuite(HideLabelIconsWithPreferencesTest.class);
        // suite.addTestSuite(LabelFontModificationsTest.class);
        if (!(System.getProperty("os.name").contains("Linux") && TestsUtil.is202003Platform())) {
            suite.addTestSuite(MultiLineLabelDiagramTest.class);
            suite.addTestSuite(NodeCreationPositionTest.class);
        }
        suite.addTestSuite(OpenMultipleRepresentationsTest.class);
        suite.addTestSuite(ShapeResizingEdgePositionStabilityTests.class);
        suite.addTestSuite(PortsOnNodePositionStabilityTest.class);
        suite.addTest(new JUnit4TestAdapter(PortLocationAfterDragAndDropTest.class));
        suite.addTestSuite(PortLocationAfterDragAndDropOnDiagramTest.class);
        suite.addTestSuite(CopyPasteLayoutOfPortsWithConflictWithNotPastedPortsTest.class);
        suite.addTestSuite(CopyPasteLayoutOfPortsWithConflictWithPastedPortsTest.class);
        suite.addTestSuite(CopyPasteFormatOfLabelOfBorderedNodeTest.class);
        suite.addTestSuite(NodeBorderLabelPositionStabilityTest.class);
        suite.addTestSuite(EdgeLayoutStabilityWithToolWizardTest.class);
        suite.addTestSuite(EdgeStabilityOnCopyPasteLayoutTest.class);
        suite.addTestSuite(PortSelectionTest.class);
        suite.addTestSuite(PortNotVisibleSelectionTest.class);
        suite.addTestSuite(RefreshAfterUndoDeletionFromGenericToolTest.class);
        suite.addTestSuite(RefreshWithCustomizedStyleFromAppearanceTabTests.class);
        suite.addTestSuite(RefreshWithCustomizedStyleFromTabbarTests.class);
        suite.addTestSuite(ResizeKindRefreshTests.class);
        suite.addTestSuite(TreeFoldingTest.class);
        suite.addTestSuite(DoubleClickToolNavigationOperationTest.class);
        suite.addTestSuite(BackgroundColorFigureUpdateTests.class);
        suite.addTestSuite(BackgroundStyleUpdateTest.class);
        suite.addTestSuite(EdgeMappingTestCase.class);
        suite.addTestSuite(InvalidMetamodelRessourceTest.class);
        suite.addTestSuite(ExtensionActivationOrderTest.class);
        suite.addTestSuite(ContainerDropPropertySectionsTests.class);
        suite.addTestSuite(BorderNodeSidePropertySectionTest.class);
        suite.addTestSuite(EditPartSelectionTest.class);
        suite.addTestSuite(LabelFontModificationsTest.class);
        suite.addTestSuite(CreatedElementsLayoutTests.class);
        suite.addTestSuite(SizeComputationExpressionTest.class);
    }

    /**
     * Add the second part of the SWTbot tests to the specified suite. This corresponds roughly to the second half of
     * the execution time of the complete suite.
     *
     * @param suite
     *            the suite into which to add the tests.
     */
    public static void addPart2(TestSuite suite) {
        // The ViewpointProjectCreationTest should be done before the others
        // ones:
        // to verify the behavior when a specifier first launches the product.
        suite.addTestSuite(ViewpointSpecificationProjectCreationTest.class);

        addGerritPart2(suite);
        STDSwtbotTestSuite.addPart2(suite);
        TableSwtbotTestSuite.addPart2(suite);

        suite.addTestSuite(CustomizationPropertySectionsTests.class);
        suite.addTestSuite(GoToMarkerTraceabilityWithUserInteractionTest.class);
        suite.addTestSuite(NoteCreationWithSnapToGridTest.class);
        suite.addTestSuite(ContainerCreationTest.class);
        suite.addTestSuite(ContainerChildrenPresentationChangeTest.class);
        suite.addTestSuite(ContainerCreationWithSnapToGridTest.class);
        suite.addTestSuite(DNodeListCreationTest.class);
        suite.addTestSuite(DNodeListCreationWithSnapToGridTest.class);
        suite.addTestSuite(BorderedNodeCreationTest.class);
        suite.addTestSuite(BorderedNodeCreationWithSnapToGridTest.class);
        suite.addTestSuite(BorderedNodeResizeCreationTest.class);
        suite.addTestSuite(BorderedNodeCreationNearCollapsedTest.class);
        suite.addTestSuite(BorderedNodeCreationNearCollapsedWithSnapToGridTest.class);
        suite.addTestSuite(CollapsedBorderedNodeCreationTest.class);
        suite.addTestSuite(CollapsedBorderedNodeCreationWithSnapToGridTest.class);
        suite.addTestSuite(CollapsedBorderedNodeCreationNearCollapsedTest.class);
        suite.addTestSuite(CollapsedBorderedNodeCreationNearCollapsedWithSnapToGridTest.class);
        suite.addTestSuite(EdgeCreationPositionTest.class);
        suite.addTestSuite(EdgeCreationPositionWithSnapToGridTest.class);
        suite.addTestSuite(EdgeWithBorderNodeCreationPositionWithSnapToGridTest.class);
        suite.addTestSuite(HideRevealDiagramElementsLabelsTest.class);
        suite.addTestSuite(HideRevealEdgeLabelsTest.class);
        suite.addTestSuite(IndirectDeleteEdgeWithLabelSelectedTest.class);
        suite.addTestSuite(LinkWithEditorFeatureWithModelExplorerViewTest.class);
        suite.addTestSuite(SpecificLayoutBendpointsOnReopeningTest.class);
        suite.addTestSuite(BorderSizeComputationExpressionTest.class);
        suite.addTestSuite(CenteredEdgesTest.class);

        suite.addTestSuite(DeleteHookTests.class);

        // TODO DLE : reenable theses tests as soon as getEditPart method will
        // be based on matcher.
        // suite.addTestSuite(ToolWizardTest.class);
        suite.addTestSuite(ExportDiagramsAsImagesTest.class);
        suite.addTestSuite(ExportDiagramAsImageFromCloseSessionTest.class);
        suite.addTestSuite(ExportDiagramsAsImagesAndHtmlTest.class);
        suite.addTestSuite(TabBarTest.class);
        suite.addTestSuite(NotInvisibleTabBarTest.class);
        suite.addTestSuite(ResetToDefaultFiltersActionTests.class);
        suite.addTestSuite(LockedTabBarTest.class);
        suite.addTestSuite(LockedRepresentationContainerTest.class);
        suite.addTestSuite(ArrangeSelectionOnBreakdownDiagramTest.class);
        suite.addTestSuite(RemovedDefaultColorMenuTest.class);
        suite.addTestSuite(DuplicationCausedBySelectionTest.class);
        suite.addTestSuite(EdgeWithMultipleLabelsTest.class);
        // This test is KO if the cdonative plugins are active (VP-4101), so
        // launch it only when cdonative plugins are not active.
        try {
            Resource resource = new ResourceSetImpl().getResource(URI.createPlatformPluginURI("org.eclipse.sirius.cdonative/model/viewpoint.genmodel", true), true);
            if (resource == null) {
                suite.addTestSuite(DisabledSiriusClipboardSupportTest.class);
            }
        } catch (WrappedException e) {
            // Nothing to do, as viewpoint.cdonative is not accessible the
            // CDONative tests will not be launched
        }
        suite.addTestSuite(SetPropertyOfTableTreeByPropertiesViewTest.class);
        suite.addTestSuite(ElementCreationWithPopupMenuTests.class);
        suite.addTestSuite(ElementCreationWithPopupMenuWith200PercentOfZoomTests.class);
        suite.addTestSuite(ElementCreationWithPopupMenuWith50PercentOfZoomTests.class);
        suite.addTestSuite(PackageLayoutStabilityOnManyViewsCreationToolTest.class);
        suite.addTestSuite(ResetOriginTest.class);
        suite.addTestSuite(LayoutStabilityOnManualRefreshTest.class);
        suite.addTestSuite(EdgeAndPortStabilityOnSemanticChangeTest.class);
        suite.addTestSuite(SessionSaveableTest.class);
        suite.addTest(new JUnit4TestAdapter(DragAndDropFromControlledResourceTest.class));
        suite.addTestSuite(RoundedCornerRefreshTest.class);
        suite.addTestSuite(ModelExplorerFilterTests.class);
        suite.addTestSuite(ModelExplorerInvalidRepTest.class);
        suite.addTestSuite(ProjectDependenciesTest.class);
        suite.addTestSuite(ProjectsConcurrentCloseTest.class);
        suite.addTestSuite(PropertyViewOnModelExplorerSelectionWithLockedObjectTest.class);
        // PropertyViewOnModelExplorerSelectionTests.testPropertyViewEditionOnModelExplorerViewSelection()
        // pass in the suite with CloseWithoutSavingTest and
        // LockedModelExplorerTest executed before because of Bug 482122
        suite.addTestSuite(PropertyViewOnModelExplorerSelectionTests.class);
        suite.addTestSuite(ContextualMenuTest.class);
        suite.addTestSuite(BracketEdgeTests.class);
        suite.addTestSuite(EdgeCopyPasteFormatTest.class);
        if (!(System.getProperty("os.name").contains("Linux") && TestsUtil.is202003Platform())) {
            suite.addTestSuite(BendpointsStabilityOnMovesTest.class);
        }
        suite.addTestSuite(BendpointsStabilityOnMovesSpecificCasesTest.class);
        suite.addTestSuite(BorderedNodeCopyPasteFormatTest.class);
        suite.addTestSuite(ContainerAndNodeCopyPasteFormatTest.class);
        suite.addTestSuite(ContainerDefaultSizeLayoutTest.class);
        suite.addTestSuite(ModifyEdgeLayoutAfterRefreshTest.class);
        suite.addTest(TreeSwtbotTestSuite.suite());
        suite.addTestSuite(CompletionProposalInVSMTest.class);
        suite.addTestSuite(DirectEditWithInputLabelTest.class);
        suite.addTestSuite(AdditionalLayerTest.class);
        suite.addTestSuite(GroupingContentProviderTest.class);
        suite.addTestSuite(GroupingContentProviderByContainingTest.class);
        suite.addTestSuite(PaletteViewManagementTest.class);
        suite.addTestSuite(PaletteManagerAfterVSMSelectionChange.class);
        suite.addTestSuite(CreateRepresentationFromSessionTest.class);
        suite.addTestSuite(ToolCreationPositionTest.class);
        suite.addTestSuite(LocalSessionViewTest.class);
        suite.addTestSuite(ShowTypeActionButtonTest.class);
        suite.addTestSuite(ChildrenPositionStabilityAfterParentResizeTest.class);
        suite.addTestSuite(VSMEditorPropertiesTest.class);
        suite.addTestSuite(SessionEditorOpeningTests.class);
        suite.addTestSuite(NoteAttachmentTest.class);

        // Scenario test cases
        suite.addTestSuite(ESEDemoTest.class);
        if (!(System.getProperty("os.name").contains("Linux") && TestsUtil.is202003Platform())) {
            suite.addTestSuite(ValidationTest.class);
        }
        suite.addTestSuite(ViewpointSelectionDialogTest.class);
        suite.addTestSuite(InitializeEmptySessionTest.class);
        suite.addTestSuite(InitializeSessionTest.class);
        suite.addTestSuite(RepairTest.class);
        suite.addTestSuite(ValidationEmptyNameTest.class);
        suite.addTestSuite(ControlUncontrolWithOpenedRepresentationTest.class);
        suite.addTestSuite(SiriusInternationalizationTest.class);
        suite.addTestSuite(DecoratorTest.class);
        suite.addTestSuite(LayoutOptionsTests.class);
        suite.addTestSuite(RenameProjectWithSessionTest.class);
        suite.addTestSuite(RectilinearNoteAttachmentWithNoteTest.class);
        suite.addTestSuite(RectilinearNoteAttachmentWithRepresentationLinkTest.class);
        suite.addTestSuite(RectilinearNoteAttachmentWithTextTest.class);
        suite.addTestSuite(RemoveBendpointsRectilinearNoteAttachmentTest.class);
        suite.addTestSuite(RectilinearNoteAttachmentWithOneBendpointTest.class);
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the disabled test.
     *
     * @return The test suite containing all the disabled tests.
     */
    public static Test disabledSuite() {
        final TestSuite suite = new TestSuite("Sirius Disabled SwtBot tests");

        // TODO MCH : Fix this test. This test is deactivate because the build
        // fails on Hudson (Timeout Exception)
        suite.addTestSuite(DndWorkspaceSupportTest.class);

        // TODO DLE : reenable theses tests as soon as getEditPart method will
        // be based on matcher.
        suite.addTestSuite(ToolWizardTest.class);
        // See VP-2451
        suite.addTestSuite(FileModificationValidationTest.class);

        // The following tests was not referenced then I added them to the
        // disabled test suite
        suite.addTestSuite(ControlUncontrolTest.class);
        suite.addTestSuite(ControlUncontrolWithNoRepresentationTest.class);
        suite.addTestSuite(ControlUncontrolWithSessionNotSavedTest.class);

        // Lost behavior : double clic on aird from java now open the text
        // editor.
        suite.addTestSuite(SessionOpeningWithAirdNoDiagramTest.class);
        return suite;
    }
}

/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.suite;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.tests.swtbot.*;
import org.eclipse.sirius.tests.swtbot.celleditor.CellEditorExtensionTest;
import org.eclipse.sirius.tests.swtbot.clipboard.CustomClipboardSupportTest;
import org.eclipse.sirius.tests.swtbot.clipboard.DisabledSiriusClipboardSupportTest;
import org.eclipse.sirius.tests.swtbot.clipboard.GenericClipboardSupportTest;
import org.eclipse.sirius.tests.swtbot.clipboard.MultiSessionCopyPasteTest;
import org.eclipse.sirius.tests.swtbot.crossTable.CrossTableIntersectionExpressionTest;
import org.eclipse.sirius.tests.swtbot.crossTable.CrossTableIntersectionTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.BorderSizeComputationExpressionTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.CompletionProposalInVSMTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.ContentAssistTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.CreateMandatoryElementsTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.CustomizationPropertySectionsTests;
import org.eclipse.sirius.tests.swtbot.editor.vsm.MetamodelPropertyTabTests;
import org.eclipse.sirius.tests.swtbot.editor.vsm.MigrationOnVsmEditorReloadTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.ResizeKindEditorTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.VSMFieldTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.ValidationEmptyNameTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.ViewpointSpecificationProjectCreationTest;
import org.eclipse.sirius.tests.swtbot.layout.BorderedNodeCopyPastLayoutTest;
import org.eclipse.sirius.tests.swtbot.layout.ContainerDefaultSizeLayoutTest;
import org.eclipse.sirius.tests.swtbot.layout.EdgeAndPortStabilityOnSemanticChangeTest;
import org.eclipse.sirius.tests.swtbot.layout.EdgeCopyPasteLayoutTest;
import org.eclipse.sirius.tests.swtbot.layout.EdgeLayoutStabilityWithToolWizardTest;
import org.eclipse.sirius.tests.swtbot.layout.EdgeStabilityOnCopyPasteLayoutTest;
import org.eclipse.sirius.tests.swtbot.layout.LayoutStabilityOnManualRefreshTest;
import org.eclipse.sirius.tests.swtbot.layout.ModifyEdgeLayoutAfterRefreshTest;
import org.eclipse.sirius.tests.swtbot.layout.PackageLayoutStabilityOnManyViewsCreationToolTest;
import org.eclipse.sirius.tests.swtbot.layout.ResetOriginTest;
import org.eclipse.sirius.tests.swtbot.modelexplorer.ContextualMenuTest;
import org.eclipse.sirius.tests.swtbot.modelexplorer.LinkWithEditorFeatureWithModelExplorerViewTest;
import org.eclipse.sirius.tests.swtbot.modelexplorer.LockedModelExplorerTest;
import org.eclipse.sirius.tests.swtbot.modelexplorer.ModelExplorerFilterTests;
import org.eclipse.sirius.tests.swtbot.modelexplorer.ProjectDependenciesTest;
import org.eclipse.sirius.tests.swtbot.modelexplorer.ProjectsConcurrentCloseTest;
import org.eclipse.sirius.tests.swtbot.modelexplorer.RepresentationVisibilityAfterSessionReloadTest;
import org.eclipse.sirius.tests.swtbot.tabbar.LockedTabBarTest;
import org.eclipse.sirius.tests.swtbot.tabbar.NotInvisibleTabBarTest;
import org.eclipse.sirius.tests.swtbot.tabbar.TabBarTest;
import org.eclipse.sirius.tests.swtbot.table.SetPropertyOfTableTreeByPropertiesViewTest;
import org.eclipse.sirius.tests.swtbot.uml.CopyPasteLayoutOfLabelOfBorderedNodeTest;
import org.eclipse.sirius.tests.swtbot.uml.CopyPasteLayoutOfPortsWithConflictWithNotPastedPortsTest;
import org.eclipse.sirius.tests.swtbot.uml.CopyPasteLayoutOfPortsWithConflictWithPastedPortsTest;
import org.eclipse.sirius.tests.swtbot.uml.PortLocationAfterDragAndDropOnDiagramTest;
import org.eclipse.sirius.tests.swtbot.uml.PortLocationAfterDragAndDropTest;

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

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the test.
     * 
     * @return The testsuite containing all the tests
     */
    public static Test suite() {
        TestSuite suite = new TestSuite("Sirius SwtBot tests");
        addPart1(suite);
        addPart2(suite);
        return suite;
    }

    /**
     * Add the first part of the SWTbot Gerrit tests to the specified suite.
     * 
     * @param suite
     *            the suite into which to add the tests.
     */
    public static void addGerritPart1(TestSuite suite) {
        suite.addTestSuite(ContentAssistTest.class);
        suite.addTestSuite(MetamodelPropertyTabTests.class);
        suite.addTestSuite(ResizeKindEditorTest.class);
        suite.addTestSuite(CenteredEdgesRepairTest.class);
        suite.addTestSuite(CascadingSiriusURITest.class);
        suite.addTestSuite(AssociatedElementsOnPropertyViewTest.class);
        suite.addTestSuite(DiagramCreationDescriptionFromDNodeListElementTest.class);
        suite.addTestSuite(DiagramCreationDescriptionTest.class);
        suite.addTestSuite(DiagramDocumentationTest.class);
        suite.addTestSuite(DiagramPrintTest.class);
        suite.addTestSuite(DirectEditLabelTest.class);
        suite.addTestSuite(SetStyleToWorkspaceImageTests.class);
        suite.addTestSuite(SelectAllAndDeselectionTest.class);
        suite.addTestSuite(SessionCreationTest.class);
        suite.addTestSuite(LabelAlignmentRefreshTest.class);
        suite.addTestSuite(LabelProviderProviderTests.class);
        suite.addTestSuite(PinnedElementsOnTreeDiagramTest.class);
        suite.addTestSuite(PinnedElementsTest.class);
        suite.addTestSuite(PinnedNotesTest.class);
        suite.addTestSuite(RemoveEdgeBendpointsTest.class);
        suite.addTestSuite(RemoveNoteTextTest.class);
        suite.addTestSuite(RequestInterpreterTest.class);
        suite.addTestSuite(ResetStylePropertiesToDefaultValuesActionTests.class);
        suite.addTestSuite(MoveBorderNodeTest.class);
        suite.addTestSuite(LayoutingModeTest.class);
        suite.addTestSuite(LabelSelectionTest.class);
        suite.addTestSuite(KeyboardDeleteFromDiagramTests.class);
        suite.addTestSuite(CustomClipboardSupportTest.class);
        suite.addTestSuite(KeyboardDeletionFromLabelTests.class);
        suite.addTestSuite(DragAndDropDifferentElementsTest.class);
        suite.addTestSuite(CellEditorExtensionTest.class);
        suite.addTestSuite(RefreshWithCustomizedStyleTests.class);
        suite.addTestSuite(EdgeReconnectionTests.class);
        suite.addTestSuite(SpecificClosedOrNotClosedEditorTest.class);
        suite.addTestSuite(LineStyleTest.class);
    }

    /**
     * Add the second part of the SWTbot Gerrit tests to the specified suite.
     * 
     * @param suite
     *            the suite into which to add the tests.
     */
    public static void addGerritPart2(TestSuite suite) {

        suite.addTestSuite(NoteCreationTest.class);
        suite.addTestSuite(MigrationOnVsmEditorReloadTest.class);
        suite.addTestSuite(VSMFieldTest.class);
        suite.addTestSuite(VSMAndDiagramEditorSynchronisationTest.class);
        suite.addTestSuite(SVGImageBundleTest.class);
        suite.addTestSuite(SVGZImageTest.class);
        suite.addTestSuite(RoutingStyleTest.class);
        suite.addTestSuite(RepresentationGroupWithoutRepresentationInstanceTest.class);
        suite.addTestSuite(RenameProjectWithSessionTest.class);
        suite.addTestSuite(ReconnectEdgeWithChangedRoutingStyleTest.class);
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
        suite.addTestSuite(DeleteSemanticElementToCheckDecorator.class);
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
    }

    /**
     * Add the first part of the SWTbot tests to the specified suite. This
     * corresponds roughly to the first half of the execution time of the
     * complete suite.
     * 
     * @param suite
     *            the suite into which to add the tests.
     */
    public static void addPart1(TestSuite suite) {

        addGerritPart1(suite);

        suite.addTestSuite(CustomizationPropertySectionsTests.class);
        // TheViepointProjectCreationTest should be done before the others ones:
        // to verify the behavior when a specifier first launches the product.
        suite.addTestSuite(ViewpointSpecificationProjectCreationTest.class);

        // suite.addTest(PseudoClearcaseSwtbotTestSuite.suite());
        suite.addTest(STDSwtbotTestSuite.suite());
        suite.addTest(UMLSwtbotTestSuite.suite());
        suite.addTest(TableSwtbotTestSuite.suite());

        suite.addTestSuite(CrossTableIntersectionTest.class);
        suite.addTestSuite(CrossTableIntersectionExpressionTest.class);

        // TODO MCH : Fix this test. This test is deactivate because the
        // build
        // fails on Hudson (Timeout Exception)
        // suite.addTestSuite(DndWorkspaceSupportTest.class);
        // suite.addTest(SequenceSwtBotTestSuite.suite());

        suite.addTestSuite(ArrangeAllLinkedBorderedNodesLayoutStabilityAppTemoinTest.class);
        suite.addTestSuite(ArrangeAllLinkedBorderedNodesLayoutStabilityTest.class);
        suite.addTestSuite(DistributeActionTests.class);
        // TODO CBR : fix these tests
        // suite.addTestSuite(ArrangeAllTest.class);
        suite.addTestSuite(EdgeStabilityOnBendpointsAlignmentTest.class);
        suite.addTestSuite(EdgeStabilityOnDragAndDropTest.class);
        suite.addTestSuite(EdgeLabelStabilityTest.class);
        suite.addTestSuite(EdgeStabilityOnLayerManagementTest.class);
        suite.addTestSuite(EdgeOnFigureWithAlphaAnchorTest.class);
        suite.addTestSuite(CenteredEdgesTest.class);
        suite.addTestSuite(EditorSavingTest.class);
        suite.addTestSuite(EmptyPropertyViewAfterDeletionTest.class);
        suite.addTestSuite(LinkWithEditorFeatureWithModelExplorerViewTest.class);
        suite.addTestSuite(RepresentationVisibilityAfterSessionReloadTest.class);
        suite.addTestSuite(HideRevealDiagramElementsLabelsTest.class);
        suite.addTestSuite(HideRevealDiagramElementsLabelsTestWithOldUI.class);
        suite.addTestSuite(HideLabelIconsWithPreferencesTest.class);
        suite.addTestSuite(LabelFontModificationsTest.class);
        suite.addTestSuite(MultiLineLabelDiagramTest.class);
        suite.addTestSuite(NodeCreationPositionTest.class);
        suite.addTestSuite(OpenMultipleRepresentationsTest.class);
        suite.addTestSuite(ChildrenPositionStabilityAfterParentResizeTest.class);
        suite.addTestSuite(ShapeResizingEdgePositionStabilityTests.class);
        suite.addTestSuite(PortsOnNodePositionStabilityTest.class);
        suite.addTestSuite(PortLocationAfterDragAndDropTest.class);
        suite.addTestSuite(PortLocationAfterDragAndDropOnDiagramTest.class);
        suite.addTestSuite(CopyPasteLayoutOfPortsWithConflictWithNotPastedPortsTest.class);
        suite.addTestSuite(CopyPasteLayoutOfPortsWithConflictWithPastedPortsTest.class);
        suite.addTestSuite(CopyPasteLayoutOfLabelOfBorderedNodeTest.class);
        suite.addTestSuite(NodeBorderLabelPositionStabilityTest.class);
        suite.addTestSuite(EdgeLayoutStabilityWithToolWizardTest.class);
        suite.addTestSuite(EdgeStabilityOnCopyPasteLayoutTest.class);
        suite.addTestSuite(PortSelectionTest.class);
        suite.addTestSuite(PortNotVisibleSelectionTest.class);
        suite.addTestSuite(RefreshAfterUndoDeletionFromGenericToolTest.class);
        suite.addTestSuite(RefreshAfterViewCreationTest.class);
        suite.addTestSuite(RefreshWithCustomizedStyleFromAppearanceTabTests.class);
        suite.addTestSuite(RefreshWithCustomizedStyleFromTabbarTests.class);
        suite.addTestSuite(ResizeKindRefreshTests.class);
        suite.addTestSuite(SpecificLayoutBendpointsOnReopeningTest.class);
        suite.addTestSuite(TreeFoldingTest.class);
        suite.addTestSuite(DoubleClickToolNavigationOperationTest.class);
        suite.addTestSuite(DoubleClickToolNavigationOperationTest.class);
        suite.addTestSuite(BackgroundColorFigureUpdateTests.class);
        suite.addTestSuite(BackgroundStyleUpdateTest.class);
        suite.addTestSuite(BorderSizeComputationExpressionTest.class);
        suite.addTestSuite(EdgeMappingTestCase.class);
        suite.addTestSuite(InvalidMetamodelRessourceTest.class);
        suite.addTestSuite(ExtensionActivationOrderTest.class);
        suite.addTestSuite(CompartmentsCreationTest.class);
        suite.addTestSuite(CompartmentsLayoutTest.class);
    }

    /**
     * Add the second part of the SWTbot tests to the specified suite. This
     * corresponds roughly to the second half of the execution time of the
     * complete suite.
     * 
     * @param suite
     *            the suite into which to add the tests.
     */
    public static void addPart2(TestSuite suite) {

        addGerritPart2(suite);
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

        suite.addTestSuite(DeleteHookTests.class);

        // TODO DLE : reenable theses tests as soon as getEditPart method will
        // be based on matcher.
        // suite.addTestSuite(ToolWizardTest.class);
        suite.addTestSuite(ExportDiagramsAsImagesTest.class);
        suite.addTestSuite(ExportDiagramsAsImagesAndHtmlTest.class);
        suite.addTestSuite(TabBarTest.class);
        suite.addTestSuite(NotInvisibleTabBarTest.class);
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
        suite.addTestSuite(DragAndDropFromControlledResourceTest.class);
        suite.addTestSuite(RoundedCornerRefreshTest.class);
        suite.addTestSuite(ModelExplorerFilterTests.class);
        suite.addTestSuite(ProjectDependenciesTest.class);
        suite.addTestSuite(ProjectsConcurrentCloseTest.class);
        suite.addTestSuite(ContextualMenuTest.class);
        suite.addTestSuite(BracketEdgeTests.class);
        suite.addTestSuite(EdgeCopyPasteLayoutTest.class);
        suite.addTestSuite(BendpointsStabilityOnMovesTest.class);
        suite.addTestSuite(BendpointsStabilityOnMovesSpecificCasesTest.class);
        suite.addTestSuite(BorderedNodeCopyPastLayoutTest.class);
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

        // Scenario test cases
        suite.addTestSuite(ESEDemoTest.class);
        suite.addTestSuite(ValidationTest.class);
        suite.addTestSuite(ViewpointSelectionDialogTest.class);
        suite.addTestSuite(InitializeEmptySessionTest.class);
        suite.addTestSuite(InitializeSessionTest.class);
        suite.addTestSuite(RepairTest.class);
        suite.addTestSuite(ValidationEmptyNameTest.class);
        suite.addTestSuite(ControlUncontrolWithOpenedRepresentationTest.class);
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the
     * disabled test.
     * 
     * @return The test suite containing all the disabled tests.
     */
    public static Test disabledSuite() {
        final TestSuite suite = new TestSuite("Sirius Disabled SwtBot tests");

        // TODO MCH : Fix this test. This test is deactivate because the build
        // fails on Hudson (Timeout Exception)
        suite.addTestSuite(DndWorkspaceSupportTest.class);

        // TODO CBR : fix these tests
        suite.addTestSuite(ArrangeAllTest.class);

        // This tests are not enabled because the method util.dragAndDrop() is
        // unreliable. Sometimes the drop works and sometimes the drop fails.
        suite.addTestSuite(DragNDropTest.class);

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

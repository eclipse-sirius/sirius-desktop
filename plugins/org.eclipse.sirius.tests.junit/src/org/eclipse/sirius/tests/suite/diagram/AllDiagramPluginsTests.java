/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.suite.diagram;

import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.tests.suite.diagram.sequence.AllSequenceDiagramsPluginTests;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.api.command.CreateDiagramElementCommandTests;
import org.eclipse.sirius.tests.unit.api.command.CreationAndDeletionUndoRedoTests;
import org.eclipse.sirius.tests.unit.api.componentization.DiagramCustomizationTest;
import org.eclipse.sirius.tests.unit.api.diagramintegrity.AddSemanticElementTest;
import org.eclipse.sirius.tests.unit.api.diagramintegrity.DeleteSemanticElementTest;
import org.eclipse.sirius.tests.unit.api.diagramintegrity.ModifySemanticElementTest;
import org.eclipse.sirius.tests.unit.api.diagramintegrity.MoveSemanticElementTest;
import org.eclipse.sirius.tests.unit.api.diagramintegrity.RetrieveEditPartFromSemanticElementTests;
import org.eclipse.sirius.tests.unit.api.dialect.ExportAsImageTest;
import org.eclipse.sirius.tests.unit.api.initialization.InitOperationForDiagramsTests;
import org.eclipse.sirius.tests.unit.api.layers.MultipleMappingImportTests;
import org.eclipse.sirius.tests.unit.api.layers.MultipleMappingImportTests2;
import org.eclipse.sirius.tests.unit.api.layers.OptionalLayersActivationTests;
import org.eclipse.sirius.tests.unit.api.mappings.CompartmentMappingsTests;
import org.eclipse.sirius.tests.unit.api.mappings.ContainerMappingImportTests;
import org.eclipse.sirius.tests.unit.api.mappings.ContainerMappingImportWithChildrenPresentationChangesTests;
import org.eclipse.sirius.tests.unit.api.mappings.EdgeMappingTest;
import org.eclipse.sirius.tests.unit.api.mappings.MappingImportAndFiltersTests;
import org.eclipse.sirius.tests.unit.api.mappings.MappingsReuseTests;
import org.eclipse.sirius.tests.unit.api.mappings.NodeMappingImportTests;
import org.eclipse.sirius.tests.unit.api.mappings.NodeMappingTest;
import org.eclipse.sirius.tests.unit.api.mm.MMTest;
import org.eclipse.sirius.tests.unit.api.mmextension.ExtensionPaletteToolTest;
import org.eclipse.sirius.tests.unit.api.refresh.ChangeIdAndLabelTests;
import org.eclipse.sirius.tests.unit.api.refresh.DefaultColorsTest;
import org.eclipse.sirius.tests.unit.api.refresh.EdgeWithConditionalStyleTest;
import org.eclipse.sirius.tests.unit.api.refresh.IconRefreshTests;
import org.eclipse.sirius.tests.unit.api.refresh.RefreshInUIThreadTests;
import org.eclipse.sirius.tests.unit.api.refresh.RefreshOnDeletionInAutoRefreshTests;
import org.eclipse.sirius.tests.unit.api.refresh.RefreshOnDeletionInManualRefreshTests;
import org.eclipse.sirius.tests.unit.api.refresh.RefreshStabilityTests;
import org.eclipse.sirius.tests.unit.api.refresh.RefreshWithCustomizedStyleTests;
import org.eclipse.sirius.tests.unit.api.refresh.StyleCustomizationAndConditionalStyleDescriptionTest;
import org.eclipse.sirius.tests.unit.api.refresh.StyleRefreshTests;
import org.eclipse.sirius.tests.unit.api.refresh.StyleRefreshWithLayerTests;
import org.eclipse.sirius.tests.unit.api.semantic.DAnalysisModelsUpdateTests;
import org.eclipse.sirius.tests.unit.api.session.SessionWorkspaceSyncTests;
import org.eclipse.sirius.tests.unit.api.session.TablesAndEntitiesDirtyTest;
import org.eclipse.sirius.tests.unit.api.startup.StartupRepresentationsTests;
import org.eclipse.sirius.tests.unit.api.tools.CreateEdgeOnEdgeTest;
import org.eclipse.sirius.tests.unit.api.tools.CreateEdgeTest;
import org.eclipse.sirius.tests.unit.api.tools.CreateEdgeViewTest;
import org.eclipse.sirius.tests.unit.api.tools.CreateNodeViewTest;
import org.eclipse.sirius.tests.unit.api.tools.DeleteTest;
import org.eclipse.sirius.tests.unit.api.tools.DirectEditTest;
import org.eclipse.sirius.tests.unit.api.tools.DragAndDropForEdgesonEdgesFromDiagramTest;
import org.eclipse.sirius.tests.unit.api.tools.DragAndDropTest;
import org.eclipse.sirius.tests.unit.api.tools.DragAndDropWithImportImportTest;
import org.eclipse.sirius.tests.unit.api.tools.EditEdgeOnEdgeTest;
import org.eclipse.sirius.tests.unit.api.tools.ExternalJavaActionTest;
import org.eclipse.sirius.tests.unit.api.tools.ForToolTests;
import org.eclipse.sirius.tests.unit.api.tools.GenericToolTest;
import org.eclipse.sirius.tests.unit.api.tools.ReconnectionEdgeOnEdgeTest;
import org.eclipse.sirius.tests.unit.api.tools.ReconnectionTest;
import org.eclipse.sirius.tests.unit.api.tools.ReusedMappingTest;
import org.eclipse.sirius.tests.unit.api.tools.SelectionWizardTest;
import org.eclipse.sirius.tests.unit.api.tools.SetValueEEnumAttributeTypeTest;
import org.eclipse.sirius.tests.unit.api.tools.SiriusControlAndCrossReferenceTest;
import org.eclipse.sirius.tests.unit.api.tools.SiriusControlAndDeleteRepresentationTest;
import org.eclipse.sirius.tests.unit.api.tools.SiriusControlTest;
import org.eclipse.sirius.tests.unit.api.tools.ToolFilterDescriptionListenerTests;
import org.eclipse.sirius.tests.unit.api.tools.UndoRedoTest;
import org.eclipse.sirius.tests.unit.api.tools.tasks.RemoveDanglingReferencesTestCase;
import org.eclipse.sirius.tests.unit.api.validation.DiagramValidationTest;
import org.eclipse.sirius.tests.unit.api.vsm.editor.LocationURITest;
import org.eclipse.sirius.tests.unit.api.vsm.editor.PopupMenuTest;
import org.eclipse.sirius.tests.unit.api.vsm.editor.TypeContentProposalTests;
import org.eclipse.sirius.tests.unit.common.DDiagramCanonicalSynchronizerTests;
import org.eclipse.sirius.tests.unit.common.DRepresentationDescriptorChangeIdTests;
import org.eclipse.sirius.tests.unit.common.SiriusUriTests;
import org.eclipse.sirius.tests.unit.common.TypeNameTest;
import org.eclipse.sirius.tests.unit.diagram.CanonicalDBorderItemLocatorTest;
import org.eclipse.sirius.tests.unit.diagram.action.CopyFormatActionTests;
import org.eclipse.sirius.tests.unit.diagram.action.DeleteEdgeOnEdgeTest;
import org.eclipse.sirius.tests.unit.diagram.action.DeleteFromDiagramActionTests;
import org.eclipse.sirius.tests.unit.diagram.action.DeleteFromModelActionTests;
import org.eclipse.sirius.tests.unit.diagram.action.DeleteWithoutToolFromModelActionTests;
import org.eclipse.sirius.tests.unit.diagram.action.EdgeOnEdgeHideRevealTest;
import org.eclipse.sirius.tests.unit.diagram.action.EntitiesDiagramHideRevealTests;
import org.eclipse.sirius.tests.unit.diagram.action.LabelHideRevealTests;
import org.eclipse.sirius.tests.unit.diagram.command.BuildDeleteCommandTest;
import org.eclipse.sirius.tests.unit.diagram.command.DeleteViewCommandTest;
import org.eclipse.sirius.tests.unit.diagram.compartment.CompartmentsLayoutTest;
import org.eclipse.sirius.tests.unit.diagram.compartment.CompartmentsMultiLabelLayoutTest;
import org.eclipse.sirius.tests.unit.diagram.compartment.CompartmentsTest;
import org.eclipse.sirius.tests.unit.diagram.compute.variable.ComputeAvailableVariableLabelTest;
import org.eclipse.sirius.tests.unit.diagram.control.ControlDetectorTest;
import org.eclipse.sirius.tests.unit.diagram.control.ControlTest;
import org.eclipse.sirius.tests.unit.diagram.control.ExternalControlTests;
import org.eclipse.sirius.tests.unit.diagram.control.HierarchicalControlTest;
import org.eclipse.sirius.tests.unit.diagram.control.HierarchicalControlWithRootElementReadOnlyTest;
import org.eclipse.sirius.tests.unit.diagram.copier.RepresentationCopierTest;
import org.eclipse.sirius.tests.unit.diagram.decorators.DecorationRefreshTest;
import org.eclipse.sirius.tests.unit.diagram.decorators.DecoratorsTest;
import org.eclipse.sirius.tests.unit.diagram.decorators.TransientLayerAndDecoratorTest;
import org.eclipse.sirius.tests.unit.diagram.dialect.DiagramDialectServicesTests;
import org.eclipse.sirius.tests.unit.diagram.dialect.DiagramUIDialectServicesTests;
import org.eclipse.sirius.tests.unit.diagram.dragndrop.DraggedObjectTesterTests;
import org.eclipse.sirius.tests.unit.diagram.dragndrop.LabelVisibilityOnCreationTest;
import org.eclipse.sirius.tests.unit.diagram.dragndrop.LabelVisibilityOnDragAndDropTests;
import org.eclipse.sirius.tests.unit.diagram.edge.AttachmentBetweenEdgeAndItsLabelsTest;
import org.eclipse.sirius.tests.unit.diagram.filter.CollapseExpandFilterTest;
import org.eclipse.sirius.tests.unit.diagram.filter.CollapseFilterTest;
import org.eclipse.sirius.tests.unit.diagram.filter.CompositeFilterTest;
import org.eclipse.sirius.tests.unit.diagram.filter.DiagramElementSelectionDialogPatternMatcherTest;
import org.eclipse.sirius.tests.unit.diagram.filter.DiagramElementSelectionDialogTest;
import org.eclipse.sirius.tests.unit.diagram.filter.DiagramListenersTests;
import org.eclipse.sirius.tests.unit.diagram.filter.FilterEdgeOnEdgeTest;
import org.eclipse.sirius.tests.unit.diagram.filter.MinimizedTransparentCollapsingTest;
import org.eclipse.sirius.tests.unit.diagram.filter.VariableFilterTest;
import org.eclipse.sirius.tests.unit.diagram.folding.ContainerFoldingTest;
import org.eclipse.sirius.tests.unit.diagram.folding.FoldingPointsIdentificationTest;
import org.eclipse.sirius.tests.unit.diagram.folding.FoldingQueriesTest;
import org.eclipse.sirius.tests.unit.diagram.folding.NodeFoldingTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.EdgeStabilityOnPortCollapsingTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.FormatHelperImplEdgeFormatDataTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.FormatHelperImplNodeFormatData1Test;
import org.eclipse.sirius.tests.unit.diagram.format.data.FormatHelperImplNodeFormatData2Test;
import org.eclipse.sirius.tests.unit.diagram.format.data.LabelPositionOnContainerAndListTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerCallCheckSequenceTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerCallCheckTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerCreateTargetDiagramDiffSessionTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerCreateTargetDiagramTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerCreateTargetSequenceDiagramDiffSessionTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerCreateTargetSequenceDiagramTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerExistingTargetDiagramDiffSessionTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerExistingTargetDiagramTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerExistingTargetSequenceDiagramTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerExternalRefTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.SiriusFormatDataManagerForDDiagramElementWithSameSemanticElementsTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.SiriusFormatDataManagerForSemanticElementsApplyWithPredefinedDataTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.SiriusFormatDataManagerForSemanticElementsStoreWithPredefinedDataTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.SiriusFormatDataManagerForSemanticElementsTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.manager.extension.FormatDataManagerSelectionTest;
import org.eclipse.sirius.tests.unit.diagram.layers.ActivateDeactivateOptionalLayersTest;
import org.eclipse.sirius.tests.unit.diagram.layers.ActivateLayerOnInvalidDiagramTest;
import org.eclipse.sirius.tests.unit.diagram.layers.LayerEdgeOnEdgeTest;
import org.eclipse.sirius.tests.unit.diagram.layers.Layers1203Tests;
import org.eclipse.sirius.tests.unit.diagram.layers.Layers1302Tests;
import org.eclipse.sirius.tests.unit.diagram.layers.Layers1796Tests;
import org.eclipse.sirius.tests.unit.diagram.layers.Layers1887Tests;
import org.eclipse.sirius.tests.unit.diagram.layers.LayersTest;
import org.eclipse.sirius.tests.unit.diagram.layers.MappingsIterationTests;
import org.eclipse.sirius.tests.unit.diagram.layers.MappingsTableTests;
import org.eclipse.sirius.tests.unit.diagram.layout.SimpleELKLayoutTest;
import org.eclipse.sirius.tests.unit.diagram.layout.margin.BorderMarginTest;
import org.eclipse.sirius.tests.unit.diagram.layout.pinning.PinnedElementsTest;
import org.eclipse.sirius.tests.unit.diagram.layoutingmode.LayoutingModeOnCustomModelerTest;
import org.eclipse.sirius.tests.unit.diagram.layoutingmode.LayoutingModeOnECoreModelerTest;
import org.eclipse.sirius.tests.unit.diagram.mapping.EdgeMappingCreationDescriptionTests;
import org.eclipse.sirius.tests.unit.diagram.migration.ActivatedFilterSortingMigrationParticipantTest;
import org.eclipse.sirius.tests.unit.diagram.migration.JumpLinkNewTypeMigrationTest;
import org.eclipse.sirius.tests.unit.diagram.migration.LabelOnBorderMigrationTests6_3_0;
import org.eclipse.sirius.tests.unit.diagram.migration.LabelOnBorderMigrationTestsBefore6_3_0;
import org.eclipse.sirius.tests.unit.diagram.modelaccessor.ModelAccessorTest;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design.EntitiesDiagramBackgroundTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design.EntitiesDiagramContainerList;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design.EntitiesDiagramDeleteFromDiagramTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design.EntitiesDiagramDerivedReferenceTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design.EntitiesDiagramDirectEditToolOnOperationTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design.EntitiesDiagramDropTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design.EntitiesDiagramFiltersTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design.EntitiesDiagramModificationOutsideEditorTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design.EntitiesDiagramSiriussConflictTest;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design.EntitiesDiagramStyleRefreshTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design.EntitiesDiagramToolsTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design.EntitiesDiagramTooltipsRefreshTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design.EntitiesDiagramTooltipsTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design.EntitiesDiagramZoomTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.documentation.EntitiesDiagramDocumentationFiltersTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.documentation.EntitiesDiagramDocumentationToolsTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.quality.DependenciesDiagramLayersTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.quality.DependenciesDiagramRefreshTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.quality.EntitiesDiagramCustomizationsTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.quality.HierarchyDiagramLayersTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.quality.HierarchyDiagramRefreshTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.quality.RelationsDiagramLayersTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.quality.RelationsDiagramRefreshTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.uml.HideAndDeleteUndoTest;
import org.eclipse.sirius.tests.unit.diagram.modeler.uml.LayerTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.uml.PortLocationTest;
import org.eclipse.sirius.tests.unit.diagram.modelers.dynamicinstance.DynamicInstanceTests;
import org.eclipse.sirius.tests.unit.diagram.modelers.ecore.EntitiesDiagramStyleCustomizationTests;
import org.eclipse.sirius.tests.unit.diagram.navigation.NavigationOperationTest;
import org.eclipse.sirius.tests.unit.diagram.navigation.OpenMenuTest;
import org.eclipse.sirius.tests.unit.diagram.navigation.OpenRepresentationTest;
import org.eclipse.sirius.tests.unit.diagram.node.style.ModifyNodeStyleTest;
import org.eclipse.sirius.tests.unit.diagram.operations.CreateViewOperationTest;
import org.eclipse.sirius.tests.unit.diagram.operations.OperationTest;
import org.eclipse.sirius.tests.unit.diagram.properties.ExtendedPropertySourceTestCase;
import org.eclipse.sirius.tests.unit.diagram.refresh.CancelCommandsRefreshTests;
import org.eclipse.sirius.tests.unit.diagram.refresh.ConditionalStyleRefreshTest;
import org.eclipse.sirius.tests.unit.diagram.refresh.ConstantStyleRefreshTest;
import org.eclipse.sirius.tests.unit.diagram.refresh.DNodeListChildrenReoderingOnChildDirectEditTests;
import org.eclipse.sirius.tests.unit.diagram.refresh.DanglingReferencesTest;
import org.eclipse.sirius.tests.unit.diagram.refresh.DiagramElementMappingHelperTest;
import org.eclipse.sirius.tests.unit.diagram.refresh.DiagramSynchronizerTest;
import org.eclipse.sirius.tests.unit.diagram.refresh.ForceRefreshToolTest;
import org.eclipse.sirius.tests.unit.diagram.refresh.LabelExpressionOnEdgeCreationTest;
import org.eclipse.sirius.tests.unit.diagram.refresh.MultiMappingImportChainsWithSameSemanticTest;
import org.eclipse.sirius.tests.unit.diagram.refresh.RefreshToolTipTest;
import org.eclipse.sirius.tests.unit.diagram.refresh.StyleSizeChangeRefreshTest;
import org.eclipse.sirius.tests.unit.diagram.refresh.customization.RefreshWithCustomizationTests;
import org.eclipse.sirius.tests.unit.diagram.resource.FileModificationValidationTest;
import org.eclipse.sirius.tests.unit.diagram.resource.GMFCrossReferenceAdapterTest;
import org.eclipse.sirius.tests.unit.diagram.services.ServiceCallsWithOperationCanceledExceptionTest;
import org.eclipse.sirius.tests.unit.diagram.session.MultiAirdResourcesSessionTest;
import org.eclipse.sirius.tests.unit.diagram.session.SessionServiceGMFDiagramTest;
import org.eclipse.sirius.tests.unit.diagram.session.SessionTest;
import org.eclipse.sirius.tests.unit.diagram.style.BorderSizeAndColorTest;
import org.eclipse.sirius.tests.unit.diagram.style.ColorManagerTest;
import org.eclipse.sirius.tests.unit.diagram.style.ComputedColorTest;
import org.eclipse.sirius.tests.unit.diagram.style.EdgeSizeComputationVariableTest;
import org.eclipse.sirius.tests.unit.diagram.style.EdgeSizeTest;
import org.eclipse.sirius.tests.unit.diagram.style.InterpolatedColorTest;
import org.eclipse.sirius.tests.unit.diagram.style.LabelColorOnListElementTest;
import org.eclipse.sirius.tests.unit.diagram.style.LabelColorTest;
import org.eclipse.sirius.tests.unit.diagram.style.MappingImportChainsTest;
import org.eclipse.sirius.tests.unit.diagram.style.NodeSizeOnDiagramCreationTest;
import org.eclipse.sirius.tests.unit.diagram.style.NodeSizeTest;
import org.eclipse.sirius.tests.unit.diagram.style.WorkspaceImageTest;
import org.eclipse.sirius.tests.unit.diagram.synchronization.DiagramSynchronizationTest;
import org.eclipse.sirius.tests.unit.diagram.synchronization.DiagramUnsynchronizedRefreshTest;
import org.eclipse.sirius.tests.unit.diagram.synchronization.SynchronizationLockTest;
import org.eclipse.sirius.tests.unit.diagram.synchronization.UnsynchronizedMappingAndDeleteFromOutsideEditorTests;
import org.eclipse.sirius.tests.unit.diagram.tabbar.TabbarActionSelectionListenerTest;
import org.eclipse.sirius.tests.unit.diagram.tools.CreateViewWithMappingImportTests;
import org.eclipse.sirius.tests.unit.diagram.tools.CreationTest;
import org.eclipse.sirius.tests.unit.diagram.tools.DoubleClicCommandBuilderForceRefreshTest;
import org.eclipse.sirius.tests.unit.diagram.tools.EdgeCreationToolWithExtraMappingTest;
import org.eclipse.sirius.tests.unit.diagram.tools.FindLabelToolTest;
import org.eclipse.sirius.tests.unit.diagram.tools.GMFbugTest;
import org.eclipse.sirius.tests.unit.diagram.tools.NoteBehaviorOnHideRevealDeleteElementTest;
import org.eclipse.sirius.tests.unit.diagram.tools.NoteBehaviorOnHideRevealDeleteManyElementsTest;
import org.eclipse.sirius.tests.unit.diagram.tools.PartAndLabelPartSelectionDeletionTest;
import org.eclipse.sirius.tests.unit.diagram.tools.SelectionAfterToolExecutionTest;
import org.eclipse.sirius.tests.unit.diagram.tools.ToolsApplicabilityTest;
import org.eclipse.sirius.tests.unit.diagram.tools.UndoAfterInconsistentEdgeCreationViewTest;
import org.eclipse.sirius.tests.unit.diagram.tools.palette.ModifySessionOutsideEclipseTest;
import org.eclipse.sirius.tests.unit.diagram.tools.palette.PaletteManagerWithFiltersTest;
import org.eclipse.sirius.tests.unit.diagram.tools.palette.PaletteManagerWithLayersTest;
import org.eclipse.sirius.tests.unit.diagram.tools.palette.PaletteManagerWithLayersWithExtensionTest;
import org.eclipse.sirius.tests.unit.diagram.tools.palette.PaletteManagerWithoutLayerTest;
import org.eclipse.sirius.tests.unit.diagram.tools.palette.PaletteReloadAfterVSMChangeTest;
import org.eclipse.sirius.tests.unit.diagram.tools.palette.PaletteUpdateDoesNotDeleteAllToolsTest;
import org.eclipse.sirius.tests.unit.diagram.tools.palette.PaletteUpdateWithoutInvalidThreadAccessTest;
import org.eclipse.sirius.tests.unit.diagram.views.session.ModelContentTest;
import org.eclipse.sirius.tests.unit.diagram.vsm.VSMValidationTest;
import org.eclipse.sirius.tests.unit.diagram.vsm.VSMVariableTypesValidationTest;
import org.eclipse.sirius.tests.unit.diagram.vsm.VSMWithCustomizationValidationTests;
import org.eclipse.sirius.tests.unit.perf.diagram.refresh.connections.DCompartmentConnectionRefreshMgrTest;
import org.eclipse.sirius.tests.unit.table.unit.migration.InitializeElementsToSelectExpressionForTableMigrationTest;
import org.eclipse.sirius.tests.unit.table.unit.tools.SelectionInTableAfterToolExecutionTest;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class AllDiagramPluginsTests {

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
     * Add the gerrit part of the Junit tests to the specified suite.
     * 
     * @param suite
     *            the suite into which to add the tests.
     */
    public static void addGerritPart(TestSuite suite) {
        suite.addTestSuite(InterpolatedColorTest.class);
        suite.addTestSuite(ComputedColorTest.class);
        suite.addTestSuite(LabelColorTest.class);
        suite.addTestSuite(LabelColorOnListElementTest.class);
        suite.addTestSuite(ColorManagerTest.class);
        suite.addTestSuite(CreateDiagramElementCommandTests.class);
        suite.addTestSuite(DRepresentationDescriptorChangeIdTests.class);
        suite.addTestSuite(CreationAndDeletionUndoRedoTests.class);
        suite.addTestSuite(DeleteSemanticElementTest.class);
        suite.addTestSuite(AddSemanticElementTest.class);
        suite.addTestSuite(ModifySemanticElementTest.class);
        suite.addTestSuite(MoveSemanticElementTest.class);
        suite.addTestSuite(DiagramValidationTest.class);
        suite.addTestSuite(RetrieveEditPartFromSemanticElementTests.class);
        suite.addTestSuite(DynamicInstanceTests.class);
        suite.addTestSuite(CanonicalDBorderItemLocatorTest.class);
        suite.addTestSuite(DiagramDialectServicesTests.class);
        suite.addTestSuite(DiagramUIDialectServicesTests.class);
        suite.addTestSuite(TypeContentProposalTests.class);
        suite.addTestSuite(TypeNameTest.class);

        // ecore modeler
        suite.addTestSuite(EntitiesDiagramDocumentationFiltersTests.class);
        suite.addTestSuite(EntitiesDiagramDocumentationToolsTests.class);
        suite.addTestSuite(MultiAirdResourcesSessionTest.class);

        suite.addTestSuite(EntitiesDiagramModificationOutsideEditorTests.class);
        // suite.addTestSuite(JavaExtensionTests.class);
        suite.addTestSuite(org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design.EntitiesDiagramRefreshTests.class);
        suite.addTestSuite(org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design.EntitiesDiagramLayersTests.class);
        suite.addTestSuite(EntitiesDiagramFiltersTests.class);
        suite.addTestSuite(EntitiesDiagramToolsTests.class);
        suite.addTestSuite(EntitiesDiagramTooltipsTests.class);
        suite.addTestSuite(EntitiesDiagramTooltipsRefreshTests.class);
        suite.addTestSuite(EntitiesDiagramDropTests.class);
        suite.addTestSuite(EntitiesDiagramZoomTests.class);
        suite.addTestSuite(InitOperationForDiagramsTests.class);
        suite.addTestSuite(DependenciesDiagramRefreshTests.class);
        suite.addTestSuite(DependenciesDiagramLayersTests.class);
        suite.addTestSuite(HierarchyDiagramRefreshTests.class);
        suite.addTestSuite(HierarchyDiagramLayersTests.class);
        suite.addTestSuite(RelationsDiagramRefreshTests.class);
        suite.addTestSuite(RelationsDiagramLayersTests.class);
        suite.addTestSuite(org.eclipse.sirius.tests.unit.diagram.modeler.ecore.documentation.EntitiesDiagramRefreshTests.class);
        suite.addTestSuite(org.eclipse.sirius.tests.unit.diagram.modeler.ecore.documentation.EntitiesDiagramLayersTests.class);
        suite.addTestSuite(StartupRepresentationsTests.class);
        suite.addTestSuite(SessionWorkspaceSyncTests.class);
        suite.addTestSuite(EntitiesDiagramDeleteFromDiagramTests.class);
        suite.addTestSuite(EntitiesDiagramCustomizationsTests.class);
        suite.addTestSuite(EntitiesDiagramDirectEditToolOnOperationTests.class);
        suite.addTestSuite(EntitiesDiagramContainerList.class);
        suite.addTestSuite(EntitiesDiagramStyleRefreshTests.class);
        suite.addTestSuite(EntitiesDiagramDerivedReferenceTests.class);
        suite.addTestSuite(EntitiesDiagramSiriussConflictTest.class);
        suite.addTestSuite(LayoutingModeOnECoreModelerTest.class);
        suite.addTestSuite(LayoutingModeOnCustomModelerTest.class);
        suite.addTestSuite(EntitiesDiagramBackgroundTests.class);

        // end of ecore modeler
        suite.addTestSuite(LayersTest.class);
        suite.addTestSuite(Layers1203Tests.class);
        suite.addTestSuite(Layers1302Tests.class);
        suite.addTestSuite(Layers1796Tests.class);
        suite.addTestSuite(Layers1887Tests.class);

        suite.addTestSuite(MappingsIterationTests.class);
        suite.addTestSuite(MappingsTableTests.class);
        suite.addTestSuite(EdgeMappingCreationDescriptionTests.class);
        suite.addTestSuite(ExtendedPropertySourceTestCase.class);
        suite.addTestSuite(ModelAccessorTest.class);
        suite.addTestSuite(OperationTest.class);

        suite.addTestSuite(CreateViewOperationTest.class);
        suite.addTestSuite(ExportAsImageTest.class);

        suite.addTestSuite(OpenRepresentationTest.class);
        suite.addTestSuite(OpenMenuTest.class);
        suite.addTestSuite(RepresentationCopierTest.class);
        // suite.addTestSuite(ExtensionAccessTest.class);
        suite.addTestSuite(DecoratorsTest.class);
        suite.addTestSuite(DecorationRefreshTest.class);
        suite.addTestSuite(TransientLayerAndDecoratorTest.class);
        suite.addTestSuite(DeleteFromModelActionTests.class);
        suite.addTestSuite(DeleteWithoutToolFromModelActionTests.class);
        suite.addTestSuite(DeleteFromDiagramActionTests.class);
        suite.addTestSuite(ControlTest.class);
        suite.addTestSuite(ControlDetectorTest.class);
        suite.addTestSuite(ExternalControlTests.class);
        suite.addTestSuite(EntitiesDiagramHideRevealTests.class);
        suite.addTestSuite(LabelHideRevealTests.class);
        suite.addTestSuite(DiagramElementSelectionDialogTest.class);
        suite.addTestSuite(DiagramElementSelectionDialogPatternMatcherTest.class);
        suite.addTestSuite(PinnedElementsTest.class);
        suite.addTestSuite(SimpleELKLayoutTest.class);
        suite.addTestSuite(CopyFormatActionTests.class);

        suite.addTestSuite(DDiagramCanonicalSynchronizerTests.class);
        suite.addTestSuite(DiagramSynchronizationTest.class);
        suite.addTestSuite(DiagramUnsynchronizedRefreshTest.class);
        suite.addTestSuite(UnsynchronizedMappingAndDeleteFromOutsideEditorTests.class);
        suite.addTestSuite(DeleteViewCommandTest.class);
        suite.addTestSuite(BuildDeleteCommandTest.class);
        suite.addTestSuite(FileModificationValidationTest.class);
        suite.addTestSuite(GMFCrossReferenceAdapterTest.class);
        suite.addTestSuite(EdgeStabilityOnPortCollapsingTest.class);
        suite.addTestSuite(MinimizedTransparentCollapsingTest.class);
        suite.addTestSuite(CompositeFilterTest.class);
        suite.addTestSuite(VariableFilterTest.class);
        suite.addTestSuite(WorkspaceImageTest.class);
        suite.addTestSuite(SynchronizationLockTest.class);
        suite.addTestSuite(NodeSizeTest.class);
        suite.addTestSuite(NodeSizeOnDiagramCreationTest.class);
        suite.addTestSuite(EdgeSizeTest.class);
        suite.addTestSuite(EdgeSizeComputationVariableTest.class);
        suite.addTestSuite(AttachmentBetweenEdgeAndItsLabelsTest.class);
        suite.addTestSuite(CollapseExpandFilterTest.class);
        suite.addTestSuite(CollapseFilterTest.class);
        // Folding
        suite.addTestSuite(FoldingPointsIdentificationTest.class);
        suite.addTestSuite(FoldingQueriesTest.class);
        suite.addTestSuite(ContainerFoldingTest.class);
        suite.addTestSuite(NodeFoldingTest.class);

        // Refresh
        suite.addTestSuite(CancelCommandsRefreshTests.class);
        suite.addTestSuite(ConditionalStyleRefreshTest.class);
        suite.addTestSuite(ConstantStyleRefreshTest.class);
        suite.addTestSuite(DanglingReferencesTest.class);
        suite.addTestSuite(DiagramSynchronizerTest.class);
        suite.addTestSuite(DiagramElementMappingHelperTest.class);
        suite.addTestSuite(DNodeListChildrenReoderingOnChildDirectEditTests.class);
        suite.addTestSuite(ForceRefreshToolTest.class);
        suite.addTestSuite(LabelExpressionOnEdgeCreationTest.class);
        suite.addTestSuite(RefreshToolTipTest.class);
        suite.addTestSuite(DAnalysisModelsUpdateTests.class);
        suite.addTestSuite(StyleSizeChangeRefreshTest.class);
        suite.addTestSuite(ConditionalStyleRefreshTest.class);
        suite.addTestSuite(RefreshWithCustomizationTests.class);
        suite.addTestSuite(RefreshStabilityTests.class);

        suite.addTestSuite(SessionTest.class);
        suite.addTestSuite(SessionServiceGMFDiagramTest.class);
        // tools
        suite.addTestSuite(ToolsApplicabilityTest.class);
        suite.addTestSuite(EdgeCreationToolWithExtraMappingTest.class);
        suite.addTestSuite(FindLabelToolTest.class);
        suite.addTestSuite(UndoAfterInconsistentEdgeCreationViewTest.class);
        suite.addTestSuite(CreationTest.class);
        suite.addTestSuite(CreateViewWithMappingImportTests.class);
        suite.addTestSuite(NoteBehaviorOnHideRevealDeleteElementTest.class);
        suite.addTestSuite(NoteBehaviorOnHideRevealDeleteManyElementsTest.class);

        suite.addTestSuite(FormatHelperImplEdgeFormatDataTest.class);
        suite.addTestSuite(FormatHelperImplNodeFormatData1Test.class);
        suite.addTestSuite(FormatHelperImplNodeFormatData2Test.class);

        suite.addTestSuite(SiriusFormatDataManagerForDDiagramElementWithSameSemanticElementsTest.class);
        suite.addTestSuite(FormatDataManagerSelectionTest.class);
        suite.addTestSuite(RefreshOnDeletionInManualRefreshTests.class);
        suite.addTest(new JUnit4TestAdapter(MappingBasedSiriusFormatDataManagerCreateTargetDiagramTest.class));
        suite.addTest(new JUnit4TestAdapter(MappingBasedSiriusFormatDataManagerExistingTargetDiagramTest.class));
        suite.addTest(new JUnit4TestAdapter(MappingBasedSiriusFormatDataManagerCreateTargetSequenceDiagramTest.class));
        suite.addTest(new JUnit4TestAdapter(MappingBasedSiriusFormatDataManagerExistingTargetSequenceDiagramTest.class));
        suite.addTest(new JUnit4TestAdapter(MappingBasedSiriusFormatDataManagerExternalRefTest.class));
        suite.addTest(new JUnit4TestAdapter(MappingBasedSiriusFormatDataManagerCreateTargetDiagramDiffSessionTest.class));
        suite.addTest(new JUnit4TestAdapter(MappingBasedSiriusFormatDataManagerExistingTargetDiagramDiffSessionTest.class));
        suite.addTest(new JUnit4TestAdapter(MappingBasedSiriusFormatDataManagerCreateTargetSequenceDiagramDiffSessionTest.class));
        suite.addTest(new JUnit4TestAdapter(MappingBasedSiriusFormatDataManagerCallCheckTest.class));
        suite.addTest(new JUnit4TestAdapter(MappingBasedSiriusFormatDataManagerCallCheckSequenceTest.class));
        suite.addTestSuite(LabelPositionOnContainerAndListTest.class);
        suite.addTestSuite(LabelVisibilityOnDragAndDropTests.class);
        suite.addTestSuite(LabelVisibilityOnCreationTest.class);
        suite.addTestSuite(PaletteManagerWithLayersTest.class);
        suite.addTestSuite(PaletteManagerWithoutLayerTest.class);
        suite.addTestSuite(PaletteManagerWithLayersWithExtensionTest.class);
        suite.addTestSuite(PaletteManagerWithFiltersTest.class);
        suite.addTestSuite(PaletteUpdateDoesNotDeleteAllToolsTest.class);
        suite.addTestSuite(PaletteUpdateWithoutInvalidThreadAccessTest.class);
        suite.addTestSuite(PaletteReloadAfterVSMChangeTest.class);
        suite.addTestSuite(ModifySessionOutsideEclipseTest.class);

        suite.addTestSuite(ModelContentTest.class);
        suite.addTestSuite(BorderSizeAndColorTest.class);
        suite.addTestSuite(BorderMarginTest.class);
        suite.addTestSuite(MappingsReuseTests.class);
        suite.addTestSuite(MappingImportAndFiltersTests.class);
        suite.addTestSuite(OptionalLayersActivationTests.class);
        suite.addTestSuite(ActivateDeactivateOptionalLayersTest.class);
        suite.addTestSuite(ActivateLayerOnInvalidDiagramTest.class);
        suite.addTestSuite(MultipleMappingImportTests.class);
        suite.addTestSuite(MultipleMappingImportTests2.class);
        suite.addTestSuite(NodeMappingTest.class);
        suite.addTestSuite(EdgeMappingTest.class);
        suite.addTestSuite(ContainerMappingImportTests.class);
        suite.addTestSuite(CompartmentMappingsTests.class);
        suite.addTestSuite(CompartmentsTest.class);
        suite.addTestSuite(CompartmentsMultiLabelLayoutTest.class);
        suite.addTestSuite(CompartmentsLayoutTest.class);
        suite.addTestSuite(ContainerMappingImportWithChildrenPresentationChangesTests.class);
        suite.addTestSuite(NodeMappingImportTests.class);
        suite.addTestSuite(MappingImportChainsTest.class);
        suite.addTest(new JUnit4TestAdapter(MultiMappingImportChainsWithSameSemanticTest.class));

        suite.addTestSuite(EdgeWithConditionalStyleTest.class);
        suite.addTestSuite(DefaultColorsTest.class);
        suite.addTestSuite(RefreshInUIThreadTests.class);
        suite.addTestSuite(RefreshOnDeletionInAutoRefreshTests.class);
        suite.addTestSuite(RefreshWithCustomizedStyleTests.class);
        suite.addTestSuite(PartAndLabelPartSelectionDeletionTest.class);
        suite.addTestSuite(StyleCustomizationAndConditionalStyleDescriptionTest.class);
        suite.addTestSuite(StyleRefreshTests.class);
        suite.addTestSuite(StyleRefreshWithLayerTests.class);
        suite.addTestSuite(ChangeIdAndLabelTests.class);

        suite.addTestSuite(ReusedMappingTest.class);
        suite.addTestSuite(SelectionWizardTest.class);
        suite.addTestSuite(DeleteTest.class);
        suite.addTestSuite(DirectEditTest.class);
        suite.addTestSuite(DragAndDropTest.class);
        suite.addTestSuite(DraggedObjectTesterTests.class);
        suite.addTestSuite(DragAndDropWithImportImportTest.class);
        suite.addTestSuite(ReconnectionTest.class);
        suite.addTestSuite(UndoRedoTest.class);
        suite.addTestSuite(SiriusControlTest.class);
        suite.addTestSuite(ExternalJavaActionTest.class);
        suite.addTestSuite(CreateEdgeViewTest.class);
        suite.addTestSuite(SetValueEEnumAttributeTypeTest.class);
        suite.addTestSuite(ForToolTests.class);
        suite.addTestSuite(ExtensionPaletteToolTest.class);
        suite.addTestSuite(CreateEdgeTest.class);
        suite.addTestSuite(SiriusControlAndDeleteRepresentationTest.class);
        suite.addTestSuite(ToolFilterDescriptionListenerTests.class);
        suite.addTestSuite(CreateNodeViewTest.class);
        suite.addTestSuite(RemoveDanglingReferencesTestCase.class);
        suite.addTestSuite(ComputeAvailableVariableLabelTest.class);
        suite.addTestSuite(SiriusControlAndCrossReferenceTest.class);
        suite.addTestSuite(DoubleClicCommandBuilderForceRefreshTest.class);
        suite.addTestSuite(ServiceCallsWithOperationCanceledExceptionTest.class);

        // Specific diagram editor tests (tabbar, palette...)
        suite.addTestSuite(TabbarActionSelectionListenerTest.class);
        suite.addTestSuite(DiagramListenersTests.class);
        // uml

        suite.addTestSuite(LayerTests.class);
        suite.addTestSuite(PortLocationTest.class);
        suite.addTestSuite(HideAndDeleteUndoTest.class);
        suite.addTestSuite(HierarchicalControlTest.class);
        suite.addTestSuite(HierarchicalControlWithRootElementReadOnlyTest.class);
        suite.addTestSuite(IconRefreshTests.class);

        suite.addTestSuite(LocationURITest.class);
        suite.addTestSuite(PopupMenuTest.class);

        suite.addTestSuite(SiriusUriTests.class);
        suite.addTestSuite(EntitiesDiagramStyleCustomizationTests.class);

        suite.addTestSuite(GenericToolTest.class);
        suite.addTestSuite(DCompartmentConnectionRefreshMgrTest.class);
        suite.addTestSuite(GMFbugTest.class);
        suite.addTestSuite(ModifyNodeStyleTest.class);
        suite.addTestSuite(NavigationOperationTest.class);
        suite.addTestSuite(ComputedColorTest.class);
        suite.addTestSuite(SelectionAfterToolExecutionTest.class);
        suite.addTestSuite(SelectionInTableAfterToolExecutionTest.class);
        suite.addTestSuite(InitializeElementsToSelectExpressionForTableMigrationTest.class);
        suite.addTestSuite(ActivatedFilterSortingMigrationParticipantTest.class);
        suite.addTestSuite(JumpLinkNewTypeMigrationTest.class);
        suite.addTestSuite(LabelOnBorderMigrationTestsBefore6_3_0.class);
        suite.addTestSuite(LabelOnBorderMigrationTests6_3_0.class);

        // Edge on edge tests
        suite.addTestSuite(EdgeOnEdgeHideRevealTest.class);
        suite.addTestSuite(EditEdgeOnEdgeTest.class);
        suite.addTestSuite(FilterEdgeOnEdgeTest.class);
        suite.addTestSuite(LayerEdgeOnEdgeTest.class);
        suite.addTestSuite(ReconnectionEdgeOnEdgeTest.class);
        suite.addTestSuite(CreateEdgeOnEdgeTest.class);
        suite.addTestSuite(DeleteEdgeOnEdgeTest.class);
        suite.addTestSuite(DragAndDropForEdgesonEdgesFromDiagramTest.class);

        // VSM
        suite.addTestSuite(VSMValidationTest.class);
        suite.addTestSuite(VSMWithCustomizationValidationTests.class);
        suite.addTest(new JUnit4TestAdapter(VSMVariableTypesValidationTest.class));

        suite.addTestSuite(DiagramCustomizationTest.class);

        suite.addTestSuite(MMTest.class);

        suite.addTest(AllSequenceDiagramsPluginTests.suite());

        String platformVersion = Platform.getBundle("org.eclipse.core.runtime").getHeaders().get("Bundle-Version");
        if (!platformVersion.startsWith("3.5")) {
            suite.addTestSuite(SiriusFormatDataManagerForSemanticElementsTest.class);
            suite.addTestSuite(MappingBasedSiriusFormatDataManagerTest.class);
            suite.addTest(new JUnit4TestAdapter(SiriusFormatDataManagerForSemanticElementsStoreWithPredefinedDataTest.class));
        }
        suite.addTestSuite(TablesAndEntitiesDirtyTest.class);
    }

    /**
     * Add the tests which for one reason or another are not part of the suite launched on each Gerrit verification.
     * 
     * @param suite
     *            the suite to add the tests into.
     */
    public static void addNonGerritPart(TestSuite suite) {
        String platformVersion = Platform.getBundle("org.eclipse.core.runtime").getHeaders().get("Bundle-Version");
        if (!platformVersion.startsWith("3.5") && TestsUtil.shouldRunLongTests()) {
            // This one is long (~9 minutes), so it is ignored when running
            suite.addTest(new JUnit4TestAdapter(SiriusFormatDataManagerForSemanticElementsApplyWithPredefinedDataTest.class));
        }
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the test.
     * 
     * @return The testsuite containing all the tests
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite("Diagram Plugins Tests");
        addGerritPart(suite);
        addNonGerritPart(suite);
        return suite;
    }
}

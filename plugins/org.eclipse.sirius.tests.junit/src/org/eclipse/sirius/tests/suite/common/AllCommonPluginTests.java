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
package org.eclipse.sirius.tests.suite.common;

import org.eclipse.sirius.tests.unit.api.componentization.DiagramComponentizationManagerTest;
import org.eclipse.sirius.tests.unit.api.componentization.DiagramExtensionDescriptionTest;
import org.eclipse.sirius.tests.unit.api.componentization.MetamodelSpecificationInRepresentationExtensionDescriptionTest;
import org.eclipse.sirius.tests.unit.api.convert.ConvertProjectToModelingProjectTest;
import org.eclipse.sirius.tests.unit.api.convert.ConvertViewpointModelingProjectToSiriusModelingProjectTest;
import org.eclipse.sirius.tests.unit.api.dialect.DialectManagerTest;
import org.eclipse.sirius.tests.unit.api.dialect.DialectUIManagerTest;
import org.eclipse.sirius.tests.unit.api.editors.EditorNameAdapterTests;
import org.eclipse.sirius.tests.unit.api.editors.EditorVariousTests;
import org.eclipse.sirius.tests.unit.api.editors.EntitiesSpecificEditorTests;
import org.eclipse.sirius.tests.unit.api.editors.WorkspaceEPackageRegistryTests;
import org.eclipse.sirius.tests.unit.api.editors.traceability.GoToMarkerTests;
import org.eclipse.sirius.tests.unit.api.find.FindTest;
import org.eclipse.sirius.tests.unit.api.initialization.InitializationTest;
import org.eclipse.sirius.tests.unit.api.interpreter.CompletionTests;
import org.eclipse.sirius.tests.unit.api.interpreter.crossReferencer.AcceleoCrossReferencerTest;
import org.eclipse.sirius.tests.unit.api.modelingproject.SaveWhenNoEditorsTests;
import org.eclipse.sirius.tests.unit.api.modelingproject.SemanticResourcesManagementTests;
import org.eclipse.sirius.tests.unit.api.navigator.GroupingContentProviderByContainingTest;
import org.eclipse.sirius.tests.unit.api.navigator.GroupingContentProviderTest;
import org.eclipse.sirius.tests.unit.api.refresh.ModifyHeaderLabelExpressionTest;
import org.eclipse.sirius.tests.unit.api.resource.SemanticResourceURIInAirdTests;
import org.eclipse.sirius.tests.unit.api.resource.WorkspaceDragAndDropSupportTests;
import org.eclipse.sirius.tests.unit.api.routing.EdgeRoutingStyleEdgeConditionalStyleTest;
import org.eclipse.sirius.tests.unit.api.routing.EdgeRoutingStyleEndUserOverrideTest;
import org.eclipse.sirius.tests.unit.api.routing.EdgeRoutingStyleTests;
import org.eclipse.sirius.tests.unit.api.semantic.XSDSemanticResourceTests;
import org.eclipse.sirius.tests.unit.api.session.DAnalysisSessionTests;
import org.eclipse.sirius.tests.unit.api.session.OpenSessionTest;
import org.eclipse.sirius.tests.unit.api.session.ReloadSessionTest;
import org.eclipse.sirius.tests.unit.api.session.SampleSessionTest;
import org.eclipse.sirius.tests.unit.api.session.SessionEditorInputTests;
import org.eclipse.sirius.tests.unit.api.session.SessionManagerListener2Tests;
import org.eclipse.sirius.tests.unit.api.session.SessionSemanticResourceTests;
import org.eclipse.sirius.tests.unit.api.session.SessionServiceTest;
import org.eclipse.sirius.tests.unit.api.session.SiriusComparatorTests;
import org.eclipse.sirius.tests.unit.api.session.SiriusRegistryListener2Tests;
import org.eclipse.sirius.tests.unit.api.session.SiriusRegistryTests;
import org.eclipse.sirius.tests.unit.api.session.ViewpointSelectionTests;
import org.eclipse.sirius.tests.unit.api.tools.SiriusControlAndCrossReferenceInMultiSessionTest;
import org.eclipse.sirius.tests.unit.api.vsm.edit.SiriusAdapterFactoryRegistryTest;
import org.eclipse.sirius.tests.unit.common.EclipseUtilTest;
import org.eclipse.sirius.tests.unit.common.EqualityHelperTestCase;
import org.eclipse.sirius.tests.unit.common.InterpreterVariablesTestCase;
import org.eclipse.sirius.tests.unit.common.OperationCanceledExceptionSessionTest;
import org.eclipse.sirius.tests.unit.common.PreferencesTests;
import org.eclipse.sirius.tests.unit.common.RefreshEditorsPrecommitListenerTests;
import org.eclipse.sirius.tests.unit.common.RestoreSessionFromEditorInputTests;
import org.eclipse.sirius.tests.unit.common.SaverTest;
import org.eclipse.sirius.tests.unit.common.SiriusCrossReferenceAdapterTests;
import org.eclipse.sirius.tests.unit.common.SubMenusPrioritiesTest;
import org.eclipse.sirius.tests.unit.common.TransientSessionTests;
import org.eclipse.sirius.tests.unit.common.WorkspaceResourceSyncTestCase;
import org.eclipse.sirius.tests.unit.common.interpreter.CompoundInterpreterTestCase;
import org.eclipse.sirius.tests.unit.common.interpreter.CreateCellToolInterpreterTest;
import org.eclipse.sirius.tests.unit.common.interpreter.acceleo.mtl.AcceleoMTInterpreterOnPackageImportTests;
import org.eclipse.sirius.tests.unit.common.interpreter.acceleo.mtl.AcceleoMTLCompletionTests;
import org.eclipse.sirius.tests.unit.common.interpreter.acceleo.mtl.AcceleoMTLInterpreterTests;
import org.eclipse.sirius.tests.unit.common.interpreter.acceleo.mtl.AcceleoPackageRegistryTest;
import org.eclipse.sirius.tests.unit.common.interpreter.acceleo.mtl.IInterpreterValidationExpressionTest;
import org.eclipse.sirius.tests.unit.common.interpreter.acceleo.mtl.InterpretedExpressionTargetSwitchTest;
import org.eclipse.sirius.tests.unit.common.interpreter.feature.FeatureCompletionTests;
import org.eclipse.sirius.tests.unit.common.interpreter.feature.FeatureInterpreterTests;
import org.eclipse.sirius.tests.unit.common.interpreter.feature.FeatureProposalProviderTests;
import org.eclipse.sirius.tests.unit.common.interpreter.ocl.OCLCompletionTest;
import org.eclipse.sirius.tests.unit.common.interpreter.service.ServiceCompletionTests;
import org.eclipse.sirius.tests.unit.common.interpreter.service.ServiceInterpreterTests;
import org.eclipse.sirius.tests.unit.common.interpreter.service.ServiceProposalProviderTests;
import org.eclipse.sirius.tests.unit.common.interpreter.variable.VariableCompletionTests;
import org.eclipse.sirius.tests.unit.common.interpreter.variable.VariableInterpreterTests;
import org.eclipse.sirius.tests.unit.common.interpreter.variable.VariableProposalProviderTests;
import org.eclipse.sirius.tests.unit.common.migration.DiagramMigrationTestCampaign01;
import org.eclipse.sirius.tests.unit.common.migration.DiagramMigrationTestCampaign02;
import org.eclipse.sirius.tests.unit.common.migration.DiagramMigrationTestCampaign03;
import org.eclipse.sirius.tests.unit.common.migration.DiagramMigrationTestCampaign04;
import org.eclipse.sirius.tests.unit.common.migration.DiagramMigrationTestCampaign05;
import org.eclipse.sirius.tests.unit.common.migration.DiagramMigrationTestCampaign06;
import org.eclipse.sirius.tests.unit.common.migration.DiagramMigrationTestCampaign07;
import org.eclipse.sirius.tests.unit.common.migration.DiagramMigrationTestCampaign08;
import org.eclipse.sirius.tests.unit.common.migration.DiagramMigrationTestCampaign09;
import org.eclipse.sirius.tests.unit.common.migration.DiagramMigrationTestCampaign10;
import org.eclipse.sirius.tests.unit.common.migration.MigrationFromSirius0_9Test;
import org.eclipse.sirius.tests.unit.common.migration.MigrationFromSirius1_0_0_M5Test;
import org.eclipse.sirius.tests.unit.common.migration.ModelsToSemanticResourcesMigrationTest;
import org.eclipse.sirius.tests.unit.diagram.filter.EObjectSelectionFilterTest;
import org.eclipse.sirius.tests.unit.diagram.migration.ComputedStyleDescriptionCachePackingFileMigrationParticipantTests;
import org.eclipse.sirius.tests.unit.diagram.migration.CorruptedViewsMigrationTests;
import org.eclipse.sirius.tests.unit.diagram.migration.DDiagramSetRemovalMigrationTest;
import org.eclipse.sirius.tests.unit.diagram.migration.DiagramSplitMigrationTest;
import org.eclipse.sirius.tests.unit.diagram.migration.FontFormatMigrationTest;
import org.eclipse.sirius.tests.unit.diagram.migration.FontStyleForDNodeListElementMigrationTest;
import org.eclipse.sirius.tests.unit.diagram.migration.FragmentedFilesMigrationTest;
import org.eclipse.sirius.tests.unit.diagram.migration.ListContainerAttributeMigrationTest;
import org.eclipse.sirius.tests.unit.diagram.migration.MigrationCompartmentWithLayoutConstraintsTest;
import org.eclipse.sirius.tests.unit.diagram.migration.MigrationEdgeLabelLocationToBoundsTest;
import org.eclipse.sirius.tests.unit.diagram.migration.MigrationInconsistentGMFVisibilityTest;
import org.eclipse.sirius.tests.unit.diagram.migration.MigrationLabelBoundsToLocationTest;
import org.eclipse.sirius.tests.unit.diagram.migration.MigrationOfCollapsedBorderedNodeTest;
import org.eclipse.sirius.tests.unit.diagram.migration.MigrationOfHideLabelFilterInconsistencyTest;
import org.eclipse.sirius.tests.unit.diagram.migration.MigrationRoutingStyleEndUserOverrideTest;
import org.eclipse.sirius.tests.unit.diagram.migration.OptionalLayerToAdditionalLayerMigrationTest;
import org.eclipse.sirius.tests.unit.diagram.migration.ReferencedModelResourceMigrationTest;
import org.eclipse.sirius.tests.unit.diagram.migration.RemoveAcceleoReferencesMigrationTest;
import org.eclipse.sirius.tests.unit.diagram.migration.RepairMigrateLostDiagramElementsTC1Test;
import org.eclipse.sirius.tests.unit.diagram.migration.RepairMigrateLostDiagramElementsTC2Test;
import org.eclipse.sirius.tests.unit.diagram.migration.RepairMigratePinStatusTest;
import org.eclipse.sirius.tests.unit.diagram.migration.RepairOnLabelHiddenTest;
import org.eclipse.sirius.tests.unit.diagram.migration.RepairOnStyleCustomizationsTest;
import org.eclipse.sirius.tests.unit.diagram.migration.RepairTest;
import org.eclipse.sirius.tests.unit.diagram.migration.RepairWithActivatedFiltersTest;
import org.eclipse.sirius.tests.unit.diagram.migration.RgbValuesEDataTypeMigrationTest;
import org.eclipse.sirius.tests.unit.diagram.migration.RunRepairTest;
import org.eclipse.sirius.tests.unit.diagram.migration.SetVersionTest;
import org.eclipse.sirius.tests.unit.perf.common.CommonPreferencesTest;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class AllCommonPluginTests extends TestCase {

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
        suite.addTestSuite(RefreshEditorsPrecommitListenerTests.class);
        suite.addTestSuite(EqualityHelperTestCase.class);
        suite.addTestSuite(WorkspaceResourceSyncTestCase.class);
        suite.addTestSuite(InterpreterVariablesTestCase.class);

        suite.addTestSuite(PreferencesTests.class);

        suite.addTestSuite(ConvertProjectToModelingProjectTest.class);

        suite.addTestSuite(RepairOnStyleCustomizationsTest.class);
        suite.addTestSuite(RepairOnLabelHiddenTest.class);
        suite.addTestSuite(RepairTest.class);
        // suite.addTestSuite(ModelBasedTransformationEngineTests.class);
        // suite.addTestSuite(ColorsMigrationTests.class);
        // suite.addTestSuite(SemanticCandidateExpressionMigrationTests.class);
        suite.addTestSuite(MigrationLabelBoundsToLocationTest.class);
        suite.addTestSuite(MigrationEdgeLabelLocationToBoundsTest.class);
        suite.addTestSuite(MigrationCompartmentWithLayoutConstraintsTest.class);
        suite.addTestSuite(MigrationInconsistentGMFVisibilityTest.class);
        suite.addTestSuite(RepairMigrateLostDiagramElementsTC1Test.class);
        suite.addTestSuite(RepairMigrateLostDiagramElementsTC2Test.class);
        suite.addTestSuite(RepairMigratePinStatusTest.class);
        // suite.addTestSuite(EdgeStyleDescriptionAndEdgeStyleMigrationTests.class);
        suite.addTestSuite(FragmentedFilesMigrationTest.class);
        suite.addTestSuite(MigrationOfCollapsedBorderedNodeTest.class);
        suite.addTestSuite(MigrationOfHideLabelFilterInconsistencyTest.class);
        suite.addTestSuite(ListContainerAttributeMigrationTest.class);
        suite.addTestSuite(SetVersionTest.class);
        suite.addTestSuite(DiagramSplitMigrationTest.class);
        // suite.addTestSuite(ComponentizedFilesMigrationTests.class);
        suite.addTestSuite(RunRepairTest.class);
        suite.addTestSuite(DDiagramSetRemovalMigrationTest.class);
        // suite.addTestSuite(AutomaticMigrationOnAirdResourceTest.class);
        suite.addTestSuite(ReferencedModelResourceMigrationTest.class);
        suite.addTestSuite(RemoveAcceleoReferencesMigrationTest.class);
        suite.addTestSuite(FontStyleForDNodeListElementMigrationTest.class);
        // suite.addTestSuite(SequenceInstanceRoleSizeRepairMigrationTestCase.class);
        suite.addTestSuite(OptionalLayerToAdditionalLayerMigrationTest.class);
        suite.addTestSuite(MigrationRoutingStyleEndUserOverrideTest.class);
        suite.addTestSuite(CorruptedViewsMigrationTests.class);
        suite.addTestSuite(OperationCanceledExceptionSessionTest.class);
        suite.addTestSuite(MigrationFromSirius0_9Test.class);
        suite.addTestSuite(MigrationFromSirius1_0_0_M5Test.class);
        suite.addTestSuite(ComputedStyleDescriptionCachePackingFileMigrationParticipantTests.class);
        suite.addTestSuite(RgbValuesEDataTypeMigrationTest.class);
        suite.addTestSuite(ConvertViewpointModelingProjectToSiriusModelingProjectTest.class);
        suite.addTestSuite(FontFormatMigrationTest.class);

        suite.addTest(new JUnit4TestAdapter(CommonPreferencesTest.class));
        suite.addTest(new JUnit4TestAdapter(GroupingContentProviderTest.class));
        suite.addTest(new JUnit4TestAdapter(GroupingContentProviderByContainingTest.class));
        suite.addTest(new JUnit4TestAdapter(DiagramMigrationTestCampaign01.class));
        suite.addTest(new JUnit4TestAdapter(DiagramMigrationTestCampaign02.class));
        suite.addTest(new JUnit4TestAdapter(DiagramMigrationTestCampaign03.class));
        suite.addTest(new JUnit4TestAdapter(DiagramMigrationTestCampaign04.class));
        suite.addTest(new JUnit4TestAdapter(DiagramMigrationTestCampaign05.class));
        suite.addTest(new JUnit4TestAdapter(DiagramMigrationTestCampaign06.class));
        suite.addTest(new JUnit4TestAdapter(DiagramMigrationTestCampaign07.class));
        suite.addTest(new JUnit4TestAdapter(DiagramMigrationTestCampaign08.class));
        suite.addTest(new JUnit4TestAdapter(DiagramMigrationTestCampaign09.class));
        suite.addTest(new JUnit4TestAdapter(DiagramMigrationTestCampaign10.class));

        suite.addTestSuite(InitializationTest.class);
        suite.addTestSuite(CompletionTests.class);
        suite.addTestSuite(OCLCompletionTest.class);
        suite.addTestSuite(DiagramComponentizationManagerTest.class);
        suite.addTestSuite(DiagramExtensionDescriptionTest.class);
        suite.addTestSuite(MetamodelSpecificationInRepresentationExtensionDescriptionTest.class);
        suite.addTestSuite(DialectManagerTest.class);
        suite.addTestSuite(DialectUIManagerTest.class);
        suite.addTestSuite(FindTest.class);
        suite.addTestSuite(EdgeRoutingStyleTests.class);
        suite.addTestSuite(EdgeRoutingStyleEdgeConditionalStyleTest.class);
        suite.addTestSuite(EdgeRoutingStyleEndUserOverrideTest.class);

        suite.addTestSuite(DAnalysisSessionTests.class);
        suite.addTestSuite(SampleSessionTest.class);
        suite.addTestSuite(SessionEditorInputTests.class);
        suite.addTestSuite(SiriusRegistryTests.class);
        suite.addTestSuite(SiriusRegistryListener2Tests.class);
        suite.addTestSuite(SessionSemanticResourceTests.class);
        suite.addTestSuite(SessionServiceTest.class);

        suite.addTestSuite(EditorNameAdapterTests.class);
        suite.addTestSuite(EditorVariousTests.class);
        suite.addTestSuite(EntitiesSpecificEditorTests.class);
        suite.addTestSuite(GoToMarkerTests.class);
        suite.addTestSuite(WorkspaceEPackageRegistryTests.class);

        suite.addTestSuite(SiriusComparatorTests.class);
        suite.addTestSuite(ViewpointSelectionTests.class);
        suite.addTestSuite(SiriusAdapterFactoryRegistryTest.class);

        suite.addTestSuite(WorkspaceDragAndDropSupportTests.class);
        suite.addTestSuite(EObjectSelectionFilterTest.class);

        // suite.addTestSuite(LabelAlignmentMigrationTests.class);
        // suite.addTestSuite(LabelAlignmentMigration2Tests.class);
        // suite.addTestSuite(BundledImageDescriptionBorderColorMigrationTests.class);
        suite.addTestSuite(SemanticResourcesManagementTests.class);
        suite.addTestSuite(SaveWhenNoEditorsTests.class);
        suite.addTestSuite(ModifyHeaderLabelExpressionTest.class);

        suite.addTestSuite(CreateCellToolInterpreterTest.class);
        suite.addTestSuite(AcceleoMTLInterpreterTests.class);
        suite.addTestSuite(AcceleoMTLCompletionTests.class);
        suite.addTestSuite(AcceleoCrossReferencerTest.class);
        suite.addTestSuite(AcceleoPackageRegistryTest.class);
        suite.addTestSuite(IInterpreterValidationExpressionTest.class);
        suite.addTestSuite(FeatureInterpreterTests.class);
        suite.addTestSuite(FeatureCompletionTests.class);
        suite.addTestSuite(ServiceInterpreterTests.class);
        suite.addTestSuite(ServiceCompletionTests.class);
        suite.addTestSuite(VariableInterpreterTests.class);
        suite.addTestSuite(VariableCompletionTests.class);
        suite.addTestSuite(FeatureProposalProviderTests.class);
        suite.addTestSuite(ServiceProposalProviderTests.class);
        suite.addTestSuite(VariableProposalProviderTests.class);
        suite.addTestSuite(InterpretedExpressionTargetSwitchTest.class);
        suite.addTestSuite(ReloadSessionTest.class);
        suite.addTestSuite(EclipseUtilTest.class);
        suite.addTestSuite(CompoundInterpreterTestCase.class);

        suite.addTestSuite(TransientSessionTests.class);
        suite.addTestSuite(RestoreSessionFromEditorInputTests.class);
        suite.addTestSuite(SiriusCrossReferenceAdapterTests.class);
        suite.addTestSuite(SaverTest.class);
        suite.addTestSuite(XSDSemanticResourceTests.class);
        suite.addTestSuite(SiriusControlAndCrossReferenceInMultiSessionTest.class);
        suite.addTestSuite(ModelsToSemanticResourcesMigrationTest.class);
        suite.addTestSuite(SemanticResourceURIInAirdTests.class);
        suite.addTestSuite(OpenSessionTest.class);

        suite.addTestSuite(SubMenusPrioritiesTest.class);
    }

    /**
     * Add the tests which for one reason or another are not part of the suite
     * launched on each Gerrit verification.
     * 
     * @param suite
     *            the suite to add the tests into.
     */
    public static void addNonGerritPart(TestSuite suite) {
        // This one takes too long (12 minutes) to be part of the Gerrit suite.
        suite.addTestSuite(AcceleoMTInterpreterOnPackageImportTests.class);
        // The ones below are "blacklisted" for now because they caused at least
        // one false-negative Gerrit Verification job
        suite.addTestSuite(SessionManagerListener2Tests.class);
        suite.addTestSuite(RepairWithActivatedFiltersTest.class);
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the test.
     * 
     * @return The testsuite containing all the tests
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite("Common Plugin Tests");
        addGerritPart(suite);
        addNonGerritPart(suite);
        return suite;
    }
}

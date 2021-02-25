/*******************************************************************************
 * Copyright (c) 2015, 2021 Obeo.
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
package org.eclipse.sirius.viewpoint;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 * 
 * @author Florian Barbin
 *
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, SiriusPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF

    @TranslatableMessage
    public static String AbstractCommandFactory_refreshTasklabel;

    @TranslatableMessage
    public static String AbstractCommonToolToAppliedOnConstraint_label;

    @TranslatableMessage
    public static String AbstractExternalJavaAction_nullParameter;

    @TranslatableMessage
    public static String AbstractExternalJavaAction_parameterErrorMsg;

    @TranslatableMessage
    public static String AbstractExternalJavaAction_parameterType;

    @TranslatableMessage
    public static String AbstractExternalJavaAction_parameterTypeErrorMsg;

    @TranslatableMessage
    public static String AbstractProviderDescriptor_attributeMissingMsg;

    @TranslatableMessage
    public static String AbstractRepresentationDialectServices_createRepresentationMsg;

    @TranslatableMessage
    public static String AbstractRepresentationDialectServices_initRepresentationMsg;

    @TranslatableMessage
    public static String AbstractResourceStrategyImpl_methodReleaseNotHandleMsg;

    @TranslatableMessage
    public static String AbstractSavingPolicy_saveMsg;

    @TranslatableMessage
    public static String AbstractSavingPolicy_savingErrorMsg;

    @TranslatableMessage
    public static String AbstractSiriusMigrationService_contributionInstantiationErrorMsg;

    @TranslatableMessage
    public static String AbstractVersionSAXParser_getVersionMsg;

    @TranslatableMessage
    public static String AbstractVersionSAXParser_stopParsingMsg;

    @TranslatableMessage
    public static String AddSemanticResourceCommand_label;

    @TranslatableMessage
    public static String AirDResourceImpl_nullUid;

    @TranslatableMessage
    public static String AnalysisResourceReloadedCommand_label;

    @TranslatableMessage
    public static String AttachSemanticResourcesJob_name;

    @TranslatableMessage
    public static String ChangeContextTask_label;

    @TranslatableMessage
    public static String CompositeResourceMonitor_addMonitorErrorMsg;

    @TranslatableMessage
    public static String CompositeResourceMonitor_alreadyRegisteredErrorMsg;

    @TranslatableMessage
    public static String CompositeResourceMonitor_alreadyUsedNameErrorMsg;

    @TranslatableMessage
    public static String CompositeResourceMonitor_uriCompareErrorMsg;

    @TranslatableMessage
    public static String Constraint_validNullLocationURIForGroupInPopupMenuConstraint_message;

    @TranslatableMessage
    public static String ControlCommand_moveErrorMsg;

    @TranslatableMessage
    public static String ControlledResourcesDetector_refreshCommandLabel;

    @TranslatableMessage
    public static String CopyRepresentationCommand_label;

    @TranslatableMessage
    public static String CreateInstanceTask_addToRefErrorMsg;

    @TranslatableMessage
    public static String CreateInstanceTask_creationErrorMsg;

    @TranslatableMessage
    public static String CreateInstanceTask_label;

    @TranslatableMessage
    public static String CreateRepresentationCommand_label;

    @TranslatableMessage
    public static String CreateRepresentationCommand_nullExpresionWarningMsg;

    @TranslatableMessage
    public static String DAnalysisSelectorService_multipleDefaultErrorMsg;

    @TranslatableMessage
    public static String DAnalysisSelectorService_noDefaultWarningMsg;

    @TranslatableMessage
    public static String DAnalysisSessionImpl_addNoParentAnalysisErrorMsg;

    @TranslatableMessage
    public static String DAnalysisSessionImpl_addSemanticErrorMsg;

    @TranslatableMessage
    public static String DAnalysisSessionImpl_addSemanticResourceMsg;

    @TranslatableMessage
    public static String DAnalysisSessionImpl_noEditingDomainErrorMsg;

    @TranslatableMessage
    public static String DAnalysisSessionImpl_noRessourceErrorMsg;

    @TranslatableMessage
    public static String DAnalysisSessionImpl_openMsg;

    @TranslatableMessage
    public static String DAnalysisSessionImpl_removeNoParentAnalysisErrorMsg;

    @TranslatableMessage
    public static String DAnalysisSessionImpl_saveInterruptedMsg;

    @TranslatableMessage
    public static String DAnalysisSessionImpl_saveMsg;

    @TranslatableMessage
    public static String DAnalysisSessionImpl_toStringMsg;

    @TranslatableMessage
    public static String DAnalysisSessionImpl_unloadingErrorMsg;

    @TranslatableMessage
    public static String DanglingRefRemovalTrigger_removeDanglingCmdLabel;

    @TranslatableMessage
    public static String DefaultLocalSessionCreationOperation_createResoureMsg;

    @TranslatableMessage
    public static String DefaultLocalSessionCreationOperation_createSessionMsg;

    @TranslatableMessage
    public static String DefaultLocalSessionCreationOperation_sessionOpenMsg;

    @TranslatableMessage
    public static String DeleteDRepresentationElementsTask_label;

    @TranslatableMessage
    public static String DeleteDRepresentationTask_label;

    @TranslatableMessage
    public static String DeleteEObjectTask_label;

    @TranslatableMessage
    public static String DeleteRepresentationCommand_label;

    @TranslatableMessage
    public static String DeleteWithoutToolTask_label;

    @TranslatableMessage
    public static String DialectManagerImpl_refreshImpactedMsg;

    @TranslatableMessage
    public static String DialectManagerImpl_refreshMsg;

    @TranslatableMessage
    public static String DRepresentationDescriptorQuery_representationError;

    @TranslatableMessage
    public static String DRepresentationDescriptorToDRepresentationLinkManager_repLoading;

    @TranslatableMessage
    public static String DRepInDViewToRootObjectsAndWithDRepDescRepPathMigrationParticipant_nameMigrationMessage;

    @TranslatableMessage
    public static String DViewOperations_addSelectedViewMsg;

    @TranslatableMessage
    public static String DViewOperations_createViewMsg;

    @TranslatableMessage
    public static String DViewOperations_initRepresentationMsg;

    @TranslatableMessage
    public static String DViewOperations_notContainedErrorMsg;

    @TranslatableMessage
    public static String DViewOperations_removeSelectedViewMsg;

    @TranslatableMessage
    public static String DViewOperations_updateSelectedVPDataMsg;

    @TranslatableMessage
    public static String EAttributeCustomizationAttributeNameCommonToAppliedOnConstraint_EAttributeDiffernentTypesErrorMsg;

    @TranslatableMessage
    public static String EAttributeCustomizationAttributeNameCommonToAppliedOnConstraint_notEAttributeErrorMsg;

    @TranslatableMessage
    public static String EAttributeCustomizationAttributeNameCommonToAppliedOnConstraint_validationNotExistErrorMsg;

    @TranslatableMessage
    public static String EAttributeCustomizationAttributeNameCommonToAppliedOnConstraint_validationStyleDescriptionErrorMsg;

    @TranslatableMessage
    public static String EclipseDeleteHookDescriptor_extensionLoadingErrorMsg;

    @TranslatableMessage
    public static String EObjectQuery_valuesErrorMsg;

    @TranslatableMessage
    public static String EReferenceCustomizationReferenceNameCommonToAppliedOnConstraint_validationNotExistErrorMsg;

    @TranslatableMessage
    public static String EReferenceCustomizationReferenceNameCommonToAppliedOnConstraint_notAReferenceErrorMsg;

    @TranslatableMessage
    public static String EditingDomainUndoContext_label;

    @TranslatableMessage
    public static String ElementsToSelectTask_errorMsg;

    @TranslatableMessage
    public static String ElementsToSelectTask_label;

    @TranslatableMessage
    public static String EmptyAppliedOnListConstraint_errorMsg;

    @TranslatableMessage
    public static String TaskExecutor_errorModifyingModelMsg;

    @TranslatableMessage
    public static String ExecuteToolOperationTask_label;

    @TranslatableMessage
    public static String ExecuteToolOperationTask_sessionNotFound;

    @TranslatableMessage
    public static String ExternalJavaActionDescriptor_actionCreationErrorMsg;

    @TranslatableMessage
    public static String ExternalJavaActionTask_label;

    @TranslatableMessage
    public static String FeatureContributor_targetObject;

    @TranslatableMessage
    public static String FeatureContributor_sourceObject;

    @TranslatableMessage
    public static String FeatureContributor_featureMissingMsg;

    @TranslatableMessage
    public static String FeatureContributor_unexpectedTypeErrorMsg;

    @TranslatableMessage
    public static String FeatureContributor_noTargetSpecifiedErrorMsg;

    @TranslatableMessage
    public static String FeatureContributor_noSourceSpefifiedErrorMsg;

    @TranslatableMessage
    public static String FeatureContributor_imcompatibleFeaturesErrorMsg;

    @TranslatableMessage
    public static String FeatureContributor_unmodifiableFeatureErrorMsg;

    @TranslatableMessage
    public static String FeatureContributor_noTargetFeatureErrorMsg;

    @TranslatableMessage
    public static String ForTask_label;

    @TranslatableMessage
    public static String IInterpreterMessages_invalidFeatureErrorMsg;

    @TranslatableMessage
    public static String IPropertiesProvider_unfoundPropertyErrorMsg;

    @TranslatableMessage
    public static String ISiriusMessages_invalidAirdFileErrorMsg;

    @TranslatableMessage
    public static String ISiriusMessages_invalidDescFileErrorMsg;

    @TranslatableMessage
    public static String ISiriusMessages_notADecoratorErrorMsg;

    @TranslatableMessage
    public static String IfTask_label;

    @TranslatableMessage
    public static String InitInterpreterFromParsedVariableTask_label;

    @TranslatableMessage
    public static String InitInterpreterVariablesTask_label;

    @TranslatableMessage
    public static String InitInterpreterVariablesTask_invalidModelErrorMsg;

    @TranslatableMessage
    public static String InitializeModelingProjectJob_invalidModelingProjectsErrorMsg;

    @TranslatableMessage
    public static String InitializeModelingProjectJob_invalidModelingProjectErrorMsg;

    @TranslatableMessage
    public static String InitializeModelingProjectJob_label;

    @TranslatableMessage
    public static String InitializeModelingProjectJob_labelEmptyProject;

    @TranslatableMessage
    public static String InterpretedExpressionVariableTask_label;

    @TranslatableMessage
    public static String InterpreterRegistry_sessionNotFoundErrorMsg;

    @TranslatableMessage
    public static String InterpretedExpressionQueryProviderRegistry_instanciationError;

    @TranslatableMessage
    public static String InterpreterRegistry_nullModelElementErrorMsg;

    @TranslatableMessage
    public static String InterpreterRegistry_ImpossibleToFindInterpreterErrorMsg;

    @TranslatableMessage
    public static String InvalidPermissionCommand_label;

    @TranslatableMessage
    public static String InvalidModelingProjectMarkerUpdaterJob_updateMarkers;

    @TranslatableMessage
    public static String SavingPolicyImpl_savingErrorMsg;

    @TranslatableMessage
    public static String JavaActionFromToolCommand_label;

    @TranslatableMessage
    public static String LaunchRunnableTask_label;

    @TranslatableMessage
    public static String LetTask_label;

    @TranslatableMessage
    public static String LoadEMFResource_loadingErrorMsg;

    @TranslatableMessage
    public static String MarkerRuntimeLoggerImpl_feature;

    @TranslatableMessage
    public static String MarkerRuntimeLoggerImpl_featureWithMessage;

    @TranslatableMessage
    public static String MigrationCommandExecutor_migrationErrorMsg;

    @TranslatableMessage
    public static String MigrationUtil_toBigErrorMsg;

    @TranslatableMessage
    public static String MigrationUtil_loadingMsg;

    @TranslatableMessage
    public static String MigrationUtil_IOErrorMsg;

    @TranslatableMessage
    public static String MigrationUtil_invalidMappingErrorMsg;

    @TranslatableMessage
    public static String ModelingModelProvider_addAnotherRepresentationFile;

    @TranslatableMessage
    public static String ModelingModelProvider_addAnotherRepresentationFileSeveralProjects;

    @TranslatableMessage
    public static String ModelingModelProvider_mainRepresentationFileDeleted;

    @TranslatableMessage
    public static String ModelingModelProvider_mainRepresentationFilesOfSomeProjectsDeleted;

    @TranslatableMessage
    public static String ModelingModelProvider_satusUnsavedDataWillBeLost;

    @TranslatableMessage
    public static String ModelingModelProvider_satusUnsaveDataWillBeLostWithProjectNames;

    @TranslatableMessage
    public static String ModelingProject_getMainRepFileURIMsg;

    @TranslatableMessage
    public static String ModelingProjectQuery_severalRepresentationsFiles;

    @TranslatableMessage
    public static String ModelingProjectQuery_mustContainOneRepFileMsg;

    @TranslatableMessage
    public static String ModelingProjectQuery_and;

    @TranslatableMessage
    public static String ModelOperationToTask_cannotCreateTaskWarningMsg;

    @TranslatableMessage
    public static String MoveElementInListAction_notAMemberErrorMsg;

    @TranslatableMessage
    public static String MoveElementInListAction_predecessorParameterErrorMsg;

    @TranslatableMessage
    public static String MoveElementInListAction_elementAndPredecessorShouldBeDiffErrorMsg;

    @TranslatableMessage
    public static String MoveElementInListAction_referenceNotChangeableErrorMsg;

    @TranslatableMessage
    public static String MoveElementInListAction_notMultiValuedRefErrorMsg;

    @TranslatableMessage
    public static String MoveElementInListAction_notARefErrorMsg;

    @TranslatableMessage
    public static String MoveElementInListAction_featureNotFoundErrorMsg;

    @TranslatableMessage
    public static String MoveElementTask_label;

    @TranslatableMessage
    public static String MoveElementTask_ImpossibleToAddValueErrorMsg;

    @TranslatableMessage
    public static String MoveRepresentationCommand_label;

    @TranslatableMessage
    public static String NoNullResourceCommand_instanceErrorMsg;

    @TranslatableMessage
    public static String NotificationTask_label;

    @TranslatableMessage
    public static String PaneBasedSelectionWizardDescriptionImpl_choiceOfValuesMsg;

    @TranslatableMessage
    public static String PaneBasedSelectionWizardDescriptionImpl_selectedValuesMsg;

    @TranslatableMessage
    public static String PrepareNewAnalysisCommand_label;

    @TranslatableMessage
    public static String RefreshHelper_notNullPredicate;

    @TranslatableMessage
    public static String RefreshImpactedElementsCommand_label;

    @TranslatableMessage
    public static String RefreshRepresentationsCommand_label;

    @TranslatableMessage
    public static String RemoveElementTask_label;

    @TranslatableMessage
    public static String RemoveElementTask_notAViewErrorMsg;

    @TranslatableMessage
    public static String RemoveSemanticResourceCommand_label;

    @TranslatableMessage
    public static String RenameRepresentationCommand_label;

    @TranslatableMessage
    public static String RepresentationDescriptionMetaModelsConstraint_noMetaModel;

    @TranslatableMessage
    public static String RestoreModelElementStateCommand_label;

    @TranslatableMessage
    public static String ResourceVersionMismatchDiagnostic_airdMessage;

    @TranslatableMessage
    public static String ResourceVersionMismatchDiagnostic_vsmMessage;

    @TranslatableMessage
    public static String RuntimeLoggerInterpreterImpl_evaluationConditionErrorMsg;

    @TranslatableMessage
    public static String SaveSessionJob_sessionSavingMsg;

    @TranslatableMessage
    public static String Saver_savingErrorMsg;

    @TranslatableMessage
    public static String SelectionWizardDescriptionImpl_title;

    @TranslatableMessage
    public static String SessionFactoryImpl_ResourceTypeErrorMsg;

    @TranslatableMessage
    public static String SessionFactoryImpl_EmptyContentErrorMsg;

    @TranslatableMessage
    public static String SessionFactoryImpl_creationFailedErrorMsg;

    @TranslatableMessage
    public static String SessionFactoryImpl_sessionCreation;

    @TranslatableMessage
    public static String SessionFactoryImpl_loadingError;

    @TranslatableMessage
    public static String SessionFactoryImpl_sessionLoadingMsg;

    @TranslatableMessage
    public static String SessionInterpreter_evaluationError;

    @TranslatableMessage
    public static String SessionManagerImpl_remoteServerConnectionErrorMsg;

    @TranslatableMessage
    public static String SessionManagerImpl_cantAddNullSessionErrorMsg;

    @TranslatableMessage
    public static String SessionManagerImpl_representationsFileLoadingErrorMsg;

    @TranslatableMessage
    public static String SessionManagerImpl_representationsFileLoadingSeeErrorLogMsg;

    @TranslatableMessage
    public static String SessionQuery_Date;

    @TranslatableMessage
    public static String SessionQuery_Resources;

    @TranslatableMessage
    public static String SessionQuery_SessionResources;

    @TranslatableMessage
    public static String SessionQuery_SemanticResources;

    @TranslatableMessage
    public static String SessionQuery_ControlledResources;

    @TranslatableMessage
    public static String SessionQuery_Elements;

    @TranslatableMessage
    public static String SessionQuery_FileSize;

    @TranslatableMessage
    public static String SessionQuery_Viewpoints;

    @TranslatableMessage
    public static String SessionQuery_ActiveViewpoints;

    @TranslatableMessage
    public static String SessionQuery_InactiveViewpoints;

    @TranslatableMessage
    public static String SessionQuery_LoadedFromResource;

    @TranslatableMessage
    public static String SessionQuery_Representations;

    @TranslatableMessage
    public static String SessionQuery_AllRepresentations;

    @TranslatableMessage
    public static String SessionQuery_Diagram;

    @TranslatableMessage
    public static String SessionQuery_Tree;

    @TranslatableMessage
    public static String SessionQuery_EditionTable;

    @TranslatableMessage
    public static String SessionQuery_CrossTable;

    @TranslatableMessage
    public static String SessionQuery_Sequence;

    @TranslatableMessage
    public static String SessionQuery_LoadedReps;

    @TranslatableMessage
    public static String SessionQuery_NbRepElements;

    @TranslatableMessage
    public static String SessionQuery_LoadedBrokenReps;

    @TranslatableMessage
    public static String SessionQuery_InvalidReps;

    @TranslatableMessage
    public static String SessionQuery_RepresentationDescriptorDetails;

    @TranslatableMessage
    public static String SessionQuery_TagInvalid;

    @TranslatableMessage
    public static String SessionQuery_TagLoaded;

    @TranslatableMessage
    public static String SessionResourcesSynchronizer_cantHandleResourceChangeMsg;

    @TranslatableMessage
    public static String SessionResourcesSynchronizer_reloadOperationFailErrorMsg;

    @TranslatableMessage
    public static String SessionResourcesTracker_addReferencedSemanticResourcesMsg;

    @TranslatableMessage
    public static String SessionResourcesTracker_semanticResourcesAccessErrorMsg;

    @TranslatableMessage
    public static String SessionVSMUpdater_VSMLoadErrorMsg;

    @TranslatableMessage
    public static String SetValueTask_label;

    @TranslatableMessage
    public static String SiriusControlCommand_controlResourceMsg;

    @TranslatableMessage
    public static String SiriusPreferencesImpl_noProjectScope;

    @TranslatableMessage
    public static String SiriusRepairProcess_contributionInstantationErrorMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_errorMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_inProgressMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_closingSessionMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_savingSessionMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_repairModelMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_backupMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_resolvingReferencesMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_openingsessionMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_loadingModelMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_restoringBckErrorMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_bckupCreationErrorMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_removeElementsMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_saveModelElementStateMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_restoringElementStatsMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_postRefreshMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_refreshingRepresentationsMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_removingElementsMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_savingElementsStateMsg;

    @TranslatableMessage
    public static String SiriusRepairProcess_handlingViewMsg;

    @TranslatableMessage
    public static String SiriusTasksKey_mmExtension;

    @TranslatableMessage
    public static String SiriusTasksKey_genericModeler;

    @TranslatableMessage
    public static String SiriusTasksKey_repairMigrate;

    @TranslatableMessage
    public static String SiriusTasksKey_cleaningADiagram;

    @TranslatableMessage
    public static String SiriusTasksKey_updatingADiagram;

    @TranslatableMessage
    public static String SiriusTasksKey_evaluatingAcceleoExpression;

    @TranslatableMessage
    public static String SiriusTasksKey_evaluatingOCLExpressions;

    @TranslatableMessage
    public static String SiriusTasksKey_checkPreconditionExpressions;

    @TranslatableMessage
    public static String SiriusTasksKey_InitAcceleoInterpreter;

    @TranslatableMessage
    public static String SiriusTasksKey_isGMFViewValid;

    @TranslatableMessage
    public static String SiriusTasksKey_getNodesCandidates;

    @TranslatableMessage
    public static String SiriusTasksKey_canonicalRefresh;

    @TranslatableMessage
    public static String SiriusTasksKey_validateAllDDiagramElements;

    @TranslatableMessage
    public static String SiriusTasksKey_cacheAccess;

    @TranslatableMessage
    public static String SiriusTasksKey_getEdgesCandidates;

    @TranslatableMessage
    public static String SiriusTasksKey_validatingTheNode;

    @TranslatableMessage
    public static String SiriusTasksKey_validatingEdge;

    @TranslatableMessage
    public static String SiriusTasksKey_cleaningAllEdges;

    @TranslatableMessage
    public static String SiriusTasksKey_getContainerCandidates;

    @TranslatableMessage
    public static String SiriusTasksKey_createMissingContainersFromViewpoint;

    @TranslatableMessage
    public static String SiriusTasksKey_refreshADiagram;

    @TranslatableMessage
    public static String SiriusTasksKey_CleaningRemoveDanglingRef;

    @TranslatableMessage
    public static String SiriusTasksKey_createMissingEdgesFromViewpoint;

    @TranslatableMessage
    public static String SiriusTasksKey_createMissingNodeFromContainer;

    @TranslatableMessage
    public static String SiriusTasksKey_isTheElementCollapsed;

    @TranslatableMessage
    public static String SiriusTasksKey_checkThatElementHasToBeDisplayed;

    @TranslatableMessage
    public static String SiriusTasksKey_isElementDisplayed;

    @TranslatableMessage
    public static String SiriusTasksKey_createMissingNodeFromViewpoint;

    @TranslatableMessage
    public static String SiriusTasksKey_updatingAllNodes;

    @TranslatableMessage
    public static String SiriusTasksKey_updateAllContainers;

    @TranslatableMessage
    public static String SiriusTasksKey_updatingAllEdges;

    @TranslatableMessage
    public static String SiriusTasksKey_getRemovedNodesCandidates;

    @TranslatableMessage
    public static String SiriusTasksKey_synchronizeDiagram;

    @TranslatableMessage
    public static String SiriusTasksKey_openSession;

    @TranslatableMessage
    public static String SiriusTasksKey_openDiagram;

    @TranslatableMessage
    public static String SiriusTasksKey_computeEdgeSrcTgtViews;

    @TranslatableMessage
    public static String SiriusTasksKey_cleanOrphanedNodes;

    @TranslatableMessage
    public static String SiriusTasksKey_removeDanglingRef;

    @TranslatableMessage
    public static String SiriusTasksKey_getKeptNodesCandidates;

    @TranslatableMessage
    public static String SiriusTasksKey_launchRefreshFromOperationHistoryListener;

    @TranslatableMessage
    public static String SiriusTasksKey_bigRefresh;

    @TranslatableMessage
    public static String SiriusTasksKey_isFold;

    @TranslatableMessage
    public static String SiriusTasksKey_instanceOf;

    @TranslatableMessage
    public static String SiriusTasksKey_addAColumnInSWTTable;

    @TranslatableMessage
    public static String SiriusTasksKey_setColumnNameInSWTTable;

    @TranslatableMessage
    public static String SiriusTasksKey_openSessionAction;

    @TranslatableMessage
    public static String SiriusTasksKey_refreshATree;

    @TranslatableMessage
    public static String SiriusTasksKey_refreshSWTTable;

    @TranslatableMessage
    public static String SiriusTasksKey_changeSWTTableCollapseState;

    @TranslatableMessage
    public static String SiriusTasksKey_refreshSWTTableLine;

    @TranslatableMessage
    public static String SiriusTasksKey_createSWTTable;

    @TranslatableMessage
    public static String SiriusTasksKey_refreshTable;

    @TranslatableMessage
    public static String SiriusTasksKey_getAddedNodesCandidates;

    @TranslatableMessage
    public static String SiriusTasksKey_findAirFromModelElement;

    @TranslatableMessage
    public static String SiriusTasksKey_resolveAll;

    @TranslatableMessage
    public static String SiriusTasksKey_launchRefreshFromLayerChange;

    @TranslatableMessage
    public static String SiriusTasksKey_openTable;

    @TranslatableMessage
    public static String SiriusTasksKey_refreshPropertiesViewSection;

    @TranslatableMessage
    public static String SiriusTasksKey_refreshPropertiesView;

    @TranslatableMessage
    public static String SiriusTasksKey_updateSWTTableLine;

    @TranslatableMessage
    public static String SiriusTasksKey_semanticRefresh;

    @TranslatableMessage
    public static String SiriusTasksKey_loadAirdFile;

    @TranslatableMessage
    public static String SiriusTasksKey_refreshRepairMigrateLostElements;

    @TranslatableMessage
    public static String SiriusTasksKey_changeSWTTableColumnVisibleState;

    @TranslatableMessage
    public static String SiriusTasksKey_changeSWTTableLineVisibleSate;

    @TranslatableMessage
    public static String SiriusTasksKey_openTree;

    @TranslatableMessage
    public static String SiriusUncontrolCommand_label;

    @TranslatableMessage
    public static String SiriusUncontrolCommand_resourceDeletionFailedMsg;

    @TranslatableMessage
    public static String TechnicalUidMigrationParticipant_message;

    @TranslatableMessage
    public static String UnexecutableTask_label;

    @TranslatableMessage
    public static String UnsetTask_nullOperationErrorMsg;

    @TranslatableMessage
    public static String UnsetTask_label;

    @TranslatableMessage
    public static String Updater_updateElementDifferentReferenceTypeErrorMsg;

    @TranslatableMessage
    public static String Updater_updateElementLogicallyDifferentErrorMsg;

    @TranslatableMessage
    public static String VSMElementCustomizationReuseValidConstraint_noEReferenceErrorMsg;

    @TranslatableMessage
    public static String VSMElementCustomizationReuseValidConstraint_noEAttributeErrorMsg;

    @TranslatableMessage
    public static String VSMElementCustomizationReuseValidConstraint_doesntConcernsStyleDescErrorMsg;

    @TranslatableMessage
    public static String VSMElementNameValidConstraint_invalidNameErrorMsg;

    @TranslatableMessage
    public static String ValidImageConstraint_imageDoesntExistErrorMsg;

    @TranslatableMessage
    public static String ValidImageConstraint_invalidPathErrorMsg;

    @TranslatableMessage
    public static String ValidationRuleImpl_elementHas;

    @TranslatableMessage
    public static String ValidationRuleSpecOperations_evaluationErrorMsg;

    @TranslatableMessage
    public static String ViewpointProtocolParser_noViewpointErrorMsg;

    @TranslatableMessage
    public static String ViewpointProtocolParser_invalidURIErrorMsg;

    @TranslatableMessage
    public static String ViewpointProtocolParser_unamed;

    @TranslatableMessage
    public static String ViewpointRegistryImpl_FileLoadingErrorMsg;

    @TranslatableMessage
    public static String ViewpointRegistryImpl_cantDeployVSMErrorMsg;

    @TranslatableMessage
    public static String ViewpointRegistryImpl_cantLoadVSMErrorMsg;

    @TranslatableMessage
    public static String ViewpointRegistryImpl_unableToUnloadFileErrorMsg;

    @TranslatableMessage
    public static String XMIModelFileHandler_parsingStopedMsg;

    @TranslatableMessage
    public static String XMIModelFileHandler_stopTheParsingMsg;

    // CHECKSTYLE:ON
    private Messages() {
        // Prevents instanciation.
    }
}

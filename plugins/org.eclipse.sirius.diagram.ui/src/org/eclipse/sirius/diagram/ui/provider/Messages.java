/*******************************************************************************
 * Copyright (c) 2015, 2021 Obeo and THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.provider;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 *
 * @author mporhel
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, DiagramUIPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF

    @TranslatableMessage
    public static String AbstractDDiagramElementLabelItemProvider_emptyLabel;

    @TranslatableMessage
    public static String AbstractDDiagramElementLabelItemProvider_label;

    @TranslatableMessage
    public static String AbstractEdgesZOrderAction_noExecutioninformationDialogTitle;

    @TranslatableMessage
    public static String AbstractExtendedContentOutlinePage_setSelectionJobName;

    @TranslatableMessage
    public static String AbstractLayoutProvider_arrangeAllCommandLabel;

    @TranslatableMessage
    public static String AbstractLayoutProvider_arrangeAllProfilerTaskCategory;

    @TranslatableMessage
    public static String AbstractLayoutProvider_arrangeAllProfilerTaskName;

    @TranslatableMessage
    public static String AbstractLayoutProvider_layoutError;

    @TranslatableMessage
    public static String AbstractModelChangeOperation_name;

    @TranslatableMessage
    public static String AbstractParser_setValuesCmdLabel;

    @TranslatableMessage
    public static String AbstractParser_UnexpectedValueTypeMessage;

    @TranslatableMessage
    public static String AbstractParser_UnknownLiteralMessage;

    @TranslatableMessage
    public static String AbstractParser_WrongStringConversionMessage;

    @TranslatableMessage
    public static String AbstractProviderDescriptor_missingAttributeMsg;

    @TranslatableMessage
    public static String AbstractSiriusLayoutDataManager_unhandledDiagramElementKind;

    @TranslatableMessage
    public static String ActivateBehaviorToolsCommand_label;

    @TranslatableMessage
    public static String ActivateFiltersCommand_label;

    @TranslatableMessage
    public static String ActivateRulesCommand_label;

    @TranslatableMessage
    public static String AddErrorCommand_label;

    @TranslatableMessage
    public static String AirResizableEditPolicy_autoSizeCommandLabel;

    @TranslatableMessage
    public static String AnonymousUserFixedColorName;

    @TranslatableMessage
    public static String ArrangeBorderNodesAction_actionText;

    @TranslatableMessage
    public static String ArrangeBorderNodesAction_commandLabel;

    @TranslatableMessage
    public static String ArrangeBorderNodesAction_toolbarActionText;

    @TranslatableMessage
    public static String ArrangeBorderNodesAction_toolTipText;

    @TranslatableMessage
    public static String BehaviorsPropertySection_activatedBehaviorsLabel;

    @TranslatableMessage
    public static String BehaviorsPropertySection_availableBehaviorsLabel;

    @TranslatableMessage
    public static String BorderItemAwareLayoutProvider_invalidItemsPosition;

    @TranslatableMessage
    public static String BorderItemAwareLayoutProvider_setBoundsCommandLabel;

    @TranslatableMessage
    public static String BorderItemLayoutData_movedBorderItemSincePreviousLayout;

    @TranslatableMessage
    public static String BorderItemLayoutData_unmovedBorderItemSincePreviousLayout;

    @TranslatableMessage
    public static String BracketBendpointEditPolicy_moveBrackedCommandLabel;

    @TranslatableMessage
    public static String BracketBendpointEditPolicy_rotateBracketCommandLabel;

    @TranslatableMessage
    public static String BundledImageEditPart_notBundleImageMsg;

    @TranslatableMessage
    public static String BundledImageShape_attributeAbsent;

    @TranslatableMessage
    public static String BundledImageShape_idMissing;

    @TranslatableMessage
    public static String BundledImageShape_usedByEndUser_idMissing_msg;

    @TranslatableMessage
    public static String BundledImageShape_usedInVSM_idMissing_msg;

    @TranslatableMessage
    public static String CenterEditPartEdgesCommand_label;

    @TranslatableMessage
    public static String ChangeBendpointsOfEdgesCommand_label;

    @TranslatableMessage
    public static String ChangeBendpointsOfEdgesCommand_mapGmfAnchorToDraw2dAnchorCommandLabel;

    @TranslatableMessage
    public static String ChangeBendpointsOfEdgesCommand_mapGmfPointsToDraw2dPoints;

    @TranslatableMessage
    public static String ChangeBendpointsOfEdgesCommand_mapGmfToDraw2dCommandLabel;

    @TranslatableMessage
    public static String ChangeBendpointsOfEdgesCommand_updateLabelsOffsetCmdLabel;

    @TranslatableMessage
    public static String ChangeBendpointsOfEdgesCommand_warningCommandResultMessage;

    @TranslatableMessage
    public static String ChangeFilterActivation_activateFilter;

    @TranslatableMessage
    public static String ChangeFilterActivation_deactivateFilter;

    @TranslatableMessage
    public static String ChangeFilterActivation_label;

    @TranslatableMessage
    public static String ChangeSynchronizedDagramStatusCommand_label;

    @TranslatableMessage
    public static String ChildrenAdjustmentCommand_errorMsg;

    @TranslatableMessage
    public static String ChildrenAdjustmentCommand_label;

    @TranslatableMessage
    public static String Column_wrongColumnViewError;

    @TranslatableMessage
    public static String CommandFactory_doNothingLabel;

    @TranslatableMessage
    public static String CommandName_OpenDiagram;

    @TranslatableMessage
    public static String CompoundEditPolicy_nullEditPolicyMsg;

    @TranslatableMessage
    public static String ConcernComboContributionItem_tooltip;

    @TranslatableMessage
    public static String ConnectionsFactory_edgeNotCreatedMsg;

    @TranslatableMessage
    public static String CopyFormatAction_clearPreviousFormatDateCommandLabel;

    @TranslatableMessage
    public static String CopyFormatAction_commandLabel;

    @TranslatableMessage
    public static String CopyFormatAction_notifyEditors;

    @TranslatableMessage
    public static String CopyFormatAction_storeFormatCommandLabel;

    @TranslatableMessage
    public static String CopyFormatAction_text;

    @TranslatableMessage
    public static String CopyFormatAction_toolTipText;

    @TranslatableMessage
    public static String CopyFormatDataCommand_label;

    @TranslatableMessage
    public static String CopyToSiriusClipboardCommand_label;

    @TranslatableMessage
    public static String CreateAndStoreGMFDiagramCommand_label;

    @TranslatableMessage
    public static String CreateRepresentationFromRepresentationCreationDescription_cmdLabel;

    @TranslatableMessage
    public static String CustomSiriusDocumentProvider_noCorrespondingResourceMsg;

    @TranslatableMessage
    public static String CustomSiriusDocumentProvider_useModelExplorerToOpenMsg;

    @TranslatableMessage
    public static String CustomTreeSelectionDialog_checkAllButtonTooltip;

    @TranslatableMessage
    public static String CustomTreeSelectionDialog_collapaseAllTooltip;

    @TranslatableMessage
    public static String CustomTreeSelectionDialog_expandAllButtonTooltip;

    @TranslatableMessage
    public static String CustomTreeSelectionDialog_regexpExplanations;

    @TranslatableMessage
    public static String CustomTreeSelectionDialog_regexpTitle;

    @TranslatableMessage
    public static String CustomTreeSelectionDialog_regexpTooltip;

    @TranslatableMessage
    public static String CustomTreeSelectionDialog_showLabelText;

    @TranslatableMessage
    public static String CustomTreeSelectionDialog_uncheckAllButtonTooltip;

    @TranslatableMessage
    public static String DDiagramEditorImpl_error_representationRefresh;

    @TranslatableMessage
    public static String DDiagramEditorImpl_editorToBeClosedAndReopenedSinceContentIsNotAccessible;

    @TranslatableMessage
    public static String DDiagramEditorImpl_cdoServerConnectionPbMsg;

    @TranslatableMessage
    public static String DDiagramEditorImpl_noAssociatedGMFDiagramMsg;

    @TranslatableMessage
    public static String DDiagramEditorImpl_noSessionMsg;

    @TranslatableMessage
    public static String DDiagramEditorImpl_refreshJobInterruptedMsg;

    @TranslatableMessage
    public static String DDiagramEditorImpl_refreshJobLabel;

    @TranslatableMessage
    public static String DeactivateBehaviorToolsCommand_label;

    @TranslatableMessage
    public static String DeactivateFiltersCommand_label;

    @TranslatableMessage
    public static String DeactivateRulesCommand_label;

    @TranslatableMessage
    public static String DEdgeCreateCommand_executionErrorMsg;

    @TranslatableMessage
    public static String DEdgeLabelItemProvider_label;

    @TranslatableMessage
    public static String DefaultLayerName;

    @TranslatableMessage
    public static String DefaultTabbarContributorProvider_contributionError_withId;

    @TranslatableMessage
    public static String DefaultTabbarContributorProvider_contributionError;

    @TranslatableMessage
    public static String DeleteRelatedNoteAttachmentsTask_label;

    @TranslatableMessage
    public static String DeleteRelatedNotesTask_label;

    @TranslatableMessage
    public static String DeselectAllAction_label;

    @TranslatableMessage
    public static String DeselectAllAction_tooltip;

    @TranslatableMessage
    public static String DiagramAppearancePreferencePage_authorizeOverlapGroupText;

    @TranslatableMessage
    public static String DiagramAppearancePreferencePage_decorationGroupText;

    @TranslatableMessage
    public static String DiagramAppearancePreferencePage_displayHeaderGroupText;

    @TranslatableMessage
    public static String DiagramAppearancePreferencePage_displayHeaderLabel;

    @TranslatableMessage
    public static String DiagramAppearancePreferencePage_hideConnectorLabelIconsLabel;

    @TranslatableMessage
    public static String DiagramAppearancePreferencePage_hideShapeLabelIconsLabel;

    @TranslatableMessage
    public static String DiagramAppearancePreferencePage_labelIconsGroupText;

    @TranslatableMessage
    public static String DiagramAppearancePreferencePage_displayUserFixedColorsInPaletteLabel;

    @TranslatableMessage
    public static String DiagramConnectionsPreferencePage_defaultValuesGroup_title;

    @TranslatableMessage
    public static String DiagramConnectionsPreferencePage_enableLineStyleOverride_label;

    @TranslatableMessage
    public static String DiagramConnectionsPreferencePage_showEdgeLabelLinkOnSelect;

    @TranslatableMessage
    public static String DiagramDialectEditorDialogFactory_forbiddenOperation;

    @TranslatableMessage
    public static String DiagramDialectUIServices_diagramDescription;

    @TranslatableMessage
    public static String DiagramDialectUIServices_diagramEditorClosingError;

    @TranslatableMessage
    public static String DiagramDialectUIServices_diagramEditorOpeningError;

    @TranslatableMessage
    public static String DiagramDialectUIServices_diagramEditorOpeningMonitorTaskName;

    @TranslatableMessage
    public static String DiagramDialectUIServices_diagramEditPartDeactivationError;

    @TranslatableMessage
    public static String DiagramDialectUIServices_diagramOpeningMonitorTaskName;

    @TranslatableMessage
    public static String DiagramDialectUIServices_exportedDiagramImageCreationError;

    @TranslatableMessage
    public static String DiagramDialectUIServices_exportedDiagramImageClassCastError;

    @TranslatableMessage
    public static String DiagramDialectUIServices_refreshDiagram;

    @TranslatableMessage
    public static String DiagramDialectUIServices_representationWithEmptyNameEditorName;

    @TranslatableMessage
    public static String DiagramDialectUIServices_requiredViewpointsDialogMessage;

    @TranslatableMessage
    public static String DiagramDialectUIServices_requiredViewpointsDialogTitle;

    @TranslatableMessage
    public static String DiagramDialectUIServices_sourcePreDescription;

    @TranslatableMessage
    public static String DiagramDialectUIServices_sourceViewPreDescription;

    @TranslatableMessage
    public static String DiagramDialectUIServices_targetPreDescription;

    @TranslatableMessage
    public static String DiagramDialectUIServices_targetViewPreDescription;

    @TranslatableMessage
    public static String DiagramEdgeEditPartOperation_unknownRountingStyle;

    @TranslatableMessage
    public static String DiagramEditorContextMenuProvider_arrangeMenuRenameError;

    @TranslatableMessage
    public static String DiagramEditorContextMenuProvider_arrangeMenuText;

    @TranslatableMessage
    public static String DiagramEditPartService_imageExportException;

    @TranslatableMessage
    public static String DiagramElementEditPartOperation_partDeactivationError;

    @TranslatableMessage
    public static String DiagramElementsSelectionDialog_grayedElementDialogMessage;

    @TranslatableMessage
    public static String DiagramGeneralPreferencePage_arrangeAndAutoSizeContainersLabel;

    @TranslatableMessage
    public static String DiagramGeneralPreferencePage_moveUnlinkedNodeLabel;

    @TranslatableMessage
    public static String DiagramGeneralPreferencePage_pinMovedElementsLabel;

    @TranslatableMessage
    public static String DiagramGeneralPreferencePage_removeHideNoteLabel;

    @TranslatableMessage
    public static String DiagramGeneralPreferencePage_showSynchronizeStatusDecoratorLabel;

    @TranslatableMessage
    public static String DiagramGeneralPreferencePage_sizeGroupLabel;

    @TranslatableMessage
    public static String DiagramGeneralPreferencePage_synchronizedModeLabel;

    @TranslatableMessage
    public static String DiagramOutlineWithBookPages_filtersTooltip;

    @TranslatableMessage
    public static String DiagramOutlineWithBookPages_layersTooltip;

    @TranslatableMessage
    public static String DiagramRepairParticipant_removeDiagramElementTaskName;

    @TranslatableMessage
    public static String DiagramPrintingPreferencePage_optionsGroupText;

    @TranslatableMessage
    public static String DiagramPrintingPreferencePage_printDecorations;

    @TranslatableMessage
    public static String DiagramRepairParticipant_restoreModelStateTaskName;

    @TranslatableMessage
    public static String DiagramRepairParticipant_saveModelStateTaskName;

    @TranslatableMessage
    public static String DiagramSelectionWizardPage_message;

    @TranslatableMessage
    public static String DiagramSelectionWizardPage_title;

    @TranslatableMessage
    public static String DiagramSelectionWizardPage_titleFor;

    @TranslatableMessage
    public static String DistributeAction_centersHorizontallyLabel;

    @TranslatableMessage
    public static String DistributeAction_centersVerticallyLabel;

    @TranslatableMessage
    public static String DistributeAction_distributeCentersHorizontallyLabel;

    @TranslatableMessage
    public static String DistributeAction_distributeCentersHorizontallyTooltip;

    @TranslatableMessage
    public static String DistributeAction_distributeCentersVerticallyLabel;

    @TranslatableMessage
    public static String DistributeAction_distributeCentersVerticallyTooltip;

    @TranslatableMessage
    public static String DistributeAction_distributeGapsHorizontallyLabel;

    @TranslatableMessage
    public static String DistributeAction_distributeGapsHorizontallyTooltip;

    @TranslatableMessage
    public static String DistributeAction_distributeGapsVerticallyLabel;

    @TranslatableMessage
    public static String DistributeAction_distributeGapsVerticallyTooltip;

    @TranslatableMessage
    public static String DistributeAction_gapsHorizontallyLabel;

    @TranslatableMessage
    public static String DistributeAction_gapsVerticallyLabel;

    @TranslatableMessage
    public static String DistributeCommand_errorMsg;

    @TranslatableMessage
    public static String DistributeMenuAction_text;

    @TranslatableMessage
    public static String DistributeMenuAction_tooltip;

    @TranslatableMessage
    public static String DNodeContainerViewNodeContainerCompartment2EditPart_title;

    @TranslatableMessage
    public static String DNodeContainerViewNodeContainerCompartmentEditPart_title;

    @TranslatableMessage
    public static String DNodeFormatDataKey_wrongKeyMsg;

    @TranslatableMessage
    public static String DNodeLayoutDataKey_wrongKeyMsg;

    @TranslatableMessage
    public static String DNodeListViewNodeListCompartment2EditPart_title;

    @TranslatableMessage
    public static String DNodeListViewNodeListCompartmentEditPart_title;

    @TranslatableMessage
    public static String DocumentationPropertySection_defaultLabel;

    @TranslatableMessage
    public static String DocumentationPropertySection_description;

    @TranslatableMessage
    public static String EclipseImageSelectorDescriptor_extensionLoadingError;

    @TranslatableMessage
    public static String EdgeGroupMoveMessage;

    @TranslatableMessage
    public static String EdgeReconnectionHelper_invalidReconnectionKind;

    @TranslatableMessage
    public static String EdgeRoutingStyleChangedCommand_label;

    @TranslatableMessage
    public static String EdgesZOrderMigrationParticipant_title;

    @TranslatableMessage
    public static String EdgesZOrderMigrationParticipant_edgesOrderChanged;

    @TranslatableMessage
    public static String EditPartTools_nullParameterMsg;

    @TranslatableMessage
    public static String ExtendedPropertyDescriptor_categoryName;

    @TranslatableMessage
    public static String ExtendedPropertyDescriptor_description;

    @TranslatableMessage
    public static String ExtendedPropertyDescriptor_errorGettingValueMsg;

    @TranslatableMessage
    public static String ExtendedPropertyDescriptor_errorRetrievingValueMsg;

    @TranslatableMessage
    public static String ExtendedPropertyDescriptor_unknownExtensionMsg;

    @TranslatableMessage
    public static String ExtendedPropertySource_errorSettingValueMsg;

    @TranslatableMessage
    public static String FilteringMode_allElements;

    @TranslatableMessage
    public static String FilteringMode_onlyCheckedElements;

    @TranslatableMessage
    public static String FilteringMode_onlyUncheckedElements;

    @TranslatableMessage
    public static String FiltersContributionItem_label;

    @TranslatableMessage
    public static String FiltersPropertySection_addButtonLabel;

    @TranslatableMessage
    public static String FiltersPropertySection_appliedFiltersLabel;

    @TranslatableMessage
    public static String FiltersPropertySection_availableFiltersLabel;

    @TranslatableMessage
    public static String FiltersPropertySection_removeButtonLabel;

    @TranslatableMessage
    public static String FiltersTableViewer_columnName;

    @TranslatableMessage
    public static String FontPropertySection_strikeThrough;

    @TranslatableMessage
    public static String FontPropertySection_underline;

    @TranslatableMessage
    public static String FormatDataHelperImpl_unkownFormatData;

    @TranslatableMessage
    public static String GenericConnectionCreationTool_label;

    @TranslatableMessage
    public static String GMFCommandWrapper_label;

    @TranslatableMessage
    public static String GMFCommandWrapper_nullCommand;

    @TranslatableMessage
    public static String GMFCommandWrapper_nullDomain;

    @TranslatableMessage
    public static String GMFHelper_invalidEdgeModelAndFigure;

    @TranslatableMessage
    public static String GMFNotationUtilities_edgeOnEdgeNotManaged;

    @TranslatableMessage
    public static String GridLayoutProvider_unknownMode;

    @TranslatableMessage
    public static String Group_Not_Displayed;

    @TranslatableMessage
    public static String Group_No_Menu_ID;

    @TranslatableMessage
    public static String HiddenElementsSelectionCommand_dialogMessage;

    @TranslatableMessage
    public static String HiddenElementsSelectionCommand_dialogTitle;

    @TranslatableMessage
    public static String IAbstractDiagramNodeEditPart_createViewCommandLabel;

    @TranslatableMessage
    public static String IAbstractDiagramNodeEditPart_resizeCommandLabel;

    @TranslatableMessage
    public static String IBorderItemLocatorWrapper_nullLocator;

    @TranslatableMessage
    public static String Identifier_invalidNullObject;

    @TranslatableMessage
    public static String IDiagramOutlinePage_outlineTooltip;

    @TranslatableMessage
    public static String IDiagramOutlinePage_overviewTooltip;

    @TranslatableMessage
    public static String InitializeHiddenElementsCommand_label;

    @TranslatableMessage
    public static String InitializeLayoutCommand_label;

    @TranslatableMessage
    public static String InsertBlankSpace_cmdName;

    @TranslatableMessage
    public static String ItemProvider_elementBasedEdge;

    @TranslatableMessage
    public static String ItemProvider_foregroundBackgroundLabel;

    @TranslatableMessage
    public static String ItemProvider_relationBasedEdge;

    @TranslatableMessage
    public static String LabelOnBorderMigrationParticipant_labelsModified;

    @TranslatableMessage
    public static String LabelOnBorderMigrationParticipant_title;

    @TranslatableMessage
    public static String LaunchBehaviorContributionItem_launchBehaviorButtonLabel;

    @TranslatableMessage
    public static String LaunchBehaviorToolAction_label;

    @TranslatableMessage
    public static String LayersCellModifier_layerSelectionChangesTaskName;

    @TranslatableMessage
    public static String LayersContribution_label;

    @TranslatableMessage
    public static String LayersTableViewer_columnName;

    @TranslatableMessage
    public static String LayoutData_illegalTarget;

    @TranslatableMessage
    public static String LayoutDataHelperImpl_unkownLayoutData;

    @TranslatableMessage
    public static String LayoutingModeSwitchingAction_label;

    @TranslatableMessage
    public static String LocationURI_ParsePb_Blank;

    @TranslatableMessage
    public static String LocationURI_ParsePb_MoreThanTwoLocations;

    @TranslatableMessage
    public static String LocationURI_ParsePb_NoId;

    @TranslatableMessage
    public static String LocationURI_ParsePb_OnlyOneLocationURIPerScheme;

    @TranslatableMessage
    public static String LocationURI_ParsePb_WrongFormat;

    @TranslatableMessage
    public static String LocationURI_ParsePb_WrongScheme;

    @TranslatableMessage
    public static String ShowingModeSwitchingAction_label;

    @TranslatableMessage
    public static String ShowingModeSwitchingAction_statusOn;

    @TranslatableMessage
    public static String LayoutingModeSwitchingAction_statusOn;

    @TranslatableMessage
    public static String LayoutProviderDescriptor_initializationErrorMsg;

    @TranslatableMessage
    public static String MarkerObserver_validationMarkerFailureMsg;

    @TranslatableMessage
    public static String MessageFormatParser_InvalidInputError;

    @TranslatableMessage
    public static String MessageFormatParser_ProxyOrNullSemanticTargetMessage;

    @TranslatableMessage
    public static String MessageFormatParser_ProxyOrNullTargetMessage;

    @TranslatableMessage
    public static String MiscPropertySection_nullObject;

    @TranslatableMessage
    public static String MiscPropertySource_errorGettingValueMsg;

    @TranslatableMessage
    public static String ModelElementSelectionPageMessage;

    @TranslatableMessage
    public static String MoveViewOperation_nullMoveDeltaError;

    @TranslatableMessage
    public static String MoveViewOperation_nullViewError;

    @TranslatableMessage
    public static String NavigationItemProvider_labelWithDescriptionName;

    @TranslatableMessage
    public static String NavigatorActionProvider_OpenDiagramActionName;

    @TranslatableMessage
    public static String NavigatorGroupName_DDiagram_1000_links;

    @TranslatableMessage
    public static String NavigatorGroupName_DEdge_4001_source;

    @TranslatableMessage
    public static String NavigatorGroupName_DEdge_4001_target;

    @TranslatableMessage
    public static String NavigatorGroupName_DNode_2001_incominglinks;

    @TranslatableMessage
    public static String NavigatorGroupName_DNode_2001_outgoinglinks;

    @TranslatableMessage
    public static String NavigatorGroupName_DNode_3001_incominglinks;

    @TranslatableMessage
    public static String NavigatorGroupName_DNode_3001_outgoinglinks;

    @TranslatableMessage
    public static String NavigatorGroupName_DNode_3007_incominglinks;

    @TranslatableMessage
    public static String NavigatorGroupName_DNode_3007_outgoinglinks;

    @TranslatableMessage
    public static String NavigatorGroupName_DNode_3012_incominglinks;

    @TranslatableMessage
    public static String NavigatorGroupName_DNode_3012_outgoinglinks;

    @TranslatableMessage
    public static String NavigatorGroupName_DNodeContainer_2002_incominglinks;

    @TranslatableMessage
    public static String NavigatorGroupName_DNodeContainer_2002_outgoinglinks;

    @TranslatableMessage
    public static String NavigatorGroupName_DNodeContainer_3008_incominglinks;

    @TranslatableMessage
    public static String NavigatorGroupName_DNodeContainer_3008_outgoinglinks;

    @TranslatableMessage
    public static String NavigatorGroupName_DNodeList_2003_incominglinks;

    @TranslatableMessage
    public static String NavigatorGroupName_DNodeList_2003_outgoinglinks;

    @TranslatableMessage
    public static String NavigatorGroupName_DNodeList_3009_incominglinks;

    @TranslatableMessage
    public static String NavigatorGroupName_DNodeList_3009_outgoinglinks;

    @TranslatableMessage
    public static String NodeDeletionEditPolicy_deleteElementCommandLabel;

    @TranslatableMessage
    public static String NodeMappingItemProvider_borderedLabel;

    @TranslatableMessage
    public static String OpenDiagramCommand_creationErrorMsg;

    @TranslatableMessage
    public static String OpenDiagramCommand_exceptionMsg;

    @TranslatableMessage
    public static String OpenDiagramCommand_notDiagramElementErrorMsg;

    @TranslatableMessage
    public static String OpenDiagramCommand_saveFailedMsg;

    @TranslatableMessage
    public static String OpenMenuContribution_menuLabel;

    @TranslatableMessage
    public static String PaletteImageProvider_noIconFor;

    @TranslatableMessage
    public static String PaletteManagerImpl_alreadyExistingEntry;

    @TranslatableMessage
    public static String PaletteManagerImpl_severalCandidatesInPalette;

    @TranslatableMessage
    public static String PasteFromSiriusClipboardCommand_label;

    @TranslatableMessage
    public static String PasteFormatAction_commandLabel;

    @TranslatableMessage
    public static String PasteFormatAction_restoreFormatCommandLabel;

    @TranslatableMessage
    public static String PasteFormatAction_text;

    @TranslatableMessage
    public static String PasteFormatAction_toolTipText;

    @TranslatableMessage
    public static String PasteFormatDataCommand_label;

    @TranslatableMessage
    public static String PasteLayoutAction_commandLabel;

    @TranslatableMessage
    public static String PasteLayoutAction_restoreLayoutCommandLabel;

    @TranslatableMessage
    public static String PasteLayoutAction_text;

    @TranslatableMessage
    public static String PasteLayoutAction_toolTipText;

    @TranslatableMessage
    public static String PasteLayoutDataCommand_label;

    @TranslatableMessage
    public static String PasteStyleAction_commandLabel;

    @TranslatableMessage
    public static String PasteStyleAction_restoreStyleCommandLabel;

    @TranslatableMessage
    public static String PasteStyleAction_text;

    @TranslatableMessage
    public static String PasteStyleAction_toolTipText;

    @TranslatableMessage
    public static String PasteStyleDataCommand_label;

    @TranslatableMessage
    public static String PinElementsEclipseAction_text;

    @TranslatableMessage
    public static String PinnedElementsHandler_notMovableMsg;

    @TranslatableMessage
    public static String PinnedElementsHandler_remainOverlapsMsg;

    @TranslatableMessage
    public static String PinnedElementsHandler_unknownDirection;

    @TranslatableMessage
    public static String PinnedElementsSelectionCommand_dialogMessage;

    @TranslatableMessage
    public static String PopupMenuContribution_storeMouseLocationCmdLabel;

    @TranslatableMessage
    public static String RefreshDiagramAction_cancelled;

    @TranslatableMessage
    public static String RefreshDiagramAction_error;

    @TranslatableMessage
    public static String RefreshDiagramAction_refreshDiagramError;

    @TranslatableMessage
    public static String RefreshDiagramOnOpeningCommand_label;

    @TranslatableMessage
    public static String RefreshRunnableWithProgress_commandLabel;

    @TranslatableMessage
    public static String RefreshRunnableWithProgress_taskName;

    @TranslatableMessage
    public static String RegionCollapseAwarePropertyHandlerEditPolicy_collapseRegionCommandLabel;

    @TranslatableMessage
    public static String RegionCollapseAwarePropertyHandlerEditPolicy_expandRegionCommandLabel;

    @TranslatableMessage
    public static String RegionCollapseAwarePropertyHandlerEditPolicy_gmfSizeUpdateCommandLabel;

    @TranslatableMessage
    public static String RegionContainerResizableEditPolicy_regionContainerAutoSizeCommandLabel;

    @TranslatableMessage
    public static String RegionContainerUpdateLayoutOperation_name;

    @TranslatableMessage
    public static String RegionResizableEditPolicy_regionAutoSizeCommandLabel;

    @TranslatableMessage
    public static String RemoveBendpointsHandler_cmdLabel;

    @TranslatableMessage
    public static String RemoveInvalidViewsCommand_label;

    @TranslatableMessage
    public static String RepairEdgesWithOneBendpointMigrationParticipant_edgesModified;

    @TranslatableMessage
    public static String RepairEdgesWithOneBendpointMigrationParticipant_title;

    @TranslatableMessage
    public static String RepairGMFbendpointsMigrationParticipant_edgesModified;

    @TranslatableMessage
    public static String RepairGMFbendpointsMigrationParticipant_title;

    @TranslatableMessage
    public static String RepresentationLinkMigrationParticipant_title;

    @TranslatableMessage
    public static String RepresentationLinkMigrationParticipant_entry;

    @TranslatableMessage
    public static String DeleteMultipleConnectorMigrationParticipant_title;

    @TranslatableMessage
    public static String DeleteMultipleConnectorMigrationParticipant_edgesModified;

    @TranslatableMessage
    public static String ResetOriginChangeModelOperation_name;

    @TranslatableMessage
    public static String ResetOriginChangeModelOperation_nameOnContainer;

    @TranslatableMessage
    public static String ResetOriginChangeModelOperation_nameOnDiagram;

    @TranslatableMessage
    public static String ResetStylePropertiesToDefaultValuesAction_text;

    @TranslatableMessage
    public static String ResizeImageCommand_label;

    @TranslatableMessage
    public static String ResourceMissingDocumentProvider_defaultMessage;

    @TranslatableMessage
    public static String RevealOutlineElementsAction_label;

    @TranslatableMessage
    public static String RevealOutlineLabelsAction_label;

    @TranslatableMessage
    public static String SafeStyleConfiguration_customStyleInvocationError;

    @TranslatableMessage
    public static String SaveAsImageFileAction_label;

    @TranslatableMessage
    public static String SelectDiagramElementBackgroundImageDialog_browseButtonText;

    @TranslatableMessage
    public static String SelectDiagramElementBackgroundImageDialog_pathLabelText;

    @TranslatableMessage
    public static String SelectDiagramElementBackgroundImageDialog_title;

    @TranslatableMessage
    public static String SelectHiddenElementsAction_tooltip;

    @TranslatableMessage
    public static String SelectionWizardCommand_cancelExceptionMsg;

    @TranslatableMessage
    public static String SelectPinnedElementsAction_label;

    @TranslatableMessage
    public static String SelectPinnedElementsAction_tooltip;

    @TranslatableMessage
    public static String SetBestHeightHeaderCommand_label;

    @TranslatableMessage
    public static String SetConnectionBendpointsAccordingToExtremityMoveCommmand_sourceSidedLabel;

    @TranslatableMessage
    public static String SetConnectionBendpointsAccordingToExtremityMoveCommmand_targetSidedLabel;

    @TranslatableMessage
    public static String SetCurrentConcernCommand_label;

    @TranslatableMessage
    public static String SetDefaultConcernCommand_label;

    @TranslatableMessage
    public static String SetLabelsOffsetCommmand_label;

    @TranslatableMessage
    public static String SetLayoutingModeCommand_activateLabel;

    @TranslatableMessage
    public static String SetLayoutingModeCommand_deactivateLabel;

    @TranslatableMessage
    public static String SetLayoutingModeCommandAndUpdateActionImage_activateLabel;

    @TranslatableMessage
    public static String SetShowingModeCommandAndUpdateActionImage_deactivateLabel;

    @TranslatableMessage
    public static String SetShowingModeCommandAndUpdateActionImage_activateLabel;

    @TranslatableMessage
    public static String SetLayoutingModeCommandAndUpdateActionImage_deactivateLabel;

    @TranslatableMessage
    public static String SetStyleToWorkspaceImageAction_text;

    @TranslatableMessage
    public static String ShiftDirectBorderedNodesOperation_name;

    @TranslatableMessage
    public static String SimpleImageTranscoder_svgImageTranscodingError;

    @TranslatableMessage
    public static String SimpleStyleConfiguration_iconFileNotFound;

    @TranslatableMessage
    public static String SiriusBaseItemSemanticEditPolicy_deleteCmdLabel;

    @TranslatableMessage
    public static String SiriusBaseItemSemanticEditPolicy_deleteFromDiagramCmdLabel;

    @TranslatableMessage
    public static String SiriusCanonicalLayoutCommand_label;

    @TranslatableMessage
    public static String SiriusClipboardGlobalActionHandler_addLayoutDataCommandLabel;

    @TranslatableMessage
    public static String SiriusClipboardGlobalActionHandler_pasteCommandLabel;

    @TranslatableMessage
    public static String SiriusClipboardGlobalActionHandler_severalFoundPasteToolError;

    @TranslatableMessage
    public static String SiriusContainerDropPolicy_dropElementsCommandLabel;

    @TranslatableMessage
    public static String SiriusContainerDropPolicy_saveEditPartLayoutCommandLabel;

    @TranslatableMessage
    public static String SiriusCopyAppearancePropertiesAction_tooltipMessage;

    @TranslatableMessage
    public static String SiriusCreationWizard_DiagramModelFilePageDescription;

    @TranslatableMessage
    public static String SiriusCreationWizard_DiagramModelFilePageTitle;

    @TranslatableMessage
    public static String SiriusCreationWizard_DomainModelFilePageDescription;

    @TranslatableMessage
    public static String SiriusCreationWizard_DomainModelFilePageTitle;

    @TranslatableMessage
    public static String SiriusCreationWizardCreationError;

    @TranslatableMessage
    public static String SiriusCreationWizardOpenEditorError;

    @TranslatableMessage
    public static String SiriusCreationWizardPageExtensionError;

    @TranslatableMessage
    public static String SiriusCreationWizardTitle;

    @TranslatableMessage
    public static String SiriusDiagramActionBarContributor_deleteFromDiagram;

    @TranslatableMessage
    public static String SiriusDiagramActionBarContributor_deleteFromModel;

    @TranslatableMessage
    public static String SiriusDiagramActionBarContributor_hideElement;

    @TranslatableMessage
    public static String SiriusDiagramActionBarContributor_showElement;

    @TranslatableMessage
    public static String SiriusDiagramActionBarContributor_hideLabel;

    @TranslatableMessage
    public static String SiriusDiagramActionBarContributor_launchBehavior;

    @TranslatableMessage
    public static String SiriusDiagramActionBarContributor_refreshDiagram;

    @TranslatableMessage
    public static String SiriusDiagramActionBarContributor_revealElement;

    @TranslatableMessage
    public static String SiriusDiagramEditor_SaveAsErrorMessage;

    @TranslatableMessage
    public static String SiriusDiagramEditor_SaveAsErrorTitle;

    @TranslatableMessage
    public static String SiriusDiagramEditor_SaveErrorMessage;

    @TranslatableMessage
    public static String SiriusDiagramEditor_SaveErrorTitle;

    @TranslatableMessage
    public static String SiriusDiagramEditor_SavingDeletedFile;

    @TranslatableMessage
    public static String SiriusDiagramEditorUtil_charsetError;

    @TranslatableMessage
    public static String SiriusDiagramEditorUtil_CreateDiagramCommandLabel;

    @TranslatableMessage
    public static String SiriusDiagramEditorUtil_CreateDiagramProgressTask;

    @TranslatableMessage
    public static String SiriusDiagramEditorUtil_defaultFileName;

    @TranslatableMessage
    public static String SiriusDiagramEditorUtil_modelAndDiagramCreationError;

    @TranslatableMessage
    public static String SiriusDiagramEditorUtil_modelAndDiagramResourceSaveError;

    @TranslatableMessage
    public static String SiriusDiagramEditorUtil_OpenModelResourceErrorDialogMessage;

    @TranslatableMessage
    public static String SiriusDiagramEditorUtil_OpenModelResourceErrorDialogTitle;

    @TranslatableMessage
    public static String SiriusDiagramGraphicalViewer_tooltipDisplayDelay;

    @TranslatableMessage
    public static String SiriusDiagramSelectionCheckStateListener_errorMsg;

    @TranslatableMessage
    public static String SiriusDiagramSelectionCheckStateListener_unknwonCodeResult;

    @TranslatableMessage
    public static String SiriusDocumentProvider_DiagramLoadingError;

    @TranslatableMessage
    public static String SiriusDocumentProvider_handleElementContentChanged;

    @TranslatableMessage
    public static String SiriusDocumentProvider_IncorrectInputError;

    @TranslatableMessage
    public static String SiriusDocumentProvider_isModifiable;

    @TranslatableMessage
    public static String SiriusDocumentProvider_NoDiagramInResourceError;

    @TranslatableMessage
    public static String SiriusDocumentProvider_SaveAsOperation;

    @TranslatableMessage
    public static String SiriusDocumentProvider_SaveDiagramTask;

    @TranslatableMessage
    public static String SiriusDocumentProvider_SaveNextResourceTask;

    @TranslatableMessage
    public static String SiriusDocumentProvider_UnsynchronizedFileSaveError;

    @TranslatableMessage
    public static String SiriusEdgeSnapBackAction_EdgeSnapBackActionToolTipText;

    @TranslatableMessage
    public static String SiriusEdgeSnapBackAction_EdgeSnapBackLabel;

    @TranslatableMessage
    public static String SiriusEdgeSnapBackAction_EdgeSnapBackCommandLabel;

    @TranslatableMessage
    public static String SiriusElementChooserDialog_SelectModelElementTitle;

    @TranslatableMessage
    public static String SiriusEnhancedPrintActionHelper_invalidIworkbenchPart;

    @TranslatableMessage
    public static String SiriusInitDiagramFileAction_InitDiagramFileResourceErrorDialogMessage;

    @TranslatableMessage
    public static String SiriusInitDiagramFileAction_InitDiagramFileResourceErrorDialogTitle;

    @TranslatableMessage
    public static String SiriusInitDiagramFileAction_InitDiagramFileWizardTitle;

    @TranslatableMessage
    public static String SiriusInitDiagramFileAction_loadResourceError;

    @TranslatableMessage
    public static String SiriusInitDiagramFileAction_OpenModelFileDialogTitle;

    @TranslatableMessage
    public static String SiriusLayoutDataManagerImpl_addCenterLayoutMarkerCommandLabel;

    @TranslatableMessage
    public static String SiriusLayoutDataManagerImpl_addLayoutMarkerCommandLabel;

    @TranslatableMessage
    public static String SiriusLayoutDataManagerImpl_addLayoutMarkerOnOpeningCommandLabel;

    @TranslatableMessage
    public static String SiriusLayoutDataManagerImpl_createdViewsArrangCommandLabel;

    @TranslatableMessage
    public static String SiriusLayoutDataManagerImpl_unhandledContainerKind;

    @TranslatableMessage
    public static String SiriusMarkerNavigationProvider_validationMarkerCreationError;

    @TranslatableMessage
    public static String SiriusModelingAssistantProviderMessage;

    @TranslatableMessage
    public static String SiriusModelingAssistantProviderTitle;

    @TranslatableMessage
    public static String SiriusNewDiagramFileWizard_CreationPageDescription;

    @TranslatableMessage
    public static String SiriusNewDiagramFileWizard_CreationPageName;

    @TranslatableMessage
    public static String SiriusNewDiagramFileWizard_CreationPageTitle;

    @TranslatableMessage
    public static String SiriusNewDiagramFileWizard_errorDuringCreation;

    @TranslatableMessage
    public static String SiriusNewDiagramFileWizard_errorDuringOpening;

    @TranslatableMessage
    public static String SiriusNewDiagramFileWizard_IncorrectRootError;

    @TranslatableMessage
    public static String SiriusNewDiagramFileWizard_InitDiagramCommand;

    @TranslatableMessage
    public static String SiriusNewDiagramFileWizard_invalidDiagramRootElement;

    @TranslatableMessage
    public static String SiriusNewDiagramFileWizard_invalidDomainModelURI;

    @TranslatableMessage
    public static String SiriusNewDiagramFileWizard_invalidEditingDomain;

    @TranslatableMessage
    public static String SiriusNewDiagramFileWizard_RootSelectionPageDescription;

    @TranslatableMessage
    public static String SiriusNewDiagramFileWizard_RootSelectionPageInvalidSelectionMessage;

    @TranslatableMessage
    public static String SiriusNewDiagramFileWizard_RootSelectionPageName;

    @TranslatableMessage
    public static String SiriusNewDiagramFileWizard_RootSelectionPageNoSelectionMessage;

    @TranslatableMessage
    public static String SiriusNewDiagramFileWizard_RootSelectionPageSelectionTitle;

    @TranslatableMessage
    public static String SiriusNewDiagramFileWizard_RootSelectionPageTitle;

    @TranslatableMessage
    public static String SiriusNewDiagramFileWizard_saveFailed;

    @TranslatableMessage
    public static String SiriusNewDiagramFileWizard_unsupportedURI;

    @TranslatableMessage
    public static String SiriusPropertyHandlerEditPolicy_applyAppearancePropertiesCommandLabel;

    @TranslatableMessage
    public static String SiriusPropertyHandlerEditPolicy_applyTabbarPropertiesCommandLabel;

    @TranslatableMessage
    public static String SiriusPropertyHandlerEditPolicy_chainedStyleCommandDebugLabel;

    @TranslatableMessage
    public static String SiriusReorientConnectionViewCommand_nullChild;

    @TranslatableMessage
    public static String SiriusReorientConnectionViewCommand_nullEdge;

    @TranslatableMessage
    public static String SiriusStatusLineContributionItemProvider_diagramSynchronized;

    @TranslatableMessage
    public static String SiriusStatusLineContributionItemProvider_diagramUnsynchronized;

    @TranslatableMessage
    public static String SiriusSWTRenderedDiagramPrinter_jobLabel;

    @TranslatableMessage
    public static String SiriusSWTRenderedDiagramPrinter_printerNotSetMsg;

    @TranslatableMessage
    public static String SiriusValidationDecoratorProvider_refreshFailureMsg;

    @TranslatableMessage
    public static String SiriusValidationProvider_validationFailed;

    @TranslatableMessage
    public static String SiriusVisualIDRegistry_parseError;

    @TranslatableMessage
    public static String SnapBackDistantLabelsMigrationParticipant_oneLabelSnapBacked;

    @TranslatableMessage
    public static String SnapBackDistantLabelsMigrationParticipant_nodesMoved;

    @TranslatableMessage
    public static String SnapBackDistantLabelsMigrationParticipant_severalLabelsSnapBacked;

    @TranslatableMessage
    public static String SnapBackDistantLabelsMigrationParticipant_title;

    @TranslatableMessage
    public static String SpecificationMenuContribution_openDefinitionMenuLabel;

    @TranslatableMessage
    public static String StandardDiagramServices_sameETypeTitle;

    @TranslatableMessage
    public static String StandardDiagramServices_sameETypeMessage;

    @TranslatableMessage
    public static String StandardDiagramServices_sameETypeTooltip;

    @TranslatableMessage
    public static String StandardDiagramServices_expressionTitle;

    @TranslatableMessage
    public static String StandardDiagramServices_expressionMessage;

    @TranslatableMessage
    public static String StandardDiagramServices_expressionTooltip;

    @TranslatableMessage
    public static String StatusDecorator_validationMarkersFailureMsg;

    @TranslatableMessage
    public static String StatusDecorator_viewIdAccessFailureMsg;

    @TranslatableMessage
    public static String StraightenToAction_commandLabel;

    @TranslatableMessage
    public static String StraightenToAction_toBottomLabel;

    @TranslatableMessage
    public static String StraightenToAction_toBottomTooltip;

    @TranslatableMessage
    public static String StraightenToAction_toLeftLabel;

    @TranslatableMessage
    public static String StraightenToAction_toLeftTooltip;

    @TranslatableMessage
    public static String StraightenToAction_toRightLabel;

    @TranslatableMessage
    public static String StraightenToAction_toRightTooltip;

    @TranslatableMessage
    public static String StraightenToAction_toTopLabel;

    @TranslatableMessage
    public static String StraightenToAction_toTopTooltip;

    @TranslatableMessage
    public static String StraightenToAction_LeftSidePinnedLabel;

    @TranslatableMessage
    public static String StraightenToAction_LeftSidePinnedTooltip;

    @TranslatableMessage
    public static String StraightenToAction_RightSidePinnedLabel;

    @TranslatableMessage
    public static String StraightenToAction_RightSidePinnedTooltip;

    @TranslatableMessage
    public static String StraightenToAction_TopSidePinnedLabel;

    @TranslatableMessage
    public static String StraightenToAction_TopSidePinnedTooltip;

    @TranslatableMessage
    public static String StraightenToAction_BottomSidePinnedLabel;

    @TranslatableMessage
    public static String StraightenToAction_BottomSidePinnedTooltip;

    @TranslatableMessage
    public static String StraightenToCommand_errorMsg;

    @TranslatableMessage
    public static String StraightenToMenuAction_text;

    @TranslatableMessage
    public static String StraightenToMenuAction_tooltip;

    @TranslatableMessage
    public static String StyleConfigurationRegistry_disableStyleConfigurationProviderInError;

    @TranslatableMessage
    public static String StyleConfigurationRegistry_profilerTaskName;

    @TranslatableMessage
    public static String StyleConfigurationRegistry_styleConfigurationProviderLoadError;

    @TranslatableMessage
    public static String SubDiagramDecorator_taskName;

    @TranslatableMessage
    public static String SubDiagramMenu_newLabel;

    @TranslatableMessage
    public static String SVGFigure_usingInvalidBundledImageShape;

    @TranslatableMessage
    public static String SVGFigure_loadError;

    @TranslatableMessage
    public static String SynchronizedDiagramCommand_unsynchronizedLabel;

    @TranslatableMessage
    public static String SynchronizeStatusFigure_diagSynchronized;

    @TranslatableMessage
    public static String SynchronizeStatusFigure_diagUnsynchronized;

    @TranslatableMessage
    public static String ToggleFoldingStateCommand_label;

    @TranslatableMessage
    public static String ToggleUpdateManager_fieldAccessError;

    @TranslatableMessage
    public static String TreeLayoutSetConnectionAnchorsCommand_nullChildInSetConnectionAnchorsCommand;

    @TranslatableMessage
    public static String TreeLayoutSetConnectionAnchorsCommand_nullEdgeInSetConnectionAnchorsCommand;

    @TranslatableMessage
    public static String UnpinElementsEclipseAction_text;

    @TranslatableMessage
    public static String UpdateGMFEdgeStyleCommand_label;

    @TranslatableMessage
    public static String UpdateVisibilityCommand_label;

    @TranslatableMessage
    public static String ValidateAction_failureMessage;

    @TranslatableMessage
    public static String ValidateActionMessage;

    @TranslatableMessage
    public static String ValidationFixResolution_editorOpeningError;

    @TranslatableMessage
    public static String ValidationPropertySection_activatedRulesLabel;

    @TranslatableMessage
    public static String ValidationPropertySection_availableRulesLabel;

    @TranslatableMessage
    public static String ViewOrderingProviderRegistry_viewOrderingProvider_loadingProblem;

    @TranslatableMessage
    public static String VisibilityUpdateCommand_label;

    @TranslatableMessage
    public static String WorkspaceImageChangeDetector_invalidUri;

    @TranslatableMessage
    public static String WorkspaceImageFigureRefresher_imageDescriptorUpdateError;

    @TranslatableMessage
    public static String WorkspaceImagePathSelector_dialogMessage;

    @TranslatableMessage
    public static String WorkspaceImagePathSelector_dialogTitle;

    @TranslatableMessage
    public static String WorkspacePathValidator_invalidPahtStatusMessage;

    @TranslatableMessage
    public static String WorkspacePathValidator_invalidPathDecorationDescriptionText;

    @TranslatableMessage
    public static String ResetToDefaultFiltersAction_text;

    @TranslatableMessage
    public static String ResetToDefaultFiltersAction_tooltip;

    @TranslatableMessage
    public static String LayoutAlgorithmProviderRegistry_badClassType;

    @TranslatableMessage
    public static String LayoutAlgorithmProviderRegistry_classInitialization;

    @TranslatableMessage
    public static String DoubleClickEditPolicy_filterConfirmDialogBody;

    @TranslatableMessage
    public static String DoubleClickEditPolicy_layerConfirmDialogBody;

    @TranslatableMessage
    public static String DoubleClickEditPolicy_confirmDialogTitle;

    @TranslatableMessage
    public static String DefaultModeAction_statusOn;

    @TranslatableMessage
    public static String DefaultModeAction_Label;

    @TranslatableMessage
    public static String EditModeAction_Label;

    @TranslatableMessage
    public static String DoubleClickEditPolicy_confirmDialogAsking;

    @TranslatableMessage
    public static String UndoRedoCapableEMFCommandFactory_insertHorizontalBlankSpaceNotImplemented;

    @TranslatableMessage
    public static String DDiagramEditorImpl_updateToolFailure;

    @TranslatableMessage
    public static String ChangeEditModeAction_ChangePropertyValueRequest_label;

    @TranslatableMessage
    public static String ChangeEditModeAction_ChangeFailure;

    @TranslatableMessage
    public static String RemoveBlankSpace_cmdName;

    @TranslatableMessage
    public static String MappingBasedDiagramContentDuplicationSwitch_ImpossibleToFindBestMapping;

    @TranslatableMessage
    public static String MappingBasedSiriusFormatManagerFactory_ImpossibleToCopyNoteInNonExistingOrUnreachableTarget;

    @TranslatableMessage
    public static String MappingBasedSiriusFormatManagerFactory_ImpossibleToResolveOtherBoundTargetNote;

    @TranslatableMessage
    public static String MappingBasedSiriusFormatManagerFactory_ImpossibleToFindTargetTextNoteContainer;

    @TranslatableMessage
    public static String MappingBasedDiagramContentDuplicationSwitch_ErrorImpossibleToCreateNodeFromNodeCandidate;

    @TranslatableMessage
    public static String MappingBasedDiagramContentDuplicationSwitch_ErrorImpossibleToCreateEdgeFromEdgeCandidate;

    @TranslatableMessage
    public static String MappingBasedSiriusFormatManagerFactory_ErrorMappingfunctionIncompleteOnSequenceDiagram;

    @TranslatableMessage
    public static String MappingBasedSiriusFormatManagerFactory_ErrorMappingfunctionIsEmpty;

    @TranslatableMessage
    public static String MappingBasedSiriusFormatManagerFactory_ErrorDiagramIsNull;

    @TranslatableMessage
    public static String MappingBasedSiriusFormatManagerFactory_ErrorTargetDiagramNameIsEmpty;

    @TranslatableMessage
    public static String MappingBasedSiriusFormatManagerFactory_ErrorSourceAndTargetDiagramDecriptionsDoesNotMatch;

    @TranslatableMessage
    public static String MappingBasedSiriusFormatManagerFactory_ErrorSourceAndTargetDiagramsAreTheSame;

    @TranslatableMessage
    public static String MappingBasedSiriusFormatManagerFactory_ErrorSourceAndOrTargetSessionsNull;

    @TranslatableMessage
    public static String MappingBasedSiriusFormatManagerFactory_ErrorTargetDiagramRootIsNull;

    @TranslatableMessage
    public static String MappingBasedSiriusFormatManagerFactory_ImpossibleToSuitableDescription;

    // CHECKSTYLE:ON
    private Messages() {
        // Prevents instanciation.
    }
}

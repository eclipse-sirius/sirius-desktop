/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
package org.eclipse.sirius.common.tools;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 * 
 * @author pcdavid
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, DslCommonPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF

    @TranslatableMessage
    public static String BundleClassLoading_ignoredEPackageDeclaration;

    @TranslatableMessage
    public static String ClassLoadingService_multipleOverridesDetected;

    @TranslatableMessage
    public static String CompoundInterpreter_impossibleToCreateInterpreter;

    @TranslatableMessage
    public static String DefaultExpressionProposal_nullProposal;

    @TranslatableMessage
    public static String DynamicEPackageService_missingLocationAttribute;

    @TranslatableMessage
    public static String DynamicPackageRegistryReader_packageConflict;

    @TranslatableMessage
    public static String EclipseEditingDomainFactoryDescriptor_errorLoadingExtension;

    @TranslatableMessage
    public static String EclipseUtil_extensionLoadError;

    @TranslatableMessage
    public static String EditingSessionWorkspaceListener_resourceRefreshError;

    @TranslatableMessage
    public static String EObjectCouple_firstNull;

    @TranslatableMessage
    public static String EObjectCouple_secondNull;

    @TranslatableMessage
    public static String FeatureInterpreter_invalidFeature;

    @TranslatableMessage
    public static String FeatureInterpreter_unknownFeature;

    @TranslatableMessage
    public static String FileModificationValidatorDescriptor_creationError;

    @TranslatableMessage
    public static String FileStatusPrecommitListener_fileModificationValidationStatus;

    @TranslatableMessage
    public static String FindMessages_abstractFindLabelDialogBackwardRadio;

    @TranslatableMessage
    public static String FindMessages_abstractFindLabelDialogCancelButton;

    @TranslatableMessage
    public static String FindMessages_abstractFindLabelDialogDialogTitle;

    @TranslatableMessage
    public static String FindMessages_abstractFindLabelDialogDirectionGroup;

    @TranslatableMessage
    public static String FindMessages_abstractFindLabelDialogErrorDialogTitle;

    @TranslatableMessage
    public static String FindMessages_abstractFindLabelDialogFindLabel;

    @TranslatableMessage
    public static String FindMessages_abstractFindLabelDialogForwardRadio;

    @TranslatableMessage
    public static String FindMessages_abstractFindLabelDialogNextButton;

    @TranslatableMessage
    public static String FindMessages_abstractFindLabelDialogNoMatchingElementMessage;
    
    @TranslatableMessage
    public static String Interpreter_evaluationError;

    @TranslatableMessage
    public static String MonomorphicService_serviceError;

    @TranslatableMessage
    public static String PolymorphicService_noCompatibleImplem;

    @TranslatableMessage
    public static String PolymorphicService_toString;

    @TranslatableMessage
    public static String ProfilerTask_nullCategory;

    @TranslatableMessage
    public static String ProfilerTask_nullName;

    @TranslatableMessage
    public static String ProfilerTaskRegistry_keyConflict;

    @TranslatableMessage
    public static String ProfilerTaskRegistry_valueConflict;

    @TranslatableMessage
    public static String ResourceSetFactory_creationError;

    @TranslatableMessage
    public static String ResourceSetFactory_ignoredOverrides;

    @TranslatableMessage
    public static String ResourceSyncClientNotifier_actionName;

    @TranslatableMessage
    public static String ResourceUtil_backupFileAlreadyExists;

    @TranslatableMessage
    public static String ResourceUtil_backupFileTask;

    @TranslatableMessage
    public static String ServiceInterpreter_invalidReceiver;

    @TranslatableMessage
    public static String ServiceInterpreter_javaClassNotFound;

    @TranslatableMessage
    public static String ServiceInterpreter_unknownService;

    @TranslatableMessage
    public static String TimeProfiler_nullListener;

    @TranslatableMessage
    public static String TimeProfiler2_emptyStackError;

    @TranslatableMessage
    public static String TimeProfiler2_otherCategory;

    @TranslatableMessage
    public static String TimeProfiler2_otherTaskName;

    @TranslatableMessage
    public static String VariableInterpreter_unknownVariable;

    @TranslatableMessage
    public static String VariableInterpreter_unkownVariable;

    @TranslatableMessage
    public static String MessageTranslator_missingResourceMessage;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instanciation.
    }
}

/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.properties.core.api;

import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.sirius.properties.DialogButton;
import org.eclipse.sirius.properties.DialogModelOperation;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.core.internal.SiriusPropertiesCorePlugin;

/**
 * Preprocesses the dialog model operation description in order to resolve inheritance relationships.
 * 
 * @author sbegaudeau
 */
public class DialogModelOperationPreprocessor {
    /**
     * The description of the dialog model operation.
     */
    private DialogModelOperation dialogModelOperation;

    /**
     * The interpreter.
     */
    private IInterpreter interpreter;

    /**
     * The variable manager.
     */
    private IVariableManager variableManager;

    /**
     * The overrides provider.
     */
    private OverridesProvider overridesProvider;

    /**
     * The constructor.
     * 
     * @param dialogModelOperation
     *            The description of the dialog model operation.
     * @param interpreter
     *            The interpreter
     * @param variableManager
     *            The variable manager
     * @param overridesProvider
     *            The overrides provider
     */
    public DialogModelOperationPreprocessor(DialogModelOperation dialogModelOperation, IInterpreter interpreter, IVariableManager variableManager, OverridesProvider overridesProvider) {
        this.dialogModelOperation = dialogModelOperation;
        this.interpreter = interpreter;
        this.variableManager = variableManager;
        this.overridesProvider = overridesProvider;
    }

    /**
     * Use the description provided in order to unfold the extends and overrides relations.
     * 
     * @return The {@link DialogModelOperation} computed
     */
    public Optional<DialogModelOperation> convert() {
        DialogModelOperation convertedDialogModelOperation = PropertiesFactory.eINSTANCE.createDialogModelOperation();

        TransformationCache cache = new TransformationCache();
        cache.put(this.dialogModelOperation, convertedDialogModelOperation);

        this.convertEAttributes(convertedDialogModelOperation);
        this.convertButtons(convertedDialogModelOperation, cache);
        this.convertPage(convertedDialogModelOperation, cache);
        this.convertGroups(convertedDialogModelOperation, cache);

        List<IDescriptionLinkResolver> linkResolvers = SiriusPropertiesCorePlugin.getPlugin().getDescriptionPreprocessorLinkResolvers();
        linkResolvers.forEach(linkResolver -> linkResolver.resolve(convertedDialogModelOperation, cache));

        return Optional.of(convertedDialogModelOperation);
    }

    /**
     * Converts the EAttribute from the dialog model operation
     * 
     * @param convertedDialogModelOperation
     *            The converted dialog model operation
     */
    private void convertEAttributes(DialogModelOperation convertedDialogModelOperation) {
        convertedDialogModelOperation.setTitleExpression(this.dialogModelOperation.getTitleExpression());
    }

    /**
     * Converts the buttons of the dialog model operation.
     * 
     * @param convertedDialogModelOperation
     *            The converted dialog model operation
     * @param cache
     *            The transformation cache
     */
    private void convertButtons(DialogModelOperation convertedDialogModelOperation, TransformationCache cache) {
        this.dialogModelOperation.getButtons().forEach(button -> {
            DialogButton convertedButton = EcoreUtil.copy(button);
            cache.put(button, convertedButton);
            convertedDialogModelOperation.getButtons().add(convertedButton);
        });
    }

    /**
     * Converts the page of the dialog model operation.
     * 
     * @param convertedDialogModelOperation
     *            The converted dialog model operation
     * @param cache
     *            The transformation cache
     */
    private void convertPage(DialogModelOperation convertedDialogModelOperation, TransformationCache cache) {
        Optional.ofNullable(this.dialogModelOperation.getPage()).ifPresent(pageDescription -> {
            Optional<IDescriptionPreprocessor> optionalPreprocessor = SiriusPropertiesCorePlugin.getPlugin().getDescriptionPreprocessor(pageDescription);
            optionalPreprocessor.ifPresent(preprocessor -> {
                EObject convertedEObject = preprocessor.convert(pageDescription, cache, interpreter, variableManager, overridesProvider);
                if (convertedEObject instanceof PageDescription) {
                    convertedDialogModelOperation.setPage((PageDescription) convertedEObject);
                }
            });
        });
    }

    /**
     * Converts the groups of the dialog model operation.
     * 
     * @param convertedDialogModelOperation
     *            The converted dialog model operation
     * @param cache
     *            The transformation cache
     */
    private void convertGroups(DialogModelOperation convertedDialogModelOperation, TransformationCache cache) {
        this.dialogModelOperation.getGroups().forEach(groupDescription -> {
            Optional<IDescriptionPreprocessor> optionalPreprocessor = SiriusPropertiesCorePlugin.getPlugin().getDescriptionPreprocessor(groupDescription);
            optionalPreprocessor.ifPresent(preprocessor -> {
                EObject convertedEObject = preprocessor.convert(groupDescription, cache, interpreter, variableManager, overridesProvider);
                if (convertedEObject instanceof GroupDescription) {
                    convertedDialogModelOperation.getGroups().add((GroupDescription) convertedEObject);
                }
            });
        });
    }
}

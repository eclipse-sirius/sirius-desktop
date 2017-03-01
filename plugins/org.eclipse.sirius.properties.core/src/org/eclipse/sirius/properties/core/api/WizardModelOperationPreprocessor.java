/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.WizardModelOperation;
import org.eclipse.sirius.properties.core.internal.SiriusPropertiesCorePlugin;

/**
 * Preprocesses the wizard model operation description in order to resolve inheritance relationships.
 * 
 * @author sbegaudeau
 */
public class WizardModelOperationPreprocessor {
    /**
     * The description of the wizard model operation.
     */
    private WizardModelOperation wizardModelOperation;

    /**
     * The interpreter.
     */
    private IInterpreter interpreter;

    /**
     * The variable manager.
     */
    private IVariableManager variableManager;

    /**
     * The constructor.
     * 
     * @param wizardModelOperation
     *            The description of the wizard model operation
     * @param interpreter
     *            The interpreter
     * @param variableManager
     *            The variable manager
     */
    public WizardModelOperationPreprocessor(WizardModelOperation wizardModelOperation, IInterpreter interpreter, IVariableManager variableManager) {
        this.wizardModelOperation = wizardModelOperation;
        this.interpreter = interpreter;
        this.variableManager = variableManager;
    }

    /**
     * Use the description provided in order to unfold the extends and overrides relations.
     * 
     * @return The {@link WizardModelOperation} computed
     */
    public Optional<WizardModelOperation> convert() {
        WizardModelOperation convertedWizardModelOperation = PropertiesFactory.eINSTANCE.createWizardModelOperation();

        TransformationCache cache = new TransformationCache();
        cache.put(this.wizardModelOperation, convertedWizardModelOperation);

        this.convertEAttributes(convertedWizardModelOperation);
        this.convertPage(convertedWizardModelOperation, cache);
        this.convertGroups(convertedWizardModelOperation, cache);

        List<IDescriptionLinkResolver> linkResolvers = SiriusPropertiesCorePlugin.getPlugin().getDescriptionPreprocessorLinkResolvers();
        linkResolvers.forEach(linkResolver -> linkResolver.resolve(convertedWizardModelOperation, cache));

        return Optional.of(convertedWizardModelOperation);
    }

    /**
     * Converts the EAttribute from the wizard model operation
     * 
     * @param convertedWizardModelOperation
     *            The converted wizard model operation
     */
    private void convertEAttributes(WizardModelOperation convertedWizardModelOperation) {
        convertedWizardModelOperation.setTitleExpression(this.wizardModelOperation.getTitleExpression());
        convertedWizardModelOperation.setWindowTitleExpression(this.wizardModelOperation.getWindowTitleExpression());
        convertedWizardModelOperation.setDescriptionExpression(this.wizardModelOperation.getDescriptionExpression());
        convertedWizardModelOperation.setIsPageCompleteExpression(this.wizardModelOperation.getIsPageCompleteExpression());

        Optional.ofNullable(this.wizardModelOperation.getInitialOperation()).map(EcoreUtil::copy).ifPresent(convertedWizardModelOperation::setInitialOperation);
    }

    /**
     * Converts the page of the wizard model operation.
     * 
     * @param convertedWizardModelOperation
     *            The converted wizard model operation
     * @param cache
     *            The transformation cache
     */
    private void convertPage(WizardModelOperation convertedWizardModelOperation, TransformationCache cache) {
        this.wizardModelOperation.getPages().forEach(pageDescription -> {
            Optional<IDescriptionPreprocessor> optionalPreprocessor = SiriusPropertiesCorePlugin.getPlugin().getDescriptionPreprocessor(pageDescription);
            optionalPreprocessor.ifPresent(preprocessor -> {
                EObject convertedEObject = preprocessor.convert(pageDescription, cache, this.interpreter, this.variableManager);
                if (convertedEObject instanceof PageDescription) {
                    convertedWizardModelOperation.getPages().add((PageDescription) convertedEObject);
                }
            });
        });
    }

    /**
     * Converts the groups of the wizard model operation.
     * 
     * @param convertedWizardModelOperation
     *            The converted wizard model operation
     * @param cache
     *            The transformation cache
     */
    private void convertGroups(WizardModelOperation convertedWizardModelOperation, TransformationCache cache) {
        this.wizardModelOperation.getGroups().forEach(groupDescription -> {
            Optional<IDescriptionPreprocessor> optionalPreprocessor = SiriusPropertiesCorePlugin.getPlugin().getDescriptionPreprocessor(groupDescription);
            optionalPreprocessor.ifPresent(preprocessor -> {
                EObject convertedEObject = preprocessor.convert(groupDescription, cache, this.interpreter, this.variableManager);
                if (convertedEObject instanceof GroupDescription) {
                    convertedWizardModelOperation.getGroups().add((GroupDescription) convertedEObject);
                }
            });
        });
    }
}

/*******************************************************************************
 * Copyright (c) 2017, 2019 Obeo.
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
package org.eclipse.sirius.properties.core.internal.preprocessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.sirius.properties.CustomDescription;
import org.eclipse.sirius.properties.CustomOperation;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.core.api.DefaultDescriptionPreprocessorWithFiltering;
import org.eclipse.sirius.properties.core.api.OverridesProvider;
import org.eclipse.sirius.properties.core.api.PreconfiguredPreprocessorUtils;
import org.eclipse.sirius.properties.core.api.TransformationCache;
import org.eclipse.sirius.properties.core.internal.converter.SiriusInitialOperationAdapter;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * Preprocessor for {@link CustomDescription}.
 * 
 * @author mbats
 */
public class CustomDescriptionPreprocessor extends DefaultDescriptionPreprocessorWithFiltering<CustomDescription> {
    /**
     * The constructor.
     */
    public CustomDescriptionPreprocessor() {
        super(CustomDescription.class, PreconfiguredPreprocessorUtils.getFeaturesToFilter(PropertiesPackage.Literals.CUSTOM_DESCRIPTION), getFeaturesToCopy());
    }

    /**
     * Get the features to copy, the custom expressions and the custom
     * operations.
     * 
     * @return The features to copy
     */
    private static Collection<EStructuralFeature> getFeaturesToCopy() {
        Collection<EStructuralFeature> featuresToCopy = PreconfiguredPreprocessorUtils.getFeaturesToCopy(PropertiesPackage.Literals.CUSTOM_DESCRIPTION);
        featuresToCopy.add(PropertiesPackage.Literals.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS);
        featuresToCopy.add(PropertiesPackage.Literals.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS);
        return featuresToCopy;
    }
    
    @Override
    protected void processManyValuedFeatureByCopying(EStructuralFeature manyValuedFeature, CustomDescription processedDescription, CustomDescription currentDescription, TransformationCache cache,
            IInterpreter interpreter, IVariableManager variableManager, OverridesProvider overridesProvider) {
        if (PropertiesPackage.eINSTANCE.getAbstractCustomDescription_CustomOperations().equals(manyValuedFeature)) {
            List<CustomOperation> transformedCustomOperations = new ArrayList<>();
            
            currentDescription.getCustomOperations().forEach(customOperation -> {
                CustomOperation processedCustomOperation = PropertiesFactory.eINSTANCE.createCustomOperation();
                processedCustomOperation.setName(customOperation.getName());
                processedCustomOperation.setDocumentation(customOperation.getDocumentation());
                
                InitialOperation processedInitialOperation = EcoreUtil.copy(customOperation.getInitialOperation());
                processedInitialOperation.eAdapters().add(new SiriusInitialOperationAdapter(EcoreUtil.getURI(customOperation.getInitialOperation())));
                processedCustomOperation.setInitialOperation(processedInitialOperation);
                transformedCustomOperations.add(processedCustomOperation);
            });
            
            processedDescription.getCustomOperations().addAll(transformedCustomOperations);
        } else {
            super.processManyValuedFeatureByCopying(manyValuedFeature, processedDescription, currentDescription, cache, interpreter, variableManager, overridesProvider);
        }
    }
    
}

/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.api;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * The default converter to use for descriptions with an initial operation.
 * 
 * @author sbegaudeau
 * 
 * @param <SIRIUS>
 *            The type of the Sirius EObject
 */
public class DefaultDescriptionWithInitialOperationConverter<SIRIUS extends EObject> extends DefaultDescriptionConverter<SIRIUS> {

    /**
     * The EAttribute containing the expression which will be used to run the
     * initial operation.
     */
    private EAttribute initialOperationExpressionEAttribute;

    /**
     * The constructor.
     * 
     * @param siriusClass
     *            The class of the Sirius EObject
     * @param eefEClass
     *            The EClass of the EEF EObject
     * @param initialOperationExpressionEAttribute
     *            The EAttribute containing the expression which will be used to
     *            run the initial operation.
     */
    public DefaultDescriptionWithInitialOperationConverter(Class<SIRIUS> siriusClass, EClass eefEClass, EAttribute initialOperationExpressionEAttribute) {
        super(siriusClass, eefEClass);
        this.initialOperationExpressionEAttribute = initialOperationExpressionEAttribute;
    }

    @Override
    protected void convertEReference(SIRIUS siriusEObject, EObject eefEObject, EReference eReference, Map<String, Object> parameters, DescriptionCache cache) {
        if (eReference.getEReferenceType().equals(ToolPackage.Literals.INITIAL_OPERATION)) {
            Object initialOperation = siriusEObject.eGet(eReference);
            if (initialOperation instanceof InitialOperation) {
                String initialOperationExpression = this.getExpressionForOperation((InitialOperation) initialOperation);
                eefEObject.eSet(this.initialOperationExpressionEAttribute, initialOperationExpression);
                return;
            }
        }
        super.convertEReference(siriusEObject, eefEObject, eReference, parameters, cache);
    }

}

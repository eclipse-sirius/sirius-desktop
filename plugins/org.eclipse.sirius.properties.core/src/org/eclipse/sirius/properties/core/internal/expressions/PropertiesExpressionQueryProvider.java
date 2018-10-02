/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
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
package org.eclipse.sirius.properties.core.internal.expressions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQueryProvider;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * An {@link IInterpretedExpressionQueryProvider} for properties view
 * description expressions.
 * 
 * @author pcdavid
 */
public class PropertiesExpressionQueryProvider implements IInterpretedExpressionQueryProvider {
    @Override
    public Option<IInterpretedExpressionQuery> getExpressionQueryFor(EObject context, EStructuralFeature expressionAttribute) {
        if (VSMNavigation.isInsideViewExtensionDescription(context) || context.eClass().getEPackage() == PropertiesPackage.eINSTANCE) {
            IInterpretedExpressionQuery value = new PropertiesInterpretedExpressionQuery(context, expressionAttribute);
            return Options.newSome(value);
        } else {
            return Options.newNone();
        }
    }
}

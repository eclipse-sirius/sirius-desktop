/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.dialect.description;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.ext.base.Option;

/**
 * Instances can provide <code>IInterpretedExpressionQuery</code> specific to
 * some particular expression occurrence in the VSM.
 * 
 * @author pcdavid
 */
public interface IInterpretedExpressionQueryProvider {
    /**
     * Optionally provides an {@link IInterpretedExpressionQuery} for a specific
     * interpreted expression occurrence.
     * 
     * @param context
     *            the VSM element in which the expression to query occurs.
     * @param expressionAttribute
     *            the attribute of the context element which designates the
     *            expression to consider (for example
     *            {@link org.eclipse.sirius.viewpoint.description.DescriptionPackage.Literals#REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION}
     *            ) .
     * @return an optional {@link IInterpretedExpressionQuery} that will query
     *         representation descriptions to determine useful informations
     *         (like the type to consider for Interpreted expressions).
     * @return
     */
    Option<IInterpretedExpressionQuery> getExpressionQueryFor(EObject context, EStructuralFeature expressionAttribute);
}

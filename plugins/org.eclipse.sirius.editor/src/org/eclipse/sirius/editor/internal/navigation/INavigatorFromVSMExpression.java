/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.internal.navigation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.editor.properties.sections.common.AbstractViewpointPropertySection;

/**
 * An {@link INavigatorFromVSMExpression} allows to navigate to any location from VSM expression content.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public interface INavigatorFromVSMExpression {
    /**
     * Returns true if this navigator handles navigation from given expression and cursor position.
     * 
     * @param propertySection
     *            the property section containing the VSM expression.
     * 
     * @param targetEObject
     *            the {@link EObject} containing the expression from which we want to know if a navigation is available.
     * @param contentContext
     *            the {@link ContentContext} containing the expression, the cursor position and the
     *            {@link IInterpreterContext}.
     * @return true if this navigator handles navigation from given expression and cursor position. False otherwise.
     */
    boolean doProvideNavigationFor(AbstractViewpointPropertySection propertySection, EObject targetEObject, ContentContext contentContext);

    /**
     * Triggers the navigation provided by this navigator from given information.
     * 
     * @param propertySection
     *            the property section containing the VSM expression.
     * 
     * @param targetEObject
     *            the {@link EObject} containing the expression from which we want to know if a navigation is available.
     * @param contentContext
     *            the {@link ContentContext} containing the expression, the cursor position and the
     *            {@link IInterpreterContext}.
     */
    void triggerNavigation(AbstractViewpointPropertySection propertySection, EObject targetEObject, ContentContext contentContext);
}

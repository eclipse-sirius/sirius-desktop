/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.internal.navigation;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

/**
 * A registry containing {@link INavigatorFromVSMExpression} allowing to navigate from VSM expression content to another location.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class NavigationFromVSMExpressionRegistry {

    /**
     * All the registered {@link INavigatorFromVSMExpression}.
     */
    private Set<Supplier<INavigatorFromVSMExpression>> navigators;

    /**
     * Constructor.
     */
    public NavigationFromVSMExpressionRegistry() {
        navigators = new HashSet<>();
    }

    /**
     * Returns all {@link INavigatorFromVSMExpression} registered.
     * 
     * @return all {@link INavigatorFromVSMExpression} registered
     */
    public Set<Supplier<INavigatorFromVSMExpression>> getNavigators() {
        return navigators;
    }

    /**
     * Add a new {@link INavigatorFromVSMExpression} to the registry.
     * 
     * @param navigator
     *            the {@link INavigatorFromVSMExpression} to add.
     */
    public void addNavigator(Supplier<INavigatorFromVSMExpression> navigator) {
        this.navigators.add(navigator);
    }

}

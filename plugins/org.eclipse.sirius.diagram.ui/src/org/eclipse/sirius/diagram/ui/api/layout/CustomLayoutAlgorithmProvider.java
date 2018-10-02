/*******************************************************************************
 * Copyright (c) 2018 Obeo
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
package org.eclipse.sirius.diagram.ui.api.layout;

import java.util.List;

/**
 * The purpose of this component is to provide custom layout algorithms to Sirius that can be used by VSM specifiers.
 * The specifiers will then be able to select the custom layout algorithms to use when arranging a Sirius diagram.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public interface CustomLayoutAlgorithmProvider {

    /**
     * Returns the custom layout algorithms that should be available to VSM specifiers to specify the kind of layouting
     * that will be done when arranging a particular diagram kind.
     * 
     * @return the custom layout algorithms.
     */
    List<CustomLayoutAlgorithm> getCustomLayoutAlgorithms();
}

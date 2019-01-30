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
package org.eclipse.sirius.diagram.business.internal.metamodel.helper;

import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * Interface declaring constants used to handle styles.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public interface StyleConstants {
    /**
     * The key of the annotation containing the cache of computed {@link StyleDescription}.
     */
    String DANNOTATION_CUSTOMIZATION_KEY = "DANNOTATION_CUSTOMIZATION_KEY"; //$NON-NLS-1$
}

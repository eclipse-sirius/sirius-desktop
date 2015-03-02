/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.interpreter;

import java.util.Set;

/**
 * A callback which is being notified if the classpath of some project did
 * change.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 */

public interface ClasspathChangeCallback {
    /**
     * Called by the {@link ClassLoading} when some projects have been
     * updated in a way which might the classpath might have changed.
     * 
     * @param updatedProjects
     *            a set of project names which have been changed.
     */
    void classpathChanged(Set<String> updatedProjects);
}

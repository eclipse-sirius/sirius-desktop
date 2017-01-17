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
package org.eclipse.sirius.tests.unit.api.convert;

import org.eclipse.core.resources.IFile;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.resourcelistener.DefaultModelingProjectResourceListener;

/**
 * This class overrides DefaultModelingProjectResourceListener and also ignores
 * genmodel files (if it is enabled).<BR>
 * It is only really enabled when the static method {@link #enable()} is called.
 * This avoids to have side effect on others tests.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class TestModelingProjectResourceListener extends DefaultModelingProjectResourceListener {

    private static boolean enabled;

    /**
     * Default constructor
     */
    public TestModelingProjectResourceListener() {
        super();
    }

    public static void enable() {
        enabled = true;
    }

    public static void disable() {
        enabled = false;
    }

    @Override
    protected boolean isPotentialSemanticResource(IFile file) {
        boolean result = super.isPotentialSemanticResource(file);
        if (enabled) {
            result = result && !"genmodel".equals(file.getFileExtension());
        }
        return result;
    }
}

/*******************************************************************************
 * Copyright (c) 2014 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

import com.google.common.base.Predicate;

/**
 * Class able to tell whether a save operation on a resource can succeed or not,
 * and if the resulting file will change.
 * 
 * Please use with care as we basically save the resource in a temporary
 * resource to know whether it will change the file or not.
 * 
 * @author cbrun
 * 
 */
public class DifferentSerialization implements Predicate<Resource> {

    private Map<?, ?> options;

    /**
     * Create a new diagnose for the given resource.
     * 
     * @param options
     *            the save options to use to compute the new serialization.
     */
    public DifferentSerialization(final Map<?, ?> options) {
        this.options = options;
    }

    @Override
    public boolean apply(Resource input) {
        boolean hasChangesToSave = false;
        ResourceSaveDiagnose diagnose = new ResourceSaveDiagnose(input);
        try {
            hasChangesToSave = diagnose.isSaveable() && diagnose.hasDifferentSerialization(this.options);
        } catch (final IOException e) {
            SiriusPlugin.getDefault().error("Error saving resource", e);
        }
        return hasChangesToSave;
    }

}

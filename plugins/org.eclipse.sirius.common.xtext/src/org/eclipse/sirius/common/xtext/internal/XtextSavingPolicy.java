/*******************************************************************************
 * Copyright (c) 2014, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.xtext.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.SavingPolicy;
import org.eclipse.xtext.common.types.access.TypeResource;
import org.eclipse.xtext.resource.SaveOptions;

/**
 * A saving policy dedicated to Xtext aware sessions. It actually work around
 * the bug #432795 by always adding options to ignore the concrete syntax
 * validation of Xtext while saving.
 * 
 * This policy only add the option and delegate the actual work to another one
 * passed as a constructor argument.
 * 
 * @author cbrun
 * 
 */
public class XtextSavingPolicy implements SavingPolicy {

    private final SavingPolicy delegate;

    /**
     * Create the saving policy.
     * 
     * @param delegate
     *            the delegate which is going to be called for the save
     *            operations, but with Xtext specific options.
     */
    public XtextSavingPolicy(SavingPolicy delegate) {
        this.delegate = delegate;
    }

    @SuppressWarnings("restriction")
    @Override
    public Collection<Resource> save(Iterable<Resource> resourcesToSave, Map<?, ?> options, IProgressMonitor monitor) {
        Map<Object, Object> newOptions = new HashMap<>();
        if (options != null) {
            newOptions.putAll(options);
        }
        newOptions.putAll(SaveOptions.newBuilder().noValidation().getOptions().toOptionsMap());

        Collection<Resource> writeableResourcesToSave = new ArrayList<>();
        resourcesToSave.forEach(r -> {
            if (!(r instanceof TypeResource)) {
                writeableResourcesToSave.add(r);
            }
        });
        return delegate.save(writeableResourcesToSave, newOptions, monitor);
    }

}

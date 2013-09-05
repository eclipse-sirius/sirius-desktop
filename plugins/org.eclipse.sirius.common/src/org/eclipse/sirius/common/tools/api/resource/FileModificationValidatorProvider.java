/*******************************************************************************
 * Copyright (c) 2008, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.resource;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.sirius.common.tools.internal.resource.FileModificationValidatorDescriptor;
import org.eclipse.sirius.common.tools.internal.resource.FileModificationValidatorProviderImpl;

/**
 * The provider of validation.
 * 
 * @author smonnier
 */
public interface FileModificationValidatorProvider {

    /** The shared instance of the provider. */
    FileModificationValidatorProvider INSTANCE = new FileModificationValidatorProviderImpl();

    /**
     * Returns the interpreter for the given expression.
     * 
     * @return the corresponding IJavaActionMenuItem.
     */
    ArrayList<IFileModificationValidator> getFileModificationValidator();

    /**
     * Returns all registered java actions.
     * 
     * @return all registered java actions.
     */
    Collection<FileModificationValidatorDescriptor> getValidationDescriptor();

}

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
package org.eclipse.sirius.common.tools.internal.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.resource.FileModificationValidatorProvider;
import org.eclipse.sirius.common.tools.api.resource.IFileModificationValidator;

/**
 * The provider of validation.
 * 
 * @author smonnier
 */
public final class FileModificationValidatorProviderImpl implements FileModificationValidatorProvider {

    /** The extension point. */
    private static final String VALIDATION_EXTENSION_POINT = DslCommonPlugin.PLUGIN_ID + ".fileModificationValidator"; //$NON-NLS-1$

    private Collection<FileModificationValidatorDescriptor> validationEditDescriptors = new HashSet<FileModificationValidatorDescriptor>();

    /**
     * Returns the interpreter for the given expression.
     * 
     * @return the corresponding IJavaActionMenuItem.
     */
    public ArrayList<IFileModificationValidator> getFileModificationValidator() {
        final ArrayList<IFileModificationValidator> fileModificationValidators = new ArrayList<IFileModificationValidator>();
        for (FileModificationValidatorDescriptor validationDescriptor : getValidationDescriptor()) {
            fileModificationValidators.add(validationDescriptor.createFileModificationValidator());
        }
        return fileModificationValidators;
    }

    /**
     * Returns all registered java actions.
     * 
     * @return all registered java actions.
     */
    public Collection<FileModificationValidatorDescriptor> getValidationDescriptor() {
        if (validationEditDescriptors.isEmpty()) {
            loadExtensions();
        }
        return validationEditDescriptors;
    }

    /**
     * Loads all interpreters that are declared in extensions.
     */
    private void loadExtensions() {
        parseExtensionMetadata();
    }

    /**
     * This will parse the currently running platform for extensions and store
     * all the match engines that can be found.
     */
    private void parseExtensionMetadata() {
        if (EMFPlugin.IS_ECLIPSE_RUNNING) {
            final IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(VALIDATION_EXTENSION_POINT).getExtensions();
            for (IExtension extension : extensions) {
                final IConfigurationElement[] configElements = extension.getConfigurationElements();
                for (IConfigurationElement configElement : configElements) {
                    final FileModificationValidatorDescriptor descriptor = new FileModificationValidatorDescriptor(configElement);
                    if (descriptor.createFileModificationValidator() != null) {
                        validationEditDescriptors.add(descriptor);
                    }
                }
            }
        }
    }

}

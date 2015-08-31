/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.internal.resource;

import java.text.MessageFormat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.Messages;
import org.eclipse.sirius.common.tools.api.resource.IFileModificationValidator;

/**
 * A factory proxy for creating a IFileModificationValidator.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class FileModificationValidatorDescriptor {
    private static final String CLASS_ATTRIBUTE = "validationClass"; //$NON-NLS-1$

    private IConfigurationElement element;

    /**
     * Creates a new descriptor with the given configuration element.
     * 
     * @param element
     *            The configuration element
     */
    public FileModificationValidatorDescriptor(final IConfigurationElement element) {
        this.element = element;
    }

    /**
     * Creates a new file modification validator.
     * 
     * @return The new IFileModificationValidator
     */
    public IFileModificationValidator createFileModificationValidator() {
        try {
            return (IFileModificationValidator) element.createExecutableExtension(CLASS_ATTRIBUTE);
        } catch (final CoreException e) {
            DslCommonPlugin.getDefault().error(MessageFormat.format(Messages.FileModificationValidatorDescriptor_creationError, element.getAttribute(CLASS_ATTRIBUTE)), e);
        } catch (final ClassCastException e) {
            DslCommonPlugin.getDefault().error(MessageFormat.format(Messages.FileModificationValidatorDescriptor_creationError, element.getAttribute(CLASS_ATTRIBUTE)), e);
        }
        return null;
    }
}

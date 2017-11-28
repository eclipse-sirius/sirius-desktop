/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.acceleo.mtl.business.internal.extension;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.sirius.common.acceleo.mtl.business.api.extension.AbstractImportHandler;

/**
 * Describes an import handler as contributed to the extension point.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class ImportHandlerDescriptor {
    /** Name of the extension point's importHandler tag "class" attribute. */
    public static final String IMPORT_HANDLER_ATTRIBUTE_CLASS = "class"; //$NON-NLS-1$

    /** Name of the extension point's importHandler tag "label" attribute. */
    private static final String IMPORT_HANDLER_ATTRIBUTE_LABEL = "label"; //$NON-NLS-1$

    /** Configuration element of this descriptor. */
    private final IConfigurationElement element;

    /** Qualified class name of this import handler. */
    private final String importHandlerClassName;

    /** Label of this particular handler. */
    private final String label;

    /**
     * Instantiates a descriptor for the given configuration element.
     * 
     * @param element
     *            The configuration element from which to create this
     *            descriptor.
     */
    public ImportHandlerDescriptor(IConfigurationElement element) {
        this.element = element;
        this.label = element.getAttribute(IMPORT_HANDLER_ATTRIBUTE_LABEL);
        this.importHandlerClassName = element.getAttribute(IMPORT_HANDLER_ATTRIBUTE_CLASS);
    }

    /**
     * Returns the class name of this handler.
     * 
     * @return The class name of this handler.
     */
    public String getClassName() {
        return importHandlerClassName;
    }

    /**
     * Returns an instance of the described import handler.
     * 
     * @return Newly create instance of the described import handler.
     */
    public AbstractImportHandler getImportHandler() {
        try {
            Object instance = element.createExecutableExtension(IMPORT_HANDLER_ATTRIBUTE_CLASS);
            if (instance instanceof AbstractImportHandler) {
                return (AbstractImportHandler) instance;
            }
        } catch (CoreException e) {
            // FIXME log this
        }
        return null;
    }

    /**
     * Returns the label of this engine.
     * 
     * @return The label of this engine.
     */
    public String getLabel() {
        return label;
    }
}

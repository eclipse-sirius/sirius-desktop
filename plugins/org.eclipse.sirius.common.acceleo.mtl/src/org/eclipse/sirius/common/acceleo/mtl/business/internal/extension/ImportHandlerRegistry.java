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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.common.acceleo.mtl.business.api.extension.AbstractImportHandler;

import com.google.common.collect.Lists;

/**
 * This will contain all of the import handlers that can be used by the Acceleo
 * interpreter.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class ImportHandlerRegistry {
    /** List of handlers parsed from the extension point contributions. */
    private static final List<ImportHandlerDescriptor> HANDLERS = new ArrayList<>();

    /** Utility classes don't need a default constructor. */
    private ImportHandlerRegistry() {
        // hides constructor
    }

    /**
     * Adds an handler to the registry.
     * 
     * @param descriptor
     *            The descriptor that is to be added to the registry.
     */
    public static void addHandler(ImportHandlerDescriptor descriptor) {
        HANDLERS.add(descriptor);
    }

    /** Clears the registry of all contributions. */
    public static void clearRegistry() {
        HANDLERS.clear();
    }

    /**
     * Returns a copy of the registered handlers' list.
     * 
     * @return A copy of the registered handlers' list.
     */
    public static List<AbstractImportHandler> getRegisteredHandlers() {
        final List<AbstractImportHandler> importHandlers = new ArrayList<>();

        for (ImportHandlerDescriptor descriptor : HANDLERS) {
            importHandlers.add(descriptor.getImportHandler());
        }

        return importHandlers;
    }

    /**
     * Removes an handler from the registry.
     * 
     * @param className
     *            Qualified class name of the handler that is to be removed from
     *            the registry.
     */
    public static void removeHandler(String className) {
        for (ImportHandlerDescriptor descriptor : Lists.newArrayList(HANDLERS)) {
            if (descriptor.getClassName().equals(className)) {
                HANDLERS.remove(descriptor);
            }
        }
    }
}

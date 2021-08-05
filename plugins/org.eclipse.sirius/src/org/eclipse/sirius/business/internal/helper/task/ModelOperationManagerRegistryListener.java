/*******************************************************************************
 * Copyright (c) 2017, 2021 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.helper.task;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryChangeEvent;
import org.eclipse.core.runtime.IRegistryChangeListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.business.api.helper.task.IModelOperationManager;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * This listener will allow us to be aware of contribution changes against the model operation manager extension point.
 * 
 * @author sbegaudeau
 */
public class ModelOperationManagerRegistryListener implements IRegistryChangeListener {

    /** Name of the extension point to parse for extensions. */
    public static final String MODEL_OPERATION_MANAGER_EXTENSION_POINT = SiriusPlugin.ID + ".modelOperationManager"; //$NON-NLS-1$

    /** Name of the extension point's "modelOperationManager" tag. */
    private static final String MODEL_OPERATION_MANAGER_TAG_EXTENSION = "modelOperationManager"; //$NON-NLS-1$

    /**
     * Register this listener and parse initial contributions.
     */
    public void init() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        registry.addRegistryChangeListener(this, MODEL_OPERATION_MANAGER_EXTENSION_POINT);
        parseInitialContributions();
    }

    /**
     * Though this listener reacts to the extension point changes, there could have been contributions before it's been
     * registered. This will parse these initial contributions.
     */
    public void parseInitialContributions() {
        final IExtensionRegistry registry = Platform.getExtensionRegistry();

        for (IExtension extension : registry.getExtensionPoint(MODEL_OPERATION_MANAGER_EXTENSION_POINT).getExtensions()) {
            parseExtension(extension);
        }
    }

    /**
     * Parses a single extension contribution.
     * 
     * @param extension
     *            Parses the given extension and adds its contribution to the registry.
     */
    private void parseExtension(IExtension extension) {
        final IConfigurationElement[] configElements = extension.getConfigurationElements();
        for (IConfigurationElement elem : configElements) {
            if (MODEL_OPERATION_MANAGER_TAG_EXTENSION.equals(elem.getName())) {
                try {
                    String id = elem.getAttribute(ModelOperationManagerDescriptor.MODEL_OPERATION_MANAGER_ID_ATTRIBUTE);
                    Object object = elem.createExecutableExtension(ModelOperationManagerDescriptor.MODEL_OPERATION_MANAGER_CLASS_ATTRIBUTE);
                    if (object instanceof IModelOperationManager) {
                        IModelOperationManager manager = (IModelOperationManager) object;
                        ModelOperationManagerRegistry.addExtension(new ModelOperationManagerDescriptor(id, manager));
                    }
                } catch (CoreException e) {
                    SiriusPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, SiriusPlugin.ID,
                            MessageFormat.format(Messages.EclipseDeleteHookDescriptor_extensionLoadingErrorMsg, elem.getDeclaringExtension().getUniqueIdentifier()), e));
                }
            }
        }
    }

    @Override
    public void registryChanged(IRegistryChangeEvent event) {
        IExtensionDelta[] deltas = event.getExtensionDeltas();
        List<IExtension> addedExtensions = new ArrayList<IExtension>();
        List<IExtension> removedExtensions = new ArrayList<IExtension>();
        for (int i = 0; i < deltas.length; i++) {
            if (!MODEL_OPERATION_MANAGER_EXTENSION_POINT.equals(deltas[i].getExtensionPoint().getUniqueIdentifier())) {
                continue;
            }
            if (deltas[i].getKind() == IExtensionDelta.ADDED) {
                IExtension extension = deltas[i].getExtension();
                addedExtensions.add(extension);
            } else if (deltas[i].getKind() == IExtensionDelta.REMOVED) {
                IExtension extension = deltas[i].getExtension();
                removedExtensions.add(extension);
            }
        }
        added(addedExtensions.toArray(new IExtension[0]));
        removed(addedExtensions.toArray(new IExtension[0]));
    }

    private void added(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            parseExtension(extension);
        }
    }

    private void removed(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            final IConfigurationElement[] configElements = extension.getConfigurationElements();
            for (IConfigurationElement elem : configElements) {
                if (MODEL_OPERATION_MANAGER_TAG_EXTENSION.equals(elem.getName())) {
                    final String extensionClassName = elem.getAttribute(ModelOperationManagerDescriptor.MODEL_OPERATION_MANAGER_CLASS_ATTRIBUTE);
                    ModelOperationManagerRegistry.removeExtension(extensionClassName);
                }
            }
        }
    }

    /**
     * Remove this listener and flush the associated registry.
     */
    public void dispose() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        registry.removeRegistryChangeListener(this);
        ModelOperationManagerRegistry.clearRegistry();
    }

}

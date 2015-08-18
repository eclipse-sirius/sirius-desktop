/*******************************************************************************
 * Copyright (c) 2009, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.api.diagramtype;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Registry for all diagram types.
 * <p>
 * Warning: This class implements a NON thread safe singleton. You have to
 * synchronize calls if you use the shared instance in a multi threads
 * environment.
 * </p>
 * 
 * @author ymortier
 */
public final class DiagramTypeDescriptorRegistry {

    /** Diagram type extension point ID. */
    private static final String DIAGRAM_TYPE_EP_ID = "org.eclipse.sirius.diagram.diagramTypeProvider"; //$NON-NLS-1$

    /** The diagram description provider attribute. */
    private static final String DIAGRAM_DESCRIPTION_PROVIDER_ATTR = "descriptionProvider"; //$NON-NLS-1$

    /** The label attribute. */
    private static final String LABEL_ATTRIBUTE = "label"; //$NON-NLS-1$

    private static DiagramTypeDescriptorRegistry instance;

    /** All providers. */
    private Collection<IDiagramTypeDescriptor> allProviders;

    /**
     * Avoid instantiation from external.
     */
    private DiagramTypeDescriptorRegistry() {
        // empty.
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance.
     */
    public static DiagramTypeDescriptorRegistry getInstance() {
        if (instance == null) {
            instance = new DiagramTypeDescriptorRegistry();
        }
        return instance;
    }

    /**
     * Returns all diagram type descriptors. The returned collection is
     * unmodifiable. All attempts to modify the result will fail with a
     * {@link UnsupportedOperationException}.
     * 
     * @return all diagram type descriptors (the returned collection is
     *         unmodifiable).
     */
    public Collection<IDiagramTypeDescriptor> getAllDiagramTypeDescriptors() {
        return Collections.unmodifiableCollection(this.internalGetAllDiagramTypeDescriptors());
    }

    /**
     * Registers the given diagram type descriptors.
     * 
     * @param diagramTypeDescriptor
     *            the diagram type descriptors to register.
     */
    public void registerDiagramTypeDescriptor(final IDiagramTypeDescriptor diagramTypeDescriptor) {
        this.internalGetAllDiagramTypeDescriptors().add(diagramTypeDescriptor);
    }

    /**
     * Unregsiters the given diagram type descriptors.
     * 
     * @param diagramTypeDescriptor
     *            the diagram type descriptors to register.
     */
    public void unregisterDiagramTypeDescriptor(final IDiagramTypeDescriptor diagramTypeDescriptor) {
        this.internalGetAllDiagramTypeDescriptors().remove(diagramTypeDescriptor);
    }

    /**
     * Returns all descriptors. Lazy loads the desciptor that used the
     * diagramDescriptionProvider extension point.
     * 
     * @return all descriptors.
     */
    private Collection<IDiagramTypeDescriptor> internalGetAllDiagramTypeDescriptors() {
        if (this.allProviders == null) {
            this.allProviders = new HashSet<IDiagramTypeDescriptor>();
            this.loadDescriptors();
        }
        return this.allProviders;
    }

    /**
     * Load descriptors from plugin.
     */
    private void loadDescriptors() {
        final IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
        final IExtensionPoint ep = extensionRegistry.getExtensionPoint(DIAGRAM_TYPE_EP_ID);
        for (final IExtension extension : ep.getExtensions()) {
            final IConfigurationElement[] ces = extension.getConfigurationElements();
            for (final IConfigurationElement ce : ces) {
                try {
                    final Object provider = ce.createExecutableExtension(DIAGRAM_DESCRIPTION_PROVIDER_ATTR);
                    if (provider instanceof IDiagramDescriptionProvider) {
                        final String label = ce.getAttribute(LABEL_ATTRIBUTE);
                        final IDiagramTypeDescriptor descriptor = new PluginDiagramTypeDescriptor(label, (IDiagramDescriptionProvider) provider);
                        allProviders.add(descriptor);
                    } else {
                        SiriusPlugin.getDefault().error("Error while loading the extension : " + extension.getLabel(), new ClassCastException());
                    }
                } catch (final CoreException e) {
                    SiriusPlugin.getDefault().error("Error while loading the extension " + extension.getLabel(), e);
                    SiriusPlugin.getDefault().getLog().log(e.getStatus());
                }
            }
        }
    }

    /**
     * Basic implementation of {@link IDiagramTypeDescriptor} for the
     * diagramType extension.
     * 
     * @author ymortier
     */
    private static class PluginDiagramTypeDescriptor implements IDiagramTypeDescriptor {

        /** The label of the diagram type. */
        private String label;

        /** The Diagram Description provider. */
        private IDiagramDescriptionProvider diagramDescriptionProvider;

        /**
         * Creates a new diagram type descriptor with the given label and
         * diagram description provider.
         * 
         * @param label
         *            the label of the diagram type.
         * @param diagramDescriptionProvider
         *            the diagram description provider.
         * @throws IllegalArgumentException
         *             if one of the arguments is <code>null</code>.
         */
        public PluginDiagramTypeDescriptor(final String label, final IDiagramDescriptionProvider diagramDescriptionProvider) throws IllegalArgumentException {
            this.label = label;
            this.diagramDescriptionProvider = diagramDescriptionProvider;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor#getDiagramDescriptionProvider()
         */
        public IDiagramDescriptionProvider getDiagramDescriptionProvider() {
            return this.diagramDescriptionProvider;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor#getLabel()
         */
        public String getLabel() {
            return label;
        }

    }

}

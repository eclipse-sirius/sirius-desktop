/*******************************************************************************
 * Copyright (c) 2007, 2025 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.business.api.componentization;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Set;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.internal.componentization.ViewpointRegistryImpl;
import org.eclipse.sirius.viewpoint.description.Component;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * The registry for all the {@link Viewpoint viewpoints} available.
 * 
 * This class is responsible for locating and loading all the valid viewpoints
 * registered in plug-ins (through
 * <code>org.eclipse.sirius.componentization</code>) or available in the
 * workspace.
 * 
 * @author mchauvin
 */
public class ViewpointRegistry implements IResourceChangeListener {

    /**
     * Comparator for viewpoints, to use in UI to provide a standard and
     * predictable ordering for users.
     * 
     * @author cbrun
     * @since 0.9.0
     */
    public static final class ViewpointComparator implements Comparator<Viewpoint>, Serializable {
        /**
         * Generated SUID.
         */
        private static final long serialVersionUID = 7003296621395597047L;

        /**
         * Sort workspace viewpoints first, then plug-in viewpoints, and sort
         * both categories by name.
         * 
         * @param vp1
         *            the first viewpoint
         * @param vp2
         *            the second viewpoint
         * @return the result of the comparison, as defined in
         *         {@link Comparator#compare(Object, Object)}.
         */
        @Override
        public int compare(final Viewpoint vp1, final Viewpoint vp2) {
            int result = -1;

            if (!ViewpointRegistry.getInstance().isFromPlugin(vp1)) {
                if (!ViewpointRegistry.getInstance().isFromPlugin(vp2)) {
                    result = compareSameFrom(vp1, vp2);
                } else {
                    // vp1 from workspace, vp2 from plugin
                    result = -1;
                }
            } else {
                if (!ViewpointRegistry.getInstance().isFromPlugin(vp2)) {
                    // vp1 from plugin and vp2 from workspace
                    result = 1;
                } else {
                    // vp1 and vp2 from plugin
                    result = compareSameFrom(vp1, vp2);
                }
            }
            return result;
        }

        private int compareSameFrom(Viewpoint vp1, Viewpoint vp2) {
            IdentifiedElementQuery vp1Query = new IdentifiedElementQuery(vp1);
            IdentifiedElementQuery vp2Query = new IdentifiedElementQuery(vp2);

            int result = vp1Query.getLabel().compareTo(vp2Query.getLabel());

            result = vp1.getName().compareTo(vp2.getName());

            /*
             * we should not return equality if they are not really equal
             */
            if (result == 0) {
                URI vp1URI = EcoreUtil.getURI(vp1);
                URI vp2URI = EcoreUtil.getURI(vp2);
                result = vp1URI.toString().compareTo(vp2URI.toString());
            }
            /*
             * we should not return equality if they are not really equal
             */
            if (result == 0) {
                result = (vp1.equals(vp2)) ? 0 : -1;
            }

            return result;
        }
    }

    /**
     * Returns the shared instance.
     * 
     * @return the global viewpoints registry.
     */
    public static ViewpointRegistry getInstance() {
        return ViewpointRegistryHolder.instance;
    }

    /*
     * This class *will not* be initialized (hence the static block not
     * triggered) until the ViewpointRegistry.getInstance() method is called.
     * This is actually the 'Initialization on demand Holder' pattern which is
     * both thread safe, lazy, and does not introduce extra synchronization
     * locks.
     */
    private static final class ViewpointRegistryHolder {
        private static ViewpointRegistry instance;
        static {
            instance = new ViewpointRegistryImpl();
            instance.init(10);
        }
    }

    /**
     * Init the registry with a given size.
     * 
     * @param size
     *            the initial size.
     */
    public void init(final int size) {
        throw new UnsupportedOperationException();
    }

    /**
     * Return an unmodifiable set of the current registered viewpoints.
     * 
     * @return the viewpoints registered
     */
    public Set<Viewpoint> getViewpoints() {
        throw new UnsupportedOperationException();
    }

    /**
     * Check if a viewpoint comes from plug-in.
     * 
     * @param viewpoint
     *            the viewpoint to check.
     * @return <code>true</code> if the plug-in comes from plug-in false if it
     *         comes from workspace.
     */
    public boolean isFromPlugin(Viewpoint viewpoint) {
        throw new UnsupportedOperationException();
    }

    /**
     * Find the emf object equals to the given one in the viewpoint registry
     * resource set.
     * 
     * @param eObject
     *            the emf object to look for
     * @return the eObject instance if found, the given object otherwise
     */
    public EObject find(EObject eObject) {
        throw new UnsupportedOperationException();
    }

    /**
     * Get cross referencer installed on modeler description resources.
     * 
     * @return the cross referencer.
     */
    public ECrossReferenceAdapter getCrossReferencer() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the viewpoint which defines the specified representation
     * description.
     * 
     * @param description
     *            the representation description.
     * @return the viewpoint which defines the representation description, or
     *         <code>null</code> if it could not be found.
     * @since 0.9.0
     */
    public Viewpoint getViewpoint(RepresentationDescription description) {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the viewpoint from a viewpoint uri.
     * 
     * @param viewpointUri
     *            the viewpoint uri. It should used viewpoint protocol.
     * @return the viewpoint if found, throw an exception otherwise
     * @since 0.9.0
     */
    public Viewpoint getViewpoint(URI viewpointUri) {
        throw new UnsupportedOperationException();
    }

    /**
     * Add a listener on the registry.
     * 
     * @param listener
     *            the listener to add;
     * @return <code>true</code> if the listener was added, <code>false</code>
     *         otherwise.
     */
    public boolean addListener(ViewpointRegistryListener2 listener) {
        throw new UnsupportedOperationException();
    }

    /**
     * Remove a listener from the registry.
     * 
     * @param listener
     *            the listener to remove
     * @return <code>true</code> if removed, <code>false</code> otherwise.
     */
    public boolean removeListener(ViewpointRegistryListener2 listener) {
        throw new UnsupportedOperationException();
    }

    /**
     * Register all components in the resource. This method should be called on
     * org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
     * 
     * @param modelerModelResourcePath
     *            the platform file path ("pluginname/rep1/rep2/file.odesign)
     * @return the added viewpoints;
     */
    public Set<Viewpoint> registerFromPlugin(String modelerModelResourcePath) {
        throw new UnsupportedOperationException();
    }

    /**
     * Dispose a {@link Viewpoint}. This method should be called on
     * {@link org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)}
     * 
     * @param viewpoint
     *            the viewpoint to dispose
     */
    public void disposeFromPlugin(Viewpoint viewpoint) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Reload all VSM that are contained in installed plug-ins.</br>
     * That is useful if the specifier has made changes in the odesign file because he do not have to restart the
     * eclipse to have the change effect.</br>
     * Opened representation editors will be automatically refreshed.
     */
    public void reloadAllFromPlugins() {
        throw new UnsupportedOperationException();
    }

    /**
     * Add a filter on the registry.
     * 
     * @see ViewpointRegistry#getViewpoints()
     * @param filter
     *            the filter to add;
     * @return <code>true</code> if the filter was added, <code>false</code>
     *         otherwise.
     */
    public boolean addFilter(ViewpointRegistryFilter filter) {
        throw new UnsupportedOperationException();
    }

    /**
     * Remove a filter from the registry.
     * 
     * @param filter
     *            the filter to remove
     * @return <code>true</code> if removed, <code>false</code> otherwise.
     */
    public boolean removeFilter(ViewpointRegistryFilter filter) {
        throw new UnsupportedOperationException();
    }

    /**
     * Remove filters on the registry based on their id.
     * 
     * @param id
     *            the id of the filters to remove
     */
    public void removeFilter(String id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Registers all components from the workspace. All previously registered
     * components will be disposed.
     * 
     * @param components
     *            the viewpoints to register.
     * @param <T>
     *            the type
     */
    public <T extends Component> void registerFromWorkspace(Set<T> components) {
        throw new UnsupportedOperationException();
    }

    /**
     * Dispose the registry.
     */
    public void dispose() {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        // Do nothing.
    }
}

/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.movida.registry.monitoring;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.viewpoint.Messages;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Aggregates an ordered set of resource monitors.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class CompositeResourceMonitor extends AbstractViewpointResourceMonitor implements ViewpointResourceListener, Comparator<URI> {
    /**
     * The aggregated monitors.
     */
    private final List<ViewpointResourceMonitor> monitors = Lists.newArrayList();

    /**
     * The names of the aggregated monitors, in the same order as in
     * <code>monitors</code>.
     */
    private final List<String> names = Lists.newArrayList();

    /**
     * A map to keep track of which monitor detected each of the currently
     * active URIs;
     */
    private final Map<URI, ViewpointResourceMonitor> origins = Maps.newHashMap();

    /**
     * Adds a new monitor to this composite.
     * 
     * @param name
     *            the symbolic name of this monitor. Must not be already in use
     *            in this composite.
     * @param monitor
     *            the monitor to add.
     * @throws IllegalStateException
     *             if this composite or the monitor is already running, is
     *             already registered here, or if the specified name is already
     *             in use.
     */
    public synchronized void addMonitor(String name, ViewpointResourceMonitor monitor) throws IllegalStateException {
        Preconditions.checkState(!this.isRunning() && !monitor.isRunning(), Messages.CompositeResourceMonitor_addMonitorErrorMsg);
        Preconditions.checkState(!monitors.contains(monitor), Messages.CompositeResourceMonitor_alreadyRegisteredErrorMsg);
        Preconditions.checkState(!names.contains(name), MessageFormat.format(Messages.CompositeResourceMonitor_alreadyUsedNameErrorMsg, name));

        monitors.add(monitor);
        names.add(name);
        monitor.setListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void start() {
        this.running = true;
        for (ViewpointResourceMonitor monitor : monitors) {
            monitor.start();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void stop() {
        for (ViewpointResourceMonitor monitor : monitors) {
            monitor.stop();
        }
        origins.clear();
        this.running = false;
    }

    /**
     * Tests whether a URI comes from a source with a higher priority than
     * another one, and thus may shadow it. Whether or not the content of
     * <code>override</code> actually overrides the content of
     * <code>shadowed</code>
     * 
     * @param override
     *            the URI of the resource which may mask the other.
     * @param shadowed
     *            the URI of the resource which my be masked by the other.
     * @return <code>true</code> if both URIs were detected by monitors from
     *         this composite, and <code>override</code> was detected by a
     *         monitor which has a higher priority than the one which detected
     *         <code>shadowed</code>, <code>false</code> otherwise.
     */
    public boolean canMask(URI override, URI shadowed) {
        return compare(override, shadowed) > 0;
    }

    /**
     * Forwards the notifications received from the our registered sources to
     * our own listener. The origin of the notification is kept: this composite
     * is transparent.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public synchronized void resourceEvent(ViewpointResourceMonitor origin, Set<URI> removed, Set<URI> added, Set<URI> changed) {
        if (this.listener != null) {
            for (URI a : Iterables.concat(added, changed)) {
                origins.put(a, origin);
            }

            this.listener.resourceEvent(origin, removed, added, changed);

            // Only forget about the removes URIs after the listener has been
            // notified, as it may still want to use us to compare() removed
            // URIs with other ones.
            for (URI r : removed) {
                origins.remove(r);
            }
        }
    }

    /**
     * Compares two URIs according to the priority of their origin. A URI is
     * considered to be higher than another one if it comes from a higher
     * priority source, and thus can potentially mask the other.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public synchronized int compare(URI uri1, URI uri2) {
        ViewpointResourceMonitor src1 = origins.get(uri1);
        ViewpointResourceMonitor src2 = origins.get(uri2);
        if (src1 != null && src2 != null) {
            int index1 = monitors.indexOf(src1);
            int index2 = monitors.indexOf(src2);
            if (index1 != -1 && index2 != -1) {
                return index1 - index2;
            }
        }
        throw new IllegalArgumentException(Messages.CompositeResourceMonitor_uriCompareErrorMsg);
    }
}

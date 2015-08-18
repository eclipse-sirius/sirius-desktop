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
package org.eclipse.sirius.diagram.ui.internal.view.factories;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;

/**
 * Hint for the location of a view.
 * 
 * @author ymortier
 */
public final class ViewLocationHint {
    /**
     * The key to use to store the anchor location for the source end of a
     * connection.
     */
    public static final String SOURCE_ANCHOR_LOCATION = "sourceConnectionAnchorLocation"; //$NON-NLS-1$

    /**
     * The key to use to store the anchor location for the target end of a
     * connection.
     */
    public static final String TARGET_ANCHOR_LOCATION = "targetConnectionAnchorLocation"; //$NON-NLS-1$

    /** the shared instance. */
    private static ViewLocationHint instance = new ViewLocationHint();

    private static final int QUEUE_SIZE = 3;

    /**
     * A global store to pass information between unrelated parts of the code.
     */
    private final ConcurrentMap<Object, Object> memento = new ConcurrentHashMap<Object, Object>();

    /** the location. */
    private Queue<Point> locations = new LinkedList<Point>();

    /** The Hint Data. */
    private Map<Object, Point> hintData = new HashMap<Object, Point>();

    /** Maps a location with its hint data. */
    private Map<Point, Object> inverseHintData = new HashMap<Point, Object>();

    /** tells if the location hint is locked. */
    private boolean isLocked;

    /**
     * Avoid instantiation.
     */
    private ViewLocationHint() {
        isLocked = false;
    }

    /**
     * Define the location.
     * 
     * @param location
     *            the location.
     */
    public synchronized void setLocation(final Point location) {
        this.setLocation(location, null);
    }

    /**
     * Store a piece of data associated to a key for later retrieval?.
     * 
     * @param key
     *            the key.
     * @param value
     *            the value to store.
     */
    public void putData(final Object key, final Object value) {
        memento.put(key, value);
    }

    /**
     * Retrieve a piece of data previously stored by calling
     * {@link #putData(Object, Object)}. The data is not removed.
     * 
     * @param key
     *            the key which was associated with the data when it was stored.
     * @return the data previously stored with this key, or <code>null</code> if
     *         there was none.
     */
    public Object getData(final Object key) {
        return memento.get(key);
    }

    /**
     * Retrieve a piece of data previously stored by calling
     * {@link #putData(Object, Object)} and remove it.
     * 
     * @param key
     *            the key which was associated with the data when it was stored.
     * @return the data previously stored with this key, or <code>null</code> if
     *         there was none.
     */
    public Object removeData(final Object key) {
        return memento.remove(key);
    }

    /**
     * Define the location.
     * 
     * @param location
     *            the location.
     * @param hint
     *            data to retrieve the location.
     */
    public synchronized void setLocation(final Point location, final Object hint) {
        if (!isLocked) {
            this.locations.add(location);
            if (this.locations.size() > QUEUE_SIZE) {
                final Point overflowedLocation = this.locations.poll();
                if (this.inverseHintData.containsKey(overflowedLocation)) {
                    final Object overflowedHintData = this.inverseHintData.get(overflowedLocation);
                    this.inverseHintData.remove(overflowedLocation);
                    this.hintData.remove(overflowedHintData);
                }
            }
            if (hint != null) {
                final Point locationPoint = this.hintData.get(hint);
                if (locationPoint != null) {
                    this.inverseHintData.remove(locationPoint);
                    this.locations.remove(locationPoint);
                }
                this.hintData.put(hint, location);
                this.inverseHintData.put(location, hint);
            }
        }
    }

    /**
     * Lock this instance. In other terms:
     * <p>
     * When the {@link ViewLocationHint} singleton is locked the location hint
     * is never modified. The singleton is unlocked when a client call the
     * {@link ViewLocationHint#consumeLocation()} method.
     * </p>
     */
    public synchronized void lock() {
        this.isLocked = true;
    }

    /**
     * Consume a location.
     * 
     * @return the location if it exists or <code>null</code> otherwise.
     */
    public synchronized Point consumeLocation() {
        return consumeLocation(null);

    }

    /**
     * Consumes a location.
     * 
     * @param hint
     *            the hint data.
     * @return the location if it exists or <code>null</code> otherwise.
     */
    public synchronized Point consumeLocation(final Object hint) {
        Point loc = null;
        if (!this.locations.isEmpty()) {
            if (hint != null) {
                loc = this.hintData.get(hint);
                if (loc == null) {
                    if (hint instanceof AbstractGraphicalEditPart) {
                        // Search in compartment editPart of this hint
                        loc = getLocationFromCompartmentParent((AbstractGraphicalEditPart) hint);
                    }
                    if (loc == null) {
                        // Search in inverseHintData
                        loc = getLocationFromInverseData(hint);
                    }
                } else {
                    remove(hint, loc);
                }

            }
            if (loc == null) {
                //
                // try to find a free location.
                final Iterator<Point> points = this.locations.iterator();
                while (points.hasNext()) {
                    final Point next = points.next();
                    if (!this.inverseHintData.containsKey(next)) {
                        loc = next;
                        points.remove();
                        break;
                    }
                }
                if (loc == null) {
                    // gets a location but do not consume it !
                    loc = this.locations.peek();
                }
            }
        }
        this.isLocked = false;
        return loc;
    }

    /**
     * Search in inverse data.
     * 
     * @param hint
     *            The container editPart
     * @return the location found in inverseData, null otherwise
     */
    private Point getLocationFromInverseData(final Object hint) {
        Point loc = null;
        for (final Entry<Point, Object> iterInverse : this.inverseHintData.entrySet()) {
            if (iterInverse.getValue() == hint) {
                // YES ! We are very lucky !!
                loc = iterInverse.getKey();
            }
        }
        if (loc != null) {
            this.inverseHintData.remove(loc);
        }
        return loc;
    }

    /**
     * Search if the hint is parent of a CompartmentEditPart of the hintData.
     * Indeed sometimes the location is added with the CompartmentEditPart and
     * consume with the parent
     * 
     * @param hint
     *            The container editPart
     * @return the location of the CompartmentEditPart (if CompartmentEditPart
     *         is child of hint), null otherwise
     */
    private Point getLocationFromCompartmentParent(final AbstractGraphicalEditPart hint) {
        Point loc = null;
        final CompartmentEditPart children = searchForCompartmentParent(hint);
        if (children != null) {
            loc = this.hintData.get(children);
            remove(children, loc);
        }
        return loc;
    }

    /**
     * Removes all the references of hint and loc.
     * 
     * @param hint
     *            The container editPart to remove
     * @param loc
     *            The location to remove
     */
    private void remove(final Object hint, final Point loc) {
        this.hintData.remove(hint);
        this.inverseHintData.remove(loc);
        this.locations.remove(loc);
    }

    /**
     * Search in the hintData if there is a compartmentEditPart which has hint
     * as parent.
     * 
     * @param hint
     *            The container editPart
     * @return the compartmentEditPart if one has hint as parent, null otherwise
     */
    private CompartmentEditPart searchForCompartmentParent(final AbstractGraphicalEditPart hint) {
        for (Object recordedHint : this.hintData.keySet()) {
            if (recordedHint instanceof CompartmentEditPart) {
                if (((CompartmentEditPart) recordedHint).getParent().equals(hint)) {
                    return (CompartmentEditPart) recordedHint;
                }
            }
        }
        return null;
    }

    /**
     * Return the shared instance.
     * 
     * @return the shared instance.
     */
    public static ViewLocationHint getInstance() {
        return instance;
    }

}

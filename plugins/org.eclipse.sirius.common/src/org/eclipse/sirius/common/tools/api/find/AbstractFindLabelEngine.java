/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.find;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract state machine which filters a list of labels according to a search
 * criterion and return the labels step by step.
 * 
 * @author glefur
 */
public abstract class AbstractFindLabelEngine {

    /**
     * Search forward ...
     */
    public static final int FORWARD = 1;

    /**
     * Search backward.
     */
    public static final int BACKWARD = 0;

    private List<?> lastFilteredLabels;

    private String lastSearchText;

    private int lastSearchIndex;

    private int direction;

    /**
     * Default constructor.
     */
    public AbstractFindLabelEngine() {
        lastSearchIndex = 0;
        lastSearchText = ""; //$NON-NLS-1$
        lastFilteredLabels = new ArrayList<>();
        direction = FORWARD;
    }

    /**
     * Filters the labels list according to the search criterion. <BR/>
     * Do nothing if the search criterion hasn't changed since the last request.
     * Reinitializes the search index otherwise
     * 
     * @param search
     *            the search criterion
     */
    public void initFind(final String search) {
        if (lastSearchText != null && !lastSearchText.equals(search)) {
            lastFilteredLabels = filterLabels(search);
            lastSearchText = search;
            lastSearchIndex = -1;
        }
    }

    /**
     * Uses the search criterion to select the matching label. This is the only
     * method that the sub-classes should define
     * 
     * @param search
     *            the search criterion
     * @return the list of matching labels (EditPart)
     */
    protected abstract List<?> filterLabels(String search);

    /**
     * Returns the next matching element according to the DIRECTION. Return null
     * if there isn't any.
     * 
     * @return the next element
     */
    public Object getNext() {
        if (lastFilteredLabels != null && lastFilteredLabels.size() > 0) {
            if (lastSearchIndex == -1) {
                lastSearchIndex = 0;
            } else {
                if (direction == FORWARD) {
                    lastSearchIndex = (lastSearchIndex + 1) % lastFilteredLabels.size();
                } else {
                    lastSearchIndex = (lastSearchIndex - 1 + lastFilteredLabels.size()) % lastFilteredLabels.size();
                }
            }
            if (lastSearchIndex < lastFilteredLabels.size()) {
                return lastFilteredLabels.get(lastSearchIndex);
            }
        }
        return null;

    }

    /**
     * Defines the search direction.
     * 
     * @param direction
     *            the direction to set
     */
    public void setDirection(final int direction) {
        if (direction == FORWARD || direction == BACKWARD) {
            this.direction = direction;
        }
    }

}

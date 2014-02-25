/*******************************************************************************
 * Copyright (c) 2010, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * Records all requests that are sent to the given edit part and all its
 * children.
 * 
 * @author ymortier
 */
public class ChangeBoundRequestRecorder {

    /** All recorded requests. */
    private Multimap<EditPart, ChangeBoundsRequest> allRequests = HashMultimap.create();

    /** <code>true</code> if the recorder is recording. */
    private boolean recording;

    /**
     * Creates a new Request recorder. All requests sent to <code>root</code>
     * and its children will be registered.
     * 
     */
    public ChangeBoundRequestRecorder() {
    }

    /**
     * Start to record the ChangeBoundRequests.
     */
    public void startRecording() {
        recording = true;
        allRequests = HashMultimap.create();
    }

    /**
     * Stops the recorder.
     */
    public void stopRecording() {
        recording = false;
    }

    /**
     * Returns <code>true</code> if the recorder is recording.
     * 
     * @return <code>true</code> if the recorder is recording.
     */
    public boolean isRecording() {
        return recording;
    }

    /**
     * Returns all recorded requests.
     * 
     * @return all recorded requests.
     */
    public Multimap<EditPart, ChangeBoundsRequest> getAllRequests() {
        return this.allRequests;
    }

    /**
     * Tell the recorder to process the request. If active it will record it,
     * otherwise it will just ignore it.
     * 
     * @param request
     *            request to process.
     * @param host
     *            the editpart which has received the request.
     */
    public void processRequest(Request request, EditPart host) {
        if (isRecording() && request instanceof ChangeBoundsRequest) {
            allRequests.put(host, (ChangeBoundsRequest) request);
        }

    }

    /**
     * Disposes the recorder.
     */
    public void dispose() {
        if (isRecording()) {
            this.stopRecording();
        }
        if (this.allRequests != null) {
            this.allRequests.clear();
        }
        this.allRequests = null;
    }

}

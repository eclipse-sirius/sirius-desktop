/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.sequence.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.action.AbstractExternalJavaAction;

import com.google.common.collect.Maps;

/**
 * An action which does nothing except record all its invocations (with context
 * and parameters) or later introspection/verification (by tests for example).
 * 
 * @author pcdavid
 */
public class InvocationRecorderAction extends AbstractExternalJavaAction {
    /**
     * A record for all the data relevant to a single invocation of the action.
     * 
     * @author pcdavid
     */
    public static class InvocationData {
        private final long timestamp;

        private final EObject context;

        private final Map<String, Object> parameters;

        /**
         * Constructor.
         * 
         * @param context
         *            the value of "self" for this invocation.
         * @param parameters
         *            the parameters of the invocation.
         */
        public InvocationData(EObject context, Map<String, Object> parameters) {
            this.timestamp = System.currentTimeMillis();
            this.context = context;
            this.parameters = Collections.unmodifiableMap(Maps.newHashMap(parameters));
        }

        /**
         * The time of the invocation.
         * 
         * @return the time of the invocation.
         */
        public long getTimestamp() {
            return timestamp;
        }

        /**
         * The value of "self" for this invocation.
         * 
         * @return the value of "self" for this invocation.
         */
        public EObject getContext() {
            return context;
        }

        /**
         * The parameters of the invocation.
         * 
         * @return the parameters of the invocation.
         */
        public Map<String, Object> getParameters() {
            return parameters;
        }
    }

    /**
     * A flag to indicate whether or not we are recording the invocations.
     */
    private static final AtomicBoolean RECORDING = new AtomicBoolean(false);

    private static final List<InvocationData> LOG = new ArrayList<>();

    /**
     * Clears the log of previously stored invocation data.
     */
    public static void reset() {
        LOG.clear();
    }

    /**
     * Start recording the invocations in the log.
     */
    public static void startRecording() {
        RECORDING.set(true);
    }

    /**
     * Stop recording the invocations in the log.
     */
    public static void stopRecording() {
        RECORDING.set(false);
    }

    /**
     * Returns the list of recorded invocations (in the order of invocation).
     * 
     * @return the list of recorded invocations.
     */
    public List<InvocationData> getRecord() {
        return LOG;
    }

    /**
     * Can always be executed. {@inheritDoc}
     */
    public boolean canExecute(Collection<? extends EObject> selections) {
        return true;
    }

    /**
     * If recording mode is enabled, add a new entry to the log for this
     * invocation with all the relevant data.
     * <p>
     * {@inheritDoc}
     */
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        if (RECORDING.get()) {
            EObject self = selections.isEmpty() ? null : selections.iterator().next();
            LOG.add(new InvocationData(self, parameters));
        }
    }
}

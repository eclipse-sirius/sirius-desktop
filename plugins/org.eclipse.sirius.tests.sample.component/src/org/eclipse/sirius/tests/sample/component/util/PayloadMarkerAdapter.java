/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.component.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;

/**
 * Marks a model element and its descendants as "payload", indicating that this
 * element and all its descendants should normally not be accessed during normal
 * operation. Also provides a journal where such unexpected accesses can be
 * logged.
 * 
 * @author pcdavid
 */
public class PayloadMarkerAdapter extends EContentAdapter {
    public static PayloadMarkerAdapter INSTANCE = new PayloadMarkerAdapter();

    public static class FeatureAccess {
        public final long timestamp;

        public final EStructuralFeature.Setting setting;

        public final String context;

        public static FeatureAccess of(Setting s, String context) {
            return new FeatureAccess(System.currentTimeMillis(), s, context);
        }

        private FeatureAccess(long accessTimestamp, Setting settingAccessed, String context) {
            this.timestamp = accessTimestamp;
            this.setting = settingAccessed;
            this.context = context;
        }

        @Override
        public String toString() {
            return "Unexpected access to " + EcoreUtil.getURI(setting.getEObject()) + "->" + setting.getEStructuralFeature().getName();
        }
    }

    private final List<FeatureAccess> accessLog = new ArrayList<PayloadMarkerAdapter.FeatureAccess>();

    private final Multiset<String> uniqueContexts = ConcurrentHashMultiset.create();

    private boolean enabled = true;

    public static void install(EObject target) {
        for (Adapter a : target.eAdapters()) {
            if (a instanceof PayloadMarkerAdapter) {
                return;
            }
        }
        target.eAdapters().add(PayloadMarkerAdapter.INSTANCE);
    }

    public static boolean isPayload(EObject o) {
        return o != null && getPayloadMarker(o) != null;
    }

    public static PayloadMarkerAdapter getPayloadMarker(EObject o) {
        for (Adapter a : o.eAdapters()) {
            if (a instanceof PayloadMarkerAdapter) {
                return (PayloadMarkerAdapter) a;
            }
        }
        return null;
    }

    public void setEnable(boolean enabled) {
        this.enabled = enabled;
    }

    public synchronized void logAccess(Setting s) {
        if (enabled) {
            String context = computeContext();
            FeatureAccess fa = FeatureAccess.of(s, context);
            accessLog.add(fa);
            uniqueContexts.add(context);
        }
    }

    private static String computeContext() {
        Throwable e = new RuntimeException().fillInStackTrace();
        StackTraceElement[] stack = e.getStackTrace();
        StringBuilder sb = new StringBuilder();
        boolean tooDeep = false;
        for (int i = 0; i < stack.length; i++) {
            StackTraceElement elt = stack[i];
            if (!tooDeep && (elt.getClassName().equals("org.eclipse.swt.widgets.RunnableLock") && elt.getMethodName().equals("run"))
                    || (elt.getClassName().equals("org.eclipse.core.runtime.SafeRunner") && elt.getMethodName().equals("run"))) {
                tooDeep = true;
            }
            if (!tooDeep && !elt.getClassName().startsWith(PayloadMarkerAdapter.class.getName())) {
                sb.append(elt.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    public synchronized List<FeatureAccess> getAccessLog() {
        return Collections.unmodifiableList(new ArrayList<FeatureAccess>(accessLog));
    }

    public Multiset<String> getUniqueContexts() {
        return ImmutableMultiset.copyOf(uniqueContexts);
    }

    public synchronized void clearAccessLog() {
        accessLog.clear();
        uniqueContexts.clear();
    }

}

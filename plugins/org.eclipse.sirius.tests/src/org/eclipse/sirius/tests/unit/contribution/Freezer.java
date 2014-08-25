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
package org.eclipse.sirius.tests.unit.contribution;

import static org.junit.Assert.fail;

import java.text.MessageFormat;
import java.util.NoSuchElementException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * A helper to detect unwanted changes on EObject during tests.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class Freezer extends AdapterImpl {
    public static void freeze(Iterable<EObject> objs) {
        for (EObject obj : objs) {
            freeze(obj);
        }
    }

    public static void freeze(EObject obj) {
        if (!Iterables.any(obj.eAdapters(), Predicates.instanceOf(Freezer.class))) {
            obj.eAdapters().add(new Freezer());
        }
    }

    public static void thaw(Iterable<EObject> objs) {
        for (EObject obj : objs) {
            thaw(obj);
        }
    }

    public static void thaw(EObject obj) {
        try {
            Adapter f = Iterables.find(obj.eAdapters(), Predicates.instanceOf(Freezer.class));
            obj.eAdapters().remove(f);
        } catch (NoSuchElementException nsee) {
            // Ignore.
        }
    }

    @Override
    public void notifyChanged(Notification msg) {
        boolean isThawing = msg.getEventType() == Notification.REMOVING_ADAPTER && msg.getOldValue() == this;
        boolean isResolving = msg.getEventType() == Notification.RESOLVE;
        if (!isThawing && !isResolving) {
            fail(MessageFormat.format("Changes not allowed on frozen object {0}.", target));
        }
    }
}

/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.dialect;

import org.eclipse.emf.common.notify.impl.AdapterImpl;

/**
 * A marker to do some operations after first diagram opening.
 * 
 * @author mchauvin
 */
final class NotYetOpenedDiagramAdapter extends AdapterImpl {

    public static final NotYetOpenedDiagramAdapter INSTANCE = new NotYetOpenedDiagramAdapter();

    private NotYetOpenedDiagramAdapter() {

    }

}

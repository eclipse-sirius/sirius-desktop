/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree.ui.tools.api.editor;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.swt.widgets.Control;

/**
 * {@link DialectEditor} for TreeDialect.
 * 
 * @author <a href="mailto:mariot.chauvin@obeo.fr">Mariot Chauvin</a>
 */
public interface DTreeEditor extends DialectEditor {

    /**
     * Get the associated SWT control.
     * 
     * @return the associated control
     */
    Control getControl();

    /**
     * Return the adapter factory used for providing views of the model of this
     * editor.
     * 
     * @return the adapter factory used for providing views of the model of this
     *         editor.
     */
    AdapterFactory getAdapterFactory();

}

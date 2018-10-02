/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.editor;

import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.ISaveablesSource;
import org.eclipse.ui.ide.IGotoMarker;

/**
 * This interface defines a Sirius editor that is a unit saving among others with user prompt capability for saving and
 * that provides an editing domain. It also provides go to functionnality.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public interface ISiriusEditor extends IEditorPart, IGotoMarker, IEditingDomainProvider, ISaveablesSource, ISaveablePart2 {

}

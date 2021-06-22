/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.metamodel.table;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Line Container</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.LineContainer#getLines <em>Lines</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getLineContainer()
 * @model abstract="true"
 * @generated
 */
public interface LineContainer extends DSemanticDecorator {
    /**
     * Returns the value of the '<em><b>Lines</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.DLine}. It is bidirectional and its opposite is
     * '{@link org.eclipse.sirius.table.metamodel.table.DLine#getContainer <em>Container</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Lines</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Lines</em>' containment reference list.
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getLineContainer_Lines()
     * @see org.eclipse.sirius.table.metamodel.table.DLine#getContainer
     * @model opposite="container" containment="true"
     * @generated
     */
    EList<DLine> getLines();

} // LineContainer

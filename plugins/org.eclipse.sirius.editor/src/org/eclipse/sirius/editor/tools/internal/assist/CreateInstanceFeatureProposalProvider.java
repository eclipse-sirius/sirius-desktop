/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.editor.tools.internal.assist;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.TypeName;
import org.eclipse.sirius.common.ui.tools.internal.interpreter.FeatureProposalProvider;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;

/**
 * A specific {@link CreateInstance} {@link FeatureProposalProvider} to make possible to restrict the feature list
 * according to the type.
 * 
 * @author fbarbin
 *
 */
public class CreateInstanceFeatureProposalProvider extends FeatureProposalProvider {

    @Override
    protected Collection<EClass> getExpectedFeatureType(IInterpreterContext context) {
        EObject element = context.getElement();
        if (element instanceof CreateInstance) {
            String typeString = ((CreateInstance) element).getTypeName();
            TypeName typeName = TypeName.fromString(typeString);
            return typeName.search(context.getAvailableEPackages()).stream().filter(EClass.class::isInstance).map(EClass.class::cast).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}

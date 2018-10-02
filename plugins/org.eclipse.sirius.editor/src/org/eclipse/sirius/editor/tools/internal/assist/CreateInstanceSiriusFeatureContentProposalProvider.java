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

import org.eclipse.sirius.common.ui.tools.internal.interpreter.FeatureProposalProvider;

/**
 * A Specific {@link SiriusFeatureContentProposalProvider} for the CreateInstance reference name. It overrides the
 * {@link FeatureProposalProvider} to make possible to restrict the feature proposal according to the type.
 * 
 * @author fbarbin
 *
 */
public class CreateInstanceSiriusFeatureContentProposalProvider extends SiriusFeatureContentProposalProvider {
    @Override
    protected FeatureProposalProvider createFeatureProposalProvider() {
        return new CreateInstanceFeatureProposalProvider();
    }
}

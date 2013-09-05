/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.contribution;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * An adapter which can be used to attach an {@link IncrementalModelContributor}
 * to an arbitrary {@link EObject} at runtime. The attachment is not persistent.
 * 
 * @author pierre-charles.david@obeo.fr
 */
// CHECKSTYLE:OFF (work in progress)
public class ModelContributorAdapter extends AdapterImpl {
    private final IncrementalModelContributor imc;

    public static ModelContributorAdapter attach(EObject target, IncrementalModelContributor imc) {
        ModelContributorAdapter result = new ModelContributorAdapter(imc);
        target.eAdapters().add(result);
        return result;
    }

    public static ModelContributorAdapter detach(EObject target) {
        ModelContributorAdapter mca = find(target);
        if (mca != null) {
            target.eAdapters().remove(mca);
        }
        return mca;
    }

    public static ModelContributorAdapter find(EObject target) {
        for (Adapter adapter : target.eAdapters()) {
            if (adapter instanceof ModelContributorAdapter) {
                return (ModelContributorAdapter) adapter;
            }
        }
        return null;
    }

    public ModelContributorAdapter(IncrementalModelContributor imc) {
        this.imc = imc;
    }

    public IncrementalModelContributor getModelContributor() {
        return imc;
    }
}

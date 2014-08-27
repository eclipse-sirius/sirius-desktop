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
package org.eclipse.sirius.tests.swtbot.services;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;

/**
 * {@link IExternalJavaAction} used to test selection on creation.
 * 
 * see Doremi-2587.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ExternalJavaActionForDDiagramElementCreation implements IExternalJavaAction {

    private static final String TYPE_PARAM = "type";

    private static final String EDGE_SOURCE = "source";

    private static final String EDGE_TARGET = "target";

    private static final String NODE_TYPE_PARAM_VALUE = "Node";

    private static final String EDGE_TYPE_PARAM_VALUE = "Edge";

    private static final String NB_PARAM = "nbOfClass";

    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        if (!selections.isEmpty() && parameters != null) {
            Object typeObjectParam = parameters.get(TYPE_PARAM);
            Object nbObjectParam = parameters.get(NB_PARAM);
            if (typeObjectParam instanceof String && nbObjectParam instanceof Integer) {
                String typeParam = String.valueOf(typeObjectParam);
                int nbOfClass = (Integer) nbObjectParam;
                if (NODE_TYPE_PARAM_VALUE.equals(typeParam)) {
                    EObject selection = selections.iterator().next();
                    if (selection instanceof EPackage) {
                        EPackage ePackage = (EPackage) selection;
                        for (int i = 0; i < nbOfClass; i++) {
                            EClass eClass = EcoreFactory.eINSTANCE.createEClass();
                            eClass.setName("newEClass" + i);
                            ePackage.getEClassifiers().add(eClass);
                        }
                    }
                } else if (EDGE_TYPE_PARAM_VALUE.equals(typeParam)) {
                    Object edgeSource = parameters.get(EDGE_SOURCE);
                    Object edgeTarget = parameters.get(EDGE_TARGET);
                    if (edgeSource instanceof EClass && edgeTarget instanceof EClass) {
                        EClass eClassSource = (EClass) edgeSource;
                        EClass eClassTarget = (EClass) edgeTarget;
                        for (int i = 0; i < nbOfClass; i++) {
                            EReference eReference = EcoreFactory.eINSTANCE.createEReference();
                            eReference.setName("newEReference" + i);
                            eReference.setEType(eClassTarget);
                            eClassSource.getEStructuralFeatures().add(eReference);
                        }
                    }
                    System.out.println();
                }

            }
        }
    }

    public boolean canExecute(Collection<? extends EObject> selections) {
        return true;
    }

}

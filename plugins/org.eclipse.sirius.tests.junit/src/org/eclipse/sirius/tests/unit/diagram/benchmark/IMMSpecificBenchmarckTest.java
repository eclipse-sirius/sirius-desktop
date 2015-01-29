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
package org.eclipse.sirius.tests.unit.diagram.benchmark;

import java.io.File;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.tests.sample.benchmark.InputData;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Contract of a class that tests performance.
 * 
 * @author ymortier
 */
public interface IMMSpecificBenchmarckTest {

    /**
     * Returns the model to test.
     * 
     * @param modelResource
     *            Resource in which to create the model.
     * @return the model to test.
     */
    EObject getNominalModelRootContainer(Resource modelResource);

    /**
     * Returns the minimum model to test.
     * 
     * @param modelResource
     *            Resource in which to create the model.
     * @return the minimum model to test.
     */
    EObject getShortModelRootContainer(Resource modelResource);

    /**
     * Returns the large model to test.
     * 
     * @param modelResource
     *            Resource in which to create the model.
     * @return the large model to test.
     */
    EObject getLargeModelRootContainer(Resource modelResource);

    /**
     * Returns the Accelo viewpoint to use.
     * 
     * @return the Accelo viewpoint to use.
     */
    Viewpoint getAcceleoSirius();

    /**
     * Returns the OCL viewpoint to use.
     * 
     * @return the OCL viewpoint to use.
     */
    Viewpoint getOclSirius();

    /**
     * This will be called in order to created the benchmark "Inputdata".
     * 
     * @param inputName
     *            Name of the input data that is to be created.
     * @param model
     *            Model for which the input data must be created.
     * @return The created input data.
     */
    InputData createSemanticModelInputData(String inputName, EObject model);

    /**
     * Returns the file where to save the result of the test.
     * 
     * @return the file where to save the result of the test.
     */
    File getOutputLocation();

    /**
     * This should return the name of the temporary file that will hold the
     * semantic model.
     * 
     * @return Name of the temporary file.
     */
    String getSemanticModelName();
}

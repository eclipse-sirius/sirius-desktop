/*******************************************************************************
 * Copyright (c) 2020, 2023 Obeo.
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
package org.eclipse.sirius.tests.unit.diagram.format.data.manager.mappingbased;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.MappingBasedSiriusFormatDataManager;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerExistingTargetDiagramTest;

/**
 * An utility class used to configure a {@link MappingBasedSiriusFormatDataManager} test case (e.g
 * {@link MappingBasedSiriusFormatDataManagerExistingTargetDiagramTest}). It allows to compute a EObject->EObject
 * mapping function from a string based one.
 * 
 * @author adieumegard
 */
public class MappingBasedTestConfiguration {

    /**
     * The source model.
     */
    private EObject srcModel;

    /**
     * The target model.
     */
    private EObject targetModel;

    /**
     * The correspondence map using IDs to represent elements.
     */
    private Map<String, String> idsMap;

    /**
     * The ID of the target model root.
     */
    private String targetRootId;

    /**
     * The name of this configuration.
     */
    private String name;

    /**
     * The correspondence map containing EObjects. Computed from {@code idsMap}.
     */
    private Map<EObject, EObject> objectsMap;

    /**
     * The target root computed from {@code targetRootId}.
     */
    private EObject targetRoot;

    /**
     * Base constructor for the test case mapping configuration.
     * 
     * @param srcModel
     *            The model element containing the source diagram elements.
     * @param destModel
     *            The model element containing the target diagram elements.
     * @param idsMap
     *            The ID-based mapping function between source and target elements.
     * @param targetRootId
     *            The ID of the target object.
     * @param name
     *            The name of this configuration.
     */
    public MappingBasedTestConfiguration(EObject srcModel, EObject destModel, Map<String, String> idsMap, String targetRootId, String name) {
        this.srcModel = srcModel;
        this.targetModel = destModel;
        this.targetRootId = (targetRootId == null ? "" : targetRootId);
        if (this.targetRootId.equals("")) {
            targetRoot = destModel;
        }
        this.name = name;
        this.idsMap = idsMap;

        computeEObjectsFilteredMap();
    }

    /**
     * @return Whether or not the configuration is valid.
     */
    public boolean isValid() {
        return targetRoot != null && objectsMap != null;
    }

    /**
     * Compute the {@code objectsMap} mapping function based on the {@code idsMap}.
     */
    private void computeEObjectsFilteredMap() {
        this.objectsMap = new HashMap<EObject, EObject>();
        Resource targetResource = this.targetModel.eResource();
        // Check if root of the model is in the "mapped" element
        addMappedElementsIfExist(targetResource, this.srcModel);
        // Check all descendants
        TreeIterator<EObject> srcContentIterator = this.srcModel.eAllContents();
        while (srcContentIterator.hasNext()) {
            EObject srcObj = srcContentIterator.next();
            addMappedElementsIfExist(targetResource, srcObj);
        }
    }

    private void addMappedElementsIfExist(Resource targetResource, EObject srcObj) {
        String destID = this.idsMap.get(getID(srcObj));
        if (destID != null) {
            EObject destObj = targetResource.getEObject(destID);
            if (targetRootId.equals(getID(destObj))) {
                targetRoot = destObj;
            }
            this.objectsMap.put(srcObj, destObj);
        }
    }

    /**
     * Compute an ID for the {@link EObject}.
     * 
     * @param semanticElement
     *            The element for which to compute an ID.
     * @return The ID of the {@link EObject}
     */
    public static String getID(EObject semanticElement) {
        return EcoreUtil.getURI(semanticElement).fragment();
    }

    /**
     * Compute an ID for the {@link EObject} and appends {@code namePrefix} as a prefix of the last segment.
     * 
     * @param semanticElement
     *            The element for which to compute an ID.
     * @param namePrefix
     *            A string prefix to add to the name of {@code semanticElement}
     * @return The ID of the {@link EObject} modified with {@code namePrefix}
     */
    public static String getID(EObject semanticElement, String namePrefix) {
        URI uri = EcoreUtil.getURI(semanticElement);
        String newLastSegment = namePrefix + uri.lastSegment();
        return uri.trimSegments(1).appendSegment(newLastSegment).fragment();
    }

    /**
     * @return Returns the computed idsMap.
     */
    public Map<EObject, EObject> getObjectsMap() {
        return objectsMap;
    }

    /**
     * @return Returns the target model root element.
     */
    public EObject getTargetRoot() {
        return targetRoot;
    }

    /**
     * @return Return the name of the configuration.
     */
    public String getName() {
        return name;
    }
}

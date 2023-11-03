/*******************************************************************************
 * Copyright (c) 2023 Obeo.
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
package org.eclipse.sirius.tests.unit.table.unit.variables;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * Service for Variable tests.
 * <p>
 * Used by TableVariable.odesign to check variables access
 * </p>
 * 
 * @author nperansin
 */
public class VirtualTableService {

    private static final String GROUP_ANNOTATION = "VirtualTableService#ClassGroup"; //$NON-NLS-1$

    private static final String FEAT_ANNOTATION = "VirtualTableService#Features"; //$NON-NLS-1$

    private static final String MODE_ANNOTATION = "VirtualTableService#Mode"; //$NON-NLS-1$

    private static final String ANNOTATION_EDITABLE = "editable"; //$NON-NLS-1$

    private static final String ANNOTATION_KEY = "key"; //$NON-NLS-1$

    private static final Comparator<DAnnotation> ANNOTATION_SORT = Comparator.comparing(VirtualTableService::getClassGroupsName);

    private static final DescriptionFactory FCT = DescriptionFactory.eINSTANCE;
    
    private static final UMLPackage UML = UMLPackage.eINSTANCE;
    
    private static final List<EAttribute> CLASS_ATTRIB_COLUMNS = Arrays.asList(
            UML.getNamedElement_Name(),
            UML.getNamedElement_Visibility(),
            UML.getClassifier_IsAbstract(),
            UML.getRedefinableElement_IsLeaf());

    public static EObject vtDebug(EObject it) {
        return it;
    }
    
    private static String getClassGroupsName(Class it) {
        if (it.getName() != null && it.getName().length() > 0) {
            return String.valueOf(it.getName().charAt(0));
        }
        return ""; //$NON-NLS-1$
    }

    private static boolean isClassGroups(DAnnotation it) {
        return Objects.equals(GROUP_ANNOTATION, it.getSource());
    }

    private static final Function<String, DAnnotation> getClassGroupAccess(DTable table) {
        Map<String, DAnnotation> annotations = table.getEAnnotations().stream() // only applicable annotations
                .filter(it -> isClassGroups(it) && getClassGroupsName(it) != null) // map by name
                .collect(Collectors.toMap(it -> getClassGroupsName(it), it -> it));

        Function<String, DAnnotation> annotationFactory = key -> createGroupAnnotation(key, table);
        
        return it -> {
            DAnnotation result = annotations.computeIfAbsent(it, annotationFactory);
            // When retrieve for first time, reference must be empty
            result.getReferences().clear();
            return result;
        };
    }

    private static DAnnotation createGroupAnnotation(String key, DTable table) {
        DAnnotation result = FCT.createDAnnotation();
        result.setSource(GROUP_ANNOTATION);
        result.getDetails().put(ANNOTATION_KEY, key);
        table.getEAnnotations().add(result);
        return result;
    }

    public static String getClassGroupsName(DAnnotation it) {
        return it.getDetails().get(ANNOTATION_KEY);
    }

    public static List<DAnnotation> getClassGroupsByInitial(Package root, DTable table) {
        Map<String, DAnnotation> result = new HashMap<>();
        Function<String, DAnnotation> previousAccess = getClassGroupAccess(table);

        // Group all classes by key
        for (Iterator<EObject> i = root.eAllContents(); i.hasNext();) {
            EObject next = i.next();
            if (next instanceof Class) {
                Class value = (Class) next;
                value.setIsAbstract(false);
                DAnnotation annotation = result.computeIfAbsent(getClassGroupsName(value), previousAccess);
                annotation.getReferences().add(value);
            }
        }

        // Remove old annotation
        table.getEAnnotations().removeIf(it -> isClassGroups(it) && !result.containsValue(it));

        return result.values().stream().sorted(ANNOTATION_SORT).collect(Collectors.toList());
    }
    
    public static DAnnotation getFeatureColumnsAnnotation(DTable table) {
        DAnnotation result = table.getDAnnotation(FEAT_ANNOTATION);
        if (result == null) {
            result = FCT.createDAnnotation();
            result.setSource(FEAT_ANNOTATION);
            result.getReferences().addAll(CLASS_ATTRIB_COLUMNS);
            table.getEAnnotations().add(result);
        }
        return result;
    }

    /**
     * Evaluates if a virtual table is editable.
     * 
     * @param it table to evaluate
     * @return if editable
     */
    public static  boolean isVirtualTableEditable(DTable it) {
        DAnnotation annotation = it.getDAnnotation(MODE_ANNOTATION);
        return annotation != null && Boolean.parseBoolean(annotation.getDetails().get(ANNOTATION_EDITABLE));
    }
    
    /**
     * Sets if a virtual table is editable.
     * 
     * @param table to set
     * @param editable mode to set
     * @return table
     */
    public static DTable setVirtualTableEditable(DTable table, boolean editable) {
        DAnnotation annotation = table.getDAnnotation(MODE_ANNOTATION);
        if (annotation != null) {
            annotation = FCT.createDAnnotation();
            annotation.setSource(MODE_ANNOTATION);
            table.getEAnnotations().add(annotation);
        }
        annotation.getDetails().put(ANNOTATION_EDITABLE, String.valueOf(editable));
        return table;
    }
    
    public static List<EAttribute> getVtClassAttributesColumns(EObject it) {
        return CLASS_ATTRIB_COLUMNS;
    }
    
}

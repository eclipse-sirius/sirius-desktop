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
package org.eclipse.sirius.business.internal.migration.resource;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.eclipse.sirius.business.api.color.RGBValuesProvider;
import org.eclipse.sirius.tools.api.ui.color.EnvironmentSystemColorFactory;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.SystemColor;
import org.eclipse.sirius.viewpoint.description.SystemColors;
import org.eclipse.sirius.viewpoint.description.UserColor;
import org.eclipse.sirius.viewpoint.description.UserColorsPalette;
import org.eclipse.sirius.viewpoint.description.UserFixedColor;

/**
 * Class encapsulating all the logic for migrating models considering the 1.0.0
 * to 1.1.0 color changes.
 * 
 * @author cbrun
 * 
 */
public class ColorsMigrationHelper {

    Map<String, UserFixedColor> createdUserColors = Maps.newHashMap();

    /**
     * check if a classifier is representing a color.
     * 
     * @param eType
     *            any classifier.
     * @return true if the classifier represents a color.
     */
    public boolean isColorAttribute(final EClassifier eType) {
        final Collection<String> colorTypes = Lists.newArrayList("DefaultColors", "BundledImageColor", "NamedColor", "ColorMapping");
        return colorTypes.contains(eType.getName());
    }

    /**
     * return a user fixed color corresponding to the red, green and blue values
     * reusing the already created colors for the given resource when possible.
     * 
     * @param resource
     *            the resource uri containing the EObject.
     * 
     * @param r
     *            red .
     * @param g
     *            green.
     * @param b
     *            blue.
     * @return a user fixed color corresponding to the red, green and blue
     *         values.
     */
    public UserFixedColor getOrCreateUserColor(final URI resource, final int r, final int g, final int b) {
        final Integer rgBKey = r + (g + 1000) + (b + 10000);
        final String key = resource.toString() + rgBKey.toString();
        UserFixedColor existing = createdUserColors.get(key);
        if (existing == null) {
            existing = DescriptionFactory.eINSTANCE.createUserFixedColor();
            existing.setBlue(b);
            existing.setGreen(g);
            existing.setRed(r);
            createdUserColors.put(key, existing);
        }
        return existing;
    }

    /**
     * Convert a color attribute.
     * 
     * @param attr
     *            the original Attribute
     * @param matchingAttr
     *            the new feature
     * @param eObject
     *            the original instance.
     * @param copyEObject
     *            the new instance.
     */
    public void convertColorAttribute(final EStructuralFeature attr, final EStructuralFeature matchingAttr, final EObject eObject, final EObject copyEObject) {
        final Object oldValue = eObject.eGet(attr);
        if (oldValue != null) {
            if (oldValue instanceof EObject && "RGBColor".equals(((EObject) oldValue).eClass().getName())) {
                copyEObject.eSet(matchingAttr, transformInstanceColors((EObject) oldValue));
            } else {
                final String colorName = getColorName(oldValue);
                if (isStandardColor(colorName)) {
                    final SystemColor systemColorDescription = EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(colorName);
                    if (copyEObject.eClass().getEPackage() == ViewpointPackage.eINSTANCE) {
                        final RGBValues values = new RGBValuesProvider().getRGBValues(systemColorDescription);
                        copyEObject.eSet(matchingAttr, values);
                    } else {
                        copyEObject.eSet(matchingAttr, systemColorDescription);
                    }
                } else {
                    throw new RuntimeException("Color : " + colorName + " is not known as a standard color");
                }
            }
        }
    }

    /**
     * return the color name from an old value
     * 
     * @param oldValue
     *            any old value from a NamedColor or any other Literals
     *            (DefaultColors...)
     * @return the color name from an old value
     */
    private String getColorName(final Object oldValue) {
        String name = null;
        if (oldValue instanceof EEnumLiteral) {
            name = ((EEnumLiteral) oldValue).getName();
        } else if (oldValue instanceof EObject) {
            final EObject eObject = (EObject) oldValue;
            final EClass eClass = eObject.eClass();
            final EEnumLiteral eEnumLiteral = (EEnumLiteral) eObject.eGet(eClass.getEStructuralFeature("colorName"));
            if (eEnumLiteral != null) {
                name = eEnumLiteral.getName();
            }
        }
        return name;
    }

    /**
     * return true if the name is a color name from the standard palette.
     * 
     * @param name
     *            color name.
     * @return true if the name is a color name from the standard palette.
     */
    public boolean isStandardColor(final String name) {
        return SystemColors.getByName(name) != null;
    }

    /**
     * Transform instance diagram colors to new types.
     * 
     * @param eObject
     *            any EObject from the model
     * @return null if the source object has nothing to do with a color, a
     *         migrated instance otherwise.
     */
    public EObject transformInstanceColors(final EObject eObject) {
        EObject copy = null;
        final String className = eObject.eClass().getName();
        if ("RGBColor".equals(className)) {
            final Integer r = (Integer) eObject.eGet(eObject.eClass().getEStructuralFeature("red"));
            final Integer g = (Integer) eObject.eGet(eObject.eClass().getEStructuralFeature("green"));
            final Integer b = (Integer) eObject.eGet(eObject.eClass().getEStructuralFeature("blue"));
            copy = getOrCreateUserColor(eObject.eResource().getURI(), r, g, b);
        } else if ("NamedColor".equals(className)) {
            final String name = ((EEnumLiteral) eObject.eGet(eObject.eClass().getEStructuralFeature("colorName"))).getName();
            if (isStandardColor(name)) {
                copy = EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(name);
            }
        }
        return copy;
    }

    /**
     * Extract all the UserFixedColors not contained in a resource and put them
     * in a user color palette.
     * 
     * @param modelResource
     *            model to process.
     */
    public void extractCustomDanglingColorsInPalette(final Resource modelResource) {

        final Iterator<EObject> danglingEObjects = getDanglingEObjects(modelResource);

        final Iterator<UserColor> it = Iterators.filter(danglingEObjects, new Predicate() {

            public boolean apply(final Object input) {
                if (input instanceof UserColor) {
                    return ((UserColor) input).eResource() == null;
                }
                return false;
            }

        });

        final Group group = getFirstGroup(modelResource);
        if (group != null) {
            final UserColorsPalette palette = DescriptionFactory.eINSTANCE.createUserColorsPalette();
            palette.setName("Migration Palette");

            int count = 1;
            if (it.hasNext()) {
                group.getUserColorsPalettes().add(palette);

                while (it.hasNext()) {
                    final UserColor color = it.next();
                    color.setName("color" + count);
                    palette.getEntries().add(color);
                    count++;
                }
            }
        }

    }

    private Iterator<EObject> getDanglingEObjects(final Resource modelResource) {
        final Iterator<EObject> danglingEObjects = (getCrossReferencer(modelResource)).keySet().iterator();
        return danglingEObjects;
    }

    private CrossReferencer getCrossReferencer(final Resource modelResource) {
        return new EcoreUtil.CrossReferencer(modelResource) {
            private static final long serialVersionUID = 616050158241084372L;

            // initializer for this anonymous class
            {
                crossReference();
            }

            @Override
            protected boolean crossReference(final EObject eObject, final EReference eReference, final EObject crossReferencedEObject) {
                return crossReferencedEObject.eResource() == null;
            }
        };
    }

    private Group getFirstGroup(final Resource modelResource) {
        final Iterator<Group> it = Iterators.filter(modelResource.getAllContents(), Group.class);
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    /**
     * check if the instance is a scale value.
     * 
     * @param oldValue
     *            instance to check.
     * @return true if the instance is a scale value
     */
    public boolean isScaleValue(final Object oldValue) {
        return oldValue instanceof EObject && "ScaleValue".equals(((EObject) oldValue).eClass().getName());
    }
}

/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.internal.dialect.description;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tree.description.ConditionalTreeItemStyleDescription;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.TreeCreationDescription;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.tree.description.TreeItemStyleDescription;
import org.eclipse.sirius.tree.description.TreeItemTool;
import org.eclipse.sirius.tree.description.TreeNavigationDescription;
import org.eclipse.sirius.tree.description.TreePopupMenu;
import org.eclipse.sirius.tree.description.util.DescriptionSwitch;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

import com.google.common.collect.Sets;

/**
 * A switch that will return the Target Types associated to a given element
 * (here all elements are tree-specific) and feature corresponding to an
 * Interpreted Expression. For example, for a NodeMapping :
 * <p>
 * <li>if the feature is semantic candidate expression, we return the domain
 * class of the first valid container (representation element mapping or
 * representation description).</li>
 * <li>if the feature is any other interpreted expression, we return the domain
 * class associated to this mapping</li>
 * </p>
 * 
 * Can return {@link Options#newNone()} if the given expression does not require
 * any target type (for example, a Popup menu contribution only uses variables
 * in its expressions).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class TreeInterpretedExpressionTargetSwitch extends DescriptionSwitch<Option<Collection<String>>> {

    /**
     * Constant used in switches on feature id to consider the case when the
     * feature must not be considered.
     */
    private static final int DO_NOT_CONSIDER_FEATURE = -1;

    /**
     * The ID of the feature containing the Interpreted expression.
     */
    protected int featureID;

    /**
     * The global switch to delegate the doSwitch method to.
     */
    protected IInterpretedExpressionTargetSwitch globalSwitch;

    private int lastFeatureID;

    /**
     * Default constructor.
     * 
     * @param feature
     *            the feature containing the Interpreted expression
     * @param globalSwitch
     *            the global switch
     */
    public TreeInterpretedExpressionTargetSwitch(EStructuralFeature feature, IInterpretedExpressionTargetSwitch globalSwitch) {
        super();
        this.featureID = feature != null ? feature.getFeatureID() : DO_NOT_CONSIDER_FEATURE;
        lastFeatureID = featureID;
        this.globalSwitch = globalSwitch;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.description.util.DescriptionSwitch#doSwitch(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public Option<Collection<String>> doSwitch(EObject theEObject) {
        Option<Collection<String>> doSwitch = super.doSwitch(theEObject);
        if (doSwitch != null) {
            return doSwitch;
        }
        Collection<String> defaultResult = Sets.newLinkedHashSet();
        return Options.newSome(defaultResult);
    }

    /*
     * @see IInterpretedExpressionTargetSwitch#getFirstRelevantContainer()
     */
    private EObject getFirstRelevantContainer(EObject element) {
        return globalSwitch.getFirstRelevantContainer(element);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.description.util.DescriptionSwitch#caseTreeDescription(org.eclipse.sirius.tree.description.TreeDescription)
     */
    @Override
    public Option<Collection<String>> caseTreeDescription(TreeDescription object) {
        Option<Collection<String>> result = null;
        Collection<String> target = Sets.newLinkedHashSet();
        switch (featureID) {
        case DescriptionPackage.TREE_DESCRIPTION__PRECONDITION_EXPRESSION:
        case DescriptionPackage.TREE_DESCRIPTION__TITLE_EXPRESSION:
        case DO_NOT_CONSIDER_FEATURE:
            target.add(object.getDomainClass());
            result = Options.newSome(target);
            break;
        default:
            break;
        }
        return result;

    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.description.util.DescriptionSwitch#caseTreeItemMapping(org.eclipse.sirius.tree.description.TreeItemMapping)
     */
    @Override
    public Option<Collection<String>> caseTreeItemMapping(TreeItemMapping object) {
        Option<Collection<String>> result = null;
        Collection<String> target = Sets.newLinkedHashSet();
        switch (featureID) {
        case DescriptionPackage.TREE_ITEM_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            result = globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
            break;
        case DescriptionPackage.TREE_ITEM_MAPPING__PRECONDITION_EXPRESSION:
        case DescriptionPackage.TREE_ITEM_MAPPING__SEMANTIC_ELEMENTS:
        case DO_NOT_CONSIDER_FEATURE:
            target.add(object.getDomainClass());
            result = Options.newSome(target);
            break;
        default:
            break;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Option<Collection<String>> caseTreeCreationDescription(TreeCreationDescription toolDescription) {
        Option<Collection<String>> result = null;
        Collection<String> targets = Sets.newLinkedHashSet();
        switch (featureID) {
        case DescriptionPackage.TREE_CREATION_DESCRIPTION__TITLE_EXPRESSION:
        case DescriptionPackage.TREE_CREATION_DESCRIPTION__BROWSE_EXPRESSION:
        case DescriptionPackage.TREE_CREATION_DESCRIPTION__PRECONDITION:
        case DescriptionPackage.TREE_CREATION_DESCRIPTION__ELEMENTS_TO_SELECT:
            for (RepresentationElementMapping mapping : toolDescription.getMappings()) {
                Option<Collection<String>> mappingTypes = globalSwitch.doSwitch(mapping, false);
                if (mappingTypes.some()) {
                    targets.addAll(mappingTypes.get());
                }
            }
            result = Options.newSome(targets);
            break;
        default:
            break;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Option<Collection<String>> caseTreeNavigationDescription(TreeNavigationDescription toolDescription) {
        Option<Collection<String>> result = null;
        Collection<String> targets = Sets.newLinkedHashSet();
        switch (featureID) {
        case DescriptionPackage.TREE_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION:
        case DescriptionPackage.TREE_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION:
        case DescriptionPackage.TREE_NAVIGATION_DESCRIPTION__PRECONDITION:
        case DescriptionPackage.TREE_NAVIGATION_DESCRIPTION__ELEMENTS_TO_SELECT:
            for (RepresentationElementMapping mapping : toolDescription.getMappings()) {
                Option<Collection<String>> mappingTypes = globalSwitch.doSwitch(mapping, false);
                if (mappingTypes.some()) {
                    targets.addAll(mappingTypes.get());
                }
            }
            result = Options.newSome(targets);
            break;
        default:
            break;
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.description.util.DescriptionSwitch#caseTreeItemStyleDescription(org.eclipse.sirius.tree.description.TreeItemStyleDescription)
     */
    @Override
    public Option<Collection<String>> caseTreeItemStyleDescription(TreeItemStyleDescription object) {
        return globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.description.util.DescriptionSwitch#caseConditionalTreeItemStyleDescription(org.eclipse.sirius.tree.description.ConditionalTreeItemStyleDescription)
     */
    @Override
    public Option<Collection<String>> caseConditionalTreeItemStyleDescription(ConditionalTreeItemStyleDescription object) {
        return globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.description.util.DescriptionSwitch#caseTreeItemTool(org.eclipse.sirius.tree.description.TreeItemTool)
     */
    @Override
    public Option<Collection<String>> caseTreeItemTool(TreeItemTool object) {
        return globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Option<Collection<String>> caseTreePopupMenu(TreePopupMenu object) {
        return globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
    }

    /**
     * Changes the behavior of this switch : if true, then the feature will be
     * considered to calculate target types ; if false, then the feature will be
     * ignored.
     * 
     * @param considerFeature
     *            true if the feature should be considered, false otherwise
     */
    public void setConsiderFeature(boolean considerFeature) {
        if (considerFeature) {
            this.featureID = lastFeatureID;
        } else {
            lastFeatureID = this.featureID;
            this.featureID = DO_NOT_CONSIDER_FEATURE;
        }
    }
}

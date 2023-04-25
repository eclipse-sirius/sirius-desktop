/*******************************************************************************
 * Copyright (c) 2011, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.business.internal.dialect.description;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateCrossColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateLineTool;
import org.eclipse.sirius.table.metamodel.table.description.DeleteColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableCreationDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription;
import org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch;

import com.google.common.collect.Iterables;

/**
 * A switch that will return the Target Types associated to a given element
 * (here all elements are table-specific) and feature corresponding to an
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
public class TableInterpretedTargetSwitch extends DescriptionSwitch<Option<Collection<String>>> {

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
    public TableInterpretedTargetSwitch(EStructuralFeature feature, IInterpretedExpressionTargetSwitch globalSwitch) {
        super();
        this.featureID = feature != null ? feature.getFeatureID() : DO_NOT_CONSIDER_FEATURE;
        lastFeatureID = featureID;
        this.globalSwitch = globalSwitch;
    }

    @Override
    public Option<Collection<String>> doSwitch(EObject theEObject) {
        Option<Collection<String>> doSwitch = super.doSwitch(theEObject);
        if (doSwitch != null) {
            return doSwitch;
        }
        Collection<String> defaultResult = new LinkedHashSet<>();
        return Options.newSome(defaultResult);
    }

    /*
     * @see IInterpretedExpressionTargetSwitch#getFirstRelevantContainer()
     */
    private EObject getFirstRelevantContainer(EObject element) {
        return globalSwitch.getFirstRelevantContainer(element);
    }

    /**
     * Returns the {@link LineMapping} associated to the given
     * {@link FeatureColumnMapping}
     * 
     * @param featureColumnMapping
     * @return sibling or container
     */
    private EObject getLineMapping(FeatureColumnMapping featureColumnMapping) {
        // If featureColumnMapping has sibling, consider siblings
        Iterator<LineMapping> siblings = Iterables.filter(featureColumnMapping.eContainer().eContents(), LineMapping.class).iterator();
        if (siblings.hasNext()) {
            return siblings.next();
        }
        // Otherwise, consider first relevant container
        return getFirstRelevantContainer(featureColumnMapping);
    }

    @Override
    public Option<Collection<String>> caseTableDescription(TableDescription object) {
        Option<Collection<String>> result = null;
        Collection<String> target = new LinkedHashSet<>();
        switch (featureID) {
        case DescriptionPackage.TABLE_DESCRIPTION__PRECONDITION_EXPRESSION:
        case DescriptionPackage.TABLE_DESCRIPTION__TITLE_EXPRESSION:
        case DO_NOT_CONSIDER_FEATURE:
            target.add(object.getDomainClass());
            result = Options.newSome(target);
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseLineMapping(LineMapping object) {
        Option<Collection<String>> result = null;
        Collection<String> targets = new LinkedHashSet<>();
        switch (featureID) {
        case DescriptionPackage.LINE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            result = globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
            break;
        case DescriptionPackage.LINE_MAPPING__HEADER_LABEL_EXPRESSION:
        case DescriptionPackage.LINE_MAPPING__SEMANTIC_ELEMENTS:
        case DO_NOT_CONSIDER_FEATURE:
            targets.add(object.getDomainClass());
            result = Options.newSome(targets);
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseElementColumnMapping(ElementColumnMapping object) {
        Option<Collection<String>> result = null;
        Collection<String> targets = new LinkedHashSet<>();
        switch (featureID) {
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            result = globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
            break;
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__HEADER_LABEL_EXPRESSION:
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__SEMANTIC_ELEMENTS:
        case DO_NOT_CONSIDER_FEATURE:
            targets.add(object.getDomainClass());
            result = Options.newSome(targets);
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseFeatureColumnMapping(FeatureColumnMapping object) {
        Option<Collection<String>> result = null;
        switch (featureID) {
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__CAN_EDIT:
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__FEATURE_PARENT_EXPRESSION:
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__HEADER_LABEL_EXPRESSION:
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__LABEL_EXPRESSION:
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__SEMANTIC_ELEMENTS:
        case DO_NOT_CONSIDER_FEATURE:
            result = globalSwitch.doSwitch(getLineMapping(object), false);
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseCellUpdater(CellUpdater object) {
        Option<Collection<String>> result = null;
        switch (featureID) {
        case DescriptionPackage.CELL_UPDATER__CAN_EDIT:
        case DO_NOT_CONSIDER_FEATURE:
            result = globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseIntersectionMapping(IntersectionMapping object) {
        Option<Collection<String>> result = null;
        Collection<String> targets = new LinkedHashSet<>();
        if (object.isUseDomainClass()) {
            // for Domain based IntersectionMappings
            switch (featureID) {
            case DescriptionPackage.INTERSECTION_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
                result = globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
                break;
            case DescriptionPackage.INTERSECTION_MAPPING__COLUMN_FINDER_EXPRESSION:
            case DescriptionPackage.INTERSECTION_MAPPING__LABEL_EXPRESSION:
            case DescriptionPackage.INTERSECTION_MAPPING__CAN_EDIT:
            case DescriptionPackage.INTERSECTION_MAPPING__SEMANTIC_ELEMENTS:
            case DescriptionPackage.INTERSECTION_MAPPING__PRECONDITION_EXPRESSION:
            case DescriptionPackage.INTERSECTION_MAPPING__LINE_FINDER_EXPRESSION:
            case DO_NOT_CONSIDER_FEATURE:
                targets.add(object.getDomainClass());
                result = Options.newSome(targets);
                break;
            default:
                break;
            }
        } else {
            // for relation based InteresectionMappings
            switch (featureID) {
            case DescriptionPackage.INTERSECTION_MAPPING__COLUMN_FINDER_EXPRESSION:
            case DescriptionPackage.INTERSECTION_MAPPING__LABEL_EXPRESSION:
            case DescriptionPackage.INTERSECTION_MAPPING__CAN_EDIT:
            case DescriptionPackage.INTERSECTION_MAPPING__SEMANTIC_ELEMENTS:
            case DescriptionPackage.INTERSECTION_MAPPING__PRECONDITION_EXPRESSION:
            case DO_NOT_CONSIDER_FEATURE:
                for (LineMapping lineMapping : object.getLineMapping()) {
                    Option<Collection<String>> lineMappingTargets = globalSwitch.doSwitch(lineMapping, false);
                    if (lineMappingTargets.some()) {
                        targets.addAll(lineMappingTargets.get());
                    }
                }
                result = Options.newSome(targets);
                break;
            case DescriptionPackage.INTERSECTION_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            case DescriptionPackage.INTERSECTION_MAPPING__LINE_FINDER_EXPRESSION:
                result = Options.newNone();
                break;
            default:
                break;
            }
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseCreateColumnTool(CreateColumnTool object) {
        // getMapping() == eContainer()
        return globalSwitch.doSwitch(object.getMapping(), false);
    }

    @Override
    public Option<Collection<String>> caseCreateCellTool(CreateCellTool object) {
        // getMapping() == eContainer()
        return globalSwitch.doSwitch(object.getMapping(), false);
    }

    @Override
    public Option<Collection<String>> caseCreateCrossColumnTool(CreateCrossColumnTool object) {
        // getMapping() != eContainer()
        return globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
    }

    @Override
    public Option<Collection<String>> caseCreateLineTool(CreateLineTool object) {
        // getMapping() != eContainer()
        return globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
    }

    @Override
    public Option<Collection<String>> caseDeleteColumnTool(DeleteColumnTool object) {
        // getMapping() == eContainer()
        return globalSwitch.doSwitch(object.getMapping(), false);
    }

    @Override
    public Option<Collection<String>> caseDeleteLineTool(DeleteLineTool object) {
        // getMapping() == eContainer()
        return globalSwitch.doSwitch(object.getMapping(), false);
    }

    @Override
    public Option<Collection<String>> caseForegroundConditionalStyle(ForegroundConditionalStyle object) {
        return globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
    }

    @Override
    public Option<Collection<String>> caseForegroundStyleDescription(ForegroundStyleDescription object) {
        return globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
    }

    @Override
    public Option<Collection<String>> caseBackgroundConditionalStyle(BackgroundConditionalStyle object) {
        return globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
    }

    @Override
    public Option<Collection<String>> caseBackgroundStyleDescription(BackgroundStyleDescription object) {
        return globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
    }

    @Override
    public Option<Collection<String>> caseTableCreationDescription(TableCreationDescription object) {
        return globalSwitch.doSwitch(object.getTableDescription(), false);
    }

    @Override
    public Option<Collection<String>> caseTableNavigationDescription(TableNavigationDescription object) {
        return globalSwitch.doSwitch(object.getTableDescription(), false);
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

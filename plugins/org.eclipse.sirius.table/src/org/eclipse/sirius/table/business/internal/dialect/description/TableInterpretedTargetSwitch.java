/*******************************************************************************
 * Copyright (c) 2011, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.internal.dialect.description;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.description.RepresentationDescription;
import org.eclipse.sirius.description.RepresentationElementMapping;
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
import org.eclipse.sirius.table.metamodel.table.description.EditionTableExtensionDescription;
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
public class TableInterpretedTargetSwitch extends DescriptionSwitch<Option<Collection<String>>> implements IInterpretedExpressionTargetSwitch {

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
     *            representationDescription
     * @param globalSwitch
     *            the global switch
     */
    public TableInterpretedTargetSwitch(EStructuralFeature feature, IInterpretedExpressionTargetSwitch globalSwitch) {
        super();
        this.featureID = feature != null ? feature.getFeatureID() : DO_NOT_CONSIDER_FEATURE;
        lastFeatureID = featureID;
        this.globalSwitch = globalSwitch;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch#doSwitch(org.eclipse.emf.ecore.EObject,
     *      boolean)
     */
    public Option<Collection<String>> doSwitch(EObject target, boolean considerFeature) {
        setConsiderFeature(considerFeature);
        Option<Collection<String>> doSwitch = super.doSwitch(target);
        if (doSwitch != null) {
            return doSwitch;
        }
        Collection<String> targets = Sets.newLinkedHashSet();
        return Options.newSome(targets);
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

    /**
     * Returns the first relevant for the given EObject, i.e. the first
     * container from which a domain class can be determined.
     * <p>
     * For example, for a given LineMapping will return the first LineMapping or
     * TableDescription that contains this mapping.
     * </p>
     * 
     * @param element
     *            the element to get the container from
     * @return the first relevant for the given EObject, i.e. the first
     *         container from which a domain class can be determined
     */
    protected EObject getFirstRelevantContainer(EObject element) {
        EObject container = element.eContainer();
        while ((!(container instanceof RepresentationDescription)) && (!(container instanceof RepresentationElementMapping))) {
            container = container.eContainer();
        }
        return container;
    }

    /**
     * Returns the {@link LineMapping} associated to the given
     * {@link FeatureColumnMapping}
     * 
     * @param object
     * @return
     */
    private EObject getLineMapping(FeatureColumnMapping featureColumnMapping) {
        // If featureColumnMapping has slibing, consider sliblings
        Iterator<LineMapping> siblings = Iterables.filter(featureColumnMapping.eContainer().eContents(), LineMapping.class).iterator();
        if (siblings.hasNext()) {
            return siblings.next();
        }
        // Otherwise, consider first relevant container
        return getFirstRelevantContainer(featureColumnMapping);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseTableDescription(org.eclipse.sirius.table.metamodel.table.description.TableDescription)
     */
    @Override
    public Option<Collection<String>> caseTableDescription(TableDescription object) {
        Option<Collection<String>> result = null;
        Collection<String> target = Sets.newLinkedHashSet();
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
    public Option<Collection<String>> caseEditionTableExtensionDescription(EditionTableExtensionDescription object) {
        Option<Collection<String>> result = null;
        Collection<String> target = Sets.newLinkedHashSet();
        switch (featureID) {
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__PRECONDITION_EXPRESSION:
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
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseLineMapping(org.eclipse.sirius.table.metamodel.table.description.LineMapping)
     */
    @Override
    public Option<Collection<String>> caseLineMapping(LineMapping object) {
        Option<Collection<String>> result = null;
        Collection<String> targets = Sets.newLinkedHashSet();
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

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseElementColumnMapping(org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping)
     */
    @Override
    public Option<Collection<String>> caseElementColumnMapping(ElementColumnMapping object) {
        Option<Collection<String>> result = null;
        Collection<String> targets = Sets.newLinkedHashSet();
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

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseFeatureColumnMapping(org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping)
     */
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseIntersectionMapping(org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping)
     */
    @Override
    public Option<Collection<String>> caseIntersectionMapping(IntersectionMapping object) {
        Option<Collection<String>> result = null;
        Collection<String> targets = Sets.newLinkedHashSet();
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

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseCreateColumnTool(org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool)
     */
    @Override
    public Option<Collection<String>> caseCreateColumnTool(CreateColumnTool object) {
        return globalSwitch.doSwitch(object.getMapping(), false);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseCreateCellTool(org.eclipse.sirius.table.metamodel.table.description.CreateCellTool)
     */
    @Override
    public Option<Collection<String>> caseCreateCellTool(CreateCellTool object) {
        return globalSwitch.doSwitch(object.getMapping(), false);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseCreateCrossColumnTool(org.eclipse.sirius.table.metamodel.table.description.CreateCrossColumnTool)
     */
    @Override
    public Option<Collection<String>> caseCreateCrossColumnTool(CreateCrossColumnTool object) {
        return globalSwitch.doSwitch(object.getMapping(), false);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseCreateLineTool(org.eclipse.sirius.table.metamodel.table.description.CreateLineTool)
     */
    @Override
    public Option<Collection<String>> caseCreateLineTool(CreateLineTool object) {
        return globalSwitch.doSwitch(object.getMapping(), false);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseDeleteColumnTool(org.eclipse.sirius.table.metamodel.table.description.DeleteColumnTool)
     */
    @Override
    public Option<Collection<String>> caseDeleteColumnTool(DeleteColumnTool object) {
        return globalSwitch.doSwitch(object.getMapping(), false);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseDeleteLineTool(org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool)
     */
    @Override
    public Option<Collection<String>> caseDeleteLineTool(DeleteLineTool object) {
        return globalSwitch.doSwitch(object.getMapping(), false);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseForegroundConditionalStyle(org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle)
     */
    @Override
    public Option<Collection<String>> caseForegroundConditionalStyle(ForegroundConditionalStyle object) {
        return globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseForegroundStyleDescription(org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription)
     */
    @Override
    public Option<Collection<String>> caseForegroundStyleDescription(ForegroundStyleDescription object) {
        return globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseBackgroundConditionalStyle(org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle)
     */
    @Override
    public Option<Collection<String>> caseBackgroundConditionalStyle(BackgroundConditionalStyle object) {
        return globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseBackgroundStyleDescription(org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription)
     */
    @Override
    public Option<Collection<String>> caseBackgroundStyleDescription(BackgroundStyleDescription object) {
        return globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseTableCreationDescription(org.eclipse.sirius.table.metamodel.table.description.TableCreationDescription)
     */
    @Override
    public Option<Collection<String>> caseTableCreationDescription(TableCreationDescription object) {
        return globalSwitch.doSwitch(object.getTableDescription(), false);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch#caseTableNavigationDescription(org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription)
     */
    @Override
    public Option<Collection<String>> caseTableNavigationDescription(TableNavigationDescription object) {
        return globalSwitch.doSwitch(object.getTableDescription(), false);
    }
}
